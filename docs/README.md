# User Guide

Violet is your personal assistant chatbot to help you keep track of your tasks.

## Features 

### Add a Todo task

Add a Todo task to your task list.

### Add a Deadline task

Add a Deadline task to your task list.

### Add an Event task

Add an event task to your task list.

### List all tasks

List all the tasks in your list.

### Mark a task

Mark a task in your task list as done.

### Unmark a task

Mark a task in your task list as undone.

### Delete a task

Delete a task in your task list.

### Find a task

Find a task in your task list.

### Sort the task list

Sort the tasks in your task list by datetime.

### Exit program

Exit the program.

## Usage

### `todo` - Add a Todo Task

Adds a Todo task to the task list.

Format: `todo DESCRIPTION`

Example of usage: 

`todo buy bread`

Expected outcome:

Todo task is successfully added to the task list.

```
[T][ ] buy bread
```

### `deadline` - Add a Deadline Task

Adds a Deadline task to the task list.

Format: `deadline DESCRIPTION /by yyyy-MM-dd HHmm`

Example of usage:

`deadline math hw /by 2023-09-20 2359`

Expected outcome:

Deadline task is successfully added to the task list.

```
[D][ ] math hw (by: Sep 20 2023 2359)
```

### `event` - Add an Event Task

Adds an Event task to the task list.

Format: `event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm`

Example of usage:

`event proj meeting /from 2023-09-19 2000 /to 2023-09-19 2200`

Expected outcome:

Event task is successfully added to the task list.

```
[E][ ] proj meeting (from: Sep 19 2023 2000 to: Sep 19 2023 2200)
```

### `list` - List all tasks

Lists all tasks in the task list.

Format: `list`

Expected outcome:

All tasks are successfully listed.

```
[T][ ] buy bread
[D][ ] math hw (by: Sep 20 2023 2359)
[E][ ] proj meeting (from: Sep 19 2023 2000 to: Sep 19 2023 2200)
```

### `mark` - Mark a task

Marks a task in the task list as done.

Format: `mark INDEX`

Example of usage:

`mark 1`

Expected outcome:

The task is marked successfully.

```
[T][X] buy bread
```

### `unmark` - Unmark a task

Marks a task in the task list as undone.

Format: `unmark INDEX`

Example of usage:

`unmark 1`

Expected outcome:

The task is unmarked successfully.

```
[T][ ] buy bread
```

### `delete` - Delete a task

Deletes a task in the task list.

Format: `delete INDEX`

Example of usage:

`delete 1`

Expected outcome:

The task is deleted successfully.

### `find` - Find a task

Finds a task in the task list.

Format: `find KEYWORD`

Example of usage:

`find buy`

Expected outcome:

All tasks containing the keyword are successfully listed.

```
[T][ ] buy bread
[T][ ] buy apples
```

### `sort` - Sort the task list

Sorts the tasks in the task list by datetime.

Format: `sort`

Expected outcome:

The task list is successfully sorted according to their datetime.

```
[E][ ] proj meeting (from: Sep 19 2023 2000 to: Sep 19 2023 2200)
[D][ ] math hw (by: Sep 20 2023 2359)
[T][ ] buy bread
```

### `bye` - Exit the program

Exits the program.

Format: `bye`

Expected outcome:

Program exits successfully.
