# How to run mutation tests:

## Nerdamer && Vectorz
**prerequisites: docker & docker compose**


command below will run both containers at the same time

`docker compose up`



once done, you can find the container name and copy the results over to any directory 

e.g) 


FOR stryker:


`docker cp <container:id>:/opt/nerdamer/reports/mutation/mutation.html .`



FOR pit:


`docker cp <container:id>:/opt/vectorz/target/reports/<time stamp>/index.html .`


