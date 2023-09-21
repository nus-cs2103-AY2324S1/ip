# TILL
# User Guide

## Features 

### Task Management

Till allows you to efficiently manage your tasks. You can add various types of tasks, list them, mark them as done, unmark them, and even delete them from your task list.

### Task Types

Till supports different types of tasks:

To-Do: Simple tasks without a specific deadline.

Deadlines: Tasks that need to be completed by a specific date and time.

Events: Tasks that have a starting and ending date and time.

## Usage

### `todo` - Add a To-Do task

Adds a To-Do task to your task list.

Example of usage: 

`todo Buy groceries`

Expected outcome:

Till adds the To-Do task to your task list.

```
Added: [T][✘] Buy groceries
```


### `deadline` - Add a Deadline task

Adds a Deadline task to your task list with a specified deadline.

Example of usage:
`deadline Complete project /by 2023/12/31 23:59`

Expected outcome:

Till adds the Deadline task to your task list.

```
Added: [D][✘] Complete project (by: Dec 31 2023, 11:59 PM)
```

### `event` - Add an Event task

Adds an Event task to your task list with a specified starting and ending date and time.

Example of usage:
`event Birthday party /from 2023-09-30 18:00 /to 2023-09-30 22:00`

Expected outcome:

Till adds the Event task to your task list.

```Added: [E][✘] Birthday party (from: 2023-09-30 18:00 to: 2023-09-30 22:00)```

### `list` - List all tasks

Lists all the tasks in your task list.

Example of usage:
`list`

Expected outcome:

Till displays a list of all your tasks.

```
1. [T][✘] Buy groceries
2. [D][✘] Complete project (by: Dec 31 2023, 11:59 PM)
3. [E][✘] Birthday party (from: Sep 30 2023, 06:00 PM to: Sep 30 2023, 10:00 PM)
```
### `mark` - Mark a task as done

Marks a task as completed in your task list.

Example of usage:
`mark 1`

Expected outcome:

Till marks the specified task as done.

```Marked as done: [T][✓] Buy groceries```

### `unmark` - Unmark a task

Unmarks a previously marked task in your task list.

Example of usage:
`unmark 1`

Expected outcome:

Till unmarks the specified task.

```Unmarked: [T][✘] Buy groceries```

### `delete` - Delete a task

Deletes a task from your task list.

Example of usage:
`delete 1`

Expected outcome:

Till deletes the specified task.

```Deleted: [T][✘] Buy groceries```

### `find` - Find tasks

Finds tasks in your task list based on a keyword.

Example of usage:
`find project`

Expected outcome:

Till displays tasks that match the keyword.

```1. [D][✘] Complete project (by: Dec 31 2023, 11:59 PM)```

### `remind` - Set reminders

Set reminders for tasks that are due within a specified number of days.

Example of usage:
`remind 7`

Expected outcome:

Till displays tasks that are due within 7 days.

```
Here are the tasks due within the next 7 days:

1. [D][✘] Complete project (by: Dec 31 2023, 11:59 PM)
```





