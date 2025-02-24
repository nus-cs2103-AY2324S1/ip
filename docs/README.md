# User Guide for Task Manager Maya

## Features 

### Adding a Task

Insert a new task, whether it's a simple to-do, a deadline, or an event, into the storage.

### Deleting a Task

Delete any task from the storage that you no longer need.

### Displaying Tasks

View a list of all tasks stored, and if necessary, sort them based on their descriptions.

### Marking Tasks as Done or Undone

Mark tasks as done or not done. Completed tasks will display a checkmark ("X") in the list.

### Searching for a Task by Keyword

Locate a specific task by entering a related keyword.

### Exiting the Program

Close the task manager application whenever needed.

## Usage

### `todo` - Adding a Todo Task

This command lets you add a to-do task to the storage.

Example:

`todo read book`

Expected outcome:
The task is added to the storage.

### `deadline` - Adding a Deadline Task

This command allows you to add a task with a specified deadline to the storage.

Example:

`deadline quiz /by 21-09-2021 23:59`

Expected outcome:
The task with the given deadline is added to the storage.

### `event` - Adding an Event Task

Utilize this command to add an event task to the storage.

Example:

`event party /from 21-09-2021 23:59 /to 22-09-2021 03:59`

Expected outcome:
The event task is added to the storage.

### `mark` - Marking a Task as Completed

Mark a task as completed using this command.

Example:

`mark 2`

2 - the 2nd task on the list

Expected outcome:
The task is marked as done, indicated by a checkmark ("X")./

### `unmark` - Marking a Task as Undone

Mark a task as undone using this command.

Example:

`unmark 2`

2 - the 2nd task on the list

Expected outcome:
The task is marked as undone.

### `delete` - Removing a Task 

Use this command to delete a task from the storage.

Example:

`delete 2`

2 - the 2nd task on the list

Expected outcome:
The task is removed from the storage.

### `find` - Finding Tasks by Keyword

Search for tasks by providing a keyword related to their description.

Example:

`find read`

Expected outcome:
Tasks with descriptions matching the keyword are returned.

### `list` - Displaying Tasks

Display a list of tasks with the ability to sort them.

Example:

`list`

Expected outcome:
All tasks are shown.

Example:

`list sortBy description asc`

`list sortBy description desc`

Expected outcome:
All tasks are shown in ascending or descending alphabetical order.

### `bye` - Exiting the Program

Use this command to exit the task manager application.

Example:

`bye`

Expected outcome:
The program is closed.
