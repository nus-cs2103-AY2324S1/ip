# User Guide
*Your Chatbot* is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, *Your Chatbot* can manage your tasks faster than traditional GUI apps.

## Features
> **NOTE:**
> Words in `UPPER_CASE` are the parameters to be supplied by the user.
>
> e.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo Math assignment`

### Adding a todo : `todo`
Add a todo to your list of tasks.

**Format:** `todo TASK_DESCRIPTION`
**Examples:**
* `todo shopping for groceries`
* `todo sumbit IP`

### Adding a deadline : `deadline`

Add a deadline to your list of tasks.

**Format:** `deadline DEADLINE_DESCRIPTION /by DATE`
* `DATE` must be in **YYYY-MM-DD** format

**Examples:**
* `deadline IP-branch-A-UserGuide /by 2023-09-16`
* `deadline Math assignment /by 2023-12-31`

### Adding an event : `event`
Add a deadline to your list of tasks.

**Format:** `event EVENT_DESCRIPTION /from START_DATE /to END_DATE`
* `START_DATE` and `END_DATE` must be in **YYYY-MM-DD** format

**Example:**
* `event orbital showcase /from 2023-09-10 /to 2023-09-11`
* `event career fair /from 2023-10-12 /to 2023-10-13`

### Listing all tasks : `list`
Shows a list of all tasks added.

**Format:** `list`

### Finding a task : `list`
Finds a task whose description contains the keyword.

**Format:** `find KEYWORD`

**Examples:**
* `find homework`
* `find math project`

### Deleting a task : `delete`
Deletes a task from the list.

**Format:** `delete INDEX`
* Deletes the task at the specified `INDEX`. The `INDEX` refers to the index number shown in the displayed list.
* `INDEX` **must be a positive integer** 1, 2, 3...

**Examples:**
* `delete 1`
* `delete 2`

### Marking a task as complete : `mark`
Marks a task from the list as complete.

**Format:** `mark INDEX`
* Marks the task at the specified `INDEX`. The `INDEX`  refers to the index number shown in the displayed list.
* `INDEX` **must be a positive integer** 1, 2, 3...

**Examples:**
* `mark 1`
* `mark 2`

### Marking a task as incomplete : `unmark`
Marks a task from the list as incomplete.

**Format:** `unmark INDEX`
* Marks the task at the specified `INDEX`. The `INDEX`  refers to the index number shown in the displayed list.
* `INDEX` **must be a positive integer** 1, 2, 3...

**Examples:**
* `unmark 1`
* `unmark 2`

### Updating an existing task : `update`
Update the details of a task from the list.

**Format:** `update INDEX /FIELD DETAILS`
* Updates the task at the specified `INDEX`. The `INDEX`  refers to the index number shown in the displayed list.
* `INDEX` **must be a positive integer** 1, 2, 3...
* `FIELD` is the relevant field to be updated of the task, only 1 field can be updated
  at a time.
* If the task is a `todo`, the appropriate `FIELD` is `/desc`.
* If the task is a `deadline`, the appropriate `FIELD`s are `/desc` or `/by`.
* If the task is an `event`, the appropriate `FIELD`s are `/desc`, `/from` or `/to`.
* `DETAILS` is the new detail to be updated into the field indicated.

**Examples:**
* If task 1 is a deadline: `update 1 /by 2023-10-23`
* If task 2 is an event: `update 2 /to 2023-11-11`
* If task 3 is a todo: `update 3 /desc English homework`
