# User Guide for Max

Welcome to Max, your personal task manager! This guide will help you get started with Max.

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
  - [4. Edit Tasks - `edit`](#4-edit-tasks---edit)
    - [4.1. Edit Todo task](#41-edit-todo-task)
    - [4.2. Edit Deadline task](#42-edit-deadline-task)
    - [4.3. Edit Event task](#43-edit-event-task)
  - [5. Delete Tasks - `delete`](#5-delete-tasks---delete)
  - [6. Find Tasks - `find`](#6-find-tasks---find)
  - [7. Help - `help`](#7-help---help)
  - [8. Exit - `bye`](#8-exit---bye)


## Quick Start 

1. Download Java 11 from [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).
2. Download the latest jar file from the [Releases tab](https://github.com/jiakai-17/ip/releases).
3. Run with `java -jar Max.jar` from your terminal.


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

---

ℹ️ Notes about the command format:

- Words enclosed in `<>` are the parameters to be supplied by the user.
  - e.g. In `todo <description>`, `<description>` is a parameter which can be used as `todo read book`.

- Items in square brackets `[]` are optional.
  - e.g. In `edit <task number> [/d <description>] [/b <deadline>]`, the `/d` and `/b` parameters are optional.
  - You can choose to include either one, both, or none of them.

- Parameters must strictly be in the order specified, unless otherwise stated.

- Extraneous parameters for commands that do not take in parameters will be ignored.
  - e.g. If the command specifies `help 123`, it will be interpreted as `help`.

---

### 1. Add Tasks

Max can help you keep track of tasks. 

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
todo read book
```

Expected Output:

```
Got it. I've added this task: 
[T][ ] read book
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
  - It must be in the format `yyyy-MM-dd HH:mm`.

Example:

```
deadline return book /by 2023-01-01 00:00
```

Expected Output:

```
Got it. I've added this task:
[D][ ] return book (by: Sun 01 Jan 2023 00:00)
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
  - It must be in the format `yyyy-MM-dd HH:mm`.
- `<end time>` is the end time of the task
  - It must be in the format `yyyy-MM-dd HH:mm`.
  
Example:

```
event project meeting /from 2023-01-01 10:00 /to 2023-01-01 11:00
```

Expected Output:

```
Got it. I've added this task:
[E][ ] project meeting (from: Sun 01 Jan 2023 10:00 to: Sun 01 Jan 2023 11:00)
Now you have 1 task in the list.
```

### 2. List Tasks - `list`

Max can show you the tasks currently present in your task list.

🗒️ Note: This command takes in no parameters.

Format: 

```
list
```

Expected Output:

```
Here are the tasks in your list:
1.[T][ ] read book
```

### 3. Mark/Unmark Tasks as Done

#### 3.1. Mark Task as Done - `mark`

Once you have completed a task, you can tell Max to mark it as done.

Format: 

```
mark <task number>
```

- `<task number>` is the number of the task in the list, as shown by the `list` command.

Example:

Suppose the task list as shown by the `list` command is as follows:

```
Here are the tasks in your list:
1.[T][ ] read book
```

Using the `mark` command:
```
mark 1
```

Expected Output:

```
Nice! I've marked this task as done:
[T][X] read book
```

#### 3.2. Unmark Task as Done - `unmark`

If you have marked a task as done by mistake, you can tell Max to unmark it.

Format: 

```
unmark <task number>
```

- `<task number>` is the number of the task in the list, as shown by the `list` command.

Example:

Suppose the task list as shown by the `list` command is as follows:

```
Here are the tasks in your list:
1.[T][X] read book
```

Using the `unmark` command:

```
unmark 1
```

Expected Output:

```
OK, I've marked this task as not done yet:
[T][ ] read book
```

### 4. Edit Tasks - `edit`

Max can help you change the details of a task, without having to delete and re-add it.

#### 4.1. Edit Todo task

For Todo tasks, you can only edit the description.

Format: 

```
edit <task number> /d <description>
```

- `<task number>` is the number of the task in the list, as shown by the `list` command.
- `<description>` is the new description of the task.
  - The description cannot be empty.

Example:

Suppose the task list as shown by the `list` command is as follows:

```
Here are the tasks in your list:
1.[T][ ] read book
```

Using the `edit` command:

```
edit 1 /d read book again
```

Expected Output:

```
I've updated this task:
[T][ ] read book again
```

#### 4.2. Edit Deadline task

For Deadline tasks, you can edit the description and/or deadline.

Format: 

```
edit <task number> [/d <description>] [/b <deadline>]
```

- Note that the parameters enclosed in `[]` are optional, and can be in any order.
  - If you do not want to change the description, do not include the `/d` parameter.
  - If you do not want to change the deadline, do not include the `/b` parameter.
- `<task number>` is the number of the task in the list, as shown by the `list` command.
- `<description>` is the new description of the task.
  - The description cannot be empty.
- `<deadline>` is the new deadline of the task.
  - It must be in the format `yyyy-MM-dd HH:mm`.

Example:

Suppose the task list as shown by the `list` command is as follows:

```
Here are the tasks in your list:
1.[D][ ] return book (by: Sun 01 Jan 2023 00:00)
```

Using the `edit` command:

```
edit 1 /d return 2nd book /b 2023-01-02 00:00
```

Expected Output:

```
I've updated this task:
[D][ ] return 2nd book (by: Mon 02 Jan 2023 00:00)
```

#### 4.3. Edit Event task

For Event tasks, you can edit the description and/or start date and/or end time.

Format: 

```
edit <task number> [/d <description>] [/s <start time>] [/e <end time>]
```

- Note that the parameters enclosed in `[]` are optional, and can be in any order.
  - If you do not want to change the description, do not include the `/d` parameter.
  - If you do not want to change the start time, do not include the `/s` parameter.
  - If you do not want to change the end time, do not include the `/e` parameter.
- `<task number>` is the number of the task in the list, as shown by the `list` command.
- `<description>` is the new description of the task.
  - The description cannot be empty.
- `<start time>` is the new start time of the task.
  - It must be in the format `yyyy-MM-dd HH:mm`.
- `<end time>` is the new end time of the task
  - It must be in the format `yyyy-MM-dd HH:mm`.
- Currently, Max does not check if the start time is before the end time.

Example:

Suppose the task list as shown by the `list` command is as follows:

```
Here are the tasks in your list:
1.[E][ ] project meeting (from: Sun 01 Jan 2023 10:00 to: Sun 01 Jan 2023 11:00)
```

Using the `edit` command:

```
edit 1 /d outing with friends /s 2023-01-02 12:00 /e 2023-01-02 14:00
```

Expected Output:

```
I've updated this task:
[E][ ] outing with friends (from: Mon 02 Jan 2023 12:00 to: Mon 02 Jan 2023 14:00)
```

### 5. Delete Tasks - `delete`

Max can help you delete tasks from your task list.

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
1.[T][ ] read book
```

Using the `delete` command:
```
delete 1
```

Expected Output:

```
Noted. I've removed this task:
[T][ ] read book
Now you have 0 tasks in the list.
```

### 6. Find Tasks - `find`

Max can help you find tasks in your task list that has a certain keyword in its description.

🗒️ Note: The numbering of tasks in the search result will be the same as the numbering of tasks as shown in the `list` command.

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
1.[T][ ] read book
```

Using the `find` command:

```
find book
```

Expected Output:

```
Here is the 1 matching task in your list:
1.[T][ ] read book
```

### 7. Help - `help`

Max can show you a list of commands that you can use.

🗒️ Note: This command takes in no parameters.

Format: 

```
help
```

Expected Output:

Max will show you a help message with a list of commands that you can use.

### 8. Exit - `bye`

Ends the chat with Max.

🗒️ Note: This command takes in no parameters.

Format: 

```
bye
```

Expected Output:

Max will bid you farewell and will no longer respond to your commands.
