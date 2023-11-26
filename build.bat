REM Iniciando as imagens
echo @off
echo MICRO-AUTH

cd micro-auth
call ./mvnw spring-boot:build-image -DskipTests
cd..

cd micro-conta
call ./mvnw spring-boot:build-image -DskipTests
cd ..

cd cliente
call ./mvnw spring-boot:build-image -DskipTests
cd..

cd micro-gerente
call ./mvnw spring-boot:build-image -DskipTests
cd..

cd saga
call ./mvnw spring-boot:build-image -DskipTests
cd..

echo build and up containeers
docker compose build --no-cache
docker compose up -d