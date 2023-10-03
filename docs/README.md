# User Guide
DukeMax is your task management assistant.

It is optimized with a Command Line Interface (CLI) and enjoying the benefits of a Graphical User Interface (GUI).

Download and run the jar file as the following to use it!

1. Copy the jar file into an empty folder.
2. Open a command window in that folder.
3. Run the command `java -jar duke.jar` 

## Features 
### Introducing Tasks
- There are 3 types of tasks: To-do, Deadline, and Event.

- You can create, remove, mark, unmark, print, and find these tasks.

## Usage - Commands

### Quick Navigation
1. Create Task Commands [Link](#create-related)
2. Manage Task Commands [Link](#manage-related)
3. View Task Commands [Link](#view-related)
4. Exit Command [Link](#exit-command)
> **Note**
> All commands are NOT case-sensitive.
---
### Create-related
### `todo` 
This command adds a Todo task.
Format: `todo TASK_DESCRIPTION`
* There is no time related to a todo task.

Example: `todo task 1`

Expected outcome: 
```
Got it! This task has been added:
[T-D][In progress] task 1
Current # of task: 1
```
---
### `deadline` 
This command adds a Deadline task.
Format: `deadline TASK_DESCRIPTION /by DATE_TIME`
* Deadline task has a task description and a deadline.
* DATE_TIME format: `yyyy-MM-dd HH:mm` `yyyy-MM-dd` `HH:mm`.

Example: `deadline task 2 /by: 2023-01-01`

Expected outcome: 
```
Got it! This task has been added:
[DDL][In progress] task 2 (by: Jan. 1 2023)
Current # of task: 2
``` 
---
### `event`
This command adds an Event task.

Format: `event TASK_ESCRIPTION /from DATE_TIME /to DATE_TIME`
* Event task has a task description, start time, and end time.
* DATE_TIME format: `yyyy-MM-dd HH:mm` `yyyy-MM-dd` `HH:mm`.

Example: `event task 3 /from 2023-01-01 /to 2023-01-02`

Expected outcome: 
```
Got it! This task has been added:
[EVT][In progress] task 3 (from: Jan. 1 2023 to Jan. 2 2023)
Current # of task: 3
```
---
### Manage-related
### `mark`
This task marks a task as completed.

Format: `mark TASK_INDEX`

Example: `mark 1`
Expected outcome: 
```
Nice! I've marked this task as complete:
[T-D][ Completed ] task 1
Here's a lollipop.
```
---
### `unmark`
This command marks a task as incomplete.

Format: `unmark TASK_INDEX`

Example: `unmark 1`

Expected outcome:
```
OK, I've marked this task as incomplete yet:
[T-D][In progress] task 1
Keep up with the good work.
```
---
### `delete`
This command deletes a task.
> **Warning**
> Tasks cannot be recovered after being cleared.

Format: `delete TASK_INDEX`

Example: `delete 1`

Expected outcome:
```
I've removed this task:
[T-D][In progress] task 1
Current # of tasks: 2
```
---
### `clear`
This command clears all tasks saved.
> **Warning**
> Tasks cannot be recovered after being cleared.

Example: `clear`

Expected outcome:
```
Okay, I have cleared all tasks.
```
---
### View-related
### `print`
This command lists all existing tasks

Example: `print`

Expected outcome:
```
You have:
1. [T-D][In progress] task 1
2. [DDL][In progress] task 2 (by: Jan. 1 2023)
3. [EVT][In progress] task 3 (from: Jan. 1 2023 to Jan. 2 2023)
Current # of tasks: 3
```
---
### `print DATE_TIME`
This command prints tasks with matching dates.

Format: `print DATE_TIME`

Example: `print 2023-01-02`

Expected outcome:
```
1. [DDL][In progress] task 2 (by: Jan. 1 2023)
2. [EVT][In progress] task 3 (from: Jan. 1 2023 to Jan. 2 2023)
Current # of tasks at Jan. 1 2023: 2
```
---
### `find`
This command prints tasks with matching task descriptions.

Format: `find TASK_DESCRIPTION`

Example: `find task 1`

Expected outcome:
```
1. [T-D][In progress] task 1
Current # of tasks with task 1: 1
```
---
### Exit-Command
### `bye` 
This command exits the chatbot
Example: `bye`

Expected outcome:
```
Bye! Hope to see you again soon!
```
