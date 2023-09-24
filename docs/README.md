# User Guide
![Ui.png](Ui.png)

EchoBot is a desktop task manager.
Simply type in the commands and relevant details,
and you can easily use all the features of this app.

Notes about the command format:
- Some commands require parameters. The user guide will show parameters in `< >`.
  The user can freely specify their own parameters.
    * e.g. `todo <description>` means the command `todo` require a parameter `<description>`
- Commands that exists on its own (such as `list`, `bye` and `sort`) does not accept parameters.
    * e.g. `list 123` is not an acceptable format.
- Commands are case-insensitive
    * i.e. `TODO do homework` and `todo do homework` means the same thing
    * However, you should note that the descriptions will follow whatever you fill in, i.e.
      `todo DO HomeWork` will create a Todo task with description `DO HomeWork`

## Types of Tasks
With EchoBot, you can add 3 different types of tasks
1. **Todo** : tasks with just a description.
2. **Deadline** : tasks with a description and a due date.
3. **Event** : tasks with a description, a start date and an end date.

## Features
EchoBot allows the following commands:
1. Adding a task
2. Listing all tasks
3. Marking a task as done
4. Marking a task as not done
5. Deleting a task
6. Finding tasks by keyword
7. Undoing the latest change to the list of tasks
8. Exiting the program

## Usage

### Adding a ToDo: `todo <description>`

Adds a todo task.

Example of format:

`todo do homework`

Expected outcome:

```
You have added a task:
    [T][ ] do homework
There are now 1 tasks in the list
```

### Adding a Deadline: `deadline <description> /by <due_date>`

Adds a deadline task.
> Note: The only format supported for `<due_date>` is YYYY-MM-DD (e.g. 2001-01-01)

Example of format:

`deadline submit homework /by 2023-09-09`

Expected outcome:

```
You have added a task:
    [D][ ] submit homework
    (by: Sep 09 2023)
There are now 2 tasks in the list
```

### Adding an Event: `event <description> /from <start_date> /to <end_date>`

Adds an event task.
> Note: The only format supported for `<from_date>` and `<to_date> is YYYY-MM-DD
  (same as deadline tasks date format) 

Example of usage:

`event summer camp /from 2023-05-25 /to 2023-06-05`

Expected outcome:

```
You have added a task:
    [E][ ] summer camp
    (from: May 25 2023 to: Jun 05 2023)
There are now 3 tasks in the list
```

### Listing all tasks: `list`

Shows the list of tasks containing all tasks that you have added.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] do homework
2. [D][ ] submit homework
    (by: Sep 09 2023)
3. [E][ ] summer camp
    (from: May 25 2023 to: Jun 05 2023)
```

### Marking task as done: `mark <index>`

Marks the task with the specified index as done.

Example of usage:

`mark 1`

Expected outcome:

```
You have marked this task as done
    [T][X] do homework
```

### Marking task as not done: `unmark <index>`

Remove the done mark on the task with the specified index.

Example of usage:

`unmark 1`

Expected outcome:

```
You have marked this task as not done
    [T][ ] do homework
```

### Finding tasks by keyword: `find <keyword>`

Shows all tasks in the task list that contains the specified keyword.
> Note: the find feature checks for instances in the task description only.
Searching for words or phrases are recommended.

Example of usage:

`find homework`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][ ] do homework
2. [D][ ] submit homework
    (by: Sep 09 2023)
```

### Deleting a task: `delete <index>`

Deletes the task at the specified index.

Example of usage:

`delete 1`

Expected outcome:

```
You have deleted a task:
    [T][ ] do homework
There are now 2 tasks in the list
```

### Undoing the latest change to the task list: `undo`
> Note: You cannot undo an undo command
nor undo directly after starting the app

Undoes the most recent calls to the following commands:
- add tasks commands (todo, deadline, event)
- mark task
- unmark task
- delete task

Example of usage:

`undo`

Expected outcome:

```
You have undone the latest change to the task list
This is your list now:
1. [T][ ] do homework
2. [D][ ] submit homework
    (by: Sep 09 2023)
3. [E][ ] summer camp
    (from: May 25 2023 to: Jun 05 2023)
```

### Exiting the program: `bye`

Exits the program.

Example of usage:

`bye`

Expected outcome:

The program will close.