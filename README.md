
# Charter-test

This application will allow to add customer transation and get their rewards.

Steps to run the project

1. clone repository

2. start the project with mvn spring-boot:run 

3. on the start of project 5 customer will automatically added 

        id | name
        --------------
        1, 'Meet'
        2, 'Raj'
        3, 'Viraj'
        4, 'John'
        5, 'Tina'

4. insert transaction with following curl command
    
    
    curl --location --request POST 'localhost:8080/customer-service/transaction' \
    --header 'Content-Type: application/json' \
    --data-raw '{"customerId": 1,"transactionValue": 120}'

5. update transaction with following curl command. Use transaction id to update a particular transaction

    curl --location --request PUT 'localhost:8080/customer-service/transaction' \ 
    --header 'Content-Type: application/json' \ 
    --data-raw '{   "id":  10, "transactionValue": 120 }'

6. get reward details of customer using below command

        curl --location --request GET 'localhost:8080/customer-service/reward?custId=1'

7. Run test using mvn test command
