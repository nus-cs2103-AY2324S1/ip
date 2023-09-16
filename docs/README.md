# User Guide
Alpha is a text-based to do list chat bot.

* [Features](#features)
  * [Adding to do task](#adding-to-do-task-todo)
  * [Adding deadline task](#adding-deadline-task-deadline)
  * [Adding event task](#adding-event-task-event)
  * [Listing all tasks](#listing-all-tasks-list)
  * [Deleting task](#deleting-task-delete)
  * [Marking task](#marking-task-mark)
  * [Unmarking task](#unmarking-task-unmark)
  * [Finding task](#finding-task-find)
  * [Exiting the program](#exiting-the-program-bye)
  * [Saving the data](#saving-the-data)


## Features

### Adding to do task: `todo`
Add to do task into the list of tasks. Name of the task must be unique.

Format: `todo [task name]`

Examples:
* `todo read book`
* `todo study for midterms`

### Adding deadline task: `deadline`
Add deadline task to list of tasks. Name of the task must be unique.

Format: `deadline [task name] /by [yyyy-MM-dd HH:mm]`

Examples:
* `deadline read book /by 2023-10-10 23:59`
* `deadline study for midterms /by 2023-11-06 08:00`

### Adding event task: `event`
Add event task to list of tasks. Name of the task must be unique.

Format: `event [task name] /from [yyyy-MM-dd HH:mm] /to [yyyy-MM-dd HH:mm]`

Examples:
* `event read book /from 2023-10-10 20:00 /to 2023-10-11 00:00`
* `event midterms /from 2023-06-03 13:00 /to 2023-06-03 14:00`

### Listing all tasks: `list`
Shows the list of all tasks.

Format: `list`

### Deleting task: `delete`
Deletes the specified task.

Format: `delete [index of task]`

Examples:
* `delete 1`
* `delete 2`

### Marking task: `mark`
Checks the specified task as completed.

Format: `mark [index of task]`

Examples:
* `mark 1`
* `mark 2`

### Unmarking task: `unmark`
Checks the specified task as uncompleted.

Format: `unmark [index of task]`

Examples:
* `unmark 1`
* `unmark 2`

### Finding task: `find`
Shows the specific task with task name that contains the given input.

Format `find [search string]`

Examples:
* `find read book`
* `find midterms`

### Exiting the program: `bye`
Saves task list into local storage and exits the program.

Format `bye`

### Saving the data
Tasks will only be saved upon exiting through the `bye` command.

## FAQ
**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and transfer the save file over. 
The save file should be located under data/save.txt