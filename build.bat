REM Iniciando as imagens
echo @off
echo MICRO-AUTH
cd demo 

call ./mvnw spring-boot:build-image -DskipTests -Dspring-boot.build-image.imageName=micro-auth

cd ..

echo build and up containeers
docker compose build
docker compose up -d