# Initiele configuratie:
oc new-build --name dstatemachine --image-stream="openshift/redhat-openjdk18-openshift:latest" --binary=true -n rzo-test 
oc start-build dstatemachine --from-file=target\dstatemachine-0.0.1-SNAPSHOT.jar
oc create -f openshift-scripts\deployconfig.yml -n rzo-test
oc create -f openshift-scripts\service.yml -n rzo-test
oc create -f openshift-scripts\route.yml -n rzo-test

# redeploy new version
mvn install
oc start-build dstatemachine --from-file=target\distributedstatemachine-0.0.1-SNAPSHOT.jar
