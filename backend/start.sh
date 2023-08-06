set -xe

./mvnw clean package
java -jar target/springreact-0.0.1-SNAPSHOT.jar
