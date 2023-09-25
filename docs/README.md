# User Guide
Potato is a chatbot who helps you keep track of your tasks.
## Features 
Words in `UPPER_CASE` are the parameters to be supplied by the user.
### Adding a todo task: todo

Adds a todo task to the list.

Format: todo DESCRIPTION

Example: todo read book

### Adding a deadline task: deadline

Adds a deadline task with a due date to the list.

Format: todo DESCRIPTION /by DUEDATE

NOTE: DUEDATE must be given in YYYY-MM-DD format.

Example: todo return book /by 2023-09-19

### Adding a event task: event

Adds a event task with a start and end date to the list.

Format: todo /DESCRIPTION /from START /to END

NOTE: START and END must be given in YYYY-MM-DD format.

Example: event library programme /from 2023-12-12 /to 2023-12-15

### Deleting a task: delete

Deletes the specified task from the list.

Format: delete INDEX

NOTE: INDEX must be an integer greater than 0.

Example: delete 1 deletes first task on the list.

### Finding a task: find

Finds all tasks that contains the given keyword.

Format: find KEYWORD

Example: find book returns 'read book' and 'return book'

### Listing tasks: list

Lists all tasks in the list.

Format: list

### Marking a task: mark

Marks the specified task from the list as completed.

Format: mark INDEX

NOTE: INDEX must be an integer greater than 0.

Example: mark 1 marks first task on the list as completed.

### Unmarking a task: unmark

Unmarks the specified task from the list as not completed.

Format: unmark INDEX

NOTE: INDEX must be an integer greater than 0.

Example: unmark 1 unmarks first task on the list as not completed.

### Setting priority for a task: priority

Sets the priority of the specified task from the list as high, medium, or low.

Format: priority INDEX #LEVEL

NOTE: INDEX must be an integer greater than 0.

Example: priority 1 #high marks the priority of the first task on the list as high.

### Exiting: bye

Exits the programme.

Format: bye

### Saving the data

Data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.
