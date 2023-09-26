# User Guide

Chatbot is a chatbot that helps to keep track of your tasks. 

## Features 

### Adding a to-do: `todo `

Adds a task without any time constraints to the list.

Format: `todo TASK_DESCRIPTION`

Example of usage:

`todo read book`


### Adding a deadline task: `deadline`

Adds a task with a specific time by which it has to be done.

Format: `deadline TASK_DESCRIPTION /by DD-MM-YYYY HH:MM`
- Time is given in 24-hour format.

Example of usage:

`deadline read book by 19-09-2023 18:00`

### Adding an event: `event`

Adds a task with specific start and end times.

Format: `event TASK_DESCRIPTION /from DD-MM-YYYY HH:MM /to DD-MM-YYYY HH:MM`
- Time is given in 24-hour format.

Example of usage:

`event read book /from 20-12-2023 12:00 /to 20-12-2023 15:00`

### Viewing list of tasks: `list`

Displays the list of tasks.

Format: `list`

### Deleting tasks from list: `delete`

Deletes the task at the specified index in the list.

Format: `delete INDEX`
- `INDEX` has to be greater than 0.
- `INDEX` has to be smaller than the last number in the list.

Example of usage:

`delete 3`

### Mark tasks: `mark`

Marks a task at the given index in the list as done.

Format: `mark INDEX`
- `INDEX` has to be greater than 0.
- `INDEX` has to be smaller than the last number in the list  .
- Tasks are marked as done with an "X".

Example of usage:

`mark 1`

### Locating a task: `find`
Searches for a task by description.
- Search is case-sensitive.
- The order of search terms matter.
- A match will happen if the search term matches any substring exactly.

### Exit program: `bye`

Closes the chatbot.

Format: `bye`
