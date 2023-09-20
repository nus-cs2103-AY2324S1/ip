# KS Task Manager User Guide

## Features 

### Adding tasks

You can add 3 types of tasks: ToDos, Deadlines and Events.

### Marking tasks

You can mark tasks as complete or incomplete.

### Finding tasks

You can find tasks by searching for a keyword.

### Updating and deleting tasks

You can update or delete tasks if you made a mistake.

## Usage

### Adding a ToDo - `todo` 

Adds a ToDo task to the task list.

Format: `todo <description>`

Example of usage:

`todo read book`

Expected outcome:

A message indicating that the task has been added and the number of tasks in the list.

```
Got it. I've added this task:
[T][ ] read book
Number of tasks: 1
```
### Adding a Deadline - `deadline`

Adds a Deadline task to the task list.

Format: `deadline <description> /by <YYYY-MM-DD>`

Example of usage:

`deadline return book /by 2020-09-20`

Expected outcome:

A message indicating that the task has been added and the number of tasks in the list.

```
Got it. I've added this task:
[D][ ] return book (by: 20-Sep-2020)
Number of tasks: 2
```
### Adding an Event - `event`

Adds an Event task to the task list.

Format: `event <description> /from <YYYY-MM-DD> /to <YYYY-MM-DD>`

Example of usage:

`event carnival /from 2020-09-20 /to 2020-09-21`

Expected outcome:

A message indicating that the task has been added and the number of tasks in the list.

```
Got it. I've added this task:
[E][ ] carnival (from: 20-Sep-2020 to: 21-Sep-2020)
Number of tasks: 3
```
### Listing all tasks - `list`

Lists all tasks in the task list.

Format: `list`

Example of usage:

`list`

Expected outcome:

Shows all tasks in the task list.

```
Here are the tasks in your list:
    1.[T][ ] read book
    2.[D][ ] return book (by: 20-Sep-2020)
    3.[E][ ] carnival (from: 20-Sep-2020 to: 21-Sep-2020)
```
### Marking a task as done - `mark`

Marks a task as done.

Format: `mark <task index>`

Example of usage:

`mark 1`

Expected outcome:

A message indicating that the task has been marked as done.

```
Nice! I've marked this task as done:
[T][X] read book
```
### Marking a task as not done - `unmark`

Marks a task as not done.

Format: `unmark <task index>`

Example of usage:

`unmark 1`

Expected outcome:

A message indicating that the task has been marked as not done.

```
OK, I've marked this task as not done yet:
[T][ ] read book
```
### Deleting a task - `delete`

Deletes a task from the task list.

Format: `delete <task index>`

Example of usage:

`delete 1`

Expected outcome:

A message indicating that the task has been deleted and the number of tasks in the list.

```
Noted. I've removed this task:
[T][ ] read book
Number of tasks: 2
```
### Finding tasks - `find`

Find tasks by searching for a keyword.

Format: `find <keyword>`

Example of usage:

`find book`

Expected outcome:

Shows all tasks that contain the keyword.
```
Here are the matching tasks in your list:
    1.[D][ ] return book (by: 20-Sep-2020)
    2.[T][ ] read book
```
### Updating tasks - `update`

Updates a task in the task list.

Format: 

`update <task index> /desc <description>`

`update <task index> /by <YYYY-MM-DD>`

`update <task index> /from <YYYY-MM-DD>`

`update <task index> /to <YYYY-MM-DD>`

Example of usage:

`update 2 /from 2023-11-08`

`update 2 /desc fun carnival`

Expected outcome:

Updates the task and shows the updated task.
```
OK, I've updated this task:
[E][ ] carnival (from: 8-Nov-2023 to: 21-Sep-2020)
```
```
OK, I've updated this task:
[E][ ] fun carnival (from: 8-Nov-2023 to: 21-Sep-2020)
```
### Ending the program - `bye`

Ends the program.

Format: `bye`

Example of usage:

`bye`

Expected outcome:

Shows a goodbye message and ends the program.
```
Bye. Hope to see you again soon!
```