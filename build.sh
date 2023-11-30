# Iniciando as imagens
echo "MICRO-AUTH"

cd micro-auth
./mvnw package spring-boot:build-image -DskipTests
cd ..

cd micro-conta
./mvnw package spring-boot:build-image -DskipTests
cd ..

cd cliente
./mvnw package spring-boot:build-image -DskipTests
cd ..

cd micro-gerente
./mvnw package spring-boot:build-image -DskipTests
cd ..

cd saga
./mvnw package spring-boot:build-image -DskipTests
cd ..

cd api_gateway
docker build -t apigetway .
cd ..

echo "build and up containers"
docker-compose build --no-cache
docker-compose up -d

