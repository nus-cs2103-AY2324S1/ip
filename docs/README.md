# Chatbot name

Chad

---

# User Guide

## Features

### List Command

Displays the list of tasks.

### Todo Command

Adds a new to-do task.

### Deadline Command

Adds a new task with a specific deadline.

### Event Command

Adds a new event task.

### Delete Command

Deletes a task from the list.

### Mark Command

Marks a task as completed.

### Unmark Command

Unmarks a completed task.

### Find Command

Searches for tasks containing a specific keyword.

### Exit Command

Exits the application.

## Usage

### `list` - List Tasks

Returns a list of tasks.

Format: `list`

Example of usage:

```
list
```

Expected outcome:

```
You have 4 tasks in the list.
1.[T][X] borrow book
2.[D][ ] return book (by: Dec 22 2019 18:00)
3.[D][ ] get book back (by: Jan 11 2019 12:00)
4.[E][ ] smth (from: Oct 10 2023 10:10 to: Dec 12 2023 12:12)
```

---

### `todo` - Add Todo Task

Adds a new todo task with the specified description.

Format: `todo DESC`

Example of usage:

```
todo Submit IP for CS2103T
```

Expected outcome:

```
Got it. I've added this task:
	[T][ ] Submit IP for CS2103T

You have 4 tasks in the list.
```

---

### `deadline` - Add Deadline Task

Adds a new deadline task with the specified description and deadline.

Format: `deadline DESC /by DATETIME (yyyy-MM-dd HH:mm>)`

Example of usage:

```
deadline Submit TP submission /by 2023-09-15 23:59
```

Expected outcome:

```
Got it. I've added this task:
	[D][ ] Submit TP submission (by: Sep 15 2023 23:59)
```

---

### `event` - Add Event Task

Adds a new event task with the specified description and duration.

Format: `event DESC /from DATETIME (yyyy-MM-dd HH:mm>) /to DATETIME (yyyy-MM-dd HH:mm>)`

Example of usage:

```
event CS2103T PE /from 2023-11-17 16:00 /to 2023-11-17 18:00
```

Expected outcome:

```
Got it. I've added this task:
	[E][ ] CS2103T PE (from: Nov 17 2023 16:00 to: Nov 17 2023 18:00)
```

---

### `delete` - Delete Task

Deletes the specified task.

Format: `delete INDEX`

Example of usage:

```
delete 4
```

Expected outcome:

```
Noted. I've removed this task:
	[E][ ] smth (from: Oct 10 2023 10:10 to: Dec 12 2023 12:12)

You have 3 tasks in the list.
```

---

### `mark` - Mark Task

Marks the specified task as completed.

Format: `mark INDEX`

Example of usage:

```
mark 2
```

Expected outcome:

```
Nice! I've marked this task as done:
	[D][X] return book (by: Dec 22 2019 18:00)
```

---

### `unmark` - Unmark Task

Unmarks the specified task as completed.

Format: `unmark INDEX`

Example of usage:

```
unmark 2
```

Expected outcome:

```
OK, I've marked this task as not done yet:
	[D][ ] return book (by: Dec 22 2019 18:00)
```

---

### `find` - Find Task

Finds tasks containing the specified keyword.

Format: `find KEYWORD`

Example of usage:

```
find book
```

Expected outcome:

```
Here are the matching tasks in your list:0.[T][X] borrow book
1.[D][ ] return book (by: Dec 22 2019 18:00)
2.[D][ ] get book back (by: Jan 11 2019 12:00)
```

---

### `bye` - Exit Program

Exits the program.

Format: `bye`

Example of usage:

```
bye
```

Expected outcome:

```
expected output
```

---

Note: Please replace `INDEX`, `DESC`, `KEYWORD`, `DATETIME` with actual values when using the commands.