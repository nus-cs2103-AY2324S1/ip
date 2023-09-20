# User Guide

## Features 

### Adding a task

Adds a task to the list.

Format: `TASKTYPE TITLE [/by DATE] [/from DATE] [/to DATE]`
- todo does not require any date
- deadline requires only a by date
- event requires a from and to date
- date must be written in a yyyy-mm-dd format

Example: 
- `todo read book`
- `deadline write essay /by 2023-12-31`
- `event winter break /from 2023-12-01 /to 2023-12-31`

Expected output if todo is added successfully:
```
A new task has been added!
To Do  -> [] read book
```

### Adding a note: `note`

Adds a note to the list.

Format: `note DESCRIPTION`
- description cannot be empty

Example:
- `note read book`
- `note return book`


### Viewing all tasks and notes: `list`

Shows a list of all tasks and notes.

Format: `list`


### Complete a task: `mark`

Mark a task as completed.

Format: `mark INDEX`

Example:
- `mark 1`
- `mark 2`


### Unmark a task: `unmark`

Mark a task as uncompleted.

Format: `unmark INDEX`

Example:
- `unmark 1`
- `unmark 2`


### Find a task: `find`

Search for tasks based on their keyword.

Format: `find KEYWORD`

Example:
- `find book`
- `find Dec`

Expected output for `find book`:
```
Here's the matching tasks!
1: To Do -> [] return book
2. Deadline -> [] read book By: Dec 31 2023
```


### Delete a task or note

Delete tasks or notes.

Format: `deletet INDEX` for tasks
Format: `deleten INDEX` for notes

Example:
- `deletet 1`
- `deleten 2`

Expected output if todo deletion is successful:
```
To Do  -> [] 1 has been deleted!
```



### Save your tasks and notes: `bye`

Saves the list of tasks and notes locally.

Format: `bye`

Expected output if tasks and notes are saved successfully:
```
Tasks saved successfully
Goodbye. Hope to see you soon!
```

