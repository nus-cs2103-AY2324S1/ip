# Chatbot name

Bob
___

# User Guide

## Features 

### List Command

Displays the list of tasks.

### Todo Command

Adds a new to-do task to the list of tasks.

### Deadline Command

Adds a new deadline task with a specific deadline to the list of tasks.

### Event Command

Adds a new event task with a specific start and end time to the list of tasks.

### Mark Command

Marks a task as completed.

### Unmark Command

Unmarks a completed task.

### Delete Command

Deletes a task from the list of tasks.

### Find Command

Searches for tasks consisting of a specific keyword.

### Exit Command

Exits the application and saves the list of tasks.

## Usage

### `list` - List Tasks

Returns a list of tasks.

Format: 
```
list
```

Example of usage: 

```
list
```

Expected outcome:

Displays the task list at the current point.

```
Here are the tasks in your list:
1.[T][X] read book
2.[D][] return book (by: Aug 15 2023 19:00)
3.[E][] project meeting (from: Aug 28 2023 16:00 to: Aug 28 2023 18:00)
4.[T][X] join sports club
```
___

### `todo` - Add Todo Task

Adds a new Todo Task with the specific description to the list of tasks.

Format: 
```
todo DESCRTIPTION
```

Example usage:

```
todo Finish iP for CS2103T
```

Expected outcome:

Displays the added todo task.

```
Got it. I've added this task:
[T][] Finish iP for CS2103T
Now you have 5 tasks in the list.
```
___

### `deadline` - Add Deadline Task

Adds a new deadline with the specific description and deadline to the list of tasks.

Format: 
```
deadline DESCRIPTION /by DATETIME (yyyy-MM-ddTHH:mm:ss)
```

Example usage:
```
deadline Submit iP for CS2103T /by 2023-08-29T18:00:00
```

Example outcome:

Displays the added deadline task.

```
Got it. I've added this task:
[D][] Submit iP for CS2103T (by: Aug 29 2023 18:00)
Now you have 6 tasks in the list.
```
___

### `event` - Add Event Task

Adds a new event with the specific description and duration to the list of tasks.

Format: 
```
event DESCRIPTION /from DATETIME (yyyy-MM-ddTHH:mm:ss) /to DATETIME (yyyy-MM-ddTHH:mm:ss)
```

Example usage:
```
event tP project meeting for CS2103T /from 2023-08-30T18:00:00 /to 2023-08-30T20:00:00
```

Example outcome:

Displays the added event task.

```
Got it. I've added this task:
[E][] tP project meeting for CS2103T (from: Aug 30 2023 18:00 to: Aug 30 2023 20:00)
Now you have 7 tasks in the list.
```
___

### `mark` - Mark Task

Marks the specified task as completed.

Format: 
```
mark INDEX
```

Example usage:
```
mark 4
```

Example outcome:

Displays the marked fourth task.

```
Nice! I've marked this task as done:
[T][X] join sports club
```
___

### `unmark` - Unmark Task

Unmarks the specified task.

Format: 
```
unmark INDEX
```

Example usage:
```
unmark 4
```

Example outcome:

Displays the unmarked fourth task.

```
OK, I've marked this task as not done yet:
[T][] join sports club
```
___

### `delete` - Delete Task

Deletes the specified task.

Format: 
```
delete INDEX
`````

Example usage:
```
delete 6
```

Example outcome:

Displays the deleted task.

```
Noted. I've removed this task:
[D][] Submit iP for CS2103T (by: Aug 29 2023 18:00)
Now you have 6 tasks in the list.
```
___

### `find` - Find Task

Finds the tasks containing the specified keyword.

Format: 
```
find KEYWORD
```

Example usage:
```
find book
```

Example outcome:

Displays the task containing the word book.

```
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][] return book (by: Aug 15 2023 19:00)
```
___

### `bye` - Exit Program

Exits the program and saves the list of tasks.

Format: 
```
bye
```

Example usage:
```
bye
```

Example outcome:

Displays the exit message and exits the program.

```
Bye. Hope to see you again soon!
```
___

Note: Please replace `INDEX`, `DESCRIPTION`, `DATETIME` and `KEYWORD` with actual values when using the commands.