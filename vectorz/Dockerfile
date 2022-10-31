FROM maven:latest

COPY ./vectorz /opt/vectorz
WORKDIR /opt/vectorz

CMD ["mvn", "-DwithHistory", "-Dverbose", "test-compile", "org.pitest:pitest-maven:mutationCoverage"]
