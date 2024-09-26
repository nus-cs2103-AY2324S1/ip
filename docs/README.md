# Duke User Guide

## Features 

### Task Management

Duke offers comprehensive task management capabilities. Users can add, delete, mark as complete, search for tasks, and even add notes to tasks.

### Graphical User Interface

Duke provides an interactive GUI, making task management a breeze. With a chatbot-like interface, users can communicate with Duke in a conversational manner.

### Persistent Storage

Duke saves your tasks automatically. When you start up Duke the next time, your tasks from the previous session will be loaded.

## Usage

### `todo` - Add a todo task

Allows the user to add a todo task to Duke.

#### Example of usage: 

`todo Read a book`

#### Expected outcome:

Duke confirms the addition of the task.

```
Got it. I've added this task:
[T][ ] Read a book
Now you have n tasks in the list.
```

### `deadline` - Add a deadline task

Allows the user to add a deadline task to Duke.

#### Example of usage:

`deadline Read a book /by 2000/11/11 2359`

#### Expected outcome:

Duke confirms the addition of the task.

```
Got it. I've added this task:
[D][ ] Read a book (by: 11-11-2000 23:59)
Now you have n tasks in the list.
```

### `event` - Add a event task

Allows the user to add a event task to Duke.

#### Example of usage:

`event Read a book /from 2000/11/11 2359 /to 2000/11/12 2359`

#### Expected outcome:

Duke confirms the addition of the task.

```
Got it. I've added this task:
[E][ ] Read a book (from: 11-11-2000 23:59, to: 11-12-2000 23:59)
Now you have n tasks in the list.
```

### `list` - List all existing tasks

Allows the user to view a list of tasks that have been added to Duke.

#### Example of usage:

`list`

#### Expected outcome:

Duke shows a list of saved tasks, or return a message saying that there is not added tasks yet.

```
1.[T][ ] Read a book
2.[D][ ] Read a book (by: 11-11-2000 23:59)
3.[E][ ] Read a book (from: 11-11-2000 23:59, to: 11-12-2000 23:59)
```
or
```
There is no task in your list yet.
```

### `mark` - Mark a task as done

Allows the user to mark an existing task as done.

#### Example of usage:

`mark 1`

#### Expected outcome:

Duke confirms the specified task is marked as done.

```
Nice! I've marked this task as done:
  [T][X] Read a book
```

### `unmark` - Unmark a task as done

Allows the user to unmark an existing task as not done.

#### Example of usage:

`unmark 1`

#### Expected outcome:

Duke confirms the specified task is marked as done.

```
OK, I've marked this task as not done yet:
  [T][ ] Read a book
```

### `delete` - Unmark a task as done

Allows the user to delete an existing task.

#### Example of usage:

`delete 2`

#### Expected outcome:

Duke confirms the specified task is deleted.

```
Noted. I've removed this task:
[D][ ] Read a book (by: 11-11-2000 23:59)
Now you have n tasks in the list.
```

### `find` - Find tasks with a keyword

Allows the user to view a list of tasks within the existing tasks that contain a keyword provided.

#### Example of usage:

`find book`

#### Expected outcome:

Duke shows a list of tasks that contain the keyword "book".

```
These are the matching tasks in your list:
1.[T][ ] Read a book
2.[E][ ] Read a book (from: 11-11-2000 23:59, to: 11-12-2000 23:59)
```

### `editN` - Update/ add a notes for a task

Allows the user to update/add a notes for an existing task.

#### Example of usage:

`editN 1 it might take some time`

#### Expected outcome:

Duke confirms the notes has been added to that task.

```
Your notes for the task:
[T][ ] Read a book
is successfully updated! Current notes:
might take some time
```

### `peekN` - Peek the notes for a task

Allows the user to see the notes for an existing task, if there is any.

#### Example of usage:

`peekN 1`

#### Expected outcome:

Duke returns the notes for that task, or says there is no notes for the selected task.

```
Your notes for the task:
[T][ ] Read a book
is: might take some time
```
or
```
You have not added any notes fir the task:
[T][ ] Read a book
```