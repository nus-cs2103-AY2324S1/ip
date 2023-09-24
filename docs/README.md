# Jarvis
# User Guide


## Feature List
Specific features will be explained in detail in the following section.

* Add a 'Todo' task
* Add a 'Deadline' task
* Add an 'Event' task
* List all tasks added
* Mark a task as done
* Mark a task as not done
* Delete a task from your list
* Find all tasks matching a keyword
* Find the closest 'Deadline' task that is not done yet
* Say goodbye to Jarvis

## Add a 'Todo' task
### `todo` - Adds a 'Todo' task with a task description to the task list
Format: `todo [DESCRIPTION]`

Example of usage:

`todo study`

Expected outcome: 

Jarvis will add a new Todo task with description 'study' to the task list

## Add a 'Deadline' task
### `deadline` - Adds a 'Deadline' task with a task description and a 'do-by date time' to the task list
Format: `deadline [DESCRIPTION] [/by DO_BY_DATE_TIME]`

> The DO_BY_DATE_TIME must be in d/M/yyyy HHmm format

Example of usage:

`deadline return book /by 01/09/2023 1800`

Expected outcome:

Jarvis will add a new Deadline task with description 'return book' 
and a do-by date time of '01/09/2023 1800' to the task list

## Add an 'Event' task
### `event` - Adds a 'Event' task with a task description, start date time and end date time to the task list
Format: `event [DESCRIPTION] [from START_DATE_TIME] [/to END_TIME]`

> The START_DATE_TIME and END_DATE_TIME must be in d/M/yyyy HHmm format

Example of usage:

'event project meeting /from 02/09/2022 1200 /to 02/09/2022 1600'

Expected outcome:

Jarvis will add a new Event task with description 'project meeting' 
and a start date time of '02/09/2022 1200' 
and a end date time of '02/09/2022 1600' to the task list

## List all tasks added
### `list` - List all the tasks you have added to your list
Format: `list`

Expected outcome:

Jarvis will list all the tasks you have added

## Mark a task as done
### `mark` - Marks a task on the list as done
Format: `mark [TASK_ID]`

Example of usage:

`mark 1`

Expected outcome:

Task 1 will be marked as done.

## Mark a task as not done
### `unmark` - Marks a task on the list as not done
Format: `unmark [TASK_ID]`

Example of usage:

`unmark 2'

Expected outcome:

Task 2 will be marked as not done.

## Delete a task from your list
### `delete` - Deletes a task from the list
Format: `delete [TASK_ID]`

Example of usage:

`delete 3`

Expected outcome:

Task 3 will be removed from the list

## Find all tasks matching a keyword
### `find` - Searches the task list and returns all task that contains the words provided
Format: `find [WORD]`

Example of usage:

`find book`

Expected outcome:

Jarvis will display all tasks that contains 'book' in their description

## Find the closest 'Deadline' task that is not done yet
### `reminder` - Displays a reminder of the closest 'Deadline' task that is not done yet
Format: `reminder`

Example of usage:

`reminder`

Expected outcome:

Displays a reminder of the closest 'Deadline' task that is not done yet

## Say goodbye to Jarvis
### `bye` - Says goodbye to Jarvis and see him bid farewell to you back
Format: `bye`

Expected outcome:

Jarvis will bid you farewell and not accept any user input thereafter.
