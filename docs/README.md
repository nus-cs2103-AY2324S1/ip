# User Guide for Misty

## Introduction

Welcome to Misty, your intuitive and user-friendly task management chatbot. Misty is designed to help you organize and manage your tasks effectively. This user guide will provide you with detailed instructions on how to use all the important features that Misty offers.

## Features

### Feature-A: Task Management

Misty enables you to manage your tasks efficiently by allowing you to add different types of tasks such as ToDo, Deadline, and Event.

### Feature-B: Task Deletion

Misty keeps your task list clutter-free by allowing you to delete tasks that you no longer need.

### Feature-C: Task List Viewing

Misty offers a consolidated Task List feature that provides an overview of your pending and completed tasks.

### Feature-D: Task Marking

With Misty, you can easily mark tasks as completed or not completed, aiding in tracking your task progress.

### Feature-E: Task Searching

Misty facilitates the quick finding of tasks using keywords, saving you time in locating specific tasks.

### Feature-F: Task Scheduling

Misty allows you to view schedules for specific dates, assisting you in effectively managing your time and tasks.

## Usage

### `todo [task]` - Add a new ToDo task

Adds a new ToDo task to your task list.

**Example of usage:**

```
todo Buy Groceries
```

**Expected outcome:**

A new ToDo task named "Buy Groceries" will be added to your task list.

### `deadline [task] /by [YYYY-MM-DD HH:mm]` - Add a new Deadline task

Adds a new Deadline task with a specified due date and time to your task list.

**Example of usage:**

```
deadline Submit Assignment /by 2023-09-30 23:59
```

**Expected outcome:**

A new Deadline task named "Submit Assignment" with the deadline set to September 30, 2023, 23:59 will be added to your task list.

### `event [task] /from [YYYY-MM-DD HH:mm] /to [YYYY-MM-DD HH:mm]` - Add a new Event task

Adds a new Event task with a specified start and end date and time to your task list.

**Example of usage:**

```
event Attend Workshop /from 2023-10-01 09:00 /to 2023-10-01 12:00
```

**Expected outcome:**

A new Event task named "Attend Workshop" scheduled from October 1, 2023, 09:00 to 12:00 will be added to your task list.

### `list` - Show all tasks

Displays all tasks in your task list.

**Example of usage:**

```
list
```

**Expected outcome:**

A list of all your tasks will be displayed.

### `mark [task number]` - Mark a task as completed

Marks a specific task in your task list as completed.

**Example of usage:**

```
mark 1
```

**Expected outcome:**

The first task in your task list will be marked as completed.

### `unmark [task number]` - Mark a task as incomplete

Marks a specific task in your task list as incomplete.

**Example of usage:**

```
unmark 1
```

**Expected outcome:**

The first task in your task list will be marked as incomplete.

### `delete [task number]` - Delete a task

Deletes a specific task from your task list.

**Example of usage:**

```
delete 1
```

**Expected outcome:**

The first task in your task list will be deleted.

### `find [keyword]` - Find a task by searching for a keyword

Searches your task list and displays all tasks containing the specified keyword.

**Example of usage:**

```
find Groceries
```

**Expected outcome:**

All tasks containing the keyword "Groceries" will be displayed.

### `schedule [YYYY-MM-DD]` - View the schedule for a specific date

Displays tasks scheduled for a specific date.

**Example of usage:**

```
schedule 2023-10-01
```

**Expected outcome:**

All tasks scheduled for October 1, 2023, will be displayed.

### `help` - Show command guide

Displays a guide showing all available commands and their usage.

**Example of usage:**

```
help
```

**Expected outcome:**

A list of all commands with descriptions and usage will be displayed.

### `bye` - Exit Misty

Closes Misty application.

**Example of usage:**

```
bye
```

**Expected outcome:**

Misty will be closed.

## Conclusion

Thank you for choosing Misty as your task management chatbot. We hope this guide has provided you with a comprehensive overview of how to utilize all the features to manage your tasks effectively. If you have any further questions or need additional assistance, please do not hesitate to ask. Happy task managing!
