# ChatGPA 5.0 User Guide

## Features

### 1. Manage tasks, deadlines and events

Add, delete and mark completed todos, events and deadlines.

### 2. Find tasks

Easily find tasks by searching for keywords.

## Usage

### `/help` - Shows list of all commands

Example of usage: '/help'

Expected outcome:
```
Commands:
/help: show this menu
list: shows list of tasks
mark {task number}: mark task as done
unmark {task number}: unmark task as not done
todo {task name}: create a task
deadline {task name} /by {deadline}: create task with deadline
event {event name} /from {start date} /to {end date}: create event
delete {task number}: delete a task
find {keyword}: find tasks with keyword
bye: close application
```

### `list` - Shows a list of all tasks

Example of usage: `list`

Sample of a list of todos, deadlines and events:
```
1. [T][ ] 1
2. [D][ ] 1 (by: Jan 01 1000 12:00AM)
3. [E][ ] 1 (from: Jan 01 1000 12:00AM to: Jan 01 1000 12:01AM)
```

### `mark {task number}` - Mark task as done

Example of usage: `mark 1`

Expected outcome:
Marks the first task as done.

```
Nice! I've marked this task as done:
[T][X] 1
```

### `unmark {task number}` - Unmark task as not done

Example of usage: `unmark 1`

Expected outcome:
Unmarks the first task as not done.

```
OK, I've marked this task as not done yet:
[T][ ] 1
```


### `todo {task name}` - Creates a new todo

Example of usage: `todo finish ip`

Expected outcome:

Adds a new todo with the name "finish ip" to the list.

```
Got it. I've added this task:
[T][ ] finish ip
Now you have 4 tasks in the list.
```


### `deadline {task name} /by {deadline}` - Creates a new deadline

Example of usage:

`deadline finish tp /by 2023-09-17 2359`

Expected outcome:

Adds a new deadline with the name "finish tp" and deadline "2023-09-17 2359" to the list.

```
Got it. I've added this task:
[D][ ] finish tp (by: Sep 17 2023 11:59PM)
Now you have 5 tasks in the list.
```

### `event {event name} /from {start date} /to {end date}` - Creates a new event

Example of usage:

`event CS2103T lecture /from 2023-09-17 0800 /to 2023-09-18 1000`

Expected outcome:

Adds a new event with the name "CS2103T lecture" and start date "2023-09-17 0800"
and end date "2023-09-18 1000" to the list.

```
Got it. I've added this task:
[E][ ] CS2103T lecture (from: Sep 17 2023 8:00AM to: Sep 18 2023 10:00AM)
Now you have 6 tasks in the list.
```

### `delete {task number}` - Deletes a task

Example of usage:

`delete 1`

Expected outcome:

Deletes the first task.

```
Noted. I've removed this task:
[T][ ] 1
Now you have 5 tasks in the list.
```

### `find {keyword}` - Finds a task.

Example of usage:

`find CS2103T`

Expected outcome:

Finds all tasks with the keyword "CS2103T".

```
Here are the matching tasks in your list:
1. [E][ ] CS2103T lecture (from: Sep 17 2023 8:00AM to: Sep 18 2023 10:00AM)
```

### `bye` - Exits program

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
