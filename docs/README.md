# TehO's User Guide

## Features 

### Manages tasks

Keeps track of user's tasks and the progress of each task.

### Lists tasks

Lists tasks according to order the tasks was added or alphabetical order. 

## Usage

### About the command format:
- Words in `UPPER_CASE` are the parameters to be supplied by the user.
- All date inputs should be in the format: `yyyy-MM-dd`
- All date outputs will be in the format: `MMM dd yyyy`

### `todo` - Adds a todo task into the list

Format: `todo TASK`

Example of usage: `todo borrow book`

Expected outcome: Adds task into the list.
```
Got it. I've added this task:
[T][ ] borrow book
Now you have 1 task(s) in the list.
```
### `deadline` - Adds a deadline task into the list

Format: `deadline TASK /by DUE_DATE`

Example of usage: `deadline return book /by 2023-12-14`

Expected outcome: Adds task into the list.
```
Got it. I've added this task:
[D][ ] return book (by: Dec 14 2023)
Now you have 2 task(s) in the list.
```

### `event` - Adds a event task into the list

Format: `event TASK /from START_DATE /to END_DATE`

Example of usage: `event book fair /from 2023-12-14 /to 2023-12-15`

Expected outcome: Adds task into the list.
```
Got it. I've added this task:
[E][ ] book fair (from: Dec 14 2023 to: Dec 15 2023)
Now you have 3 task(s) in the list.
```
### `mark` - Marks a task in the list as done

Format: `mark TASK_NUMBER`

Example of usage: `mark 1`

Expected outcome: Marks the first task in list as done.
```
Good job! I've marked this task as done:
[T][X] borrow book
```
### `unmark` - Unmarks a task in the list as undone

Format: `unmark TASK_NUMBER`

Example of usage: `unmark 1`

Expected outcome: Unmarks the first task in list as udone.
```
Okay, I've marked this task as not done yet:
[T][ ] borrow book
```

### `delete` - Deletes a task in the list

Format: `delete TASK_NUMBER`

Example of usage: `delete 1`

Expected outcome: Deletes the first task in list.
```
Noted. I've removed this task:
[T][ ] borrow book
Now you have 2 task(s) in the list.
```

### `find` - Finds task(s) in the list

Format: `find TASK_DESCRIPTION`

Example of usage: `find book`

Expected outcome: Generates list of all tasks matching the task description.
```
Here are the matching task(s) in your list:
[D][ ] return book (by: Dec 14 2023)
[E][ ] book fair (from: Dec 14 2023 to: Dec 15 2023)
```

### `list` - Lists task(s) in the list

Format: `list`

Example of usage: `list`

Expected outcome: Generates list of all tasks.
```
Here are the task(s) in your list:
1.[D][ ] return book (by: Dec 14 2023)
2.[E][ ] book fair (from: Dec 14 2023 to: Dec 15 2023)
```

### `sortAlphabetically` - Lists task(s) in the list in alphabetical order

Format: `sortAlphabetically`

Example of usage: `sortAlphabetically`

Expected outcome: Generates list of all tasks in alphabetical order.
```
Here are the tasks in your list in alphabetical order:
1.[E][ ] book fair (from: Dec 14 2023 to: Dec 15 2023)
2.[D][ ] return book (by: Dec 14 2023)
```
### `bye` - Exits the program

Format: `bye`

Example of usage: `bye`

Expected outcome: TehO generates a goodbye message. 
```
Bye! Have a great day & see you again soon:)
```