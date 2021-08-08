# sport-center
The service provides endpoints that can add players to the tennis courts if they are not full
## Prerequisites

* [Java 11 Runtime](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

### Run
    
1. How to run Springboot bt-api-solution application

   java -jar sport-center-0.0.1-SNAPSHOT.jar

2. Endpoint urls
   
 1)  to create 3 new courts for the sport center for start of the day
   http://localhost:8080/center/create
   input json body for reference:
   
   {
        "courts":[
            {
                "players": 0
            },
{
                "players": 0
            },
{
                "players": 0
            }

            ]
} 

 2) to add players to a specific court：
  for example： below url can add 3 players to court 1 of sportcenter 1
  http://localhost:8080/sportcenters/1/courts/1/playerAdd/3
  json input is not required
   
 3) to add players to the whole sport center without specifying court number
    for example： below url can add 3 players to any courts of the sportcenter 1
    http://localhost:8080/sportcenters/1/playerAdd/3
    json input is not required
    
 4)  to get the availability of the whole sport center
     http://localhost:8080/center/all
     to get the availability of all courts
     http://localhost:8080/court/all
     to get the availability of a specific court
      http://localhost:8080/court/details/{id}
       (for the interest of time, I don't list all urls in the controllers for now)
   
3. App description
   Design notes:
  
  Customer is an owner of a sports center with 3 tennis courts. Players can come and register interest to play for a particular day, once 4 players are interested one court could    be occupied. 

  Once a court is occupied i.e. 4 players shown interest to play, a notification is sent to players to indicate that game is on. Notification means just printing in console.

  Once 3 courts are occupied for a day, further registration will be rejected for that day. 
 
