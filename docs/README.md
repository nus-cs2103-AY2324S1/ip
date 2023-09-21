# DUCKY User Guide

## Features 

### Task Management

You can add various types of tasks (Todo, Deadline, Event), view tasks, delete tasks and mark them as complete/incomplete.

### Filter

You can filter tasks (deadlines) by name, or fall on a certain date.

### Data Persistence

Data will automatically be saved at the end of each session. Data will be stored as a `tasks.txt` file in the active directory.

## Usage

### `todo` - Adding to-do

Adds a to-do to the task manager.

Format: `todo DESCRIPTION`

Example of usage: 

`todo go to the supermarket`

Expected outcome:

Adds a to-do called "go to the supermarket" to the task manager.

```
Okay! I've added this task:
[TODO] [ ] go to the supermarket
There is now 1 task in your list.
```

### `deadline` - Adding deadline

Adds a deadline to the task manager.

Format: `deadline DESCRIPTION /by DATE`

- Date should be in the format `yyyy-mm-dd` (e.g. 2023-12-31)

Example of usage:

`deadline cs2103t user guide /by 2023-09-22`

Expected outcome:

Adds a deadline for "cs2103t user guide" on Sep 22 2023 to the task manager.

```
Okay! I've added this task:
[DEADLINE] [ ] cs2103t user guide (by: Sep 22 2023)
There are now 2 tasks in your list.
```

### `event` - Adding event

Adds an event to the task manager.

Format: `event DESCRIPTION /from START-TIME /to END-TIME`

Example of usage:

`event week 6 /from Monday /to Sunday`

Expected outcome:

Adds an event called "week 6" that occurs from "Monday" to "Sunday" to the task manager.

```
Okay! I've added this task:
[EVENT] [ ] week 6 (from: Monday to: Sunday)
There are now 3 tasks in your list.
```

### `list` - Listing all tasks

Shows a list of all tasks in the task manager.

Format: `list`

Expected outcome:

Shows an indexed list of all tasks in the task manager.

```
Here are the tasks in your list:
1. [TODO] [ ] go to the supermarket
2. [DEADLINE] [ ] cs2103t user guide (by: Sep 22 2023)
3. [EVENT] [ ] week 6 (from: Monday to: Sunday)
```

### `find` - Locating tasks by name

Finds tasks with descriptions that contain the specified keyword.

Format: `find KEYWORD`

Example of usage:

`find cs2103t`

Expected outcome:

Shows a list of tasks containing the keyword "cs2103t".

- The index of each task corresponds to its position in the task manager (i.e. the same index as when using the list command)

```
Here are the task(s) that contain "cs2103t":
2. [DEADLINE] [ ] cs2103t user guide (by: Sep 22 2023)
```

### `schedule` - Locating tasks by date

Finds tasks (particularly deadlines) containing the specified date.

Format: `schedule DATE`

- Date should be in the format `yyyy-mm-dd` (e.g. 2023-12-31)

Example of usage:

`schedule 2023-09-22`

Expected outcome:

Shows a list of tasks containing the date Sep 22 2023.

- The index of each task corresponds to its position in the task manager (i.e. the same index as when using the list command)

```
Here are the task(s) scheduled for Sep 22 2023:
2. [DEADLINE] [ ] cs2103t user guide (by: Sep 22 2023)
```

### `mark` - Marking task as complete

Marks the task with the specified index as complete.

Format: `mark INDEX`

- The index of each task corresponds to its position in the task manager (i.e. the same index as when using the list command)

Example of usage:

`mark 2`

Expected outcome:

Marks the task with the index `2` in the task manager as complete.

```
Okay! I've marked this task as complete:
[DEADLINE] [X] cs2103t user guide (by: Sep 22 2023)
```

### `unmark` - Marking task as incomplete

Marks the task with the specified index as incomplete.

Format: `unmark INDEX`

- The index of each task corresponds to its position in the task manager (i.e. the same index as when using the list command)

Example of usage:

`unmark 2`

Expected outcome:

Marks the task with the index `2` in the task manager as incomplete.

```
Okay! I've marked this task as incomplete:
[DEADLINE] [ ] cs2103t user guide (by: Sep 22 2023)
```

### `delete` - Deleting a task

Deleting the task with the specified index from the task manager.

Format: `delete INDEX`

- The index of each task corresponds to its position in the task manager (i.e. the same index as when using the list command)

Example of usage:

`delete 1`

Expected outcome:

Deletes the task with the index `1` from the task manager.

```
Okay! I've deleted this task:
[TODO] [ ] go to the supermarket
There are now 2 tasks in your list.
```