# User Guide for Ronaldo
As a task chatbot, I can help you to organize your tasks with timelines and much more! SUI!
## Features 

### Add a Todo task `todo`

Adds a todo task to the task list.

### Add a Deadline task `deadline`

Adds a deadline task to the task list.

### Add an Event task `event`

Adds a event task to the task list.

### Delete a task `delete`

Deletes a task from the task list given an index.

### List all tasks `list`

Lists all the tasks in the task list.

### Mark a task `mark`

Mark a task as done in the tasklist given an index.

### Unmark a task `unmark`

Mark a task as undone in the tasklist given an index.

### Find a task containing a keyword `find`

Find a task that has description matching the `keyword` entered by user.

## Usages

### Add a Todo task `todo`

**Example of usage:** `todo borrow book`

**Expected outcome:**

```
SUI, Got it. I've added this task:
[T][ ] borrow book
SUI, Now you have 2 tasks in the list.
```

### Add a Deadline task `deadline`

**Example of usage:** `deadline cs2103 iP /by 2023-10-20 09:00`

The `/by` date should be of the format `yyyy-mm-dd` and time should in 24hr format `hh:mm`.

+ Make sure to include the description of the task.
+ Make sure to include /by date and time in the correct format.
+ Ensure that the deadline is past current date and time.

**Expected outcome:**

```
SUI, Got it. I've added this task:
[D][ ] cs2103 iP (by: Oct 20 2023 09:00)
SUI, Now you have 3 tasks in the list.
```

### Add an Event task `event`

**Example of usage:** `event MS event /from 2023-10-20 09:00 /to 2023-10-22 08:00`

The `/from` and `/to` date should be of the format `yyyy-mm-dd` and time should in 24hr format `hh:mm`.

+ Make sure to include the description of the task.
+ Make sure to include /from and /to date and time in the correct format.
+ Ensure that the deadline is after the start date and time.

**Expected outcome:**

```
SUI, Got it. I've added this task:
[E][ ] MS event (from: Oct 20 2023 09:00 to: Oct 22 2023 08:00)
SUI, Now you have 4 tasks in the list.
```

### Delete a task `delete`

**Example of usage:** `delete 2`

**Expected outcome:**

```
SUI, Noted. I've removed this task:
  [D][ ] cs2103 iP (by: Oct 20 2023 09:00)
SUI, Now you have 2 tasks in the list.
```

### List all tasks `list`

**Example of usage:** `list`

**Expected outcome:**

```
SUI, Here are the 2 tasks in your tasklist:
1.[T][ ] borrow book
2.[E][ ] MS event (from: Oct 20 2023 09:00 to: Oct 22 2023 08:00)
```

### Mark a task `mark`

**Example of usage:** `mark 2`

**Expected outcome:**

```
SUI, Nice! I've marked this task as done:
[E][X] MS event (from: Oct 20 2023 09:00 to: Oct 22 2023 08:00)
```

### Unmark a task `unmark`

**Example of usage:** `umark 2`

**Expected outcome:**

```
SUI, Nice! I've unmarked this task as done:
[E][ ] MS event (from: Oct 20 2023 09:00 to: Oct 22 2023 08:00)
```

### Find a task containing a keyword `find`

**Example of usage:** `find borrow`

**Expected outcome:**

```
Here are your results for your search:
1.[T][ ] borrow book
```
