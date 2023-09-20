# User Guide for _Duke_

## Features 

### Add Event: ```event```

Adds an Event to the list.

### Add Deadline: ```deadline```

Adds a Deadline to the list.

### Add Todo: ```todo```

Adds a Todo to the list.

### Delete: ```delete```

Deletes a task from the list.

### Mark: ```mark```

Marks a task in the list as done.

### Unmark: ```unmark```

Unmarks a task in the list.

### List: ```list```

Lists all tasks in the list.

### On: ```on```

Lists all tasks in the list on a given date.

### Find: ```find```

Lists all tasks in the list that contains a given keyword.

### Get statistics: ```stats```

Gets key statistics about the tasks in the list.

### Exit: ```bye```

Exits from the application.

## Usage

### `event` - Adds a event to the list

Format: ```event EVENT_NAME /from EVENT_START /to EVENT_END```

* EVENT_START and EVENT_END should be in the form "YYYY-MM-DD HHmm"

Examples
* ```event award ceremony /from 2023-09-20 1400 /to 2023-09-20 1600```
* ```event marathon /from 2023-09-20 2300 /to 2023-09-21 0300```

Expected output:

```
Got it. I've added this task:
[E][] EVENT_NAME (from: EVENT_START to: EVENT_END)
Now you have 1 task in the list.
```
### `deadline` - Adds a deadline to the list

Format: ```deadline DEADLINE_NAME /by DEADLINE_BY```

* DEADLINE_BY should be in the form "YYYY-MM-DD HHmm"

Examples
* ```deadline math homword /by 2023-09-20 1600```
* ```deadline report /by 2023-09-20 2200```

Expected output:

```
Got it. I've added this task:
[D][] DEADLINE_NAME (by: DEADLINE_BY)
Now you have 1 task in the list.
```

### `todo` - Adds a todo to the list

Format: ```todo TODO_NAME```

Examples
* ```todo run```
* ```todo math homework```

Expected output:

```
Got it. I've added this task:
[T][] TODO_NAME
Now you have 1 task in the list.
```

### `delete` - Deletes a task from the list

Format: ```delete TASK_NUMBER```

Examples
* ```delete 1```

Expected output:

```
Noted. I've removed this task:
[T][] TODO_NAME
Now you have 1 task in the list.
```

### `mark` - Marks a task in the list as done

Format: ```mark TASK_NUMBER```

Examples
* ```mark 1```

Expected output:

```
Nice!. I've marked this task as done:
[T][X] TODO_NAME
```

### `unmark` - Unmarks a task in the list

Format: ```unmark TASK_NUMBER```

Examples
* ```unmark 1```

Expected output:

```
OK, I've marked this task as not done yet:
[T][] TODO_NAME
```

### `list` - Lists all tasks in the list

Format: ```list```

Examples
* ```list```

Expected output:

```
Here are the tasks in your list:
1. [T][] TODO_NAME
2. [D][] DEADLINE_NAME (by: DEADLINE_BY)
3. [E][] EVENT_NAME (from: EVENT_FROM to: EVENT_TO)
```

### `on` - Lists all tasks in the list on a given date

Format: ```on DATE```
* DATE should be in the form "YYYY-MM-DD HHmm"

Examples
* ```on 2023-09-20```

Expected output:

```
1. [T][] TODO_NAME
2. [D][] DEADLINE_NAME (by: DEADLINE_BY)
3. [E][] EVENT_NAME (from: EVENT_FROM to: EVENT_TO)
```

### `find` - Lists all tasks in the list with a keyword

Format: ```find KEYWORD```

Examples
* ```find homeword```
* ```find math homeword```

Expected output:

```
Here are the matching tasks in your list:
1. [T][] TODO_NAME
2. [D][] DEADLINE_NAME (by: DEADLINE_BY)
3. [E][] EVENT_NAME (from: EVENT_FROM to: EVENT_TO)
```

### `stats` - Lists key statistics regarding the list

Format: ```stats```

Examples
* ```stats```

Expected output:

```
Tasks completed in the past 1 day is: 0
Tasks completed in the past 3 day is: 2
Tasks completed in the past 4 day is: 5
```

### `bye` - Exits the application

Format: ```bye```

Examples
* ```bye```

Expected output:
No output, user interface closes.
