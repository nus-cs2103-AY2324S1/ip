# Gobble Gobble User Guide

Gobble gobble is a task manager that handles three types of tasks:

1. Todo
2. Deadline
3. Event

Gobble Gobble also stores these tasks and loads them when the application is started again.

## Usage

### Help

Lists out all the commands and their formats.

**Format:**
`help`

**Example of usage:**
`help`

**Expected outcome:**

```
Here are the commands you can use:
1. todo <description>
2. deadline <description> /by <date>
3. event <description> /from <date> /to <date>
4. list
5. done <task number>
6. delete <task number>
7. find <keyword>
8. bye
```

### List

Lists out all the tasks in the task list.

**Format:**
`list`

**Example of usage:**
`list`

**Expected outcome:**

```
1. [T][✘] read book
2. [D][✘] return book (by: Sep 17 2023)
3. [E][✘] project meeting (from: Sep 17 2023 to: Sep 18 2023)
```

### Todo

Creates a todo task with a description and shows how many tasks you have in your list.

**Format:**
`todo <DESCRIPTION>`

**Example of usage:**
`todo read book`

**Expected outcome:**

```
Got it. I've added this task:
[T][✘] read book
Now you have 1 tasks in the list.
```

### Deadline

Creates a deadline task with a description and a deadline date.

**Format**:
`deadline <DESCRIPTION> /by <DEADLINE>`

**Example of usage:**
`deadline return book /by 2023-09-17`

**Expected outcome:**

```
Got it. I've added this task:
[D][✘] return book (by: Sep 17 2023)
Now you have 2 tasks in the list.
```

### Event

Creates an event task with a description and an event date.

**Format**:
`event <DESCRIPTION> /from <START_DATE> /to <END_DATE>`

**Example of usage:**
`event project meeting /from 2023-09-17 /to 2023-09-18`

**Expected outcome:**

```
Got it. I've added this task:
[E][✘] project meeting (from: Sep 17 2023 to: Sep 18 2023)
Now you have 3 tasks in the list.
```

### Mark

Marks a task as done.

**Format:**
`mark <INDEX>`

**Example of usage:**
`mark 1`

**Expected outcome:**

```
Nice! I've marked this task as done:
[T][✓] read book
```

### Unmark

Unmarks a task as done.

**Format:**
`unmark <INDEX>`

**Example of usage:**
`unmark 1`

**Expected outcome:**

```
Nice! I've unmarked this task as done:
[T][✘] read book
```

### Find

Finds a task with the input keyword.

**Format:**
`find <KEYWORD>`

**Example of usage:**
`find book`

**Expected outcome:**

```
Here are the matching tasks in your list:
[T][✘] read book
[D][✘] return book (by: Sep 17 2023)
```

### Delete

Deletes a task with the given index in the list.

**Format:**
`delete <INDEX>`

**Example of usage:**
`delete 1`

**Expected outcome:**

```
Noted. I've removed this task:
[T][✘] read book
Now you have 2 tasks in the list.
```

### Bye

Exits the application.

**Format:**
`bye`

**Example of usage:**
`bye`

**Expected outcome:**
Application closes.