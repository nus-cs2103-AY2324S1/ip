# User Guide

## Table of Contents

- [Getting Started](#getting-started)
- [Features](#features)

## Getting Started

1. Ensure that you are using java 11.
2. Download the jar file from the version on github.
3. Navigate to the directory containing the jar file.
4. Run java -jar duke.jar to begin.
5. A chatbot should appear.

## Features

### Adding a task: `todo`, `event`, `deadline`, `recurring`

Add a new task of the choosen type into the task list.

Format:

- `todo [TASKCONTENT]`
- `event [TASKCONTENT] /from [FROMDATE] /to [TODATE]`
- `deadline [TASKCONTENT] /by [yyyy-MM-dd]`
- `recurring [TASKCONTENT] /time [HH-mm]`

Example of usage:

- `todo do 2103t project`
- `event meeting /from today sep 19 /to tmr`
- `deadline 2100 assignment /by 2023-10-23`
- `recurring meeting my gf /time 17-00`

### Deleting a task: `delete`

Delete the task from the task list.

Format:

`delete [TASKNUMBER]`

Example of usage:

`delete 1`

### Listing all the tasks: `list`

List down all the tasks.

Format:

`list`

### Find specific tasks: `find`

Find all tasks that contains the keyword.

Format:

`find [KEYWORD]`

Example of usage:

`find do`

### Marking each task: `mark`

Mark the task as done with an X.

Format:

`mark [TASKNUMBER]`

Example of usage:

`mark 1`

### Unmarking marked task: `unmark`

Remove the X in marked task.

Format:

`unmark [TASKNUMBER]`

Example of usage:

`unmark 1`

### Exiting the application: `bye`

Exit the application.

Format:

`bye`

## Command Summary

Action | Format, Examples
--------|------------------
**Add Todo** | `todo [TASKCONTENT]` <br> e.g., `todo do 2103t project`
**Add Event** | `event [TASKCONTENT] /from [FROMDATE] /to [TODATE]` <br> e.g., `event meeting /from today sep 19 /to tmr`
**Add Deadline** | `deadline [TASKCONTENT] /by [yyyy-MM-dd]` <br> e.g., `deadline 2100 assignment /by 2023-10-23`
**Add Recurring** | `recurring [TASKCONTENT] /time [HH-mm]` <br> e.g., `recurring meeting my gf /time 17-00`
**Delete** | `delete [TASKNUMBER]` <br> e.g., `delete 1`
**List** | `list`
**Find** | `find [KEYWORD]` <br> e.g., `find do`
**Mark** | `mark [TASKNUMBER]` <br> e.g., `mark 1`
**Unmark** | `unmark [TASKNUMBER]` <br> e.g., `unmark 1`
**Exit** | `bye`
