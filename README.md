# CDN Origin Server System

![Java](https://img.shields.io/badge/Java-17-%23ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.5-%236DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.8.1-%23C71A36?logo=apachemaven)
![MongoDB](https://img.shields.io/badge/MongoDB-%2347A248?logo=mongodb)
![Tomcat](https://img.shields.io/badge/Tomcat-%23F8DC75?logo=apachetomcat)

# Objective
Deployment of a Content Delivery Network (CDN) using Spring Boot for REST API infrastructure, employing HTTP/2 protocol with enhanced security features. The project showcases a novel CDN architecture with randomized surrogate server selection and a user-friendly interface for demonstration.A Spring Boot-based Content Delivery Network (CDN) system with origin server and multiple edge servers.

## Key Features

- **CDN Architecture** with 1 origin server and 4 edge servers
- **Spring Boot 3.1.5** backend with Java 17
- **MongoDB** integration for data persistence
- **Load balancing** between edge servers
- **OkHttp** for efficient HTTP communication
- **Spring Session** for session management
- **Lombok** for reduced boilerplate code

## System Architecture

```plaintext
Client (test2.html)
    |
Origin Server (Redirecter)
    ├── Edge Server 1
    ├── Edge Server 2
    ├── Edge Server 3
    ├── Edge Server 4
    └── Edge Server 5
```

# How to run
To run this project one must add all the maven projects, build them and run each and open the html file in repository to view the result
mvn spring-boot: run 

on all servers



