# Nobital - User Guide

## Features 

### ToDo, Deadline, Event

Nobita enable tracking of different types of tasks:

- A generic todo task
- A deadline task that has a due date.
- An event task that is within a period

### List, Mark, Unmark, Delete, Find, Update
Nobita allow manipulation of stored tasks which includes:

- view all exising tasks
- marking and unmarking of tasks
- deleting of particular task
- finding of tasks with task name
- Update a exist task details

Description of the feature.

## Usage

### `todo` - Add a todo task

Add a todo task to task list.

Example of usage: 

`todo Borrow Book`

Expected outcome:

A `todo` task with the task name is added to task list.

```
Got it. I've added this task:
[T][ ] Borrow Book
Now you have 1 tasks in the list.
```
### `deadline` - Add a deadline task

Add a deadline task to task list.

Example of usage:

`deadline Return Book /by 2023-09-20`

Expected outcome:

A `deadline` task with the task name and due date is added to task list.

```
Got it. I've added this task:
[D][ ] Return Book (by: 20 Sep 2023)
Now you have 2 tasks in the list.
```

### `event` - Add a event task

Add a event task to task list.

Example of usage:

`event Read Book /from 2023-09-10 /to 2023-09-15`

Expected outcome:

A `event` task with the task name and time period is added to task list.

```
Got it. I've added this task:
[E][ ] Read Book (from: 10 Sep 2023 to: 15 Sep 2023)
Now you have 3 tasks in the list.
```

### `list` - List tasks

List all tasks stored in task list.

Example of usage:

`list`

Expected outcome:

list all stored tasks.

```
1. [T][ ] Borrow Book
2. [D][ ] Return Book (by: 20 Sep 2023)
3. [E][ ] Read Book (from: 10 Sep 2023 to: 15 Sep 2023)
```

### `mark` - Mark a task

Mark a task as completed

Example of usage:

`mark 2`

Expected outcome:

The corresponding task will be mark as completed

```
Nice! I've marked this task as done:
[D][X] Return Book (by: 20 Sep 2023)
```

### `unmark` - Unmark a task

Mark a task as not complete.

Example of usage:

`unmark 2`

Expected outcome:

The corresponding task will be mark as not complete.

```
OK, I've marked this task as not done yet:
[D][ ] Return Book (by: 20 Sep 2023)
```

### `delete` - Delete a task

Delete a task.

Example of usage:

`delete 2`

Expected outcome:

The corresponding task will be deleted.

```
Noted. I've removed this task:
[D][ ] Return Book (by: 20 Sep 2023)
Now you have 2 tasks in the list.
```

### `find` - Find a task with name

Find all tasks that has task name matches with query

Example of usage:

`find book`

Expected outcome:

Task name that matches the query will be listed.

```
1. [T][ ] Borrow Book
2. [E][ ] Read Book (from: 10 Sep 2023 to: 15 Sep 2023)
```

### `update` - Update parameter of a task

Update a task's name, a deadline's due date or an event's from and to period.

Example of usage:

`update 2 /to 2023-09-18`

Expected outcome:

list all stored tasks.

```
OK, I've update this task:
[E][ ] Read Book (from: 10 Sep 2023 to: 18 Sep 2023)
```

### `bye` - Exit Nobita program

Exit Nobita program.

Example of usage:

`bye`

Expected outcome:

Nobita will exit.

```
Bye. Hope to see you again soon!
```




