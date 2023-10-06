# WallE Quick Start Guide

## Step 1: Download WallE Chatbot

1. Go to the [WallE Chatbot release page](https://github.com/timleow/ip/releases/tag/A-Release).
2. Download the `duke.jar` file by clicking on it.

## Step 2: Install Java 11

Make sure you have Java 11 installed on your system. If not, you can download it [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

## Step 3: Run WallE Chatbot

1. Open your terminal or command prompt.
2. Navigate to the directory where you have the `duke.jar` file.
3. Run the following command:

```bash
java -jar duke.jar
```


# WallE User Guide

## Table of Contents

- [Features](#Features)
- [commands](#Commands)
    - [`help`](#help---get-help-pop-up-to-display)
    - [`list`](#list---list-all-of-your-current-tasks)
    - [`todo`](#todo-taskname---add-a-todo-to-your-task-list)
    - [`deadline`](#deadline-taskname-by-deadlinedate---add-a-deadline-with-a-specific-date-to-your-task-list)
    - [`event`](#event-eventname-from-eventstart-to-eventend---add-an-event-with-a-start-and-end)
    - [`mark`](#mark-taskid---mark-the-task-with-id-taskid-as-done)
    - [`unmark`](#unmark-taskid---mark-the-task-with-id-taskid-as-not-done)
    - [`delete`](#delete-taskid---delete-the-task-with-id-taskid)
    - [`find`](#find-searchtext---find-all-tasks-that-have-relevant-information-to-searchtext)
    - [`bye`](#bye---bid-farewell-and-close-the-window)

## Features

### Task Tracking

`WallE` is equipped with a robust task tracking system, allowing you to manage your tasks efficiently.

### Help Page

`WallE` also has a useful help pop-up which shows you a list of all the possible commands that `WallE` can recognise.

## commands

### `help` - Get help pop-up to display.

This command provides a pop-up window with helpful information about using WallE.

### `list` - List all of your current tasks.

This command displays a list of all your current tasks.

[Back to Table of Contents](#table-of-contents)

### `todo {taskName}` - Add a ToDo to your task list.

* `taskName` - The name of the ToDo task, which can be more than 1 word.

This command adds a ToDo task to your task list.

Example of usage:
```
todo Learn Tap Dance sequence
```
<img src="https://i.imgur.com/qCYjcQM.png" alt="Alt text" height="300">

[Back to Table of Contents](#table-of-contents)

### `deadline {taskName} /by {deadlineDate}` - Add a Deadline with a specific date to your task list.
* `taskName` - The name of the Deadline task, which can be more than 1 word.
* `deadlineDate` - The deadline of the task, in this format : `yyyy-MM-dd HHmm`. The time field is optional.

This command adds a Deadline (a task with a specific deadline) to your task list.

Example of usage:

```
deadline finish iP tasks /by 2023-09-22 2359
```

<img src="https://i.imgur.com/QoYypFD.png" alt="Alt text" height="300">

[Back to Table of Contents](#table-of-contents)


### `event {eventName} /from {eventStart} /to {eventEnd}` - Add an event with a start and end.
* `eventName` - The name of the Event task, which can be more than 1 word.
* `eventStart` - The string that indicates the start of the event.
* `eventEnd` - The string that indicates the end of the event.

This command adds an event to your task list with a specified start and end time (which are both just strings).

Example of usage:

```
event tP meeting /from 9pm /to 10pm
```
<img src="https://i.imgur.com/qN6wYWJ.png" alt="Alt text" height="300">

[Back to Table of Contents](#table-of-contents)

### `mark {taskId}` - Mark the task with id taskId as done.
* `taskId` - The id of the task (can be seen when the `list` command is executed).

This command marks a specific task as done. 

Example of usage:

```
mark 1
```

<img src="https://i.imgur.com/P8m47wb.png" alt="Alt text" height="300">

[Back to Table of Contents](#table-of-contents)


### `unmark {taskId}` - Mark the task with id taskId as not done.
* `taskId` - The id of the task (can be seen when the `list` command is executed).

This command marks a specific task as not done.

Example of usage:

```
unmark 1
```

<img src="https://i.imgur.com/kZ2PTEJ.png" alt="Alt text" height="300">

[Back to Table of Contents](#table-of-contents)


### `delete {taskId}` - Delete the task with id taskId.
* `taskId` - The id of the task (can be seen when the `list` command is executed).

This command removes a task from your task list.

Example of usage:

```
delete 2
```

<img src="https://i.imgur.com/CIxo2UO.png" alt="Alt text" height="300">

[Back to Table of Contents](#table-of-contents)


### `find {searchText}` - Find all tasks that have relevant information to searchText.
* `searchText` - The keyword that you would like to search for amongst all your tasks. Could be related to the time too.

This command searches for tasks containing the specified search text.

Example of usage:

```
find groceries
```

<img src="https://i.imgur.com/gw46T23.png" alt="Alt text" height="300">

[Back to Table of Contents](#table-of-contents)

### `bye` - Bid farewell and close the window.

This command allows you to bid farewell and close the WallE application.

Example of usage:

```
bye
```

<img src="https://i.imgur.com/2zsDtd8.png" alt="Alt text" height="300">

**Note**: Make sure to replace `{taskName}`, `{yyyy-MM-dd HHmm}`, `{eventName}`, `{eventStart}`, `{eventEnd}`, and `{searchText}` with actual values.

This user guide provides you with a comprehensive overview of WallE's capabilities. Use these commands to effectively manage your tasks. If you have any further questions or need additional assistance, feel free to reach out. Happy tasking!

[Back to Table of Contents](#table-of-contents)
