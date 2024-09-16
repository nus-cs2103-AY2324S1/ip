# GBot User Guide
GBot is the **ultimate task-tracking chatbot** that you can use. 
Optimised via a Command Line Interface (CLI), plus the aesthetic benefit of a
Graphical User Interface (GUI), GBot helps you keep track of your
tasks diligently.

## Features

#### Asking for a help guide: `help`
#### Listing all existing tasks: `list`
#### Adding a task to do: `todo`
#### Adding a task with a deadline: `deadline`
#### Adding an event to complete: `event`
#### Marking a task as done: `mark`
#### Marking all tasks as done: `markAll`
#### Unmarking a task: `unmark`
#### Unmarking all tasks: `unmarkAll`
#### Deleting a task: `delete`
#### Deleting all existing tasks: `deleteAll`
#### Finding a task: `find`
#### Exiting the program: `bye`

## Usage

### `help` - asking for a help guide from GBot
Format: `help`

Expected outcome: GBot will display a list of commands for the user to view.

```
Here are some commands for me to better assist you with:
1) list (eg. list): display the current tasks you have
2) todo (eg. todo read book): adds a Todo task
...
```

### `list` - listing all existing tasks in the task list
Format: `list`

Expected outcome: GBot will display the list of existing tasks for the user to view.
```
Here are the tasks in your magnificent list:
1. [T][ ] read book
...
```

### `todo` - adding a task to do into the task list
Format: `todo DESCRIPTION`
- `DESCRIPTION` is the task description in String.

Example usage: `todo read book`

Expected outcome: GBot will add the Todo task into the task list and 
display a success message.
```
Yet another task to do, you're amazing. Have added this:
    [T][ ] read book
```

### `deadline` - adding a task with a deadline into the task list
Format: `deadline DESCRIPTION /by DEADLINE`
- `DESCRIPTION` is the task description in String.
- `DEADLINE` is in the format `YYYY-MM-DD`.

Example usage: `deadline return book /by 2023-09-21`

Expected outcome: GBot will add the Deadline task into the task list and 
display a success message.
```
No rush on this but do take note of the deadline. Have added this:
    [D][ ] return book (by: Sep 21 2023)
```

### `event` - adding an event to complete into the task list
Format: `event DESCRIPTION /from FROM_DATE /to TO_DATE`
- `DESCRIPTION` is the task description in String.
- `FROM_DATE` and `TO_DATE` are in the format `YYYY-MM-DD`.

Example usage: `event attend meeting /from 2023-09-21 /to 2023-09-22`

Expected outcome: GBot will add the Event task into the task list and 
display a success message.

```
Events are the norm of the upper echelon. Have added this event:
    [E][ ] attend meeting (from: Sep 21 2023 to: Sep 22 2023)
```

### `mark` - marking an existing task as done
Format: `mark INDEX`
- Marks the task at the specified `INDEX` as done.
- The index refers to the index number of the task in the task list.
- The index **must be a positive integer** 1, 2, 3, ...

Example usage: `list` followed by `mark 1` marks the first task in the list as done.

Expected outcome: GBot will mark the specified task as done and
display a success message.

```
Awesome. I've marked this task as done:
    [T][X] read book
```

### `markAll` - marking all existing tasks in the task list as done
Format: `markAll`

Example usage: `list` followed by `markAll` marks all tasks in the list as done.

Expected outcome: GBot will mark all tasks as done and
display a success message.

```
Simply wonderful. All tasks have been marked as done. You're just too good.
```

### `unmark` - unmarking an existing task in the list
Format: `unmark INDEX`
- Unmarks the task at the specified `INDEX`.
- The index refers to the index number of the task in the task list.
- The index **must be a positive integer** 1, 2, 3, ...

Example usage: `list` followed by `unmark 1` unmarks the first task in the list.

Expected outcome: GBot will unmark the specified task as done and
display a success message.

```
No worries, this probably isn't important. I've unmarked this task:
    [T][ ] read book
```

### `unmarkAll` - unmarking all existing tasks in the task list
Format: `unmarkAll`

Example usage: `list` followed by `unmarkAll` unmarks all tasks in the list.

Expected outcome: GBot will unmark all tasks and display a success message.

```
Of course, a busy person needs their time. All tasks have been unmarked.
```

### `delete` - deleting an existing task from the list
Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index refers to the index number of the task in the task list.
- The index **must be a positive integer** 1, 2, 3, ...

Example usage: `list` followed by `delete 1` deletes the first task in the list.

Expected outcome: GBot will delete the specified task and
display a success message.

```
Just clearing up I see. I got you, I've removed this task:
    [T][ ] read book
You now have 1 task(s) to do.
```

### `deleteAll` - deleting all existing tasks in the list
Format: `deleteAll`

Example usage: `list` followed by `deleteAll` deletes all tasks in the list.

Expected outcome: GBot will delete all tasks and
display a success message.

```
Already done? Amazing. I've removed all existing tasks.
```

### `find` - finding an existing task in the list
Format: `find KEYWORD`
- `KEYWORD` is a keyword to refer to in String.

Example usage: `find read` will return all tasks containing the keyword `read`.

Expected outcome: GBot will list all tasks containing the keyword.

```
Here are the matching tasks in your beautiful list:
1. [T][ ] read book
...
```

### `bye` - exiting the program
Format: `bye`

Expected outcome: GBot will display an ending message and end the program.

```
Thank you for your time G. I'll be of your service at any time.
```