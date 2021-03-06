## Description
Simple REST service for storing any kind of data. Application uses Token authentication.
This solution designed with storing binary data using BLOB in database.

## Dependencies
* Java 1.7+
* Groovy 2.4.6
* Grails 3.1.6
* PotgreSQL 9.4+

## How-to-deploy

0) Install Java 1.7 (or above) and PostgreSQL 9.4

1) Setup database with __scripts/db.sql__ script.

2) Download Grails 3.1.6 from https://grails.org/download.html

3) Clone the project

git clone https://github.com/OlegGasul/hid-rest-api

4) Setup application

In application.yml set:

database url, login/pass ("dataSource" section)

5) Run the project

To run project as standalone application use __grails run-app__ in root of project folder.

You can also deploy it under any application server.

For generation war file use: __grails war__ command.

## Examples

You can use any UI tool for making requests (Postman, Advanced REST Client, Paw, etc.)

### Login
Get new access token

URL:  
GET http://{host}:{port}/api/login

Headers:  
Accept: application/json  
Content-Type: application/json  

Body:  
{"username": "root", "password": "se3ret123"}

Result:  
{"username":"root","roles":["ROLE_USER"],"token_type":"Bearer","access_token":"9ojjug6thusuid9j8aue8slrkge2guc9"}

curl -H "Accept: application/json" -H "Content-Type: application/json" -X POST -d '{"username":"root","password":"se3ret123"}' http://{host}:{port}/api/login

You will receive token_key:

"token_type": "Bearer"  
"access_token": "9ojjug6thusuid9j8aue8slrkge2guc9"

Use this token key in all other queries.

### Create data
Post text data

URL:  
POST http://{host}:{port}/api/resource/artwork/key/pn0004

Headers:  
Authorization: Bearer {token_key}  
Content-Type: application/json  

Body:  
{"test": "test"}

Result:  
{"result": true}

curl -H "Authorization: Bearer {token_key}" -H "Content-Type: application/json" -X POST -d '{"test": "test"}' http://{host}:{port}/api/resource/artwork/key/pn0004

### Request data
URL:  
GET http://{host}:{port}/api/resource/artwork/key/pn0004

Headers:  
Authorization: Bearer {token_key}  
Content-Type: application/json  

Result:  
{"test": "test"}

curl -H "Authorization: Bearer {token_key}" -H "Accept: application/json" -X GET http://{host}:{port}/api/resource/artwork/key/pn0004

### Update data

URL:  
PUT http://{host}:{port}/api/resource/artwork/key/pn0004

Headers:  
Authorization: Bearer {token_key}  
Content-Type: application/json

Body:  
{"test2": "test2"}

Result:  
{"result": true}

curl -H "Authorization: Bearer {token_key}" -H "Content-Type: application/json" -X PUT -d '{"test": "test2"}' http://{host}:{port}/api/resource/artwork/key/pn0004

### Delete data

URL:  
DELETE http://{host}:{port}/api/resource/artwork/key/pn0004

Headers:  
Authorization: Bearer {token_key}  
Accept: application/json

curl -H "Authorization: Bearer {token_key}" -H "Accept: application/json" -X DELETE http://{host}:{port}/api/resource/artwork/key/pn0004

### Request metadata information
For receiving only information about resource use HEAD method.

URL:  
HEAD http://{host}:{port}/api/resource/artwork/key/pn0004

Headers:  
Authorization: Bearer {token_key}  
Accept: application/json

Result:
Empty response body. All information you can find in headers.  
X-Content-Length: 7  
Content-Type: application/json;charset=UTF-8  

curl -H "Authorization: Bearer {token_key}" -H "Accept: application/json" -X HEAD http://{host}:{port}/api/resource/artwork/key/pn0004


