# User Guide

## Features 

### Deadline Task Creation

Enables users to create a Deadline Task, which has a due by date.

### Delete

Enables users to delete a selected Task.

### Event Task Creation

Enables users to create an Event Task, which has a date range.

### Find

Enables users to find a specific task matching the search query.

### List

Enables users to list tasks for easy viewing.

### Marking Status of Tasks

Enables users to mark tasks as done or not done for easy tracking.

### Todo Task Creation

Enables users to create a Todo Task, which has no date.


## Usage

### `bye` - Exits the Application

The `bye` command exits the application at its current state.

Format:

`bye`

Example of usage: 

`bye`

Expected outcome:

The application exits successfully

### `deadline` - Creates a Deadline Task

The `deadline` command creates a deadline task, which includes a due by date.
It must include a non-empty description and a due date.

Format:

`deadline <description> /by <YYYY-MM-DD>`

Example of usage:

`deadline CS2101 OP1 /by 2023-09-22`

Expected outcome:

Successfully creates a Deadline Task and adds it to storage.

```
Got it. I've added this task:
  [D][] CS2101 OP1 (by: Sep 22 2023)
Now you have 5 tasks in the list.
```

### `delete` - Deletes a selected Task

The `delete` command deletes a specified task.
It must include a non-empty numerical choice which corresponds to a valid task. 
Where the task id is the id when using the `list` command.

Format:

`delete <task id>`

Example of usage:

`delete 1`

Expected outcome:

Successfully deletes a selected task and removes it from storage.

```
Noted. I've removed this task:
  [D][] CS2101 OP1 (by: Sep 22 2023)
Now you have 4 tasks in the list.
```

### `event` - Creates an Event Task

The `event` command creates an Event Task, which has a date range.
It must include a non-empty description, a from date and to date.

Format:

`event <description> /from <YYYY-MM-DD> /to <YYYY-MM-DD>`

Example of usage:

`event CS2101 OP1 /from 2023-09-23 /to 2023-09-23`

Expected outcome:

Successfully creates an event task and adds it to storage.

```
Got it. I've added this task:
  [E][] CS2101 OP1 (from: Sep 22 2023 to: Sep 23 2023)
Now you have 5 tasks in the list.
```

### `find` - Finds Tasks matching Search Query

The `find` command searches the list of tasks and returns those, if any, whose description matches the given search query.

Format:

`find <query>`

Example of usage:

`find apple`

Expected outcome:

Returns a list of tasks, if any, whose description matches the given search query.

```
3. [E][] apple pie (from: Sep 22 2023 to: Sep 23 2023)
5. [D][] apple sauce (by: Sep 22 2023)
```

### `list` - List Tasks

The `list` command list the tasks stored in the application. It can operate in the following modes
* List all tasks
* List tasks whose end date is before one week from now
* List all tasks whose end date is before a specified date

Format:
List all tasks
`list`

List tasks whose end date is before one week from now
`list now`

List all tasks whose end date is before a specified date
`list <YYYY-MM-DD>`

Example of usage:

List all tasks
`list`

List tasks whose end date is before one week from now
`list now`

List all tasks whose end date is before a specified date
`list 2023-09-24`

Expected outcome:

Returns a list of tasks, filtered depending on the additional arguments specified.

```
1. [E][] apple pie (from: Sep 22 2023 to: Sep 23 2023)
2. [D][X] apple sauce (by: Sep 22 2023)
```

### `mark` - Marks a specified task as done

The `mark` command marks a specified task as done.
It must include a non-empty numerical choice which corresponds to a valid task.
Where the task id is the id when using the `list` command.

Format:

`mark <task id>`

Example of usage:

`mark 1`

Expected outcome:

Marks a specified task as done.

```
Nice! I've marked this task as done:
  1. [E][X] apple pie (from: Sep 22 2023 to: Sep 23 2023)
```

### `todo` - Creates a todo task.

The `todo` command creates a todo task, which has no date.
It must include a non-empty description

Format:

`mark <description>`

Example of usage:

`todo bake pie`

Expected outcome:

Marks a specified task as done.

```
Got it. I've added this task:
  [T][] bake pie
Now you have 5 tasks in the list.
```

### `unmark` - Marks a specified task as done

The `unmark` command marks a specified task as not done.
It must include a non-empty numerical choice which corresponds to a valid task.
Where the task id is the id when using the `list` command.

Format:

`unmark <task id>`

Example of usage:

`unmark 1`

Expected outcome:

Marks a specified task as not done.

```
OK, I've marked this task as not done yet:
  1. [E][] apple pie (from: Sep 22 2023 to: Sep 23 2023)
```
