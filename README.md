## EasyCheckout Project

### Requirements:
- A working computer (At least 8GB RAM, 2.0GHZ Processor and 120GB Storage Space)
- [Gradle](https://gradle.org/install)
- [JDK 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- Java IDE, preferably [IntelliJ IDEA](https://www.jetbrains.com/help/idea/install-and-set-up-product.html)
- [Postman](https://www.getpostman.com/apps)
- [MySQL 5.7](https://dev.mysql.com/downloads/mysql/5.7.html)
- [Payara Server](https://www.payara.fish/software/downloads/)

### Clone
- To clone this project: git clone git@bitbucket.org:softpay/qrpay.git

### Set Up
- Copy `gradle.properties.template` to `gradle.properties` and change the contents to match your configurations.
- CD to into the project directory and:
- Run `gradle startServer` on your terminal
- Run `gradle redeploy` on your terminal to deploy the application
- To stop the server, run `gradle stopServer` on your terminal
