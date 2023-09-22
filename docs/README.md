# MAX Chatbot User Guide

Welcome to the MAX Chatbot User Guide! Duke is a personal task manager to help you keep track of your tasks with a simple command-line interface.

## Table of Contents

- [Introduction](#introduction)
- [Getting Started](#getting-started)
- [Features](#features)
- [Commands](#commands)
- [Exiting the Program](#exiting-the-program)
- [Troubleshooting](#troubleshooting)

## Introduction

Duke is designed to make task management easy and efficient. With a range of commands at your disposal, you can add, delete, and manage tasks seamlessly.

## Getting Started

1. Ensure you have Java installed on your computer.
2. Download the Duke application.
3. Navigate to the directory containing the Duke application and run it.

Upon starting, Duke will greet you with a welcome message.

## Features

Duke offers the following types of tasks:

1. **Todo**: A simple task with just a description.
2. **Deadline**: A task with a description and a deadline.
3. **Event**: A task with a description, start time, and end time.
4. **Period**: A task with a description, start time, and end time, representing a period.

## Commands

### Adding a Todo task

**Format**: `todo [description]`

**Example**: `todo Read a book`

### Adding a Deadline task

**Format**: `deadline [description] /by [deadline]`

**Example**: `deadline Submit assignment /by 2023-09-30 1800`

### Adding an Event task

**Format**: `event [description] /from [start time] /to [end time]`

**Example**: `event Team meeting /from 2023-09-25 0900 /to 2023-09-25 1100`

### Adding a Period task

**Format**: `period [description] /from [start time] /to [end time]`

**Example**: `period Vacation /from 2023-12-01 0000 /to 2023-12-15 2359`

### Listing all tasks

**Format**: `list`

### Marking a task as done

**Format**: `mark [index]`

**Example**: `mark 2`

### Marking a task as not done

**Format**: `unmark [index]`

**Example**: `unmark 2`

### Deleting a task

**Format**: `delete [index]`

**Example**: `delete 3`

### Searching for tasks

**Format**: `find [keyword]`

**Example**: `find book`
 
### Exiting the Program

**Format**: `bye`

## Troubleshooting

1. **Error loading tasks from file**: Ensure the `duke.txt` file is in the `./data/` directory. If not, Duke will attempt to create it.
2. **Date-Time Format Errors**: Ensure dates and times are in the correct format, e.g., `2023-09-30 1800`.

:bulb: **Tip**: Always ensure your commands are correctly formatted to avoid errors.

---

Thank you for using Duke! For feedback or issues, please contact our support team.
