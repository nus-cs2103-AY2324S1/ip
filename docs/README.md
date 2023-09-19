# User Guide

## TY's slave
TY's slave is your personal *saikang* warrior **for keeping track of your daily tasks, optimised for use via a command 
line interface (CLI)** while still utilising the benefits of the graphical user interface (GUI).

## Features 
### Adding of tasks
TY's slave will help you to add your tasks to a task list.

### Viewing of tasks
You can ask TY's slave to help you view your your current and completed tasks.

### Marking of tasks
TY's slave allows you to mark certain tasks as done.

## Usage
### `list` - Displays the tasks
Displays the current tasks.

Format: `list`

Example of usage:

`list`

### `todo` - Adds a Todo task
Adds a task to be done by no specified time.

Format: `todo [description]`

Example of usage:

`todo shower`, `todo homework`
- The description of the task comes after the todo command
- There can only be one description
- The description **must** be supplied

### `deadline` - Adds a Deadline task
Adds a task to be done by a specified date/time.

Format: `deadline [description] /by [YYYY-MM-DD]`

Example of usage:

`deadline essay /by 2023-09-09`, `deadline homework /by 2023-09-10`
- The date need not follow the above format

### `event` - Adds a Event task
Adds a task to be done over a specified duration.

Format: `event [description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]`

Example of usage:

`event party /from 2023-09-09 /to 2023-09-10`, `event attend meeting /from Aug 21 2pm /to 4pm`
- The specified date and time of the start and end of event need not follow the above format

### `find` - Find tasks containing the input word
Find the tasks that match the input word and displays them.

Format: `find [input word]`

Example of usage:
`find homework`, `find meeting`

### `mark` - Mark a task as completed
Marks a task at a specific index as completed.

Format: `mark [index]`

Example of usage:
`mark 1`, `mark 3`
- Note that the starting index is 1

### `unmark` - Unmark a task as incomplete
Unmarks a task at a specific index as incomplete.

Format: `unmark [index]`

Example of usage:
`unmark 1`, `unmark 3`
- Note that the starting index is 1

### `delete` - Deletes a task
Deletes a task at the specified index.

Format: `delete [index]`

Example of usage:
`delete 1`, `delete 4`
- Note that the starting index is 1

### `bye` - Exits the application
Exits and closes the application window.

Format: `bye`
