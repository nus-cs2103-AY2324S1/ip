# Grumpy Gordon User Guide

## Getting Started
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `grumpygordon.jar` from [here](https://github.com/craigtonlian/ip/releases/download/v0.3/grumpygordon.jar).
3. Copy the file to the folder you want to use as the home folder for your Grumpy Gordon.
4. Open a terminal window, `cd` into the folder you put the jar file in, and run the command `java -jar grumpygordon.jar`.
5. A GUI similar to the one shown below should appear in a few seconds.
   ![List Command](./Ui.png)
6. Type the command in the command box and press Enter to execute it. e.g. typing `list` and pressing Enter will list all tasks.
7. Refer to the [Features](#features) below for details of each command.

<br>

## Features

### `list` - Listing all tasks

Displays a list of all tasks in the task list.

![List Command](./ListCommand.png)

Format: `list`

<br>

### `todo` - Adding a todo task

Adds a todo task to the task list.

Format: `todo DESCRIPTION`
- The `DESCRIPTION` is the description of the todo task.

Examples:
- `todo read book`

<br>

### `deadline` - Adding a deadline tasks

Adds a deadline task to the task list.

Format: `deadline DESCRIPTION /by END_DATETIME`
- The `DESCRIPTION` is the description of the deadline task.
- The `END_DATETIME` is the end date and time of the deadline task in the format `YYYY-MM-DD hh:mm`.

Examples:
- `deadline return book /by 2022-09-30 23:59`
- `deadline submit assignment /by 2023-02-25 08:03`

<br>

### `event` - Adding an event task

Adds an event task to the task list.

Format: `event DESCRIPTION /from START_DATETIME /to END_DATETIME`
- The `DESCRIPTION` is the description of the event task.
- The `START_DATETIME` and `END_DATETIME` is the starting and ending date and time of the event task in the format `YYYY-MM-DD hh:mm`.

Examples:
- `event singing lesson /from 2023-05-22 12:00 /to 2023-05-22 14:00`

<br>

### `mark` - Marking a task as done

Marks a task as done.

Format: `mark TASK_INDEX`

Examples:
- `list` followed by `mark 2` marks the second task in the task list as done.

<br>

### `unmark` - Marking a task as undone

Marks a task as undone.

Format: `unmark TASK_INDEX`

Examples:
- `list` followed by `unmark 2` marks the second task in the task list as undone.

<br>

### `delete` - Deleting a task

Deletes a task from the task list.

Format: `delete TASK_INDEX`
- Deletes the task at the specified `TASK_INDEX`.
- The index refers to the index number shown in the task list displayed from the `list` command.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:
- `list` followed by `delete 1` deletes the first task in the task list.

<br>

### `find` - Finding a task in the task list

Displays a list of all tasks that contains the keyword.

Format: `find KEYWORD`
- Only the description of the task is searched.
- The search is case-insensitive. e.g. `find book` will match `Book`
- Partial words will be matched. e.g. `find boo` will match `book`

<br>

### `sort` - Sorting the tasks in the task list

Sorts the tasks in the task list alphabetically.

Format: `sort`

<br>

### `bye` - Exiting the program

Exits the program.

Format: `bye`

<br>

### Saving the data

Task list data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.




