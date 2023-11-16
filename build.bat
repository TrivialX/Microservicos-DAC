REM Iniciando as imagens
echo @off
echo MICRO-AUTH

cd micro-auth
call ./mvnw spring-boot:build-image -DskipTests
cd..

cd micro-conta
call ./mvnw spring-boot:build-image -DskipTests
cd ..

echo build and up containeers
docker compose build
docker compose up -d