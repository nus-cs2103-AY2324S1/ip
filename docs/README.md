# Crusader User Guide

## Features 

### Addition of Tasks

Crusader allows you to add 3 different types of Tasks using specific commands. These include:
- Todo Tasks
- Deadline Tasks
- Event Tasks

### Deletion of Tasks

Once a Task is no longer relevant, Crusader lets you remove those Tasks to declutter your workspace.

### Marking and unmarking of Task completion status

Crusader tracks Tasks, letting you update their completion status to let you have a better idea of your remaining workload.

### List Tasks

Crusader allows you to view all of your Tasks to have an overview of your workload.

### Find Tasks

If you desire to search for specific Tasks, Crusader allows you to add search information to bring up specific tasks.

### Sort Tasks

As you continue adding Tasks, Crusader allows you to rearrange Tasks by alphabetical order to keep them well organized.

## Usage

### `bye` - Exit Crusader

Saves all Tasks to memory and closes the program.

Example of usage: 

`bye`

Expected outcome:

Description of the outcome.

```
Bye!
```

### `delete` - Deletes a Task

Removes a Task from Crusader. The Task is identified with its position in Crusader's list.

Example of usage: 

`delete 1`

Expected outcome:

Task 1 is removed.

```
Deleting the task:
[T][ ] TASK 1
Now there are 0 tasks in the list.
```

### `list` - Show all Tasks

Displays all the Tasks in Crusader.

Example of usage:

`list`

Expected outcome:

Crusader shows all Tasks.

```
Here are your tasks:
1. [T][ ] TASK 1
2. [T][ ] TASK 2
```

### `mark` - Mark a Task as complete

Marks a Task in Crusader as completed. The Task is identified with its position in Crusader's list.

Example of usage:

`mark 3`

Expected outcome:

Task 3 is marked as completed.

```
I have marked the following task as done:
[D][X] mark this task (by: Sep 23 2023 00)
```

### `unmark` - Mark a Task as incomplete

Unmarks a Task in Crusader, setting it as incomplete. The Task is identified with its position in Crusader's list.

Example of usage:

`unmark 2`

Expected outcome:

Task 2 is marked as incomplete.

```
I have unmarked this task:
[T][ ] unmark this task
```

### `todo` - Add a Todo Task to Crusader

Creates a Todo Task for Crusader.

Example of usage:

`todo drink water`

Expected outcome:

Incomplete Todo Task "drink water" is added to Crusader.

```
Adding the task:
[T][ ] drink water
Now there are 1 tasks in the list.
```

### `deadline` - Add a Deadline Task to Crusader

Creates a Deadline Task, which has a set deadline, to Crusader.

Example of usage:

`deadline complete CS2103T iP /by 22/09/2023 23`
 
Expected outcome:

Incomplete Deadline Task "complete CS2103T iP" with deadline of Sep 22 2023 23:00 is added to Crusader.

```
Adding the task:
[D][ ] complete CS2103T iP (by: Sep 22 2023 23)
Now there are 2 tasks in the list.
```

### `event` - Add an Event Task to Crusader

Creates an Event Task, which has a specified starting and ending time, to Crusader.

Example of usage:

`event my birthday /from 25/06/2023 00 /to 26/06/2023 00`

Expected outcome:

Incomplete Event Task "my birthday" with starting time Jun 25 2023 00:00 and ending time Jun 26 2023 00:00 is added to Crusader

```
Adding the task:
[E][ ] my birthday (from: Jun 25 2023 00 to: Jun 26 2023 00)
Now there are 3 tasks in the list.
```

### `find` - Searches for matching tasks in Crusader


Example of usage:

`find he`

Expected outcome:

All Tasks with names containing "he" are listed.

```
Here are the matching tasks in the list:
1. [T][ ] hello
2. [T][X] theatrics
```

### `sort` - Arranges Crusader's Tasks in alphabetic order

Orders all tasks in Crusader using alphabetic order.

Example of usage:

`sort`

Expected outcome:

All Tasks in Crusader are now sorted in order.

```
Sorting the list now.
Here is your list now:
1. [T][ ] apples
2. [T][X] bananas
3. [T][ ] cherries
```