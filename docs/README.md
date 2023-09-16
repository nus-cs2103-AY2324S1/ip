# User Guide
## Prawn
Prawn is a **desktop chatbot for storing of various tasks, optimised for
use via the command line interface (CLI)** while still have the benefits
of the graphical user interface (GUI). 
## Features 

### Adding Tasks

Allows users to add various tasks *(Eg. todo, deadline, event)* to the 
task list for further viewing.

### Marking of tasks

Allows users to mark specific tasks as done or unmark them if they
are incomplete.

### Viewing of tasks

Allows users to view all the tasks that they currently have and their
completion status.

## Usage

### `list` - Displays the tasks

Displays the current tasks .

Format : `list`

Example of usage: 

`list`

### `todo` - Adds a todo task

Adds a todo task to the current list.

Format: `todo [description]`

Example of usage:

`todo read book`, `todo finish homework`

- The description of the task comes after the todo command
- The description **must** be supplied
- There can only be one description

### `deadline` - Adds a deadline task

Adds a deadline task with a date to the current list.

Format: `deadline [description] /by [YYYY-MM-DD]`

Example of usage:

`deadline return books /by 2023-09-09`, `deadline homework /by 2023-11-09`

- The description and date **must** be specified in the above format

### `event` - Adds an event task

Adds an event task with a start and end date or time.

Format: `event [description] /from [start date/time] /to [end date/time]`

Example of usage:

`event attend meeting /from Aug 21 2pm /to 4pm`

- Description, start and end must be specified
- Note that start and end **does not follow** the same format as deadline
- The format of start and end is up to the user

### `mark` - Marks a task as done

Marks a task at a specific index as done.

Format: `mark [index]`

Example of usage:

`mark 1`, `mark 3`

- Note that the starting index is 1
- The indexing follows the order displayed under `list`

### `unmark` - Unmarks a task as undone

Unmarks a task at a specific index as undone.

Format: `unmark [index]`

Example of usage:

`unmark 1`, `unmark 3`

- Note that the starting index is 1
- The indexing follows the order displayed under `list`

### `delete` - Deletes a task

Deletes a task at a specific index.

Format: `delete [index]`

Example of usage:

`delete 1`, `delete 4`

- Note that the starting index is 1

### `find` - Find tasks that contains input word

Find tasks that contain the input word and displays them.

Format: `find [word]`

Example of usage:

`find book`, `find movie`

- Note that the word is **case_sensitive**

### `bye` - Exits the application

Exits and closes the application window

Format: `bye`



