# Rock-Paper-Scissor-API
This is a simple HTTP API that follows the principles of REST and allows developers to resolve their differences using the game Rock, Paper, Scissor. 
The rules are simple, best of 1 match wins.

## Prerequisite
This project targets jdk-10 to build and to run. You can either change the `sourceCompatibility` in the build.gradle or make sure that you install and use the correct JVM. 
## Build
You can either download/clone this repo and import it into your IDE (remember to import it as a gradle-project), then you can build and run the application, or you can run the application from the command line by using ./gradlew bootRun. 
 
You can also build a single executable JAR file by using `./gradlew build` in the command line and then run the JAR-file with `java -jar build/libs/rock-paper-scissor-api-0.0.1-SNAPSHOT.jar`

## How to play the game

### Step 1 - Creating a game
Start by creating a game. To create a game the user needs to send a HTTP
POST request to `/api/games` with a name in a request body.
##### Example Request Body
```json
{
  "name":"John"
}
```
##### Example in HTTPie
`http :8080/api/games name=John`
##### Response
The user will receive a Json-object containing an invitation link with the unique ID of the game.
The user can use this link in order to invite someone to the game.
```json
{
  "invitation":"/api/games/{id}/join"
}
``` 
### Step 2 - Invite your friends
From previous step you will get an answer with an ID for the game that you created.
The ID can be share with a friend you want to play with. 
The person in mind can join by sending a HTTP POST request to `/api/games/{id}/join` with a Request Body.
The `{id}` is the game-ID that you've so kindly provided.
##### Example Request Body
```json
{
  "name": "Lisa"
}
```
##### Example in HTTPie
`http :8080/api/games/{id}/join name=lisa`
##### Response
The user will receive a Json-object containing a inital representional state of the game without any moves (because, no cheating).
This is just for the user to get some feedback so that it knows it came to the right game.
```json
{
  "id": "{id}",
  "players" ["John", "Lisa"]
  "round": 0
}
``` 
#### Joined the wrong game?
No worries, it happens to the best of us. The player can of course leave by sending a HTTP POST request to `/api/games/{id}/leave` with a Request Body.
##### Example Request Body
```json
{
  "name": "Lisa"
}
```
##### Example in HTTPie
`http :8080/api/games/{id}/leave name=lisa`
##### Response
The user will receive a Json-object containing a message ensuring that the user has left or not. NOTE! That a user cannot leave a game if the game has started.
```json
{
  "id": "{id}",
  "message": "You've successfully left the game"
}
``` 
or 
```json
{
  "id": "{id}",
  "message": "Something is preventing you from leaving the game... Consult the 'README' page"
}
```
### Step 3 - Make your moves
Now that both you and your friend have joined, you will need to decide what move you want to make. 
The moves available are 'Rock', 'Paper' and 'Scissor'. 
Please note that your option needs to be spelled correctly and with a capital only first letter.
##### Example Request bodies
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
##### Examples in HTTPie
`http :8080/api/games/{id}/move name=lisa move=Paper` <br>
`http :8080/api/games/{id}/move name=john move=Scissor`
##### Response
The user will receive a Json-object containing a message ensuring that the user has made it's move .
```json
{
  "id": "{id}",
  "name": "Lisa",
  "message": "You've chosen Paper"
}
``` 
```json
{
  "id": "{id}",
  "name": "John",
  "message": "You've chosen Scissor"
}
```  
### Step 4 - Check the result
Now that both players have made their moves, the round is over and another one .
You and your friend can check out who is leading by sending a HTTP GET request to `/api/games/{id}/{round}`, no request body needed.
##### Example in HTTPie
`http GET :8080/api/games/{id}/{round}`
##### Response
The user will receive a Json-object containing a representional state of the requested round.
```json
{
  "id": "{id}",
  "round": "{round}",
  "winner": {
    "name": "John",
    "move": "Scissor"
  },
  "loser": {
    "name": "Lisa",
    "move": "Paper"
  },
}
```   
#### Check the score
If a few rounds has gone by and no one remembers the score then simply send an HTTP GET request to `/api/games/{id}/score`, no request body needed.
##### Example in HTTPie
`http GET :8080/api/games/{id}/{round}`
##### Response
The user will receive a Json-object containing a representional state of the game score.
```json
{
  "id": "{id}",
  "players": [
    {
      "name": "John",
      "score": "1"
    },
    {
      "name": "Lisa",
      "move": "0"
    }
  ],
}
```
### Check the entire game
The user can get a broad overview of the game by sending an HTTP GET request to `/api/games/{id}`, no request body needed.
##### Example in HTTPie
`http GET :8080/api/games/{id}/{round}`
##### Response
The user will receive a Json-object containing a representional state of the game score.
```json
{
  "id": "{id}",
  "rounds": [
    {
      "round": 1,
      "winner": {
        "name": "John",
        "move": "Scissor"
      },
      "loser": {
        "name": "Lisa",
        "move": "Paper"
      },
    }
  ],
  "players": [
    {
      "name": "John",
      "score": "1"
    },
    {
      "name": "Lisa",
      "move": "0"
    }
  ],
}
```