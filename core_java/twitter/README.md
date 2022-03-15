# Twitter CLI Application

## <ins>Introduction

The `Twitter Command Line Interface (CLI) Application` is a Java app built for posting, showing, and deleting a tweet post 
using the command line. The app Implements Twitter API v1.1 and uses the HTTP protocol to communicate with the Twitter Rest API.
The Jackson library was used to convert JSON objects to Tweet objects. Moreover, we used Maven as our project standard layout management tool,
Springboot was used to handle project dependencies, testing was done using `JUnit4` and `Mockito`, and the app was deployed using
Docker
Upost regularly on Twitter

__Technologies:__
> <span style = "color:green"> Git | Docker | Java SE 8 | Apache Maven | Twitter Rest API </span>

## <ins> Quick Start

Pull image from Docker

`docker pull techapps101/twitter`

Obtain your
<span style = "color:blue"> consumerKey </span> |
<span style = "color:blue"> consumerSecret </span> |
<span style = "color:blue"> accessToken </span> |
<span style = "color:blue"> tokenSecret </span>
from Twitter Developer's Portal

```
docker run --rm \
-e consumerKey= null \
-e consumerSecret= null \
-e accessToken= null \
-e tokenSecret= null \
techapps101/twitter post|show|delete [options]
```

#### Post

```
docker run --rm \
-e consumerKey= null \
-e consumerSecret= null \
-e accessToken= null \
-e tokenSecret= null \
techapps101/twitter post "message" latitude:longitude
```

#### Show

```
docker run --rm \
-e consumerKey= null \
-e consumerSecret= null \
-e accessToken= null \
-e tokenSecret= null \
techapps101/twitter show id_of_tweet
```

#### Delete

```
docker run --rm \
-e consumerKey= null \
-e consumerSecret= null \
-e accessToken= null \
-e tokenSecret= null \
techapps101/twitter delete id_of_tweet
```

## <ins> UML Diagram

![my image](./assets/twittercli.png)

#### Description of the components

The application consists of the following components:
### Application (Top Layer)
The top layer is responsible for reading and parsing user inputs from the command line (passed in as program parameters). The program checks if the user has provided one of the three valid commands, and passes the instruction down to the controller layer.
### Controller
The controller layer further validates the instruction by checking for any required and optional parameters. If the instruction is complete and valid, the controller calls the respective service layer function with the corresponding method arguments.
### Service
Now that the instruction is converted into an internal representation, the service layer can focus on business logic. For instance, it ensures that a post does not exceed 140 characters. It then provides the corresponding payload to one of the DAO CRUD methods.
### DAO
The DAO layer is responsible for sending HTTP requests and receiving responses from the Twitter REST server. It also handles unexpected server replies in case of errors. The final response is parsed into a JSON string and returned to the top layer.

## Models
The application primarily relies on a Tweet object model since tweets are primarily what the application needs to handle. The Tweet object contains multiple fields such as ID, text, creation date, and other attributes that a typical tweet has. It also contains a custom Coordinates class for holding the longitude/latitude values of the corresponding Tweet location, and also an Entities class that holds hashtags and user mentions. Both hashtags and user mentions are separate classes with the necessary fields to hold all required information.
## Spring
This application implements many interdependent layers. As such, many dependencies exist, where one class object relies on the existence of another class object and so on. To manage dependencies automatically, the Spring framework was applied with Spring Boot which injects dependencies automatically based on simple class annotations. In this application, the following are Spring components:
* TwitterCLIApp
* Controller
* Service
* CrdDao
* HttpHelper

## Deployment
          
The application was containerized using Docker and deployed to a public Docker image at Docker Hub. Important source code was also pushed to this remote Git repository.          

## <ins> Test

Testing for each module was done using `Junit4` and `Mockito`. Unit and integration tests were written for each individual layer to ensure desired behaviour
## <ins> Improvements
-  Storing tweets in a CSV file .
- Allow users to post videos from their storage
- Check tweet updates within a specific timeframe
