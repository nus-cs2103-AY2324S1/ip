# Forine
Forine is a desktop chat bot for managing tasks via a Graphical User Interface (GUI). 
You can categorise your tasks into Event, Deadline and Todo tasks and get reminders when they are due.

# User Guide

## Features 
### List

List all tasks input by user. 

### Todo

Add todo tasks by adding task description.

### Deadline

Add deadline tasks by adding task description and deadline.

### Event

Add event tasks by adding task description, start and end date.

### Mark

Mark specified task as completed.

### Unmark

Unmark specified task as completed.

### Find

Find tasks with descriptions that match the specified keyword.

### Remind

Remind user of upcoming deadlines and events through popups.

## Usage

### `list` - List all tasks.

Example of usage:

`list`

Expected outcome:
```
1. [D][] CS2103 ip (by: Sep 21 2023)
2. [T][] Rest well
```

### `todo (description)` - Add a todo task.

Add a todo task.

Example of usage: 

`todo Rest well`

Expected outcome:
```
Got it. I've added this task: 
[T][] Rest well
Now you have 2 tasks in the list.
```
Description of the outcome.
```
1. [D][] CS2103 ip (by: Sep 21 2023)
2. [T][] Rest well
```

### `deadline(description) /by (YYYY-MM-DD)` - Add a deadline task.

Add a deadline task.

Example of usage:

`deadline Quiz /by 2023-09-21`

Expected outcome:
```
Got it. I've added this task: 
[D][] CS2103 ip (by: Sep 21 2023)
Now you have 2 tasks in the list.
```
Description of the outcome.
```
1. [T][] Rest well
2. [D][] CS2103 ip (by: Sep 21 2023)
```

### `event(description) /from (YYYY-MM-DD) /to (YYYY-MM-DD)` - Add a deadline task.

Add a deadline task.

Example of usage:

`event FOW /from 2023-07-31 /to 2023-08-02` 

Expected outcome:
```
Got it. I've added this task: 
[E][] FOW (from: Jul 31 2023 to: Aug 02 2023)
Now you have 3 tasks in the list.
```
Description of the outcome.
```
1. [T][] Rest well
2. [D][] CS2103 ip (by: Sep 21 2023)
3. [E][] FOW (from: Jul 31 2023 to: Aug 02 2023)
```

### `mark (task number)` - Mark a task as completed.

Mark a task as completed.

Example of usage:

`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
[T][x] Rest well
```
Description of the outcome.
```
1. [T][X] Rest well
2. [D][] CS2103 ip (by: Sep 21 2023)
3. [E][] FOW (from: Jul 31 2023 to: Aug 02 2023)
```

### `unmark (task number)` - Mark a task as uncompleted.

Mark a task as uncompleted.

Example of usage:

`unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
[T][] Rest well
Now you have 3 tasks in the list.
```
Description of the outcome.
```
1. [T][] Rest well
2. [D][] CS2103 ip (by: Sep 21 2023)
3. [E][] FOW (from: Jul 31 2023 to: Aug 02 2023)
```

### `find (keyword)` - Find tasks with matching description.

Find a list of tasks with matching description.

Example of usage:

`find Rest`

Expected outcome:
```
1. [T][] Rest well
```
