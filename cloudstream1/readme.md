Sample cloudstream applicatie, die kafka gebruikt als message bus 


# Initiele configuratie:
oc new-build --name cloudstream1 --image-stream="openshift/redhat-openjdk18-openshift:latest" --binary=true -n rzo-test 
oc start-build cloudstream1 --from-file=target\cloudstream1-0.0.1-SNAPSHOT.jar
oc create -f openshift-scripts\deployconfig.yml -n rzo-test
oc create -f openshift-scripts\service.yml -n rzo-test
oc create -f openshift-scripts\route.yml -n rzo-test

# redeploy new version
mvn install
oc start-build cloudstream1 --from-file=target\cloudstream1-0.0.1-SNAPSHOT.jar

# you need to have a rzokafka running on localhost: 9092.  
oc port-forward `oc get pod | grep rzokafka | grep -v build | awk '{print $1;}'` 9092:9092

./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic greetings2 