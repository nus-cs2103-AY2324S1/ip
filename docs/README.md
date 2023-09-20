# BeeBot User Guide

BeeBot is a basic CLI chatbot task manager that helps you keep track of your tasks.

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.   
2. Download the latest `beebot.jar` from [here](
3. Copy the file to the folder you want to use as the home folder for your BeeBot.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
![Ui](Ui.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.
6. Refer to the [Features](#features) below for details of each command.
7. Some example commands you can try:
    * `list` : Lists all tasks.
    * `todo read book` : Adds a todo task named `read book` to the task list.
    * `delete 3` : Deletes the 3rd task shown in the current list.
    * `bye` : Exits the app.
* Refer to the [Command Summary](#command-summary) below for a summary of the commands.
* Refer to the [FAQ](#faq) below for a list of frequently asked questions.

## Features

Beebot supports 3 types of tasks:
* Todo
* Deadline
* Event

And supports the following operations:
* Listing all tasks: `list`
* Marking a task as done: `done`
* Deleting a task: `delete`
* Finding tasks by name: `find`
* Exiting the program: `bye`

## Usage

### Adding a todo task: `todo`

Adds a todo task to the task list.

Format: `todo TASK_NAME`

Shortened Format: `t TASK_NAME`

Example of usage:

`todo read book`


### Adding a deadline task: `deadline`

Adds a deadline task to the task list.

Format: `deadline TASK_NAME /by DATE (YYYY-MM-DD HH:MM)`

Shortened Format: `d TASK_NAME /by DATE (YYYY-MM-DD HH:MM)`

Example of usage:

`deadline return book /by 2021-09-17`

### Adding an event task: `event`

Adds an event task to the task list.

Format: `event TASK_NAME /from DATE (YYYY-MM-DD HH:MM) /to DATE (YYYY-MM-DD HH:MM)`

Shortened Format: `e TASK_NAME /from DATE (YYYY-MM-DD HH:MM) /to DATE (YYYY-MM-DD HH:MM)`

Example of usage:

`event project meeting /at 2021-09-17`

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

### Marking a task as done: `done`

Marks a task as done in the task list.

Format: `done INDEX`

* Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...
* The index **must be within the range of the task list**.

Example of usage:

`done 2`

### Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...
* The index **must be within the range of the task list**.

Example of usage:

`delete 2`

### Finding tasks by name: `find`

Finds tasks whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `read book` will match `Read Book`
* The order of the keywords does not matter. e.g. `book read` will match `read book`

Example of usage:

`find book`

### Exiting the program: `bye`

Exits the program.

Format: `bye`

## Saving the data
BeeBot data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Command Summary

Action | Format, Examples
--------|------------------
**Add Todo** | `todo TASK_NAME` e.g., `todo read book`
**Add Deadline** | `deadline TASK_NAME /by DATE` e.g., `deadline return book /by 2021-09-17`
**Add Event** | `event TASK_NAME /at DATE` e.g., `event project meeting /at 2021-09-17`
**List** | `list`
**Done** | `done INDEX` e.g., `done 2`
**Delete** | `delete INDEX` e.g., `delete 2`
**Find** | `find KEYWORD [MORE_KEYWORDS]` e.g., `find book`
**Bye** | `bye`

