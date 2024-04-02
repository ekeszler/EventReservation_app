
# Hi, I'm Erik! 👋

And here you can find the documentation of the Event Reservation project
## 🚀 About Me
💻Dedicated back-end software developer | 👨‍💻Motivated to work for companies to build great back-ends | Java, Spring Boot | Passionate about solving problems using technology | 💼 Actively looking for a job | 10️⃣ personal projects


## 🛠 Skills
Back-end development, Software development, Web development, Java, Spring framework, Spring boot, Data structures, Algorithms, OOP, MySQL, Relational databases, SQL, Git, HTML, CSS, Web services, Rest APIs, Unit Testing

## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/ekeszler)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/erikkeszler/)


# Event Reservation App

As a response to the necessity of a reservation platform for a company that provides photo booths, video booths, and other entertainment services, I have developed the Event Reservation App. Through this app, clients can reserve 
a service or a package of services by creating an event for a specific date and time. Each user has an event; each event has a package; each package has products. The prices for products and packages are set up by the admin user  
## Features

As a user, I can:
- view all products
- view all packages
- create a new event
- add package to event
- add product to package
- view a specific event


As an admin, I can:
- update event date
- delete event
- add link to event
- update package price'
- create package
- detele package
- create product
- update product price
- delete product
- create user
- add role to user
- delete user


## Built with

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

## Demo

You can view the demo here:

https://in_progress


## API Reference

#### Create a new event

```http
  POST /event/addEvent
```

| Parameter | Type     | Description                                           |
| :-------- | :------- |:------------------------------------------------------|
| `body`    | `json`   | **Required**. The properties of event to be added     |

Request body example:

```json
{
  "name": "string",
  "start": "2024-04-02T08:55:20.105Z",
  "end": "2024-04-02T08:55:20.105Z",
  "userId": 0
}
```  

#### Add a link to event

```http
  POST /event/addLink
```

| Parameter | Type     | Description                                           |
| :-------- | :------- |:------------------------------------------------------|
| `link`    | `string` | **Required**. The link which will be addes            |
| `body`    | `json`   | **Required**. The properties of event                 |

Request body example:

```json
{
  "name": "string",
  "start": "2024-04-02T08:55:20.105Z",
  "end": "2024-04-02T08:55:20.105Z",
  "userId": 0
}
```  

#### Add a package to event

```http
  POST /event/addPackage
```

| Parameter | Type     | Description                                           |
| :-------- | :------- |:------------------------------------------------------|
| `body`    | `json`   | **Required**. The properties of package               |

Request body example:

```json
{
   "eventId": 1,
  "packageName": "test",
  "price": 10
}
```  


#### Get all events

```http
  GET /event/all
```

#### Update event date 

```http
  PUT event${eventID}/update-start
```

| Parameter | Type     | Description                                                     |
| :-------- | :------- |:----------------------------------------------------------------|
| `id`      | `string` | **Required**. Id of event for which the date will be updated    | 
| `newStart`| `string` | **Required**. New date of event                                 |


#### Delete an event

```http
  DELETE /event/${eventId}
```

| Parameter | Type     | Description                                 |
| :-------- | :------- |:--------------------------------------------|
| `id`      | `string` | **Required**. Id of event to be deleted     |


#### Create a new package

```http
  POST /package/add
```

| Parameter | Type     | Description                                       |
| :-------- | :------- |:--------------------------------------------------|
| `body`    | `json` | **Required**. The properties of package to be added |

Request body example:

```json
{
  "eventId": 0,
  "packageName": "string",
  "price": 0
}
```  


#### Get all packages

```http
  GET /package/all
```


#### Add a product to package

```http
  POST /package/addToPackage
```

| Parameter | Type     | Description                                        |
| :-------- | :------- |:---------------------------------------------------|
| `body`    | `json`   |**Required**. The properties of package to be added |

Request body example:

```json
{
   "productName": "string",
  "price": 0,
  "packageId": 0
}
``` 

#### Update package price

```http
  PUT /package/${packageID}/update-price
```

| Parameter | Type     | Description                                                     |
| :-------- | :------- |:----------------------------------------------------------------|
| `id`      | `string` | **Required**. Id of package for which the price will be updated |
| `new price`| `double`| **Required**. New price of the package                          |  


