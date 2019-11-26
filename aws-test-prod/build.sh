rm aws-test-1.0.jar 
cp ../aws-test/target/aws-test-1.0.jar .
docker build -t "natasevero/aws-test:v1-prod" .
docker push natasevero/aws-test:v1-prod
