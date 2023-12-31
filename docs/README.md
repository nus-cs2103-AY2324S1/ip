~~# DaDaYuan Chatbot User Guide
![Ui](./Ui.png)

## Table of Contents

- [Introduction](#introduction)
- [Quick Start](#quick-start)
    - [Run the project in terminal](#run-the-project-in-terminal)
    - [Run the project in IntelliJ](#run-the-project-in-intellij)
- [Command Summary](#command-summary)
- [Features](#features)
    * [Listing All Commands](#listing-all-commands)
    * [Adding a Task](#adding-a-task)
    * [Deleting a Task](#deleting-a-task)
    * [Viewing Tasks](#viewing-tasks)
    * [Marking Tasks as Done](#marking-tasks-as-done)
    * [Finding Tasks](#finding-tasks)
    * [Getting Help](#getting-help)
- [Acceptable Formats for `<DateTime>`](#acceptable-formats-for-datetime)
- [Acceptable Formats for `<Date>`](#acceptable-formats-for-date)
- [Useful Resources](#useful-resources)

## Introduction

DaDaYuan is your intelligent chatbot assistant designed to help you manage tasks effectively. Built on the Duke project framework, DaDaYuan provides a user-friendly interface and a robust set of features to keep you organized.

## Quick Start

### Run the project in terminal

1. Download the latest **DaDaYuan.jar** from [GitHub](https://github.com/mamayuan/ip/releases/tag/v1.0-DaDaYuan)
2. Place the jar file in an empty folder.
3. Open terminal and navigate to the folder.
4. Run `java -jar DaDaYuan.jar`

### Run the project in IntelliJ

1. Open IntelliJ IDEA.
2. Click on "Open" and select the DaDaYuan project folder.
3. Navigate to `src/main/java/duke/ui/Launcher.java`, right-click and run.

## Command Summary

| Command      | Syntax             | Example             |
|--------------|--------------------|---------------------|
| List Tasks   | `list`             | `list`              |
| Add Task     | `add <task>`       | `add buy groceries` |
| Delete Task  | `delete <task_id>` | `delete 1`          |
| Mark as Done | `mark <task_id>`   | `mark 1`            |
| UnMark       | `unmark <task_id>` | `unmark 1`          |
| Find Tasks   | `find <keyword>`   | `find groceries`    |
| Help         | `help [DATETIME]`  | `help DATETIME`     |

## Features

### Listing All Commands
The `list` command displays all the tasks you have.
- Example: `list`

### Adding a Task
To add a task, use the `add` command followed by the task description.
- Example: `add buy groceries`

### Deleting a Task
The `delete` command removes a task from the list.
- Example: `delete 1`

### Viewing Tasks
The `list` command allows you to view all tasks in the list.
- Example: `list`

### Marking Tasks as Done
The `mark` command marks a task as completed.
- Example: `mark 1`

### UnMarking Tasks
The `unmark` command marks a task as to be completed.
- Example: `unmark 1`

### Finding Tasks
The `find` command searches for tasks containing a specific keyword.
- Example: `find groceries`

### Getting Help
The `help` command shows a list of commands or displays valid date and time formats.
- Example: `help DATETIME`

## Acceptable Formats for `<DateTime>`

List of acceptable date-time formats:
- `MMM dd yyyy HHmm`
- `yyyy-MM-dd'T'HH:mm`
- `dd/MM/yyyy HHmm`
- ...

## Acceptable Formats for `<Date>`

List of acceptable date formats:
- `MMM dd yyyy`
- `dd/MM/yyyy`
- `yyyy-MM-dd`
- ...

## Useful Resources

1. [JavaFX Tutorial](https://se-education.org/guides/tutorials/javaFx.html)
2. [Checkstyle Tutorial](https://se-education.org/guides/tutorials/checkstyle.html)
3. [Working with Jar files Tutorial](https://se-education.org/guides/tutorials/jar.html)

---
