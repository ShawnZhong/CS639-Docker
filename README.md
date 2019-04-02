# Docker image for CS 639: Introduction to Software Security

## How to run

`docker run -t -p 63900:63900 -p 8080:8080 -p 5000:5000 -p 5001:5001 -p 5002:5002 -v "${PWD}:/root/project" shawnzhong/cs639`

## How to build

`docker build . -t shawnzhong/cs639`

