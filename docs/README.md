# ChadBot Task Manager User Guide

## Table of Contents
- [Introduction](#introduction)
- [Features and Usage](#features-and-usage)
  - [List Tasks](#list-tasks)
  - [Add Task](#add-task)
  - [Mark Task As Done](#mark-task-as-done)
  - [Delete Task](#delete-task)
  - [Find Task](#find-task)
  - [Sort Tasks](#sort-tasks)

---

## Introduction

ChadBot Task Manager helps you manage your tasks with ease. With an intuitive chat-based interface, you can add, modify, and search for tasks like never before.

---

## Features and Usage

### List Tasks - `list` 

Lists all the tasks saved in the Task Manager.

Format: `list`

Example of usage: `list`

Expected Outcome:

```  
Here's your tasks m8:
1. [T] [X] Buy groceries
2. [D] [ ] Project submission (by: Sep 25 2023, 11:59 PM)
3. [E] [ ] Team meeting (from: Sep 22 2023, 3:00 PM to: Sep 22 2023, 4:00 PM)
```  

### Add ToDo Task - `todo` 

Adds a ToDo task.

Format: `todo DESCRIPTION`

Example of usage: `todo Buy groceries`

Expected Outcome: 

```  
Added new ToDo: Buy groceries
```  

### Add Deadline Task - `deadline`

Adds a task with a deadline.

Format: `deadline DESCRIPTION /by YYYY-MM-DD HH:MM`

Example of usage: `deadline Project submission /by 2023-09-25 23:59`

Expected Outcome: 

```  
Added new Deadline: Project submission by Sep 25 2023, 11:59 PM
```  

### Add Event Task - `event`

Adds an event task with a start and end time.

Format: `event DESCRIPTION /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM`

Example of usage: `event Team meeting /from 2023-09-22 15:00 /to 2023-09-22 16:00`

Expected Outcome:

```  
Added new Event: Team meeting from Sep 22 2023, 3:00 PM to Sep 22 2023, 4:00 PM
```  

### Mark Task As Done - `mark`

Marks a task as done. The task index starts from 1.

Format: `mark INDEX`

Example of usage: `mark 1`

Expected Outcome: 

```  
Yessir! This task is marked as done:
[T] [X] Buy groceries
```  

### Unmark Task As Done - `unmark`

Unmarks a task that was previously marked as done. The task index starts from 1.

Format: `unmark INDEX`

Example of usage: `unmark 1`

Expected Outcome:

```  
Aite, I've marked this task as not done yet:
[T] [ ] Buy groceries
``` 


### Delete Task - `delete`

Deletes a task based on its index. The task index starts from 1.

Format: `delete INDEX`

Example of usage: `delete 1`

Expected Outcome: 

```  
Aite, this task is gone bro: [T] [X] Buy groceries
```  

### Find Task - `find`

Finds a task by keyword.

Format: `find KEYWORD`

Example of usage: `find groceries`

Expected Outcome:

```  
Here are the matching tasks in your list:
1. [T] [X] Buy groceries
```  

### Sort Tasks - `sort`

Sorts tasks by their deadlines or event timings.

Format: `sort`

Example of usage: `sort`

Expected Outcome:

```  
Tasks sorted by deadline:
1. [D] [ ] Project submission (by: Sep 25 2023, 11:59 PM)
2. [E] [ ] Team meeting (from: Sep 22 2023, 3:00 PM to: Sep 22 2023, 4:00 PM)
```  

---
### That's the basics to get you started with ChadBot Task Manager! HAND 