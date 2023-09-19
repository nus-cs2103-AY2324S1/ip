# Nuvo User Guide

Nuvo is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Nuvo is for you. Take control of your tasks and manage them all in one place.

## Features 

### Adding a Todo task: `todo`

The todo feature allows you to add tasks to your list.

Format: `todo [DESCRIPTION]`

`Example of usage: todo buy groceries`

### Adding a Event task: `event`

The event feature enables you to add event-based tasks with start and end times.

Format: `event [DESCRIPTION] /from [DATE_TIME] /to [DATE_TIME`

Example of usage: `event car roadshow /from 2023/12/12 1200 /to 2023/12/15 1200`

### Adding a Deadline task: `deadline`

The deadline feature enables you to add tasks that have a specific deadline.

Format: `deadline [DESCRIPTION] /by [DATE_TIME]`

Example of usage: `deadline assignment submission /by 15/10/2023 0800`

### Listing all tasks: `list`

The list feature displays a comprehensive list of all your tasks.

Format: `list`

### Marking a task as done: `mark`

The mark command allows you can mark a task as completed.

Format: `mark [TASK_INDEX]`

Example of usage: `mark 3`

### Marking a task as not done: `unmark`

The unmark command allows you can mark a task as not completed.

Format: `unmark [TASK_INDEX]`

Example of usage: `unmark 2`

### Deleting a task: `delete`

The delete command allows you to easily remove a task from your list.

Format: `delete [TASK_INDEX]`

Example of usage: `delete 5`

### Searching for a task: `search`

The search feature allows you to find specific tasks based on a keyword. You can also narrow your search by specifying a task type (optional).

Format: `search [KEYWORD_STRING] [TASK_TYPE] (optional)`

Example of usage: `search tuna`, `search tuna event`, `search tuna deadline` 

* `search tuna` search for the keyword "tuna" in all tasks:
* `search tuna event` - search for the keyword "tuna" in Event tasks:
* `search tuna deadline` - search for the keyword "tuna" in Deadline tasks:

### Exit the program: `bye`

The bye command allows you to exit the program.

Format: `bye`

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Delete** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Search** | `search` e.g., `search tuna event`
**Bye** | `bye`
