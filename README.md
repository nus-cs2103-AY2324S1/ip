# Frenchie User Guide

## Features 

### Feature 1 - Storing tasks

Storing of 3 different tasks:
1. To Do (with no deadline)
2. Deadlines (with a deadline)
3. Events (with a Start and End time)

### Feature 2 - Listing of stored tasks
### Feature 3 - Marking tasks as done/undone
### Feature 4 - Deleting tasks from the list
### Feature 5 - Searching for all tasks containing any keyword

## Usage

### `todo` - Adds a ToDo type task into the list



Example of usage: 

`todo read book`

Expected outcome:

Frenchie will return this message for successfully adding a task to the list. [T] represents a ToDo task and the [ ] indicates the task is not yet completed.
 ```
Got it! I've added this task:

[T][ ] read book

Now you have X tasks in the list.
```

### `deadline` - Adds a Deadline type task into the list. 

Deadline has to be in format dd/MM/yyyy HH:mm.

Example of usage:

`deadline read book /by 09/09/2023 18:00`

Expected outcome:

Frenchie will return this message for successfully adding a task to the list. [D] represents a Deadline task and the [ ] indicates the task is not yet completed.
 ```
Got it! I've added this task:

[D][ ] read book (by: 2023-09-09 18:00).

Now you have X tasks in the list.
```

### `event` - Adds an event type task into the list

Start and end time of event have to be in format dd/MM/yyyy HH:mm.
Example of usage:

`todo read book /from 09/09/2023 18:00 /to 09/09/2023 19:00`

Expected outcome:

Frenchie will return this message for successfully adding a task to the list. [E] represents an event task and the [ ] indicates the task is not yet completed.
 ```
Got it! I've added this task:

[E][ ] read book (from: 2023-09-09 18:00 to: 2023-09-09 19:00)

Now you have X tasks in the list.
```

### `list` - lists all tasks currently stored in Frenchie

Example of usage:

`list`

Expected outcome:

```
1. [T][ ] read book
2. [D][ ] read book (by: 2023-09-09 18:00)
3. [E][ ] read book (from: 2023-09-09 18:00 to: 2023-09-09 19:00)
```

### `mark` - mark the specified task as complete

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] read book
```

### `unmark` - mark the specified task as complete

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][ ] read book
```

### `delete` - delete the specified task from the list

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][ ] read book 
Now you have X tasks in the list.
```

### `find` - search through the list and returns all tasks that contain the keyword

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][ ] read book (by: 2023-09-09 18:00)
2. [E][ ] read book (from: 2023-09-09 18:00 to: 2023-09-09 19:00)
```

### `help` - returns a quickstart guide for users who have forgotten any command

Example of usage:

`help`

Expected outcome:

A quickstart guide for Frenchie listing the commands.