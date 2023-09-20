# User Guide for Snake CYQJ

Welcome to Snake CYQJ, your personal task manager! This guide will help you get started with Snake CYQJ.

## Contents

- [Contents](#contents)
- [Quick Start](#quick-start)
- [Features](#features)
- [Usage](#usage)
    - [1. Add Tasks](#1-add-tasks)
        - [1.1. Add Todo task - `todo`](#11-add-todo-task---todo)
        - [1.2. Add Deadline task - `deadline`](#12-add-deadline-task---deadline)
        - [1.3. Add Event task - `event`](#13-add-event-task---event)
    - [2. List Tasks - `list`](#2-list-tasks---list)
    - [3. Mark/Unmark Tasks as Done](#3-markunmark-tasks-as-done)
        - [3.1. Mark Task as Done - `mark`](#31-mark-task-as-done---mark)
        - [3.2. Unmark Task as Done - `unmark`](#32-unmark-task-as-done---unmark)
    - [4. Delete Tasks - `delete`](#4-delete-tasks---delete)
    - [5. Find Tasks - `find`](#5-find-tasks---find)
    - [6. Sort Tasks](#6-sort-tasks)
        - [6.1. Sort by Chronological Order - `sort`](#61-sort-by-chronological-order---sort)
        - [6.2. Sort by Task Type - `sort by type`](#62-sort-by-task-type--sort-by-type)
    - [7. Exit - `bye`](#7-exit---bye)


## Quick Start

1. Download Java 11 from [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).
2. Download the latest jar file from the [Releases tab](https://github.com/freshcabbage123/ip/releases).
3. Run with `java -jar Duke.jar` from your terminal.


## Features

1. Task Management
    1. Create, read, update and delete various types of tasks.
    2. Mark and unmark tasks as done.
    3. Find tasks that contain a keyword in their description.

2. Data Persistence
    1. Tasks are saved to a file locally.
        1. On the first run, the save file is created automatically.
    2. On subsequent runs, the save file is loaded automatically.
    3. Tasks are saved to the local file whenever the task list is modified.

## Usage

### 1. Add Tasks

Snake CYQJ can help you keep track of tasks.

There are 3 different types of tasks that you can add:

- Todo, a task with no deadline
- Deadline, a task with a deadline
- Event, a task with a start and end time

#### 1.1. Add Todo task - `todo`

This command adds a Todo task to your list of tasks.

Format:

```
todo <description>
```

- `<description>` is the description of the task.
    - The description cannot be empty.

Example:

```
todo feed snake
```

Expected Output:

```
Got it. I've added this task: 
[T][ ] feed snake
Now you have 1 task in the list.
```

#### 1.2. Add Deadline task - `deadline`

This command adds a Deadline task to your list of tasks.

Format:

```
deadline <description> /by <deadline>
```

- `<description>` is the description of the task.
    - The description cannot be empty.
- `<deadline>` is the deadline of the task.
    - It must be in the format `dd-MM-yyy HH:mm`.

Example:

```
deadline feed snake /by 29-10-2024 00:00
```

Expected Output:

```
Got it. I've added this task:
[D][ ] feed snake (by: 29-10-2024 00:00)
Now you have 1 task in the list.
```

#### 1.3. Add Event task - `event`

This command adds an Event task to your list of tasks.

Format:

```
event <description> /from <start time> /to <end time>
```

- `<description>` is the description of the task.
    - The description cannot be empty.
- `<start time>` is the start time of the task.
    - It must be in the format `dd-MM-yyy HH:mm`.
- `<end time>` is the end time of the task
    - It must be in the format `dd-MM-yyy HH:mm`.

Example:

```
event snake gathering /from 02-01-2024 10:00 /to 03-01-2024 11:00
```

Expected Output:

```
Got it. I've added this task:
[E][ ] snake gathering (from: 02-01-2024 10:00 to: 03-01-2024 11:00)
Now you have 1 task in the list.
```

### 2. List Tasks - `list`

Snake CYQJ can show you the tasks currently present in your task list.

Note: This command takes in no parameters.

Format:

```
list
```

Expected Output:

```
Here are the tasks in your list:
1.[T][ ] feed snake
```

### 3. Mark/Unmark Tasks as Done

#### 3.1. Mark Task as Done - `mark`

Once you have completed a task, you can tell Snake CYQJ to mark it as done.

Format:

```
mark <task number>
```

- `<task number>` is the number of the task in the list, as shown by the `list` command.

Example:

Suppose the task list as shown by the `list` command is as follows:

```
Here are the tasks in your list:
1.[T][ ] feed snake
```

Using the `mark` command:
```
mark 1
```

Expected Output:

```
Nice! I've marked this task as done:
[T][X] feed snake
```

#### 3.2. Unmark Task as Done - `unmark`

If you have marked a task as done by mistake, you can tell Snake CYQJ to unmark it.

Format:

```
unmark <task number>
```

- `<task number>` is the number of the task in the list, as shown by the `list` command.

Example:

Suppose the task list as shown by the `list` command is as follows:

```
Here are the tasks in your list:
1.[T][X] feed snake
```

Using the `unmark` command:

```
unmark 1
```

Expected Output:

```
OK, I've marked this task as not done yet:
[T][ ] feed snake
```

### 4. Delete Tasks - `delete`

Snake CYQJ can help you delete tasks from your task list.

⚠️ Caution: Once a task is deleted, it cannot be recovered.

⚠️ Caution: Deleting a task will shift the task numbers of the tasks after it forward by 1.

- If you delete task 2, task 1 will remain as task 1, but task 3 will become task 2, task 4 will become task 3, and so on.

Format:

```
delete <task number>
```

- `<task number>` is the number of the task in the list, as shown by the `list` command.

Example:

Suppose the task list as shown by the `list` command is as follows:

```
Here are the tasks in your list:
1.[T][ ] feed snake
```

Using the `delete` command:
```
delete 1
```

Expected Output:

```
Noted. I've removed this task:
[T][ ] feed snake
Now you have 0 tasks in the list.
```

### 5. Find Tasks - `find`

Snake CYQJ can help you find tasks in your task list that has a certain keyword in its description.

Note: The numbering of tasks in the search result will be the same as the numbering of tasks as shown in the `list` command.

Format:

```
find <keyword>
```

- `<keyword>` is the keyword to search for in the task description.
    - The keyword cannot be empty.
    - The keyword is case-sensitive.

Example:

Suppose the task list as shown by the `list` command is as follows:

```
Here are the tasks in your list:
1.[T][ ] feed snake
```

Using the `find` command:

```
find book
```

Expected Output:

```
Here is the 1 matching task in your list:
1.[T][ ] feed snake
```

### 6. Sort Tasks

#### 6.1. Sort by Chronological Order - `sort`

Snake CYQJ can show you the tasks currently present in your task list in sorted chronological order.

Note: This command takes in no parameters.

Format:

```
sort
```

Suppose the task list as shown by the `sort` command is as follows:

```
1.[D][ ] assignment (by: 09-03-2024 18:00)
2.[D][ ] oa (by: 08-03-2024 17:00)
3.[D][ ] submission (by: 01-04-2024 16:00)
```

Expected Output:

```
Here are the tasks in your list:
1.[D][ ] oa (by: 08-03-2024 17:00)
2.[D][ ] assignment (by: 09-03-2024 18:00)
3.[D][ ] submission (by: 01-04-2024 16:00)
```
#### 6.2. Sort by Task Type- `sort by type`

Snake CYQJ can show you the tasks currently present in your task list in sorted chronological order based on task type.

Note: This command takes in no parameters.

Format:

```
sort by type
```

Suppose the task list as shown by the `list` command is as follows:

```
1.[D][ ] assignment (by: 09-03-2024 18:00)
2.[D][ ] oa (by: 08-03-2024 17:00)
3.[D][ ] submission (by: 01-04-2024 16:00)
4.[T][ ] collect laundry
5.[E][ ] wedding dinner (from: 08-09-2024 18:00 to: 08-09-2024 20:00)
```

Expected Output:

```
1.[T][ ] collect laundry
2.[D][ ] oa (by: 08-03-2024 17:00)
3.[D][ ] assignment (by: 09-03-2024 18:00)
4.[D][ ] submission (by: 01-04-2024 16:00)
5.[E][ ] wedding dinner (from: 08-09-2024 18:00 to: 08-09-2024 20:00)
```

### 7. Exit - `bye`

Ends the chat with Snake CYQJ.

Note: This command takes in no parameters.

Format:

```
bye
```

Expected Output:

Snake CYQJ will bid you farewell and app will exit after 1 second.
