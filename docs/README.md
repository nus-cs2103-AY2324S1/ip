# Chat Buddy User Guide

Chat Buddy is a desktop app to manage tasks, optimized for use via a Command Line Interface (CLI) while still having 
the benefits of a Graphical User Interface (GUI). Chat Buddy manage tasks in a conversation style, making task 
management more interesting. 

## Features 

> [!NOTE]
> Words in `UPPER_CASE` are the parameters to be supplied by the user.
>
> e.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo read book`.

> [!NOTE]
> Items in square brackets are optional.
> 
> e.g. `tag INDEX [TAG]` can be used as `tag 1 urgent` or `tag 1`.

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

### Adding a task: `todo`

Adds a task to the task list.

Format: `todo TASK_DESCRIPTION`

Examples:
- `todo buy book`
- `todo buy groceries`

### Adding a task with a deadline: `deadline`

Adds a task with a deadline to the task list.

Format: `deadline TASK_DESCRIPTION /by DEADLINE`
- Deadlines should be in the format: dd/MM/yyyy

Examples:
- `deadline do homework /by 02/09/2023`
- `deadline return book /by 21/10/2023`

### Adding an event: `event`

Adds an event to the task list.

Format: `event TASK_DESCRIPTION /from START_DATE_AND_TIME /to END_DATE_AND_TIME`
- Date and time should be in the format: dd/MM/yyyy HHmm

Examples:
- `event birthday party /from 03/09/2023 1400 /to 03/09/2023 1700`
- `event meet friends /from 23/09/2023 1030 /to 23/09/2023 1815`

### Deleting a task: `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the full task list.
- The index **must be a positive integer** 1, 2, 3, ...

Example:
- `delete 1`

### Marking a task as done: `mark`

Marks the specified task as done.

Format: `mark INDEX`
- Marks the task at the specified `INDEX` as done.
- The index refers to the index number shown in the full task list.
- The index **must be a positive integer** 1, 2, 3, ...

Example:
- `mark 1`

### Marking a task as not done: `unmark`

Marks the specified task as not done.

Format: `unmark INDEX`
- Marks the task at the specified `INDEX` as not done.
- The index refers to the index number shown in the full task list.
- The index **must be a positive integer** 1, 2, 3, ...

Example:
- `unmark 1`

### Finding a task by keyword: `find`

Finds all tasks which task description contains the keyword.

Format: `find KEYWORD`
- The `KEYWORD` is case-sensitive, e.g. `homework` will not match `Homework`
- Tasks which task description contains part of the `KEYWORD` will be listed e.g. `work` will return `homework`.
- Only the task description is searched.

Assuming the task list contains
1. todo homework
1. todo work

Examples:
- `find work` returns `todo homework` and `todo work`
- `find Homework` does not find any matching tasks

### Adding a tag to a task: `tag`

Adds a tag to the specified task

Format: `tag INDEX [TAG]`
- Changes the tag of the task at the specified `INDEX`.
- The index refers to the index number shown in the full task list.
- The index **must be a positive integer** 1, 2, 3, ...
- Not inputting a `TAG` is equivalent to removing a tag from the task.

Examples:
- `tag 1 urgent` adds the tag `urgent` to task 1, causing task 1 to become `find work #urgent`
- `tag 1` removes task 1's tag, causing task 1 to become `find work`
