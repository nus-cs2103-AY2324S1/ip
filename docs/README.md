# OrangesBot
# User Guide

## Features

### Adding Tasks

Choose from `todo`, `deadline` or `event` to add a task to the list.

### Deleting Tasks

Use `delete` with the index of the task to delete tasks from the list.

### Marking Tasks as Done

Use `mark` with the index of the task to mark tasks as done.

### Finding tasks from keywords

Use `find` to find tasks that contain the keyword.

### Listing Tasks
Use `list` to list all tasks in the list with date included.

### Adding places 
Use `place` to add a place to a separate list.

### Listing places
Use `listplaces` to list all places in the list.

## Usage

### `todo <event name>` - Creates a todo task

Creates a todo task with the given event name and adds it to the list.

Example of usage: 

`todo read book`

Expected outcome:

You will be notified that the task has been added to the list, showing you the new task you created.

```
Got it. I've added this task:
[T][ ] read book
```
### `deadline <event name> /by <date>` - Creates a deadline task

Creates a deadline task with the given event name and deadline and adds it to the list.

Example of usage:

`deadline read book /by 2023-09-22`

Expected outcome:

You will be notified that the task has been added to the list, showing you the new task you created.

```
Got it. I've added this task:
[D][] read book (by: Sep 22 2023)
```
### `event <event name> /from <date> /to <date>` - Creates a event task

Creates a event task with the given event name and duration and adds it to the list.

Example of usage:

`event book festival /from 2023-09-22 /to 2023-09-25`

Expected outcome:

You will be notified that the task has been added to the list, showing you the new task you created.

```
Got it. I've added this task:
[E][] book festival (from: Sep 22 2023 to: Sep 25 2023)
```
### `delete <index>` - Deletes the specified task

Deletes the task at the specified index from the list.

Example of usage:

`delete 1`

Expected outcome:

You will be notified that the task has been deleted from the list, showing you the task you deleted, 
and the number of tasks remaining.

```
Noted. I've removed this task:
[T][ ] read book
Now you have 2 task(s) in the list
```
### `find <keyword>` - Finds the matching tasks based on keyword

Given a keyword, finds all tasks that contain the keyword and displays them.

Example of usage:

`find read`

Expected outcome:

You will be shown all tasks that contain the keyword, with their index.

```
Here are the matching tasks in your list:
1. [D][] read book (by: Sep 22 2023)
3. [T][] read book
```
### `list` - Lists out all tasks

Given a keyword, finds all tasks that contain the keyword and displays them.

Example of usage:

`list`

Expected outcome:

You will be shown all tasks that you have put into OrangesBot.

```
1. [D][] read book (by: Sep 22 2023)
2. [E][] book festival (from: Sep 22 2023 to: Sep 25 2023)
3. [T][] read book
```
### `mark <index>` - Marks a specified task as done

Marks the task at the specified index as done.

Example of usage:

`mark 1`

Expected outcome:

You will be notified that the task has been marked as done, showing you the task you marked as done.

```
Nice! I've marked this task as done:
[D][X] read book (by: Sep 22 2023)
```
### `unmark <index>` - marks a specified task as not done

Marks the task at the specified index as not done.

Example of usage:

`unmark 1`

Expected outcome:

You will be notified that the task has been marked as not done, showing you the task you marked as not done.

```
OK, I've marked this task as not done yet:
[D][] read book (by: Sep 22 2023)
```
### `place <place name>` - Adds a place to the list

Adds a place to the list of places.

Example of usage:

`place library /type study /desc at CLB`

Expected outcome:

You will be notified if this is a new location added. If it already exists, the description will be updated.

```
place library /type study /desc at CLB
Nice! This is a new location. Adding it now.
```
```
place library /type study /desc located at CLB
This study place exists! Description has been updated.
```

### `listplaces` - Lists out all places

Lists out all places that you have added to OrangesBot.

Example of usage:

`listplaces`

Expected outcome:

You will be shown all places that you have added to OrangesBot.

```
library: located at CLB
```
### `bye` - Exits OrangesBot

Ends the conversation with OrangesBot

Example of usage:

`bye`

Expected outcome:

GUI will close.