package dev.enzosoares.consignado.custodia.simulacaoemprestimo.facade;

import dev.enzosoares.consignado.custodia.errors.NotFoundException;
import dev.enzosoares.consignado.custodia.errors.SystemError;
import dev.enzosoares.consignado.custodia.simulacaoemprestimo.SimulacaoEmprestimo;
import dev.enzosoares.consignado.custodia.simulacaoemprestimo.facade.response.GetSimulacaoEmprestimoResponseBody;
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
    final String SIMULACAO_EMPRESTIMO_API = "http://localhost:8080/api/v1/simulacoes/";

    @Override
    public Optional<SimulacaoEmprestimo> fetchById(UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        try {
            GetSimulacaoEmprestimoResponseBody simulacaoEmprestimo = restTemplate
                    .exchange(SIMULACAO_EMPRESTIMO_API + id, HttpMethod.GET, requestEntity, GetSimulacaoEmprestimoResponseBody.class)
                    .getBody();

            assert simulacaoEmprestimo != null;
            return Optional.of(GetSimulacaoEmprestimoResponseBody.toDomain(simulacaoEmprestimo));

        } catch (HttpClientErrorException e) {

            if (e.getStatusCode().value() == 404) return Optional.empty();
            if (e.getStatusCode().value() == 400) throw new NotFoundException("Simulação de empréstimo inválida");
        } catch (Exception e) {

            if (e.getMessage().contains("Connection refused")) {
                throw new SystemError("Serviço de simulação de empréstimo indisponível, por favor tente mais tarde.");
            }

            throw new SystemError("Erro ao buscar simulação de empréstimo");
        }
        return Optional.empty();
    }
}