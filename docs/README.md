
# User Guide for Quack the duck

## Features

### Store tasks
Quack is able to keep track of a variety of tasks, namely, todo, deadline and event tasks. These three tasks are sufficient enough to represent most of the day to day tasks. These tasks are stored locally, so no need to worry about missing tasks!

### Keep track of task's progress
Quack is able to keep track of completed tasks by marking it. You are able to unmark it at any point in time, so no need to worry about creating a new task in case it is not completed.

### Remind you of time sensitive tasks
On start up, Quack will remind you of today's tasks. Quack is also able to search and find tasks due soon by your command.

### Friendly welcome message
Quack is always delighted to see you and will enthusiastically greet you. Quack will remind you of possible commands and their format as he is worried you have forgotten. Then, Quack will list out all current tasks and their progress. Lastly, Quack will remind you of the tasks due today!

## Commands

### `help` - Shows the Help message

Quack provides a help message, detailing all possible commands and its required format

Example of usage:

`help`

### `list`  - Lists out all tasks

Quack will show all tasks and their current state in order

Example of usage:

`list`

Expected outcome:

```
Quack Quack, here are the tasks in quack's memory:
1. [T][ ] quiz
```
### `mark`  - Marks a tasks as complete

Quack will mark a current unmarked task by its id, if it is already marked Quack will inform you about it.

Example of usage:

`mark 1`

Expected outcome:

```
Quack! Congrats for finishing the task!
[T][X] CS2100 quiz
```
### `unmark`  - Marks a tasks as not completed

Quack will unmark a current marked task by its id, if it is already unmarked Quack will inform you about it.

Example of usage:

`unmark 1`

Expected outcome:

```
Quack, I've marked this task as not done yet :(
[T][ ] CS2100 quiz
```
### `Find`  - Finds a task

Quack will find and show all the tasks that match or contains the keyword.

Example of usage:

`find quiz`

Expected outcome:

```
Quack has found 2 matching tasks in your list:
1. [T][ ] CS2103T quiz
2. [T][ ] CS2100 quiz
```

### `reminder`  - Reminds you on upcoming tasks

Quack will find and show all the tasks that are due or starting in the days given. This command will work on time sensitive tasks such as Deadline and Event tasks. For Event tasks, Quack compares by the starting date.

Example of usage:

`find quiz`

Expected outcome:

```
Quack has found 2 matching tasks in your list:
1. [T][ ] CS2103T quiz
2. [T][ ] CS2100 quiz
```
### `todo`  - Creates a todo task

Quack will remember a non time sensitive task so that you do not have to!

Example of usage:

`todo CS2103T quiz`

Expected outcome:

```
Quack! I have added this task:
[T][ ] CS2103T quiz
Quack! Quack is currently remembering 2 tasks.
```
### `deadline`  - Creates a deadline task

Quack will remember a time sensitive task so that you do not have to! As Quack is a duck, you have to signal the deadline to Quack by providing a /by flag followed by a date in this format `YYYY-MM-DD HH:MM`

Example of usage:

`deadline CS2100 quiz /by 2023-09-20 23:59`

Expected outcome:

```
Quack! I have added this task:
[D][ ] CS2100 quiz (:by Wed 11:59PM, Sep 2023)
Quack! Quack is currently remembering 2 tasks
```
### `event`  - Creates a event task

Quack will remember a time sensitive task so that you do not have to! As Quack is a duck, you have to signal the event duration to Quack by providing a /from and a /to flag. Each followed by a date in this format `YYYY-MM-DD HH:MM`

Example of usage:

`event recess week /from 2023-09-22 23:59 /to 2023-10-02 23:59`

Expected outcome:

```
Quack! I have added this task:
[E][ ] recess week (:from Fri 11:59PM, Sep 2023 :to Mon 11:59PM, Oct 2023)
Quack! Quack is currently remembering 3 tasks
```
