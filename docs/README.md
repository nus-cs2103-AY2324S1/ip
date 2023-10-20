# Frodo Task Manager - User Guide

## Features 
### Add Tasks
1. Todo: Simple tasks with no specific date attached.
2. Deadline: Tasks that need to be completed by a certain date.
3. Event: Tasks that occur between two dates.
### List Tasks
See all your current tasks in one overview.

### Mark Tasks as Done
Indicate which tasks you have completed.

### Unmark Tasks
Reverse a task's status if marked incorrectly.

### Delete Tasks
Remove tasks you no longer need.

### Search for Tasks
Find tasks by keyword or date.

### Archive Tasks
Move tasks to an archive to declutter your main list.

### `todo [description]` - Add a todo task

Add a todo task with a description.

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

### `deadline [description] /by [yyyy-MM-dd HH:mm]` - Add a deadline task

Add a deadline task with the description and a deadline specified by
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
A deadline task is added to the task list.


### `event [description] /from [yyyy-MM-dd HH:mm] /by [yyyy-MM-dd HH:mm]` - Add an event task

Add an event task with the description", start time,
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
An event task is added to the task list.


### `mark n` - Mark a task as done

Mark the nth task (as shown in the list index) as done.

Example of usage:

`mark 1`

Expected outcome:
```
Frodo heard: mark 1
Wow! Good job clearing this task [T][X] run 2 miles
```
The task corresponding to the index is marked as done, with a cross [X] to show completion status.


### `unmark n` - Unmark a task

Unmark the nth task (as shown in the list index).

Example of usage:

`unmark 1`

Expected outcome:
```
Frodo heard: unmark 1
Okay. I see you haven't done this task yet [T][] run 2 miles
```
The task corresponding to the index is unmarked, with an empty [] to show completion status.

### delete [task number] - Deletes a task from the task list
Deletes a task based on its index number in the task list.

Example of usage:
`delete 1`

Expected outcome:
```
Frodo heard: delete 2
Woohoo. I've removed this task:
[D][] Buy a new book (by Oct 01 2023 6:00 pm)
Now you have 2 task(s) in the list
```
The task corresponding to the index is deleted from the task list.

### find [keyword] - Find a task with the keyword
Find tasks that contain the specified keyword in their descriptions.

Example of usage:
`find book`

Expected outcome:
```
Frodo heard: find book
Here are the tasks in your list:
1.[T][] Read a book
2.[D][] Buy a new book (by Oct 01 2023 6:00 pm)
```
The tasks with the keyword are listed.

### archive - Archives all tasks.
Archives all tasks from the current task list to a separate file and clears the main task list.

Example of usage:
`archive`

Expected outcome:
```
Frodo heard: archive
Successfully archived tasks in a new file
```
All tasks in the current list are archived in a new file. The task list in the current file is now empty.

### bye - Exit program.
Exits the program. All changes made to task list will be saved to file.

Example of usage:
`bye`

Expected outcome:
```
Frodo heard: bye
Goodbye. See you again
```