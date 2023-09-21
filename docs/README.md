# Deterministic Parrot User Guide

Deterministic Parrot is a chatbot for managing tasks. It allows users to add, edit, and track tasks in a convenient and intuitive manner.

## Table of Contents
- Quick Start
- Features
    - Viewing tasks: list
    - Exiting the program: bye
    - Marking a task as done: mark
    - Marking a task as not done: unmark
    - Adding a ToDo task: todo
    - Adding a Deadline task: deadline
    - Adding an Event task: event
    - Deleting a task: delete
    - Searching for a task: find
    - Viewing upcoming tasks: upcoming
- Command Summary

## Quick Start
1. Ensure you have Java installed on your computer.
2. Download the latest `DeterministicParrot.jar` file.
3. Navigate to the folder containing the jar file and open a terminal.
4. Run the application using the command `java -jar DeterministicParrot.jar`.

## Features

### Viewing tasks: list
Shows all tasks currently in the list.

Format: `list`

### Exiting the program: bye
Closes the Deterministic Parrot application.

Format: `bye`

### Marking a task as done: mark
Marks a task as completed.

Format: `mark TASK_NUMBER`

### Marking a task as not done: unmark
Marks a completed task as not done.

Format: `unmark TASK_NUMBER`

### Adding a ToDo task: todo
Adds a simple task without any specific deadline or schedule.

Format: `todo TASK_DESCRIPTION`

### Adding a Deadline task: deadline
Adds a task with a specific deadline.

Format: `deadline TASK_DESCRIPTION /by DEADLINE`

### Adding an Event task: event
Adds an event with a start and end time.

Format: `event EVENT_DESCRIPTION /from START_TIME /to END_TIME`

### Deleting a task: delete
Removes a task from the list.

Format: `delete TASK_NUMBER`

### Searching for a task: find
Searches and displays tasks that contain the specified keyword.

Format: `find KEYWORD`

### Viewing upcoming tasks: upcoming
[This feature is referenced in the code but its description is not provided. Typically, it might show tasks that are due soon.]

Format: `upcoming`

## Command Summary
- `list` - View all tasks
- `bye` - Exit the application
- `mark TASK_NUMBER` - Mark task as done
- `unmark TASK_NUMBER` - Mark task as not done
- `todo TASK_DESCRIPTION` - Add ToDo task
- `deadline TASK_DESCRIPTION /by DEADLINE` - Add Deadline task
- `event EVENT_DESCRIPTION /from START_TIME /to END_TIME` - Add Event task
- `delete TASK_NUMBER` - Delete task
- `find KEYWORD` - Search tasks
- `upcoming` - View upcoming tasks
