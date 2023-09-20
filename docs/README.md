# Evan - User Guide

## Features 

### Adding Tasks into the list

We allow you to add 3 different types of tasks into the list.
1. ToDo Task
2. Deadline Task
3. Event Task

A ToDo task allows you to add a task without any dates specified.
A Deadline task allows you to add a task with 1 date/time specified as it's deadline.
An Event task allows you to add a task with 2 date/times. The first is specified as the start time and the second is specified as the end time of the event.

### View List

Users can view the entire list of their pending tasks.

### Delete Task

You can delete a specified task on the list.

### Task Status

Users can mark a task as incompleted or completed.

### Find Task

Users can find a particular task they want to see by searching keywords.

## Usage

### `list` - Show the entire list of tasks

All the user has to type is "list", no arguments required.

Format: `list`

Example of usage: 

`list`

Expected outcome:

The Chatbot will reply with a list of all the tasks that is tracked

```
Here are the tasks in your list:
1. [T][] Read Book
2. [D][X] Finish CS2100 Assignment (by: 18-Sep-2023 1300)
3. [D][X] Finish CS2103T ip (by: 22-Sep-2023)
4. [E][] NUSBS Dharma Camp (from: 1-Jul-2024 to: 2-Jul-2024)
```

### `todo` - Starts a process to add a ToDo entry into the list

All the user has to type is "todo", no arguments required.
Further follow-up actions are required to complete the insertion of the task as instructed by the chatbot.
<br>
Follow-up actions required:
1. Type in name of the task


Format: `todo`

Example of usage: 

1. User types `todo`
<br>
Expected outcome:

The Chatbot will instruct users on follow up actions to create a ToDo task

```
Here are the tasks in your list:
1. [T][] Read Book
2. [D][X] Finish CS2100 Assignment (by: 18-Sep-2023 1300)
3. [D][X] Finish CS2103T ip (by: 22-Sep-2023)
4. [E][] NUSBS Dharma Camp (from: 1-Jul-2024 to: 2-Jul-2024)
```
