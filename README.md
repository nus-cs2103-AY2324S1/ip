# Taskmaster

## Overview

### **Taskmaster** is chatbot that is designed to help you manage your task better. Using commands, you can manage 3 different types of task; Todos, Deadlines and Events.

## Features 

Generally, if the command requires you to indicate a time/date, you can either input it as:
- Normal date / time eg. 3pm, 2 Dec
- YYYY-MM-DD Date format eg. 2023-10-30

### List out all tasks `list`
Displays all current tasks.

**Example usage:**

`list`

**Expected outcome:**

`Here are the tasks in your list:
Task 1: [T][] Homework
Task 2: [D][X] Internship application (by: Dec 12 2023`

### Mark a task as done: `mark [index]`
Mark the task with the indicated index as done. `index` must be a positive integer, and within valid range of the task list, it indicates the index of the task in the task list to be marked.

**Example usage:**

`mark 1`

**Expected outcome:**

The task with index 1 in the task list is marked as **done**, and `X` indicator beside the task is shown.
`Good job! I have marked this task as completed:
[T][X] homework`

### Mark a task as undone: `unmark [index]`
Unmark a marked task with the indicated index. `index` must be a positive integer, and within valid range of the task list, it indicates the index of the task in the task list to be unmarked.

**Example usage:**
`unmark 1`

**Expected outcome:**

The task with index 1 in the task list is marked as **not done**, and `X` indicator beside the task is shown.
`OK, i have marked this as undone:
[T][] homework`

### Delete a task: `delete [index]`
Delete a task with the indicated index. `index` must be a positive integer, and within valid range of the task list, it indicates the index of the task in the task list to be deleted.

**Example usage:**
`delete 1`

**Expected outcome:**

The task with index 1 in the task list is marked as **not done**, and `X` indicator beside the task is shown.
`Noted. I've removed this task:
[T][] homework
Now you have 2 tasks in the list`

### Add a new todo task: `todo [name]`
Add a new todo task with specified `name`. A todo task as no start time or end time, all characters after `todo` keyword will be considered as `name`.

**Example usage:**

`todo complete presentation slides`

**Expected outcome:**

Adds a todo task to the current task list.
`Got it. I've added this to-do task:
[T][] complete presentation slides
Now you have 3 tasks in the list`

### Add a new deadline task: `todo [name] /by [datetime]`
Add a new deadline with specified `name` and a duedate given as `datetime`. All deadlines must be specified with a end time with the `/by` keyword. Note `datetime` can also be in yyyy-mm-dd format.

**Example usage:**

`deadline CS2103T quiz /by Friday`

**Expected outcome:**

Add a new deadline task to the current task list.
`Got it. I've added a new deadline:
[D][] CS2103T quiz (by: Friday)
Now you have 4 tasks in the list`

### Add a new event task: `event [name] /from [start] /to [end]`
Add a new event with specified `name` and given `start` and `end` times. All events must be specified with a start and end time with the `/from` amd `to` keyword respectively. Note `start` and `end` can also be in yyyy-mm-dd format.

**Example usage:**

`event student fair /from 2pm /to 3pm`

**Expected outcome:**

Add a new event task to the current task list.
`Got it. I've added a new event:
[E][] student fair (from: 3pm to 5pm)
Now you have 5 tasks in the list`

### Find task using keyword: `find [keyword]`
Find a task that has `keyword` as name. Note that keyword is case sensitive and must match exactly to task that you are looking for.

**Example usage:**

`find project work`

**Expected outcome:**

Displays all tasks that has name matching keyword.
`1: [D][] project work (by Oct 11 2023)`

### Find task using duedate: `due [duedate]`
Find all deadlines and events that has `duedate` as their `datetime` and `start` times respectively. Note that duedate needs to be in yyyy-mm-dd datetime format.

**Example usage:**

`due 2023-10-11`

**Expected outcome:**

Displays all tasks that has name matching keyword.
`1: [D][] project work (by Oct 11 2023)
2: [E][] student fair (from: Oct 11 2023 to Oct 12 2023)`








