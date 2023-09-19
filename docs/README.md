# User Guide for Glub
Glub is a desktop app for managing tasks in the form of a chat interface.

## Features
> **NOTE:**
> Words in `UPPER_CASE` are the parameters to be supplied by the user.
>
> e.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo homework`

### Adding a todo : `todo`
Add a todo to your list of tasks.

**Format:** `todo TASK_DESCRIPTION`

**Examples:**
* `todo run`

### Adding a deadline : `deadline`

Add a deadline to your list of tasks.

**Format:** `deadline DEADLINE_DESCRIPTION /by DATE`
* `DATE` must be in **dd-MM-yyyy HHmm** format

**Examples:**
* `deadline individual project /by 22-09-2023 2359`
* `deadline graduate /by 01-01-2026 2359`

### Adding an event : `event`
Add a deadline to your list of tasks.

**Format:** `event EVENT_DESCRIPTION /from START_DATE /to END_DATE`
* `START_DATE` and `END_DATE` must be in **dd-MM-yyyy HHmm** format

**Example:**
* `event orientation camp /from 01-08-2023 0900 /to 06-09-2023 1000`
* `event party /from 11-11-2023 1900 /to 12-11-2023 0300`

### Listing all tasks : `list`
Shows a list of all tasks added.

**Format:** `list`

### Finding a task : `find`
Finds a task which contains the search pattern.

**Format:** `find SEARCH_PATTERN`

**Examples:**
* `find run`
* `find party`

### Deleting a task : `delete`
Deletes a task from the list.

**Format:** `delete TASK_INDEX`
* Deletes the task at the specified `TASK_INDEX`. The `TASK_INDEX` corresponds to the index number shown in the displayed list.
* `TASK_INDEX` **must be a positive integer** 1, 2, 3...

**Examples:**
* `delete 1`
* `delete 2`

### Marking a task as complete : `mark`
Marks a task from the list as complete.

**Format:** `mark TASK_INDEX`
* Marks the task at the specified `TASK_INDEX`. The `TASK_INDEX`  corresponds to the index number shown in the displayed list.
* `TASK_INDEX` **must be a positive integer** 1, 2, 3...

**Examples:**
* `mark 1`
* `mark 2`

### Marking a task as incomplete : `unmark`
Marks a task from the list as incomplete.

**Format:** `unmark TASK_INDEX`
* Marks the task at the specified `TASK_INDEX`. The `TASK_INDEX`  corresponds to the index number shown in the displayed list.
* `TASK_INDEX` **must be a positive integer** 1, 2, 3...

**Examples:**
* `unmark 1`
* `unmark 2`

### Tagging a task : `tag`
Add a tag to a task in the list.

**Format:** `tag TASK_INDEX TAG`
* Tags the task at the specified `TASK_INDEX` with the `TAG` provided.
* `TASK_INDEX` **must be a positive integer** 1, 2, 3...

**Examples:**
* `tag 1 important`
* `tag 2 low priority`

