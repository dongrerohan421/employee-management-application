# Employee Management REST API


This is Employee Management Backend Application with REST API's developed in SpringBoot 2, Lombok, Maven and Swagger2


Please free to modify the server port. The current state uses 8080 as server port.

Please use below URL's to browse

- **For APi docs:** Please visit http://localhost:8080/employee-management-application/v2/api-docs
- **To use swagger:** http://localhost:8080/employee-management-application/swagger-ui.html#/
- Please use below JSON format to test 
    ```$xslt
    {
        "emailId": "string",
        "firstName": "string",
        "id": 0,
        "lastName": "string"
    }
    ```

* **Create employee:**
    - http://localhost:8080/employee-management-application/api/v1/employees

        ```$xslt
         curl -X POST "http://localhost:8080/employee-management-application/api/v1/employees" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"emailId\": \"rd@gmail.com\", \"firstName\": \"R\", \"id\": 1, \"lastName\": \"D\"}"
        ```
   
* **Get employees:**
    - http://localhost:8080/employee-management-application/api/v1/employees
        ```$xslt
        curl -X GET "http://localhost:8080/employee-management-application/api/v1/employees" -H "accept: application/json"
        ```
    - http://localhost:8080/employee-management-application/api/v1/employees/1
        ```$xslt
        curl -X GET "http://localhost:8080/employee-management-application/api/v1/employees/1" -H "accept: application/json"
        ```
    
* **Update employee**
    - http://localhost:8080/employee-management-application/api/v1/employees/1
        ```$xslt
        curl -X PUT "http://localhost:8080/employee-management-application/api/v1/employees/1" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"emailId\": \"rsd@gmail.com\", \"firstName\": \"R\", \"id\": 1, \"lastName\": \"D\"}"
        ```
  
* **Delete employee**
    - http://localhost:8080/employee-management-application/api/v1/employees/1
        ```$xslt
        curl -X DELETE "http://localhost:8080/employee-management-application/api/v1/employees/1" -H "accept: application/json"
        ```

- **To Build app:**
    ```$xslt
    mvn compile
    ```
- **To install app:** 
    ```$xslt
    mvn install
    ```
- **To Run Tests:**
    ```$xslt
    mvn test
    ```
- **To Start app:**
    ```$xslt
    mvn spring-boot:run
    ```

- **Swagger UI preview:**
![Alt text](EmployeeManagement-Swagger-ui.png?raw=true "Swagger-UI")
