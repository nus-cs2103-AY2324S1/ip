# Thea User Guide

_The name Thea comes from the Greek word "[Alethea](https://www.dictionary.com/browse/alethea)" which means "truth"._

Thea is a chatbot designed to help you manage your tasks. No more forgetting your tasks with Thea helping you!


## Features 

### Manage your list of tasks

Tell Thea about your tasks and she will help you manage them! 

A task can either be a ToDo, a Deadline or an Event. 

A ToDo is a simple task with description, a Deadline is a task with an attached deadline and an Event lets you specify the starting and finishing timing of your event.

You can mark or unmark tasks as done for your reference, and delete the tasks that are not relevant anymore.

### Find a task easily

When you have a lot of tasks on your list, you can easily find your tasks with keywords.

### Automatically save your tasks for next time use

Thea automatically saves your task list everytime you update them, so you don't have to worry about losing your task list.

### Multiple different lists possible

Manage multiple different lists by specifying the file name you saved your list to.

## Usage

### `load` - Changes the data source of the application

Data source is where your task list data is stored. By specifying a file using this command, last saved data will be loaded and changes will be saved to the specified data. If no load is specified, the default data source of the application is "thea.txt".

Format:

load DATA_SOURCE

Example of usage: 

`load september.txt`

Expected outcome:

Thea will confirm that the file has been loaded.

```
september.txt loaded
```

### `todo` - Adds a new ToDo to your task list.

A ToDo is a simple task with a description. Use this command to add a new ToDo to your list. 

Format:

todo DESCRIPTION

Example of usage: 

`todo Sweep the floor`

Expected outcome:

Thea will confirm that the ToDo has been added to the list and let you know the number of tasks you have in your list after the addition.
The "[T]" in front of the description indicates that the task is a ToDo, while the second square brackets indicate whether the task is done. The second brackets show "X" if the task is done and is empty if the task is not done yet. After addition, the task is not done yet by default.


```
I have added the following task to your list:
 [T][] Sweep the floor
Now you have 1 task on the list. You can do this!
```

### `deadline` - Adds a new Deadline to your task list.

A Deadline is a task that has a deadline. Indicate your deadline in yyyy-MM-dd HH:mm format after the word `/by`.

Format:

deadline DESCRIPTION /by DEADLINE

Example of usage: 

`deadline Maths homework /by 2023-09-25 23:59`

Expected outcome:

Thea will confirm that the Deadline has been added to the list and let you know the number of tasks you have in your list after the addition.
The "[D]" in front of the description indicates that the task is a Deadline, while the second square brackets indicate whether the task is done. The second brackets show "X" if the task is done and is empty if the task is not done yet. After addition, the task is not done yet by default.

```
I have added the following task to your list:
 [D][] Maths homework (by: Sep 25 2023 23:59)
Now you have 2 tasks on the list. You can do this!
```

### `event` - Adds a new Event to your task list.

An Event task indicates an event with a starting and a finishing timing. Indicate your starting and finishing time in yyyy-MM-dd HH:mm format after the word `/from` and `/to`, respectively.

Format:

event DESCRIPTION /from START_TIME /to END_TIME

Example of usage: 

`event Career fair /from 2023-09-12 13:00 /to 2023-09-12 17:00`

Expected outcome:

Thea will confirm that the Event has been added to the list and let you know the number of tasks you have in your list after the addition.
The "[E]" in front of the description indicates that the task is an Event, while the second square brackets indicate whether the task is done. The second brackets show "X" if the task is done and is empty if the task is not done yet. After addition, the task is not done yet by default.

```
I have added the following task to your list:
 [E][] Career fair (from: Sep 12 2023 13:00 to: Sep 12 2023 17:00)
Now you have 3 tasks on the list. You can do this!
```

### `list` - Shows the current task list.

Shows you your current task list with their task types and done indicators.

Format:

list

Example of usage: 

`list`

Expected outcome:

Thea will show your task list.

```
1. [T][] Sweep the floor
2. [D][] Maths homework (by: Sep 25 2023 23:59)
3. [E][] Career fair (from: Sep 12 2023 13:00 to: Sep 12 2023 17:00)
```

### `mark` - Marks a task as done.

Marks a task of the specified index as done.

Format:

mark INDEX

Example of usage: 

`mark 2`

Expected outcome:

Thea will confirm the task that has been marked as done.

```
Great job! I've marked this task as done:
 [D][X] Maths homework (by: Sep 25 2023 23:59)
```

### `unmark` - Unmarks a task.

Unmarks a task of the specified index. Task will be shown as not done yet.

Format:

unmark INDEX

Example of usage: 

`unmark 2`

Expected outcome:

Thea will confirm the task that has been marked as done.

```
Okay, I've marked this task as not done yet:
 [D][] Maths homework (by: Sep 25 2023 23:59)
```

### `delete` - Deletes a task.

Deletes a task of the specified index from the task list.

Format:

delete INDEX

Example of usage: 

`unmark 2`

Expected outcome:

Thea will confirm the task that has been marked as done.

```
I have removed the following task from your list:
 [D][] Maths homework (by: Sep 25 2023 23:59)
Now you have 2 tasks on the list.
```

### `find` - Finds tasks containing the specified keywords.
 
Finds tasks containing the specified keyword(s) from the tasklist.

Format:

find KEYWORD(S)

Example of usage: 

`find career`

Expected outcome:

Thea will show a list of tasks containing the specified keyword(s).

```
Here are the matching tasks on your list:
1. [E][] Career fair (from: Sep 12 2023 13:00 to: Sep 12 2023 17:00)
```

### `bye` - Exits the application.

Use this command to exit the application.

Format:

bye

Example of usage: 

`bye`

Expected outcome:

Thea will greet you goodbye.

```
I hope I made your day easier with my service. See you again! > <
```
