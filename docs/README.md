# User Guide

**L** 😼 is a **desktop app for managing tasks, optimized for use via a Command Line Interface (CLI)** while still bringing delights through 
a Graphical User Interface (GUI). Other than a traditional chatbot, L also **pops up meaningful quotations**.🧙

## Features 

### 📌 Adding a task: `todo`, `deadline`, `event`

Adds a task to the task list.  

Format: `todo TASK_DESCRIPTION`  

`deadline TASK_DESCRIPTION /by YYYY-MM-DD`  

`event TASK_DESCRIPTION /from EVENT_START_TIME /to EVENT_END_TIME`  

> **_⚠️ NOTE:_** Deadline date cannot be before today.

Examples:
* `todo watch movies`
* `deadline submit user guide /by 2024-12-31`

Expected outcome:
```
Task added:
[T][ ] watch movies
Now you have 1 task in total.
"Be here now."
```

### 📝 Listing all the tasks: `list`

Shows a list of all tasks in the task list.

Format: `list` or `List`

Expected outcome:
```
Here are your tasks::
1 [T][ ] watch movies
2 [D][ ] submit user guide (by: Dec 31 2024)
"One thing at a time."
```

### ✔️ Marking a task as done/undone: `mark`, `unmark`

Marks a task as done/undone in the task list.

Format: `mark TASK_INDEX`

Examples: `mark 1`

Expected outcome:
```
Here's your modified task:
[T][X] watch movies
"Keep moving forward."
```

### 🚮 Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete TASK_INDEX`

Examples: `delete 2`

Expected outcome:
```
I've successfully deleted this task:
[D][ ] submit user guide (by: Dec 31 2024)
Now you have 1 task in total.
"Ride the waves."
```

### 🔍 Finding a task: `find`

Finds a task from the task list.

Format: `find KEYWORD`

Examples: `find movies`

Expected outcome:
```
Here are the matching tasks in your list:
1 [T][X] watch movies
"One thing at a time."
```

### ⏰ Getting the reminder: `reminder`

Shows a list of all **_unfinished_ deadlines** in the task list.

Format: `reminder`

Expected outcome:
```
Upcoming deadlines:
1 [D][ ] submit user guide (by: Dec 31 2024)
"One thing at a time."
```

### 🔚 Exiting the program: `bye`

Exits the program.

Format: `bye` or `88`

Expected outcome:
```
Bye!
"Beware the barrenness of a busy life."
```

### 🔏 Reading the data

After starting the chatbot, L will automatically read previous data saved on the hard disk. If no such data is found, L 
will start a new session.
> **_❗CAUTION:_** If data is changed into invalid format, L will not include that data in the task list.

### 🔐 Saving the data

Data are saved in the hard disk automatically after every action. There is no need to save manually.


