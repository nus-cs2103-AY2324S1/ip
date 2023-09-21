# User Guide

## Features 

### Todo

Creates a new Todo with a description.
Format: `todo {Description}`
Example:
- `todo Buy milk`

### Deadline

Creates a new Deadline with a end DateTime and a description.
Format: `deadline {description} /by {yyyy-MM-dd HH:mm}`
Example:
- `deadline Finish milk /by 2023-09-22 09:00`

### Event

Creates a new Event with a start DateTime, end DateTime and a description.
Format: `event {description} /from {yyyy-MM-dd HH:mm} /to {yyyy-MM-dd HH:mm}`
Example:
- `event Go for tutorial /from 2023-09-21 09:00 /to 2023-09-21 10:00`

### Mark

Marks a task as done.
Format: `mark {task number}`
Example:
- `mark 1`

### Unmark

Marks a task as not done.
Format: `unmark {task number}`
Example:
- `unmark 1`

### Delete

Deletes a task from the user's todo list.
Format: `delete {task number}`
Example:
- `delete 1`

### Find

Search for a task that matches the search string in the user's todo list.
Format: `find {key word}`
Example:
- `find milk`

### Help

Shows the list of possible commands that the user can input.
Format: `help`

### Bye

Quits the bot.
Format: `bye`
