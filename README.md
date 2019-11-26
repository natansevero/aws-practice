# aws-practice

A quick Spring Boot application deploy practice with a PostgreSQL connection using AWS services like **Elastick Beanstalk** and **RDS**.

### Infos about the folders

* ```/aws-test``` Is a simple Spring Boot application with two endpoints
* ```/aws-test-dev``` Have all files to do local test
* ```/aws-test-prod``` Have all files to structure the deploy

### Doing all stuffs

Let's do all the steps to do a local test and deploy the application on AWS.

#### Local build

First of all, let's compile de Spring Boot application. Make sure that there's a local PostgreSQL server instance to the application to do connection and have a successful build.

```
cd aws-test
mvn clean package
```

#### Docker config dev test

So, switch to the ```/aws-test-dev``` folder and let's run a local application with docker environment.

```
cd aws-test-dev
sh ./build.sh
docker-compose up
```
Access ```http://localhost``` and see the follow message: **Hello, baby!**

#### Docker config prod test

Switch to the ```/aws-test-prod``` and let's build the production image. We will push this image to the Dockerhub. Because, the EB instance will be configured with a Docker environment.

Pay attention to the ```Dockerrun.aws.json```. The file will be uploaded to the EB instance. It had a config to the EB instance recognize our Dockerhub image.

```
cd /aws-test-prod
sh ./build.sh
```
#### RDS and EB config

Okay, we finished the project config steps. Now, we need to create the RDS and EB instances and config them.

##### RDS

1. Create a database
2. Use the Standard Create
3. Select the PostgreSQL
4. Select the **Free tier** template
5. Define your credentials
6. In Additional connectivity configuration, make sure to select **Yes** for the Publicly accessible. So, later, we will can have a connection with a PostgreSQL client and manipulate the RDS instance.
7. Create a database called ```awstest``` on **Additional configuration**
8. So, click on **Create database** and wait... (This operation may take a long time)
9. After the creation, edit the Security group inbound to accept all connections.
10. Use a PostgreSQL client to connect with the instance and create a table called ```usuario``` with the follow simple schema:
```
create table usuario (
    id integer,
    nome varchar,
    primary key(id)
)
```
11. Run the follow SQL to popule the table
```
insert into usuario(id, nome) values (1, 'Natan');
insert into usuario(id, nome) values (2, 'Jonas');
```

##### EB

1. Create environment
2. Select the Web server environment
3. Define the Environment informations
4. Choose Docker as platform
5. Upload the ```Dockerrun.aws.json``` file
6. Wait the creation
7. Go to configuration ```->``` software ```->``` click on Modify
8. Set the follow **environment properties** (key | value):
```
SPRING_DATASOURCE_URL | jdbc:postgresql://<your-rds-host>:5432/awstest
SPRING_DATASOURCE_USERNAME | <your-rds-user>
SPRING_DATASOURCE_PASSWORD | <your-rds-password>
```
9. Apply the config and wait the instance restart

In the end, access your EB instance link and see the message: **Hello, baby!**

In additional, access the ```/usuarios``` to see the inserted users.