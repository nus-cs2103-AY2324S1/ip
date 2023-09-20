# Jeeves User Guide

## Feature List
The exact syntax to be used to access these features will be detailed in the next section.

* [Add and track 'Todo' tasks](#add-and-track-'todo'-tasks)
* Add and track 'Deadline' tasks
* Add and track 'Event' tasks
* List all of your tasks
* Mark and Unmark tasks to keep track of your work
* Delete tasks from your list
* Find all tasks matching a keyword
* Editing a data file to edit tasks without interacting with Jeeves's CLI
* Track your 'Notes'
* List all of your notes
* Editing a data file to edit notes without interacting with Jeeves's CLI

>[!IMPORTANT]
>
> * Words in `UPPER_CASE` are parameters that the user has to supply
>
> * Unless otherwise stated, no parameters are optional and commands have to follow the precise syntax provided

## Add and track 'Todo' tasks
### `todo` - Adds a 'Todo' task with a task description to the task list
Format: `todo TASK_DESCRIPTION`

Example of usage: 

`todo sleep`

Expected outcome:
Jeeves will create a new Todo task with description 'sleep' and adds it to the task list

## Add and track 'Deadline' tasks
### `deadline` - Adds a 'Deadline' task a task description and a 'do-by date' to the task list
Format: `todo TASK_DESCRIPTION /by DO_BY_DATE`

> [!NOTE]
> 
> The DO_BY_DATE must be provided in the YYYY-MM-DD format

Example of usage:

`deadline finish user guide /by 2023-09-20`

Expected outcome:
Jeeves will create a new Deadline task with description 'finish user guide' with the deadline being '20 Sep 2023'