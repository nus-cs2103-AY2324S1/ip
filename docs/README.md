# User Guide for RyamBot

![img.png](img.png)

## Features

### Keep track of To-Do tasks

Duke keeps track of your To-Do list.

### Keep track of Deadline tasks

Duke keeps track of your deadlines and the date they are supposed to be completed by.

### Keep track of Event tasks

Duke keeps track of your events and the date they are being held on.

### Find the tasks with specific keyword(s)

Duke allows you to find tasks that contain keyword(s) that you input.

## Usage

### `todo` - Adds a todo task

Duke parses the input and adds the todo task into its memory.

Example of usage:

`todo borrow book`

Expected outcome:

```
Got it. I've added this task:
[T][X] borrow book
Now you have 1 tasks in the list.
```

### `deadline` - Adds a deadline task

Duke parses the input and adds the deadline task into its memory.

Example of usage:

`deadline return book /by 121213 2359`

Expected outcome:

```
Got it. I've added this task:
[D][ ] return book (by: 12 Dec 2013, 11:59PM)
Now you have 2 tasks in the list.
```

### `event` - Adds a deadline task

Duke parses the input and adds the event task into its memory.

Example of usage:

`event Go to the Bar /from 121213 2359 /to 121214 2359`

Expected outcome:

```
Got it. I've added this task:
[E][ ] Go to the Bar (from: 12 Dec 2013, 11:59PM to: 12 Dec 2014, 11:59PM)
Now you have 3 tasks in the list.
```

### `list` - Lists all tasks

Duke lists all tasks that it is keeping track of.

Example of usage:

`list`

Expected outcome:

```
1. [T][X] borrow book
2. [D][ ] return book (by: 12 Dec 2013, 11:59PM)
3. [E][ ] Go to the Bar (from: 12 Dec 2013, 11:59PM to: 12 Dec 2014, 11:59PM)
```

### `mark` - Marks a task as done

Duke marks a task as done.

Example usage:

`mark 1`

Expected outcome:

```
This task is marked as done:
1. [T][X] borrow book
```

### `delete` - Delete a task

Duke deletes the task from its memory.

Example usage:

`delete 1`

Expected outcome:

```
This task has been deleted:
[T][X] borrow book
Now you have 2 tasks in the list.
```

### `find` - Find related tasks

Duke finds the tasks that contain keyword(s) input from user.

Example usage:

`find book`

Expected outcome:

```
1. [D][X] return book (by: 12 Dec 2013, 11:59PM)
2. [E][X] go to book event (from: 12 Dec 2013, 11:59PM to: 12 Dec 2014, 11:59PM)
```