# Cheesebot User Guide

Cheesebot is a simple command-line task management application that helps you keep track of your tasks. It supports three types of tasks: todo, deadline, and event.

## Features
### Add Todo Task

Add a new todo task to your task list.

Example of usage:

```
todo Buy groceries

```
Expected outcome:

Cheesebot adds a new todo task to your list.

```
Added: [T][ ] Buy groceries
```

### Add Deadline Task

Add a new deadline task to your task list. You can specify the deadline date and time.

Example of usage:

```
deadline Submit report /by 2023-12-31 23:59

```
Expected outcome:

Cheesebot adds a new deadline task to your list.

```
Added: [D][ ] Submit report (by: Dec 31 2023 23:59)

```
### Add Event Task

Add a new event task to your task list. You can specify the event's date and time range.

Example of usage:

```
event Project meeting /from 2023-09-20 14:00 /to 2023-09-20 15:30

```
Expected outcome:

Cheesebot adds a new event task to your list.

```
Added: [E][ ] Project meeting (from: Sep 20 2023 14:00 to: Sep 20 2023 15:30)

```
### Mark Task as Done

Mark a task as done by specifying its index.

Example of usage:

```
mark 1

```
Expected outcome:

Cheesebot marks the task as done.


```
Nice! I've marked this task as done:
[X] Buy groceries

```
### List Tasks

List all tasks in your task list.

Example of usage:

```
list

```
Expected outcome:

Cheesebot displays all your tasks.


```
Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [D][ ] Submit report (by: Dec 31 2023 23:59)
3. [E][ ] Project meeting (from: Sep 20 2023 14:00 to: Sep 20 2023 15:30)

```
### Delete Task

Delete a task from your task list by specifying its index.

Example of usage:

```
delete 2

```
Expected outcome:

Cheesebot removes the task from your list.

```
Noted. I've removed this task:
[X] Submit report (by: Dec 31 2023 23:59)

```
### Exit

Exit Cheesebot.

Example of usage:
```
bye
```
Expected outcome:
``````
Cheesebot says goodbye.


Bye. Hope to see you again soon!
``````

### Help

```
Command Summary

    Add todo task: todo DESCRIPTION
    Add deadline task: deadline DESCRIPTION /by DATE TIME
    Add event task: event DESCRIPTION /from DATE TIME /to DATE TIME
    Mark task as done: mark INDEX
    List all tasks: list
    Delete task: delete INDEX
    Exit Cheesebot: bye

```
Feel free to use Cheesebot to manage your tasks efficiently!
