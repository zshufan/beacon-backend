#!/usr/bin/env bash

checkEnv(){
    hash docker 2>/dev/null||{echo >&2 "docker not installed."}
    hash java 2>/dev/null||{echo >&2 "java environment not installed."}
}

buildProject(){
    bash gradlew clean bootJar;
    rm -f dist/beacon/beacon-*.jar;
    rm -f dist/beacon/application-prod.yaml;
    cp build/libs/beacon-*jar dist/beacon/;
    cp src/main/resources/application-dev.yaml application-prod.yaml;
    docker build ./dist/sql/ -t beacon-db;
    docker build ./dist/beacon/ -t beacon;
}

checkEnv ;
buildProject ;