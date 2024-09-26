# Luxion User Guide

## Features 

### Adding a To-Do item: `todo`

Adds a To-Do task to the task list.

Format: `todo TASK_NAME`

Examples: 

- `todo Take care of my garden`
- `todo Watch CS2103T lecture`

Notes:
- Everything after `todo` will be used as the task name/description.

### Adding a task with a deadline: `deadline`

Adds a task with a deadline to the task list.

Format: `deadline TASK_NAME /by DATETIME`

DATETIME is in "dd-MM-yyyy hhmm" format.
- 21-12-2001 2359
- day-month-year 24_hour_time

Examples: 

- `deadline Submit CS2100 Assignment 1 /by 18-09-2023 1200`
- `deadline Submit internship application /by 07-09-2023 2359`

### Adding an event: `event`

Adds a event to the task list.

Format: `event TASK_NAME /from DATETIME /to DATETIME`

DATETIME is in "dd-MM-yyyy hhmm" format.
- 21-12-2001 2359
- day-month-year 24_hour_time

Examples:
- `event Attend Birthday Celebration /from 07-09-2023 1800 /to 07-09-2023 2300`
- `event Business meeting /from 07-09-2023 1000 /to 07-09-2023 1200`

### Listing all items: `list`

Shows a list of all the items on the task list.

Format: list

### Finding an item: `find`

Finds items which name/description contains the given string.

Format: `find STRING`

- The search is case-sensitive. e.g `Submit` will not match `submit`
- The entire `STRING` will be used to search all the `TASK_NAME` in the task list.
- The index of the item will appear along with the item.
- If no item is found, Luxion will inform you.

Examples:
- `find assignment` will return `2.[D][ ] submit CS2100 assignment 1 (by: 18-09-2023 1200)`

### Deleting an item: `delete`

Deletes an item from the task list.

Format: `delete ITEM_INDEX`

- `ITEM_INDEX` represents the index of the item on the task list
- `delete` can only accept a number within the task list index

Examples:
- `delete 2` will return `[D][ ] submit CS2100 assignment 1 (by: 18-09-2023 1200) has been deleted`

### Marking and unmarking an item as complete: `mark`/`unmark`

Marks or unmarks an item as completed.

Format: `mark ITEM_INDEX`, `unmark ITEM_INDEX`

- `ITEM_INDEX` represents the index of the item on the task list
- `mark`/`unmark` can only accept a number within the task list index
- If an item is already marked/unmarked, Luxion will inform you

Examples:
- `mark 2` will return `[D][X] submit CS2100 assignment 1 (by: 18-09-2023 1200)`
- `unmark 2` will return `[D][ ] submit CS2100 assignment 1 (by: 18-09-2023 1200)`

### Undoing a previous command: `undo`

Undoes the previous user command.

Format: `undo`

- `undo` will only be able to undo user inputs
- `undo` will not be able to undo any commands from a previous session
- If there are no commands to undo, Luxion will inform you

Examples:
- `mark 2` will return `[D][X] submit CS2100 assignment 1 (by: 18-09-2023 1200)`
- `undo` will return `[D][ ] submit CS2100 assignment 1 (by: 18-09-2023 1200)`

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data

Luxion auto-saves data whenever the task list changes

- Save for `mark`/`unmark`, `todo`, `deadline`, `event`, `delete`, `undo`
- Does not save when command is unsucessful, `list`, `find`, `bye`

### Error checking while loading data

Luxion will inform the user of whatever errors are in its save file.
The user can then edit the save file from [JAR file location]/data/duke.txt according to the errors Luxion has described to them.
The user can also ignore those errors and continue using Luxion.
This would result in erroneous data from the save file being deleted.
Undoing will not be able to revert this change.
