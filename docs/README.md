# User Guide
YeboiJoe is a **desktop application that manages task scheduling**. 
If you can type as fast as Kanye raps, this application is for you.

## Features 

### Viewing help: `help`
Shows a message listing all available commands for the user.

Format: `help`

### Viewing task list: `list`
Shows all current tasks in the task list with their details.

Format: `list`

### Adding a Todo task: `todo`
Add a Todo task to the task list. 

Format: `todo <TASK_NAME>`

Example of usage: 

`todo buy beer`

Expected outcome:

Adds a todo task named 'buy beer'

```
added new task:
[T] [] buy beer
you now have 1 tasks in your list.
```

### Adding a Deadline task: `deadline`
Adds a Deadline task to the task list.

Format: `deadline <TASK_NAME> /by <[DATE] [TIME]>`
- Either date or time may be omitted, but **not both**.
- Date must strictly be in *yyyy-HH-dd* format.
- Time must strictly be in *HH:mm* format.

Examples of usage:

- `deadline finish homework /by 2023-09-23 18:00`
- `deadline wash dishes /by 20:00`
- `deadline paint toenails /by 2023-09-11`

Expected outcome:

Adds a deadline task named after your task name.

```
added new task:
[D] [] finish homework (09 Sep 2023 18:00)
you now have 2 tasks in your list.
```

### Adding an Event task: `event`
Adds an Event task to the task list. A warning will be sent when events that have overlapping timings.

Format: `event <TASK_NAME> /from <[START_DATE] [START_TIME]> /to [END_DATE] [END_TIME]`
- Either date or time may be omitted, but **not both**.
- Date must strictly be in *yyyy-HH-dd* format.
- Time must strictly be in *HH:mm* format.

Examples of usage:

- `event wife's wedding /from 01:00 /to 06:00`
- `event cousin birthday party /from 2023-01-01 /to 2023-12-31`

Expected outcome:

Adds a event task named after your task name.

```
added new task:
[E] [] cousin birthday party (01 Jan 2023 00:00 to 31 Dec 2023 00:00)
you now have 3 tasks in your list.
```

### Locating tasks by name: `find`
Finds all tasks which names contains the given keyword.

Format: `find <KEYWORD>`
- Keyword is case-sensitive.

Example of usage:

`find cousin` 

Expected outcome:

Locates and returns all tasks with name matching 'cousin'.

```
Here are the matching tasks in your list:
[E] [] cousin birthday party (01 Jan 2023 00:00 to 31 Dec 2023 00:00)
```

### Setting a task as done: `mark`
Sets a task as completed.

Format: `mark <INDEX>`
- The index is the position the task is in the task list.
- The index **must be a positive integer** i.e. 1, 2, 3, ...
- Use the `list` command to view position of all tasks.



### Setting a task as not done: `unmark`
Sets a task as uncompleted.

Format: `unmark <INDEX>`
- The index is the position the task is in the task list.
- The index **must be a positive integer** i.e. 1, 2, 3, ...
- Use the `list` command to view position of all tasks.

### Deleting a task: `delete`
Deletes a specified task from the task list.

Format: `delete <INDEX>`
- The index is the position the task is in the task list.
- The index **must be a positive integer** i.e. 1, 2, 3, ...
- Use the `list` command to view position of all tasks.

### Exiting the program: `exit`
Exits the program.

Format: `exit`