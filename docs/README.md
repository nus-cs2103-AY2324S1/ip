# User Guide for Minion Bot

Note: Todos, events, deadlines are collectively referred to as tasks. 

## Features 

### Adding a todo: `todo`

Creates a todo.

Format: `todo [todo name]`

Example:
* `todo homework`

### Adding a deadline: `deadline`

Creates a deadline with a given datetime in the format dd/mm/yyyy hhmm.

Format: `deadline [deadline name] /by [datetime]`

Example:
* `deadline assignment /by 3/9/2023 1732`

### Adding an event: `event`

Creates an event with the given from and to datetimes both in the format dd/mm/yyyy hhmm.

Format: `event [event name] /from [datetime] /to [datetime]`

Example:
* `event party /from 3/9/2023 1700 /to 4/9/2023 0400`

### Listing all events: `list`

Lists all events.

Format: `list`

### Marking task as done: `mark`

Format: `mark INDEX`

* Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the task list.
* The index **must be a positive integer** 1, 2, 3, …​.

### Marking task as undone: `unmark`

Format: `unmark INDEX`

* Marks the task at the specified `INDEX` as undone.
* The index refers to the index number shown in the task list.
* The index **must be a positive integer** 1, 2, 3, …​.

### Deleting task: `delete`

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the task list.
* The index **must be a positive integer** 1, 2, 3, …​

### Finding tasks: `find`

Finds task(s) whose names and/or datetimes match the given pattern.

Format: `find [pattern]`

Examples:
* `find assignment`
* `find 3 Sep 2023`

### Exiting the program: `bye`

Exits the program. 

Format: `bye`

### Saving the data

Task data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
