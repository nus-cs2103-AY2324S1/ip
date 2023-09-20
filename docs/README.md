# User Guide

## Features 

### Feature - Record your tasks of different kinds

You can record to-do tasks, tasks with a deadline, tasks / events with a start and end time and tasks with a fixed
duration with this application.

### Feature - Mark tasks

You can mark a task as completed or not.

### Feature - Find tasks by name

You can find tasks by partial name.

## Usage

### `list` - List all tasks

List all tasks stored in the bot.

Usage:

`list`

Expected outcome:

The bot will reply with a list containing all the tasks it stores.
```
Here are the tasks in your list:
1.[] some task
...
```

### `mark / unmark` - Mark or unmark a task as completed.

By default a newly added task is unmarked. You can mark any tasks as completed with `mark` command, or reverse the
effect of `mark` command by `unmark` command.

Usage:

`mark EXISTING_TASK_NAME`

`unmark EXISTING_TASK_NAME`

Example of usage:

`mark sometask`

`unmark sometask`

Expected outcome:

The bot will mark / unmark the task and reply with the result.
```
Nice! I've marked this task as done: [X] sometask
```

```
Nice! I've marked this task as not done yet: [] sometask
```

### `find` - Find all tasks with a partial name

Find all tasks that their names contains a given partial name.

Usage:

`find PARTIAL_NAME`

Example of usage:

`find tutorial`

Expected outcome:

The bot will reply with all the tasks that it can find with the given partial name.
```
Here are the matching tasks in your list:
[] CS2103T tutorial
[] CS2101 tutorial
```

### `bye` - Exit the application

Exit the application immediately without double confirmation.

Usage:

`bye`

Expected outcome:

The application will show a message box for goodbye then exit.
```
Bye, hope to see you soon!
```

### `delete` - Delete a task

Delete a task with its number from the task list replied by the command `list`. The task number is the number before
the task name in the task list.

Usage:

`delete TASK_NUMBER`

Example of usage:

`delete 1`

Expected outcome:

The bot will delete the task if the number provided can correctly refer to an existing task.
```
Noted, I've removed this task:
[] some task
Now you have 2 tasks in the list.
```

### `todo` - Add a to-do task

Add a to-do task with a given task name.

Usage: 

`todo NEW_TASK_NAME`

Example of usage:

`todo CS2103T iP`

Expected outcome:

The bot will tell you that this task has been added if no exception presents.

```
Got it. I've added this task:
 [T][] CS2103T iP
Now you have 1 task in the list.
```

### `deadline` - Add a deadline task

Add a deadline task with a given task name and a deadline time.

Usage:

`deadline NEW_TASK_NAME /by DEADLINE_TIME`

Example of usage:

`deadline Submit CS2103T iP final version /by 2023-09-22 23:59:00`

Expected outcome:

The bot will tell you that this task has been added if no exception presents.

```
Got it. I've added this task:
 [D][] Submit CS2103T iP final version (by: 2023-09-22 23:59:00)
Now you have 2 tasks in the list.
```

### `event` - Add an event

Add an event with a given task name, a start time and an end time. Note that the start time of the event can not behind
the end time.

Usage:

`event NEW_TASK_NAME /from START_TIME /to END_TIME`

Example of usage:

`event CS2103T Lecture /from 2023-09-22 16:00:00 /to 2023-09-22 18:00:00`

Expected outcome:

The bot will tell you that this event has been added if no exception presents.

```
Got it. I've added this task:
 [E][] CS2103T Lecture (from: 2023-09-22 16:00:00 to: 2023-09-22 18:00:00)
Now you have 3 tasks in the list.
```

### `duration` - Add a task with fixed duration

Add a task with fixed duration with a given task name and a duration in hours.

Usage:

`duration NEW_TASK_NAME /for NUMBER_OF_HOURS`

Example of usage:

`duration Read CS2103T textbook /for 1.5`

Expected outcome:

The bot will tell you that this task has been added if no exception presents.

```
Got it. I've added this task:
 [Duration][] Read CS2103T textbook (needs 1.5 hours)
Now you have 4 tasks in the list.
```

### Adding tasks without any kind
A command that doesn't begin with `list / mark / unmark / find / bye / delete / todo / deadline / event / duration`
will be treated as a direct task name for a task without any kind currently supported in the bot.

Usage:

`TASK_NAME`

Example of usage:

`Have fun after studying`

Expected outcome:

The bot will tell you that this task has been added if no exception presents.

```
Got it. I've added this task:
 [] Have fun after studying
Now you have 5 tasks in the list.
```

### General - Date-Time format

The bot can accept date-time (in `deadline` and `event` command) only in the following formats:

`yyyy-MM-dd` (If only date is specified, the time will be `00:00:00` by default.)

`yyyy-MM-dd HH:mm:ss`

Examples:

```
2023-09-20
2024-01-01
2023-11-12

2023-09-20 00:12:01
2023-09-20 10:01:03
```
