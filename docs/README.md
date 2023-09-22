# Gudetama User Guide
Gudetama is an application that helps you to manage tasks. It is optimised for use via a Command Line Interface (CLI).
It comes with a user-friendly and intuitive GUI. 

## Features 
```
Note: 

1. All commands are case-sensitive. 

2. All dates should be in the dd-MM-yyyy HH:mm:ss format
```

### Add todo 

Create a todo 

### Add deadline

Create a deadline

### Add event

Create an event 

### Delete task

Delete a task that you have previously added 

### Mark task

Mark completed tasks as done

### Unmark task

Unmark tasks that you have accidentally marked as done

### Find task 

Find a task in your list 

### Help

Ask Gudetama for the valid commands that you can use 

## Usage

### `todo` - Creates a new todo 

Creates a new todo and adds it to your list of tasks

Example of usage: 

`todo run`

Expected outcome:
```
Got it.I've added this task: 
[T] [] run
Now you have 1 tasks in the list. 
```

### `deadline` - Creates a new deadline

Creates a new deadline and adds it to your list of tasks

Example of usage:

`deadline assignment /by 12-12-2012 12:12:12`

Expected outcome:

```
Got it.I've added this task: 
[D] [] assignment (by: 12/12/2012 12:12:12)
Now you have 1 tasks in the list. 
```

### `event` - Creates a new event

Creates a new event and adds it to your list of tasks

Example of usage:

`event sports day /from 12-12-2012 12:12:12 
/to 12-12-2012 18:12:12`

Expected outcome:

```
Got it.I've added this task: 
[E] [] sports day (from: 12/12/2012 12:12:12
to: 12/12/2012 18:12:12)
Now you have 1 tasks in the list. 
```

### `delete` - Deletes a task

Deletes a task and removes it from the list of tasks

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task: 
[T][] run 
Now you have 0 tasks in the list
```

### `mark` - Marks a completed task as done.

Marks a completed task as done, 
which is indicated by a cross symbol in the list. 

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] run 
```

### `unmark` - Unmarks a completed task

Unmarks a completed task as done 
which is indicated by the removal of the cross symbol

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][] run 
```
### `list` - Lists the tasks 

Lists all the task in the tasklist 

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][] run 
```
### `help` - Lists the valid commands 

Lists the valid commands that the user can use with examples 

Example of usage:

`help`

Expected outcome:

```
Here is a list of valid commands:
1. todo 
2. event
3. deadline
4. mark 
5. unmark 
6. list 
7. delete 
```
### `find` - Finds task with the specified keyword

Finds and returns  the tasks in the list that matches the specified keyword

Example of usage:

`find run`

Expected outcome:

```
Here are the matching tasks in your list:
[T][] run 
```
