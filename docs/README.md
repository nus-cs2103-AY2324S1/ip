# Sana User Guide

## Features 
### List tasks

Lists all of user's tasks.

### Add Todo

Adds a task of type todo.

### Add Event

Adds a task of type event which has a starting date and ending date.

### Add Deadline

Adds a task which has a deadline.

### Delete task

Deletes a task from the tasks list.

### Mark task as done

Marks a task as done.

### Mark task as not done yet

Marks a task as not done yet.

### Find task by description

Finds a task that matches the given description.

## Usage

### `todo` - adds a task of type todo 

Adds a task with no deadline (todo) to the list of tasks.

Example of usage: 

`todo DESCRIPTION`

Expected outcome:

adds a todo task with the given description to the list.

Description of the outcome.

```
[T][] TASK DESCRIPTION
```

### `deadline` - adds a task with deadline

Adds a task with deadline (todo) to the list of tasks.

Example of usage:

`deadline DESCRIPTION /by YYYY-MM-DD`

Expected outcome:

adds a task with the given description and deadline.

Description of the outcome.

```
[D][] TASK DESCRIPTION (by: MM DD YYYY)
```

### `event` - adds an event

Adds an event with a start and end date to the list of tasks.

Example of usage:

`event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`

Expected outcome:

adds an event with the given description and starting and ending dates.

Description of the outcome.

```
[E][] DESCRIPTION (from: MM DD YYYY to: MM DD YYYY)
```
### `list` - lists all tasks added

Example of usage:

`list`

Expected outcome:

lists all tasks added along with their status.

Description of the outcome.

```
Here are the tasks in your list:
1. [T][] TASK DESCRIPTION
2. [D][] TASK DESCRIPTION (by: MM DD YYYY)
3. [E][] DESCRIPTION (from: MM DD YYYY to: MM DD YYYY)
```

### `delete` - delete a task from the list

Example of usage:

`delete INDEX`

Expected outcome:

delete task at the given index from the list.

Description of the outcome.

```
Noted I've removed this task:
[T][] TASK DESCRIPTION
Now you have X tasks in the list
```

### `mark` - marks a task as done

Example of usage:

`mark INDEX`

Expected outcome:

marks a task at the given index as done.

Description of the outcome.

```
Nice! I've marked this task as done: 
[T][X] TASK DESCRIPTION
```

### `unmark` - marks a task as not done yet

Example of usage:

`unmark INDEX`

Expected outcome:

marks a task at the given index as not done yet.

Description of the outcome.

```
OK, I've marked this task as not done yet:
[T][X] TASK DESCRIPTION
```

### `find` - finds all tasks with the given description

Example of usage:

`find DESCRIPTION`

Expected outcome:

finds all tasks whose descriptions match the given DESCRIPTION

Description of the outcome.

```
Here are the matching tasks in your list:
1. ...
2. ...
```