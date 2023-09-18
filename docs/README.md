# User Guide: Dude

## Features 
### Tasks
Dude tracks 3 different types of tasks: ToDo, Event and Deadline.
### Commands
Dude supports the following commands:
* `todo` - Adds a ToDo task.
* `event` - Adds an Event task.
* `deadline` - Adds a Deadline task.
* `list` - Lists all tasks.
* `save` - Saves all tasks to a file.
* `load` - Loads all tasks from a file.
* `mark` - Marks a task as done.
* `unmark` - Marks a task as not done.
* `schedule` - Lists all tasks that occur on a given date.
* `delete` - Deletes a task.
* `find` - Finds tasks with a given keyword.
* `snooze` - Postpones a task.
* `bye` - Exits the program.

## Available Commands:
### `todo` - Adds a ToDo task.
Adds a ToDo task to the task list.

Format: `todo DESCRIPTION`

Examples: `todo cs2103 ip`

### `event` - Adds an Event task.
Adds an Event task to the task list.

Format: `event DESCRIPTION /from START_DATE START_TIME /to END_DATE END_TIME`

Start and end dates must be in the format DD/MM/YYYY.

Start and end times must be in the format HHMM.

Examples: `event cs2103 meeting /from 01/10/2021 1400 /to 01/10/2021 1600`

### `deadline` - Adds a Deadline task.
Adds a Deadline task to the task list.

Format: `deadline DESCRIPTION /by DD/MM/YYYY HHMM`

### `list` - Lists all tasks.
Lists all tasks in the task list.

### `save` - Saves all tasks to a file.
Saves all tasks in the task list to a file.

### `load` - Loads all tasks from a file.
Loads all tasks from a file.

### `mark` - Marks a task as done.
Marks a task as done.

Format: `mark INDEX`

Examples: `mark 1` marks the first task in the list as done.

### `unmark` - Marks a task as not done.
Marks a task as not done.

Format: `unmark INDEX`

Examples: `unmark 1` marks the first task in the list as not done.

### `schedule` - Lists all tasks that occur on a given date.
Lists all tasks that occur on a given date.

Format: `schedule DD/MM/YYYY`

Examples: `schedule 01/10/2021` lists all tasks that occur on 1st October 2021.

### `delete` - Deletes a task.
Deletes a task.

Format: `delete INDEX`

Examples: `delete 1` deletes the first task in the list.

### `find` - Finds tasks with a given keyword.
Finds tasks with a given keyword.

Format: `find KEYWORD`

Examples: `find cs2103` lists all tasks with the keyword cs2103.

Expected outcome:
```
Here are the matching tasks in your list:
2.[E][ ] cs2103 meeting (at: 01/10/2021 1400 to 01/10/2021 1600)
```

### `snooze` - Postpones a task.
Postpones a task.

Format: `snooze INDEX DD/MM/YYYY HHMM`

Examples: `snooze 1 01/10/2021 1400` postpones the first task in the list to 1st October 2021, 2pm.

### `bye` - Exits the program.
Exits the program.


