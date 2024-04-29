# Case Consignado

Este é um projeto técnico desenvolvido em Spring Boot com uma arquitetura limpa (clean architecture) e um design de arquitetura em nuvem. O projeto pode ser executado usando o Docker Compose.

## Como executar o projeto

1. Certifique-se de ter o Docker e o Docker Compose instalados em sua máquina.

2. Clone este repositório em sua máquina local:

    ```bash
    git clone https://github.com/seu-usuario/nome-do-repositorio.git
    ```

3. Navegue até o diretório raiz do projeto:

    ```bash
    cd nome-do-repositorio
    ```

4. Execute o Docker Compose para construir e iniciar os contêineres do projeto:

    ```bash
    docker-compose up -d
    ```

5. Aguarde até que todos os contêineres sejam iniciados com sucesso.

6. Acesse o projeto em seu navegador usando o seguinte URL:

    ```
    http://localhost:8080
    ```

## Arquitetura

O projeto segue uma arquitetura limpa (clean architecture), que promove a separação de responsabilidades em camadas distintas. A estrutura de diretórios do projeto é organizada da seguinte forma:

- `app`: Contém a camada de aplicação, responsável por orquestrar as ações do sistema.
- `domain`: Contém a camada de domínio, onde estão as regras de negócio e entidades do sistema.
- `infra`: Contém a camada de infraestrutura, responsável por implementar detalhes técnicos, como acesso a banco de dados e serviços externos.
- `interfaces`: Contém as interfaces de entrada e saída do sistema, como APIs REST, interfaces gráficas, etc.

## Imagens

Aqui você pode adicionar imagens relevantes para ilustrar a arquitetura ou qualquer outra parte do projeto.

## Testes

## Documentacao



## Melhorias

Aqui você pode citar possíveis melhorias para o projeto, como otimizações de desempenho, implementação de novas funcionalidades, correção de bugs, etc.
