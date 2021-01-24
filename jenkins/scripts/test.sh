#!/usr/bin/env bash
name=spring_test

# remove test image if exists	
if [[ "$(docker images -q ${name} 2> /dev/null)" != "" ]]; then
  docker image rm ${name}
fi

# create test image	
docker image build --no-cache -t ${name} ./
