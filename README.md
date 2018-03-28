# Reproduce bug of SO_REUSEPORT

Run on a linux machine

```
mvn clean package -Plinux
nohup java -jar target/vertx-native-transport-test-1.0-SNAPSHOT-jar-with-dependencies.jar &
java -jar target/vertx-native-transport-test-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Start the second process, it will get the error **Address already in use**,
which means the SO_REUSEPORT is not set correctly.