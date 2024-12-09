[LMS.postman_collection.json](https://github.com/user-attachments/files/18058664/LMS.postman_collection.json)# LMS (Learning Management System)

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
[Uploading{
	"info": {
		"_postman_id": "59dadb04-9a90-42c5-9ccb-456a73424acb",
		"name": "LMS",
		"description": "# 🚀 Get started here\n\nThis template guides you through CRUD operations (GET, POST, PUT, DELETE), variables, and tests.\n\n## 🔖 **How to use this template**\n\n#### **Step 1: Send requests**\n\nRESTful APIs allow you to perform CRUD operations using the POST, GET, PUT, and DELETE HTTP methods.\n\nThis collection contains each of these [request](https://learning.postman.com/docs/sending-requests/requests/) types. Open each request and click \"Send\" to see what happens.",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "11053376"
	},
	"item": [
		{
			"name": "Student",
			"item": [
				{
					"name": "get all students",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{base_url}}/students/",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"students",
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "delete student by id",
					"request": {
						"method": "DELETE",
						"header": [],
						"url": {
							"raw": "{{base_url}}/students/1",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"students",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "register student",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"studentName\":\"Sahil2 Gupta\",\r\n    \"subjectsId\":[1]\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{base_url}}/students/",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"students",
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "Update student details",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"studentName\":\"Sahil Gupta\",\r\n    \"subjectsId\":[1, 2]\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{base_url}}/students/",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"students",
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "get student by id",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{base_url}}/students/2",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"students",
								"2"
							],
							"query": [
								{
									"key": "studentId",
									"value": "1",
									"disabled": true
								}
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Subject",
			"item": [
				{
					"name": "get all subject",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{base_url}}/subjects/",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"subjects",
								""
							]
						}
					},
					"response": []
				},
				{
					"name": "get subject by id",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{base_url}}/subjects/1",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"subjects",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "get subject by id Copy",
					"request": {
						"method": "DELETE",
						"header": [],
						"url": {
							"raw": "{{base_url}}/subjects/1",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"subjects",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "create new subject",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n    \"subjectName\":\"Math\"\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{base_url}}/subjects/",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"subjects",
								""
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Easter egg",
			"item": [
				{
					"name": "get random fact",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{base_url}}/hidden-feature/2",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"hidden-feature",
								"2"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "Exam",
			"item": [
				{
					"name": "get all exams",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"pm.test(\"Status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});"
								],
								"type": "text/javascript",
								"packages": {}
							}
						}
					],
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{base_url}}/exam/",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"exam",
								""
							]
						},
						"description": "This is a GET request and it is used to \"get\" data from an endpoint. There is no request body for a GET request, but you can use query parameters to help specify the resource you want data on (e.g., in this request, we have `id=1`).\n\nA successful GET response will have a `200 OK` status, and should include some kind of response body - for example, HTML web content or JSON data."
					},
					"response": []
				},
				{
					"name": "create exam",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"pm.test(\"Successful POST request\", function () {",
									"    pm.expect(pm.response.code).to.be.oneOf([200, 201]);",
									"});",
									""
								],
								"type": "text/javascript",
								"packages": {}
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n\t\"subjectId\": 1\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{base_url}}/exam/",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"exam",
								""
							]
						},
						"description": "This is a POST request, submitting data to an API via the request body. This request submits JSON data, and the data is reflected in the response.\n\nA successful POST request typically returns a `200 OK` or `201 Created` response code."
					},
					"response": []
				},
				{
					"name": "register for exam",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"pm.test(\"Successful POST request\", function () {",
									"    pm.expect(pm.response.code).to.be.oneOf([200, 201]);",
									"});",
									""
								],
								"type": "text/javascript",
								"packages": {}
							}
						}
					],
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n\t\"subjectId\": 1\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{base_url}}/exam/register?studentId=2&examId=2",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"exam",
								"register"
							],
							"query": [
								{
									"key": "studentId",
									"value": "2"
								},
								{
									"key": "examId",
									"value": "2"
								}
							]
						},
						"description": "This is a POST request, submitting data to an API via the request body. This request submits JSON data, and the data is reflected in the response.\n\nA successful POST request typically returns a `200 OK` or `201 Created` response code."
					},
					"response": []
				},
				{
					"name": "Update data",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"pm.test(\"Successful PUT request\", function () {",
									"    pm.expect(pm.response.code).to.be.oneOf([200, 201, 204]);",
									"});",
									""
								],
								"type": "text/javascript",
								"packages": {}
							}
						}
					],
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\n\t\"examId\": 1,\n    \"subjectId\":2\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{base_url}}/exam/",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"exam",
								""
							],
							"query": [
								{
									"key": "id",
									"value": "1",
									"disabled": true
								}
							]
						},
						"description": "This is a PUT request and it is used to overwrite an existing piece of data. For instance, after you create an entity with a POST request, you may want to modify that later. You can do that using a PUT request. You typically identify the entity being updated by including an identifier in the URL (eg. `id=1`).\n\nA successful PUT request typically returns a `200 OK`, `201 Created`, or `204 No Content` response code."
					},
					"response": []
				},
				{
					"name": "Delete data",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"pm.test(\"Successful DELETE request\", function () {",
									"    pm.expect(pm.response.code).to.be.oneOf([200, 202, 204]);",
									"});",
									""
								],
								"type": "text/javascript",
								"packages": {}
							}
						}
					],
					"request": {
						"method": "DELETE",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "{{base_url}}/exam/1",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"exam",
								"1"
							],
							"query": [
								{
									"key": "examId",
									"value": "1",
									"disabled": true
								}
							]
						},
						"description": "This is a DELETE request, and it is used to delete data that was previously created via a POST request. You typically identify the entity being updated by including an identifier in the URL (eg. `id=1`).\n\nA successful DELETE request typically returns a `200 OK`, `202 Accepted`, or `204 No Content` response code."
					},
					"response": []
				},
				{
					"name": "get exam by id",
					"event": [
						{
							"listen": "test",
							"script": {
								"exec": [
									"pm.test(\"Status code is 200\", function () {",
									"    pm.response.to.have.status(200);",
									"});"
								],
								"type": "text/javascript",
								"packages": {}
							}
						}
					],
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "{{base_url}}/exam/1",
							"host": [
								"{{base_url}}"
							],
							"path": [
								"exam",
								"1"
							]
						},
						"description": "This is a GET request and it is used to \"get\" data from an endpoint. There is no request body for a GET request, but you can use query parameters to help specify the resource you want data on (e.g., in this request, we have `id=1`).\n\nA successful GET response will have a `200 OK` status, and should include some kind of response body - for example, HTML web content or JSON data."
					},
					"response": []
				}
			]
		}
	],
	"event": [
		{
			"listen": "prerequest",
			"script": {
				"type": "text/javascript",
				"exec": [
					""
				]
			}
		},
		{
			"listen": "test",
			"script": {
				"type": "text/javascript",
				"exec": [
					""
				]
			}
		}
	],
	"variable": [
		{
			"key": "base_url",
			"value": "http://localhost:8080/api/v1"
		}
	]
} LMS.postman_collection.json…]()




