# Calculator API

Development of a REST API that provides basic functionality of a calculator.
Two modules Rest and Calculator that communicate via RabbitMQ.

## APIs

### Sum - Example 200
```
curl -i "http://localhost:8080/api/sum?a=5.5&b=3.5"
...
HTTP/1.1 200 OK Content-Type: application/json
{"result": "9.0"}
```
---

### Subtraction - Example 200
```
curl -i "http://localhost:8080/api/subtraction?a=22.2&b=2"
...
HTTP/1.1 200 OK Content-Type: application/json
{"result": "20.2"}
```
---

### Multiplication - Example 200
```
curl -i "http://localhost:8080/api/multiplication?a=4.5&b=3"
...
HTTP/1.1 200 OK Content-Type: application/json
{"result": "13.5"}
```
---

### Division - Example 200
```
curl -i "http://localhost:8080/api/division?a=85&b=3.5"
...
HTTP/1.1 200 OK Content-Type: application/json
{"result": "24.29"}
```
### Division - Example 400
```
curl -i "http://localhost:8080/api/division?a=85&b=0"
...
HTTP/1.1 400 Bad Request Content-Type: application/json
{"result": "ERROR: / by zero"}
```
### Division - Example 500
```
curl -i "http://localhost:8080/api/division?a=85&b=0,5"
...
HTTP/1.1 400 Bad Request Content-Type: application/json
{"result": "Failed to convert value of type 'java.lang.String' to required type 'java.math.BigDecimal'; nested exception is java.lang.NumberFormatException: Character , is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark."}
```

# Author

* Priscila Luz
