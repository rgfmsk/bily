# Bilyoner Assignment

1. First of all build a spring boot rest api project that can have mongodb database connection configured with maven.

It should be run as a jar. 
(No services or controllers needed at this step. Only boilerplate code.)


2. Wind up a eureka server in your local machine and register your service into it. 


3. Open up the eureka console and take a screenshot of the list of registered services in eureka.

4. Write a resource that makes POST request to your rest api, which inserts a number into db as a mongodb document.

The number in the database MUST be unique.
If the number is already in the db. You should return an error code and a message.
You shoulh also add the insertion date attribute to the mongodb document like below:


e.g: 

```
{
   "number": 23,
   "insert_date": "2010-03-18 12:21:48"
}
```

5. Write a resource that can get the persisted object as JSON by number. The verb should be like http://localhost/<resource-name>/{number}

6. Write a resource that can get the persisted object as JSON having the maximum number in db.

7. Write a resource that can get the persisted object as JSON having the minimum number in db.

8. Write a resource that can delete the persisted object with the given number as parameter. If the number does not exist in the db you will return an error code.

9. In the same resource if no parameter have been given, it would fetch all objects at once in JSON format. The list of objects must be ordered by number. I can change the ordering type(ascending/descending) with a parameter. By default it should be ascending. 



10. [BONUS] Dockerize the service that you have written. You will build a docker image for your service which can communicate with your mongodb server.


11. [BONUS] Code coverage at least 70%.


12. [BONUS] Stick to the principles of Clean Code.


# Submission

1. You should push the rest api code to your personal github repository.
2. You should push the code of spring cloud eureka server that you have implemented.
3. You should send the screenshot of your eureka server console with registered service in it which is instructed in 3rd article above.
4. If you have dockerized your projects, you should have include your Dockerfile in your code and give your run command in the README file.


