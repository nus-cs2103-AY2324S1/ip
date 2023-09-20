# MachoDuke

Want to feel **productive** and **motivated**? **_Macho cat is here!_**
MachoDuke is a powerful chatbot program designed to help you manage your tasks effectively. It provides the following features:

### MachoDuke Features
- [x] Sets tasks with various options such as to-do, deadline, events
- [x] Quickly mark your tasks as done, (or undone if you did an oopsie!)
- [x] Too many tasks to track? Fear not! Keyword search is here to save the day!
- [x] Sort by urgent tasks

## User Guide

### Table of Contents
1. [MachoDuke Commands Overview](#machoduke-commands-overview)
2. [List all tasks: `list`](#list-all-tasks-list)
3. [Create a todo task: `todo [task description]`](#create-a-todo-task-todo-task-description)
4. [Create a deadline task: `deadline [task description] /by [datetime]`](#create-a-deadline-task-deadline-task-description-by-datetime)
5. [Create an event task: `event [task description] /from [datetime] /to [datetime]`](#create-an-event-task-event-task-description-from-datetime-to-datetime)
6. [Mark a task as completed: `mark [task list number]`](#mark-a-task-as-completed-mark-task-list-number)
7. [Unmark a completed task: `unmark [task list number]`](#unmark-a-completed-task-unmark-task-list-number)
8. [Delete a task: `delete [task list number]`](#delete-a-task-delete-task-list-number)
9. [Find tasks using keyword search: `find [keywords]`](#find-tasks-using-keyword-search-find-keywords)
10. [Sort tasks: `sort [order]`](#sort-tasks-sort-order)
    

### MachoDuke Commands Overview
- `list`: Lists all tasks.
- `todo [task description]`: Creates a todo task.
- `deadline [task description] /by [datetime]`: Creates a deadline task with a specific date and time.
- `event [task description] /from [datetime] /to [datetime]`: Creates an event task with a specific start and end date and time.
- `mark [task list number]`: Marks a task in the list as completed based on the number order of the list given.
- `unmark [task list number]`: Unmarks a completed task in the list based on the number order of the list given.
- `delete [task list number]`: Deletes a task by its based on the number order of the list given.
- `find [keywords]`: Finds tasks matching a keyword or regular expression.
- `sort [order]`: Sorts tasks in ascending or descending order based on `asc` or `desc`.

---
## Usage

### List all tasks: `list`

This command will list all the tasks you have added.

**Example of usage:**

```
list
```

**Expected outcome:**

```
Here are the list of tasks recorded, Macho!
1. [T][ ] Buy groceries
2. [D][ ] Submit report (by: 2023-09-30 18:00)
3. [E][ ] Team meeting (from: 2023-09-25 15:00 to 2023-09-25 16:00)
```
---
### Create a todo task: `todo [task description]`

This command will create a new todo task with the provided description.

**Format**
- `[task description]` :The description of the task pending

**Example of usage:**

```
todo Buy groceries
```

**Expected outcome:**

```
Got it macho! I've added this task:
[T][ ] Buy groceries
You now have 3 tasks in the list, macho!
```
---
### Create a deadline task: `deadline [task description] /by [datetime]`

This command will create a new deadline task with the provided description and deadline.

**Format**
- `[task description]` :The description of the task pending
- `[datetime]`: In the format of `yyyy-MM-dd HH:mm`

**Example of usage:**

```
deadline Submit report /by 2023-09-30 18:00
```

**Expected outcome:**

```
Got it macho! I've added this task:
[D][ ] Submit report (by: 2023-09-30 18:00)
You now have 3 tasks in the list, macho!
```
---
### Create an event task: `event [task description] /from [datetime] /to [datetime]`

This command will create a new event task with the provided description and event duration.

**Format**
- `[task description]` :The description of the task pending
- `[datetime]`: In the format of `yyyy-MM-dd HH:mm`

**Example of usage:**

```
event Team meeting /from 2023-09-25 15:00 /to 2023-09-25 16:00
```

**Expected outcome:**

```
Got it macho! I've added this task:
[E][ ] Team meeting (from: 2023-09-25 15:00 to 2023-09-25 16:00)
You now have 3 tasks in the list, macho!
```
---
### Mark a task as completed: `mark [task list number]`

This command will mark the task at the specified index as completed.

**Format**
- `[task list number]` : The order number of this task in the list.

**Example of usage:**

```
mark 1
```

**Expected outcome:**

```
I have marked this task as done per your request, macho!
[T][X] Buy groceries
```
---
### Unmark a completed task: `unmark [task list number]`

This command will unmark a completed task at the specified index.

**Format**
- `[task list number]` : The order number of this task in the list.

**Example of usage:**

```
unmark 1
```

**Expected outcome:**

```
I have marked this task as undone yet, per your request, macho!
[T][ ] Buy groceries
```
---
### Delete a task: `delete [task list number]`

This command will delete the task at the specified index.

**Format**
- `[task list number]` : The order number of this task in the list.

**Example of usage:**

```
delete 1
```

**Expected outcome:**

```
I have deleted this task as done per your request, macho!
[T][ ] Buy groceries
You now have 2 tasks in the list, macho!
```
---
### Find tasks using keyword search: `find [keywords]`

This command will find tasks that match the provided keyword or regular expression.

**Format**
- `[keywords]` : The input keywords to search.

**Example of usage:**

```
find report
```

**Expected outcome:**

```
Here are the matching tasks found, macho!
2. [D][ ] Submit report (by: 2023-09-30 18:00)
```
---
### Sort tasks: `sort [order]`

This command will sort tasks based on keywords in either ascending or descending order.

**Format**
- `[order]` : The order to sort by, `asc` or `desc`.

**Example of usage:**

```
sort desc
```

**Expected outcome:**

```
Tasks are now sorted, macho!
1. [E][ ] Team meeting (from: 2023-09-25 15:00 to 2023-09-25 16:00)
2. [D][ ] Submit report (by: 2023-09-30 18:00)
3. [T][ ] Buy groceries
```

Feel free to explore and utilize MachoDuke to manage your tasks efficiently!