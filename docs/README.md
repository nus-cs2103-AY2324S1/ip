# User Guide

_**EnPassant**_ is a personalized chatbot that helps you keep track of your tasks through a 
mix of a Graphical User Interface (GUI) and a Command Line Interface (CLI). This _combines the 
best of both worlds_ by letting you **quickly** input commands, while still enjoying a **pleasant
user interface**. This bot is inspired by a famous chess move and loves to give over the
top replies to your commands.

## Features 

### Listing of Tasks

You can see at one go all the tasks you need to complete, as well as tasks you have ready completed.

You can even sort your deadlines to prioritize which tasks to begin first!

### Marking and Unmarking Tasks as Done

Once you have completed your task, you can gain a peace of mind by marking it as done. However, fret not 
if you realize you did not complete a task properly, you can always unmark it!

### Deleting Tasks

If you have completed a task and you feel that it is cluttering your task list, you can delete it 
off your list.

### Three Different Types of Tasks

To cater to a wide range of options, EnPassant has included three types of tasks:
* `Todo` tasks. Tasks which do not have a definite deadline.
* `Deadline` tasks. Tasks which have a specific date and time to be completed by.
* `Event` tasks. Tasks with a start and end that may not be precisely specified.

### Finding Tasks

As your task list grows, it may become hard to find a specific task you want to do from the list.
EnPassant solves this by letting you query for a specific task.

## Usage

### `bye` - Exit program

Close EnPassant.

Example of usage: 

`bye`

Expected outcome:

EnPassant prints the following message, then closes one second later.

```
Bye! Hope to see you again soon!
```

### `list` - List all tasks

List all of your tasks.

Example of usage:

`list`

Expected outcome:

EnPassant prints all your tasks in the order you added them.

```
Here are the tasks in your list:
1. [T][ ] Go for a jog in the park
2. [D][ ] Complete CS2103T assignment (by: Sep 22 2022, 11:59 PM)
```

Tip: Use `list sort` to sort the deadlines by chronological order.

### `mark` - Mark a task as done

Mark a specific task as done, using the index of the task when `list` is called.

Example of usage:

`mark 1`

Expected outcome:

EnPassant adds an `X` beside the task.

```
Great success! I have marked this task as done:
  [T][X] Go for a jog in the park
```

### `unmark` - Unmark a task as done

Mark a specific task as not done, using the index of the task when `list` is called.

Example of usage:

`unmark 1`

Expected outcome:

EnPassant removes the `X` beside the task.

```
Very nice! I have marked this task as not done yet:
  [T][ ] Go for a jog in the park
```


### `delete` - Delete a task

Delete a specific task as not done, using the index of the task when `list` is called.

Example of usage:

`delete 1`

Expected outcome:

EnPassant removes the task.

```
Task went on vacation, never came back.
  [T][ ] Go for a jog in the park
You now have 1 tasks in the list!
```

### `todo` - Create a Todo task

Example of usage:

`todo Go for a jog in the park`

Expected outcome:

EnPassant adds the task to your list.

```
New task just dropped!
  [T][ ] Go for a jog in the park
You now have 2 tasks in the list!
```

### `deadline` - Create a Deadline task

Example of usage:

`deadline Complete CS2103T assignment /by 2022-09-22 2359`

Expected outcome:

EnPassant adds the task to your list.

```
New task just dropped!
  [D][ ] Complete CS2103T assignment (by: Sep 22 2022, 11:59 PM)
You now have 2 tasks in the list!
``` 


### `event` - Create an Event task

Example of usage:

`event Party! /from tonight /to tomorrow`

Expected outcome:

EnPassant adds the task to your list.

```
New task just dropped!
  [E][ ] Party! (from: tonight to: tomorrow)
You now have 3 tasks in the list!
``` 

### `find` - Find a specific task

Find a specific task matching the query.

Example of usage:

`find assignment`

Expected outcome:

EnPassant finds and displays the task.

```
Very nice! Here are the tasks matching your query:
1. [D][ ] Complete CS2103T assignment (by: Sep 22 2022, 11:59 PM)
``` 
Note: This index does not match the one given by `list`. Do not confuse them and delete the wrong tasks!