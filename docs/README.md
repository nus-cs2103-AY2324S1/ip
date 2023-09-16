# Trash Gremlin Caelus

A chatbot that can help keep track of a list of tasks. Note that all indexes begin at 1.

## Features 

### Add Task

Add tasks of different natures to the task list.

### Mark Task

Mark tasks as done or not done.

### Delete Task

Remove tasks from the task list.

### Find Task

Find tasks with names that match the search term.

### List Tasks

List all tasks in the task list.

## Usage

### `todo` - Add to-do

Adds a task with no deadline or time window to the list.

Example of usage: 

`todo Make bed`

Expected outcome:

Adds a to-do to the list.

```
Added:
[T][ ] Make bed
```

### `deadline` - Add deadline

Adds a task with a deadline to the list. Deadline must not be before the current date.

Example of usage:

`deadline Make bed /by 2023-09-16`

Expected outcome:

Adds a deadline to the list.

```
Added:
[D][ ] Make bed (by: Sep 16 2023)
```

### `event` - Add event

Adds a task with a time window to the list.

Example of usage:

`event heck week /from 2023-09-18 /to 2023-09-25`

Expected outcome:

Adds an event to the list.

```
Added:
[E][ ] heck week (from: Sep 18 2023 to: Sep 25 2023)
```

### `mark` - Mark task as done

Marks the task at the given index as completed.

Example of usage:

`mark 1`

Expected outcome:

Task at position 1 of the list is marked as complete.

```
I'll mark this as done:
[T][X] Make bed
```

### `unmark` - Mark task as not done

Marks the task at the given index as not completed.

Example of usage:

`unmark 1`

Expected outcome:

Task at position 1 of the list is marked as not complete.

```
I'll mark this as not done:
[T][ ] Make bed
```

### `find` - Find task

Displays all tasks with the given search term in their names.

Example of usage:

`find bed`

Expected outcome:

Finds all tasks with the word "bed" in their names.

```
Here are the matching tasks in your list:
1. [T][ ] Make bed
2. [D][ ] Make bed (by: Sep 16 2023)
```

### `delete` - Delete task

Deletes the task at the given index from the list.

Example of usage:

`delete 1`

Expected outcome:

Deletes the task from the list.

```
I've removed this task:
[T][ ] Make bed
```

### `list` - List tasks

Lists all tasks currently in the list.

Example of usage:

`list`

Expected outcome:

Lists all tasks.

```
Here are the tasks in your list:
1. [T][ ] Make bed
2. [D][ ] Make bed (by: Sep 16 2023)
```

### `bye` - Stop the bot

Stop the bot and prevent it from taking any more commands.

Example of usage:

`bye`

Expected outcome:

Stops the bot and disables messages from being sent to the bot.

```
Bye. I'll be at the nearest trash can!
```
