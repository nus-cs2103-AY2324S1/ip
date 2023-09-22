# Sisyphus User Guide

Sisyphus is a desktop app for managing tasks optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
>One must imagine Sisyphus happy - Albert Camus

## Features 

>Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Go back home`.

### Adding a ToDo - `todo`

Adds a ToDo in the task list.

Format: `todo DESCRIPTION`

Examples:
* `todo Buy present`
* `todo eat lunch`

### Adding a deadline - `deadline`

Adds a deadline in the task list.

Format: `deadline DESCRIPTION /by DATE`
* `DATE` must be in the form of YYYY-MM-DD to be parsed correctly. e.g. 2023-09-22

Examples:
* `deadline ip /by 2023-09-22`
* `deadline find the meaning of life /by 2028-12-31`

### Adding an event - `event`

Adds an event in the task list.

Format: `event DESCRIPTION /from START_DATE /to END_DATE`
* `START_DATE` & `END_DATE` must be in the form of YYYY-MM-DD to be parsed correctly. e.g. 2023-09-22

  Examples:
* `event recess week /from Week 6 Friday /to Week 7 Monday`
* `event Chinese New Year /from 10th February 2024 /to 11th February 2024` 

### Listing all tasks - `list`

List all the tasks in the task list.

Format: `list`

### Finding a specific task - `find`

Find tasks that contain the keyword provided. 
* The search is case-sensitive. e.g. `Homework` will not match `homework`.
* Non-full words will be matched. e.g. `home` will match `homework`.

Format: `find KEYWORD`

Examples:
* `find Homework`
* `find festival`


### Marking a specific task - `mark`

Mark the specified task from the task list.
* Upon marking, the task will be displayed with a `[x]` to represent the status of the task.

Format: `mark INDEX`
* Marks the task at the specified `INDEX`.
* The index refers to the index in the displayed task list from `list`.
* The index must be a positive integer.

Examples:
* `mark 2` marks the second task in the list from `list`.
* `mark 5` marks the fifth task in the list from `list`.

### Unmarking a specific task - `unmark`

Unmarks the specified task from the task list.
* Upon unmarking, the task will be displayed with a `[ ]` to represent the status of the task.

Format: `unmark INDEX`
* Marks the task at the specified `INDEX`.
* The index refers to the index in the displayed task list from `list`.
* The index must be a positive integer.

Examples:
* `unmark 2` unmarks the second task in the list from `list`.
* `unmark 5` unmarks the fifth task in the list from `list`.
### Deleting a specific task - `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`
* Deletes the task at the specified `INDEX`.
* The index refers to the index in the displayed task list from `list`.
* The index must be a positive integer.

Examples:
* `delete 2` deletes the second task in the list from `list`.
* `delete 5` deletes the fifth task in the list from `list`.

