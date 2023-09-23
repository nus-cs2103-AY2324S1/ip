# Bob User Guide

## Features 

### Add task

Tasks of 3 types:
- Todo (T)
- Deadline (D)
- Event (E)
can be added to your tasklist, and classified by priority

### View tasks

You can display all your tasks, or select based on priority.

### Mark done/ undone

Once a task is complete, you can mark it as done.
Conversely, you can also mark a previous completed task as undone.

### Find

You can find specific tasks based on keyword.

### Delete task

Tasks can be deleted.


## Usage

### `todo` - Add task

Adds task of type Todo into tasklist.
#### Arguments:
- priority - `p/high`, `p/mid`, `p/low`
- task name

Example of usage: 

`todo p/mid read book`

Expected outcome:

```
You have added a new task: 
    [T][ ][mid] read book
Now, you have 1 task!
```
