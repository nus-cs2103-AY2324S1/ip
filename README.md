# User Guide for Bert

## Introduction
Bert is a desktop chatbot for **keeping track of tasks**, optimized for use via a Command Line Interface (CLI).

## Quick start
1. Ensure you have **Java 11 or above** installed in your computer.
2. Download the latest `Bert.jar` from [here](https://github.com/peasantbird/ip/releases).
3. Open a command terminal, `cd` into the folder the jar file is in, 
and use `java -jar Bert.jar` command to run the application.
4. A GUI similar to the one below will appear, indicating that the application is running!

![Screenshot of GUI](https://github.com/peasantbird/ip/blob/master/docs/Ui.png)

## Features

### Add tasks

There are 3 types of tasks that can be added:
1. A ***todo*** task does not have any date attached to it.
2. A ***deadline*** task has a specific date that it needs to be done by.
3. An ***event*** task starts at a specific date and ends at a specific date.

Add these tasks respectively using `todo`, `deadline` and `event`.

### View tasks
- List all tasks using `list`. 
- Search for tasks matching a keyword using `find`.

### Manage tasks
- Mark tasks as done/undone using `mark`/`unmark`.
- Delete tasks using `delete`.
- Sort tasks alphabetically using `sort`.

### Save tasks and exit
- Save tasks and exit the program using `bye`.

## Usage

### Add a *todo* task: `todo`

Adds a todo task with a description.

Format: `todo <description>`

Example: `todo borrow book`

### Add a *deadline* task: `deadline`

Adds a deadline task with a description and deadline date.

Format: `deadline <description> /by <YYYY-MM-DD>`

Example: `deadline return book /by 2023-09-24`

### Add an *event* task: `event`

Adds an event task with a description, starting date and ending date.

Format: `event <description> /from <YYYY-MM-DD> /to <YYYY-MM-DD>`

Example: `event holiday /from 2023-09-24 /to 2023-09-26`

### List all tasks: `list`

Displays a list of all the tasks.

Format: `list`

### Find tasks by keyword: `find`

Displays a list of tasks matching a given keyword.

Format: `find <keyword>`

Example: `find book`

### Mark a task as done: `mark`

Marks the specified task as done.

Format: `mark <index>`
- Marks the task at the specified `<index>`.
- The `<index>` refers to the index number shown in the displayed task list.
- The `<index>` **must be a positive integer** 1, 2, 3, ...

Example: `mark 1`

### Mark a task as undone: `unmark`

Marks the specified task as undone.

Format: `unmark <index>`
- Unmarks the task at the specified `<index>`.
- The `<index>` refers to the index number shown in the displayed task list.
- The `<index>` **must be a positive integer** 1, 2, 3, ...

Example: `unmark 1`

### Delete a task: `delete`

Deletes the specified task.

Format: `delete <index>`
- Deletes the task at the specified `<index>`.
- The `<index>` refers to the index number shown in the displayed task list.
- The `<index>` **must be a positive integer** 1, 2, 3, ...

Example: `delete 1`

### Sort tasks alphabetically: `sort`

Sorts all the tasks in alphabetical order.

Format: `sort`

### Exit the program: `bye`

Saves all the tasks and exits the program.

Format: `bye`