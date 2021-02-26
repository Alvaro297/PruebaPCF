# api-upload-files-spring

This project allow you to upload files in a persistent volume (file folder) 📝 by a REST API

## Getting Started

To use this project you could use the Dockerfile to build a local image or clone and run locally 

### Prerequisites

* Java 1.8 + 
* maven 
* Docker

Once you have cloned this project:

```
cd thc-upload-files-java
mvn clean package 
mvn spring-boot:run
```


### Installing with docker 

```
cp Dockerfle target/
cd target 
docker build -t thc-upload-images-local .
docker run -ti -d  -p 8080:8080 --restart=always -v $(pwd)/upload:/upload-dir/ --name local-files-thc thc-upload-images-local

```

Now you will be able to use postman to call:

* POST  http://localhost:8080/api/uploadFile/folder-imgs --> "folder-imgs" is a mandatory path variable, this endpoint sotore a file
* GET http://localhost:8080/api/getFiles --> Get all files 
* GET http://localhost:8080/api/files/folder-imgs/sample.jpg --> display an stored image 
* DELETE http://localhost:8080/api/deleteFile/uploadFile/sample.jpg --> delete a file 

```
until finished

```

### Installing with maven local 

```
mvn spring-boot:run

```

Now you will be able to use postman to call:

* POST  http://localhost:8080/api/uploadFile/folder-imgs --> "folder-imgs" is a mandatory path variable, this endpoint sotore a file
* GET http://localhost:8080/api/getFiles --> Get all files 
* GET http://localhost:8080/api/files/folder-imgs/sample.jpg --> display an stored image 
* DELETE http://localhost:8080/api/deleteFile/uploadFile/sample.jpg --> delete a file 


## Configurations 

To change limits you ccould edit  application.properties


* spring.servlet.multipart.max-file-size=1028KB  -> Limits  to store
* spring.servlet.multipart.max-request-size=1028KB -> Limits  to rq


## Deployment

TODO: Create a gitlab-ci file with this stages:

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management


## Build-Images docker image in docker.techinc.com.mx 
* [Docker] (https://docker.techinc.com.mx) - Private Repository
* buld, push

## Contributing

TBD

## Versioning

TBD

## Authors

* **Christian Regner 👺 ** - *Initial work* - [xtian](https://regner.com.mx)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
