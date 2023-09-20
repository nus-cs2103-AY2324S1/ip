# Jeeves User Guide

## Feature List
The exact syntax to be used to access these features will be detailed in the next section.

* [Add and track 'Todo' tasks](#add-and-track-todo-tasks)
* [Add and track 'Deadline' tasks](#add-and-track-deadline-tasks)
* [Add and track 'Event' tasks](#add-and-track-event-tasks)
* [List all of your tasks](#list-all-of-your-tasks)
* [Mark and Unmark tasks](#mark-and-unmark-tasks)
* [Delete tasks from your list](#delete-tasks-from-your-list)
* [Find all tasks matching a keyword](#find-all-tasks-matching-a-keyword)
* [Add and track your 'Notes'](#add-and-track-your-notes)
* [List all your notes](#list-all-your-notes)
* [Editing tasks data file](#editing-tasks-data-file)
* [Editing notes data file](#editing-notes-data-file)

> ###  IMPORTANT
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
 
> The DO_BY_DATE must be provided in the YYYY-MM-DD format

Example of usage:

`deadline finish user guide /by 2023-09-20`

Expected outcome:
Jeeves will create a new Deadline task with description 'finish user guide' with the deadline being '20 Sep 2023'

## Add and track 'Event' tasks
### `event` - Adds a 'Event' task with a task description, start time and end time to the task list
Format: `todo TASK_DESCRIPTION /from START_TIME /to END_TIME`

Example of usage:

`event suffer alone /from 2pm today /to 8pm`

Expected outcome:
Jeeves will create a new Event task with description 'suffer alone', start time of '2pm today' and end time of '8pm' and adds it to the task list (Please note that date/time are kept as strings for the Event command)

## List all of your tasks
### `list` - List all the tasks you have added to your list
Format: `list`

Expected outcome:
Jeeves will list all the tasks he is tracking for you

## Mark and Unmark tasks
### `mark` - Marks a task on the list as done
### `unmark` - Marks a task on the list as not done
Format: `mark/unmark TASK_ID`

Example of usage:

`mark 3`

`unmark 8`

Expected outcome:
The specified task (if it exists) will be marked as done or not done.

## Delete tasks from your list
### `delete` - Deletes a task from the list
Format: `delete TASK_ID`

Example of usage:

`delete 5`

Expected outcome:
The specified task (if it exists) will be deleted from the list and cease to be tracked.

## Find all tasks matching a keyword
### `find` - Searches the task list and returns all task that contains the letters/words provided
Format: `find LETTERS`

Example of usage:

`find x12`

Expected outcome:
Jeeves will display all tasks that contains 'x12' in their description

## Add and track your 'Notes'
### `note` - Adds a Note to the note list
Format: `note NOTE_DESCRIPTION`

Example of usage:

`note Buy milk later`

Expected outcome:
A new note with description 'Buy milk later' will be created and added to the note list

## List all your notes
### `list notes` - Adds a 'Todo' task with a task description to the task list
Format: `list notes`

Expected outcome:
Display all notes that you have added to Jeeves to track.

## Editing tasks data file
### Editing the JeevesData.txt file allows for editing tasks without using commands
Format: `TASK_TYPE|MARK_STATUS|DESCRIPTION|EXTRA_PARAMETERS`

> ###  IMPORTANT
> List each task on a separate line
> 
> `|EXTRA_PARAMETERS` are only required when their task object requires that information
>
> `DO_BY_DATE` is only required for deadline tasks, it also MUST be in the YYYY-MM-DD format
> 
> `START_TIME` and `END_TIME` are only required for event tasks

`TASK_TYPE:` `T` `D` or `E` to denote Todo, Deadline or Event respectively

`MARK_STATUS:` `0` to denote unmarked `1` to denote marked

`DESCRIPTION:` A string description of the task

Example of usage:

`T|0|relax during recess week`

`D|0|die|2023-08-31`

`E|1|project meeting|Aug 6th 2pm|4pm`


Expected outcome:

* A 'Todo' task that is unmarked with description 'relax during 
recess week' will be added to the task list
* A 'Deadline' task that is unmarked with description 'die' and 
do-by-date of '31 Aug 2023' will be added to the task list
* A 'Event' task that is marked with the description 'project meeting',
start time of '6 August 2pm' and end time of '4pm' will be added to the 
task list

## Editing notes data file
### Editing the JeevesNote.txt file allows for editing notes without using commands
Format: `NOTE`

Example of usage:

`Call Charles later`

`Do 1932 assignments for CS2103`

Expected outcome:
Two notes will be created and added to the note list when the application
is launched.
