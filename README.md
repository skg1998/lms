# LMS (Learning Management System)

> Develop a RESTful API service using Spring Boot to manage the exam enrollment process for a Learning Management System (LMS). You are required to use MySQL to persist the data.

## Endpoints

### Student
- GET /student - Retrieve a list of all registered student
- GET /student/{studentId} - Retrieve the details of a specific student
- POST /student - Register a new student
- PUT /student/{studentId} - Update detail of a specific student
- DELETE /student/{studentId} - Delete a specific student

### Exam
- GET /exam - Retrieve a list of all created exam
- GET /exam/{studentId} - Retrieve the details of a specific exam
- POST /exam - Create a new exam
- PUT /exam/{examId} - Update detail of a specific exam
- DELETE /exam/{examId} - Delete a specific exam
- POST /exam/ - student Register for exam

### Exam
- GET /subject/ - Retrieve a list of all created subject
- GET /subject/{subjectId} - Retrieve the details of a specific subject
- POST /subject - Create a new subject
- PUT /subject/{subjectId} - Update detail of a specific subject
- DELETE /subject/{subjectId} - Delete a specific subject


## Postman Collection
[LMS.postman_collection.json](https://github.com/user-attachments/files/18058664/LMS.postman_collection.json)
