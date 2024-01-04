# Sorveteria


![java](https://camo.githubusercontent.com/57cec1c01287dfdc2a3fe64954936293c761b7fa9a7fc1b9de3916a295f15170/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6a6176612d2532334544384230302e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d6f70656e6a646b266c6f676f436f6c6f723d7768697465)
![spring](https://camo.githubusercontent.com/49f645b5e439b0d748424412207eae5748b81d77563f866d8528f60c66b669e1/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f737072696e672d2532333644423333462e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d737072696e67266c6f676f436f6c6f723d7768697465)
![postgres](https://camo.githubusercontent.com/29e7fc6c62f61f432d3852fbfa4190ff07f397ca3bde27a8196bcd5beae3ff77/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f706f7374677265732d2532333331363139322e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d706f737467726573716c266c6f676f436f6c6f723d7768697465)
## Índice

- <a href="#-Resumo">Sobre</a>
- <a href="#-Instalacao">Instalação</a>
- <a href="#-Uso">Uso</a>
- <a href="#-PrincipalFunc">Funcionalidades Principais</a>
- <a href="#-EndPoints">Endpoints</a>
- <a href="#-Demonstração">Demonstração</a>
- <a href="#-DB">Banco de Dados</a>
- <a href="#-Diagrama">Diagrama</a>
- <a href="#-Desenvolvedores">Desenvolvedores</a>



## Sobre

Esta é uma API Restful desenvolvida como suporte a uma sorveteria como parte da disciplina de Desenvolvimento Web 2 do curso de TADS do Instituto Federal de Pernambuco.

## Instalação
 1. Clone o repositório

        https://github.com/Allamymp/sorveteria

 2. Instale as dependencias utilizando o Maven

 3. Instale o [PostgresSql](https://www.postgresql.org/)


## Uso

1. Inicie a aplicação com o Maven
2. A api estará acessível na url: http://localhost:8000

## Funcionalidades Principais 

- CRUD Sabor
- CRUD TipoSorvete
- CRD Sorvete
- Cadastro de Solicitações de Suporte
- Operações GET, POST, PUT e DELETE.

## API Endpoints

A API disponibiliza os seguintes EndPoints:

    Tipo PUT:
        - /sabor/update - atualiza um sabor
        - /tipoSorvete/update - atualiza um tipo de sorvete

    
    Tipo GET:
        -/sabor/all - retorna todos os sabores.
        -/sabor/findById - retorna um sabor por id.
        -/tipoSorvete/all - retorna todos os tipos de sorvete.
        -/tipoSorvete/findById - retorna um tipo de sorvete por id.
        -/sorvete/findById - retorna um sorvete por tipo de id.
        -/sorvete/all - retorna todos os sorvetes.
        -/sorvete/report - retorna todos os sorvetes em uma data especifica. 
        
    
    Tipo POST:
        - /sorvete - cria um sorvete
        - /sabor - cria um  sabor de sorvete
        - /tipoSorvete - cria um tipo de sorvete

    Tipo DELETE:
        - /sabor - deleta um sabor do banco de dados através de um id.
        - /sorvete - deleta um sorvete do banco de dados através de um id.
        - /tipoSorvete -  deleta um tipoSorvete do banco de dados através de um id.
        
## Demonstração 

 A desmonstração do projeto pode ser obtida via swagger:
    Link = servidor + /swagger-ui/index.html#/
 
 Caso esteja rodando o projeto localmente na porta padrão o link será: 
 - http://localhost:8080/swagger-ui/index.html#

## Banco de Dados

O projeto utiliza [PostgresSQL](https://www.postgresql.org/) como banco de dados. As requisições são feitas usando sql e não JPA conforme solicitado por docente da disciplina. 


## Diagrama

https://github.com/Allamymp/sorveteria/blob/main/diagrama.png

## Desenvolvedores

Desenvolvedores que contribuiram com esta aplicação:

- Allamy Monteiro Pereira: [@Allamymp](https://github.com/Allamymp)
 
 
 

