docker rmi aws-test:v1-dev -f
rm aws-test-1.0.jar 
cp ../aws-test/target/aws-test-1.0.jar .
docker build -t "aws-test:v1-dev" .
