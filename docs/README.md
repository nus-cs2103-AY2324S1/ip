# Moira<sup>TM</sup>

A feisty chat bot to help you **manage your to-do list**! Optimized for use via a **Command Line Interface (CLI)**, simply type in a few simple commands and get Moira<sup>TM</sup> up to speed on your latest schedule so that you'll never forget a single thing again!

+ [Getting Started](#getting-started)
+ [Features](#features)
  + [Listing All Tasks: `list`](#listing-all-tasks-list)
  + [Adding a ToDo Task: `todo`](#adding-a-todo-task-todo)
  + [Adding a Deadline Task: `deadline`](#adding-a-deadline-task-deadline)
  + [Adding an Event: `event`](#adding-an-event-event)
  + [Deleting a Task: `delete`](#deleting-a-task-delete)
  + [Marking a Task as Complete: `mark`](#marking-a-task-as-complete-mark)
  + [Marking a Task as Incomplete: `unmark`](#marking-a-task-as-incomplete-unmark)
  + [Finding Tasks by Keyword: `find`](#finding-tasks-by-keyword-find)
  + [Sorting the Task List: `sort`](#sorting-the-task-list-sort)
  + [Exiting the Application: `bye`](#exiting-the-application-bye)
 + [Command Summary](#command-summary)

# Getting Started

1. Download the latest build [here](https://github.com/wujy28/ip/releases/tag/A-Release).
   + Under 'Assets', click on 'moira.jar' to begin your download.
2. Locate the download and move it to a convenient location.
3. Launch the application by clicking on the file.
4. Type the command in the command box and press Enter to execute it.
5. Refer to the [Features](#features) below for details of each command.

# Features 

## Listing All Tasks: `list`

Shows a list of all tasks on the task list.

**Format:** `list`

## Adding a ToDo Task: `todo`

Adds a task without a deadline to the task list.

**Format:** `todo TASK_DESCRIPTION`

**Parameters:**

1. `TASK_DESCRIPTION` The description of the task

**Example:**
```
todo read books
```

## Adding a Deadline Task: `deadline`

Adds a task with a deadline to the task list.

**Format:** `deadline TASK_DESCRIPTION /by DEADLINE`

**Parameters:**

1. `TASK_DESCRIPTION` The description of the task

2. `DEADLINE` The deadline of the task in the format, yyyy-MM-dd HH:mm

**Example:**
```
deadline math assignment /by 2023-09-29 17:00
```

## Adding an Event: `event`

Adds an event with a start and end time to the task list.

**Format:** `event TASK_DESCRIPTION /from START_TIME /to END_TIME`

**Parameters:**

1. `TASK_DESCRIPTION` The description of the task

2. `START_TIME` The starting time of the event in the format, yyyy-MM-dd HH:mm

3. `END_TIME` The ending time of the event in the format, yyyy-MM-dd HH:mm

**Example:**
```
event violin lesson /from 2023-10-17 14:00 /to 2023-10-17 16:00
```

## Deleting a Task: `delete`

Deletes the specified task from the task list.

**Format:** `delete INDEX`

**Parameters:**

1. `INDEX` The task's index number shown in the displayed task list (positive whole number)

**Example:**
```
delete 1
```

## Marking a Task as Complete: `mark`

Marks the specified task on the task list as complete.

**Format:** `mark INDEX`

**Parameters:**

1. `INDEX` The task's index number shown in the displayed task list (positive whole number)

**Example:**
```
mark 1
```

**Expected Output:** The checkbox [ ] beside the task's description should be marked with an "X", e.g. `[T][X] read books`

## Marking a Task as Incomplete: `unmark`

Marks the specified task on the task list as incomplete.

**Format:** `unmark INDEX`

**Parameters:**

1. `INDEX` The task's index number shown in the displayed task list (positive whole number)

**Example:**
```
unmark 1
```

**Expected Output:** The checkbox [ ] beside the task's description should be empty, e.g. `[T][ ] read books`

## Finding Tasks by Keyword: `find`

Finds all tasks on the task list containing the specified keyword.

**Format:** `find KEYWORD`

**Parameters:**

1. `KEYWORD` The keyword to search by (case-insensitive)

**Example:**
```
find read
```

**Expected Output:** A list of search results.

> **NOTE:**
> The indices of the tasks in the list of search results DO NOT correspond to their original indices in the complete task list. Please refer to the original index of each task when running other commands.

## Sorting the Task List: `sort`

Sorts the task list by the specified property.

**Format:** `sort PROPERTY`

**Parameters:**

1. `PROPERTY` The property used to sort the task list. It can be one of the following:
   + `description` to sort by description in alphabetical order
   + `date` to sort by earliest deadline
   + `type` to sort by task type (ToDo, Deadline, Event)

**Example:**
```
sort description
```

**Expected Output:** The sorted task list.

## Exiting the Application: `bye`

Exits the application.

**Format:** `bye`

> **WARNING:**
> Always exit the application with the `bye` command. If you close it by any other means (e.g. using the close button of the window or forcing the application to close), your data WILL NOT be saved to the hard disk and will be lost.

## Command Summary

| Action | Format, Examples |
| :---         | :---           |
| Add a Todo Task   | `todo DESCRIPTION` <br />e.g. `todo read books`  |
| Add a Deadline Task   | `deadline DESCRIPTION /by yyyy-MM-dd HH:mm` <br />e.g. `deadline math assignment /by 2023-09-29 17:00`  |
| Add an Event   | `event DESCRIPTION /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm` <br />e.g. `event violin lesson /from 2023-10-17 14:00 /to 2023-10-17 16:00`  |
| Delete | `delete INDEX` <br />e.g. `delete 1` |
| List | `list` |
| Mark | `mark INDEX` <br />e.g. `mark 1` |
| Unmark | `unmark INDEX` <br />e.g. `unmark 1` |
| Find | `find KEYWORD` <br />e.g. `find read` |
| Sort | `sort PROPERTY` <br />i.e. `sort description`, `sort date`, or `sort type` |
| Exit | `bye` |
