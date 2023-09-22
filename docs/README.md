# User Guide

Chrainx is a chatbot for managing task, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 

## Table of Content

- [Quick Start](#quick-start)
- [Features](#features)
  - [Add a todo task: `todo`](#add-a-todo-task--todo)
  - [Add a deadline task: `deadline`](#add-a-deadline-task--deadline)
  - [Add an event task: `event`](#add-an-event-task--event)
  - [Add a period task: `period`](#add-a-period-task--period)
  - [Mark a task as done: `mark`](#mark-a-task-as-done--mark)
  - [Mark a task as not done: `unmark`](#mark-a-task-as-not-done--unmark)
  - [Delete a task: `delete`](#delete-a-task--delete)
  - [List all tasks: `list`](#list-all-tasks--list)
  - [Find a task with certain words: `find`](#find-a-task-with-certain-words--find)
  - [Close and save the chatbot: `bye`](#close-and-save-the-chatbot--bye)
- [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest duke.jar from here.
3. Copy the file to the folder you want to use as the home folder for your Chrainx chatbot.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar Chrainx.jar command to run the application.
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

<img src = "Ui.png" width = "300px">

5. Type the command in the command box and press Enter to execute it.
6. Refer to the [Features](#features) below for details of each command.

⤴️ Go back to [Table of Content](#table-of-content)

## Features 

Note:
- Items with <item> format can be replaced with any words.
- Items with [item] format can be replaced with word satisfying requirements.

### Add a todo task: `todo`

Adds a task todo to the list of tasks.

Format: `todo <name>`

Example:
- `todo sleep`
- `todo eat`
- `todo read my novel`

### Add a deadline task: `deadline`

Adds a task deadline to the list of tasks.

Format: `deadline <name> /by [deadline]`

The deadline must be in: yyyy-mm-dd format.

Example:
- `deadline CS2103T IP /by 2023-09-22`
- `deadline CS2103T TP /by 2023-09-24 `
- `deadline Complete 2101 user guide /by 2023-11-18`

### Add an event task: `event`

Adds a task event to the list of tasks.

Format: `event <name> /from [start] /to [end]`

The start must be in: yyyy-mm-dd format.
The end must be in: yyyy-mm-dd format.

Example:
- `event CS2105 /from 2023-09-22 /to 2023-09-25`
- `event Recess Week /from 2023-09-25 /to 2023-09-29`
- `event Study for Midterm /from 2023-09-22 /to 2023-10-01`

### Add a period task: `period`

Adds a task event to the list of tasks.

Format: `event <name> /between <period>`

Example:
- `period CS2103T IP /between 8 P.M. and 11 P.M.`
- `period CS2103T TP /between 2023-09-22`
- `period Complete 2101 user guide /by 2023-11-18`

### Mark a task as done: `mark`

Marks a specified task as done.

Format: `mark [number]` 

The number must be an integer (1,2,3,...).

Example:
- `mark 1`
- `mark 7`
- `mark 12`


### Mark a task as not done: `unmark`

### Delete a task: `delete`

### List all tasks: `list`

### Find a task with certain words: `find`

### Close and save the chatbot: `bye`

## Command Summary

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
