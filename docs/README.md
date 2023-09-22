# Bartholomew Hamish Montgomery User Guide

## Features 

### 1. Add Tasks

Add any of the following Tasks to start taking control of your day!

- Todo
- Deadline
- Event

### 2. View Tasks

View all the tasks that you have added:
- in chronological order
- in alphabetical order

You can view complete and incomplete Tasks!

### 3. Delete Tasks

Delete Tasks that have become dead and/or obsolete to declutter using the `delete` command! 🔥

### 4. Mark Tasks

Mark Tasks as:
- completed
- incomplete

Keep track of what tasks are pending completion 😄

### 5. Find Tasks

Too many Tasks to do? Easily find the task that you're looking for 😲

### 5. End Chat

 Done Talking to Bart? Simply Enter a command and bid farewell!

## Usage

### `todo` - Add a todo task

follow `todo` with the task to be done, and simply click enter.

Example of usage: 

`todo run`

Expected outcome:

Bart will send a message confirming the addition of the task to your list of tasks, as well as the current number of tasks you have in the list.

```
Got it! I've added this task:
[T][] run
You now have 1 task(s) in the list
```

### `deadline` - Add a task with a deadline

follow `deadline` with the task to be done. Specify the deadline in YYY-MM-DD format after a `/by`. Click enter to add the task into the list.

Example of usage: 

`deadline CS2103 /by 2023-09-22`

Expected outcome:

Bart will send a message confirming the addition of the task to your list of tasks (together with the deadline), as well as the current number of tasks you have in the list.

```
Got it! I've added this task:
[D][] CS2103 (by: Sep 22 2023)
You now have 2 task(s) in the list
```
### `event` - Add an upcoming event

follow `event` with the task to be done. Specify the start time and end time of the event in YYY-MM-DD format after a `/from` and `/to` respectively. Click enter to add the task into the list.

Example of usage: 

`event Hackathon /from 2023-09-01 /to 2023-09-20`

Expected outcome:

Bart will send a message confirming the addition of the event to your list of tasks (together with the start and end times), as well as the current number of tasks you have in the list.

```
Got it! I've added this task:
[E][] CS2103 (from: Sep 01 2023 to: Sep 20 2023)
You now have 3 task(s) in the list
```
### `list` - view all the tasks in the list

simply type `list` and click enter.

Example of usage: 

`list`

Expected outcome:

Bart will list out all the task you have. Details of the tasks, such as the type of task, as well as its completion status, will also be visible.

```
1. [T][] run
2. [D][] CS2103 (by: Sep 22 2023)
3. [E][] CS2103 (from: Sep 01 2023 to: Sep 20 2023)
```
### `sort` - view all the tasks in the list, sorted by alphabetical order

simply type `sort` and click enter.

Example of usage:

`sort`

Expected outcome:

Bart will list out all the task you have, sorted by alphabetical order. Details of the tasks, such as the type of task, as well as its completion status, will also be visible.

```
1. [D][] CS2103 (by: Sep 22 2023)
2. [E][] CS2103 (from: Sep 01 2023 to: Sep 20 2023)
3. [T][] run
```

### `delete` - remove a task from the list

simply type `delete` and the number of the task (if you're unsure what the number of the task you want to delete is, use the list keyword and find the index of the task).

Example of usage: 

`delete 2`

Expected outcome:

Bart will send a message confirming the deletion of the task, as well as the current number of tasks you have in the list.


```
Got it! I've removed this task:
[E][] CS2103 (from: Sep 01 2023 to: Sep 20 2023)
You now have 2 task(s) in the list
```
### `mark` - mark a task as complete

simply type `mark` and the number of the task (if you're unsure what the number of the task you want to delete is, use the list keyword and find the index of the task).

Example of usage: 

`mark 1`

Expected outcome:

Bart will send a message confirming the marking of the task as complete.


```
Great job! You've completed the following task:
[D][X] CS2103 (by: Sep 22 2023)
```

### `unmark` - mark a task as incomplete

simply type `unmark` and the number of the task (if you're unsure what the number of the task you want to unmark is, use the list keyword and find the index of the task).

Example of usage: 

`unmark 1`

Expected outcome:

Bart will send a message confirming the marking of the task as incomplete.


```
You've marked the following task as incomplete:
[D][] CS2103 (by: Sep 22 2023)
```

### `find` - find a specific task that you are looking for

simply type `find` and the task before clicking enter.

Example of usage:

`find run`

Expected outcome:

Bart will display a list of all the relevant matches, indexed in chronological order.


```
1. [T][] run
```

### `bye` - end the interaction with Bart

simply type `bye` and click enter.

Example of usage: 

`bye`

Expected outcome:

Bart will send a message confirming the end of the conversation, and the window will close after 5 seconds.


```
Until we meet once more in the near future, I bid you farewell.
```
