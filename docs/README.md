# James User Guide

James is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 
James is able to track three types of tasks: todos, deadlines and events.

## Features 

### Feature - Track Tasks
James is able to track three types of tasks: todos, deadlines and events. 
Todos are tasks that do not have a specific date or time associated with them.
Deadlines are tasks that have a specific date and time that they are due by.
Events are tasks that have a specific date and time associated with them.
James provides an easy interface to view all added tasks as well as their status and description.

### Feature - Add Tasks
James is able to add tasks to the list of tasks that it is tracking. 
It has support for adding tasks with a description and a date and time if applicable.

### Feature - Mark Tasks as Done
James is able to mark tasks as done from the list of tasks that it is tracking. This is useful to keep track of tasks that have been completed.


### Feature - Delete Tasks
James is able to delete tasks from the list of tasks that it is tracking. 


## Usage

### `todo` - Add a todo task

Adds a todo task to the list of tasks that James is tracking.

Example of usage: 

`todo read book`

Expected outcome:

This adds a todo task with the description "read book" to the list of tasks that James is tracking.

```
Got it. I've added this task:
[T]

Now you have 1 taskS in the list.
```

### `deadline` - Add a deadline task

Adds a deadline task to the list of tasks that James is tracking.

Example of usage:

`deadline return book /by 2020-09-18 2359`

Expected outcome:

This adds a deadline task with the description "return book" and the date and time "18 Sep 2020 11:59PM" to the list of tasks that James is tracking.

```
Got it. I've added this task:
[D][] return book (by: 18 Sep 2020 11:59PM)

Now you have 1 tasks in the list.
```


### `event` - Add an event task

Adds an event task to the list of tasks that James is tracking.

Example of usage:

`event project meeting /from 2022-08-06 14:00 /to 2022-08-06 18:00`

Expected outcome:

This adds an event task with the description "project meeting" and the date and time "18 Sep 2020 2:00PM" to the list of tasks that James is tracking.

```
Got it. I've added this task:
[E][] project meeting (from: 18 Sep 2020 14:00 to: 18 Sep 2020 18:00)

Now you have 1 tasks in the list.
```

### `list` - List all tasks

Lists all tasks that James is tracking.

Example of usage:

`list`

Expected outcome:

This lists all tasks that James is tracking.

```
Here are the tasks in your list:
1. [T][] read book
2. [D][] return book (by: 18 Sep 2020 11:59PM)
3. [E][] project meeting (from: 18 Sep 2020 14:00 to: 18 Sep 2020 18:00)
```

### `mark` - Mark a task as done

Marks a task as done from the list of tasks that James is tracking. The tasks to mark is selected by the index.

Example of usage:

`mark 1`

Expected outcome:
This marks the first task in the list of tasks that James is tracking as done.

```
Nice! I've marked this task as done:
[T][X] read book
```

### `delete` - Delete a task

Deletes a task from the list of tasks that James is tracking. The tasks to delete is selected by the index.

Example of usage:

`delete 1`

Expected outcome:
This deletes the first task in the list of tasks that James is tracking.

```
Noted. I've removed this task:
[T][X] read book
Now you have 2 tasks in the list.
```

### `find` - Find tasks

Finds tasks from the list of tasks that James is tracking. The tasks to find is selected by the keyword.

Example of usage:

`find book`

Expected outcome:
This finds all tasks in the list of tasks that James is tracking that contains the keyword "book".

```
Here are the matching tasks in your list:
1. [D][] return book (by: 18 Sep 2020 11:59PM)
```

### `bye` - Exit James

Exits the James program.





