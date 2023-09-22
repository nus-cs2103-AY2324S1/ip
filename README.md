# Axela User Guide

Welcome to Axela, your personal AI assistant! This guide will help you get started and make the most of Axela's
features.

## Table of Contents
- [Features](#features)
- [Usage](#usage)
    - [Adding a Task](#adding-a-task)
    - [Exiting the Application](#exiting-the-application)
    - [Deleting a Task](#deleting-a-task)
    - [Searching for Tasks](#searching-for-tasks)
    - [Finding the Features](#finding-the-features)
    - [Greeting Alexa](#greeting-alexa)
    - [Listing Tasks](#listing-tasks)
    - [Marking a Task as Done](#marking-a-task-as-done)
    - [Marking a Task as Not Done](#marking-a-task-as-not-done)

## Features

### Task Management

Axela can help you manage your tasks efficiently. You can add, delete, mark as done, mark as not done, and list
tasks. There are three types of tasks:

1. **Todo** - Simple tasks with specific dates indicating from when to when do we carry out the task.
2. **Deadline** - Tasks with a deadline date.
3. **Event** - Tasks that happen at a specific time.

### Priority Tracking

You can set priorities for your tasks, allowing you to focus on what's most important. Axela supports three priority
levels: High, Medium, and Low.

### Task Search

Easily find tasks by searching for keywords or dates. Axela will display tasks matching your search criteria.

## Usage

### Adding a Task

To add a task, use one of the following commands:

- `todo [task description] /from [YYYY-MM-DD] /from [YYYY-MM-DD]`
- `todo [task description] /priority low /from [YYYY-MM-DD] /from [YYYY-MM-DD]`
- `deadline [task description] /by [YYYY-MM-DD]`
- `deadline [task description] /priority medium /by [YYYY-MM-DD]`
- `event [task description] /at [YYYY-MM-DD]`
- `event [task description] /priority high /at [YYYY-MM-DD]`

Example:
```
todo Buy groceries /priroity high /from 2023-09-22 /to 2023-12-10
deadline Coding Assignment /priroity medium /by 2019-12-12
event printing workshop /priroity low /at 2024-01-31
```

### Exiting the Application

To exit the application, use:

- `bye`

Example:
```
bye
```

### Deleting a Task

You can remove a task by specifying its index:

- `delete [task index]`

Example:
```
delete 2
```

### Searching for Tasks

Find tasks by keywords or dates with:

- `find [keyword]`

Example:
```
find important
```

### Finding the Features

Find what are the different commands possible:

- `help`

Example:
```
help
```

### Greeting Alexa

To greet Alexa, use:

- `hi`

Example:
```
hi
```

### Listing Tasks

To see all your tasks, use:

- `list`

Example:
```
list
```

### Marking a Task as Done

Mark a task as done using:

- `mark [task index]`

Example:
```
mark 1
```

### Marking a Task as Not Done

Mark a task as not done using:

- `unmark [task index]`

Example:
```
unmark 1
```

This user guide provides a brief overview of Axela's key features and how to use them effectively. Feel free to explore
and make the most of your AI assistant!