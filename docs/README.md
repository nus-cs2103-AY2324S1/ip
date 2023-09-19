# User Guide for Ding
Ding is your personal desktop app that sometimes gets confused, but always remembers your tasks for you!
It is optimised for use via a Command Line Interface (CLI) while still having a Graphical User Interface (GUI).

## Features 
>**NOTE:**
> Words in `UPPER_CASE` are the parameters which the user inputs.
> 
> e.g. in `delete TASK_INDEX`, `TASK_INDEX` is a parameter which can be used to determine the task to be deleted.
### Adding a ToDo: `todo`
Add a todo to your list of tasks.

**Format:** `todo TASK_DESCRIPTION`
* The `TASK_DESCRIPTION` is the description of the todo task.

**Examples:**
* `todo return book`


### Adding a Deadline: `deadline`
Add a deadline to your list of tasks.

**Format:** `deadline TASK_DESCRIPTION /by END_DATETIME`
* The `TASK_DESCRIPTION` is the description of the deadline task.
* The `END_DATETIME` is the end date and time of the deadline task in the format `YYYY-MM-DD hh:mm`.

**Examples:**
* `deadline shower /by 2023-09-30 22:30`
* `deadline sleep /by 2023-09-19 23:59`


### Adding an Event: `event`
Add an event to your list of tasks.

**Format:** `event TASK_DESCRIPTION /from FROM_DATETIME /to TO_DATETIME`
* The `TASK_DESCRIPTION` is the description of the event task.
* The `FROM_DATETIME` and `TO_DATETIME` is the start and end date and time of the event in the format `YYYY-MM-DD hh:mm`.

**Examples:**
* `event sleep /from 2023-09-19 22:30 /to 2023-09-20 06:15`
* `event workout in the gym /from 2023-09-20 09:00 /to 2023-09-20 10:30`

### Displaying all tasks: `list`
Displays the list of tasks.

**Format:** `list`

**Examples:**
* `list` displays the list of tasks currently stored.


### Marking a task as done: `mark`
Marks a task as done.

**Format:** `mark TASK_INDEX`
* The `TASK_INDEX` is the index of the task in the list of tasks to be marked as done.

**Examples:**
* `mark 2` marks the second task in the task list as done.

### Marking a task as undone: `unmark`
Marks a task as undone.

**Format:** `unmark TASK_INDEX`
* The `TASK_INDEX` is the index of the task in the list of tasks to be marked as undone.

**Examples:**
* `unmark 2` marks the second task in the task list as undone.

### Deleting a task: `delete`
Deletes a task from the task list.

**Format:** `delete TASK_INDEX`
* The `TASK_INDEX` is the index of the task in the list of tasks to be deleted.

**Examples:**
* `delete 2` deletes the second task in the task list.

### Finding a task: `find`
Displays a list of tasks with descriptions containing the given `KEYWORD`.

**Format:** `find KEYWORD`
* The `KEYWORD` is the keyword to search for in the list of tasks.
* The search is case-sensitive. e.g. `find boy` will not display `Boy`.

**Examples:**
* `find boy` displays a list of tasks with descriptions containing the word "boy".

### Rescheduling a task: `reschedule`
Reschedules a task.

**Format:** 
* For deadlines: `reschedule TASK_INDEX NEW_BY_DATETIME`
* For events: `reschedule TASK_INDEX NEW_FROM_DATETIME NEW_TO_DATETIME`


* The `TASK_INDEX` is the index of the task in the list of tasks.
* The `NEW_BY_DATETIME` is the new end date and time of the deadline task in the format `YYYY-MM-DD hh:mm`.
* The `NEW_FROM_DATETIME` and `NEW_TO_DATETIME`  is the start and end date and time of the event in the format `YYYY-MM-DD hh:mm`.


**Examples:**
* `reschedule 2 2023-09-19 12:30` reschedules the second task (deadline) in the task list to the specified time.
* `reschedule 4 2023-09-20 10:00 2023-09-23 15:30` reschedules the fourth task (event) in the task list to the specified time.


### Exiting the program: `bye`
Exits the program.

**Format:** `bye`

**Examples:**
* `bye` exits the program.

