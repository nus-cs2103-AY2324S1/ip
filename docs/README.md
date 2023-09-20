# Gman Chatbot User Guide

## Features 

### Add task

Add tasks such as a todo, deadline or an event.

### Delete task

Delete tasks by index.

## Usage

### `todo` - Adds a todo task to the task list. Has to be followed up with a String.
A todo task consists of one field: description.


Example of usage: 
\
`todo Get a haircut`

Expected outcome:
\
A todo with the description of task is added to the task list.

Expected Output from GUI:
```
Got it. I've added this task:
[T][ ] Get a haircut
Now you have x tasks in the list. _(x is for the total number of tasks in the list)_
```

### `deadline` - Adds a deadline task to the task list. Has to be followed up with a String.

A deadline task consists of two fields: description and by. The fields are separated by the user through the use of 
"/by".
\
The by field should be in the form of a date in the format yyyy-mm-dd.

Example of usage:
\
`deadline Submit CS2100 Assignment /by 2023-09-10`

Expected outcome:
\
A deadline task with the description and by is added to the task list.

Expected Output from GUI:
```
Got it. I've added this task:
[D][ ] Submit CS2100 Assignment (by: Sep 10 2023)
Now you have x tasks in the list. _(x is for the total number of tasks in the list)_
```

### `event` - Adds an event task to the task list. Has to be followed up with a String.

An event task consists of three fields: description, from, & to. The fields are separated by the user through the 
use of
"/from" & "/to".
\
There is no constraint on the format of from & to.

Example of usage:
\
`event Sheares Hall Marathon Training /from Monday /to Wednesday`

Expected outcome:
\
An event task with the description, from, & to is added to the task list.

Expected Output from GUI:
```
Got it. I've added this task:
[E][ ] Sheares Hall Marathon Training (from: Monday to: Wednesday)
Now you have x tasks in the list. _(x is for the total number of tasks in the list)_
```

### `list` - Lists out all tasks in the task list. Should not be followed up with anything.

Lists out the tasks to the user in a numbered format. Tasks are listed from the earliest task added.

Example of usage:
\
`list`

Expected outcome:
\
A numbered list of all tasks in the task list is output to the user in the GUI. Assuming we have added all three 
tasks from before in the order of task, deadline, then event:

Expected Output from GUI:

```
Here are the tasks in your list:
1. [T][ ] Get a haircut
2. [D][ ] Submit CS2100 Assignment (by: Sep 10 2023)
3. [E][ ] Sheares Hall Marathon Training (from: Monday to: Wednesday)
```

### `mark` - Mark a task in the task list as done. Should be followed up with an integer.

Marks a task in the task list at given index as done. Index given should not be 0 or an integer greater than the 
size of the task list (i.e. the index should at most be the number of tasks in the task list).

Example of usage:
\
`mark 1`

Expected outcome:
\
The first task is now marked as done. This is shown to the user with a [X] checkbox.

```
Nice! I've marked this task as done:
[T][X] Get a haircut
```

### `unmark` - Un-mark a task in the task list as undone. Should be followed up with an integer.
Un-marks a task in the task list at given index as undone. Index given should not be 0 or an integer greater than the
size of the task list (i.e. the index should at most be the number of tasks in the task list).


Example of usage:

`unmark 1`

Expected outcome:

The first task that was just previously marked as done is now un-marked as undone. This is shown to the user with an 
empty [ ] checkbox.

Expected Output from GUI:
```
OK, I've marked this task as not done yet:
[T][ ] Get a haircut
```

### `delete` - Deletes a task in the task list. Should be followed up with an integer.
Deletes a task in the task list at given index. Index given should not be 0 or an integer greater than the size of 
the task list (i.e. the index should at most be the number of tasks in the task list).


Example of usage:
\
`delete 3`

Expected outcome:
\
The task at the specified index is removed from the task list. GUI outputs to user the task that has been deleted, 
along with the number of tasks left in the list.

Expected Output from GUI:
```
Noted. I've removed this task:
[E][ ] Sheares Hall Marathon Training (from: Monday to: Wednesday)
Now you have 2 tasks in the list.
```

### `find` - Finds all tasks in the task list that contains the keyword specified by the user. Should be followed up with a String.

User inputs a keyword after find. 

Example of usage:
\
`find CS2100`

Expected outcome:
\
A numbered list showing all matching tasks containing the keyword in the task list.

```
Here are the matching tasks in your list:
1. [D][ ] Submit CS2100 Assignment (by: Sep 10 2023)
```

### `undo` - Undoes the previous (most recent) command given by the user, only if it is undo-able. Should not be followed up with anything.

Undo-able commands include: todo, deadline, event, delete, mark and unmark.

Example of usage: 
\
`undo`

Expected outcome:
\
Assuming user has just marked the first task:
GUI outputs to the user that the undo is successful, and reverts the command.

Expected Output from GUI:
```
Undo successful!
OK, I've marked this task as not done yet:
[T][ ] Get a haircut
```

### `bye` - Indicates that user is done with managing tasks. This saves the current tasks in the tasklist to a .txt file for future retrieval.

Should not be followed up with anything. 

Example of usage:
\
`bye`

Expected outcome:
\
Current task list is stored in a .txt file.

Expected Output from GUI:
```
Bye. Hope to see you again soon!
```