# User Guide

## Features

### Add Deadline Task: `deadline`

Adds a task with a deadline into the task list.

### Add Event Task: `event`

Adds a task with a start date and end date into the task list.

### Add ToDo Event: `todo`

Adds a task into the task list.

### Delete Task: `delete`

Delete a task from the task list.

### Find Task: `find`

Find a task from the task list.

### Mark Task as completed: `mark`

Mark a task in the task list as completed.

### Mark Task as incomplete: `unmark`

Mark a task in the task list as incomplete.

### View List: `list`

List every task in the task list.


## Usage

### `deadline` - Creates a task with a deadline

Creates a task with a given deadline and adds it into the task list.

Format: `deadline DESCRIPTION /by: DATE_TIME`
- DESCRIPTION: Description of the deadline
- DATE_TIME: The date and time the task has to be done by in format "YYYY-MM-DDTHH:MM"

Example:

- `deadline CS2103T iP submission /by: 2023-09-22T23:59`

Expected outcome:

```
Got it. I've added this task:
[D][ ] DESCRIPTION (by: DATE_TIME)
Now you have TOTAL_TASK tasks in the list.
```

### `event` - Creates an event

Creates a task with a given start date-time and end date-time, and adds it into the task list.

Format: `event DESCRIPTION /from: START_DATE_TIME /to: END_DATE_TIME`
- DESCRIPTION: Description of the event
- START_DATE_TIME: The start date and time the task has to be done by in format "YYYY-MM-DDTHH:MM"
- END_DATE_TIME: The end date and time the task has to be done by in format "YYYY-MM-DDTHH:MM"

Example:

- `event CS2103T Finals /from: 2023-12-01T09:00 /to: 2023-12-01T11:00`

Expected outcome:

```
Got it. I've added this task:
[E][ ] DESCRIPTION (from: START_DATE_TIME to: END_DATE_TIME)
Now you have TOTAL_TASK tasks in the list.
```

### `todo` - Creates a task to be done

Creates a task and adds it into the task list.

Format: `todo DESCRIPTION`
- DESCRIPTION: Description of the event

Example:

- `todo CS2103T: tP UG Draft 1`

Expected outcome:

```
Got it. I've added this task:
[T][ ] DESCRIPTION
Now you have TOTAL_TASK tasks in the list.
```

### `delete` - Deletes a task

Deletes a task from the task list.

Format: `delete TASK_INDEX`
- TASK_INDEX: Index of the task in the task list

Example:

- `delete 5`

Expected outcome:

```
Noted. I've removed this task:
[ TASK_TYPE ][ TASK_STATUS ] TASK_DESCRIPTION [DATE_TIME]...
Now you have TOTAL_TASK tasks in the list.
```

### `find` - Deletes a task

Finds task(s) in the task list.

Format: `find QUERY_STRING`
- QUERY_STRING: The string to be searched

Example:

- `find CS2103T`

Expected outcome:

```
Here are the matching tasks in your list:
1.[ TASK_TYPE_1 ][ TASK_STATUS_2 ] TASK_DESCRIPTION_1 [DATE_TIME_1]...
2.[ TASK_TYPE_1 ][ TASK_STATUS_2 ] TASK_DESCRIPTION_2 [DATE_TIME_2]...
```

### `mark` - Marks a task as completed

Marks the task as completed in the task list.

Format: `mark TASK_INDEX`
- TASK_INDEX: Index of the task in the task list

Example:

- `mark 5`

Expected outcome:

```
Noted. I've marked this task as done:
[ TASK_TYPE ][ TASK_STATUS ] TASK_DESCRIPTION [DATE_TIME]...
Now you have TOTAL_TASK tasks in the list.
```

### `unmark` - Marks a task as completed

Marks the task as incomplete in the task list.

Format: `unmark TASK_INDEX`
- TASK_INDEX: Index of the task in the task list

Example:

- `unmark 5`

Expected outcome:

```
Noted. I've marked this task as not done yet:
[ TASK_TYPE ][ TASK_STATUS ] TASK_DESCRIPTION [DATE_TIME]...
Now you have TOTAL_TASK tasks in the list.
```

### `list` - Marks a task as completed

Marks the task as incomplete in the task list.

Format: `list`

Expected outcome:

```
1.[ TASK_TYPE_1 ][ TASK_STATUS_2 ] TASK_DESCRIPTION_1 [DATE_TIME_1]...
2.[ TASK_TYPE_1 ][ TASK_STATUS_2 ] TASK_DESCRIPTION_2 [DATE_TIME_2]...
```
