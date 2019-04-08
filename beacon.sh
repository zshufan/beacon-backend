#!/usr/bin/env bash

checkEnv(){
    hash docker 2>/dev/null||{echo >&2 "docker not installed."}
    hash java 2>/dev/null||{echo >&2 "java environment not installed."}
    hash docker-compose 2>/dev/null||{echo >&2 "docker-compose environment not installed."}
}

genWxConfig(){
    if [[ ! -f src/main/resources/config/a.txt  ]];then
        touch src/main/resources/config/wechat.properties;
        cat > src/main/resources/config/wechat.properties <<EOF
app_id=${app_id}
auth_url=https://api.weixin.qq.com/sns/jscode2session?appid={?}&secret={?}&js_code={?}&grant_type=authorization_code
app_secret=${app_secret}
EOF
    fi
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
    docker-compose up -d --force-recreate
}

checkEnv;
genWxConfig;
buildProject;
