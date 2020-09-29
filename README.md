# Power of Attorney Service
This awesome web service provides REST API for accessing power of attorney information of a user
  - Power of attorney details such as grantee, grantor and account details (/power-of-attorneys/{id})
  - Details for card products authorized by the power of attorney (/debit-cards/{id} and /credit-cards/{id})
  - Account details (/accounts/{id})
  - Some developer might have made an error somewhere

**To build and run:** use `mvn compile exec:java`
**Application runs on:** http://localhost:8080
**REST API documentation:** http://localhost:8080/swagger/

# Exercise!
  - Build a REST API presenting aggregated information from different services
  - Only show data that a user is actually authorized for
  - Handle any server errors you might run into gracefully
  
# Requirements
  - Requirements of the code and functionality is up to the candidate
  - We suggest using Java 11, Spring-Boot & Maven, but using Kotlin or Gradle is also fine
  - Perform whatever validation seems necessary
  - Don't return inactive products or accounts
  - (Optional) Expose the API over HTTPS
 
# Tips
  - Because every candidate has different experience and background, the candidate should decide on how complex code they want to show us
  - If the assignment is unclear, do what you feel is best and focus on the code, not the exercise
  - We look at the quality and readability of code that has been delivered more than if the functionality matches our expectations
  - Impress us!

# How to's
- Start app: 'mvn clean spring-boot:run'
- Swagger: 'http://localhost:8080/swagger-ui.html'

# Steps to take
- [x] Spring boot plumming
- [x] Make Rest Resources 
- [x] Swagger
- [x] start with Accounts
- [x] Continue with Cards
- [x] Power of Attorneys
- [x] How to deal with groups (Fellowship)
- [x] find out domain logic and aggregates
- [x] outside in -> add logic
- [] transforming to the right structure
- [] exceptions mapping to http status codes
- [] test direction of dependencies with archunit
- [x] blocked cards and ended accounts can be viewed
- [] validate domain primitives
- [x] Static repositories -> read only
- [x] hexagonal at start
- [] check TODO's
- [] more test coverage
- [] endpoint tests with testdata

