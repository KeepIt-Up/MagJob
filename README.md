# MagJob

MagJob is an application supporting work and communication in companies and organizations.

**WARNING**  
- Please be aware that certain features may be unavailable or incomplete during this phase.
- Work is actively underway to finalize preliminary tasks and implement necessary security measures.
## Documentation

[Project Requirements and Use Cases](https://docs.google.com/document/d/16KAi7zJVni5puUcCnkQ6iIyY0P7fxuUKd_5zU1fNr8k/edit?usp=sharing)

### API Documentation (Swagger)

It is available at http://localhost:8080/swagger-ui/index.html#/ after starting the API
## Run Locally
Clone the project

```bash
  git clone https://github.com/KeepIt-Up/MagJob.git
```
### Run with Docker
Before you begin, ensure that you have the following tools installed on your system:

- [Docker](https://www.docker.com/get-started)
- [Maven](https://maven.apache.org/download.cgi)
- [Node.js](https://nodejs.org/)

Go to the project directory

```bash
  cd MagJob
```
Run *build.ps1* script
```
  ./build.ps1
  ```
Run *docker-compose.yml* with docker compose up

```bash
  docker-compose -f docker-compose.yml up  
```

### Run Frontend App
Before running the MagJob Frontend locally, ensure you have the following prerequisites installed on your system:
- Node.js and npm - [Download and Install Node.js](https://nodejs.org/)
- Angular CLI - Install globally using npm:
  ```bash
  npm install -g @angular/cli
  ```
Go to the project directory

```bash
  cd magjob-frontend
```
Install dependencies

```bash
  npm install
```
Run the Angular Development Server
```bash
  ng serve
```
This will start the development server, and you can access the application at http://localhost:4200 in your web browser.

### Run API
Before running the MagJob Backend locally, ensure you have the following prerequisites installed on your system:
- Java Development Kit (JDK) - [Download and Install JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- Apache Maven - [Download and Install Maven](https://maven.apache.org/download.cgi)

Go to the project directory

```bash
  cd magjob-backend
```
Build the project
```bash
  mvn clean install
```
Run the Spring Boot Application
```
  java -jar target/MagJobBackendApplication.jar
```
Once the application is running, you can access it locally by navigating to http://localhost:8080 in your web browser.
## Roadmap

- [x]   Login and Registration
- [x]   Creating an Organization
- [x]   Inviting Members to the Organization
- [x]   Member Management
- [ ]   Tasks with Chat for specific Roles and Organization Members
- [ ]   Roles in the Organization
- [ ]   Chats between Members of a given Organization,
- [ ]   Announcements with Notifications for specific Roles or the entire Organization
- [ ]   Schedule based on the Availability hours received from the Organization's Members
## Authors

- [@DamianLaczynski](https://github.com/DamianLaczynski) Team Leader, Product Owner, Frontend Developer
- [@WallyS02](https://github.com/WallyS02) DevOps, Backend Developer
- [@kmonieczny](https://github.com/kmonieczny) Backend Developer
- [@szymon002](https://github.com/szymon002) Backend Developer
- [@Ekmoot13](https://github.com/Ekmoot13) Frontend Developer
