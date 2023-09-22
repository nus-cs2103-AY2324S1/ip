# User Guide
Welcome to Duke Chat Bot. It can help you better manage your tasks!!!
Please Proceed to the following to know the specific command to make the most out of the application.

## Features 
### Notes on command format
- Words in `UPPER_CASE` are parameters supplied by you.
  e.g. `todo DESCRIPTION`, `DESCRIPTION` is a parameter the command can be used as todo read book.
- Parameters' places are not interexchangeable.
  e.g. `event DESCRIPTION /from START_TIME /to ENDTIME`, the application cannot auto-correct the misplaced time parameter.
- Parameters like `START_TIME` and `END_TIME` accept a date in this format `dd/mm/yyyy`. A time can follow the date specified.
- Time inputs should be either `24hr` or `12hr`. The output will be presented as `12hr` time. e.g. 2359 will be 11:59 pm.

### Add a todo task -`todo`

This will add a task that has no specific date or duration

Format: `todo DESCRIPTION`

examples:

`todo read book`

`todo do laundry`

### Add a deadline task - `deadline`

`deadline` will add a task that has a specific deadline by which you should finish the task.

Format: `deadline DESCRIPTION /by DEADLINE`

examples:

`deadline finish homework /by mon 2359`

`deadline return book /by sunday`

### Add an event task - `event`

`event` will add a task that has a specific duration.

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

examples:

`event discussion /from mon 8 pm/to 10 pm`

`event hitting weights /from Sat 1500 /to 1600`

### List all existing tasks - `list`

`list` will list out all the current tasks that have been stored.

Format: `list`

### mark task as done - `mark`

`mark` will mark a task to status done and change the status icon from [ ] to [X].

Format: `mark INDEX`

The INDEX must be a positive integer (1...*)

An INDEX that is out of the current selectable range will throw an error message(_The given index is out of range!_)

Example:

`mark 1` will mark the first task as done.

### unmark a task - `unmark`

`unmark` will mark a task to be undone and change the status icon from [X] back to [ ].

Format: `unmark INDEX`

The INDEX must be a positive integer (1...*)

An INDEX that is out of the current selectable range will throw an error message(_The given index is out of range!_)

Example:

`unmark 1` will mark the first task as done.

### delete a task - `delete`

`delete` will delete a task.

Format: `delete INDEX`

The INDEX must be a positive integer (1...*)

An INDEX that is out of the current selectable range will throw an error message(_The given index is out of range!_)

Example:

`delete 1` will delete the first task.

### Locating tasks by keyword - `find`

`find` will find all tasks that fit the keyword.

Format: `find KEYWORD`

The search is case-sensitive. e.g. read book can not be interpreted as READ BOOK

Example:

`find pm` will find out all the tasks that are over noon.

`find read` will return tasks that contain the word `read`.

### exiting the application - `bye`

`bye` will exit the application

Format: `bye`

### Saving the data

The data will be automatically saved and updated when you do each operation.
