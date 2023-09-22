# ADJ

ADJ is a **chatbot that will help you manage your tasks**. For **efficiency**, ADJ is optimized for use via a **Command Line Interface (CLI)** while still having
the benefits of a **Graphical User Interface (GUI)**.

## Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
  * [Viewing help: `help`](#viewing-help--help)
  * [Adding tasks:](#adding-tasks)
    * [ToDo: `todo`](#1-todo--todo)
    * [Deadline: `deadline`](#2-deadline--deadline)
    * [Event: `event`](#3-event--event)
  * [List all current tasks: `list`](#list-all-current-tasks--list)
  * [Find tasks by keyword: `find`](#find-tasks-by-keyword--find)
  * [Delete a task by index: `delete`](#delete-a-task-by-index--delete)
  * [Mark a task by index: `mark`](#mark-a-task-by-index--mark)
  * [Unmark a task by index: `unmark`](#unmark-a-task-by-index--unmark)
  * [Exit : `bye`](#exit--bye)
* [FAQ](#faq)
* [Command Summary](#command-summary)
* [Uncovered Issues?](#uncovered-issues)

## Quick Start
1. Ensure you have Java `11` or above installed
   > Refer [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) for the installation guide. 
2. Download the latest release of adj.jar [here](https://github.com/AndrewJanong/ip/releases).
3. Copy the file to a folder as the _home folder_ that would also contain your task data.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar adj.jar` command to run the application. Alternatively, double-clicking can also launch the JAR file. <br>
   A GUI similar to the below should appear in a few seconds.

<img src="Ui.png" width="500px">

> Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window. <br>
> Refer to the [Features](#features) below for details of each command.
   

## Features

> **Note** <br>
> Words in UPPER_CASE represents parameter that you should supply (e.g in todo TASK, TASK is a parameter which can used as todo CS2103T Quiz)

### Viewing help: `help`

Shows a message containing all available commands

Format: `help`

### Adding Tasks

Adds a task to your task list. There are three types of tasks:

### 1. ToDo: `todo`

A Todo is a task where you don't need to specify the date to do the task.

Format: `todo TASK`

Examples: 
* `todo Clean Toilet`
* `todo Watch QF1100 Lecture`

Expected output:
```
Got it. I've added this task:
	 [T][ ] Watch QF1100 Lecture
Now you have 2 tasks in your list. Good luck!
```


### 2. Deadline: `deadline`

A Deadline is a task that needs to be done by a specific date.

Format: `deadline TASK /by DUE_DATE`
* Must have a `/by` followed by the `DUE_DATE`
* `DUE_DATE` must be of the form `yyyy-mm-dd`.

Examples:
* `deadline QF1100 Quiz /by 2023-09-21` Adds a deadline with description "QF1100 Quiz" and due date set to be 21st September 2023.
* `deadline Do TikTok OA /by 2023-09-15` Adds a deadline with description "Do TikTok OA" and deadline set to be 15th September 2023.

Expected output:
```
Got it. I've added this task:
	 [D][ ] Do TikTok OA (by: Sep 15 2023)
Now you have 4 tasks in your list. Good luck!
```

### 3. Event: `event`

An Event is a task that happens between a start date and an end date.

Format: `event TASK /from START_DATE /to END_DATE`

* Must have a `/from` and `/to` each followed by `START_DATE` and `END_DATE` respectively.
* `START_DATE` and `END_DATE` must both be of the form `yyyy-mm-dd`.

Examples:
* `event NUS Freshmen Orientation /from 2023-08-07 /to 2023-08-12` Adds an Event with description "NUS Freshmen Orientation" 
where start date and end date will be set to 7th August 2023 and 12th August 2023 respectively.

Expected output:
```
Got it. I've added this task:
	 [E][ ] NUS Freshmen Orientation (from: Aug 7 2023 to: Aug 12 2023)
Now you have 5 tasks in your list. Good luck!
```

### List all current tasks: `list`

Lists all tasks currently on the list.

Format: `list`

Expected output:
```
Here are the tasks in your list:
	 1.[T][ ] Clean Toilet
	 2.[T][ ] Watch QF1100 Lecture
	 3.[D][ ] QF1100 Quiz (by: Sep 21 2023)
	 4.[D][ ] Do TikTok OA (by: Sep 15 2023)
	 5.[E][ ] NUS Freshmen Orientation (from: Aug 7 2023 to: Aug 12 2023)
Keep up the good work!
```


### Find tasks by keyword: `find`

Shows a list of tasks containing a certain keyword. 

Format: `find KEYWORD`

Examples:
* `find TikTok OA`
* `find QF1100`

Expected output:
```
Here are the matching tasks in your list:
1.[T][ ] Watch QF1100 Lecture
2.[D][ ] QF1100 Quiz (by: Sep 21 2023)
```

### Delete a task by index: `delete`

Deletes a specified task by its index from the list.

Format: `delete INDEX`
* Deletes the task at the specified `INDEX`
* The index refers to the index number shown in the task list when we use `list`
* The index **must be a positive integer** and **not more than the length of the list**

Examples: 
* `delete 2` Deletes the task with index 2 from the list

Expected output:
```
Noted. I've removed this task:
[T][ ] Clean Toilet
Now you have 4 tasks in your list. Good luck!
```


### Mark a task by index: `mark`

Marks as done a specified task by its index from the list.

Format: `mark INDEX`
* Marks as done the task at the specified `INDEX`
* The index refers to the index number shown in the task list when we use `list`
* The index **must be a positive integer** and **not more than the length of the list**

Examples:
* `mark 1` Marks as done the task with index 1 from the list

Expected output:
```
Nice job! I've marked this task as done:
	 [T][X] Watch QF1100 Lecture
```


### Unmark a task by index: `unmark`

Unmarks (Marks as not done) a specified task by its index from the list.

Format: `unmark INDEX`
* Marks as not done the task at the specified `INDEX`
* The index refers to the index number shown in the task list when we use `list`
* The index **must be a positive integer** and **not more than the length of the list**

Examples:
* `unmark 3` Unmarks the task with index 3 from the list

Expected output:
```
What happened? I've marked this task as not done yet:
	 [D][ ] Do TikTok OA (by: Sep 15 2023)
```


### Exit : `bye`

Turns off the chatbot.

Format: `bye`


## FAQ
**Q**: Why can't I even start the chatbot? <br>
**A**: Make sure that you have Java `11` or above installed. 
Please refer [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) for the installation guide.

**Q**: How to I transfer my data to another computer? <br>
**A**: Install ADJ in your other computer and replace your data file on that computer with the data file on your current computer which contains data of the tasks of your previous ADJ folder.

## Command summary

| Action                                                    |                   Format                   | Example                                                          |
|-----------------------------------------------------------|:------------------------------------------:|------------------------------------------------------------------|
| [Viewing help](#viewing-help--help)                       |                   `help`                   | `help`                                                           |
| [Add ToDo](#1-todo--todo)                                 |                `todo TASK`                 | `todo Watch QF1100 Lecture`                                      |
| [Add Deadline](#2-deadline--deadline)                     |        `deadline TASK /by DUE_DATE`        | `deadline CS2103T Quiz /by 2023-09-22`                           |
| [Add Event](#3-event--event)                              | `event TASK /from START_DATE /to END_DATE` | `event NUS Freshmen Orientation /from 2023-08-07 /to 2023-08-12` |
| [List all current tasks](#list-all-current-tasks--list)   |                   `list`                   | `list`                                                           |
| [Find tasks by keyword](#find-tasks-by-keyword--find)     |               `find KEYWORD`               | `find Quiz`                                                      |
| [Delete a task by index](#delete-a-task-by-index--delete) |               `delete INDEX`               | `delete 2`                                                       |                                                                   |
| [Mark a task by index](#mark-a-task-by-index--mark)       |                `mark INDEX`                | `mark 2`                                                         |
| [Unmark a task by index](#unmark-a-task-by-index--unmark) |               `unmark INDEX`               | `unmark 2`                                                       |
| [Exit](#exit--bye)                                        |                   `bye`                    | `bye`                                                            |

## Uncovered Issues
If you have any issues with the app, feel free to address you concerns [here](https://github.com/AndrewJanong/ip/issues)
