# User Guide

## Features 

### Add tasks and events
In Barbie world, you can add todos, deadlines, anddddd PARTIES! 

Some of the characteristics of these are as follows:
- Todo: only has a description
- Deadline: description + deadline
- Party: description + start + end
### Edit and update tasks
When you've completed a task, you can **mark** it as done! Other ways of editing your list of fabulous todos include
**deleting** and **unmarking** a task as done.

### Find tasks
When things get *REALLY* busy in Barbieland, Barbies may lose track of their tasks! Which is why in Barbie World, 
we want to help you get back on track! You may find tasks with a keyword, or list out all your tasks!

## Usage
- "todo" -- adds a new todo to your list of tasks
- "deadline" -- adds a new task with a deadline, indicate the deadline using a "/" in the format YYYY-MM-DD.
    - (e.g. deadline buy bread /2023-09-20)
- "party" -- adds a new party with a start and end date
    - (e.g. party barbie's birthday party /2023-09-20 /2023-09-21)

### `todo` - add todo

This command adds a todo to your task list.

Example of usage: 

`todo buy Barbie's present`

Expected outcome:

```
Got you barbie! I've added this task to your Barbie list: <task>
```



### `deadline` - add deadline

This command adds a deadline to your task list, with the deadline indicated in the format 
YYYY-MM-DD

Example of usage:

`deadline buy Barbie's present /2023-09-29`

Expected outcome:

```
Got you barbie! I've added this task to your Barbie list: <task>
```

### `party` - add todo

This command adds a party to your task list, with the start and end date indicated in the format
YYYY-MM-DD

Example of usage:

`party Barbie's birthday party /2023-08-20 /2023-09-20`

Expected outcome:

```
Got you barbie! I've added this task to your Barbie list: <task>
```
### `party` - add todo

This command adds a party to your task list, with the start and end date indicated in the format
YYYY-MM-DD

Example of usage:

`party Barbie's birthday party /2023-08-20 /2023-09-20`

Expected outcome:

```
Got you barbie! I've added this task to your Barbie list: <task>
```
### `mark` - add todo

This command marks a task in your task as done, using the item number 
of the task in that list.

Example of usage:

`mark 2`

Expected outcome:

```
Nice! I've marked this task as done:
<task>
```
### `unmark` - add todo

This command unmarks a task as done, using that item number of the task
in that list

Example of usage:

`unmark 2`

Expected outcome:

```
Alright! I've marked this task as not done yet!
<task>
```
### `del` - add todo

This command deleted a task from the list, using that item number of the task
in that list

Example of usage:

`del 2`

Expected outcome:

```
Deletion success! I've deleted this task off your list.
<task>
```
### `find` - add todo

This command finds a task using a provided keyword.

Example of usage:

`find milk`

Expected outcome:

```
These are your tasks with the keyword: milk
<task>
```


### `list` - add todo

This command lists all tasks in the task list.

Example of usage:

`list`

Expected outcome:

```
1. <task>
2. <task>
...
```

### `exit` - add todo

This command closes the application.

Example of usage:

`exit`
