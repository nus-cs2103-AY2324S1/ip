# Anya Chatbot User Guide

Anya is a versatile chatbot designed to help you manage your tasks and provide you with useful information. 
This user guide will walk you through the features and usage of Anya, 
making your interaction with the chatbot more productive and enjoyable.

## Features

### Task Management
Anya can help you manage your tasks effectively. It supports three types of tasks:
- **Todo**: A simple to-do task.
- **Deadline**: A task with a specific deadline.
- **Event**: A task with a specific start and end time.

### Task Operations
You can perform various operations on your tasks, including:
- Marking tasks as done.
- Unmarking tasks as not done.
- Listing all your tasks.
- Deleting tasks.
- Finding tasks based on keywords.

### Interactive Help
Anya provides helpful information to assist you with using its features.

## Usage

### `todo` - Add a Todo task

Add a new Todo task to your task list.

Example of usage:

`todo Buy groceries`

Expected outcome:

```
I've added this task:
[T][ ] Buy groceries
Now you have 1 tasks in the list.
```

### `deadline` - Add a Deadline task

Add a new Deadline task to your task list with a specified deadline.

Example of usage:

`deadline Submit report /by 2023-12-31T23:59`

Expected outcome:

```
I've added this task:
[D][ ] Submit report (by: Dec 31 2023 23:59)
Now you have 2 tasks in the list.
```

### `event` - Add an Event task

Add a new Event task to your task list with a specified start and end time.

Example of usage:

`event Team meeting /from 2023-09-30T14:00 /to 2023-09-30T15:30`

Expected outcome:

```
I've added this task:
[E][ ] Team meeting (from: Sep 30 2023 14:00 PM to: Sep 30 2023, 15:30)
Now you have 3 tasks in the list.
```


### `list` - List all tasks

Display a list of all your tasks.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ] Buy groceries
2.[D][ ] Submit report (by: Dec 31 2023 23:59)
3.[E][ ] Team meeting (from: Sep 30 2023 14:00 PM to: Sep 30 2023, 15:30)
```


### `mark` - Mark a task as done

Mark a task as done by specifying its task number.

Example of usage:

`mark 1`

Expected outcome:

```
I've marked this task as 'DONE':
[T][X] Buy groceries
```


### `unmark` - Mark a task as not done

Mark a task as not done by specifying its task number.

Example of usage:

`unmark 1`

Expected outcome:

```
I've marked this task as 'NOT DONE':
[T][ ] Buy groceries
```


### `delete` - Delete a task

Delete a task by specifying its task number.

Example of usage:

`delete 1`

Expected outcome:

```
I've deleted this task:
[T][ ] Buy groceries
Now you have 2 tasks in the list.
```


### `find` - Find tasks

Find tasks containing specific keywords.

Example of usage:

`find report`

Expected outcome (if found):

```
Here are the matching tasks in your list:
1.[D][ ] Submit report (by: Dec 31 2023 23:59)
```

Expected outcome (if not found):

```
There are no matching tasks found.
```


### `help` - Get help

Request assistance and guidance from Anya.

Example of usage:

`help`

Expected outcome:

A helpful message with instructions on how to use Anya's features.


### `bye` - Exit the application

Exit Anya, and it will save your tasks for future use.

Example of usage:

`bye`

Expected outcome:

`Bye! Hope to see you again.`

### Now you're ready to make the most of Anya and efficiently manage your tasks. Enjoy using the chatbot!
