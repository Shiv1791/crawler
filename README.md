# crawler

#hit the api using postman

curl --location --request POST 'http://localhost:8081/postUrlRoute' \
--header 'Content-Type: application/json' \
--data-raw '{
    "urls": ["https://www.google.com/", "https://www.youtube.com/watch?v=YD77wifQi-g&ab_channel=MarkLewis"]
}'
