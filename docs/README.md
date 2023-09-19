# User Guide

## Features 

### Feature-add

Add tasks of types `ToDo`, `Deadline`, `Event` to the list of tasks.

### Feature-delete

Deletes a task from the task list.

### Feature-mark

Marks a task as done.

### Feature-unmark

Unmarks a task as not done.

### Feature-priority

Set the priority of any task in the task list to be of `Priority.LOW`,`Priority.MEDIUM`,`Priority.HIGH`.

### Feature-list

Lists out all the tasks in the task list.

### Feature-find

Finds a task from the task list that match the keywords.

## Usage

### `todo` - Adds a to do task to the task list.

Adds the task to do with a default priority of `Priority.LOW`.

Example of usage:

`todo eat`

Expected outcome:

The todo task to eat will be added to the task list with a default priority of LOW.

```
Alright! I've added this task:
 [T][][LOW] eat
Now you have 1 tasks in the list.
```


### `deadline` `/by` - Adds a deadline task to the task list.

Adds the deadline task with a default priority of `Priority.LOW`.

Example of usage:

`deadline eat /by 2023-09-19 10:00`

Expected outcome:

The deadline task to eat will be added to the task list with a default priority of LOW, and deadline date time.

```
Alright! I've added this task:
 [T][][LOW] eat (by: Sep 19 2023 10:00 am)
Now you have 1 tasks in the list.
```

### `event` `/from` `/to` - Adds an event task to the task list.

Adds the event task with a default priority of `Priority.LOW`.

Deadline's by date time must be in the following format:
`yyyy-MM-dd hh:mm`

Example of usage:

`event eat /from 2023-09-19 10:00 /to 2023-09-19 11:00`

Expected outcome:

The event task to eat will be added to the task list with a default priority of LOW, and event from date time 
and event to date time.

```
Alright! I've added this task:
 [E][][LOW] eat (from: Sep 19 2023 10:00 am to: Sep 19 2023 11:00 am)
Now you have 1 tasks in the list.
```

### `event` `/from` `/to` `/priority` - Adds an event task to the task list with a preferred priority.

Adds the event task with a priority set by user.

Event's from and to date time must be in the following format: 
`yyyy-MM-dd hh:mm`

Example of usage:

`event eat /from 2023-09-19 10:00 /to 2023-09-19 11:00 /priority high`

Expected outcome:

The event task to eat will be added to the task list with a priority of HIGH, and event from date time
and event to date time.

```
Alright! I've added this task:
 [E][][HIGH] eat (from: Sep 19 2023 10:00 am to: Sep 19 2023 11:00 am)
Now you have 1 tasks in the list.
```

### ***SET OWN PRIORITY FOR `todo`/`deadline`/`event`***

To set own priority, add a `/priority` at the end of description the preferred priority of:
- low
- high
- medium

Example of usage:

`todo eat /priority high`

Expected outcome:

The todo task to eat will be added to the task list with a priority of HIGH.

```
Alright! I've added this task:
 [T][][HIGH] eat
Now you have 1 tasks in the list.
```

### `delete`- Deletes a task to the task list.

Deletes the task of task number specified by user

Example of usage:

`delete 1`

Expected outcome:

The task of task number 1 in the task list will be deleted.

```
Ok, I've removed this task: [E][][HIGH] eat (from: Sep 19 2023 10:00 am to: Sep 19 2023 11:00 am)
Now you have 3 tasks in the list.
```

### `mark`- Marks the task from task list as done with an X.

Marks the task of task number specified by user as done.

Example of usage:

`mark 1`

Expected outcome:

The task of task number 1 in the task list will be marked as done.

```
Nice! I've marked this task as done: [T][X][HIGH] cry
```

### `unmark`- Unmarks the task from task list as not done.

Unmarks the task of task number specified by user as not done.

Example of usage:

`unmark 1`

Expected outcome:

The task of task number 1 in the task list will be unmarked as not done.

```
Nice! I've unmarked this task as not done yet: [T][X][HIGH] cry
```

### `priority`- Sets the task's priority from task list to preferred priority.

Sets the task of task number specified by user as priority of:
- low
- medium
- high

Example of usage:

`priority 1 high`

Expected outcome:

The task of task number 1 in the task list will be have a priority of HIGH.

```
Nice! I've set the priority of this task: [T][X][HIGH] cry
```

### `find`- Finds the task(s) from task list that match user's input.

Finds the task(s) that match the description specified by user as long as description contains user's inputs.

Example of usage:

`find submit`

Expected outcome:

The tasks with submit in its description will be listed out for user.

```
Here are the matching tasks in your list:
 1.[T][][LOW] submit tutorial 
 2.[D][][HIGH] submit iP (by: Sep 22 2023 11:59 pm)
```

### `list`- Lists all the task(s) from task list.

Lists the task(s) in the task list in the order that they were added.

Example of usage:

`list`

Expected outcome:

The list of tasks will be showed to user.

```
Here are the tasks in your list:
 1.[T][][LOW] submit tutorial 
 2.[D][][HIGH] submit iP (by: Sep 22 2023 11:59 pm)
 3.[D][X][MEDIUM] sleep (by Sep 19 2023 11:30 pm)
```