# Initiele configuratie:
oc new-build --name web-app2 --image-stream="openshift/redhat-openjdk18-openshift:latest" --binary=true -n rzo-test 
oc start-build web-app2 --from-file=target\web-app2-0.0.1-SNAPSHOT.jar
oc create -f openshift-scripts\deployconfig.yml -n rzo-test
oc create -f openshift-scripts\service.yml -n rzo-test
oc create -f openshift-scripts\route.yml -n rzo-test

# redeploy new version
oc start-build web-app2 --from-file=target\web-app2-0.0.1-SNAPSHOT.jar
