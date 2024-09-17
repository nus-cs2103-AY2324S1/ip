# Iris User Guide

Iris is a Personal Assistant Chatbot that helps users to keep track of their day-to-day tasks.

## Features 

### Creating Tasks

You can create a task of type todo, deadline or event.
- Todo: A task with only a description. Used to keep track of tasks that do not have a deadline.
- Deadline: A task with a description and a deadline. Used to keep track of tasks that are to be done by a specified date or time.
- Event: A task with a description and a date. Used to keep track of tasks that happen between a specified date or time.

### Marking Tasks as Done

You can mark a task as done in Iris once you have completed it.

### Listing Tasks

You can list all the tasks that you have created in Iris.

### Finding Tasks

You can find tasks that contain a specified keyword by searching for it in Iris.

### Deleting Tasks

You can delete tasks that you have created in Iris.

### Automatic Saving and Loading of Tasks

All tasks that you have created in Iris will be automatically saved to your computer. When you start Iris, all your tasks will be loaded from your computer.

### Postponing Tasks

You can postpone tasks that you have created in Iris.

## Usage

### `todo` - Adds a todo task

Creates a todo task and adds it to the list of tasks.

Example of usage: 

`todo read book`

Expected outcome:

A new todo task with the description "read book" will be created and added to the list of tasks.

```
Got it. I've added this task:
[T][ ] read book
Now you have 1 task in the list.
```

### `event` - Adds an event task

Creates an event task and adds it to the list of tasks.

Example of usage:

`event project meeting /at 2023-08-25`

Expected outcome:

A new event task with the description "project meeting" and date "25 Aug 2023" will be created and added to the list of tasks.

```
Got it. I've added this task:
[E][ ] project meeting (at: 25 Aug 2023)
Now you have 2 tasks in the list.
```

### `deadline` - Adds a deadline task

Creates a deadline task and adds it to the list of tasks.

Example of usage:

`deadline return book /by 2023-08-25`

Expected outcome:

A new deadline task with the description "return book" and date "25 Aug 2023" will be created and added to the list of tasks.

```
Got it. I've added this task:
[D][ ] return book (by: 25 Aug 2023)
Now you have 3 tasks in the list.
```

### `list` - Lists all tasks

Lists all tasks that you have created in Iris.

Example of usage:

`list`

Expected outcome:

All tasks that you have created in Iris will be listed.

```
Here are the tasks in your list:
1. [T][ ] read book
2. [E][ ] project meeting (at: 25 Aug 2023)
3. [D][ ] return book (by: 25 Aug 2023)
```

### `done` - Marks a task as done

Marks a task as done in Iris.

Example of usage:

`done 1`

Expected outcome:

The first task in the list of tasks will be marked as done.

```
Nice! I've marked this task as done:
[T][X] read book
```

### `find` - Finds tasks that contain a specified keyword

Finds tasks that contain a specified keyword by searching for it in Iris.

Example of usage:

`find book`

Expected outcome:

All tasks that contain the keyword "book" will be listed.

```
Here are the matching tasks in your list:
1. [T][X] read book
2. [D][ ] return book (by: 25 Aug 2023)
```

### `delete` - Deletes a task

Deletes a task that you have created in Iris.

Example of usage:

`delete 1`

Expected outcome:

The first task in the list of tasks will be deleted.

```
Noted. I've removed this task:
[T][X] read book
Now you have 2 tasks in the list.
```

### `postpone` - Postpones a task

Postpones a task that you have created in Iris.

Example of usage:

`postpone 1 2023-08-25`

Expected outcome:

The first task in the list of tasks will be postponed to the specified date.

```
Noted. I've postponed this task:
[E][ ] project meeting (at: 25 Aug 2023)
```

### `bye` - Exits Iris

Exits Iris.

Example of usage:

`bye`

Expected outcome:

Iris will exit.

```
Bye. Hope to see you again soon!
```

