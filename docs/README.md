# Duke User Guide

## Features 

### Adding Tasks

- The user can add three types of tasks: 
  1. To do: `todo`
  2. Deadline: `deadline`
  3. Event: `event`

### Viewing Tasks
- See all tasks using `list`
- Find specific tasks using `find`

### Edit Task List
- Delete tasks with `delete`
- Mark tasks with `mark`
- Unmark tasks with `unmark`

### Ask for help
Be informed of available commands with `help`

### Exit program
Close duke with `bye`

---
## Usage

### `todo` - Add a new todo

Use `todo <description>` to add a new todo to your list.

Example of usage:

`todo homework`

Expected outcome:

Duke will show an acknowledgement message, displaying the added todo and number of tasks in your existing list.

```
Got it. I've added this task:
    [T][ ] homework
Now you have 1 task(s) in the list.
```
---
### `Deadline` - Add a new deadline

use `deadline <description> /by <date in dd/MM/yyyy>` to add a new deadline to your list.

Example of usage:

`deadline submit homework /by 15/03/2023`

Expected outcome:

Duke will show an acknowledgement message, displaying the added deadline and number of tasks in your existing list.

```
Got it. I've added this task:
    [D][ ] submit homework (by: Mar 15 2023)
Now you have 2 task(s) in the list.
```
---
### `Event` - Add a new event

Use `event <description> /from <date in dd/MM/yyyy> /to <date in dd/MM/yyyy>` to add a new event to your list.

Example of usage:

`event camp /from 22/05/2023 /to 25/05/2023`

Expected outcome:

Duke will show an acknowledgement message, displaying the added event and number of tasks in your existing list.

```
Got it. I've added this task:
    [E][ ] camp (from: May 22 2023 to: May 25 2023)
Now you have 3 task(s) in the list.
```
---
### `list` - Display all tasks

Use `list` to display all existing tasks in the list.

Example of usage:

`list`

Expected outcome:

Duke will acknowledge the `list` command and display all tasks.

```
Here are the tasks in your list:
1. [T][ ] homework
2. [D][ ] submit homework (by: Mar 15 2023)
3. [E][ ] camp (from: May 22 2023 to: May 25 2023)
```
If there are no existing tasks in the list, Duke will display:
```
You currently have no tasks in your list.
```

---
### `find` - Search for specific tasks

Use `find <keyword>` to filter all tasks, displaying those that contain the given keyword

Example of usage:

`find work`

Expected outcome:

Duke will list all the tasks that have 'work' in the task description.

```
Here are the matching tasks in your list:
1. [T][ ] homework
2. [D][ ] submit homework (by: Mar 15 2023)
```
If there are no matching tasks, Duke will display the message:

```
No matching tasks found.
```
---
### `delete` - Remove task from list

Use `delete <task number>` to remove the task at the given integer position in the list.

Example of usage:

`delete 2`

Expected outcome:

Duke will acknowledge the command, display the deleted task and update the number of tasks in the list.

```
Noted. I've removed this task: 
    [D][ ] submit homework (by: Mar 15 2023)
Now you have 2 task(s) in the list.
```
If the given task does not exist, Duke will display the message: 
```
The given task does not exist.
```

---
### `mark` - Mark a task as done

Use `mark <task number>` to mark the task at the given integer position in the list.

Example of usage:

`mark 1`

Expected outcome:

Duke will acknowledge the command and update its status, displaying the updated version of the task.

```
OK, I've marked this task as done:
    [T][X] homework
```
---
### `unmark` - Unmark a task

Use `unmark <task number>` to set the task at the given integer position as not done yet.

Example of usage:

`unmark 1`

Expected outcome:

Duke will acknowledge the command and update its status, displaying the updated version of the task.

```
OK, I've marked this task as not done yet:
    [T][X] homework
```
---
### `help` - Get the list of available commands

Use `help` to get the list of commands and their uses.

Example of usage:

`help`

Expected outcome:

Duke will acknowledge the command and display the list of commands to use.

```
I heard you needed some help!
Here are your commands:
Viewing your tasks:
    list
    find {keyword}
Adding new tasks:
    todo {task}
    deadline {task} /by {dd/MM/yyyy}
    "event {task} /from {dd/MM/yyyy} /to {dd/MM/yyyy}
Editing your task list:
    mark {task number}
    unmark {task number}
    delete {task number}
Exit the program: bye
```
---
### `bye` - Exit Duke

Use `bye` when you're done using Duke to close the program.

Example of usage:

`bye`

Expected outcome:

Duke will acknowledge the command and close the program shortly after.

```
Bye! Hope to see you again soon
```