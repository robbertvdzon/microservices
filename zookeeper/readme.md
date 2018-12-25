# Initiele configuratie:
oc create -f openshift-scripts\is.yml -n rzo-test
oc create -f openshift-scripts\buildconfig.yml -n rzo-test
oc create -f openshift-scripts\deployconfig.yml -n rzo-test
oc create -f openshift-scripts\service.yml -n rzo-test
oc create -f openshift-scripts\route.yml -n rzo-test
