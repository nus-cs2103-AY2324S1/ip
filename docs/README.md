# Sunacchi User Guide
Welcome to Sunacchi! Sunacchi is a command-line task 
management application that helps you keep track of 
your tasks. 
You can add, list, mark as done, and delete tasks. 
Sunacchi also supports different types of tasks, 
including Todo, Deadline, and Event.

## Features 

### Feature-List
You can list all your tasks to see what's on your to-do list.

### Feature-Mark
Mark tasks as done when you've completed them.

### Feature-Unmark
Unmark tasks if they are not yet completed.

### Feature-Delete
Delete tasks that you no longer need.

### Feature-Todo
Add a Todo task with an optional duration 
(e.g., "todo Read a book /takes 2 hours").

### Feature-Deadline
Add a Deadline task with an optional date 
(e.g., "deadline Submit report /by 2023-09-30" 
or "deadline Pay bills /by today").

### Feature-Event
Add an Event task with a start and end time 
(e.g., "event Team meeting /from 2023-09-20 14:00 /to 2023-09-20 16:00").

### Feature-Event
Search for tasks that match a specific keyword.

## Usage
To use Sunacchi, you can type various commands to perform actions. 
Here are the available commands and their usage:

### `list` - Lists tasks

Use the list command to display all your tasks.

Example of usage: 

`list`

Expected outcome:

Lists all tasks added into Sunacchi

```
1.[T][X] Task 1
2.[D][ ] Task 2 (by: 2023-09-30)
3.[E][ ] Task 3 (from: 2023-09-20 14:00 to: 2023-09-20 16:00)
```

### `mark` - Marks a task

Use the mark command to mark a task as done.

Example of usage:

`mark 1`

Expected outcome:

marks a tasks as done

```
Nice! I've marked this task as done:
[X] Task 1
```

### `unmark` - Unmarks a task

Use the unmark command to mark a task as undone.

Example of usage:

`unmark 1`

Expected outcome:

marks a tasks as undone

```
OK, I've marked this task as not done yet:
[ ] Task 1
```

### `delete` - Deletes a task

Use the delete command to delete a task.

Example of usage:

`delete 1`

Expected outcome:

deletes a task

```
Noted. I've removed this task:
[ ] Task 1
Now you have 2 tasks in the list.
```

### `todo` - Adds a todo task

Use the todo command to add a Todo task. 
You can also specify the duration with "/takes" 
(e.g., "todo Read a book /takes 2 hours").

Example of usage:

`todo Read a book`

Expected outcome:

Adds a todo task

```
Got it. I've added this task:
[T][ ] Read a book
Now you have 3 tasks in the list.
```

### `deadline` - Adds a deadline task

Use the deadline command to add a Deadline task. 
You can specify the due date as "yyyy-MM-dd" 
or use "today" for the current date 
(e.g., "deadline Submit report /by 2023-09-30" or "deadline Pay bills /by today").

Example of usage:

`deadline Submit report /by 2023-09-30`

Expected outcome:

Adds a Deadline task

```
Got it. I've added this task:
[D][ ] Submit report (by: 2023-09-30)
Now you have 4 tasks in the list.
```

### `event` - Adds a event task

Use the event command to add an Event task 
with a start and end time 
(e.g., "event Team meeting /from 2023-09-20 14:00 /to 2023-09-20 16:00").

Example of usage:

`event Team meeting /from 2023-09-20 14:00 /to 2023-09-20 16:00`

Expected outcome:

Adds a Event task

```
Got it. I've added this task:
[E][ ] Team meeting (from: 2023-09-20T14:00 to: 2023-09-20T16:00)
Now you have 5 tasks in the list.
```

### `search` - Searches for a task

Use the search command to find tasks that contain a specific keyword.

Example of usage:

`search report`

Expected outcome:

returns all matching tasks

```
Here are the matching tasks in your list:
1.[D][ ] Submit report (by: 2023-09-30)
```