# User Guide

TaskMate is a desktop application designed for task management, ideally utilized through a Command-Line Interface (CLI),
all the while retaining the advantages of a Graphical User Interface (GUI).

## Features 

### Add Task

TaskMate supports adding three different categories of tasks:
1. todo: A task without any date/time attached to it (e.g., learn HTML/CSS one day)
2. deadline: A task that needs to be done before a specific date/time (e.g., purchase anniversary gift by 2023-09-19)
3. event: A task that start at a specific date/time and ends at a specific date/time
   (e.g., learning journey 2023-09-23 to 2023-09-24)

### Delete Task

TaskMate permits you to delete tasks from your task-list that you no longer wish to keep.

### Listing All Tasks

TaskMate enables you to list down the tasks in your task-list, along with accompanying information
indicating whether each task has been completed or not.

### Finding Tasks

TaskMate enables you to find tasks within your task list by using keywords found in the task descriptions or dates.

### Mark & Unmark Tasks

TaskMate offers the capability to mark tasks in your task-list as completed, and subsequently, if desired, to revert
(unmark) them back to an incomplete status.

### Updating Tasks

TaskMate allows you to change (update) the names and/or dates of the tasks in your task-list.

### Call for "help"!!!

In case you've forgotten the syntax of available commands, simply call for help, and TaskMate will provide a detailed
list of each command along with its corresponding syntax.

## Usage

### `todo` - Add a *todo* task to your task-list

This command creates a *todo* task for you and adds it to your task-list. Note that a *todo* task is a task **without
any dates** associated to it.

Example of usage: 

`todo learn HTML/CSS one day`

Expected outcome:

The *todo* task should appear in your task-list.

```
Got it. I've added this task:
   [T][ ] learn HTML/CSS one day
Now you have 1 task(s) in the list.
```

### `deadline` - Add a *deadline* task to your task-list

This command creates a *deadline* task for you and adds it to your task-list. Note that a *deadline* task is a task that
has a date representing a deadline attached to it. This date must be in the "YYYY-mm-dd" format.

Example of usage:

`deadline purchase anniversary gift /by 2023-09-19`

Expected outcome:

The *deadline* task should appear in your task-list.

```
Got it. I've added this task:
   [D][ ] purchase anniversary gift (by: Sep 19 2023)
Now you have 2 task(s) in the list.
```

### `event` - Add an *event* task to your task-list

This command creates a *event* task for you and adds it to your task-list. Note that an *event* task is a task that
has two dates representing the start-date and the end-date attached to it. These dates must be in the "YYYY-mm-dd"
format.

Example of usage:

`event learning journey /from 2023-09-23 /to 2023-09-24`

Expected outcome:

The *deadline* task should appear in your task-list.

```
Got it. I've added this task:
   [E][ ] learning journey (from: Sep 23 2023 to: Sep 24 2023)
Now you have 3 task(s) in the list.
```

### `mark` - mark a task in your task-list a complete

This command adds a label 'X' next to your task that represents if the task has been completed.

Example of usage:

`mark 3`

Expected outcome:

The third task (*event* task) should be marked with an 'X' next to it.

```
Nice! I've marked this task as done:
   [E][X] learning journey (from: Sep 23 2023 to: Sep 24 2023)
```

### `list` - lists down all the tasks in your task-list

This command lists down the tasks you have in your task-list. It also tells you whether those tasks have been marked as
completed.

Example of usage:

`list`

Expected outcome:

You should see all the remaining tasks in your list.

```
Here are the tasks in your list:
1.[T][ ] learn HTML/CSS one day
2.[D][ ] purchase anniversary gift (by: Sep 19 2023)
3.[E][X] learning journey (from: Sep 23 2023 to: Sep 24 2023)
```

### `find` - finds all the tasks in your list that match a specific keyword / keyphrase

This command lists down the tasks in your task-list that contain a keyword / keyphrase that you specify. It also tells
you whether those tasks have been marked as completed.

Note: This keyword / keyphrase could be a substring from the tasks' name, or a substring from the tasks' dates.

Note: This matching is **case-insensitive**

Example of usage (1):

`find sep`

Expected outcome (1):

You should see all the matching tasks that contain "sep" (case-insensitive) in your task-list.

```
Here are the matching tasks in your list:
1.[D][ ] purchase anniversary gift (by: Sep 19 2023) 
2.[E][X] learning journey (from: Sep 23 2023 to: Sep 24 2023)
```

Example of usage (2):

`find learn`

Expected outcome (2):

You should see all the matching tasks that contain "sep" (case-insensitive) in your task-list.

```
Here are the matching tasks in your list:
1.[T][ ] learn HTML/CSS one day 
2.[E][X] learning journey (from: Sep 23 2023 to: Sep 24 2023)
```

### `delete` - Delete a task from your task-list

This command deletes a task from your task-list. The task is specified by the task number associated to it. You can get
the task number by calling `list`. (See also: `list` command)

Example of usage:

`delete 1`

Expected outcome:

Task 1 should be removed from your task-list.

```
Noted. I've removed this task:
   [T][ ] learn HTML/CSS one day
Now you have 2 task(s) in the list.
```





