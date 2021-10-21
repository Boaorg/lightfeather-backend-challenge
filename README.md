# Overview
Submission for Lightfeather.io Backend coding challenge.

The project utilizes the Spring Docker template found here: https://github.com/Team-LightFeather/coding-challenge-templates.

I learned a lot working on this project, and enjoyed the challenge of combining my limited prior knowledge of Java with both Spring and Docker to create a RESTful app.

Unfortunately, I did not have time to learn about and implement requirements for the submitted data parameters. If I had more time to work on this, I have no doubt that I would be able to understand and implement these concepts.

# Requirements
- Docker
- Gradle 7.2

# Instructions
1. Download the zip and extract. 
2. Open in the IDE of your choice. (I personally used Visual Studio Code for this project.)
3. Ensure you are in the working directory in the terminal.
4. Run the following commands:
```
docker build --tag=spring-template:latest .
```
```
docker run -d --name spring-template -it -p8080:8080 spring-template:latest
```
5. The container should then be running on localhost:8080.