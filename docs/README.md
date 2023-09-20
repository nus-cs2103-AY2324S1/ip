# User Guide

Dook is a task manager chatbot assistant that can help you keep track of your 
upcoming tasks, optimized for use with a **Command Line Interface (CLI)** while
still having the benefits of a Graphical User Interface (GUI).

## Features 

### Manage tasks

- Add todos, deadlines, and events
- Delete outdated tasks
- Mark tasks as done and undone

### Searching for tasks
- Search for task by keywords
- Search for date-related tasks by date

### User customisation
- User defined aliases for commands

### Save data between sessions
- Task list is saved between sessions
- Aliases also saved between sessions


# Usage
## Basic features

### `todo` - Add a new todo

Adds a todo to the task list.

Example of usage: 

`todo Homework`

Expected outcome:
```
added: [T][ ] Homework.
Now you have 1 task in the list.
```

### `deadline` - Add a new deadline

Adds a deadline to the task list.

Example of usage:

`deadline Assignment /by 23/06/2001`

Expected outcome:
```
added: [D][ ] Assignment (by: 23/06/2001).
Now you have 2 tasks in the list.
```

### `event` - Add a new event

Adds an event to the task list.

Example of usage:

`event Career Fair /from 12/04/2001 /to 15/04/2001`

Expected outcome:
```
added: [E][ ] Career Fair (from: 12/04/2001 to: 15/04/2001).
Now you have 3 tasks in the list.
```
### `list` - List all current tasks

Displays all current tasks to the user.

Example of usage:
`list`

### `delete` - Delete a task from the list

Deletes task at specified index in the list.

Example of usage:

`delete 2`
### `mark` / `unmark` - Mark/unmark a task from the list

Marks/unmarks task at specified index in the list as done.

Example of usage:

`mark 2`

## Advanced features

### `find` - Search the list

Finds tasks matching/containing the specified keyword.

Example of usage:

`find Assignment`
### `before` `during` `after` - Search the list for time related tasks

Finds tasks falling within the specified timeframe.
- Only deadlines and events are stored as time reated tasks.
- If the format of the dates are unrecognised, these operations won't work.

Example of usage:

`before 05/05/2005`
`during 14/02/2006`
`after 20/04/2010`
### `alias` - Add user defined shortcut

Adds user defined aliases for specified command.

Example of usage:

`alias l list` - `l` will now function the same as `list`.
