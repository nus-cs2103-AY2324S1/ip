# Beary

## Features 

### Add tasks

Assign tasks to Beary for tracking. Tasks can be classified as todo, deadline or event.

### List tasks

Lists out the current tasks and their details.

### Delete tasks

Delete a specific task. Deleted tasks will no longer be tracked by Beary.

### Mark and unmark tasks

Tasks can be marked as completed and unmarked if need be.

### Find tasks

Search for tasks using a keyword.


## Usage

### `list` - Lists out current tasks

Lists out all tasks currently being tracked by Beary.

Example of usage: 

`list`
`keyword (optional arguments)`

Expected outcome:

The current tasks are shown in a numbered list.  


### `todo` - Adds a new todo task

Adds a new task without a deadline.

Example of usage:

`todo <task name>`

Expected outcome:

A new todo task is added to the list of tasks.

### `deadline` - Adds a new deadline task

Adds a new task with a deadline. Date must be given in YYYY-MM-DD format.

Example of usage:

`deadline <task name> /by <date>`

Expected outcome:

A new deadline task is added to the list of tasks.

### `event` - Adds a new event task

Adds a new event task with a start date and end date. Dates must be given in YYYY-MM-DD format.

Example of usage:

`event <task name> /from <start date> /to <end date>`

Expected outcome:

A new event task is added to the list of tasks.

### `mark` - Marks a task as completed

Mark a task to show that it has been completed.

Example of usage:

`mark <task number>`

Expected outcome:

The specified task is marked with a "X" beside it to show that it is completed.

### `unmark` - Unmarks a marked task

Unmarks a task that has been marked as completed.

Example of usage:

`unmark <task number>`

Expected outcome:

The specified task is no longer marked with a "X" beside it.

### `delete` - Deletes a task

Removes a task from the list of tasks.

Example of usage:

`delete <task number>`

Expected outcome:

The specified task is removed from the list of tasks.

### `find` - Finds one or more tasks

Finds all tasks containing the keyword.

Example of usage:

`find <keyword>`

Expected outcome:

Tasks containing the keyword are displayed.

### `help` - Displays the help message

Lists out all commands and how to use them.

Example of usage:

`help`

Expected outcome:

All commands and how to use them are shown.

### `bye` - Exits the application

Closes the application.

Example of usage:

`bye`

Expected outcome:

The application closes after a short pause.

