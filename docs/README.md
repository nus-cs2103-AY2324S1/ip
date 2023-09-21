# User Guide
Mimi is a simple task manager that helps you keep track of your tasks.

### Notes about formatting
Parameters that need to be entered by the user is in ALL_CAPS. 
  e.g `todo TASK_NAME` can be used as `todo homework` where homework is supplied as a parameter.

Errors in formatting such as wrong types (e.g string where a number is expected), missing parameters, will result in a message displayed prompting you to try again.

Commands such as `list` and `remind` which do not expect any parameters will ignore additional arguments supplied. E.g `list this_is_redundant` will be the same as `list`.

All tasks will be displayed in the following format: `[TASK_CODE][STATUS] TASK_DESCRIPTION`
  TASK_CODE will be one of the following:
  - T: Todo
  - D: Deadine
  - E: Event

  STATUS will be one of the following:
  - X: Task is marked as completed
  -   : Task is not completed

  TASK_DESCRIPTION shows a description of the task such as its name, and deadline(s).


### List of all features
- todo
- deadline
- event
- list
- mark
- unmark
- delete
- remind
- find
- bye

### Add a Todo: `todo`

Adds a task to be done using a given TASK_NAME.

# Usages:
- Format: `todo TASK_NAME`
- Example: `todo homework`

### Add a Deadline: `deadline`

Adds a task to be done using a given TASK_NAME and a DEADLINE.

# Notes: the format for the deadline must be given in DD/MM/YYYY HHmm exactly. Any deviation from this will prompt you to try again.
**You will also not be allowed to add a deadline that is earlier than the current time. This will prompt you to try again.**

## Usages:
- Format: `deadline TASK_NAME /by DEADLINE
- Example: `deadline homework /by 23/09/2023 0000`

### Add a Event: `event`

Adds a event using a given TASK_NAME, a START_TIME and an END_TIME.

# Notes: the format for the start and end time must be given in DD/MM/YYYY HHmm exactly. Any deviation from this will prompt you to try again.
**You will also not be allowed to add a start time that is earlier than the current time. You will also not be allowed to add an end time that is earlier than the start time. These will also prompt you to try again.**

## Usages:
- Format: `event TASK_NAME /from START_TIME /to END_TIME`
- Example: `event play ball /from 23/09/2023 1500 /to 23/09/2023 1700`

### List all tasks: `list`

Lists all the tasks that you have previously added. This command takes no additional parameters.

## Usages:
- Example: `list`

### Mark a task: `mark`

Marks a task as done using a given INDEX that is a number. You can access the index using `list`.

## Usages:
- Format: `mark INDEX`
- Example: `mark 1`

### Mark as task as not done: `unmark`

Marks a task as not done using a given INDEX that is a number. You can access the index using `list`.

## Usages:
- Format: `unmark INDEX`
- Example: `unmark 1`

### Delete a task: `delete`

Deletes a task using a given INDEX that is a number. **This action is irreversible**

## Usages:
- Format: `delete INDEX`
- Example: `delete 1`

### Send a reminder: `remind`

Gives a reminder for tasks that are due in less than a week. Also gives a reminder on tasks that are currently overdue. This command takes no additional parameters.

**Note: Upon initialisation, the user will be sent a reminder automatically. However you can still manually call for a reminder using `remind`**

## Usages:
- Example: `remind`

### Find tasks: `find`

Finds all tasks that match a given PROMPT. 

## Usages:
- Format: `find PROMPT`
- Example: `find homework`

### Exit the program: `bye`

Exits the programme after 3 seconds.

## Usages:
Example: `bye`


