# User Guide for Duke Prince
Duke Prince is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke Prince can manage your tasks faster than traditional GUI apps.

## Features
> **NOTE:**
> Words in `UPPER_CASE` are the parameters to be supplied by the user.
>
> e.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo Math assignment`

### Adding a todo : `todo`
Add a todo to your list of tasks.

**Format:** `todo TASK_DESCRIPTION`
**Examples:**
* `todo run 2.4km`
* `todo sumbit IP`

### Adding a deadline : `deadline`

Add a deadline to your list of tasks.

**Format:** `deadline DEADLINE_DESCRIPTION /by DATE`
* `DATE` must be in **DD-MM-YYYY HHMM** format

**Examples:**
* `deadline CS2100 assignment /by 16-09-2023 1800`
* `deadline CS2103T assignment /by 31-12-2023 2359`

### Adding an event : `event`
Add a deadline to your list of tasks.

**Format:** `event EVENT_DESCRIPTION /from START_DATE /to END_DATE`
* `START_DATE` and `END_DATE` must be in **DD-MM-YYYY HHMM** format

**Example:**
* `event RUNNUS /from 10-09-2023 0600 /to 11-09-2023 1000`
* `event sundown party /from 12-10-2023 1900 /to 13-10-2023 2100`

### Listing all tasks : `list`
Shows a list of all tasks added.

**Format:** `list`

### Finding a task : `find`
Finds a task whose description contains the keyword.

**Format:** `find KEYWORD`

**Examples:**
* `find food`
* `find IS project`

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

### Showing statistics of tasks : `stats`
Shows statistics of the tasks in the task list

**Format:** `stats`

### Saying goodbye : `bye`
Displays a farewell message

**Format:** `bye`
