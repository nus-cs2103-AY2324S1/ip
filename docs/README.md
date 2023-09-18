# Aj-Bot

## Features 
1.  Add / Delete task
2. Mark / Unmark task
3. View list of tasks
4. Find tasks via keyword 
5. Save changes to database 
6. Undo latest command


### Add / delete task

Allows users to add task (Todo, Deadline, Event tasks) into a task list.

### Mark / Unmark task

Allows users to mark or unmark a task as done.

### View list of tasks

Allows users to view all their tasks.

### Find tasks via keyword

Allows users to view all their tasks.

### Save changes to database

Allows users task list or updates to be saved into database.

### Undo latest command

Allows users to undo the latest command.

## Usage


### `help` - View a list of possible commands

Example of usage:

`help`

Expected outcome:

```
Here is a list of commands you can try: 

list - To list your added tasks
mark - To mark a task as completed
unmark - To unmark a task
...
```

---

### `<Command>` - View specific usage of 'Command'

Example of usage:

`event`

What it does :
Adds an `event` task

Expected outcome:

```
event <task name> /from <date/time> /to <date/time>
```
>Ensure that parameter orderings are strictly followed!!

---

### `list` - List tasks in task list

Example of usage:

`list`

Expected outcome:

```
1.[E][X] cs2103T outing (from:monday 1pm to:2pm)
2.[T][ ] Return book
```

---

### `mark <task_no>` - Mark task at 'task_no' as completed

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[E][X] cs2103T outing (from:monday 1pm to:2pm)
```
---
### `find <keyword>` - Find tasks with 'keyword' in description

Example of usage:

`find cs`

Expected outcome:

```
Finding tasks with names matching : "cs"
Here they are!!:
1.[E][X] cs2103T outing (from:monday 1pm to:2pm)
2.[T][ ] cs2101 presentation script
```
---
