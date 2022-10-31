# How to run mutation tests:

## Nerdamer
**prerequisites: docker & docker compose**
`docker compose up`

**prerequisites: docker**
or you can build the image and run the container in interactive mode:
`docker build . -t nerdamer`
`docker run -it nerdamer`

the mutation test report will be in nerdamer/reports/mutation/mutation.html

