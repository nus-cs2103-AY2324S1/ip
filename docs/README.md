# Whelmed User Guide

Whelmed is a thoroughly average chatbot, with only the necessary functionality.

Why be under or over whelming, when you can just be whelmed?

## Features

### Minimalistic Task Management

Add, delete, list tasks from 3 distinct categories,
with task marking / unmarking, schedule tracking, and search features.

### Saving and Archiving

The tasks persist between sessions, with the option to archive and start afresh.
Archived tasks are stored in an easily accessible location, in a human-readable format.

## Usage

### `todo` - Add a todo task

Adds a todo task to the task list.

Example of usage: 

`todo finish iP`

Expected outcome:

A todo task with the input description is added
to the task list, with a confirmation message.

```
 Got it. I've added this task:
  [T][] finish iP
 Now you have 1 tasks in the list.
```

### `deadline` - Add a deadline task

Adds a deadline task to the task list.

Example of usage:

`deadline finish iP /by 22/09/2023 2359`

Expected outcome:

A deadline task with the input description is added
to the task list, with a confirmation message.

```
 Got it. I've added this task:
  [D][] finish iP (by: Sep 22 2023 23:59)
 Now you have 2 tasks in the list.
```

### `event` - Add an event task

Adds an event task to the task list.

Example of usage:

`event tP /from 22/9/2023 2359 /to 22/10/2023 2359`

Expected outcome:

A deadline task with the input description is added
to the task list, with a confirmation message.

```
 Got it. I've added this task:
  [E][] tP (from: Sep 22 2023 23:59 to: Oct 22 2023 23:59)
 Now you have 3 tasks in the list.
```

### `list` - List all tasks

Lists all tasks in the task list.

Example of usage:

`list`

Expected outcome:

All event displayed, enumerated in order of addition to the task list.

```
 1. [T][] finish iP
 2. [D][] finish iP (by: Sep 22 2023 23:59)
 3. [E][] tP (from: Sep 22 2023 23:59 to: Oct 22 2023 23:59)
```

### `mark` - Mark a specified task

Marks the specified task, using the task number

Example of usage:

`mark 1`

Expected outcome:

Specified task is marked.

```
 Fine. Marked this task as done, better not change your mind:
  1. [T][X] finish iP
```

### `unmark` - Unmark a specified task

Unmarks the specified task, using the task number

Example of usage:

`unmark 1`

Expected outcome:

Specified task is unmarked.

```
 Well, you changed your mind :(. Just this once:
  1. [T][] finish iP
```

### `delete` - Delete a specified task

Deletes the specified task, using the task number

Example of usage:

`delete 1`

Expected outcome:

Specified task is deleted.

```
 Noted. I've removed this task:
  1. [T][] finish iP
 Now you have 2 tasks in the list.
```

### `find` - Search for a specific task

Searches for tasks with the matching input description.

Example of usage:

`find tP`

Expected outcome:

Matching tasks are enumerated and displayed.

```
 Check out the matches:
  1. [E][] tP (from: Sep 22 2023 23:59 to: Oct 22 2023 23:59)
```

### `schedule` - Search for tasks on a specific date

Searches for tasks with the specified date.

Example of usage:

`schedule /on 22/9/2023`

Expected outcome:

Matching tasks are enumerated and displayed.

```
 Tasks on Sep 23 2023 0000:
  1. [D][] finish iP (by: Sep 22 2023 23:59)
  2. [E][] tP (from: Sep 22 2023 23:59 to: Oct 22 2023 23:59)
```

### `archive` - Save the current task list and start afresh

Saves the current task list and starts afresh.

Example of usage:

`archive`

Expected outcome:

A fresh task list for the user.

```
  Tasks archived, you're now working with a fresh list.
```

### `bye` - Save the current task list and exit the chatbot

Saves the current task list and exits the chatbot.

Example of usage:

`bye`

Expected outcome:

The chatbot closes.
