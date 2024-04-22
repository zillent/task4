# task4 example project

Init and run Postgres:

1. cd db
2.  docker build -t task4-db .
3. docker run --name task4-db -p 5432:5432 -d task4-db

to connect to Postgres DB:
jdbc:postgresql://localhost:5432/task4
user: task4
password: task4

Build:
mvn package

Run:
java -jar target/task4-0.0.1-SNAPSHOT.jar files/

