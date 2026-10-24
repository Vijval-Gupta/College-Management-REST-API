# College Management REST API

A backend application built with **Java, Spring Boot, Spring Data JPA, Hibernate, and PostgreSQL** to manage students, departments, and courses through RESTful APIs.

The application provides endpoints for creating, retrieving, updating, and deleting records. It also manages relationships between students, departments, and courses, with DTOs used to structure API requests and responses.

## Project Overview

The College Management REST API provides a centralized backend for managing student, department, and course information.

Students can be associated with departments and enrolled in multiple courses. Departments maintain their student relationships, while courses can be associated with multiple students.

The application uses a layered structure to separate HTTP requests, business logic, database operations, and data models. DTOs are used to control the data exposed through API responses and to avoid directly returning complete entity relationships.

## Features

### Student Management
- Create student records with personal details and department information.
- Retrieve all students or a specific student by ID.
- Update existing student information.
- Delete student records.
- Return student information through response DTOs.

### Department Management
- Create departments with a name and department code.
- Retrieve all departments or a specific department.
- Update department information.
- Delete departments along with their associated students, as configured through cascading deletion.
- Retrieve department details along with student summary information.

### Course Management
- Create courses with course details.
- Retrieve all courses or a specific course.
- Update existing course information.
- Delete courses.
- Retrieve students associated with a particular course.

### Data and Relationship Management
- Manage one-to-many, many-to-one, and many-to-many relationships.
- Use DTOs to structure incoming requests and outgoing responses.
- Persist application data in PostgreSQL using Spring Data JPA and Hibernate.
- Use repository classes for database operations.
- Apply cascading deletion from departments to their associated students.

## Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application programming language |
| Spring Boot | Application configuration and backend development |
| Spring Web | Building REST endpoints and handling HTTP requests |
| Spring Data JPA | Database access and repository operations |
| Hibernate | ORM implementation for mapping Java objects to database tables |
| Jakarta Persistence API (JPA) | Entity mapping and relationship configuration |
| PostgreSQL | Relational database |
| Maven | Dependency management and application execution |
| Postman | Testing API endpoints |

## Project Structure

The application separates controllers, services, repositories, entities, and DTOs into different packages.

```text
src/main/java/org/vijval/studentmanager/
├── Controller/
│   ├── StudentController.java
│   ├── DepartmentController.java
│   └── CourseController.java
│
├── Service/
│   ├── StudentService.java
│   ├── DepartmentService.java
│   └── CourseService.java
│
├── Repository/
│   ├── StudentRepository.java
│   ├── DepartmentRepository.java
│   └── CourseRepository.java
│
├── Entity/
│   ├── Student.java
│   ├── Department.java
│   └── Course.java
│
└── DTO/
    ├── StudentRequestDTO.java
    ├── StudentResponseDTO.java
    ├── StudentSummaryDTO.java
    ├── DepartmentRequestDTO.java
    ├── DepartmentResponseDTO.java
    ├── DepartmentSummaryDTO.java
    ├── CourseRequestDTO.java
    └── CourseResponseDTO.java
```

*This structure represents the main classes used in the project. Adjust file names or packages if they differ in your repository.*

### Role of Each Layer

- **Controller:** Defines API endpoints, accepts HTTP requests, and returns HTTP responses.
- **Service:** Contains application logic and coordinates operations between repositories and entities.
- **Repository:** Handles database operations through Spring Data JPA.
- **Entity:** Defines the `Student`, `Department`, and `Course` data models and their database relationships.
- **DTO:** Defines the data accepted by API requests and returned in API responses.

## Entity Relationships

The application contains three main entities: `Student`, `Department`, and `Course`.

### 1. Student and Department

**Relationship: Many-to-One / One-to-Many**

- A student belongs to one department.
- A department can have multiple students.
- The relationship is defined using `@ManyToOne` in the `Student` entity and `@OneToMany` in the `Department` entity.
- The student's department reference is stored through a foreign key in the student table.

### 2. Student and Course

**Relationship: Many-to-Many**

- A student can be associated with multiple courses.
- A course can be associated with multiple students.
- The relationship is defined using `@ManyToMany`.
- A join table named `student_course` maintains the associations between students and courses.

### 3. Cascading Department Deletion

When a department is deleted, its associated students are also deleted through the configured `CascadeType.REMOVE` relationship.

The associated student-course mappings are removed as part of deleting the students. The related course records are not intended to be deleted through the department relationship.

## API Documentation

The following endpoints are exposed by the application.

**Base URL:** `http://localhost:8080`

### 1. Student API

Base path: `/api/student`

