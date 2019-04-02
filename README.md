# Docker image for CS 639: Introduction to Software Security

## How to run

`docker run -t -p 63900:63900 -p 8080:8080 -v "${PWD}:/root/project" shawnzhong/cs639`

## How to build

`docker build . -t shawnzhong/cs639`

