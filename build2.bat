@echo off
start cmd /c "cd micro-auth &&  call ./mvnw spring-boot:build-image -DskipTests"
start cmd /c "cd micro-conta &&  call ./mvnw spring-boot:build-image -DskipTests"
start cmd /c "cd cliente &&  call ./mvnw spring-boot:build-image -DskipTests"
start cmd /c "cd micro-gerente &&  call ./mvnw spring-boot:build-image -DskipTests"
start cmd /c "cd saga &&  call ./mvnw spring-boot:build-image -DskipTests"

:WAIT
timeout /nobreak /t 1 >nul
tasklist | find "cmd.exe" >nul && goto :WAIT

docker compose build --no-cache
docker compose up -d