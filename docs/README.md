# Iris User Guide

## Features 

## Adding Tasks

Different types of tasks can be added.

### Usage

### `todo` - Adds a todo task. 


Example of usage: 

`todo (name of task)`

Expected outcome:

Adds a task with the given name.

```
added: [T][] name
```
`todo (name of task)`

Expected outcome:

Adds a todo task with the given name.

```
added: [T][] name
```
<hr />

### `deadline` - Adds a deadline task.


Example of usage:

`deadline (name of task)/by (year-month-day) (time)`

Expected outcome:

Adds a deadline task with the given name.

```
added: [D][] name (by:date,time)
```

<hr />

### `event` - Adds a event task.

Example of usage:

`event (name of task)/from (start date) (time)/to (end date) (time)`

Expected outcome:

Adds a deadline task with the given name.

```
added: [E][ ] name (from: date,time to: date,time)
```
<hr />

## Listing Tasks
### `list` - List the tasks saved

Example of usage:

`list`

Expected outcome:

Lists out all the tasks saved in the bot

```
1. Task 1
2. Task 2
...
There are n tasks in the list.
```
<hr />

## Deleting Tasks
### `delete` - Delete a task from the list

Example of usage:

`delete 1`

Expected outcome:

Deletes the task at the given index

```
I've removed this task:
[T][] name
```
## Marking Tasks As Done
### `mark` - Marks a task as done
Marked tasks are ticked with an X in their entry.

Example of usage:

`mark 1`

Expected outcome:

Marks the task at the given index as done

```
Nice! I've marked this task as done:
[T][X] name
```

### `unmark` - Unmarks a task as done
Example of usage:

`unmark 1`

Expected outcome:

Unmarks the task at the given index

```
OK, I've marked this task as not done yet:
[T][] name
```

<hr />

## Exiting The Bot

### 'bye' - Exits the bot
Example of usage:

'bye'

Expected outcome:

Exits the bot

```
Bye, hope to see you again soon!
```
