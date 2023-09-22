# User Guide for Pippi
Pippi is a chatbot desktop application for tasks management via a Command Line Interface. 

## Features 
> **NOTE:**
> Words in `UPPER_CASE` are the parameters to be supplied by the user.
>
> e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo chores`
### Viewing help : `help`

View the list of commands available

**Format:** `help`

### Adding a todo task: `todo`
Add a todo to the list of tasks.

**Format:** `todo TASK_DESCRIPTION`

**Examples:**
`todo drink water`


### Adding a deadline : `deadline`

Add a deadline to the list of tasks.

**Format:** `deadline TASK_DESCRIPTION /by DATE`
* `DATE` must be in **yyyy-mm-dd** format

**Examples:**
* `deadline submit assignment /by 2023-09-23 2359`



### Adding an event : `event`
Add an event to your list of tasks.
**Format:** `event TASK_DESCRIPTION /from START /to END`

**Example:**
* `event Party /from 9am /to 9pm`

### Listing all tasks : `list`
Shows a list of all the current tasks.

**Format:** `list`

### Finding a task : `find`
Finds a task which contains the keyword.

**Format:** `find KEYWORD`

**Examples:**
* `find party`

### Deleting a task : `delete`
Deletes a task from the list.

**Format:** `delete TASK_INDEX`
* Deletes the task at the specified `TASK_INDEX`. The `TASK_INDEX` is the index number shown in the displayed list after entering the `list` command.
> * `TASK_INDEX` **must be a positive integer** 1, 2, 3...
> * Note that the index of the remaining tasks might change after each deletion.

**Examples:**
* `delete 2`

### Marking a task as complete : `mark`
Marks a task in the list as complete.

**Format:** `mark TASK_INDEX`
* Marks the task at the specified `TASK_INDEX`. The `TASK_INDEX` is the index number shown in the displayed list after entering the `list` command.
> * `TASK_INDEX` **must be a positive integer** 1, 2, 3...

**Examples:**
* `mark 1`

### Marking a task as incomplete : `unmark`
Marks a task in the list as incomplete.

**Format:** `unmark TASK_INDEX`
* Marks the task at the specified `TASK_INDEX`. The `TASK_INDEX`  corresponds to the index number shown in the displayed list.
* `TASK_INDEX` **must be a positive integer** 1, 2, 3...

**Examples:**
* `unmark 1`

