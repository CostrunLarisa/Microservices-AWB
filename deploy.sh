docker build -t costrunlarisa/multi-client -f ./client-service/Dockerfile ./client-service
docker build -t costrunlarisa/multi-eurekaserver -f ./eureka-server/Dockerfile ./eureka-server
docker build -t costrunlarisa/multi-product -f ./product-service/Dockerfile ./product-service
docker build -t costrunlarisa/multi-order -f ./order-service/Dockerfile ./order-service

docker push costrunlarisa/multi-client
docker push costrunlarisa/multi-eurekaserver
docker push costrunlarisa/multi-order
docker push costrunlarisa/multi-product

kubectl apply -f config
kubectl set image deployments/server-deployment server=costrunlarisa/multi-eurekaserver