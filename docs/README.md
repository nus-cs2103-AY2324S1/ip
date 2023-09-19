# Duke's User Guide

## Introduction

Duke is a desktop app for managing tasks specifically designed for **anyone who can type fast**. With Duke, you can now manage your tasks effectively in one unified place and get things done faster than traditional Graphical User Interface (GUI) apps.

This app is optimized via a Command Line Interface (CLI) while still having the benefits of a GUI.

## Features 

### Viewing help: `help`
Shows the list of commands available, the format of each command and what each command does.

Format: `help`

### Adding a todo: `todo`

Adds a new todo task to your list of tasks.

Format: `todo {DESCRITPION}`

Example: `todo finish research` - adds a new todo with the given description “finish research”.

### Adding a deadline: `deadline`

Adds a new deadline task to your list of tasks.

Format: `deadline {DESCRITPION} /by {DATE_TIME}`

Example:
- `deadline assignment 1 /by 22/9/2023 2359` - adds a new deadline "assignment 1" with the given description and date-time.

### Adding an event: `event`

Adds a new event task to your list of tasks.

Format: `event {DESCRITPION} /at {DATE_TIME}`

Example:
- `event career fair /at 23/9/2023 1800` - adds a new event "career fair" with the given description and date-time.

### Listing all tasks: `list`

Shows a list of all tasks in your task list.

Format: `list` - displays a list of all tasks.

### Marking a task as done: `mark`

Marks the specified task as done.

Format: `mark {TASK_INDEX}`

Example:
- `mark 2` - marks the task at index 2 as done.

### Marking a task as not done: `unmark`

Marks the specified task as not done.

Format: `unmark {TASK_INDEX}`

Example:
- `unmark 2` - marks the task at index 2 as not done.

### Deleting a task: `delete`

Deletes the specifed task.

Format: `delete {TASK_INDEX}`

Example:
- `delete 2` - deletes the task at index 2 from the task list.

### Finding tasks by name: `find`

Finds tasks whose description contain any of the keyword.

Format: `find {KEYWORD}`

Example:
- `find book` - returns all tasks with name that matches “book”. E.g. borrow book, return book

### Updating a task: `update`

Updates the specified task.

Format: `update {TASK_INDEX}, [NEW_DESCRIPTION], [NEW_DATE_TIME]`

Example:
- `update 2, Watch CS2103T lecture` - updates the description of the task at index 2 to “Watch CS2103T lecture”.
- `update 2, 21/9/2023 2100` - updates the date-time of the task at index 2 to "21 Sep 2023 9:00pm".
- `update 2, Watch CS2103T lecture, 21/9/2023 2100` - updates the description and date-time of the task at index 2 to "Watch CS2103T lecture" and "21 Sep 2023 9:00pm" respectively.

### Checking tasks for a specified date: `check`

Shows the list of tasks on the specified date.

Format: `check {DATE}`

Example:
- `check 21/9/2023` - displays a list of all tasks that are on 21 Sep 2023.

### Checking tasks for today: `today`

Shows the list of tasks on your system's local date.

Format: `today`

### Exiting the program: `bye`

Exits the program.

Format: `bye`
