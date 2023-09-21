# User Guide for JavAI
As a task chatbot, I can help you to organize your tasks!
## Features 

### Add a Todo task `todo`

Adds a todo task to the task list.

### Add a Deadline task `deadline`

Adds a deadline task to the task list.

### Add an Event task `event`

Adds an event task to the task list.

### Delete a task `delete`

Deletes a task from the task list based on given index.

### List all tasks `list`

Lists all the tasks in the task list.

### Mark a task `mark`

Mark a task as done in the tasklist based on given index.

### Unmark a task `unmark`

Mark a task as undone in the tasklist based on given index.

### Find a task containing a keyword `find`

Find task(s) that have description matching the `keyword` entered by user.

### Find a task containing a keyword `reminder`

Find task(s) that have their deadlines in the next 3 days.

## Usages

### Add a Todo task `todo`

**Example of usage:** `todo borrow book`

**Expected outcome:**

```
Got it. I've added this task:
[T][ ] borrow book
Now you have 1 tasks in the list.
```

### Add a Deadline task `deadline`

**Example of usage:** `deadline Sample Deadline /by 2023-09-23 0900`

The `/by` date should be of the format `yyyy-mm-dd` and time should in 24hr format `hhmm`.

+ Make sure to include the description of the task.
+ Make sure to include /by date and time in the correct format.
+ Ensure that the date and time is past current date and time.

**Expected outcome:**

```
Got it. I've added this task:
[D][ ] Sample Deadline (by: Sep 23 2023 09:00)
Now you have 3 tasks in the list.
```

### Add an Event task `event`

**Example of usage:** `event Sample Event /from today /to tomorrow`

There should be a valid `/from` and `/to` in order for command to work.

+ Make sure to include the description of the task.
+ Make sure to include /from and /to date and time in the correct format.

**Expected outcome:**

```
Got it. I've added this task:
[E][ ] Sample Event (from: today to: tomorrow)
Now you have 4 tasks in the list.
```

### Delete a task `delete`

**Example of usage:** `delete 2`

**Expected outcome:**

```
Noted. I've removed this task:
  [D][ ] Sample Deadline (by: Sep 23 2023 09:00)
Now you have 2 tasks in the list.
```

### List all tasks `list`

**Example of usage:** `list`

**Expected outcome:**

```
Here are the 2 tasks in your list:
1.[T][ ] borrow book
2.[E][ ] Sample Event (from: today to: tomorrow)
```

### Mark a task `mark`

**Example of usage:** `mark 2`

**Expected outcome:**

```
Nice! I've marked this task as done:
[E][X] Sample Event (from: today to: tomorrow)
```

### Unmark a task `unmark`

**Example of usage:** `unmark 2`

**Expected outcome:**

```
Ok! I've marked this task as not done yet:
[E][ ] Sample Event (from: today to: tomorrow)
```

### Find a task containing a keyword `find`

**Example of usage:** `find borrow`

**Expected outcome:**

```
Here are your results for your search:
1.[T][ ] borrow book
```

### Find a task containing a keyword `reminder`

**Example of usage:** `reminder`

Command returns the tasks due in the next 3 days.

**Expected outcome:**

```
Here are the tasks that are due within next 3 days:
2.[D][ ] Sample Deadline ( by: Sep 23 2023 20:00)
```
