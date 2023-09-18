# HelpBuddy User Guide :robot:

HelpBuddy is a chatbot; **it is a Graphical User Interface (GUI) desktop app for managing your tasks.** If you like a 
personal assistant, HelpBuddy can help you keep track of your tasks in mind.

## Quick start

1. Click on `Releases` and select `v0.3`.
2. Download the `helpbuddy.jar` file. 
3. Open your terminal and navigate to the directory where `helpbuddy.jar` is downloaded. 
4. Run `java -jar helpbuddy.jar` and chat away with HelpBuddy! :smile:

## Features 

HelpBuddy supports a list of commands to ease your management of tasks. It includes:
1. Add tasks 
2. List 
3. Delete tasks 
4. Find tasks 
5. Mark tasks 
6. Unmark tasks 

### 1. Add tasks: `todo`, `deadline`, `event`

Adds a task for HelpBuddy to keep track of. HelpBuddy supports 3 types of tasks:
1. ToDo
2. Deadline
3. Event

### Usage

### `todo` - Adds a ToDo task

:information_source:Note about command format:

Input only the name of task after the command `todo`.

Example of usage: 

`todo watch CS2103T lecture`


Expected outcome:

```
Got it. I've added this task:
[T][ ] watch CS2103T lecture
Now you have 1 tasks in the list.
```

### `deadline` - Adds a Deadline task

:information_source:Note about command format:

Input the name of task after the command `deadline`, followed by `/by` command, with the deadline of the task in 
`DD/MM/YY HH:mm`.

Example of usage:

`deadline finish IP tasks /by 22/09/23 23:59`

Expected outcome:

```
Got it. I've added this task:
[D][ ] finish IP tasks (by: Sep 22 2023 23:59)
Now you have 2 tasks in the list.
```

### `event` - Adds an Event task

:information_source:Note about command format:

Input the name of task after the command `event`, followed by `/from` command, with the start time of the event in 
`DD/MM/YY HH:mm` and lastly, `/to` command,  with the end time of the event in `DD/MM/YY HH:mm`.

Example of usage:

`event CS2103T meeting /from 18/09/23 16:30 /to 18/09/23 17:30`

Expected outcome:

```
Got it. I've added this task:
[E][ ] CS2103T meeting (from: Sep 18 2023 16:30 to: 
Sep 18 2023 17:30)
Now you have 3 tasks in the list.
```

### 2. List tasks: `list`

Lists all the task that you asked HelpBuddy to keep track of.

### Usage

### `list` - Lists all tasks added

:information_source:Note about command format:

Input only the command `list`.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] watch CS2103T lecture
2. [D][ ] finish IP tasks (by: Sep 22 2023 23:59)
3. [E][ ] CS2103T meeting (from: Sep 18 2023 16:30 to: 
Sep 18 2023 17:30)
```

### 3. Delete tasks: `delete`

Deletes a task that HelpBuddy no longer needs to keep track of.

### Usage

### `delete` - Deletes a task

:information_source:Note about command format:

Input the index of task in the task list after the command `delete`.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][ ] watch CS2103T lecture
Now you have 2 tasks in the list.
```

### 4. Find tasks: `find`

Finds and produces a list of task that HelpBuddy is keeping track of by searching a keyword.

### Usage

### `find` - Finds relevant tasks

:information_source:Note about command format:

Input the description of task after the command `find`. Note that the description of task is **case sensitive**.

Example of usage:

`find IP`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][ ] finish IP tasks (by: Sep 22 2023 23:59)
```

### 5. Mark tasks: `mark`

Marks a specific task in the task list as done.

### Usage

### `mark` - Marks specific task

:information_source:Note about command format:

Input the index of task in the task list after the command `mark`.

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
      [D][X] finish IP tasks (by: Sep 22 2023 23:59)
```

### 6. Unmark tasks: `mark`

Unmarks a specific task in the task list from done to not done.

### Usage

### `unmark` - Unmarks specific task

:information_source:Note about command format:

Input the index of task in the task list after the command `unmark`.

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
      [D][] finish IP tasks (by: Sep 22 2023 23:59)
```
