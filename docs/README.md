# User Guide

## Features 

### Listing Tasks: `list`
Shows a list of all recorded tasks

Format: `list`

### Marking Tasks: `mark`

Format: `mark INDEX`

- Marks the task at the specified INDEX. The index must be a positive integer, and be within the range of recorded tasks

Examples: `mark 2`

### Unmarking Tasks: `mark`

Format: `mark INDEX`

- Unmarks the task at the specified INDEX. The index must be a positive integer, and be within the range of recorded tasks

Examples: `unmark 2`

### Adding a todo task: `todo`

Format: `todo TASK`

- Creates a todo task where TASK gives the description of the task

Examples: `todo borrow a book`

### Adding a deadline task: `deadline`

Format: `deadline TASK /by DATE`

- Creates a deadline task where TASK gives the description of the task
- DATE has to be in the format dd/mm/yyyy hhmm

Examples: `deadline eat fish /by 22/03/2021 0900`

### Adding a deadline task: `deadline`

Format: `event TASK /from START /to END`

- Creates a deadline task where TASK gives the description of the task
- START and END has to be in the format dd/mm/yyyy hhmm
- END has to be a later datetime than START

Examples: `event spend time with dog /from 22/03/2021 0900 /to 22/04/2021 0900`

### Deleting a task: `delete`

Format: `delete INDEX`

- Deletes the task at the specified INDEX. The index must be a positive integer, and be within the range of 
recorded tasks

Examples: `delete 2`

### Deleting a task: `find`

Format: `find INDEX`

- Returns a list of tasks with the associated keyword. The keyword
is case-insensitive

Examples: `find chicken`

### Ending the chat: `bye`

Format: `bye`

- Ends the chat



