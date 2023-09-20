# IRIS

## User Guide

## Overview

1. [Features](#features)
2. [Usage](#usage)
    1. [`todo`](#todo---add-a-todo-task)
    2. [`deadline`](#deadline---add-a-deadline-task)
    3. [`event`](#event---add-an-event-task)
    4. [`list`](#list---list-all-tasks)
    5. [`find`](#find---finds-all-tasks-with-a-matching-name)
    6. [`mark`](#mark---mark-a-task-as-completed)
    7. [`unmark`](#unmark---mark-a-task-as-incomplete)
    8. [`delete`](#delete---delete-a-task)
    9. [`bye`](#bye---close-iris)

## Features

### Intuitive user interface
The user interface is similar to the ones 
that can be found in many applications, so you'll feel right at home even as
a first time user. _Even IRIS' name is familiar_ :).

### Track your to-dos, deadlines, and events
IRIS will keep you up to date with your tasks that have no specified date, an due date,
or ones that occur over a duration.

### Duplicate task detection
Forgetful? Fear not, IRIS will remind you of duplicate tasks, easing the mental
exertion of remembering whether you've already added the task.

## Usage


### `todo` - Add a todo task

Format: `todo <content>`

Adds a task with only content.

Use case:

`todo CS2103T Tutorial`

```
Got it. I've added this task:
[T][ ] CS2103T Tutorial
Now you have 1 task in the list.
```


### `deadline` - Add a deadline task

Format: `deadline <content> /by <date in YYYY-MM-DD format>`

Adds a task with content and a date that it is due by.

Use case:

`deadline CS2100 graded assignment /by 2023-09-19`

```
Got it. I've added this task:
[D][] CS2100 graded assignment (by: Sep 19 2023)
Now you have 2 tasks in the list.
```


### `event` - Add an event task

Format:`event <content> /from <date in YYYY-MM-DD format> /to <date in YYYY-MM-DD format>`

Adds a task with content and a duration of time that it occurs

Use case:

`event Hackathon /from 2023-09-19 /to 2023-09-20`
```
Got it. I've added this task:
[E][ ] Hackathon (from: Sep 19 2023 to: Sep 20 2023)
Now you have 3 tasks in the list.
```


### `list` - List all tasks

Format: `list`

Lists all tasks added so far, even from previous usage of IRIS, in the order that they were listed.

Use case:

`list`

```
1. [T][ ] CS2103T Tutorial
2. [D][ ] CS2100 graded assignment (by: Sep 19 2023)
3. [E][ ] Hackathon (from: Sep 19 2023 to: Sep 20 2023)
```

### `find` - Finds all tasks with a matching name

Format: `find <target>`

Finds all tasks whose content includes the target (case-insensitive), 
even if the target is sandwiched between other characters.

Use case:

`find CS2100`

```
Here are the matching tasks in your list:
  2. [D][ ] CS2100 graded assignment (by: Sep 19 2023)
```


### `mark` - Mark a task as completed

Format: `mark <index number of task>`

Marks the task with that index number as completed. 

The index number can be found by using the **find** command 
or the **list** command.

Use case:

`mark 1`

```
Nice! I've marked this task as done:
[T][X] CS2103T Tutorial
```

### `unmark` - Mark a task as incomplete

Format: `umark <index number of task>`

Marks the task with that index number as incomplete.

The index number can be found by using the **find** command
or the **list** command.

Use case:

`unmark 1`

```
Nice! I've marked this task as not done yet:
[T][ ] CS2103T Tutorial
```

### `delete` - Delete a task

Format: `delete <index number of task>`

Deletes a task with the corresponding index number. 

Use case:

`delete 1`

```
Noted. I've removed this task:
[T][ ] CS2103T Tutorial
Now you have 2 tasks in the list.
```

### `bye` - Close IRIS

Format: `bye`

Terminates the current IRIS window

Use case:

`bye`