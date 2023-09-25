# User Guide - ChickyChat

## Features 

### Feature - Add tasks

Tasks that you want to track can be added to the list.
There are 3 types of tasks:
- todo: tasks with only description
- deadline: tasks with a deadline in addition to its description
- event: tasks with a starting and ending time

### Feature - List tasks

List all the tasks that you have inputted.

### Feature - Mark tasks

Cross the task off your list when it is completed.

### Feature - Unmark tasks

Tasks that you marked prematurely can be unmarked.

### Feature - Find tasks

Search for the task that you are looking for efficiently.

### Feature - Delete tasks

Tasks can be removed to keep your list concise.

### Feature - Bye

Bids you farewell.

## Usage

### `todo` - Add a todo task

This adds a todo task to your list. 
The todo task only contains description of the task.

Example of usage: 

`todo Tidy my room`

Expected outcome:

A todo task is added to your list.

```
Got it. I've added this task:
  [T][ ] Tidy my room
Now you have 1 task(s) in the list.
```

### `deadline` - Add a deadline task

This adds a deadline task to your list.
The deadline task contains both description and deadline of the task.
The deadline of the task is prefixed by `/by` followed by the date written in the yyyy-mm-dd format.

Example of usage:

`deadline English essay /by 2023-10-01`

Expected outcome:

A deadline task is added to your list.

```
Got it. I've added this task:
  [D][ ] English essay (by: Oct 1 2023)
Now you have 2 task(s) in the list.
```

### `event` - Add a deadline task

This adds an event task to your list.
The event task contains both description and the starting and ending time of your task.
The starting time of the task is prefixed by `/from` while the ending time is prefixed by `/to`.

Example of usage:

`event Appointment /from Sunday 12pm /to 6pm`

Expected outcome:

An event task is added to your list.

```
Got it. I've added this task:
  [E][ ] Appointment (from: Sunday 12pm to: 6pm)
Now you have 3 tasks in the list.
```

### `list` - List all tasks

This lists all tasks that you have added to your list.

Example of usage:

`list`

Expected outcome:

A list that contains all the tasks you have inputted.

```
Here are the tasks in your list:
1. [T][ ] Tidy my room
2. [D][ ] English essay (by: Oct 1 2023)
3. [E][ ] Appointment (from: Sunday 12pm to: 6pm)
```

### `mark` - Mark selected tasks

This marks the tasks that you have completed as done.
When multiple tasks are to be marked, tasks should be separated by comma and arranged in ascending order.

Example of usage:

`mark 1, 2`

Expected outcome:

The selected tasks are marked as done.

```
Nice! I've marked the following task(s) as done:
  [T][X] Tidy my room
  [D][X] English essay (by: Oct 1 2023)
```

### `unmark` - Unmark selected tasks

This unmarks the tasks that are incomplete.
When multiple tasks are to be unmarked, tasks should be separated by comma and arranged in ascending order.

Example of usage:

`unmark 1, 2`

Expected outcome:

The selected tasks are marked as not done.

```
OK, I've marked the following task(s) as not done yet:
  [T][ ] Tidy my room
  [D][ ] English essay (by: Oct 1 2023)
```

### `find` - Find tasks

This finds all tasks that contains the keyword inputted.
Only one word is accepted as the keyword.

Example of usage:

`find essay`

Expected outcome:

All tasks that contain the keyword 'essay' are listed.

```
Here are the matching task(s) in your list:
1. [D][ ] English essay (by: Oct 1 2023)
```

### `delete` - Delete tasks

This deletes tasks from your list and allows you to keep it concise.
When multiple tasks are to be deleted, tasks should be separated by comma and arranged in ascending order.

Example of usage:

`delete 1, 3`

Expected outcome:

Task 1 and 3 are removed from the list.

```
Noted. I've removed the following task(s):
  [T][ ] Tidy my room
  [E][ ] Appointment (from: Sunday 12pm to: 6pm)
Now you have 1 task(s) in the list.
```

### `bye` - Bids you farewell

This will prompt the ChickyChat to say goodbye.

Example of usage:

`bye`

Expected outcome:

ChickyChat will say goodbye.

```
Bye. Hope to see you again soon!
```