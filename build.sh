#!/bin/bash


mvn clean package  -DskipTests -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true

docker build -t toutiao . 