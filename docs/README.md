# Duke Chat Bot User Guide

## Introduction

Duke is a chat bot designed to assist you with your daily tasks and also trivia. This guide will provide you with a comprehensive overview on how to utilize the Duke chat bot effectively.

---

## Table of Contents
1. [Setup](#setup)
2. [Features](#features)
    * [Adding a Todo Task](#adding-a-todo-task)
    * [Adding a Deadline Task](#adding-a-deadline-task)
    * [Adding an Event Task](#adding-an-event-task)
    * [Deleting a Task](#deleting-a-task)
    * [Listing All Tasks](#listing-all-tasks)
    * [Marking a Task as Done](#marking-a-task-as-done)
    * [Unmarking a Task](#unmarking-a-task)
    * [Searching for Tasks](#searching-for-tasks)
    * [Trivia Features](#trivia-features)
3. [Command Summary](#duke-command-summary)
4. [Error Handling](#error-handling)

---

## Setup

### Prerequisites

- **Java Development Kit (JDK)**: Ensure you have the JDK installed on your system.

### Setup Instructions

1. **Clone or Download the Repository:**
First, you'll need to get the project onto your local machine. If you're using Git, you can clone the repository:
`git clone https://github.com/junnengsoo/ip.git`

2. **Navigate to the Project Directory:**
`cd [PATH_TO_IP]`

3. **Ensure File Permissions (For Unix-like systems):**
Before running the Gradle Wrapper script on Unix-like systems (like Linux and macOS), you might need to ensure that the gradlew script has execute permissions.
`chmod +x gradlew`

4. **Run the Application:**
For Linux/macOS:
`./gradlew run`
For Windows users, the command will be slightly different:
`gradlew.bat run`

When Duke is started, it will automatically load tasks and trivia from these files with a GUI interface.

---

## Features

### Adding a Todo Task

Command: `todo [description]`

Example:
`todo read book`


### Adding a Deadline Task

Command: `deadline [description] /by [date in format yyyy-MM-dd]`

Example:
`deadline return book /by 2020-02-20`


### Adding an Event Task

Command: `event [description] /from [start date in format yyyy-MM-dd] /to [end date in format yyyy-MM-dd]`

Example:
`event project meeting /from 2020-02-20 /to 2020-02-21`


### Deleting a Task

Command: `delete [task index]`

Example:
`delete 1`


### Listing All Tasks

Command: `list`

### Marking a Task as Done

Command: `done [task index]`

Example:
`done 1`


### Unmarking a Task

Command: `undone [task index]`

Example:
`undone 1`


### Searching for Tasks

Command: `find [keyword]`

Example:
`find book`


### Trivia Features

* **Adding a Trivia:** `addtrivia [question] /answer [answer]`
* **Listing all Trivia:** `listtrivia`
* **Testing a Trivia:** `testtrivia [trivia index] [your answer]`

Examples:
`addtrivia What is the capital of Singapore? /answer Singapore`
`testtrivia 1 Singapore`


---

## Duke Command Summary

### Tasks

| Feature                    | Command                                               | Example                                                 |
|----------------------------|-------------------------------------------------------|---------------------------------------------------------|
| **Adding a Todo Task**     | `todo [description]`                                  | `todo read book`                                        |
| **Adding a Deadline Task** | `deadline [description] /by [date in yyyy-MM-dd]`      | `deadline return book /by 2020-02-20`                   |
| **Adding an Event Task**   | `event [description] /from [start date in yyyy-MM-dd] /to [end date in yyyy-MM-dd]` | `event project meeting /from 2020-02-20 /to 2020-02-21` |
| **Deleting a Task**        | `delete [task index]`                                 | `delete 1`                                              |
| **Listing All Tasks**      | `list`                                                | `list`                                                  |
| **Marking a Task as Done** | `done [task index]`                                   | `done 1`                                                |
| **Unmarking a Task**       | `undone [task index]`                                 | `undone 1`                                              |
| **Searching for Tasks**    | `find [keyword]`                                      | `find book`                                             |

### Trivia

| Feature                 | Command                                               | Example                                                         |
|-------------------------|-------------------------------------------------------|-----------------------------------------------------------------|
| **Adding a Trivia**     | `addtrivia [question] /answer [answer]`               | `addtrivia What is the capital of Singapore? /answer Singapore` |
| **Listing all Trivia**  | `listtrivia`                                          | `listtrivia`                                                    |
| **Testing a Trivia**    | `testtrivia [trivia index] [your answer]`             | `testtrivia 1 Singapore`                                        |

---

## Error Handling

Duke has built-in error checks and will inform you if:
* The date format is invalid
* The provided task index is out of bounds
* The task or trivia format is incorrect

---

## Conclusion

With Duke, managing tasks and trivia is a breeze! Do note to follow the command formats and ensure your input is correct to have a smooth experience. Enjoy your journey with Duke!

