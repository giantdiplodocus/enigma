# EnigmaRotor

Java program that simulates the functionality of an Enigma rotor used in the Enigma machine during World War II. 
The Enigma machine was used for encrypting and decrypting messages, and the rotor played a crucial role in its operation.

## Description

This Java program implements an Enigma rotor with wiring connections at positions. 
It allows for setting the initial configuration of the rotor and translating characters forward or backward through the rotor.
Additionally, it provides a REST API endpoint for processing messages with rotor settings.

## Usage

### REST API Endpoint
This program provides a REST API endpoint for processing messages with rotor settings. 
You can send a POST request to /process with the following JSON payload:
```    
    {
        "message": "test",
        "rotorSettings": "ABC"
    }
```
#### Swagger UI
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
### Configuration with Properties File
The rotor configuration can be specified using a properties file.
 ```   
    rotor.left.type=I  
    rotor.right.type=III   
    rotor.center.type=II 
 ```

## React UI
A Simple react app is available in /enigma-ui
### How to run
```
  cd enigma-ui
  npm install
  npm start
```
### App Url
[localhost:3000]()
