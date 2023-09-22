# HEAD todo list

## Features 

### Adding a Todo `todo`

Adds a Todo task to the task list

Format : `todo TITLE`

- Title can have spaces and need not be a singular word

Example : `Todo cs2103 ip`

### Adding a Deadline `deadline`

Adds a deadline task to the task list

Format : `todo TITLE /by DEADLINE`

- DEADLINE must either be in the format DD/MM/YYYY HH:mm or DD/MM/YYYY
- Default time is 12:00

Example : 
- `deadline cs2103 ip /by 22/2/2022`
- `deadline cs2103 ip /by 22/2/2022 23:59`

### Adding an Event `event`

Adds an event task to the task list

Format : `todo TITLE /from FROM /to TO`

- From and To can either be in the format DD/MM/YYYY HH:mm or DD/MM/YYYY
- Default time is 12:00

Example :
- `event cs2103 meeting /from 22/2/2022 /to 23/2/2022`
- `event cs2103 meeting /from 22/2/2022 12:00 /to 23/2/2022 14:00`

### Listing all tasks `list`

Lists all added tasks in the task list

Format : `list`

### Find task with keyword `find`

Finds all tasks containing the provided keywords

Format : `find KEYWORD`

- Keyword can be space separated, eg. CS2103 ip, but 
- Only one keyword or phrase can be input

Example : `find cs2103` 

### Marks a task as done `mark`

Marks the task at the provided index 

Format : `mark INDEX`

- INDEX provided must be a **positive integer** between 0 and size of the task list

>💡 Use list to find the index of a task if unsure of the 
> index of a specific task

Example : `mark 0`

### Marks a task as undone `unmark`

Marks the task at the provided index

Format : `unmark INDEX`

Example : `unmark 0`

### Delete a task `delete`

Deletes the task at the provided index

Format : `delete INDEX`

- INDEX provided must be a **positive integer** between 0 and size of the task list

>💡  Use list to find the index of a task if unsure of the
> index of a specific task

Example : `unmark 0`

### Undo the previous change `undo` 

Reverts the task list to its state one command ago

Format : `undo`

### Saving the data 
Tasks in the task list are automatically saved after every command that edits the task list. There is no 
need for manual saving.


