Sample statemachine applicatie, die z'n state opslaat in een remote zookeeper instantie.
Hierdoor kunnen er meerdere instanties van deze applicatie draaien die de state delen. 


# Initiele configuratie:
oc new-build --name dstatemachine --image-stream="openshift/redhat-openjdk18-openshift:latest" --binary=true -n rzo-test 
oc start-build dstatemachine --from-file=target\dstatemachine-0.0.1-SNAPSHOT.jar
oc create -f openshift-scripts\deployconfig.yml -n rzo-test
oc create -f openshift-scripts\service.yml -n rzo-test
oc create -f openshift-scripts\route.yml -n rzo-test

# redeploy new version
mvn install
oc start-build dstatemachine --from-file=target\distributedstatemachine-0.0.1-SNAPSHOT.jar

# you need to have a zookeeper running on localhost: 2181.  
oc port-forward `oc get pod | grep rzozookeeper | grep -v build | awk '{print $1;}'` 2181:2181 