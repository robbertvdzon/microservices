# Initiele configuratie:
oc new-build --name web-app1 --image-stream="openshift/redhat-openjdk18-openshift:latest" --binary=true -n rzo-test 
oc start-build web-app1 --from-file=target\web-app1-0.0.1-SNAPSHOT.jar
oc create -f openshift-scripts\deployconfig.yml -n rzo-test
oc create -f openshift-scripts\service.yml -n rzo-test
oc create -f openshift-scripts\route.yml -n rzo-test

# redeploy new version
mvn package
oc start-build web-app1 --from-file=target\web-app1-0.0.1-SNAPSHOT.jar
