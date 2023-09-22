# User Guide for Pogo

## Introduction

Pogo is a simple, easy-to-use task manager that helps you keep track of your tasks.

## Features 

### Adding Tasks

There are 3 types of tasks that can be added to Pogo.

1. A **todo** task is a basic task with a description.
2. A **deadline** task is a task with a due date.
3. An **event** is a task with a start date and end date.

To get started, see the [Usage](#Usage) section.

### Viewing Tasks

You can view all the tasks in your task list using the `list` command.

Search for a specific task using the `find` command.

### Managing Tasks

- Mark a task as done using the `mark` command.
- Mark a task as not done using the `unmark` command.
- Delete a task using the `delete` command.
- Exit the application using the `bye` command.

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `pogo.jar` from the releases page.
3. Copy the file to the folder you want to use as the home folder for your Pogo.
4. Double-click the `pogo.jar` file to start the app. 
The GUI should appear in a few seconds.

## Usage

### Basic command format

The basic format of a command looks like this:

```shell
[task_type] [task_description] [additional_parameters]
```

For example, the following command adds a todo task with the description `read book`:

```shell
todo read book
```

In the subsequent sections, we'll be going through the different types of commands
supported by Pogo.

### `add`: Add a task

The `add` command allows you to add different types of tasks to your task list, 
such as deadlines, events, and to-dos.

#### Adding a todo task: `todo`

Format: `todo [description]`

Sample usage:

The following command adds a todo task with the description `read book`.

```
todo read book

Got it. I've added this task:
[T][ ] read book
```

#### Adding a deadline task: `deadline`

Format: `deadline [description] /by [due_date]`

Sample usage:

The following command adds a deadline task with the description `return book` 
and due date `2021-09-17`.

```
deadline return book /by 2021-09-17 01:12

Got it.  I've added this task:
[D][ ] return book (by: 17/09/2021 01:12)
```

#### Adding an event task: `event`

Format: `event [description] /from [start_date] /to [end_date]`

Sample usage:

The following command adds an event task with the description `project meeting`
with start date `2021-09-17` and end date `2021-09-17`.

```
event project meeting /from 2021-09-17 12:00 /to 2021-09-18 14:00

Got it. I've added this task:
[E][ ] project meeting (from: 17/09/2021 12:00 to: 18/09/2021 14:00)
```

### `list` - Show all tasks

The `list` command allows you to view all the tasks in your task list.

Format: `list`

Sample usage:

```
list

Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: 17/09/2021 01:12)
3. [E][ ] project meeting (from: 17/09/2021 12:00 to: 18/09/2021 14:00)
```

### `delete` - Delete a task

The `delete` command allows you to delete a task from your task list.

Format: `delete [task_index]`

Sample usage:

```
delete 1

Noted. I've removed this task:
[T][ ] read book
There are 2 tasks left.
```

### `mark` - Mark a task as done

The `mark` command allows you to mark a task as done.

Format: `mark [task_index]`

Sample usage:

```
mark 1

Nice! I've marked this task as done:
[D][X] return book (by: 17/09/2021 01:12)
```

### `unmark` - Mark a task as not done

The `unmark` command allows you to mark a task as not done.

Format: `unmark [task_index]`

Sample usage:

```
unmark 1

Ok, I've marked this task as not done yet.
[D][ ] return book (by: 17/09/2021 01:12)
```


### `find` - Search for a specific task

The `find` command allows you to search for a specific task.

Search is done using partial matching (see sample usage).

Format: `find [keyword]`

Sample usage:

```
find rb

Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: 17/09/2021 01:12)
```

### `bye` - Exit the application

The `bye` command allows you to exit the application.

Format: `bye`

Sample usage:

```
bye

Bye. Hope to see you again soon!
```

