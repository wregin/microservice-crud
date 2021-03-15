# Projeto microserviço utilizando Spring Boot, security, netflix Zuul, netflix Eureka, RabbitMQ e outros.

O projeto é composto por ttrês microserviços sendo esse próprio que é o crud de produto, o [Auth](https://github.com/wregin/auth) para autenticação e o [pagamento](https://github.com/wregin/pagamento), além disso temos também o [discovery]() que será utilizado para os microserviços se registrarem nesse. basicamente é onde o gateway faz a busca do microservice registrado.

Aqui vou procurar resumir o projeto, conforme print abaixo veja que os bancos do crud de produto e pagamento apontam para o localhost, mas a ideia é que isso poderia estar em qualquer outro lugar e de forma que a subida de um não afeta a do outro, isso é, basicamente é tornar esses serviços autônomo mas de forma que trabalhe em conjunto com outros microserviços.

![app config](https://github.com/wregin/microservice-crud/blob/master/showroom/microservice_db.png?raw=true)

## Banco de dados MySQL

As três bases nesse caso são no localhost mesmo, mas poderia ser por exemplo uma base paga.
![db_local](https://github.com/wregin/microservice-crud/blob/master/showroom/db_local.png?raw=true)

# Spring Eureka

Aqui é onde é feito o gerenciamento das aplicações, o microserviço fica visível a aqui, e é também utilizado para escabilidade da app, entre muitas outras aplicações mais complexas

![Spring Eureka registrando as microservices](https://github.com/wregin/microservice-crud/blob/master/showroom/spring_eureka.png?raw=true)

# Gateway Netflix Zuul

Basicamente o Zuul é utilizado como uma porta de entrada das solicitações, pode ser feito roteamente, monitoramente das solicitações, balanceamento entre outras funcionalidades. Nesse caso aqui as solicitações passa aqui no gateway, depois para o spring eureka.

# Postman

Testes realizados pelo Postman

![Autenticação e geração do token](https://github.com/wregin/microservice-crud/blob/master/showroom/gerando_token.png?raw=true)
![Consulta de um produto](https://github.com/wregin/microservice-crud/blob/master/showroom/consulta_um_produto.png?raw=true)
![Inserção de produto](https://github.com/wregin/microservice-crud/blob/master/showroom/insert_produto.png?raw=true)
![Deleção de um produto](https://github.com/wregin/microservice-crud/blob/master/showroom/delete_produto.png?raw=true)
![Inserção de venda](https://github.com/wregin/microservice-crud/blob/master/showroom/inserir_venda.png?raw=true)
![Consulta de venda](https://github.com/wregin/microservice-crud/blob/master/showroom/consulta_venda.png?raw=true)
