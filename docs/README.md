# JEDuke User Guide

JEDuke, or _JED_, is your personal task manager chatbot of the decade.

## Features

### Manage Tasks
Tasks are split into Todos, Deadlines and Events.

You can create, remove, and list all tasks with JED.

### Aliasing

Add your own aliases to handle commands more efficiently!

## Usage
### Contents
1. Task-related commands [Jump](#task-related)
2. Alias-related commands [Jump](#alias-related)


---
### Task Related

### `list` - List all existing tasks

Example of usage: 

`list`

Expected outcome:
Lists all existing tasks

```
  1. [T][X] do work
  2. [T][ ] more work
  3. [T][ ] finish work
```

---

### `todo` - Add a Todo task.

Format: `todo TODO_NAME`

Example of usage: `todo go to school`

Expected outcome:

Adds a new todo to the list of tasks.

---

### `deadline` - Add a Deadline task.

Format: `deadline /by dd-MM-yyyy HHmm`

Example of usage: `deadline complete tutorial \by 12-10-2023 2359`

Expected outcome:

Adds a new deadline to the list of tasks. 

---

### `event` - Add a Event task.

Format: `event /from dd-MM-yyyy HHmm /to dd-MM-yyyy HHmm`

Example of usage: `event tiktok hackathon \from 12-10-2023 0800 \to 13-10-2023 1800`

Expected outcome:

Adds a new event to the list of tasks. 

---

### `mark` - Mark a task as completed.

Format: `mark INDEX`

Example of usage: `mark 1`

Expected outcome:

Marks the task at the specified index as completed.

---

### `unmark` - Unmark a task as incomplete.

Format: `unmark INDEX`

Example of usage: `unmark 1`

Expected outcome:

Un-marks the task at the specified index as incomplete.

---

### `delete` - Delete a task.

Format: `delete INDEX`

Example of usage: `delete 1`

Expected outcome:

Deletes the task at the specified index.

---

### `find` - Find tasks by matching keyword.

Format: `find KEYWORD`

Example of usage: `find hackathon`

Expected outcome:

Lists all tasks with matching keyword.

---
### Alias Related

### `alias_list` - List all aliases saved.

Example of usage: `alias_list`

Expected outcome:

```
td=todo
ls=list
dl=deadline
als=alias_list
```

Lists all aliases, and corresponding full commands.

---

### `alias_add` - Add a new alias.

Format: `alias_add ALIAS=FULLCOMMAND`

Example of usage: `alias_add ev=event`

Expected outcome:

Creates a new alias for the given command.

---

### `alias_delete` - Delete an existing alias.

Format: `alias_delete ALIAS`

Example of usage: `alias_delete ev`

Expected outcome:

Deletes the existing alias.

---







