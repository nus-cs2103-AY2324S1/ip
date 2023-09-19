# Nicole the Chatbot
# User Guide

## Features 

### Adding a to-do task: `todo`

Adding a to-do task to the task list.
Format: `todo TASK`

Examples:
- `todo read book`
- `todo do homework`

### Adding a task with deadline: `deadline` 

Adding a task with deadline to the task list.
Format: `deadline TASK /by TIME`

- `TIME` should be in the format `yyyy-mm-dd`

Examples:
- `deadline finish assignment 1 /by 2023-10-15 `
- `deadline project /by 2023-09-20`

### Adding an event: `event`

Adding a event task to the task list.
Format: `event TASK /from START_TIME /to END_TIME`

- `START_TIME` and `END_TIME` should be in the format `yyyy-mm-dd`

Examples:
- `event hackathon /from 2023-10-20 /to 2023-10-21`
- `event sleep /from 2023-05-25 /to 2023-05-29`

### Mark a completed task: `mark`

Mark a selected task as completed.
Format: `mark INDEX`

- Mark the task at the specified `INDEX` as completed. The index refers to the index number shown in the task list. 
The index must be a positive integer 1, 2, 3, …

Example:
- `mark 2`

### Unmark an uncompleted task: `unmark`
Mark a selected task as completed.
Format: `unmark INDEX`

- Mark the task at the specified `INDEX` as uncompleted. The index refers to the index number shown in the task list. 
The index must be a positive integer 1, 2, 3, …​

Example:
- `unmark 2`

### Find a task: `find`
List down tasks with the same keyword.

Format: `find TASK`

Examples:
- `find read`
- `find homework`

### Edit a task: `edit`
Edit the description of a task in the list.
Format: `edit INDEX TASK`
- Edits the task at the specified `INDEX`The index refers to the index number shown in the displayed person list. 
The index must be a positive integer 1, 2, 3, ...
- Description of the task will be updated to `TASK` instead

Examples:
- `edit 2 write`
- `edit 3 do work`

### Delete a task: `delete`
Deletes a selected task.
Format: `delete INDEX`

- Deletes the task at the specified `INDEX`. The index refers to the index number shown in the task list.
  The index must be a positive integer 1, 2, 3, …

Example:
- `delete 2`
