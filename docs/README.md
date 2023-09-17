# Dot's User Guide

## Unique Features

### Adding of 3 types of Tasks–Todo, Event and Deadline

A [todo](#creating-a-todo-todo) is a simple task with a description, you can use
a [deadline](#creating-a-deadline-deadline) task if you prefer setting a deadline. \
You can specify an [event](#creating-an-event-event) with start and end date!

### Auto-datetime

When adding [deadlines](#creating-a-deadline-deadline) or [events](#creating-an-event-event),
you can choose to use the auto datetime formatter. Instead of `deadline_desc`, `start`, or
`end`, write a datetime in the following format: `dd/MM/YYYY hhmm` e.g. `17/09/2023 2300`.

It will be translated to `Sep 17 2023 11PM`.

> [!NOTE]
> This is not the same format as the `whatsgoingon dd/MM/yyyy` command.

### Undo commands that changes tasks

You are able to undo the latest command if they are one of the following:

- `todo`, `deadline`, `event`
- `mark`, `unmark`
- `delete`

An undo cannot be undone, and an undo cannot be performed twice.

---
> [!NOTE] Notes about the usage formats
> - Words surrounded by angle brackets `<` and `>` represent placeholders, which you can input a value into. Some
    > placeholders are specified like `<deadline_desc:dd/MM/yyyy hhmm>` which means you can use [this datetime format]
    > (#auto-datetime).
> - It is not compulsory to specify in the required datetime format, you can input any other text
    > if you like.
> - Some placeholders are specified like `<index_number>` which indicates that a task's index number
    > is expected.

A task is defined as either a `todo`, `event`, or `deadline`.

## Usage

### Accessing the help menu: `help`

> Format: `help`

### Creating a todo: `todo`

> Format: `todo <description>` \
> Example: `todo assignment1`

### Creating a deadline: `deadline`

> Format: `deadline <description> /by <deadline_desc:dd/MM/yyyy hhmm>` \
> Example: `deadline assignment 2 /by 20/09/2023 1800`

### Creating an event: `event`

> Format: `event <description> /from <start:dd/MM/YYYY hhmm> /to <end:dd/MM/yyyy hhmm>` \
> Example: `event recess week /from 25/09/2023 0000 /to 01/10/2023 2359`

### Listing all tasks: `list` or `ls`

> Format: `undo` \
> This shows a list of all tasks and their index number. **This index number must be used in
> `mark`, `unmark`, `delete`.**

### Marking a task as done: `mark`

> Format: `mark <index number>` \
> Example: `mark 1` (given that at least 1 task exist)

### Marking a task as **_not_** done: `unmark`

> Format: `unmark <index number>` \
> Example: `mark 1` (given that at least 1 task exist)

### Deleting a task: `delete`

> Format: `delete <index number>` \
> Example `delete 1` (given that at least 1 task exist)

### Find the deadlines or events falling on a data: `whatsgoingon`

> Format: `delete <dd/MM/yyyy>` \
> Example `whatsgoingon 25/09/2023`

### Find the tasks matching some text: `find`

> Format: `find <query>` \
> Example: `find assignment`

### Undo a command: `undo`

The list of commands that can be undone and restrictions can be found [here](#undo-commands-that-changes-tasks).

> Format: `undo`

---

# FAQ

**Q**: The text size is too small, how do I increase the font size? \
**A**: The window is resizeable. You can choose to drag from the edges of the window, or use the maximize button
located at
the top left of the window.