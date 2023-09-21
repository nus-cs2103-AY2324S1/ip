# User Guide of Lemon 
Lemon is a desktop chat bot for managing tasks via a Command Line Interface (CLI) and Graphical User Interface (GUI). 
You can categorise your tasks into Event, Deadline and Todo tasks.

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

### Reschedule

Reschedule specified deadline task to new date indicated.

### Find

Find tasks with descriptions that match the specified keyword.


## Usage

### `list` - List all tasks.

List all tasks.

Example of usage:

`list`

Expected outcome:
```
1. [D][] Quiz (by:Feb 12 2023)
2. [T][] Jump 20 times
```


### `todo (description)` - Add a todo task.

Add a todo task.

Example of usage: 

`todo Jump 20 times`

Expected outcome:
```
Got it. I've added this task: 
[T][] Jump 20 times
Now you have 2 tasks in the list.
```
Description of the outcome.
```
1. [D][] Quiz (by:Feb 12 2023)
2. [T][] Jump 20 times
```

### `deadline(description) /by (YYYY-MM-DD)` - Add a deadline task.

Add a deadline task.

Example of usage:

`deadline Quiz /by 2023-02-12`

Expected outcome:
```
Got it. I've added this task: 
[D][] Quiz (by:Feb 12 2023)
Now you have 2 tasks in the list.
```
Description of the outcome.
```
1. [T][] Jump 20 times
2. [D][] Quiz (by:Feb 12 2023) 
```

### `event(description) /from (YYYY-MM-DD) /to (YYYY-MM-DD)` - Add a deadline task.

Add a deadline task.

Example of usage:

`event Orientation /from 2023-02-12 /to 2023-02-14` 

Expected outcome:
```
Got it. I've added this task: 
[E][] Orientation (from:Feb 12 2023 to:Feb 14 2023)
Now you have 3 tasks in the list.
```
Description of the outcome.
```
1. [T][] Jump 20 times
2. [D][] Quiz (by:Feb 12 2023)
3. [E][] Orientation (from:Feb 12 2023 to:Feb 14 2023)
```

### `mark (task number)` - Mark a task as completed.

Mark a task as completed.

Example of usage:

`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
[T][x] Jump 20 times
Now you have 3 tasks in the list.
```
Description of the outcome.
```
1. [T][x] Jump 20 times
2. [D][] Quiz (by:Feb 12 2023)
3. [E][] Orientation (from:Feb 12 2023 to:Feb 14 2023)
```

### `unmark (task number)` - Mark a task as completed.

Mark a task as completed.

Example of usage:

`unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
[T][] Jump 20 times
Now you have 3 tasks in the list.
```
Description of the outcome.
```
1. [T][] Jump 20 times
2. [D][] Quiz (by:Feb 12 2023)
3. [E][] Orientation (from:Feb 12 2023 to:Feb 14 2023)
```

### `reschedule (task number) /to (YYYY-MM-DD)` - Reschedule a task as completed.

Mark a task as completed.

Example of usage:

`reschedule 1 /to 2023-02-21`

Expected outcome:
```
OK, I've marked this task as not done yet:
[D][] Quiz (by:Feb 21 2023)
Now you have 3 tasks in the list.
```
Description of the outcome.
```
1. [T][] Jump 20 times
2. [D][] Quiz (by:Feb 21 2023)
3. [E][] Orientation (from:Feb 12 2023 to:Feb 14 2023)
```

### `find (keyword)` - Find tasks with matching description.

Find a list of tasks with matching description.

Example of usage:

`find Jump`

Expected outcome:
```
1. [T][] Jump 20 times
2. [T][] Jump 40 times
```
