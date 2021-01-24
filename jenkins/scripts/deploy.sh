#!/usr/bin/env bash
name=$1
external_port=$2

# remove older docker
if docker ps --format \'{{.Names}}\' | grep ${name} &> /dev/null; then
    docker stop ${name}
    docker rm ${name}
fi

# remove older image
if [[ "$(docker images -q ${name} 2> /dev/null)" != "" ]]; then
  docker image rm ${name}
fi

# create new image
docker image build -t ${name} ./

# create new docker
docker run --restart always -p ${external_port}:8080 --name ${name} -e POSTGRES_URL="${POSTGRES_URL}" -d ${name}
