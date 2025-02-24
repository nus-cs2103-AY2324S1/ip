# Duke User Guide

## Features 

### Record Todo Task 

This feature allows you to record a "todo" task in the GUI task list. A "todo" task is a simple task without a specific date associated with it.

### Record Deadline Task 

This feature allows you to record a "deadline" task in the GUI task list. A "deadline" task is a task that has a specific due date associated with it.

### Record Event Task

This feature allows you to record an "event" task in the GUI task list. An "event" task is a task that has both a starting date and an ending date associated with it.

### Task Marking

You can easily mark tasks as completed or uncompleted within the task list.

### Search task description

You can easily search a task in the task list by using keywords in the description. 

### Short and Vim-like Commands

The GUI task list supports short and Vim-like input commands, allowing you to perform actions quickly and efficiently. For example, you can use commands like ls to list tasks and dd to delete a task.

### Local Storage

The application automatically stores your task list locally when you close the window. When you run the app again, it reloads your task list, ensuring that your tasks are always available.

## Usage

### Record Todo Task 

Record todo task in the task list. 

**Example of usage:** 

`td read book`

**Expected outcome**:

The todo task will be added to the task list. 

```
Got it. I've added this task:
[T][] read book 
Now you have (number of task) tasks in the list. 
```


### Record Event Task 

Record an event task in the task list with a specific start and end date.

**Example of usage:** 

`ev team meeting /from 2023-10-05 14:00 /to 2023-10-05 15:30`

**Expected outcome**:

The event task will be added to the task list with the specified start and end dates.

```
Got it. I've added this event:
[E][] team meeting (from: Oct 5 2023 14:00 to: Oct 5 2023 15:30)
Now you have (number of tasks) tasks in the list.
```


### Record Deadline Task 

Record a deadline task in the task list with a specific due date.

**Example of usage:** 

`ddl project report /by 2023-11-15`

**Expected outcome**:

The deadline task will be added to the task list with the specified due date.

```
[D][] project report (by: 2023-11-15)
Now you have (number of tasks) tasks in the list.
```


### Mark and Unmark Tasks 

You can mark tasks as completed or uncompleted and keep track of your progress.

**Example of usage:** 

To mark a task as done, use the following command:  

`mark 1`

**Expected outcome**:  

The task will be marked as done.

```
Nice! I've marked this task as done:
1. [T][x] read book
```

- To unmark a task as not done yet, use the following command:  

`unmark 1`

**Expected outcome**:

The task will be marked as not done yet.

```
Ok, I've marked this task as not done yet:
1. [T][] read book
```

### Delete Task

You can remove a task from your task list using the dd command.

**Example of usage:** 

`dd (index number, example: 1)`

**Expected outcome**:

The task will be removed from the task list.

```
Noted. I've removed this task:
[T][] read book
Now you have (number of tasks) tasks in the list.
```


### List all Tasks 

You can list all the tasks currently in your task list using the ls command.

**Example of usage:**

`ls`

**Expected outcome:**

All tasks in the task list will be displayed.

```
1. [T][] read book
2. [E][] party (from: Oct 15 2023 19:00 to: Oct 15 2023 23:00)
3. [D][] project due (by: Nov 15 2023)
```

### Search Task

You can search tasks by entering the description keyword. 

**Example of usage:**
`f book`

**Expected outcome:**

All tasks that has description that matched with the keyword will be shown. 

```
Here are the matching tasks in your list:
1. [T][] read book
2. [T][] return book
3. [T][] borrow book
```
