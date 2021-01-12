# Kafka

### Install Kafka
source:
https://medium.com/@Ankitthakur/apache-kafka-installation-on-mac-using-homebrew-a367cdefd273


```
$ brew cask install java
$ brew install kafka
```

Here are the logs:

``` ==> Installing dependencies for kafka: zookeeper
    ==> Installing kafka dependency: zookeeper
    ==> Downloading https://homebrew.bintray.com/bottles/zookeeper-3.4.12.high_sierra.bottle.tar.gz
    ######################################################################## 100.0%
    ==> Pouring zookeeper-3.4.12.high_sierra.bottle.tar.gz
    ==> Caveats
    To have launchd start zookeeper now and restart at login:
    brew services start zookeeper
    Or, if you don't want/need a background service you can just run:
    zkServer start
    ==> Summary
    🍺  /usr/local/Cellar/zookeeper/3.4.12: 242 files, 32.9MB
    ==> Installing kafka
    ==> Downloading https://homebrew.bintray.com/bottles/kafka-2.0.0.high_sierra.bottle.tar.gz
    ######################################################################## 100.0%
    ==> Pouring kafka-2.0.0.high_sierra.bottle.tar.gz
    ==> Caveats
    To have launchd start kafka now and restart at login:
    brew services start kafka
    Or, if you don't want/need a background service you can just run:
    zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties & kafka-server-start /usr/local/etc/kafka/server.properties
    ==> Summary
    🍺  /usr/local/Cellar/kafka/2.0.0: 160 files, 46.8MB
    ==> Caveats
    ==> zookeeper
    To have launchd start zookeeper now and restart at login:
    brew services start zookeeper
    Or, if you don't want/need a background service you can just run:
    zkServer start
    ==> kafka
    To have launchd start kafka now and restart at login:
    brew services start kafka
    Or, if you don't want/need a background service you can just run:
    zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties & kafka-server-start /usr/local/etc/kafka/server.properties 
```


### Now start Zookeeper and Kafka

#### Start Zookeeper

``` 
$ zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties 
```


#### Start Kafka server

``` 
$ kafka-server-start /usr/local/etc/kafka/server.properties 
```

WARNING: During server start, you might be facing connection broken issue.

``` 
[2018-08-28 16:24:41,166] WARN [Controller id=0, targetBrokerId=0] Connection to node 0 could not be established. Broker may not be available. (org.apache.kafka.clients.NetworkClient)
[2018-08-28 16:24:41,268] WARN [Controller id=0, targetBrokerId=0] Connection to node 0 could not be established. Broker may not be available. (org.apache.kafka.clients.NetworkClient)
```

To fix this issue, we need to change the server.properties file.

```
$ vim /usr/local/etc/kafka/server.properties
```

or

```
$ open /usr/local/etc/kafka/server.properties
```

Here uncomment the server settings and update the value ``listeners=PLAINTEXT://:9092`` to

```
############################# Socket Server Settings #############################
# The address the socket server listens on. It will get the value returned from 
# java.net.InetAddress.getCanonicalHostName() if not configured.
#   FORMAT:
#     listeners = listener_name://host_name:port
#   EXAMPLE:
#     listeners = PLAINTEXT://your.host.name:9092
listeners=PLAINTEXT://localhost:9092
```

and restart the server and it will work great.


#### Create Kafka Topic

```
$ kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka_test
```
Here we have created a topic name `test`


#### Initialize Producer console

Now we will initialize the Kafka producer console, which will listen to localhost at port 9092 at topic test :
```
$ kafka-console-producer --broker-list localhost:9092 --topic kafka_test
>send first message
>send second message
>wow it is working
```


#### Initialize Consumer console

Now we will initialize the Kafka consumer console, which will listen to bootstrap server localhost at port 9092 at topic test from beginning:
```
$ kafka-console-consumer --bootstrap-server localhost:9092 --topic kafka_test --from-beginning
send first message
send second message
wow it is working
```
