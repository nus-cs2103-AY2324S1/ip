# Qi - The Chatbot

# User Guide

## Quick start
1. Ensure you have `Java 11` or above installed in your Computer.
1. Download the latest `qi.jar` from [here](https://github.com/VN-Hao/ip/releases).
1. Copy the downloaded file to the folder you want to store the app.
1. Open a command terminal and use `cd` command to navigate to the directory containg the app (can learn more from [here](https://www.digitalcitizen.life/command-prompt-how-use-basic-commands/)).
1. Launch the app by using command: `java -jar qi.jar`
1. Type `help` to get a quick brief the the commands' formats and functionalities. 

## Features 

### Add a todo: `todo`

Adds a todo task to the task list.

Format: `todo TASK_DESCRIPTION`

Example:
- `todo read book`  


### Add a dealine: `deadline`

Adds a deadline to the task list.

Format: `deadline TASK_DESCRIPTION /by yyyy-mm-dd`

Example:
- `deadline return book /by 2023-09-23`


### Add an event: `event`

Adds an event to the task list.

Format: `event TASK_DESCRIPTION /from START_TIME /to END_TIME`

Example:
- `event book festival /from Monday 2:00pm /to 4:30pm`


### Mark a task as done: `mark`

Marks a specific task in the task list as done.

Format: `mark TASK_ID`
- marks the task with index `TASK_ID` as done. Index starts from 1.

Example:
- `mark 2`


### Mark a task as unfinished: `unmark`

Marks a specific task in the task list as unfinished.

Format: `unmark TASK_ID`
- marks the task with index `TASK_ID` as unfinished. Index starts from 1.

Example:
- `unmark 2`


### Delete a task: `delete`

Removes a specific task from the task list.

Format: `delete TASK_ID`
- removes the task at index `TASK_ID`. Index starts from 1.

Example:
- `delete 3`


### List all tasks: `list`

Lists available tasks in the task list.

Format: `list`


### Find tasks by keyword: `find`

Lists all tasks in the task list whose descriptions contain the keyword.

Format: `find KEY_WORD`

Example:
- `find book`


### Exit app by command: `bye`

Exits the app immediately after the input.

Format: `bye`





