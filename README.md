## ABOUT THIS PROJECT

This project is created as an assignment for the Current Trends in Software Engineering - SE4010 module.
This project is implemented to serve the frontend of the application.

### Implemented Services

- [x] Order Management

### Getting started:

### Used Docker commands

1. To build the docker image.

```console
docker build -t e-commerce-app-backend:v1.0.0 .
```

2. To run create and run the container.

```console
docker run --rm -p 8080:8080 --name e-commerce-app-container e-commerce-app-backend:v1.0.0
```

3. To see the container's stuffs.

```console
docker exec -it e-commerce-app-container /bin/sh
```

4. Push the docker image to the dockerhub repository.

```console
docker tag e-commerce-app-backend:v1.0.0 dhanushaperera07/e-commerce-app-backend:v1.0.0
docker push dhanushaperera07/e-commerce-app-backend:v1.0.0
```

### Minikube related commands.

1. Start Minikube cluster

```console
minikube start
```

```console
minikube status
```

### kubectl commands.

```console
kubectl get node
```

```console
kubectl get pod
```

```console
kubectl apply -f ./kubernetes/e-commerce-backend-configmap.yaml
```

```console
kubectl apply -f ./kubernetes/e-commerce-backend-secret.yaml
```

```console
kubectl apply -f ./kubernetes/e-commerce-backend.yaml
```

```console
kubectl get all
```

```console
kubectl get configmap
```

```console
kubectl get secret
```

Get pods only.

```console
kubectl get pod
```

```console
kubectl get --help
```

```console
kubectl describe service e-commerce-backend-java-service
```

```console
kubectl describe pod e-commerce-backend-java-deployment-7847bf67bb-7cmtz
```

```console
kubectl get all \
kubectl logs e-commerce-backend-java-deployment-7847bf67bb-7cmtz

# Stream the logs using -f
kubectl logs e-commerce-backend-java-deployment-7847bf67bb-7cmtz -f
```

```console
kubectl get service

# get the minikube cluster IP address to check our API.
minikube ip

# or we can use this following command to find the minikube cluster IP address.
kubectl get node -o wide
```