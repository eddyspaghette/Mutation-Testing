# How to run mutation tests:

## Nerdamer
**prerequisites: docker & docker compose**



`docker compose up`



**prerequisites: docker**


eg) or you can build the image and run the container in interactive mode:


`docker build . -t nerdamer`



`docker run -it nerdamer`


once done, you can find the container name and copy the results over to any directory 

e.g) 


FOR stryker:


`docker cp <container:id>:/opt/nerdamer/reports/mutation/mutation.html .`



FOR pit:


`docker cp <container:id>:/opt/vectorz/target/reports/<time stamp>/index.html .`


