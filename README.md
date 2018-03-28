# Reproduce bug of SO_REUSEPORT

Run on a linux machine

```
mvn clean package -Plinux
nohup java -jar target/vertx-native-transport-test-1.0-SNAPSHOT-jar-with-dependencies.jar &
java -jar target/vertx-native-transport-test-1.0-SNAPSHOT-jar-with-dependencies.jar
```

We hope that the two process can listen on the same port, with same ip address.
Unfortunately, it will cause an error [**Address already in use**], which means the SO_REUSEPORT is not set correctly.