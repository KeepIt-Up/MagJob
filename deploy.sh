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

docker stack deploy -c docker-compose.yml $STACK_NAME --with-registry-auth