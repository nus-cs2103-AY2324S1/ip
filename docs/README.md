# User Guide

## Features 

### Add tasks

Add a task from the following categories: todo, deadline, event.

### Mark tasks as done

Mark a task as completed with a cross on the list, but not remove it from the task list.

## See current tasks
List all tasks in the current task list with completed status.

### `todo xxx` - Add a todo task

Add a todo task with the description "xxx".

Example of usage: 

`todo run 2 miles`

Expected outcome:
```
Frodo heard: run 2 miles
Okay another task added:
[T][] run 2 miles
Now you have 1 task(s) in the list
```
A todo task is added to the task list.

### `deadline xxx /by yyyy-MM-dd HH:mm` - Add a deadline task

Add a deadline task with the description "xxx" and a deadline specified by
yyyy-MM-dd HH:mm.
Example of usage:

`deadline finish hw /by 2023-09-05 10:00`

Expected outcome:
```
Frodo heard: deadline finish hw /by 2023-09-05 10:00
Okay another task added:
[D][] finish hw (by Sep 05 2023 10:00 am)
Now you have 1 task(s) in the list
```
Description of the outcome.
A deadline task is added to the task list.



### `event xxx /from yyyy-MM-dd HH:mm /by yyyy-MM-dd HH:mm` - Add an event task

Add an event task with the description "xxx", start time,
and end time specified by yyyy-MM-dd HH:mm.
Example of usage:

`event competition /from 2023-09-05 10:00 /to 2023-10-05 10:00`

Expected outcome:
```
Frodo heard: event competition /from 2023-09-05 10:00 /to 2023-10-05 10:00
Okay another task added:
[E][] finish hw (from Sep 05 2023 10:00 am to Oct 05 2023 10:00 am)
Now you have 1 task(s) in the list
```

Description of the outcome.
An event task is added to the task list.


### `mark n` - Add a todo task

Mark the nth task (as shown in the list index) as done.

Example of usage:

`mark 1`

Expected outcome:
```
Frodo heard: mark 1
Wow! Good job clearing this task [T][X] run 2 miles
```
The task corresponding to the index is marked as done, with a cross [X] to show completion status.


### `unmark n` - Add a todo task

Unmark the nth task (as shown in the list index) as done.

Example of usage:

`unmark 1`

Expected outcome:
```
Frodo heard: unmark 1
Okay. I see you haven't done this task yet [T][] run 2 miles
```
The task corresponding to the index is unmarked, with an empty [] to show completion status.
