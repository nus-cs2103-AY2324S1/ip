# ET User Guide

## Features 
- [Adding a Todo task: `todo`]((https://github.com/alientian/ip/blob/master/docs/README.md#adding-a-todo-task-todo))
- [Adding a Deadline task: `deadline`](https://github.com/alientian/ip/blob/master/docs/README.md#adding-a-deadline-task-deadline)
- [Adding an Event task: `event`](https://github.com/alientian/ip/blob/master/docs/README.md#adding-an-event-task-event)
- [Listing all tasks: `list`](https://github.com/alientian/ip/blob/master/docs/README.md#listing-all-tasks-list)
- [Deleting a task: `delete`](https://github.com/alientian/ip/blob/master/docs/README.md#deleting-a-task-delete)
- [Finding a task: `find`](https://github.com/alientian/ip/blob/master/docs/README.md#finding-a-task-find)
- [Exiting a programme: `bye`](https://github.com/alientian/ip/blob/master/docs/README.md#exiting-a-programme-bye)

## Usage

### Adding a Todo task: `todo`

Adds a todo task with description to the list.

Format: `todo DESCRIPTION`

Example of usage:

`todo read book`

Expected outcome:

```
Got it. I've added this task:
 [T][] read book
Now you have __ tasks in the list.
```
### Adding a deadline task: `deadline`

Adds a deadline task with description and deadline to the list.

Format: `deadline DESCRIPTION /by DATE(yyyy-mm-dd)`

Example of usage:

`deadline return book /by 2023-09-09`

Expected outcome:

```
Got it. I've added this task:
 [D][] return book (by: Sep 9 2023)
Now you have __ tasks in the list.
```

### Adding an Event task: `event`

Adds a event task with description, start date and end date to the list.

Format: `event DESCRIPTION /from DATE(yyyy-mm-dd) / by DATE(yyyy-mm-dd)`

Example of usage:

`event camp /from 2023-09-01 /to 2023-09-05`

Expected outcome:

```
Got it. I've added this task:
 [E][] camp (from: Sep 1 2023 to: Sep 5 2023)
Now you have __ tasks in the list.
```

### Listing all tasks: `list`

Lists all the tasks in the list.

Format: `list`

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][] read book
2. [D][] return book (by: Sep 9 2023)
3. [E][] camp (from: Sep 1 2023 to: Sep 5 2023)
```
### Deleting a task: `delete`

Deletes a task from the list.

Format: `delete INDEX`

Example of usage:
`delete 1`

Expected outcome:

Deletes the first task shown in the `list` command

```
Noted. I've removed this task:
[T][] read book
Now you have __ tasks in the list.
```

### Finding a task: `find`

Finds tasks that contains the keyword.

Format: `find KEYWORD`

Example of usage:
`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][] read book
2. [D][] return book (by: Sep 9 2023)
```

### Exiting a programme: `bye`

Exits the programme.

Format: `bye`

Example of usage:
`bye`


