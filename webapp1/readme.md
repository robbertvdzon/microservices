
# Initiele configuratie:
oc new-build --name app1 --image-stream="openshift/redhat-openjdk18-openshift:latest" --binary=true -n rzo-test 
oc start-build app1 --from-file=target\demo-0.0.1-SNAPSHOT.jar
oc create -f deployconfig.yml -n rzo-test
oc create route edge --service=app1

# Nieuwe versie deployen:
mvn package
oc start-build app1 --from-file=target\demo-0.0.1-SNAPSHOT.jar
