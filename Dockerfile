FROM maven:3.8.5-jdk-8

WORKDIR /tmp

COPY . /usr/src/wordlearnerapp
WORKDIR /usr/src/wordlearnerapp

RUN mvn clean install

CMD ["java", "-jar", "target/word-learner-0.0.1-SNAPSHOT.jar"]
