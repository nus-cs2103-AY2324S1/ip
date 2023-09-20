# Alyssa User Guide

## Features 

### Adding of tasks

- Alyssa supports the addition of tasks. There are 3 different types of tasks: Deadlines, Todos and Events.
  - Todos are tasks without dates associated
  - Deadlines are tasks which are done by a certain date and time
  - Events are tasks which start from a certain date and time, and end at a certain date and time

### Marking and unmarking of tasks

- You can mark a task as done, or unmark a task to undo it. By default, newly created tasks are undone (unmarked).

### Listing of tasks
- You can see all your current tasks, whether or not they're marked, and the task type (Deadline, Event or Todo).

### Deleting of tasks
- Delete tasks that you don't need in your tasklist anymore.

### Finding of tasks
- Search for tasks with a certain keyword in their description.

### Help
- Any unrecognised command or the command `help` shows a message with all commands and their syntax.

## Usage

### `todo [description]` 

Adds a todo with the specified description to the task list.

_Example of usage:_

`todo CS2100 tutorial`

_Expected outcome:_

Adds a Todo to the task list. The Todo is for cs2100 tutorial.

_Description of the outcome:_

```
Got it. I've added this task:
[T][] cs2100 tutorial
Now you have 1 tasks in the list.
```

### `deadline [description] /by [YYYY-MM-DD]` 

Adds a deadline with the specified description and due date to the task list.

_Example of usage:_

`deadline cs2100 tutorial /by 2023-10-05`

_Expected outcome:_

Adds a Deadline to the task list. The Deadline is for cs2100 tutorial, due on 5th October.

_Description of the outcome:_

```
Got it. I've added this task:
[D][] cs2100 tutorial (by: Oct 05 2023)
Now you have 2 tasks in the list.
```

### `event [description] /from [when] /to [when]` 

Adds an event with the specified description, start time and end time to the list. The [when] field 
only needs to be a string, with no required format.

_Example of usage:_

`event cs2100 tutorial /from 10am /to 12pm`

_Expected outcome:_

Adds an Event to the task list. The event is cs2100 tutorial, starting at 10am and ending at 12pm.

_Description of the outcome:_

```
Got it. I've added this task:
[E][] cs2100 tutorial (from: 10am to: 12pm)
Now you have 3 tasks in the list.
```

### `mark [index]`

Marks a task at the specified index as done. Indexes start from 1.

_Example of usage:_

`mark 1`

_Expected outcome:_

Marks the task at index 1.

_Description of the outcome:_

```
Nice! I've marked this task as done:
[T][X] cs2100 tutorial
```

### `unmark [index]`

Unmarks a task at the specified index. Indexes start from 1.

_Example of usage:_

`unmark 1`

_Expected outcome:_

Unmarks the task at index 1.

_Description of the outcome:_

```
OK, I've marked this task as not done yet:
[T][] cs2100 tutorial
```

### `list`

Lists all existing tasks, with their associated index.

_Example of usage:_

`list`

_Expected outcome:_

Lists existing tasks.

_Description of the outcome:_

```
Here are the tasks in your list:
1.[T][] cs2100 tutorial
2.[D][] cs2100 tutorial (by: Oct 05 2023)
3.[E][] cs2100 tutorial (from: 10am to: 12pm)
```

### `delete [index]`

Delete the task at the given index.

_Example of usage:_

`delete 1`

_Expected outcome:_

Deletes the first task in the list.

_Description of the outcome:_

```
Noted I've removed this task:
1.[T][] cs2100 tutorial
Now you have 2 tasks in the list.
```

### `find [keywords]`

Finds tasks with the specified keyword(s).

_Example of usage:_

`find tut`

_Expected outcome:_

Finds tasks with description containing the keyword `tut`.

_Description of the outcome:_

```
Here are the matching tasks in your list:
1.[D][] cs2100 tutorial (by: Oct 05 2023)
2.[E][] cs2100 tutorial (from: 10am to: 12pm)
```

### `bye`

Prevents the program from receiving further inputs. When the user types anything in after entering this command, the GUI closes.

_Example of usage:_

`bye`

_Expected outcome:_

Informs user that subsequent messages will close the program.

_Description of the outcome:_

```
Bye. Hope to see you again soon! Subsequent messages entered will cause the program to close.
```

### `help`

Prints a list of commands and their syntax.

_Example of usage:_

`help`

_Expected outcome:_

Prints a list of commands and their syntax.

_Description of the outcome:_

```
Commands:
Add todo: todo {description}
Add deadline: deadline {description} /by {YYYY-MM-DD}
Add event: event {description} /from {date/time} /to {date/time}
List tasks: list
Mark task as done: mark {task number}
Unmark task: unmark {task number}
Delete task: delete {task number}
Find task: find {keyword}
Close program: bye
Help Page: help
```