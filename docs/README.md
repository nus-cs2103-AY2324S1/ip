# Muddy User Guide

## Features
- [Listing all tasks: `list`](https://tayruxin.github.io/ip/###list-command)
- [Adds a new ToDo Task: `todo`](https://tayruxin.github.io/ip/#adds-a-todo-task-todo) 
- [Adds a new Deadline Task: `deadline`](https://tayruxin.github.io/ip/#adds-a-deadline-task-deadline)
- [Adds a new Event Task: `event`](https://tayruxin.github.io/ip/#adds-a-new-event-task-event)
- [Delete a task: `delete`](https://tayruxin.github.io/ip/#delete-a-task-delete)
- [Find a task: `find`](https://tayruxin.github.io/ip/#find-a-task-find)
- [Set priority for a task: `setPriority`](https://tayruxin.github.io/ip/#set-priority-for-a-task-setpriority)
- [Exiting the program: `bye`](https://tayruxin.github.io/ip/#exit-the-program-bye) 

##  list all tasks: `list` 
Shows a list of all task in the list

format: `list`

Example of usage:

`list`

Expected outcome:

```
1. [D][x][High] CS2103 Assignment (by: Apr 2 2023)
2. [E][ ][Low] Dinner prep (from: Dec 12 2023 to Dec 13 2023)
3. [T][ ][None] Buy fruits 
```

## Adds a ToDo Task: `todo`
Adds a todo task with the specified description into the list.

Format: `todo DESCRIPTION`

Example of usage: 

`todo exampleToDo`

Expected outcome:

```
Got it. I've added this task:
[T][][None] exampleToDo
Now you have 8 tasks in the list
```

## Adds a deadline Task: `deadline`
Adds a deadline task with the specified description and date into the list.

Format: `deadline DESCRIPTION /by DATE(yyyy-mm-dd) TIME`

- DATE must be in yyyy-mm-dd format
- TIME must be in 24hr format e.g `1800`

Example of usage:

`deadline exampleDeadline /by 2023-11-11 1800`

Expected outcome:

```
Got it. I've added this task:
[D][][None] exampleDeadline (by: Nov 11 2023 06:00pm)
Now you have 8 tasks in the list
```

## Adds a new Event task: `event`
Adds an event task with the specified description and date into the list.

Format: `event DESCRIPTION /from DATE(yyyy-mm-dd) /to DATE(yyyy-mm-dd)`

- DATE must be in yyyy-mm-dd format

Example of usage:

`event exampleEvent /from 2023-11-11 /to 2023-12-12`

Expected outcome:

```
Got it. I've added this task:
[E][][None] exampleEvent (from: Nov 11 2023 to: Dec 12 2023)
Now you have 8 tasks in the list
```

## Delete a task: `delete`
Deletes the specified task from the list.

Format: `delete INDEX`

- Deletes the person at the specified `INDEX`
- The index refers to the index number shown in the task list
- The index must be a positive integer

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][][Low] todo1
Now you have 8 tasks in the list
```

## Find a task: `find`
Find tasks whose description contain any of the given keyword

Format: `find KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive e.g `example` will match `Example`
- As long as the description contain the keyword it will be shown

Example of usage:

`find tutorial`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][X][Low] CS2100 tutorial (by: Feb 2 2021 8:00am)
2. [T][ ][High] Review CS2101 tutorial
```

## Set priority for a task: `setPriority`
Set priority got a given task in the list

Format: `setPriority INDEX PRIORITY_LEVEL`

> PRIORITY_LEVEL
> 
> 0 = None
> 
> 1 = Low
> 
> 2 = Mid
> 
> 3 = High

- Sets the priority for the task at the specified `INDEX`
- The index refers to the index number shown in the task list
- The index must be a positive integer
- PRIORITY_LEVEL must be either 0,1,2 or 3

Example of usage:

`setPriority 1 2`

Expected outcome:

```
OK, I've set the priority for this task: 
[D][X][Mid] CS2103 Assignment (by: Apr 2 2023)
```

## Exit the program: `bye`
Exits the program

Format: `bye`



