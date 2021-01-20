# SingleIBANVerificationService

<br/>
<br/>
Deployment instructions: <br/>
  prerequisites: <br/>
    - Java JDK 11.0.7 installed and JAVA_HOME environment variable set<br/>
    - Port localhost:8081 should not be blocked<br/>
  deployment steps:<br/>
    - Build the application. Command: %LocalRepositoryPath%/mvnw clean package<br/>
    - Run the application. Command: java -jar %LocalRepositoryPath%/target/ibanvalidation-1.0.0.jar<br/>
