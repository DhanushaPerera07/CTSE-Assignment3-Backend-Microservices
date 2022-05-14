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
