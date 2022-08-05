## Spring-Security

This repo is an implementation of complete registration flow with email verification, resend email verification code, forgot password, change password and login functionality with OAuth 2.0 and Open Id Connect. This was accomplished with the help of `Spring-Boot`, `Spring-Data`, `Spring-Security` and `Postman` for 
testing the webservice api.

#### This folder contains the following:
1. **src** - This source folder includes all the packages and java classes that build up the service.
2. **applications.yml** - includes the connector for MySQL.
3. **pom.xml** - includes all of the dependencies necessary for the service to fuction as intended. 

## Getting started

To run, you need to need to have Java 11 (and above) installed. It is recommended to customize the `application.yml` according to your needs and use it in the following way:

If you already have an `application.yml` file in the directory, run:

```
java -jar spring-security.jar
```

To use a properties/yml file from another directory, add the full path as an argument:

```
java -jar spring-security.jar /.../application.yml
```

This will try to read the specified properties file based on availability. If it does fail or has missing values, the server will read from the defaults.

The properties file should also include a password column in the application.properties file, so that the connection can be made with the database.

If you'd like to build from source, follow along:

## Build Instructions

#### For building the project do the following:

#### Clone the project

```
git clone https://github.com/parithoshpoojary/spring-security.git
cd spring-security/
```

### Configuration

To choose a custom port number, etc. head over to the [application.yml](https://github.com/parithoshpoojary/spring-security/blob/main/src/main/resources/application.yml) file in the src/main/resources/ directory and change the configuration to your preferences.

Once you are done configuring, do either of the following methods.

### Building with Maven (For developers)

If you'd like to change something on the service itself and test it on your local machine, I'd personally prefer this method since it can use your local `.m2` repository.

Build Requirements

* Java 11 or Java 17
* Apache Maven

#### Steps

If you already have maven installed in your system, you can compile the source to generate a binary.

`mvn clean package` to build the system. It will run all the tests in the project and verify everything's alright. If the tests fail, it probably is because there was no mysql database running on your machine. Turn them on and run `mvn clean package` once again. A binary will be generated at `target/`, enter the directory.

Run the generated binary with: `java -jar spring-security.jar`

## Contributing
To contribute to the repository, open an issue or a pull request.

For more information about spring, check their [documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/).
