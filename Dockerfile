FROM abdelnaby1/alpine-jdk17-curl-jq:latest

#FROM bellsoft/liberica-openjdk-alpine:17.0.8

#RUN apk add curl jq

WORKDIR /home/selenium-docker

Add target/docker-resources ./

ADD runner.sh runner.sh

ENTRYPOINT sh runner.sh