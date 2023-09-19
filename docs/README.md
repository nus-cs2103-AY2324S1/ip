# User Guide

## Features 

### 1. Add a To-Do Task

To add a todo-task without a specific time.

### 2. Add a Deadline

To add a task with a deadline.

### 3. Add an Event

To add an event with a specific time frame.

### 4. Mark a task

To mark a task as completed.

### 5. Unmark a task

To unmark a completed task.

### 6. Delete a Task

To remove a task from your list.

### 7. List All Tasks

To view a list of all your tasks

### 8. Help command

If you need a quick reminder of available commands.

### 9.Ending the Session

When you're done managing your tasks.

## Usage

### `Todo` - Add a To-Do Task

To add a to-do task without a specific time.

Example of usage: 

`todo ${Your Task Name}`

Expected outcome:

The to-do task will be added to your task list.

```
Got it. I've added this task:
[T][] YOUR TASK NAME
Now you have 1 task in the list.
```

### `Deadline` - Add a Deadline Task

To add a task with a deadline

Example of usage:

`daeadline ${Your Task Name} /by yyyy-mm-dd `

Expected outcome:

The deadline task will be added to your task list.

```
Got it. I've added this task:
[D][] YOUR TASK NAME (by: Mm dd yyyy)
Now you have 1 task in the list.
```

### `Event` - Add an Event Task

To add an event with a specific time frame.

Example of usage:

`event ${Your Task Name} /from date time /to date time `
`event Group Meeting /from 2023-03-30 2pm /to 2023-03-30 4pm `

Expected outcome:

The deadline task will be added to your task list.

```
Got it. I've added this task:
[E][] YOUR TASK NAME (from: DATE TIME to DATE TIME)
Now you have 1 task in the list.
```
### `Mark` - To Mark a Task

To mark a certain task as done according to the index of the task in the list.

Example of usage:

`mark ${index} `

Expected outcome:

The task on the index will be marked.

```
Nice! I've marked this task as done:
[T][X] YOUR TASK NAME
```

### `Unmark` - To Unmark a Task

To unmark a certain task as not done according to the index of the task in the list.

Example of usage:

`unmark ${index} `

Expected outcome:

The task on the index will be unmarked.

```
Nice! I've marked this task as not done yet:
[T][] YOUR TASK NAME
```

### `Delete` - To Delete a Task

To remove a task from your list.

Example of usage:

`delete ${index} `

Expected outcome:

The task on the index will be deleted.

```
Noted. I've removed this task:
[T][X] YOUR TASK NAME
Now you have 0 task in the list.
```
### `Find` - To Find a Task

To find a task by inputting keyword.

Example of usage:

`find ${keyword} `

Expected outcome:

The task containing the keyword will be displayed.

```
Here are the matching tasks in your list:
1.[T][] CS Quiz 
2.[T][X] ST Quiz
```

### `List` - To Get the List of Task

To obtain a list containing all the tasks you have.

Example of usage:

`list `

Expected outcome:

The list containing all the tasks will be displayed.

```
Here are the tasks in your list:
1.[T][] CS Quiz 
2.[T][X] ST Quiz
```

### `Bye` - To end the session

To end the session.

Example of usage:

`bye `

Expected outcome:

The goodbye from Duke.

```
Bye. Hope to see you again soon!
```