# Rock-Paper-Scissor-API
This is a simple HTTP API that follows the principles of REST and allows developers to resolve their differences using the game Rock, Paper, Scissor. 
The rules are simple, best of 1 match wins.

## Prerequisite
This project targets jdk-10 to build and to run. You can either change the `sourceCompatibility` in the build.gradle or make sure that you install and use the correct JVM. 
## Build
You can either download/clone this repo and import it into your IDE (remember to import it as a gradle-project), then you can build and run the application, or you can run the application from the command line by using ./gradlew bootRun. 
 
You can also build a single executable JAR file by using `./gradlew build` in the command line and then run the JAR-file with `java -jar build/libs/rock-paper-scissor-api-0.0.1-SNAPSHOT.jar`

## How to play the game

#### Step 1 - Creating a game
Start by creating a game. To create a game you'll need to send a HTTP
POST request to `/api/games` with a name in a request body.
###### Example Request Body
```json
{
  "name":"John"
}
```
###### Example in HTTPie
`http :8080/api/games name=John`

#### Step 2 - Invite your friends
From previous step you will get an answer with an ID for the game that you created.
The ID can be share with a friend you want to play with. 
The person in mind can join by sending a HTTP POST request to `/api/games/{id}/join` with a Request Body.
The `{id}` is the game-ID that you've so kindly provided.
###### Example Request Body
```json
{
  "name": "Lisa"
}
```
###### Example in HTTPie
`http :8080/api/games/{id}/join name=lisa`
#### Step 3 - Make your moves
Now that both you and your friend have joined, you will need to decide what move you want to make. 
The moves available are 'Rock', 'Paper' and 'Scissor'. 
Please note that your option needs to be spelled with a capital only first letter .
###### Example Request bodies
```json
{
  "name": "Lisa",
  "move": "Paper"
}
```
```json
{
  "name": "John",
  "move": "Scissor"
}
```
###### Examples in HTTPie
`http :8080/api/games/{id}/move name=lisa move=Paper` <br>
`http :8080/api/games/{id}/move name=john move=Scissor`
#### Step 4 - Check the result
Now that both players have made their moves, the game is over.
You and your friend can check out the result by sending a HTTP GET request to `/api/games/{id}` with a request body.
The request body is necessary for the application to know who wants to see the game result. 
###### Example Request Body
```json
{
  "name": "Lisa"
}
```
###### Example in HTTPie
`http GET :8080/api/games/{id} name=lisa`

