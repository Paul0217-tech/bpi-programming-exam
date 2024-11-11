# Task Scheduler API

## Overview
This API is designed to schedule tasks in a project plan, taking into account task durations and dependencies. Each task has a `startDate`, `endDate`, and `duration`. If a task depends on other tasks, its `startDate` will be automatically calculated based on the `endDate` of its dependencies.

The API allows users to create, and retrieve tasks, while also ensuring that the schedule is consistent and the tasks are ordered correctly.

## Features
- Create tasks with a name, duration, and optional dependencies on other tasks. (Note: first entry, should have an empty dependency since this will be the base date. If `startDate` is not indicated for the first entry, it will automatically get the current date of the day)
- Automatically calculate the `startDate` and `endDate` based on task durations and dependencies.
- Update tasks and recalculate their start and end dates accordingly.
- Delete tasks.
- Retrieve a list of all tasks or a specific task by its ID.

### Sample of Creating a Schedule
#### Request:
curl --location 'http://localhost:8080/schedule' \
--header 'Content-Type: application/json' \
--data '[
{
"name": "Task A",
"duration": 5,
"startDate": "2024-11-13",
"dependencies": []
},
{
"name": "Task B",
"duration": 3,
"dependencies": [{"id": 1}]  
},
{
"name": "Task C",
"duration": 4,
"startDate": "2024-11-13",
"dependencies": [{"id": 1}]  
},
{
"name": "Task D",
"duration": 2,
"dependencies": [{"id": 3}, {"id": 1}]  
},
{
"name": "Task E",
"duration": 2,
"dependencies": [{"id": 3}, {"id": 2}]  
}
]
'

#### Response: 
Tasks have been scheduled successfully!

### Sample of Viewing the Tasks/Schedule
#### Request:
curl --location 'http://localhost:8080/tasks'

#### Response:
[
{
"id": 1,
"name": "Task A",
"duration": 5,
"startDate": "2024-11-13",
"endDate": "2024-11-18",
"dependencies": []
},
{
"id": 2,
"name": "Task B",
"duration": 3,
"startDate": "2024-11-18",
"endDate": "2024-11-21",
"dependencies": [
{
"id": 1,
"name": "Task A",
"duration": 5,
"startDate": "2024-11-13",
"endDate": "2024-11-18",
"dependencies": []
}
]
},
{
"id": 3,
"name": "Task C",
"duration": 4,
"startDate": "2024-11-18",
"endDate": "2024-11-22",
"dependencies": [
{
"id": 1,
"name": "Task A",
"duration": 5,
"startDate": "2024-11-13",
"endDate": "2024-11-18",
"dependencies": []
}
]
},
{
"id": 4,
"name": "Task D",
"duration": 2,
"startDate": "2024-11-22",
"endDate": "2024-11-24",
"dependencies": [
{
"id": 3,
"name": "Task C",
"duration": 4,
"startDate": "2024-11-18",
"endDate": "2024-11-22",
"dependencies": [
{
"id": 1,
"name": "Task A",
"duration": 5,
"startDate": "2024-11-13",
"endDate": "2024-11-18",
"dependencies": []
}
]
},
{
"id": 1,
"name": "Task A",
"duration": 5,
"startDate": "2024-11-13",
"endDate": "2024-11-18",
"dependencies": []
}
]
},
{
"id": 5,
"name": "Task E",
"duration": 2,
"startDate": "2024-11-22",
"endDate": "2024-11-24",
"dependencies": [
{
"id": 3,
"name": "Task C",
"duration": 4,
"startDate": "2024-11-18",
"endDate": "2024-11-22",
"dependencies": [
{
"id": 1,
"name": "Task A",
"duration": 5,
"startDate": "2024-11-13",
"endDate": "2024-11-18",
"dependencies": []
}
]
},
{
"id": 2,
"name": "Task B",
"duration": 3,
"startDate": "2024-11-18",
"endDate": "2024-11-21",
"dependencies": [
{
"id": 1,
"name": "Task A",
"duration": 5,
"startDate": "2024-11-13",
"endDate": "2024-11-18",
"dependencies": []
}
]
}
]
}
]


## Technologies Used
- **Spring Boot** - Framework for building the API.
- **JPA** - For database access (via `JpaRepository`).
- **H2 Database** - In-memory database for storing tasks (configurable in `application.properties`).
- **Lombok** - To reduce boilerplate code for getters, setters, and constructors.

## Prerequisites
- **Java 17** or later.
- **Maven** to manage dependencies and build the project.

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Paul0217-tech/bpi-programming-exam.git
