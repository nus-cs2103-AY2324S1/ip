# User Guide
![Ui.png](Ui.png)

_Beep Boop Bot_ is a **desktop app for managing your tasks**, 
**optimized for use via a Command Line Interface** (CLI) while still
having the benefits of a Graphical User Interface (GUI).

_Beep Boop Bot_ helps keep track of your tasks so that you don't have to!

## Features 

ℹ️ Notes about the command format:
- Words in `< >` are the **parameters** to be supplied by the user.
  * e.g, in `deadline <description> by/ <due_date>`, `<description>` and `<due_date>` is 
  are paramters which can be used as `deadline return book by/ 2023-12-12`.
- Commands are **case-sensitive**.
  * e.g. `todo quiz` is acceptable, but `TODO quiz` and `Todo quiz` are 
  unacceptable command formats.
- Extraneous parameters for commands that do not take in parameters
(such as `list`, `bye` and `sort`) are **unacceptable**.
  * e.g. `list 123` is not an acceptable format.

### Types of Tasks
_Beep Boop Bot_ supports 3 different types of tasks (ToDos, Deadlines and Events).
1. **ToDos** : basic tasks with just a description.
2. **Deadlines** : tasks with a hard due date.
3. **Events** : tasks with a specific period of time.

## Usage

### Adding a ToDo: `todo <description>`

Adds a todo to the task list.

Example of usage: 

`todo quiz`

Expected outcome:

```
Got it. I've added this task:
[T] [ ] quiz
Now you have 1 task(s) in the list.
```

### Adding an Event: `event <description> /from <from_date> /to <to_date>`

Adds an event to the task list.
> Note: The only format supported for `<from_date>` and `<to_date> is YYYY-MM-DD (e.g. 2023-12-12)

Example of usage:

`event reading week /from 2023-09-25 /to 2023-09-29`

Expected outcome:

```
Got it. I've added this task:
[E] [ ] reading week (from: Sep 25 2023 to: Sep 29 2023)
Now you have 2 task(s) in the list.
```

### Adding a Deadline: `deadline <description> /by <due_date>`

Adds a deadline to the task list.
> Note: The only format supported for `<due_date>` is YYYY-MM-DD (e.g. 2023-12-12)

Example of usage:

`deadline return book /by 2023-12-12`

Expected outcome:

```
Got it. I've added this task:
[D] [ ] return book (by: Dec 12 2023)
Now you have 3 task(s) in the list.
```

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T] [ ] quiz
2. [E] [ ] reading week (from: Sep 25 2023 to: Sep 29 2023)
3. [D] [ ] return book (by: Dec 12 2023)
```

### Marking task as done: `mark <index>`

Marks the tasks at the specified index as done.

Example of usage:

`mark 1`

Expected outcome:

```
Beep Boop NICE! I've marked this task as done:
[T] [X] quiz
```

### Marking task as not done: `unmark <index>`

Marks the tasks at the specified index as not done.

Example of usage:

`unmark 1`

Expected outcome:

```
Beep Boop NICE! I've unmarked this task as done:
[T] [ ] quiz
```

### Deleting a task: `delete <index>`

Deletes the tasks at the specified index.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T] [X] quiz
Now you have 2 task(s) in the list.
```

### Finding tasks by keyword: `find <keyword>`

Finds tasks in the task list that match or contain the keyword.

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D] [ ] return book (by: Dec 12 2023)
```

### Sorting tasks in the task list: `sort`

Sorts the tasks in the task list in order of their types (Tasks, Deadlines, Events).

Example of usage:

`sort`

Expected outcome:

```
Here are the tasks in your list sorted by category:
1. [D] [ ] return book (by: Dec 12 2023)
2. [E] [ ] reading week (from: Sep 25 2023 to: Sep 29 2023)
```

### Exiting the program: `bye`

Exits the program.

Example of usage:

`bye`

Expected outcome:

```
Bye Bye! Hope to see you again soon! Beep Boop!
```