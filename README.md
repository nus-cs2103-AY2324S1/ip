# Bot User Guide

## Getting Started
1. Check that you are using Java 11
2. Download the latest JAR file
3. Navigate to the directory containing the downloaded JAR file
4. Run java -jar duke.jar to start the application

## Features

### Creating a new task: `todo`, `deadline`, `event`
Add a new task of the specified type to the task list.

**Format:**
1. `todo [TASK]`
2. `deadline [TASK] /by [DD/MM/YYYY HHmm]`
3. `event [TASK] /from [DD/MM/YYYY HHmm] /to [DD/MM/YYYY HHmm]`

**Examples:**
- `todo buy lunch`
- `deadline submit report /by 30/09/2023 2359`
- `event attend workshop /from 28/09/2023 1000 /to 28/09/2023 1800`

**Expected Bot output:**
```
Got it. I've added this task:
[T][] buy lunch
Now you have 1 tasks in the list
```

### Deleting a task: `delete`

Delete an existing task from the task list.

**Format:** `delete [TASK_NUMBER]`

**Example:** `delete 1`

**Expected Bot output:**
```
Noted. I've removed this task:
[T][] buy lunch
Now you have 0 tasks in the list
```

### Listing all tasks: `list`

List all the tasks that have been added to the task list.

**Format:** `list`

**Expected Bot output:**
```
Here are the tasks in your list:
1. [T][] buy lunch
2. [D][] submit report (by: Sep 30 2023 23:59)
3. [E][] attend workshop (from: Sep 28 2023 10:00, to: Sep 28 2023 18:00)
```

### Marking a task: `mark`

Mark an existing task as done, indicated by X.

**Format:** `mark [TASK_NUMBER]`

**Example:** `mark 1`

**Expected Bot output:**
```
Nice! I've marked this task as done:
[T][X] buy lunch
```

### Finding a task: `find`

Find and list out tasks containing the keyword entered.

**Format:** `find [KEYWORD]`

**Example:** `find report`

**Expected Bot output:**
```
Here are the matching tasks in your list:
1. [D][] submit report (by: Sep 30 2023 23:59)
```

### Getting help: `help`

List all commands Bot understands.

**Format:** `help`

**Expected Bot output:**
```
Here are the commands I understand:
1. list
2. todo [TASK]
3. deadline [TASK] /by [DD/MM/YYYY HHmm]
4. event [TASK] /from [DD/MM/YYYY HHmm] /to [DD/MM/YYYY HHmm]
5. mark [TASK_NUMBER]
6. delete [TASK_NUMBER]
7. find [KEYWORD]
8. bye
9. help
```

### Exiting the application: `bye`

Exit the application after 3 seconds.

**Format:** `bye`

**Expected Bot output:**
```
Bye! Hope to see you again soon!
Bot is leaving in 3 seconds...
```

## Command Summary

Command | Format
--------|------------------
**Todo** | `todo [TASK]`
**Deadline** | `deadline [TASK] /by [DD/MM/YYYY HHmm]` 
**Event** | `event [TASK] /from [DD/MM/YYYY HHmm] /to [DD/MM/YYYY HHmm]`
**Delete** | `delete [TASK_NUMBER]`
**List** | `list`
**Mark** | `mark [TASK_NUMBER]` 
**Find** | `find [KEYWORD]` 
**Help** | `help` 
**Exit** | `bye`
