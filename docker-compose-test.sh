#!/bin/bash
export FRONT_VERSION="1.0"
export ARCH=$(uname -m)

export ALLURE_DOCKER_API="https://allure-service.mxytin.ip-ddns.com"
export BUILD_URL="https://github.com/mourav2222/rangiffler"
export HEAD_COMMIT_MESSAGE="test docker run"
export EXECUTION_TYPE="manual"

docker compose -f docker-compose-test.yml down
bash ./gradlew clean
if [ "$1" = "push" ]; then
  echo "### Build & push images ###"
  bash ./gradlew jib -x :rangiffler-e2e:test
  docker compose push client.rangiffler.dc
else
  echo "### Build images ###"
  bash ./gradlew jibDockerBuild -x :rangiffler-e2e:test
fi

#docker image prune -f
echo #docker rmi $(docker images -q --filter "reference=dtuchs/*" | awk '{ print $1 }')
echo images: $(docker images -q --filter "reference=dtuchs/*" | awk '{ print $1 }')
docker rmi $(docker images -q --filter "reference=dtuchs/*" | awk '{ print $1 }')

docker compose -f docker-compose-test.yml up -d
#docker ps -a | grep -E "dtuchs|mysql"
docker compose -f docker-compose-test.yml ps