| HTTP Method | Endpoint | Description | Expected Success Response |
|---|---|---|---|
| `POST` | `/api/student` | Create a student | `200 OK` or configured success status |
| `GET` | `/api/student` | Retrieve all students | `200 OK` |
| `GET` | `/api/student/{id}` | Retrieve a student by ID | `200 OK` |
| `PUT` | `/api/student/{id}` | Update a student | `200 OK` |
| `DELETE` | `/api/student/{id}` | Delete a student | Depends on controller implementation |

### 2. Department API

Base path: `/api/department`

| HTTP Method | Endpoint | Description | Expected Success Response |
|---|---|---|---|
| `POST` | `/api/department` | Create a department | `200 OK` |
| `GET` | `/api/department` | Retrieve all departments | `200 OK` |
| `GET` | `/api/department/{id}` | Retrieve a department by ID | `200 OK` |
| `PUT` | `/api/department/{id}` | Update a department | `200 OK` |
| `DELETE` | `/api/department/{id}` | Delete a department and its associated students | Depends on controller implementation |

### 3. Course API

Base path: `/api/courses`

| HTTP Method | Endpoint | Description | Expected Success Response |
|---|---|---|---|
| `POST` | `/api/courses` | Create a course | `201 Created` |
| `GET` | `/api/courses` | Retrieve all courses | `200 OK` |
| `GET` | `/api/courses/{id}` | Retrieve a course by ID | `200 OK` |
| `PUT` | `/api/courses/{id}` | Update a course | `200 OK` |
| `DELETE` | `/api/courses/{id}` | Delete a course | `204 No Content` |
| `GET` | `/api/courses/{id}/students` | Retrieve students associated with a course | `200 OK` |

## Example API Requests

The following examples illustrate how to interact with the API using JSON. Adjust the request fields to match the corresponding DTO definitions in your source code.

### Create a Department

**Request**

```http
POST /api/department
Content-Type: application/json
```

```json
{
  "name": "Computer Science",
  "code": "CSE"
}
```

### Retrieve All Departments

```http
GET /api/department
```

A department response can contain its ID, name, code, and a list of student summaries.

### Create a Student

**Request**

```http
POST /api/student
Content-Type: application/json
```

```json
{
  "name": "John Doe",
  "age": 21,
  "email": "john@example.com",
  "department_id": 1
}
```

The department ID must refer to an existing department. The exact JSON property name for the department ID depends on the `StudentRequestDTO` definition.

### Retrieve a Student

```http
GET /api/student/1
```

The response includes student details and a summary of the associated department, according to the response DTO.

### Update a Student

**Request**

```http
PUT /api/student/1
Content-Type: application/json
```

```json
{
  "name": "John Doe",
  "age": 22,
  "email": "john@example.com",
  "department_id": 1
}
```

### Create a Course

**Request**

```http
POST /api/courses
Content-Type: application/json
```

```json
{
  "name": "Database Management Systems",
  "credits": 4
}
```

The example assumes the course request DTO contains `name` and `credits`.

### Retrieve Students Enrolled in a Course

```http
GET /api/courses/1/students
```

This endpoint returns the students associated with the specified course.

### Delete a Department

```http
DELETE /api/department/1
```

Deleting a department also deletes its associated students, according to the configured cascading relationship.

## Database Configuration

The application uses PostgreSQL to store student, department, and course data.

### 1. Create the Database

Create a database in PostgreSQL:

```sql
CREATE DATABASE college_db;
```

### 2. Configure the Connection

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/college_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Set `DB_USERNAME` and `DB_PASSWORD` to your PostgreSQL credentials.

Ensure the PostgreSQL JDBC driver is included in the project's Maven dependencies. The `spring.jpa.hibernate.ddl-auto` setting shown above allows Hibernate to update the database schema based on the mapped entities. The exact schema-management setting may differ in your local configuration.

## Installation and Setup

### Prerequisites

Before running the application, install:

- Java Development Kit (JDK) compatible with the project's Spring Boot version
- PostgreSQL
- Maven, or use the Maven Wrapper included in the repository
- Postman or another API client for testing

### Step 1: Clone the Repository

```bash
git clone <your-repository-url>
cd College-Management-REST-API
```

Replace `<your-repository-url>` with the URL of your GitHub repository.

### Step 2: Set Up PostgreSQL

Create the `college_db` database and configure the database credentials as described in the database configuration section.

### Step 3: Run the Application

**Windows:**

```bash
mvnw.cmd spring-boot:run
```

**Linux/macOS:**

```bash
./mvnw spring-boot:run
```

Alternatively, open the project in an IDE and run the main class annotated with `@SpringBootApplication`.

### Step 4: Test the Endpoints

Once the application has started, use Postman to send requests to the endpoints listed in the API documentation.

For example:

```http
GET http://localhost:8080/api/student
```

Use the configured application port if it differs from `8080`.

## Author

**Vijval**

