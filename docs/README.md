# User Guide

## Features 

### Feature-add

Add tasks of types `ToDo`, `Deadline`, `Event` to the list of tasks.

### Feature-delete

Deletes a task from the task list.

### Feature-list

Lists out all the tasks in the task list.

### Feature-find

Finds a task from the task list that match the keywords.

### Feature-mark

Marks a task as done.

### Feature-unmark

Unmarks a task as not done.

### Feature-priority

Set the priority of any task in the task list to be of `Priority.LOW`,`Priority.MEDIUM`,`Priority.HIGH`.

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
### `todo` `/priority` - Adds a to do task to the task list with a preferred priority.

Adds the task to do with a priority set by user.

Example of usage:

`todo eat /priority high`

Expected outcome:

The todo task to eat will be added to the task list with a priority of HIGH.

```
Alright! I've added this task:
 [T][][HIGH] eat
Now you have 1 tasks in the list.
```

### `deadline` `/by` - Adds a deadline task to the task list.

Adds the deadline task with a default priority of `Priority.LOW`.

Example of usage:

`todo eat`

Expected outcome:

The todo task to eat will be added to the task list with a default priority of LOW.

```
Alright! I've added this task:
 [T][][LOW] eat
Now you have 1 tasks in the list.
```