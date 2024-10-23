Desafio Mais Condomínio
===
![downloads](https://img.shields.io/github/downloads/atom/atom/total.svg)
![build](https://img.shields.io/appveyor/ci/:user/:repo.svg)
![chat](https://img.shields.io/discord/:serverId.svg)


## Resumo do desafio
Você deve implementar um sistema de microserviços para gerenciar pedidos de pratos e bebidas em um restaurante, 
abrangendo desde a listagem dos produtos no cardápio até a entrega final dos pedidos. 
O sistema deve ser escalável e preparado para alta disponibilidade.


## Requisitos de tecnologias a serem utilizados
 - Java
 - Mensageria
 - Eureka Server
 - Spring Cloud
 - Docker Compose


## Requisitos extras
 - Documentação
 - Testes unitários


## Estratégia utilizada no projeto



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

## Estratégia utilizada no projeto
A arquitetura do projeto é composta por 6 microserviços que se comunicam de forma assíncrona via **Kafka** e de forma síncrona via **OpenFeign**. Cada microserviço tem responsabilidades específicas, dividindo o fluxo de um pedido em várias etapas:

<div style="width: 100%; height: 50vh; overflow: hidden; display: flex; justify-content: center; align-items: center;">
    <img src="https://github.com/devjuliomesquita/arquitetura.png" 
    alt="Tela 1" style="width: 100%; height: 100%; object-position: top; object-fit: cover;"/>
</div>

1. **Server (Spring Cloud)**: Fornece um contexto de configuração centralizado para todos os microserviços dentro de um único monorepo, garantindo consistência nas configurações e compartilhamento de informações como o docker.
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

---
Júlio C. Mesquita

###### tags: `Java` `Desing Patterns` `Patterns` `Documentation`