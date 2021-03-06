# personapi

## Abstract

This project was developed with the intent of learning the basics of the development of a API REST.
The idea was to develope a API that could be realistic(although not really) used by a organization to store and manage basic data about people. 



## Technnologies
+ Java 11
+ Maven 4.0
+ SpringBoot 2.3.4
  + Spring Web
  + Spring Actuator
  + Spring Data JPA
  + Spring Dev Tools
  + H2 Database
+ Project Lombok
+ Mapstruct 1.3.1 [**¹**](#observations)

## Running the program

### Requirements

+ Java 11
+ Maven 3.6.3 or superior


Open the project on the terminal.

```shell script
mvn spring-boot:run 
```

After that you can open the following address to visualise the execution of the project.

```
http://localhost:8080/api/v1/people
```

Alternatively you can access the following adress with postman or similar software.

````
 https://samme-personapi-live.herokuapp.com/
````


## JSON Format

````json
"firstName": "",
"lastName": "",
"cpf" "999.999.999-99",
"birthDate" : "dd-MM-yyyy",
"phone": [
          {
          "type":"(MOBILE/HOME/COMERCIAL)",
          "number":"(99)999999999"
          }        
          ]
````



## Observations

¹ Using Lombok and Mapstruct together will produce the error "<i>Could not retrieve @Mapper annotation during compilation</i>" to prevent that you'll need to configure the path of mapstruct to contain lombok. See [MapStruct](https://mapstruct.org/faq/#Can-I-use-MapStruct-together-with-Project-Lombok) for more information.


