# User Guide for Spot

Spot is a chatbot puppy who manages your tasks. If you live a busy life, you can be sure that Spot will become your very best friend! Bau bau!

## Features

### Adding a ToDo: `todo`

Adds a ToDo to your list of tasks. A ToDo is a task that must be done with no fixed deadline or start/end date.

Format:
`todo [task description]`

Example: 
`todo buy cheese`

### Adding a Deadline: `deadline`

Adds a Deadline to your list of tasks. A Deadline is a task that must be done before a certain date.
- The date given for the deadline must follow the format: dd-mm-yyyy

Format:
`deadline [task description] /by [task deadline]`

Example:
`deadline submit cs2100 assignment 1 /by 18-09-2023`

### Adding an Event: `event`

Adds an Event to your list of tasks. An Event is a task with a set start and end date.
- The dates given for the start and end dates must follow the format: dd-mm-yyyy

Format:
`event [task description] /from [start date] /to [end date]`

Example:
`event NUS LifeHack /from 22-05-2023 /to 01-06-2023`

### Listing all tasks: `list`

Shows a list of all tasks.
- Each line will begin with a [T], [D] or [E], which indicates if the task is a ToDo, Deadline or Event.
- This [T], [D] or [E] is then followed by either a [ ] or [X], which indicates the task's completion status.
- For instance, [T] [X] indicates that the task is a ToDo which has been completed.

Format:
`list`

### Listing tasks on a certain date: `list tasks on`

Shows a list of all tasks that fall on a certain date.
- The date used in the command must follow the format: dd-mm-yyyy
- Each line will begin with a [T], [D] or [E], which indicates if the task is a ToDo, Deadline or Event.
- This [T], [D] or [E] is then followed by either a [ ] or [X], which indicates the task's completion status.
- For instance, [T] [X] indicates that the task is a ToDo which has been completed.

Format:
`list tasks on [date]`

Example:
`list tasks on 07-10-2023`

### Finding tasks by keyword: `find`

Shows a list of all tasks with descriptions that match a given keyword.
- Each line will begin with a [T], [D] or [E], which indicates if the task is a ToDo, Deadline or Event.
- This [T], [D] or [E] is then followed by either a [ ] or [X], which indicates the task's completion status.
- For instance, [T] [X] indicates that the task is a ToDo which has been completed.

Format:
`find [keyword]`

Example:
`find NUS`

### Updating a task: `update`

Updates a task in the list.
- The task number refers to the task number shown in the list displayed when the `list` command is used.
- Any date used in the command must follow the format: dd-mm-yyyy

Format:
`update [task number] /[field to update] [updated value]`

Examples:
- `update 3 /description return book`
- `update 2 /deadline 05-11-2023`
- `update 5 /start 20-01-2024`
- `update 1 /end 22-09-2023`

### Marking a task as done: `mark`

Marks a task in the list as done. 
- The task number refers to the task number shown in the list displayed when the `list` command is used.

Format:
`mark [task number]`

Example:
`mark 1`

### Marking a task as not done: `unmark`

Marks a task in the list as not done. 
- The task number refers to the task number shown in the list displayed when the `list` command is used.

Format:
`unmark [task number]`

Example:
`unmark 1`

### Deleting a task: `delete`

Deletes a task from the list. 
- The task number refers to the task number shown in the list displayed when the `list` command is used.

Format:
`delete [task number]`

Example:
`delete 1`

### Exiting the program: `bye`

Exits the program.

Format:
`bye`

### Saving the task list

Upon receiving the `bye` command, Spot will automatically save all tasks to the hard disk! There is no need for you to save the tasks manually.