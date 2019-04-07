#!/usr/bin/env bash

checkEnv(){
    hash docker 2>/dev/null||{echo >&2 "docker not installed."}
    hash java 2>/dev/null||{echo >&2 "java environment not installed."}
    hash docker-compose 2>/dev/null||{echo >&2 "docker-compose environment not installed."}
}

buildProject(){
    bash gradlew clean bootJar;
    
    if [[ -f dist/beacon/beacon-latest.jar  ]];then
        rm -f dist/beacon/beacon-latest.jar;
    fi

    if [[ -f "dist/beacon/application-prod.yaml" ]]; then
        rm -f dist/beacon/application-prod.yaml;
    fi
    
    cp build/libs/beacon-*jar dist/beacon/beacon-latest.jar;
    cp src/main/resources/application-dev.yaml dist/beacon/application-prod.yaml;
    docker-compose up -d
}

checkEnv;
buildProject;