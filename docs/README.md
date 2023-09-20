# TaskMate - Your Personal Task-Tracking Assistant

# User Guide

TaskMate is a desktop application designed for task management, ideally utilized through a Command-Line Interface (CLI),
all the while retaining the advantages of a Graphical User Interface (GUI). It is recommended to follow along with the
commands in this guide in a step-by-step manner as the expected output assumes you have been following the commands
sequentially.

![TaskMate GUI](https://elhy1999.github.io/ip/Ui.png "TaskMate Gui")

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

TaskMate enables you to find tasks within your task list by using keywords / keyphrases found in the tasks' names
or dates.

### Mark & Unmark Tasks

TaskMate offers the capability to mark tasks in your task-list as completed, and subsequently, if desired, to revert
(unmark) them back to an incomplete status.

### Updating Tasks

TaskMate allows you to change (update) information about existing tasks in your task-list.

### Call for "help"!!!

In case you've forgotten the syntax of available commands, simply call for help, and TaskMate will provide a detailed
list of each command along with its corresponding syntax.

### Saving your data
When you close TaskMate using the `bye` command, TaskMate automatically saves your task data onto your computer so that
when you boot TaskMate up again in the future, this data will be loaded and you will not need to retype all your tasks
from scratch.

## Usage

### `todo` - Add a *todo* task to your task-list

This command creates a *todo* task for you and adds it to your task-list. Note that a *todo* task is a task **without
any dates** associated to it.

#### **Syntax**: `todo <name>`

`<name>`: The name of the *todo* task

#### Example of usage: 

`todo learn HTML/CSS one day`

#### Expected outcome:

The *todo* task should appear in your task-list.

```
Got it. I've added this task:
   [T][ ] learn HTML/CSS one day
Now you have 1 task(s) in the list.
```

### `deadline` - Add a *deadline* task to your task-list

This command creates a *deadline* task for you and adds it to your task-list. Note that a *deadline* task is a task that
has a date representing a deadline attached to it. This date must be in the "YYYY-mm-dd" format.

#### **Syntax**: `deadline <name> /by: <by>`

`<name>`: The name of the *deadline* task

`<by>`: A datetime representing **by when** does the task have to be completed. This datetime has to be in the
YYYY-mm-dd format

#### Example of usage:

`deadline purchase anniversary gift /by 2023-09-19`

#### Expected outcome:

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

#### **Syntax**: `event <name> /from: <from> /to <to>`

`<name>`: The name of the *deadline* task

`<from>`: A datetime representing the date which the event **starts from**. This datetime has to be in the YYYY-mm-dd
format

`<to>`: A datetime representing the date which the event **lasts to**. This datetime has to be in the YYYY-mm-dd format

#### Example of usage:

`event learning journey /from 2023-09-23 /to 2023-09-24`

#### Expected outcome:

The *event* task should appear in your task-list.

```
Got it. I've added this task:
   [E][ ] learning journey (from: Sep 23 2023 to: Sep 24 2023)
Now you have 3 task(s) in the list.
```

### `mark` - mark a task in your task-list as complete

This command adds a label 'X' next to your task that represents if the task has been completed.

#### **Syntax**: `mark <index>`

`<index>`: An integer from 1 to the number of tasks available which represents the task you wish to mark as complete. To
view the index of the task you wish to mark, use the `list` command. (See also: `list`)

#### Example of usage (1):

`mark 3`

#### Expected outcome (1):

The third task (*event* task) should be marked with an 'X' next to it.

```
Nice! I've marked this task as done:
   [E][X] learning journey (from: Sep 23 2023 to: Sep 24 2023)
```

#### Example of usage (2):

`mark 1`

#### Expected outcome (2):

The first task (*todo* task) should be marked with an 'X' next to it.

```
Nice! I've marked this task as done:
   [T][X] learn HTML/CSS one day
```

### `unmark` - label a task in your task-list as incomplete

This command removes the label 'X' next to your task (if it exists. Otherwise, this command does nothing).

#### **Syntax**: `unmark <index>`

`<index>`: An integer from 1 to the number of tasks available which represents the task you wish to revert back to the
"incomplete" status. To view the index of the task you wish to unmark, use the `list` command. (See also: `list`)

#### Example of usage:

`unmark 1`

#### Expected outcome:

The third task (*event* task) should be marked with an 'X' next to it.

```
OK, I've marked this task as not done yet:
[T][ ] learn HTML/CSS one day 
```

### `list` - lists down all the tasks in your task-list

This command lists down the tasks you have in your task-list. It also tells you whether those tasks have been marked as
completed.

#### **Syntax**: `list`

#### Example of usage:

`list`

#### Expected outcome:

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

#### **Syntax**: `find <query>`

`<query>`: A String representing the keyword / keyphrase you which to find matches on

#### Example of usage (1):

`find sep`

#### Expected outcome (1):

You should see all the matching tasks that contain "sep" (case-insensitive) in your task-list.

```
Here are the matching tasks in your list:
1.[D][ ] purchase anniversary gift (by: Sep 19 2023) 
2.[E][X] learning journey (from: Sep 23 2023 to: Sep 24 2023)
```

#### Example of usage (2):

`find learn`

#### Expected outcome (2):

You should see all the matching tasks that contain "sep" (case-insensitive) in your task-list.

```
Here are the matching tasks in your list:
1.[T][ ] learn HTML/CSS one day 
2.[E][X] learning journey (from: Sep 23 2023 to: Sep 24 2023)
```

### `delete` - Delete a task from your task-list

This command deletes a task from your task-list. The task is specified by the task number associated to it. You can get
the task number by calling `list`. (See also: `list` command)

#### **Syntax**: `delete <index>`

`<index>`: An integer from 1 to the number of tasks available which represents the task you wish to delete. To view the
index of the task you wish to delete, use the `list` command. (See also: `list`)


#### Example of usage:

`delete 1`

#### Expected outcome:

Task 1 should be removed from your task-list.

```
Noted. I've removed this task:
   [T][ ] learn HTML/CSS one day
Now you have 2 task(s) in the list.
```

### `update` - Update information about an existing task in your task-list

This command updates / changes information about a task from your task-list. The task is specified by the task number
associated to it. You can get the task number by calling `list`. (See also: `list` command)

You may chain all the attributes you wish to update
into a single command. See the example below which demonstrates changing the /name and /from attribute of a single
*event* task.

#### **Syntax**: `update <index> <TAG> <newValue> ...`

`<index>`: An integer from 1 to the number of tasks available which represents the task you wish to delete. To view the
index of the task you wish to delete, use the `list` command. (See also: `list`)

`<TAG>`: This tag represents which task attribute you which to update. Reference the table below for valid `<TAG>`
values for each task type:

| Task Type | Possible `<TAG>` values |
|-|-|
| todo | /name |
| deadline | /name, /by |
| event | /name, /from, /to |

`<newValue>`: The new value you which to change the task's `<TAG>` attribute to. Note that if `<TAG>` is /by, /from, or
/to, then `<newValue>` must be a datetime of the form YYYY-mm-dd.

#### Example of usage:

`update 2 /from 2023-09-21 /name learning journey to Science Centre`

#### Expected outcome:

The /from and /name attributes of the *event* task should be updated to the new values.

```
Updates successfully made to task 2:
name: learning journey to Science Centre
from: 2023-09-21
```

### `help` - lists down the available commands and their syntax

This command is helpful for users in the beginning who may not be used to the syntax of TaskMate. If you wish to find
the syntax of specific commands, call for `help` and TaskMate will assist you.

Note: This command also gives you the absolute path of the folder where your tasks' data will be saved.

#### **Syntax**: `help`

#### Example of usage:

`help`

#### Expected outcome:

You should see all the commands and their syntax. As the help page is pretty verbose, only part of the expected outcome
is shown below.

```
Adding Tasks:
1. Todo tasks: todo <name>
2. Deadline tasks: deadline <name> /by <date>
3. Event tasks: event <name> /from <date> /to <date>

Marking and Unmarking Tasks:
1. Marking tasks as completed: mark <integer>
2. Unmarking tasks as incomplete: unmark <integer>

Deleting Tasks:
1. delete <integer>

Updating Tasks:
1. update <integer> <TAG> <newValue> ...
...
```

### `bye` - exit TaskMate and save your tasks to disk

This command closes TaskMate and saves all information about your undeleted tasks to your laptop. When you boot up
TaskMate again, your data will be loaded and you do not need to input all your tasks from scratch again. To view the
location on your computer where your data will be stored, call for `help`. (See also: `help`)

Note: If you do not call `bye` and close the application in any other way, **TaskMate will not save your data**.

#### **Syntax**: `bye`

#### Example of usage:

`bye`

#### Expected outcome:

TaskMate closes and your data is saved for future reference.
