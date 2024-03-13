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
  --name=prometheus-auxiliary \
  -v ./monitoring/prometheus:/prometheus-auxiliary \
  --mount type=volume,source=prometheus,target=/prometheus-auxiliary,volume-driver=vieux/sshfs,volume-opt=sshcmd=${STORAGE_USER}@${STORAGE_IP}:/home/ec2-user/volume/prometheus \
  busybox cp -r /prometheus-auxiliary/* prometheus

docker run -d \
  --name=grafana-auxiliary \
  -v ./monitoring/grafana/provisioning/datasources:/grafana-auxiliary \
  --mount type=volume,source=grafana,target=/grafana-auxiliary,volume-driver=vieux/sshfs,volume-opt=sshcmd=${STORAGE_USER}@${STORAGE_IP}:/home/ec2-user/volume/grafana \
  busybox cp -r /grafana-auxiliary/* grafana

$(cat .env | sed 's/^/export /') && docker stack deploy -c docker-compose.yml $STACK_NAME --with-registry-auth

docker container rm prometheus-auxiliary
docker container rm grafana-auxiliary
