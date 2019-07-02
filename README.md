# Description
This is guide for basic operation with Kafka
# Setup and start Kafka server
- Download Kafka: https://www.apache.org/dyn/closer.cgi?path=/kafka/2.2.0/kafka_2.12-2.2.0.tgz
- Extract Kafka to: D:\Apps\kafka_2.12-2.2.0
- cd D:\Apps\kafka_2.12-2.2.0
- Kafka uses ZooKeeper, need to first start a ZooKeeper server: `bin\windows\zookeeper-server-start.bat config\zookeeper.properties`
- Start Kafka server: `bin\windows\kafka-server-start.bat config/server.properties`

Note: Make sure Java 8 or above is used on Kafka server.

# Create a topic
- create a topic named "test" with a single partition and only one replica: `bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test`
- List topic: `bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092`
# Send messages to topic
- `bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic test`
# Start a consumer
- `bin\windows\kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning`
# Setting up a multi-broker cluster
This is guide for setting up a cluster of three nodes.
- Make a config file for each of the brokers
  - `copy config\server.properties config\server-1.properties`
    - Update these properties in server-1.properties file:
        - broker.id=1
        - listeners=PLAINTEXT://:9093
        - log.dirs=/tmp/kafka-logs-1
  - `copy config\server.properties config\server-2.properties`
      - Update these properties in server-1.properties file:
        - broker.id=2
        - listeners=PLAINTEXT://:9094
        - log.dirs=/tmp/kafka-logs-2
- Start the two new nodes (Zookeeper and one Kafka node with server.properties has already started before)
  - `bin\windows\kafka-server-start.bat config/server-1.properties`
  - `bin\windows\kafka-server-start.bat config/server-2.properties`
- List processId of nodes: `wmic process where "caption = 'java.exe' and commandline like '%server-%.properties%'" get processid`
- Create a new topic with a replication factor of three
  `bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 1 --topic my-replicated-topic`
- Check which broker is doing what with topic
  - `bin\windows\kafka-topics.bat --describe --bootstrap-server localhost:9092 --topic my-replicated-topic`
# Use Kafka Connect to import/export data
- TODO:...
# Use Kafka Streams to process data
- TODO:...


auto.create.topics.enable
