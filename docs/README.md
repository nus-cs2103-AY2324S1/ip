# User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the
benefits of a Graphical User Interface (GUI).

- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding a todo: `todo`](#adding-a-todo---todo)
  - [Adding a deadline: `deadline`](#adding-a-deadline---deadline)
  - [Adding an event: `event`](#adding-an-event---event)
  - [Printing the list: `list`](#printing-the-list---list)
  - [Marking a task: `mark`](#marking-a-task---mark)
  - [Unmarking a task: `unmark`](#unmarking-a-task---unmark)
  - [Deleting a task: `delete`](#deleting-a-task---delete)
  - [Finding in list: `find`](#finding-in-list---find)
  - [Displaying statistics: `stats`](#displaying-statistics---stats)
  - [Filtering completed tasks: `completed`](#filtering-completed-tasks---completed)
  - [Filtering uncompleted tasks: `uncompleted`](#filtering-uncompleted-tasks---uncompleted)
  - [Filtering todos: `todos`](#filtering-todos---todos)
  - [Filtering deadlines: `deadlines`](#filtering-deadlines---deadlines)
  - [Filtering events: `events`](#filtering-events---events)
  - [Exiting the program: `bye`](#exiting-the-program---bye)
- [Command Summary](#command-summary)

---

## Quick Start

1. Ensure you have `Java 11` or above installed in your computer.
2. Download the latest duke.jar from [here](https://github.com/tllshan/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar duke.jar` commmand to run the application.
5. Type the command in the command box and press Enter to execute it. You may refer to the Features below for the details of each command.

---

## Features

### Adding a todo - `todo`

Adds a todo to the list.

Format: `todo DESCRIPTION`

- A todo is a task that has only a description.

Example: `todo read book`

Expected output: 
```
Got it. I've added this task:
    [ ][T] read book
Now you have 1 tasks in the list.
```

### Adding a deadline - `deadline`

Adds a deadline to the list

Format: `deadline DESCRIPTION /by DUE DATE`

- A deadline is a task that has a description and a due date. 
- Date must be in the format YYYY-MM-DD.

Example: `deadline return book /by 2023-09-22`

Expected output:
```
Got it. I've added this task:
    [ ][D] return book /by 2023-09-22
Now you have 2 tasks in the list.
```

### Adding an event - `event`

Adds an event to the list.

Format: `event DESCRIPTION /from START DATE /to END DATE`

- An event is a task that has a description, a start date, and an end date. 
- Dates must be in the format YYYY-MM-DD.

Example" `event holiday /from 2023-12-20 /to 2023-12-25`

Expected output:
```
Got it. I've added this task:
    [ ][E] return holidy /from 2023-12-20 /to 2023-12-25
Now you have 3 tasks in the list.
```

### Printing the list - `list`

Prints a numbered list of tasks.

Format: `print`

- `X` indicates tasks that have been marked.
- `T`, `D`, and `E` tags indicate todos, deadlines and events respectively. 
- Deadlines and events are printed with the relevant dates, formatted as DD/MM/YYYY.

Expected output:
```
Here are the tasks in your list:
    1.[ ][T] read book
    2.[ ][D] return book (by: 29/09/2023)
    3.[ ][E] holiday (from: 20/12/2023 to: 25/12/2023)
```

### Marking a task - `mark`

Marks a task as done.

Format: `mark TASK INDEX`

- Marks the task at the specified `TASK INDEX`.
- The index refers to the index number shown in list command.
- The index must be a positive integer.

Example: `mark 2`

Expected output:
```
Nice! I've marked this task as done:
    [X][D] return book (by: 29/09/2023)
```

### Unmarking a task - `unmark`

Marks a task as not done yet.

Format: `unmark TASK INDEX`

- Unmarks the task at the specified `TASK INDEX`.
- The index refers to the index number shown in list command.
- The index must be a positive integer.

Example: `unmark 2`

Expected output:
```
OK, I've marked this task as not done yet:
    [ ][D] return book (by: 29/09/2023)
```

### Deleting a task - `delete`

Deletes a task from the list.

Format: `delete TASK INDEX`

- Deletes the task at the specified `TASK INDEX`.
- The index refers to the index number shown in list command.
- The index must be a positive integer.

Example: `delete 1`

Expected output:
```
Noted. I've removed this task.
    [ ][T] read book
Now you have 2 tasks in the list.
```

### Finding in list - `find`

Finds tasks containing a key word or phrase.

Format: `find KEYWORD`

- Finds and lists tasks with the given `KEYWORD`.
- The search is case-sensitive. e.g. `book` will not match `Book`.

Example: `find book`

Expected output:
```
Here are the matching tasks in your list:
    1.[X][D] return book (by 29/09/2023)
```

### Displaying statistics - `stats`

Displays the number of each type of task, and the percentage of tasks completed.

Format: `stats`

Expected output:
```
Your tasklist consists of:
    - 1 todos
    - 2 deadlines
    - 3 events
You are 66% done with all tasks.
```

### Filtering completed tasks - `completed`

Displays tasks that have been marked as done.

Format: `completed`

Expected output:
```
Congratulations! You have completed 2 of 3 tasks!
    1.[X][T] read book
    2.[X][E] holiday (from: 20/12/2023 to: 25/12/2023)
```

### Filtering uncompleted tasks - `uncompleted`

Displays tasks that have not been marked as done.

Format: `uncompleted`

Expected output:
```
You still have 1 of 3 tasks uncompleted. Jia you!
    1.[ ][D] return book (by: 22/09/2023)
```

### Filtering todos - `todos`

Displays all todos in the list.

Format: `todos`

Expected output:
```
You have 1 todos on your list!
    1.[X][T] read book
```

### Filtering deadlines - `deadlines`

Displays all deadlines in the list.

Format: `deadlines`

Expected output:
```
You have 2 deadlines on your list!
    1.[ ][D] return book (by: 22/09/2023)
    2.[X][D] submit report (by; 01/10/2023)
```

### Filtering events - `events`

Displays all events in the list.

Format: `events`

Expected output:
```
You have 2 events on your list!
    1.[X][E] holiday (from: 20/12/2023 to: 25/12/2023)
    2.[ ][E] exams (from: 30/11/2023 to: 05/12/2023)
```

### Exiting the program - `bye`

Displays exit message and disables user input.

Format: `bye`

Expected output:
```
Bye. Hope to see you again soon!
```
---

## Command Summary

- `todo DESCRIPTION`
- `deadline DESCRIPTION /by DUE DATE`
- `event DESCRIPTION /from START DATE /to END DATE`
- `list`
- `mark TASK INDEX`
- `unmark TASK INDEX`
- `delete TASK INDEX`
- `find KEYWORD`
- `stats`
- `completed`
- `uncompleted`
- `todos`
- `deadlines`
- `events`
- `bye`