# Overview
Submission for Lightfeather.io Backend coding challenge.

The project utilizes the Spring Docker template found here: https://github.com/Team-LightFeather/coding-challenge-templates.

I learned a lot working on this project, and enjoyed the challenge of combining my limited prior knowledge of Java with both Spring and Docker to create a RESTful app.

Unfortunately, I did not have time to learn about and implement requirements for the submitted data parameters. If I had more time to work on this, I have no doubt that I would be able to understand and implement these concepts.

# Requirements
- Docker
- Gradle 7.2

# Instructions
Download the zip and open in the IDE of your choice. I personally used Visual Studio Code for this project.

From there, Docker can be leveraged to build and run the application, making it accessible at localhost:8080 using the commands below.

# Running locally with gradle
gradle build
gradle bootRun

# Running with docker

With Docker installed, you can build your a new image. This build needs to be run after any changes are made to the source code.
```
docker build --tag=spring-template:latest .
```

After the image builds successfully, run a container from that image.
```
docker run -d --name spring-template -it -p8080:8080 spring-template:latest
```

When you are done testing, stop the server and remove the container.
```
docker rm -f spring-template
```

# Running with docker-compose
docker-compose up