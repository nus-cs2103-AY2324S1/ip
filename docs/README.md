# ANNOY-O-TRON User Guide

### Table of Contents
- [Getting Started](#getting-started)
- [Features](#features)

---
## Getting Started
1. Ensure you have Java `11` installed in your Computer.
2. Download the latest `annoyotron.jar` from [here](https://github.com/Darren159/ip/releases)
3. Copy the file to the folder you want to use as the home folder for your Annoy-O-Tron.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar annoyotron.jar` command to run the application.
   A GUI should appear in a few seconds.
5. Type the command in the command box and press Enter to execute it. e.g. typing `list` and pressing Enter will display all tasks in the task list.
   Some example commands you can try:
   - `todo Buy groceries`: Adds a todo task with the description "Buy groceries" to your list.

   - `delete 3`: Deletes the 3rd task in the task list.

   - `bye` : Exits the app.

Refer to the [Features](#features) below for details of each command.

---
## Features

### Task List

You can manage your tasks efficiently using the following commands:

#### Add Tasks
- **Todo**: Add a todo task to your list (tasks without any date/time attached to it)
- **Event**: Add an event task to your list (tasks that need to be done before a specific date/time)
- **Deadline**: Add a deadline task to your list (tasks that start at a specific date/time and ends at a specific date/time)

#### View Tasks
- **List**: View all the tasks in your list

#### Delete Tasks
- **Delete**: Remove tasks from your list

#### Find Tasks
- **Find**: Search for existing tasks in your task list using keywords

#### Mark Tasks as Done
- **Done**: Mark tasks as completed
- **Undone**: Mark tasks as not completed yet

#### Sort Deadlines
- **Sort**: Organize your deadlines in chronological order

### Save Tasks
Your task data is automatically saved to the `data/tasks.txt` file and loaded when you restart the app.

---
## Usage

### Notes about the command format:
- Words in `UPPER_CASE` are the parameters to be supplied by the user. e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Buy groceries`.
- All commands are case-insensitive. e.g. `todo` and `TODO` are both valid commands.
- Date and time must be in the format `YYYY-MM-DD HH:MM`. e.g. `2023-09-01 18:00` is a valid date/time.

---



### `todo` - Add a Todo Task

Adds a todo task to your list.

Format: `todo DESCRIPTION`

Example of usage:

`todo Buy groceries` Adds a todo task with the description "Buy groceries" to your list.

---

### `deadline` - Add a Deadline Task

Adds a deadline task with a specific end date/time to your list.

Format: `deadline DESCRIPTION /by DATE_TIME`

Example of usage:

`deadline Submit report /by 2023-10-01 18:00` Adds a deadline task with the description "Submit report" and the end date/time "2023-10-01 18:00" to your list.

---

### `event` - Add an Event Task

Adds an event task with a specific start and end date/time to your list.

Format: `event DESCRIPTION /from DATE_TIME /to DATE_TIME`

Example of usage:

`event Team meeting /from 2023-09-01 10:00 /to 2023-09-01 11:00` Adds an event task with the description "Team meeting" and the start date/time "2023-09-01 10:00" and end date/time "2023-09-01 11:00" to your list. 

---
### `list` - View All Tasks

Displays all tasks in your task list.

Format: `list`

---

### `delete` - Delete a Task

Deletes a task from your task list.

Format: `delete TASK_NUMBER`

Example of usage:

`delete 3` Deletes the third task in your list.

---

### `find` - Find Tasks

Finds tasks in your task list using keywords.

Format: `find KEYWORD`

Example of usage:

`find report` Finds all tasks in your list containing the keyword "report".

---

### `done` - Mark a Task as Done

Marks a task in your task list as done.

Format: `done TASK_NUMBER`

Example of usage:

`done 2` Marks the second task in your list as done.

---

### `unmark` - Mark a Task as Undone

Marks a task in your task list as not done yet.

Format: `unmark TASK_NUMBER`

Example of usage:

`unmark 2` Marks the second task in your list as not done yet.

---

### `sort` - Sort Tasks

Sorts all the deadline tasks in your list chronologically.

Format: `sort`

---

### `bye` - Exit the App

Exits the app.

Format: `bye`
