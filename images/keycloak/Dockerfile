FROM quay.io/keycloak/keycloak:latest
COPY magjob-realm.json /opt/keycloak/data/import/magjob-realm.json

ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]