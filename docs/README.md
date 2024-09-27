# Albatross User Guide
__Albatross__ is a simple desktop app for managing tasks, optimized for 
use via a Command Line Interface (CLI) while still having the
benefits of a Graphical User Interface (GUI).

## Features 

### Feature - Finding Help

Displays a help list containing all commands that can be used.

### Feature - Adding, Deleting Tasks

Adds different tasks to the task list and deletes them if the user
chooses to.

### Feature - Marking/Unmarking Tasks

Marks specified tasks as done or not done.

### Feature - Displaying Tasks

Displays all tasks in the task list in the order they were added.

## Usage

### `help`

Returns a list of all commands that can be used.

### `todo {taskName}` - Describe action

Creates a new todo task.

Example of usage: 

`todo {nothing}`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
[T][ ] nothing
Now you have 1 tasks in the list.
```

### `deadline {taskName} /by {YYYY-MM-DD}` - Describe action

Creates a new deadline task.

Example of usage:

`todo {nothing /by 1111-11-11}`

Expected outcome:

```
Got it. I've added this task:
[T][ ] nothing By: Nov 11 1111
Now you have 1 tasks in the list.
```
### `event {taskName} /from {YYYY-MM-DD} /to {YYYY-MM-DD}`

Creates a new event task.

### `delete {index}`

Deletes the task with the index number.

Example outcome:

```
Noted. I've removed this task:
[T][ ] nothing
Now you have 1 tasks in the list.
```

### `mark {index}`

Marks the task with the index number done.

Example of usage:

```
mark 1
```

Example outcome:

```
Nice! I've marked this task as done:
[T][X] nothing
```

### `unmark {index}`

Marks the task with the index number as not done.

### `list`

Displays all tasks in the task list.

Example outcome:

```
Here are the tasks in your list:
1.[D][ ] nothing By: Nov 11 1111
2.[T][ ] nothing
```

### `find {keyword}`

Finds and displays all tasks that have names containing the keyword.
