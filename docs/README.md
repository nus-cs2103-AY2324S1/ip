# User Guide

## Features 

### Manage Tasks

+ Add `Todo`, `Event` and `Deadline` tasks
+ Mark tasks as done
+ Find tasks by keyword
+ View relevant tasks by date

## Usage

### `todo` - Adds a Todo task to the list

Format: `todo <task description here>`

Example of usage: 

`todo eat milo drink coffee`

Expected outcome:

Duke will repeat the task you have just added and output the total number of tasks in the list.
```
Got it. I've added this task:
[T][] eat milo drink coffee
Now you have 1 task in the list.
```

### `deadline` - Adds a Deadline task to the list

Format: `deadline <task description here> /by <due date in yyyy-mm-dd>`

Example of usage: 

`deadline eat milo by 2001-02-12`

Expected outcome:

Duke will repeat the task you have just added and output the total number of tasks in the list.
```
Got it. I've added this task:
[D][] eat milo by: 2001-02-12
Now you have 2 tasks in the list.
```

### `event` - Adds an Event task to the list

Format: `event <task description here> /from <start date in yyyy-mm-dd> /to <end date in yyyy-mm-dd>`

Example of usage: 

`event apply for internships /from 2001-02-12 /to 2001-11-23`

Expected outcome:

Duke will repeat the task you have just added and output the total number of tasks in the list.
```
Got it. I've added this task:
[E][] apply for internships from: 2001-02-12 to: 2001-11-23
Now you have 3 tasks in the list.
```

### `mark` - Marks a task on the list

Format: `mark <task index here>`

Example of usage: 

`mark 2`

Expected outcome:

Duke marks completed tasks with a cross.
```
Nice! I've mark this task as done:
[D][X] eat milo by: 2001-02-12
```

### `unmark` - Unmarks a task on the list

Format: `unmark <task index here>`

Example of usage: 

`unmark 2`

Expected outcome:

Duke unmarks completed tasks by removing the cross.
```
Ok. I've marked this task as not done yet:
[D][] eat milo by: 2001-02-12
```
### `list` - Lists all tasks in the list

Format: `list`

Example of usage: 

`list`

Expected outcome:

Duke lists all tasks in the list.
```
Here are the tasks in your list:
1. [T][] eat milo drink coffee 
2. [D][] eat milo by: 2001-02-12
3. [E][] apply for internships from: 2001-02-12 to: 2001-11-23
```
### `find` - Find tasks containing keyword

Format: `find <keyword here>`

Example of usage: 

`find eat milo`

Expected outcome:

Duke lists all tasks with task description matching the keyword.
```
Here are the matching tasks in your list:
1. [T][] eat milo drink coffee 
2. [D][] eat milo by: 2001-02-12
```
### `view` - View all relevant tasks to date

Format: `view <query date here>`

Example of usage: 

`view 2001-01-01`

Expected outcome:

Duke lists all relevant undone tasks based on specified date.
```
Here are the active tasks you have happening on 2001-01-01: 
[D][ ] eat milo (by: Feb 12 2001)
```
### `delete` - Deletes task from list

Format: `delete <task index here>`

Example of usage: 

`delete 3`

Expected outcome:

Duke deletes specified task and gives total number of remaining tasks in the list.
```
Noted. I've removed this task:
[E][ ] apply for internships (from: Feb 12 2001 to: Nov 23 2001)
Now you have 2 tasks in the list.
```
