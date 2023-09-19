# JermBot User Guide

## Features 

### Interactive Chatbot

The chatbot listens to your commands and carries out the corresponding actions.

### Multiple Types of Tasks

Tasks can be classified as Todos, Deadlines and Events, each with different properties. 

## Usage

### `list` - List all tasks

Lists out all the tasks in the task list.

Example of usage: 

`list`

Expected outcome:

A list of all tasks.

```
1. [E][ ] sleep (from: now to: later)
2. [D][ ] cook (by: tonight)
3. [T][ ] clean
```

### `todo` - Add Todo

Adds a Todo to the list.

Example of usage:

`todo clean`

Expected outcome:

Adds the Todo to the list.

```
Haha now you have this task to do:
  [T][ ] clean
In total you have 1 things to do.
```

### `deadline` - Add Deadline

Adds a Deadline to the list.

Example of usage:

`deadline cook /by tonight`

Expected outcome:

Adds the Deadline to the list.

```
Haha now you have this task to do:
  [D][ ] cook (by: tonight)
In total you have 1 things to do.
```

### `event` - Add Event

Adds an Event to the list.

Example of usage:

`event sleep /from now /to tomorrow`

Expected outcome:

Adds the Event to the list.

```
Haha now you have this task to do:
  [E][ ] sleep (from: now to: tomorrow)
In total you have 1 things to do.
```

### `mark` - Mark Task

Marks a task as done.

Example of usage:

`mark 1`

Expected outcome:

Marks the 1st task in the list as done.

```
Ok good job lor you finished this task:
  [T][X] clean
```

### `unmark` - Unmark Task

Marks a task as undone.

Example of usage:

`umark 1`

Expected outcome:

Marks the 1st task in the list as undone.

```
Wah why you never do this task:
  [T][ ] clean
```

### `find` - Find Task

Find tasks that start with the substring passed in the command.

Example of usage:

`find cle`

Expected outcome:

Displays the list of all tasks that start with the given substring.

```
1. [T][X] clean
```

### `bye` - Close JermBot

Stops operation of the chatbot and saves task list into storage.

Example of usage:

`bye`

Expected outcome:

Displays goodbye greeting.

```
Good riddance.
```