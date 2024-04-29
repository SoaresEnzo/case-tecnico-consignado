# Case Consignado

  

Este é um projeto técnico desenvolvido em Java 21 + Spring Boot utilizando uma versão simplificada de Clean Architecture.

O projeto pode ser executado usando o Docker Compose.


## Requisitos para executar
1. Docker
2. Docker-compose
3. Maven(Opcional) 

## Como executar o projeto (Sem maven)

  

1. Certifique-se de ter o Docker e o Docker Compose instalados em sua máquina.

  

2. Clone este repositório em sua máquina local:

  

    ```bash
    git clone https://github.com/SoaresEnzo/case-tecnico-consignado.git
    ```

  

3. Navegue até o diretório raiz do projeto:

  

    ```bash
    cd case-tecnico-consignado
    ```

  

4. Execute o Docker Compose para construir e iniciar os contêineres do projeto:

  

    ```bash
    docker-compose up -d
    ```

  

5. Aguarde até que todos os contêineres sejam iniciados com sucesso.

  

6. As APIs estarão rodando nas portas 8080 e 8081 do seu computador. Acesse o projeto pelo insomnia importando a collection insomnia.json disponível na raiz do projeto ou acesse as urls de documentação para entender como chamar as APIs.

  

- http://localhost:8081/swagger-ui/index.html
- http://localhost:8081/v3/api-docs
- http://localhost:8080/swagger-ui/index.html
- http://localhost:8080/v3/api-docs
  

  

## Arquitetura

  

O projeto segue uma versão simplificada de clean architecture, buscando segregar as responsabilidades da aplicação em camadas diferentes, inspirada no artigo [Descomplicando a Clean Architecture](https://helpdev.com.br/2020/05/21/descomplicando-a-clean-architecture/) .

<img src="https://helpdev.com.br/wp-content/uploads/2020/05/simple-clean-arch.png" alt="imagem ilustrativa da arquitetura">

  

- `app`: Contém a camada de aplicação, responsável por fornecer as implementações técnicas do sistema. É nessa camada que ficam implementações de banco de dados ou de comunicações externas do sistema.

- `core`: É o coração do sistema, contém as entidades e a orquestração das regras de negócio do sistema buscando independência de qualquer framework.
  

## Propostas de solução na AWS

Solução 1
![Solucao sem fila](/Solution1.drawio.svg)

Solução 2
![Solucao com fila](/Solution2.drawio.svg)
  

## Possíveis melhorias

1. (Tech) Utilizar o Consul para comunicação entre microsserviços.
2. (Tech) Colocar o Prometheus e o Grafana no docker-compose para coletar métricas dos microsserviços e monitorar.
3. (Negócios) Criar tabela separada para segmento e APIs para deixar dinâmico o número máximo de parcelas por segmento.
4. (Negócios) Validar se a custódia do contrato já foi feita para uma simulação.
5. (Tech) Deixar o fuso-horário dinâmico para o usuário da API nos retornos de datas e horários.
6. (Tech) Ajustar testes de integração e de implementações de banco de dados para funcionar com @DataJpaTest ao invés de @SpringBootTest