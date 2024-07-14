# Sara Chatbot
# User Guide

## Features
### Keep track of your tasks
Add todos, events, and deadlines with their corresponding date and time
### Mark your tasks as done
Mark each task as done to keep track of your undone tasks or un-mark them when unfinished
### Search through your task list
Use the find command to find all relevant tasks that matches your keyword


## Usage
### `todo` - Adds a to-do task
Adds a to-do task with the specified content to the task list.

Format: `todo <TASKNAME>` or `t <TASKNAME>`

Examples:
- `todo read`
- `t buy food`

**Expected outcome:**
```
Got it. I've added this task:
[T][] <TASKNAME>
Now you have 1 task in the list.
```

<br/>

###  `deadline` - Adds a deadline with date and time
Adds a new deadline with the specified content with date and time to your tasks list.

Format: `deadline <TASKNAME> /by <yyyy-MM-dd HHmm>` or `d <TASKNAME> /by <yyyy-MM-dd HHmm>`

Examples:
- `deadline return book /by 2023-10-10 12:00`
- `d return book /by 2023-10-10 12:00`

**Expected outcome:**
```
Got it. I've added this task:
[D][] <task-name> (by: <date-and-time>)
Now you have 1 task in the list.
```

<br/>

###  `event` - Adds a deadline with date and time
Adds a new event with the specified content with date and time to your tasks list.

Format: `Event <TASKNAME> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>` or `e <TASKNAME> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`

Examples:
- `event meeting /from 2023-10-10 12:00 /to 2023-10-10 13:00`
- `e return book /by 2023-10-10 12:00 /to 2023-10-10 13:00`

**Expected outcome:**

```
Got it. I've added this task:
[E][] <TASKAME> (from: <date-and-time> to: <date-and-time>)
Now you have 1 task in the list.
```
<br/>

### `list` - List out all tasks in the list
Displays the full list of all tasks in the list.

Format: `list` or `l`

Examples:
- `list`
- `l`

**Expected outcome:**

```
Here are the tasks in your list:
    1. [T][] Read book
    2. [D][] Return book (by: 2023-12-12 15:30)
```

<br />

###  `mark` - Marks a task done
The corresponding task will be marked as completed

Format: `mark <TASKNUMBER>` or `m <TASKNUMBER>`

Examples:
- `mark 5`
- `m 2`

**Expected outcome:**
```
OK, I've marked this task as done:
    1. [T][X] Read book
    2. [D][] Return book (by: 2023-12-12 15:30)
```

<br />

###  `unmark` - Marks a task undone
The corresponding task will be unmarked as completed

Format: `unmark <TASKNUMBER>` or `u <TASKNUMBER>`

Examples:
- `unmark 5`
- `u 2`

**Expected outcome:**
```
OK, I've marked this task as not done yet:
    1. [T][X] Read book
    2. [D][] Return book (by: 2023-12-12 15:30)
```

<br />

### `delete` - Deletes a task
The corresponding task will be deleted from the task list

Format: `delete <TASKNUMBER>` or `del <TASKNUMBER>`

Examples:
- `delete 5`
- `del 2`

**Expected outcome:**
```
Noted. I've removed this task:
[T][] Read
Now you have 1 task in the list.
```
<br />

### `find` - Finds task
Tasks that contains the specified keyword will be displayed.

Format: `find <keyword>` or `f <keyword>`

Examples:
- `find read`
- `f eat`

**Expected outcome:**
```
Here are the matching tasks in your list:
    1. [T][] Read book
    2. [D][] Return book (by: 2023-12-12 15:30)
```

<br />

### `bye` - Exits the chatbot
Terminates the program

Format: `bye`

Example:
- `bye`

**Expected outcome:**
```
Bye. Hope to see you again soon!
```