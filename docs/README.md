# User Guide

## Features 

### Add To-Do

Adds a task that is **non time bound**

### Add Deadline

Adds a task with a due `date and time`.

### Add Event

Adds a task with a start `date and time` and end `date and time`

### Delete Task

Removes a task from a list of recorded tasks.

### List Tasks

Displays all tasks that were recorded.

### Find Tasks

Search for task descriptions that match a given keyword.

### Mark Task

Set a task as `done` or `not done`

### Check Due Tasks

Search for tasks due on a certain `date`

### Add Venue

Adds a venue name and its details: address, capacity and rent.

### Delete Venue

Removes a venue from a list of recorded venues.

### List Venues

List all venues that were recorded.

### Find Venues

Search for venue names that match a given keyword.

### Save Data

Saves all recorded tasks and venues. Data is automatically saved whenever a new task or venue is added or deleted.

## Usage

### `todo <description>` - Create a ToDo task

Create a ToDo task by providing a description.

Example of usage: 

`todo Buy Eggs`

Expected outcome:

When successfully created, the chatbot responds with a success message.

```
Got it! I've added the following task:
    T[] Buy Eggs
You've now 3 tasks in the list.
```

### `deadline <description> /by <dd-mm-yyyy hh:mm>` - Create a Deadline task

Creates a Deadline task by providing a description and a deadline.

Example of usage:

`deadline Report Submission /by 27-09-2023 23:59`

Expected outcome:

When successfully created, the chatbot responds with a success message.

```
expected output
```