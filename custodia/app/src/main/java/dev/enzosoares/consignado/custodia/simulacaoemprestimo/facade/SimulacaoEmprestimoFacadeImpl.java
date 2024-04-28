package dev.enzosoares.consignado.custodia.simulacaoemprestimo.facade;

import dev.enzosoares.consignado.custodia.errors.NotFoundException;
import dev.enzosoares.consignado.custodia.errors.SystemError;
import dev.enzosoares.consignado.custodia.simulacaoemprestimo.SimulacaoEmprestimo;
import dev.enzosoares.consignado.custodia.simulacaoemprestimo.facade.response.GetSimulacaoEmprestimoResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
public class SimulacaoEmprestimoFacadeImpl implements SimulacaoEmprestimoFacade {
    @Value("${consignado.simulacao_emprestimo_api}")
    String SIMULACAO_EMPRESTIMO_API ;

    @Override
    public Optional<SimulacaoEmprestimo> fetchById(UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        try {
            GetSimulacaoEmprestimoResponseBody simulacaoEmprestimo = restTemplate
                    .exchange(SIMULACAO_EMPRESTIMO_API + "/api/v1/simulacoes/" + id, HttpMethod.GET, requestEntity, GetSimulacaoEmprestimoResponseBody.class)
                    .getBody();

            assert simulacaoEmprestimo != null;
            return Optional.of(GetSimulacaoEmprestimoResponseBody.toDomain(simulacaoEmprestimo));

        } catch (HttpClientErrorException e) {

            if (e.getStatusCode().value() == 404) return Optional.empty();
            if (e.getStatusCode().value() == 400) throw new NotFoundException("Simulação de empréstimo inválida");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (e.getMessage().contains("Connection refused")) {
                throw new SystemError("Serviço de simulação de empréstimo indisponível, por favor tente mais tarde.");
            }

            throw new SystemError("Erro ao buscar simulação de empréstimo");
        }
        return Optional.empty();
    }
}