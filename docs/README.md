# User Guide
***
JonBird is an application that manages your tasks.

## Features
___

| Feature  | Function                                         |
|----------|--------------------------------------------------|
| list     | Lists all the existing tasks                     |
| todo     | Adds task without date/time attached<br/> to it  |
| deadline | Adds task with a deadline                        |
| event    | Adds task to be done within a period<br/>of time |
| delete   | Deletes specific task                            |
| mark     | Marks specific task as done                      |
| unmark   | Marks specific task as undone                    |
| find     | Finds task that contains specific <br/>keywords  |
| undo     | Undo the last command that you<br/>have entered  |
| bye      | Exits the application                            |


## Usage
***

### `list`
Lists all the existing tasks

Example of usage: 

`list`

Expected outcome:

Displays all existing tasks.

```
JonBird:
    Here are the tasks in your list:
        1. [T][] walk to school
        2. [D][] read notes (by: Nov 11 1999 12:12)
        3. [T][X] swim
```
___

### `todo`
Adds task without date/time attached to it.

Format:

`todo <task description>`

Example of usage:

`todo read book`

Expected outcome:

Adds task to task list and display confirmation message, with number of tasks remaining in the list.

```
JonBird:
    Got it. I've added this task:
        [T][] read book
    Now you have 4 tasks in the list.
```
___

### `deadline`
Adds task with a deadline.

Format:

`deadline <task description> /by <yyyy-MM-dd HH:mm>`

Example of usage:

`deadline do lab1 /by 2023-09-12 23:59`

Expected outcome:

Adds task to task list and display confirmation message, with number of tasks remaining in the list.

```
JonBird:
    Got it. I've added this task:
        [D][] do lab1 (by: Sep 12 2023 23:59)
    Now you have 5 tasks in the list.
```
___

### `event`
Adds task to be done within a period of time.

Format:

`event <task description> /from <yyyy-MM-dd HH:mm> /to <yyyy-MM-dd HH:mm>`

Example of usage:

`event watch lecture /from 2023-09-13 11:00 /to 2023-09-13 14:00`

Expected outcome:

Adds task to task list and display confirmation message, with number of tasks remaining in the list.

```
JonBird:
    Got it. I've added this task:
        [E][] watch lecture (from: Sep 13 2023
11:00 to: 14:00)
    Now you have 6 tasks in the list.
```
___

### `delete`
Deletes a specific task.

Format:

`delete <task index>`

Example of usage:

`delete 4`

Expected outcome:

Deletes task from task list and display confirmation message, with number of tasks remaining in the list.

```
JonBird:
    Noted. I've removed this task:
        [T][] read book
    Now you have 5 tasks in the list.
```
___

### `mark`
Marks a specific task as done.

Format:

`mark <task index>`

Example of usage:

`mark 4`

Expected outcome:

Marks task as done and display confirmation message.

```
JonBird:
    Nice! I've marked this task as done:
        [D][X] do lab1 (by: Sep 12 2023 23:59)
```
___

### `unmark`
Marks a specific task as undone.

Format:

`unmark <task index>`

Example of usage:

`unmark 4`

Expected outcome:

Marks task as undone and display confirmation message.

```
JonBird:
    OK, I've marked this task as not done yet:
        [D][] do lab1 (by: Sep 12 2023 23:59)
```
___

### `find`
Finds task with description that contains specific keywords.

Format:

`find <keywords>`

Example of usage:

`find lab1`

Expected outcome:

Finds all the task with matching descriptions and display them with their corresponding task index.

```
JonBird:
    Here are the matched tasks in your list:
        4. [D][] do lab1 (by: Sep 12 2023 23:59)
```
___

### `undo`
Undo the last command that you have entered. Can continue to undo all your previous commands, excluding undo and commands like bye, list, find which do not modify the task list.

Example of usage:

`undo`

Expected outcome:

Undo the last command and display confirmation message.

```
JonBird:
    I have undo your last command!
    I have marked: [D][X] do lab1 (by: Sep 12 2023
23:59)
```
___

### `bye`
Exits the application.

Example of usage:

`bye`

Expected outcome:

The application will close by itself.