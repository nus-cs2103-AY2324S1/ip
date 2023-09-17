# Juke User Guide

## Features 

### Task tracking

Add your todos, deadlines, or events to help you keep track of them by marking 
them as done or undone.

### Task filtering

Filter your tasks by any search term

### Task tagging
Add tags to your tasks for additional customisation


## Usage

### `todo` - Add a todo task

Adds a todo task with the specified name

Example of usage: 

`todo <name>`

Expected outcome:

Create a todo task

```
todo Go to the supermarket
```

```
Got it. I've added this task:
    [T][] Go to the supermarket
Now you have 7 tasks in the list.
```

### `deadline` - Add a deadline task

Adds a deadline task with the specified name and due date

Example of usage:

`todo <name> /by <date (YYYY-MM-DD)>`

Expected outcome:

Create a deadline task

```
deadline CS2100 Assignment /by 2023-09-18
```

```
Got it. I've added this task:
    [D][] CS2100 Assignment (by: Sep 18 2023)
Now you have 8 tasks in the list.
```

### `event` - Add a event task

Adds a event task with the specified name, start date, and end date

Example of usage:

`event <name> /from <date (YYYY-MM-DD)> /to <date (YYYY-MM-DD)>`

Expected outcome:

Create an event task

```
event Winter break /from 2023-12-01 /to 2024-01-02
```

```
Got it. I've added this task:
    [E][] Winter break (from: Dec 1 2023 to: Jan 2 2024)
Now you have 9 tasks in the list.
```

### `list` - List all saved tasks

Lists all saved tasks in chronological order in which they were added.

Example of usage:

`list`

Expected outcome:

```
list
```

```
1. [T][] Go to the supermarket
2. [D][] CS2100 Assignment (by: Sep 18 2023)
3. [E][] Winter break (from: Dec 1 2023 to: Jan 2 2024)
```
### `mark` - mark a task as done

Marks a task as done using its index.

Example of usage:

`mark <index>`

Expected outcome:

The task is marked as done.
```
mark 2
```
```
Nice! I've marked this task as done:
[D][X] CS2100 Assignment (by: Sep 18 2023)
```

### `unmark` - mark a task as undone

Marks a task as undone using its index.

Example of usage:

`unmark <index>`

Expected outcome:

The task is marked as undone.
```
unmark 2
```
```
OK, I've marked this task as not done yet:
[D][] CS2100 Assignment (by: Sep 18 2023)
```

### `delete` - delete a task

Deletes a task as undone using its index.

Example of usage:

`delete <index>`

Expected outcome:

The task is deleted from your tasklist.
```
delete 2
```
```
Noted. I've removed this task:
    [D][X] CS2100 Assignment (by: Sep 18 2023)
Now you have 7 tasks in the list.
```

### `find` - find tasks with a search term

Searches for all tasks containing a specified search term

Example of usage:

`find <search term>`

Expected outcome:

Displays all tasks which contains your search term.
```
search assignment
```
```
1. [D][X] CS2100 Assignment (by: Sep 18 2023)
2. [D][X] CS2101 assignment (by: Sep 29 2023)
```

### `tag` - tag a task

Attaches a specified tag to a task based on its index

Example of usage:

`tag <index> <tag name(cannot contain whitespace)>`

Expected outcome:

Attaches a tag to your task, which will show whenever the task is displayed.
```
tag 2 important
```
```
Great! You have added a tag to this task:
1. [D][X] CS2100 Assignment #important (by: Sep 18 2023)
```

### `bye` - close the bot

Closes the bot

Example of usage:

`bye`

Expected outcome:

The bot should close after 2 seconds.
```
bye
```
```
Bye. Hope to see you again soon!
```