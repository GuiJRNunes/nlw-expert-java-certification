<h1 align="center"> NLW Expert Event - Java - Tech quiz and certification REST API </h1>

This project was built during RocketSeat's free online event "NLW Expert" which promoted the teaching of various technologies.

## Project

NLW Expert Java Certification is a web application that offers a REST API for answering quizes based on some technologies (e.g. Java) and generating certifications for those quizes.

## Utilized technologies, concepts and resources

- [Java 17](https://dev.java/learn/).
- [Maven](https://maven.apache.org/) - Project management.
- [SpringBoot](https://spring.io/) - Java framework.
- [Docker](https://www.docker.com/) - Isolate the database.
- [PostgreSQL](https://www.postgresql.org/) - Relational database.

## API

| HTTP Verbs | Endpoints                          | Action                                                           |
| ---------- | ---------------------------------- | ---------------------------------------------------------------- |
| GET        | /students/hasCertification         | Check if a given student already has the specified certification |
| GET        | /questions/technology/{technology} | Get all questions for the given technology                       |
| GET        | /ranking/top10                     | Get the top ten ranking certifications in all technologies       |
| POST       | /students/certification/answer     | Post an answer to a technology quiz                              |


Example body of a request for the `/students/hasCertification` endpoint:
```JSON
{
  "email": "test@test.com",
  "technology": "JAVA"
}
```

Example body of a request for the `/students/certification/answer` endpoint:
```JSON
{
  "email": "grade@two.com",
  "technology": "JAVA",
  "answers": [
    {
      "questionId": "c5f02721-6dc3-4fa6-b46d-6f2e8dca9c66",
      "answerId": "bafdf631-6edf-482a-bda9-7dce1efb1890"
    },
    {
      "questionId": "b0ec9e6b-721c-43c7-9432-4d0b6eb15b01",
      "answerId": "f8e6e9b3-199b-4f0d-97ce-7e5bdc080da9"
    },
    {
      "questionId": "f85e9434-1711-4e02-9f9e-7831aa5c743a",
      "answerId": "e4dbf524-0a54-428a-b57c-853996fc8e19"
    }
  ]
}
```

## Installing and Running

Make sure you have Maven and Docker installed.

Download the repository.
```
git clone https://github.com/GuiJRNunes/nlw-expert-java-certification.git
```

Move to the appropriate  folder.
```
cd .\nlw-expert-java-certification\
```

Clean and install necessary dependencies.
```
mvn clean dependency:copy-dependencies
```

Set up and run the Docker container for PostgreSQL in the background.
```
docker compose up -d
```

Run the project.
```
mvn spring-boot:run
```

Run the `src\main\java\com\github\guijrnunes\certification_nlw\seed\CreateSeed.java` main function to seed the database (Inserts a Java quiz with 3 questions).

Access one of the endpoints to see if it is all working.