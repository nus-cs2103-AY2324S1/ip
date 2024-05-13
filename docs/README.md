# Chatter User Guide

This repo contains the code for Chatter, the chatbot, as part of CS2103T Software Engineering.

Chatter is a **desktop chatbot application for managing and storing tasks via a Command Line Interface (CLI)** while 
having its own Graphical User Interface (GUI).

--------------------------------------------------------------------------------------------------------------------
## Features 

### Add a todo, event or deadline task

Depending on the user, the user can add tasks of various format to Chatter.

### List out all the tasks

Confused about what tasks you currently have? Chatter is able to list out
all the current tasks that the user has.

### Find tasks

Only remember a keyword in the task but not sure where it is? Chatter is 
able to find tasks based on a keyword entered by the user.

### Mark tasks as done

Chatter is able to mark tasks as done or mark tasks as undone.

### Delete tasks

Chatter is able to delete tasks off the user's list.

### Save tasks

After every valid user input, Chatter automatically saves the user's tasks to
a local data file so that the user is able to continue working on the current 
list of tasks. 

## Usage

### `todo` - Adds a todo task

Adds a todo task to the list of tasks.

Example of usage: 

`todo return book`

Expected outcome:

A message will be printed after the task has been successfully added to the
list of tasks.

```
Got it. I have added this task to do:
    [T][ ] return book
You now have 1 task(s) in the list.
```
<br>

### `deadline` - Adds a deadline task

Adds a deadline task to the list of tasks.

Example of usage:

`deadline return book /by 2024-12-27`

Expected outcome:

A message will be printed after the task has been successfully added to the
list of tasks.

```
Got it. I have added this task to do:
    [D][ ] return book (by: 27/12/2024) 
You now have 1 task(s) in the list.
```
<br>

### `event` - Adds an event task

Adds an event task to the list of tasks.

Example of usage:

`event project meeting /from 2pm /to 3pm`

Expected outcome:

A message will be printed after the task has been successfully added to the
list of tasks.

```
Got it. I have added this task to do:
    [E][ ] project meeting (from: 2pm to: 3pm) 
You now have 1 task(s) in the list.
```
<br>

### `list` - Lists all tasks

Lists all the tasks that the user currently has.

Example of usage:

`list`

Expected outcome:

A message will be printed to display all the tasks that the user currently
has.

```
These are all the task(s) in your list:
    1.[T][ ] return book
```
<br>

### `mark` - Mark task as done

Mark a particular task at the specified index as done where the index
must be a positive integer (1, 2, 3, ...).

Example of usage:

`mark 1`

Expected outcome:

A message will be printed to show the task being marked as done.

```
Good job! I've marked this task as completed:
    [T][X] return book
```
<br>

### `delete` - Delete task

Delete a particular task at the specified index where the index
must be a positive integer (1, 2, 3, ...).

Example of usage:

`delete 1`

Expected outcome:

A message will be printed to show the task being deleted.

```
Noted! I have removed this task:
    [T][X] return book
You now have 0 task(s) in the list.
```
<br>

### `find` - Find tasks

Finds all tasks with matching keyword.

Example of usage:

`find book`

Expected outcome:

A message will be printed to show the tasks with the matching keyword.

```
Here are the matching tasks in your list:
    1.[T][ ] return book
```
<br>

### `help` - View help

Shows all available commands that Chatter understands.

Example of usage:

`help`

Expected outcome:

A message will be printed to show all available commands.

```
Available commands:
todo <task>             Create a todo task
deadline <task>         Create a deadline task
    /by <deadline>
event <task>            Create an event
    /from <date/time>
    /to <date/time>
list                    List all your events
done <index>            Mark done for task at index
delete <index>          Delete task at index
find <keyword>          Find tasks with keyword
help                    Show all available commands
bye                     Quit bot
```
<br>

### `bye` - Exit program

Exits the program.

Example of usage:

`bye`