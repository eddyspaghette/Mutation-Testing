FROM maven:latest

COPY ./vectorz /opt/vectorz
WORKDIR /opt/vectorz

CMD ["mvn", "-DwithHistory", "test-compile", "org.pitest:pitest-maven:mutationCoverage"]