#### Delete a package

```http
  DELETE /package/${packageId}
```

| Parameter | Type     | Description                                   |
| :-------- | :------- |:----------------------------------------------|
| `id`      | `string` | **Required**. Id of package to be deleted     |



#### Create a new product

```http
  POST/product/add
```

| Parameter | Type     | Description                                           |
| :-------- | :------- |:------------------------------------------------------|
| `body`    | `json`   | **Required**. The properties of product to be created |

Request body example:

```json
{
  "productName": "test",
  "price": 5,
  "packageId": 1
}
```  


#### Get all products

```http
  GET /product/all
```

#### Update product price

```http
  PUT /product/${productID}/update-price
```

| Parameter | Type     | Description                                                     |
| :-------- | :------- |:----------------------------------------------------------------|
| `id`      | `string` | **Required**. Id of product for which the price will be updated | 
| `new price`| `string` | **Required**. New price of the product                         | 


#### Delete a product

```http
  DELETE /product/${productId}
```

| Parameter | Type     | Description                                   |
| :-------- | :------- |:----------------------------------------------|
| `id`      | `string` | **Required**. Id of product to be deleted     |


## API Authentication and Authorization

There are only two requests which don't require authorization headers.

#### Authenticate (login)

```http
  POST /authenticate
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of user to authenticate  |

Request body example:

```json
{
  "username": "string",
  "password": "string"
}
```  

#### Register 

```http
  POST /register
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of user to register  |

Request body example:

```json
{
  "username": "string",
  "password": "string"
}

```  
After running the authenticate request, the client will obtain an access token that will be used in all subsequent request in order to authenticate the user and to authorize the user based on its role.

This is an example of what should be included in the request header:

```http
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjcxMTQzMzEyfQ.dxIzsD9Bm8y_kw3MOoZ2JXIKOg--uZaA5XNtBLdGYc4Ps3nlzBFDwBJi0bEeHlCggonZ6nQ2zwCI0D5a7dXjmw
```  
## Prerequisites

For building and running the application you need:
- JDK 1.8 or higher
- Maven 3

For deploying on Heroku you need:
- GIT 
- Heroku CLI

## Dependencies

You don't need any additional dependencies.
All dependecies related to database management, server management, security management and so on, will be automatically injected by Maven using the pom.xml file located in the root folder of the project.
## Installation

Clone the project

```bash
  git clone https://github.com/ekeszler/EventReservation_app
```

Go to the project directory

```bash
  cd my-project
```
    
## Run Locally

Use maven to build the app and, to run it, and to start the local embedded Tomcat server

```bash
  mvn spring-boot:run
```


## Running Tests

To run tests, run the following command

```bash
  mvn test
```


## Environment Variables

You can deploy this project using Heroku or other providers as well, by specifying the following environment variables:

`PROFILE`

`MYSQL_URL`

`MYSQL_USER`

`MYSQL_PASS`



## Deployment

To deploy this project run

```bash
  git push heroku master
```


## Usage

You can use the demo version of the app, using SwaggerUI and following this link:

```javascript
https://willbeuploaded
```

First, obtain an access token by running the /authenticate endpoint with username "user" and password "pass", which will grant you admin rights in the application.

![App Screenshot](https://i.imgur.com/VTQibfA_d.webp?maxwidth=760&fidelity=grand)

After that, authorize yourself by clicking the authorize button and copy-pasteing the token from the response.

![App Screenshot](https://i.imgur.com/arTX2Ke_d.webp?maxwidth=760&fidelity=grand)

From now on, you can use all other available endpoints, as per swagger documentation.
## Roadmap

In the future, application can be extended with following:

- a review functionality - after use the companys services the user can write a review.

- an e-mail sending functionality -before and after the event, reminder e-mails will be sent to remind the customer that he/she will have an event at the established date and some days after the event another mail will be sent to remind that the user can leave a review


## Badges


![Maintained](https://img.shields.io/badge/Maintained%3F-yes-green.svg)
![GIT](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![Heroku](https://img.shields.io/badge/heroku-%23430098.svg?style=for-the-badge&logo=heroku&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![JWT](https://img.shields.io/badge/json%20web%20tokens-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink)
