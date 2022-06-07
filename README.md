

| HTTPS method | URL path                              | HTTP status code | description                                                    |
|--------------|---------------------------------------|------------------|----------------------------------------------------------------|
| POST         | /authenticate                         | 200 (OK)         | to allow user to sign in and get the JWT access token          |
| POST         | /getToken                             | 200 (OK)         | to allow the user to sign in the system and get the token      |
|              |                                       |                  |                                                                |
| GET          | /api/customer                         | 200 (OK)         | to get all customers information                               |
| POST         | /api/customer                         | 201 (CREATED)    | to Add a customers to the system                               |
| GET          | /api/customer/id                      | 200 (OK)         | to get a specific customers information                        |
| PUT          | /api/customer/id                      | 200 (OK)         | to update a specific customers information                     |
| DELETE       | /api/customer/id                      | 200 (OK)         | to delete a specific customers                                 |
|              |                                       |                  |                                                                |
| GET          | /api/order                            | 200 (OK)         | to get all order information                                   |
| POST         | /api/order                            | 201 (CREATED)    | to Add an order to the system                                  |
| GET          | /api/order/id                         | 200 (OK)         | to get an order customers information                          |
| PUT          | /api/order/id                         | 200 (OK)         | to update a specific order  information                        |
| DELETE       | /api/order/id                         | 200 (OK)         | to delete a specific order                                     |
|              |                                       |                  |                                                                |
| GET          | /api/productOrder                     | 200 (OK)         | to get all productOrder information                            |
| POST         | /api/productOrder                     | 201 (CREATED)    | to Add a productOrder to the system                            |
| GET          | /api/productOrder/id                  | 200 (OK)         | to get a productOrder  information                             |
| PUT          | /api/productOrder/id                  | 200 (OK)         | to update a specific productOrder  information                 |
| DELETE       | /api/productOrder/id                  | 200 (OK)         | to delete a specific productOrder                              |
|              |                                       |                  |                                                                |
| GET          | /api/product                          | 200 (OK)         | to get all product information                                 |
| POST         | /api/product                          | 201 (CREATED)    | to Add a product to the system                                 |
| GET          | /api/product/id                       | 200 (OK)         | to get a product  information                                  |
| PUT          | /api/product/id                       | 200 (OK)         | to update a specific product  information                      |
| DELETE       | /api/product/id                       | 200 (OK)         | to delete a specific product                                   |
|              |                                       |                  |                                                                |
| GET          | /api/stock                            | 200 (OK)         | to get all stock information                                   |
| POST         | /api/stock                            | 201 (CREATED)    | to Add a stock to the system                                   |
| GET          | /api/stock/id                         | 200 (OK)         | to get a stock  information                                    |
| PUT          | /api/stock/id                         | 200 (OK)         | to update a specific stock  information                        |
| DELETE       | /api/stock/id                         | 200 (OK)         | to delete a specific stock customers                           |
|--------------|---------------------------------------|------------------|----------------------------------------------------------------|

--------------------------------------------------------------------------------------------------------------------------------------------

To build the application, you need to run it first and make sure its working, add this depedency 
<!-- Package as an executable jar/war -->
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
then you can use command mvn install or in the maven bar on the left, and choose lifesycle, then install 

--------------------------------------------------------------------------------------------------------------------------------------------

to build an img, you need to add dockerfile and docker-compose.yaml to the project, with the values i added, run docker and use the command lines or the IntelliJ command line to do docker-compse up and wait, after that just wait it to finish, push the image to the docker and run the container(previously created).

--------------------------------------------------------------------------------------------------------------------------------------------

Docker Image Link:https://hub.docker.com/r/ahmadkhateebq/docker-sample
