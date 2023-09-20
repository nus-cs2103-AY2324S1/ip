# Funny Bot User Guide

## Features and Usage

### Listing all tasks

Shows the list of all tasks

### `Keyword` - "list"
**Format**: `list`

-------------------------------------------------------------


### Add a ToDo Task

Add a ToDo task to the existing list of tasks

### `Keyword` - "todo"
**Notes**: `todo <description>`

Examples:
```
    todo buy groceries
    todo exercise
```

-------------------------------------------------------------


### Add a Deadline Task

Add a Deadline task to the existing list of tasks

### `Keyword` - "deadline"
**Notes**: `deadline <description> /by <date>`

Examples:
```
    deadline buy groceries /by 2023-10-09
    deadline return book /by 2023-09-23
```
**Notes:**
1. Date field has to be in the yyyy-mm-dd format
2. All fields are mandatory

-------------------------------------------------------------


### Add an Event Task

Add an Event task to the existing list of tasks

### `Keyword` - "event"
**Format**: `event <description> /from <start_date> /to <end_date>`

Examples:
```
    event holiday /from 2023-11-09 /to 2023-11-20
    event read book /from 2023-09-23 /to 2023-10-25
```
**Notes:**
1. Both date fields has to be in the yyyy-mm-dd format
2. All fields are mandatory

-------------------------------------------------------------

### Mark a task as complete

Checks off a task in the tasklist as completed

### `Keyword` - "mark"
**Format**: `mark <index>`

**Notes:**
1. Index has to be a numeric character
2. Index must exist within the tasklist

-------------------------------------------------------------

### Unmark a task as incomplete

Reverts a task in the tasklist as not completed

### `Keyword` - "unmark"
**Format**: `unmark <index>`

**Notes:**
1. Index has to be a numeric character
2. Index must exist within the tasklist

-------------------------------------------------------------

### Delete a task

Deletes a task from the tasklist

### `Keyword` - "delete"
**Format**: `delete <index>`

**Notes:**
1. Index has to be a numeric character
2. Index must exist within the tasklist

-------------------------------------------------------------

### Search for a task

Find a task from the tasklist that matches a given search string

### `Keyword` - "find"
**Format**: `find <description>`

-------------------------------------------------------------

### Reschedule a task

Reschedule either a deadline or an event task

### `Keyword` - "reschedule"
**Format**: `reschedule <index> <end_date>`

**Notes:**
1. Index has to be a numeric character
2. Index must exist within the tasklist
3. Date field has to be in the yyyy-mm-dd format
