Desafio Mais Condomínio
===
![downloads](https://img.shields.io/github/downloads/atom/atom/total.svg)
![build](https://img.shields.io/appveyor/ci/:user/:repo.svg)
![chat](https://img.shields.io/discord/:serverId.svg)

# Desafio Mais Condomínio

O objetivo deste repositório é realizar o desafio Mais Condomínios. O projeto é uma implementação deem microserviços 
para gerenciar pedidos de pratos e bebidas em um restaurante, abrangendo desde a listagem dos produtos no cardápio 
até a entrega final dos pedidos. O sistema deve ser escalável e preparado para alta disponibilidade.

## Summary | Conteúdo do programático

<details>
<summary>Tópicos do conteúdo</summary>

1. Requisitos Obrigatórios
2. Requisitos extras
3. Estratégia arquitetural
4. Guia do usuário
</details>

---

## Requisitos de tecnologias a serem utilizados
- Java
- Banco relacional
- Mensageria
- Eureka Server
- Spring Cloud
- Docker Compose

## Requisitos extras
- Documentação
- Testes unitários

> [!NOTE]
>
> Mais informações sobre o desafio verificar esse link:
> https://github.com/devjuliomesquita/Desafio_Mais_Cond/blob/main/documents/Enunciado%20para%20Teste%20Pr%C3%A1tico%20-%20Desenvolvedor%20Backend%20Java%20Pleno.pdf

## Estratégia utilizada no projeto
A arquitetura do projeto é composta por 6 microserviços que se comunicam de forma assíncrona via **Kafka** e de forma síncrona via **OpenFeign**. Cada microserviço tem responsabilidades específicas, dividindo o fluxo de um pedido em várias etapas:

<div style="width: 100%; height: 50vh; overflow: hidden; display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/devjuliomesquita/Desafio_Mais_Cond/blob/main/documents/arquitetura.png" 
    alt="Tela 1" style="width: 100%; height: 100%; object-position: top; object-fit: cover;"/>
</div>

1. **Config Server (Spring Cloud)**: Fornece um contexto de configuração centralizado para todos os microserviços dentro de um único monorepo, garantindo consistência nas configurações e compartilhamento de informações como o docker.
2. **Eureka Server**: Este é o service discovery, permitindo que os microserviços se encontrem dinamicamente.
3. **Menu Service**: Responsável por listar os produtos (pratos e bebidas) e as mesas disponíveis para consumo.
4. **Order Service**: Gerencia a criação e atualização de pedidos, monitorando o status à medida que o pedido progride pelos outros serviços.
5. **Preparation Service**: Representa a cozinha, onde os pedidos são preparados.
6. **Delivery Service**: Lida com a fase de entrega, notificando quando o pedido for entregue ao cliente.

A comunicação entre os serviços, do menu, order, preparation e delivery, ocorre via **Kafka** em um fluxo assíncrono, garantindo que a entidade `Order` seja atualizada conforme cada serviço processa sua parte do pedido. Isso permite escalabilidade, desacoplamento e maior tolerância a falhas no sistema.

Clean Arch foi utilizada na construção de cada micro serviço, diminuindo o acoplamento, aumentando o reuso de serviços e facilitando os testes.
Os testes realizados foram de unidade e integração utilizando Junit5 e Mockito. Alguns outros testes que poderiam ter sido feitos são integração subindo o contexto do banco em memória e test conteiners.
Para fins de documentação de API REST foi utilizado o swagger. 

> [!NOTE]
>
> Por conta do tipo de projeto, proposta e praticidade foi utilizado o banco H2 em memória com a opção jpa.hibernate.dll-auto: create-drop
> para criação e deleção do banco automático. Caso fosse utilizado outro banco, como o  Postgres, seria impressindível além do docker para inicialização
> utilizar ferrementas para o gerenciamento de migrations para Flyway ou o Liquibase.
 
## Guia do usuário
### Clonar o projeto
``` git
  git clone https://github.com/devjuliomesquita/Desafio_Mais_Cond.git
```
### Baixar dependências maven
Após clonar o repositório abra na ide de sua preferência e pode rodar o comando:
``` git
  mvn clean install -pl $(find . -name "pom.xml" | sed 's/\/pom.xml//g' | tr '\n' ',')
```
Este comando encontra todos os arquivos *pom.xml* do monorepo e baixa suas depedências.

### Subir o kafka via docker 
Na raiz do projeto você pode navegar até encontrar o *docker-compose.yml*.
``` 
  // Para entrar na pasta docker
  cd docker
  
  //Dentro da pasta docker rodar o comando
  docker compose up -d
```
Este comando subirá um container docker do kafka e do zookeper.

### Inicialização dos micro serviços
 - Inicialize primeiramente o server do spring cloud para subir o contexto de configurações;
 - Inicialize o discovery para que suba o eureka e os demais serviços possam se inscrever;
 - Por fim inicialize os demais serviços. A order lógica sugerida é menu, order, preparation e delivery.

### Acessando o swagger, eureka e H2
#### Links do swagger | Portas escolhidas aleatoriamente
 - Menu: http://localhost:8050/swagger-ui/index.html#/
 - Order: http://localhost:8060/swagger-ui/index.html#/
 - Preparation: http://localhost:8070/swagger-ui/index.html#/
#### Link do eureka
 - http://localhost:8761/
#### Links do H2 | Todos com user e password = sa
- Menu: http://localhost:8050/h2-console/
  - url: jdbc:h2:mem:menu
- Order: http://localhost:8060/h2-console/
    - url: jdbc:h2:mem:order
- Preparation: http://localhost:8070/h2-console/
    - url: jdbc:h2:mem:comanda

> [!WARNING]
>
> Lembre-se o h2 é um banco em memória que é destrido no shutdown da aplicação.

### Como usar
1. Na API Menu verifique quais mesas estão disponíveis e quais produtos você deseja.
2. Após a escolha da mesa e produtos faça adicione os produtos a uma mesa no método POST como por exemplo:
``` json
{
  "tableId": "string",
  "products": [
    {
      "id": "string",
      "quantity": 2
    }
  ]
}
```
3. Este POST criará um pedido com status: *CREATED* e esse pedido será transferido para a cozinha.
4. Após a confirmação de recebimento a cozinha atualizará o status do pedido para: *PREPARED*. Acompanhe seu pedido pelo API Order. 
5. Na API Preparation você pode listar todas as comandas que chegaram.
6. Após o preparo da comanda é possível fecha-lá informado o ID. Assim chamará o serviço de delivery que após receber o status do pedido mudará para: *SENT*
7. Após o pedido ser entregue o status do pedido mudará para *COMPLETED* Simbolizando o término do ciclo de vida do pedido.

> [!NOTE]
>
> No serviço de delivery não temos um domínio de negócio. Então a thread foi pausada por segundos apenas ver melhor a troca de status do pedido. 

---
Júlio C. Mesquita

###### tags: `Java` `Desing Patterns` `Patterns` `Documentation`