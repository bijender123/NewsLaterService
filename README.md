# NewsLaterService
NewsLater is a Spring Boot application designed to send news updates to subscribers based on selected topics. It includes functionality to add subscribers, assign topics to subscribers, add content to specific topics, and automatically send content to subscribers at specified times.

Available Topics:
War (ID: 1)
Government (ID: 2)
Politics (ID: 3)
Education (ID: 4)
Entertainment (ID: 5)

API Endpoints<br />
1. Test Service<br />
    - Endpoint: /users/test<br />
    - Method: GET<br />
    - Description: Endpoint to test if the service is up and running.<br />
    - Example Request:</br>
      ```
      curl --location --request GET 'https://news-later-e85c9565aa6d.herokuapp.com/users/test' 
      ```
    - Example Response : </br>
      ```
       this is working
      ``` 
    <br />

2. Create Subscriber<br />
    - Endpoint: /users/create <br />
    - Method: POST<br />
    - Description: Add a subscriber's email ID.<br />
    - Request Body: <br/>
      - email (string, required): Email ID of the subscriber.
    - Example Request:</br>
        ```
        curl --location --request POST 'https://news-later-e85c9565aa6d.herokuapp.com/users/create' \
        --header 'Content-Type: application/json' \
        --data-raw '{
            "email": "yadavbijender2000@gmail.com"
          }'
        ```
     - Example Response:
         ```
         {"response":{"user_id":3,"email":"yadavbijender2000@gmail.com"},"status":"success"}
         ```
           
<br /> 

3. Add User Topic
    - Endpoint: /users/addUserTopics
    - Method: POST
    - Description: Add topics to a user.
    - Request Body:
      - user_id (integer, required): ID of the user.
      - topics (array of integers, required): IDs of topics to be added to the user.
    - Example Request:</br>
        ```
        curl --location --request POST 'https://news-later-e85c9565aa6d.herokuapp.com/users/addUserTopics' \
        --header 'Content-Type: application/json' \
        --data-raw '{
            "user_id": 5,
            "topics": [2,3,5]
        }'
        ```
    - Example Response:
        ```
        {"response":{"message":"Topic added to user"},"status":"success"}
        ```
<br />

4. Add Content
    - Endpoint: /content/add
    - Method: POST
    - Description: Add content to a specific topic.
    - Request Body:
      - topic_id (string, required): ID of the topic.
      - title (string, required): Title of the content.
      - text (string, required): Content text.
      - live_date (string, required): Date and time when the content will be live (format: "yyyy-MM-dd HH:mm:ss" in UTC).
    - Example Request:
         ```
          curl --location --request POST 'https://news-later-e85c9565aa6d.herokuapp.com/content/add' \
          --header 'Content-Type: application/json' \
          --data-raw '{
            "topic_id": "4",
            "title": "HeatWave Relief to Students",
            "text" : "Bangladesh Schools Reopen After Searing Heatwave Decline.",
            "live_date": "2024-05-05 22:16:39"
           }'
         ```
    - Example Response:
        ```
        {"response":"content added successfully","status":"success"}
        ```

</br>

--- 
 ### Automatic Content Delivery Service

The application includes a service that automatically sends content to subscribers of a specific topic at the specified time. This service ensures that subscribers receive timely news updates without manual intervention. The service is scheduled to run every hour using Spring's scheduling capabilities. Additionally, dependency injection is used to make the code loosely coupled, and Kafka is utilized for handling email sending requests asynchronously.








