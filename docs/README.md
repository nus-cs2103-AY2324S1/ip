# Handsome Chatbot User Guide

Handsome Chatbot is a desktop app for **creating and managing your own to-do list**, 
optimised for use via a Command Line Interface(CLI).

## Features 

> **Notes about the command format:**
> 
> - Words in `UPPER_CASE` are the parameters to be supplied by the user e.g. in `deadline TASK_DESCRIPTION /by DEADLINE`
> DEADLINE is a parameter that can be used as `deadline do homework /by 01/01/2024 0800`
> 
> 
> - Date and times have to be specified in the format: `dd/mm/yyyy hhmm` eg. `19/09/2023 1530`

### Viewing help: `help`
Shows a message explaining a command and its format, alternatively, view all available commands.

Format: `help`

- Displays all available commands

Format: `help COMMAND`

- Shows the usage of the specified command and its format.

Example: 

- `help list`

### Adding a todo: `todo`

Adds a todo to the task list.

Format: `todo TASK_DESCRIPTION`

Example:
- `todo do laundry`
- `todo read book`

### Adding a deadline: `deadline`

Adds a deadline to the task list. A deadline includes the date and time the task is to be done by.

Format: `todo TASK_DESCRIPTION /by DEADLINE`

Example:
- `deadline cs2100 assignment /by 18/09/2023 1300`
- `deadline apply for exchange /by 24/09/2023 2359`

### Adding an event: `event`

Adds an event to the task list. An event includes the starting and ending date and time.

Format: `event TASK_DESCRIPTION /from START /to END`

Example:
- `event cs2103 weekly meeting /from 19/09/2023 1300 /to 19/09/2023 1400`
- `event watch movie /from 20/09/2023 1000 /to 20/09/2023 1230`

### Deleting a task: `delete`

Deletes a specified task from the task list

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list.
  The index must be a positive integer 1, 2, 3, ...

Example:
- `delete 1`

### Listing all tasks: `list`

Shows all the current tasks in the task list.

Format: `list`

### Marking a task as done: `mark`

Marks a specified task in the task list as done.

Format: `mark INDEX`

- Marks the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list.
The index must be a positive integer 1, 2, 3, ...

Example:
- `mark 3`

### Marking a task as not done: `unmark`

Marks a specified task in the task list as not done.

Format: `unmark INDEX`

- Unmarks the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list.
  The index must be a positive integer 1, 2, 3, ...

Example:
- `unmark 2`

### Searching tasks by description: `find`

Find tasks whose names contain any of the given keywords

Format: `find KEYWORD`

- The search is case-insensitive e.g. `BOOK` will match `book`
- Only the task description is searched.
- Partial names can be searched e.g. `Boo` will match `Book`

Example:
- `find Homework` returns `homework`
- `find home` returns `homework` and `go home`

### Exiting the application: `bye`

Exits the application

Format: `bye`

### Saving the data

Handsome Chatbot data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually. The data can be found in `[JAR file location]/data/tasks.txt`

### Editing the data

Handsome Chatbot data are saved automatically as a txt file `[JAR file location]/data/tasks.txt`.
Advanced users are welcome to update data directly by editing that data file.

> **Caution:** If your changes to the data file is in an invalid format, Handsome Chatbot will not be
> able to read it properly. Hence, it is recommended to take a backup of the file before editing it.
