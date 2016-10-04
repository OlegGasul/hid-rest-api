## Description
Simple REST service for storing any kind of data. Application uses token authentication.

## Dependencies
* Java 1.7+
* Groovy 2.4.6
* Grails 3.1.6
* PotgreSQL 9.4

## How-to-use

# Login
Get new access token
curl -H "Accept: application/json" -H "Content-Type: application/json;" -X POST -d '{"username":"root","password":"se3ret123"}' http://{host}:{port}/api/login

You will recieve token_key:
"token_type": "Bearer"
"access_token": "9ojjug6thusuid9j8aue8slrkge2guc9"

# Insert data
Put text data

curl -H "Authorization: Bearer {token_key}" -H "Content-Type: application/json" -X POST -d '{"test": "test"}' http://{host}:{port}:8080/api/resource/artwork/key/pn0004

# Request data
curl -H "Authorization: Bearer {token_key}" -H "Accept: application/json" -X GET http://{host}:{port}/api/resource/artwork/key/pn0004

# Request metadata information
curl -H "Authorization: Bearer {token_key}" -H "Accept: application/json" -X HEAD http://{host}:{port}/api/resource/artwork/key/pn0004

# Delete data
curl -H "Authorization: Bearer {token_key}" -H "Accept: application/json" -X DELETE http://{host}:{port}/api/resource/artwork/key/pn0004

## Examples
