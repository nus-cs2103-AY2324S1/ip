# ChadBob User Guide
ChadBob is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ChadBob can 
help you manage your tasks efficiently and save you time! Start using it and you will be a chad in no time!

## Features 

### Adding task: `todo`,`deadline`,`event`

Adds a type of task into task list.  

Format:  
- `todo TODO_NAME`,  

- `deadline DEADLINE_NAME /by BY_DATE`,  

- `event EVENT_NAME /from FROM /to TO`  

Examples:
- `todo Wash dishes`
- `deadline CS2100 Tutorial /by 17/09/2023`
- `event Zoom Meeting /from 17/09/2023 1800 /to 17/09/2023 2000`


### Updating a task: `update`

Updates a task based on its index, attribute with given content.  

Attributes of task types:
- ToDo: description
- Deadline: description, by
- Event: description, from, to

Format: `update TASK_INDEX TASK_ATTRIBUTE CONTENT`  

Examples:
- `update 1 description Buy groceries`
- `update 2 from 10/10/2023` 

### Finding a task: `find`

Finds a task based on given input

Format: `find KEYWORD_TO_FIND`

Examples:`find Submit` finds all the task with 'Submit' in its description

### Listing all the tasks: `list`

Lists all tasks stored in task list

Format: `list`

### Deleting a task: `delete`

Deletes a task based on task index

Format: `delete TASK_INDEX`

Examples:`list` followed by`delete 1` will delete the first task in the task list.

### Marking a task as done: `mark`

Marks a task as done based on task index

Format: `mark TASK_INDEX`

Examples:`list` followed by `mark 1` marks the first task as done in the task list.

### Marking a task as undone: `unmark`

Marks a task as undone based on task index

Format: `unmark TASK_INDEX`

Examples:`list` followed by `unmark 1` marks the first task as undone in the task list.


### Exiting the program: `bye`

Exits the program

Format: `bye`


## Usage

### `todo` - Add ToDo task

Adds a ToDo task into task list.

Example of usage: 

`todo Wash Dishes`

Expected outcome:

Task list has ToDo task Wash Dishes.  
Task is labelled as T to represent ToDo task type.

```
Got it. I've added this task:
[T][] Wash Dishes
Now you have 1 task in the list.
```

### `deadline` - Add Deadline task

Adds a Deadline task into task list.

Example of usage:

`deadline CS2100 Tutorial /by 17/09/23`

Expected outcome:

Task list has CS2100 Tutorial and its deadline .  
Task is labelled as D to represent Deadline task type.

```
Got it. I've added this task:
[D][] CS2100 Tutorial (by: Sep 17 2023)
Now you have 1 task in the list.
```

### `event` - Add Event task

Adds an Event task into task list.

Example of usage:

`event Zoom Meeting /from 17/09/2023 1800 /to 17/09/2023 2000`

Expected outcome:

Task list has Zoom Meeting and its duration.  
Task is labelled as E to represent Event task type.

```
Got it. I've added this task:
[E][] Zoom Meeting (from: Sep 17 2023 1800 to: Sep 17 2023 2000)
Now you have 1 task in the list.
```
### `mark` - Mark a task as done

Marks a task based on index in task list as done.

Example of usage:

`mark 2`

Expected outcome:

Second task in task list is marked.  
The task is marked as done with an 'X' in the second box.

```
Nice! I've marked this task as done:
[T][X] Task 2
```

### `unmark` - Mark a task as undone

Marks a task based on index in task list as undone.

Example of usage:

`unmark 2`

Expected outcome:

Second task in task list is unmarked.  
The task is marked as undone by leaving the second box empty.

```
OK, I've marked this task as not done yet:
[T][] Task 2
```

### `delete` - Delete a task 

Deletes a task based on index in task list.

Example of usage:

`delete 1`

Expected outcome:

First task in task list is deleted.  
Task list informs remaining number of tasks.

```
Noted. I've removed this task:
[T][] Task 1
Now you have 0 task left in the list.
```

### `update` - Update a task

Updates a task based on index in task list.

Example of usage:

`update 2 description Wash dishes`

Expected outcome:

Second task in task list is updated.  
Updated task list is shown.

```
Update successful! This is the updated task list:
1.[T][] Task 1
2.[T][] Wash dishes
```

### `find` - Find tasks

Finds all tasks based on given string.

Example of usage:

`find Help`

Expected outcome:

All tasks with 'Help' in its description is returned back.

```
Here are the matching tasks in your list:
1.[T][] Help wash dishes
2.[T][] Help fold clothes
```
### `list` - List all tasks

List all tasks in task list.

Example of usage:

`list`

Expected outcome:

All tasks in the task list are listed out.

```
Here are the tasks in your list:
1.[T][] Task 1
2.[T][X] Task 2
3.[T][] Task 3
```

### `bye` - Exit program

Exits the program.

Example of usage:

`bye`

Expected outcome:

Program exits after 1 second.

```
Bye. Hope to see you again!
```