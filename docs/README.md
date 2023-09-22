# User Guide

Siri is a **desktop app for managing tasks, optimized for use via a Command-Line Interface (CLI)**
while still having a Graphical User Interface (GUI).


## Table of Contents
- [Quick Start / Setting Up](#quick-start--setting-up)
- [Features](#features)
    - [Adding tasks :](#adding-tasks)
        - [Todo `todo`](#1-todo--todo)
        - [Event `event`](#2-event--event)
        - [Deadline `deadline`](#3-deadline--deadline)
    - [Other Commands :](#other-commands)
      - [Listing your tasks : `list`](#listing-tasks--list)
      - [Deleting your tasks : `delete`](#deleting-your-tasks--delete)
      - [Marking your tasks as done : `mark`](#marking-your-tasks-as-done--mark)
      - [Unmark your tasks : `unmark`](#unmark-your-tasks--unmark)
      - [Finding your tasks : `find`](#finding-your-tasks--find)
      - [Changing duplicate checker mode : `duplicatemode`](#changing-duplicate-checker-mode--duplicatemode)
- [Command Summary](#command-summary)

## Quick Start / Setting Up
1. Ensure you have Java `11` installed in your computer.
2. Download the latest iteration of Siri [here](https://github.com/marioalvaro/ip/releases/tag/A-Release). The specific file to download is `siri-{version}.jar`, e.g. `siri-v0.2.jar`.
3. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

<img src = "Ui.png" width = "300px">

> **Note:** The list is filled with placeholder data.

## Features

### Adding Tasks

### 1. **ToDo** : `todo`
Quick tasks that need to be done soon without the need of a specific time or place.

**Format:** `todo <TODO_DESCRIPTION>`

**Examples:**
- `todo Homework`
- `todo Laundry`
- `todo CS3233 Contest`

**Example input:**
```
todo Homework
```

**Expected output:**
```
Got it. I've added this task:
[T][ ] Homework
Now you have 1 tasks in the list.
```

### 2. **Event**: `event`
Task of type Event ranging from .

**Format:** `event <EVENT_NAME> /from <YYYY-MM-DD> <HHMM> /to <YYYY-MM-DD> <HHMM>`

**Examples:**
- `event Welfare /from 2003-11-12 2000 /to 2004-01-01 1000`
- `event Angklung Exercise /from 2023-10-10 1900 /to 2023-10-10 2100`

**Example input:**
```
event Welfare /from 2003-11-12 2000 /to 2004-01-01 1000
```

**Expected output:**
```
Got it. I've added this task:
 [E][ ] Welfare (from: Nov 12 2003 20:00 to: Jan 01 2004 10:00)
Now you have 1 tasks in the list.
```
### 3. **Deadline** : `deadline`
Tasks that need to be done before a specific time.

**Format:** `deadline <DEADLINE_NAME> /by <YYYY-MM-DD> <HHMM>`

**Examples:**
- `deadline CS2100 /by 2010-09-11 2359`
- `deadline admin stuff /by 2023-10-23 1000`
- `deadline Lab CS2100 /by 2023-08-03 1300`

**Example input:**
```
deadline Lab CS2100 /by 2023-08-03 1300
```

**Expected output:**
```
Got it. I've added this task:
 [D][ ] Lab CS2100 (by: Aug 03 2023 13:00)
Now you have 1 tasks in the list.
```

### Other Commands

### Listing tasks : `list`
Lists the tasks that have been added to the bot.

**Format:** `list`

**Expected output:**
```
Here are the tasks in your list:
 1.[E][ ] Welfare (from: Nov 12 2003 20:00 to: Jan 01 2004 10:00)
 2.[D][ ] Lab CS2100 (by: Aug 03 2023 13:00)
 3.[T][ ] Laundry
```
### Deleting your tasks : `delete`

Deletes a task that has been added to the bot.

**Format:** `delete <TASK_INDEX>`
- Deletes task at the specified index.
- The index refers to the index number shown in the displayed list.
- The index must be a positive integer 1, 2, 3, 4, ...
- The index must be less than the list of task size

**Examples:**
- `delete 3` deletes the second task in the list.

**Expected output:**
```
Noted. I've removed this task:
  [T][ ] Laundry
Now you have 2 tasks in the list.
```

### Marking your tasks as done: `mark`
- Marks a task as done. 
- Tasks that have been marked will be denoted with an "X".
- The index must be a positive integer 1, 2, 3, 4, ...
- The index must be less than the list of task size

**Format:** `mark <TASK_INDEX>`

**Examples:**
- `mark 2` marks the second task in the list.

**Expected output:**
```
Nice! I've marked this task as done:
 [D][X] Lab CS2100 (by: Aug 03 2023 13:00)
```

### Unmark your tasks: `unmark`

- Unmark a task as done. 
- Tasks that have been unmarked will be denoted with a blank space [ ].
- The index must be a positive integer 1, 2, 3, 4, ...
- The index must be less than the list of task size

**Format:** `unmark <TASK_INDEX>`

**Examples:**
- `unmark 2` unmarks the second task in the list.

**Expected output:**
```
OK, I've marked this task as not done yet:
 [D][ ] Lab CS2100 (by: Aug 03 2023 13:00)
```

### Finding your tasks : `find`
Finds a task with the given keyword, if exist.

**Format:** `find <KEYWORD>`
- Keywords can consist of more than one word.
- Keywords can only be the **description of the task**, not the dates or time.
- This feature is case-sensitive.
- returns empty list if the there is no task with the keyword

**Examples:**

- `find Angklung`
- `find Lab CS2100`

**Example input:**
```
find Angklung
```

**Expected Output:**
```
Here are the tasks in your list:
1.[E][ ] Angklung Exercise (from: Oct 10 2023 19:00 to: Oct 10 2023 21:00)
```

### Changing Duplicate checker mode : `duplicatemode`

- Change the duplicate checker mode between 3 modes:
- Mode ON: Siri will check the duplicates based on the text description **and** timing.
- Mode TEXT: Siri will check duplicates **ONLY** based on the text description and the type of task.
- Mode OFF: Siri will not check any duplicates
- Duplicates will only be checked when attempting to add a new task.

**Format:** `duplicatemode <MODE>`
- MODE value is either: `on`, `text`, `off`

**Examples:**
- `duplicatemode on`
- `duplicatemode off`
- `duplicatemode text`

**Example input:**
```
duplicatemode text
```

**Expected Output:**
```
OK, update duplicates mode.
```

**Working example:**

Assume the mode is text and there already exist these tasks:
```
Here are the tasks in your list:
 1.[T][ ] Laundry
 2.[D][X] CS2100 Lab (by: Oct 23 2023 23:59)
```
With the input:
```
deadline CS2100 Lab /by 2024-12-12 1000
```
The output will be:
``` 
☹ OOPS!!! Duplicate Task Detected. 
```


### Exiting the program
Exit the task.

**Input:**
```
bye
```

The program will exit.



## Command Summary

| Action                                                                             |                                 Format                                 | Example                                                   | Output                                                                                                                                                                        | 
|------------------------------------------------------------------------------------|:----------------------------------------------------------------------:|-----------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Add Todo](#1-todo--todo)                                                          |                       `todo <TODO_DESCRIPTION>`                        | `todo Homework`                                           | Got it. I've added this task: <br> [T][ ] Homework <br> Now you have 1 tasks in the list.                                                                                     |
| [Add Event](#2-event--event)                                                       | `event <EVENT_NAME> /from <YYYY-MM-DD> <HHMM> /to <YYYY-MM-DD> <HHMM>` | `event Welfare /from 2003-11-12 2000 /to 2004-01-01 1000` | Got it. I've added this task: <br> [E][ ] Welfare (from: Nov 12 2003 20:00 to: Jan 01 2004 10:00) <br> Now you have 1 tasks in the list.                                      |
| [Add Deadline](#3-deadline--deadline)                                              |           `deadline <DEADLINE_NAME> /by <YYYY-MM-DD> <HHMM>`           | `deadline Lab CS2100 /by 2023-08-03 1300`                 | Got it. I've added this task: <br> [D][ ] Lab CS2100 (by: Aug 03 2023 13:00) <br> Now you have 1 tasks in the list.                                                           |
| [Listing tasks](#listing-tasks--list)                                              |                                 `list`                                 | `list`                                                    | Here are the tasks in your list: <br> 1.[E][ ] Welfare (from: Nov 12 2003 20:00 to: Jan 01 2004 10:00) <br> 2.[D][ ] Lab CS2100 (by: Aug 03 2023 13:00) <br> 3.[T][ ] Laundry | 
| [Deleting your tasks](#deleting-your-tasks--delete)                                |                         `delete <TASK_INDEX>`                          | `delete 3`                                                | Noted. I've removed this task: <br> [T][ ] Laundry <br> Now you have 2 tasks in the list.                                                                                     |
| [Marking your tasks](#marking-your-tasks-as-done--mark)                            |                          `mark <TASK_INDEX>`                           | `mark 2`                                                  | Nice! I've marked this task as done: <br> [D][X] Lab CS2100 (by: Aug 03 2023 13:00)                                                                                           |
| [Unmark your tasks](#unmark-your-tasks--unmark)                                    |                         `unmark <TASK_INDEX>`                          | `unmark 2`                                                | OK, I've marked this task as not done yet: <br> [D][ ] Lab CS2100 (by: Aug 03 2023 13:00)                                                                                     |
| [Finding your tasks](#finding-your-tasks--find)                                    |                            `find <KEYWORD>`                            | `find Angklung`                                           | Here are the tasks in your list: <br> 1.[E][ ] Angklung Exercise (from: Oct 10 2023 19:00 to: Oct 10 2023 21:00)                                                              |
| [Changing Duplicate checker mode](#changing-duplicate-checker-mode--duplicatemode) |                         `duplicatemode <MODE>`                         | `duplicatemode text`                                      | OK, update duplicates mode.                                                                                                                                                   |
