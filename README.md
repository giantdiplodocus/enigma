# Enigma

The Enigma machine is a cipher device developed and used in the early- to mid-20th century to protect commercial, diplomatic, and military communication. It was employed extensively by Germany during World War II, in all branches of the German military.

This Java program implements an Enigma machine with 3 rotors and a reflector. 

| Rotor #   | ABCDEFGHIJKLMNOPQRSTUVWXYZ |
|-----------|----------------------------|
| Rotor I   | EKMFLGDQVZNTOWYHXUSPAIBRCJ |
| Rotor II  | AJDKSIRUXBLHWTMCQGZNPYFVOE |
| Rotor III | BDFHJLCPRTXVZNYEIWGAKMUSQO |
| Rotor IV  | ESOVPZJAYQUIRHXLNFTGKDCMWB |
| Rotor V   | VZBRGITYUPSDNHLXAWMJQOFECK |
 | Reflector | YRUHQSLDPXNGOKMIEBFZCWVJAT |

Rotor configurations are stored in db. It allows for setting the initial configuration of the rotor and translating characters forward or backward through the rotor.
Additionally, it provides a REST API endpoint for processing messages with rotor settings.

## Usage
### Configuration with Properties File
The rotor configuration can be specified using a properties file.
 ```   
    rotor.left.type=I  
    rotor.right.type=III   
    rotor.center.type=II 
 ```

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
