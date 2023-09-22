# User Guide for Noel ChatBot

## Features

### `todo` - Adding a ToDo

Deletes a specified task from the task list.

### `deadline` - Adding a Deadline

Adds a deadline task to the task list.

### `event` - Adding an Event

Adds an event task to the task list.

### `list` - Listing All Tasks

Lists all tasks in the task list.

### `delete` - Deleting a Task

Deletes a specified task from the task list.

### `find` - Finding a Task

Finds tasks that match the given keyword.

### `mark` - Marking a Task as Done

Marks a specified task as done.

### `unmark` - Marking a Task as un-done

Marks a specified task as un-done.

### `snooze` - Snoozing a Task

Updates the time of a specified task.

### `bye` - Exiting the Program

Terminates the program and displays a farewell message.

### Invalid Command

If an unrecognized command is inputted, the bot will return a message indicating that the input command is invalid.

## Usage

### `todo` - Adding a ToDo

**Example of usage:**

**Format:** `todo taskName`

Expected outcome:
```
Got it. I've added this task: [T][] taskName
Now you have have 1 task in the list.
```

Insufficient Commands:

`todo `

Expected outcome:
```
Invalid! The description of a deadline cannot be empty.
```
<br>

### `Deadline` - Adding a Deadline

**Example of usage:**

**Format:** `deadline taskName /by yyyy-mm-dd HH:MM`
or
`deadline taskName /by dd/mm/yyyy HH:MM`

Expected outcome:
```
Got it. I've added this task: [D][] taskName (by: yyyy-m-dd HH:MM)
Now you have have 1 task in the list.
```

Invalid Examples:

1. Invalid Date

`deadline read chapter 1 /by 2023/02-02`

Expected outcome:
```
Invalid Date!
```
2. Insufficient Commands

`deadline read chapter 1 /by ` or `deadline read chapter 1 2034-02-02 06:00 `

Expected outcome:
```
Invalid! The descriptions of a deadline cannot be empty.
```

<br>

### `event` - Adding an Event

**Example of usage:**

**Format:** `event taskName /from yyyy-mm-dd HH:MM /to yyyy-mm-dd HH:MM`
or
`event taskName /from dd/mm/yyyy HH:MM /to dd/mm/yyyy HH:MM`

Expected outcome:
```
Got it. I've added this task: [E][] taskName (from: yyyy-m-dd HH:MM to: yyyy-m-dd HH:MM)
Now you have have 1 task in the list.
```

Examples:

`event ori /from 2023-04-02 06:00 /to 2023-04-04 06:00`

Expected outcome:
```
Got it. I've added this task: [E][] taskName (from: 2023-04-02 06:00 to: 2023-04-04 06:00)
Now you have have 1 task in the list.
```

<br>

### `list` - Lists all stored tasks

When executed, this command will return a string representing the list of all tasks the user has.
If there are no tasks, it will return a message indicating that the task list is empty.

Example of usage:

`list`

Expected outcome:
```
1.[E][] orientation (from:2023-04-04 06:00 to: 2023-05-05 06:00)
2.[D][] IP (by:2023-03-03 06:00)`
```

Lists the previously saved tasks.

<br>

### `find` - Finding a Task

**Format:** `find KEYWORD`

**Example of usage:**

Example of usage:

`find orientation`

Expected outcome:
```
Here are the matching tasks in your list:
1.[E][] orientation (from:2023-04-04 06:00 to: 2023-05-05 06:00)
```
<br> 

### `mark` - Marking a Task as Done

**Format:** `mark TASK_NUMBER`

**Example of usage:**

Example of usage:

```
mark 1
```

Expected outcome:

```
Nice! I've marked this task as done: [E][X] orientation (from:2023-04-04 06:00 to: 2023-05-05 06:00)
```

Invalid Usage (out of bounds):

```
mark 200
```

Expected outcome:

```
Out of bounds!
```

<br>

### `unmark` - Unmarking a Task

**Format:** `unmark TASK_NUMBER`

**Example of usage:**

Example of usage:

```
unmark 1
```

Expected outcome:
```
Ok, I've marked this task as not done yet: [E][] orientation (from:2023-04-04 06:00 to: 2023-05-05 06:00)
```

<br>

### `snooze` - Snoozing a Task

**Format:** `snooze TASK_NUMBER /by yyyy-mm-dd HH:MM` or `snooze TASK_NUMBER /from yyyy-mm-dd HH:MM /to yyyy-mm-dd HH:MM`

**Example of usage:**

Example of usage:

```snooze 2 /by 2023-03-05 06:00```

Expected outcome:

```Updated Task! [D][X] read books (by:2023-03-05 06:00)```

Example of usage:

```snooze 1 /from 2023-03-05 06:00 /to 2023-03-07 06:00```

Expected outcome:

```Updated Task! [E][X] Orientation (from:2023-03-05 06:00 to: 2023-03-07 06:00)```

<br> 

### `bye` - Exiting the Program

**Format:** `bye`

**Example of usage:**

Example of usage:

```bye```

Expected outcome:

```Bye. Hope to see you again soon!```


