BACKEND_IMAGE_NAME="keepitup2023/magjob-backend:0.0.1"
FRONTEND_IMAGE_NAME="keepitup2023/magjob-frontend:0.0.1"
STACK_NAME="magjob"

docker pull $BACKEND_IMAGE_NAME
docker pull $FRONTEND_IMAGE_NAME

if [ -d "MagJob" ]; then
    rm -rf MagJob
fi

git clone https://github.com/KeepIt-Up/MagJob/

cp .env MagJob

cd MagJob

docker run -d \
  --name=keycloak-auxiliary \
  -v keycloak:/keycloak \
  -v ./keycloak/certs:/keycloak-auxiliary \
  busybox cp -r /keycloak-auxiliary/* /keycloak

docker run -d \
  --name=prometheus-auxiliary \
  -v prometheus:/prometheus \
  -v ./monitoring/prometheus:/prometheus-auxiliary \
  busybox cp -r /prometheus-auxiliary/* /prometheus

docker run -d \
  --name=grafana-auxiliary \
  -v grafana:/grafana \
  -v ./monitoring/grafana/provisioning/datasources:/grafana-auxiliary \
  busybox cp -r /grafana-auxiliary/* /grafana

$(cat .env | sed 's/^/export /') && docker stack deploy -c docker-compose.yml $STACK_NAME --with-registry-auth

docker container rm keycloak-auxiliary
docker container rm prometheus-auxiliary
docker container rm grafana-auxiliary
