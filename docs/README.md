# User Guide
Ken is a chatbot for managing tasks.

- Features
  - List all task: [`list`](#list)
  - Add todo task: [`todo`](#todo)
  - Add deadline task: [`deadline`](#deadline)
  - Add event task: [`event`](#event)
  - Mark task as completed: [`mark`](#mark)
  - Mark task as incomplete: [`unmark`](#unmark)
  - Find task with similar description: [`find`](#find)
  - Delete task: [`delete`](#delete)
  - Exit the program: [`bye`](#bye)
  - Viewing help: [`help`](#help)
## Features 

Notes about the command format:
- Words in UPPER_CASE are the parameters to be supplied by the user.\
  e.g. in todo DESCRIPTION, DESCRIPTION is a parameter which can be used as todo borrow book.
- DATE is preferably in `dd/MM/yyyy HHmm` format
- INDEX **must be a positive integer** 1, 2, 3, …​



### `list`
Show a list of all tasks.\
Format: `list`

### `todo`
Add todo task.\
todo task only have description.\
Format: `todo DESCRIPTION`

### `deadline`
Add deadline task.\
deadline task contain description and date the task have to be finished.\
Format: `deadline DESCRIPTION /by DATE`

### `event`
Add event task.\
event task contain description, start date and end date of event.\
Format: `event DESCRIPTION /from DATE /to DATE`

### `mark`
Mark task at specified index as done.\
Completed task will indicate [X] as done.\
Format: `mark INDEX`

### `unmark`
Unmark task at specified index as incomplete.\
Incomplete task will indicate [] as not done.\
Format: `unmark INDEX`

### `find`
Find task and list all tasks with similar description provided. \
Format: `find DESCRIPTION`

### `delete`
Delete task at specified index.\
Format: `delete INDEX`

### `bye`
Exit the program.\
Format: `bye`

### `help`
List all the commands that is available.\
Format: `help`

