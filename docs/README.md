# Ken - User Guide

Ken is a task management bot designed to help you organize your tasks, set deadlines, and manage events efficiently.

## Features 

### Task Management

Manage your tasks effectively with features that allow you to add, delete, mark as done, and list all tasks.

### Searching for Tasks

Easily find specific tasks using keywords.

### Task Reminders

Get reminders for tasks that are due soon.

## Usage

### `todo` - Add a ToDo task

Adds a ToDo task to the list.

Example of usage:

`todo Read book`

Expected outcome:

A ToDo task with the given description is added.

```
Got it. I've added this task:
[T][ ] Read book
```

### `deadline` - Add a Deadline task

Adds a Deadline task with a due date.

Example of usage:

`deadline Submit assignment /by 2023-09-20`

Expected outcome:

A Deadline task with the given description and due date is added.

```
Got it. I've added this task:
[D][ ] Submit assignment (by: Sep 20 2023)
```

### `event` - Add an Event task

Adds an Event task with a start and end time.

Example of usage:

`event Team meeting /from 2023-09-19 /to 2023-09-20`

Expected outcome:

An Event task with the given description, start time, and end time is added.

```
Got it. I've added this task:
[E][ ] Team meeting (from: Sep 20 2023 to: Sep 20 2023)
```

### `list` - List all tasks

Lists all the tasks you currently have (can be used to search for a task at a specific date).

Example of usage:

`list`

Expected outcome:

A list of all tasks, along with their status.

```
1.[T][X] Read book
2.[D][ ] Submit assignment (by: Sep 20 2023)
```

Example of usage with date:

`list 2023-09-20`

Expected outcome:

A list of all tasks, along with their statuses, is displayed.

```
Here is what I found:

1.[D][ ] Submit assignment (by: Sep 20 2023)
```

### `mark` - Mark task as done

Marks a specified task as done.

Example of usage:

`mark 1`

Expected outcome:

The task at index 1 will be marked as done.

```
Nice! I've marked this task as done: 
[T][X] Read book
```

### `unmark` - Mark task as not done

Unmarks a specified task.

Example of usage:

`unmark 1`

Expected outcome:

The task at index 1 will be marked as not done.

```
I've unmarked this task: 
[T][ ] Read book
```

### `delete` - Delete a task

Deletes a task from the list based on its index.

Example of usage:

`delete 1`

Expected outcome:

The task at index 1 will be removed from the task list.

```
Noted. I've removed this task:
[T][X] Read book
```

### `find` - Find tasks by keyword

Finds and lists all tasks that contain the given keyword.

Example of usage:

`find book`

Expected outcome:

All tasks containing the keyword "book" will be listed.

```
Here are the tasks that contains the keyword: book
1.[T][X] Read book
```

### `reminders` - Show upcoming tasks

Shows tasks that are due soon.

Example of usage:

`reminders`

Expected outcome:

A list of tasks that are due soon is displayed.

```
Here are your upcoming tasks:
1.[D][ ] Submit assignment (by: 2023-09-20)
```
### `bye` - Exit the program

Exits the program safely, saving all your tasks.

Example of usage:

`bye`

Expected outcome:

The program exits.
