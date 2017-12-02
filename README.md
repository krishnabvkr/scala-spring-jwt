# Spring Boot application in Scala with JWT Authentication

The application in a simple Rest service that manages movie ticket booking and use MySql as database 

Database configuration
__A running MySql on localhost:3306 is needed and a database with name 'crossoverdb' needs to be created.

$ mySql> create database crossoverdb;


The application uses Spring Boot, Spring Security and Spring Data.

Additional dependencies are JacksonModule for scala and the JWT library.

## Usage

* To register a user make a POST request to:

    `localhost:8080/api/users/register`
    
    with the User (see implementation) in the body. You should receive a success message.
    
* To authenticate make a POST request to:

    `localhost:8080/auth
    
    with the User (see implementation) in the body.
    You should receive a blank message with the JWT token in the Authorization header.
    The token is a long string with "Bearer" prefix, something like that:
    
    `Bearer eyJhbGciOiJI........A.LOT.OF.CHARACTERS.......eNRCd_76Q`
    
* To make authenticated requests against the movie controller simply put the received token in the Authorization header of all your following requests.

* To get movies information make a GET request to:

    `localhost:8080/api/list
    
    with the received token in the Authorization header. You should receive a movies list.


* To book ticket make a POST request to:

    `localhost:8080/api/book
    
    with token, useremailid ,movieid and credit card information (see implementation) in the body. You should receive a success message.
	If card details are not valid then you should receive a message like invalid credit card information
	
	* Validation rules againest to credit card information.
	-> name should not be empty
	-> card number should be 16 digits
	-> cvv number should be 3 digits
	-> expiry month should be between 1 to 12
	-> experiy year should be >= current year.

## How to build

You need to install gradle. Then go to the project directory and type:

`$ gradle build`

Now it should compile. The first time it takes a while because gradle have to download all the dependencies.

To run the application type:

$ gradle bootrun