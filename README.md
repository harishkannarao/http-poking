# http-poking
This repository is to configure and keep webclient and rest assured component to poke http apis or website on adhoc basis

## Travis CI Build status
[![Build Status](https://travis-ci.org/harishkannarao/http-poking.svg?branch=master)](https://travis-ci.org/harishkannarao/http-poking)

## Required Softwares, Tools and Version
* Java JDK Version: 17 (`java -version`)
* Git Client: Any latest version (`git --version`)
* Integrated Development Environment: Any version of IntelliJ Idea or Eclipse

## To build

    ./gradlew clean build
    
## To assemble and run as jar

    ./gradlew clean assemble
    
    java -jar build/libs/http-client-app-exec.jar