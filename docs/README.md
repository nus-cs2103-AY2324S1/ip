# User Guide: Slay!
Slay is a **text-based task management app** for you to keep track of your tasks and manage your time easily.
<br>
## Features 

### Viewing help: `help`
Shows a message explaning the usage of available commands with examples. 
 
_Format_: `help`

<br>

### Adding a task: `add`
Adds a new task into the task list.

There are three types of tasks for you to choose from:
- ToDos: tasks without any date/time attached to it
- Deadlines: tasks that need to be done before a specific date and time
- Events: tasks that start at a specific date and time and ends at a specific date and time

_Format_:
- ToDos: `add todo TASK_DESCRIPTION`
- Deadlines: `add deadline TASK_DESCRIPTION /by YYYY-MM-DD HH:MM`
- Events: `add event TASK_DESCRIPTION /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM`

_Examples_:
- `add todo skateboarding@lakeside`
- `add deadline ip user guide /by 2023-09-21 23:39`
- `add event birthday party /from 2023-10-01 18:00 /to 2023-10-01 22:00`

<br>

### Deleting a task: `delete`
Deletes the specified task from the task list.

_Format_: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the task list.
> [!NOTE]
> The index must be a positive integer 1, 2, 3, …​ and must be in the range of the task list.

_Example_: `delete 1`
- This deletes the first task in the task list.

<br>

### Marking a task as done: `mark`
Marks the specified task in the task list as done.

_Format_: `mark INDEX`

_Example_: `mark 1`
- This marks the first task in the task list as done.

<br>

### Unmarking a task as not done: `unmark`
Unmarks the specified task in the task list as not done.

_Format_: `unmark INDEX`

_Example_: `unmark 1`
- This unmarks the first task in the task list as not done.

<br>

### Listing all tasks: `list`
Show a list of all tasks in the task list.

_Format_: `list`

<br>

### Searching for tasks by description: `find`
Finds tasks which contain the specified keyword in the description.

_Format_: `find KEYWORD`

_Example_: `find cs2103`
- This returns a list of tasks which contain "cs2103" in their description.

<br>

### Tagging a task: `tag`
Tags the specified task with given label.

_Format_: `tag INDEX LABEL`
- Tags the task at the specified `INDEX` with `LABEL`.

_Example_: `tag 1 FUN`
- This tags the first task in the task list with the label "FUN".

<br>

### Exiting the program: `bye`
Exits the program.

_Format_: `bye`
<br>
