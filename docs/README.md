# Mr Red
# User Guide

## Features 
### Notes about the command format
1. All the command word at the start of the 
input are all lower cased.
2. Words starting with "/" are key command attribute and is needed for the command to be valid. eg ```/by```
3. The datetime format it can take is dd/mm/yyyy tttt where time is 24 hrs clock eg. ``22/02/2023 2200``

### Adding todo task - ```todo```
Adds simple todo task to keep track off.

### Adding deadline task - ```deadline```
Adds deadline task to keep track of tasks that have a due datetime.

### Adding event task - ```event```
Adds event task to keep track of tasks that have a start and end datetime.

### Adding todotime task - ```todotime```
Adds todotime task to keep track of tasks that have a duration.

### Setting the task as done - ```mark```
Marks a task as done.

### Setting the task as not done - ```unmark```
Unmarks a task as not done.

### Deleting a task - ```delete```
Deletes a task from the list. 

### Listing all the tasks - ```list```
Shows a list of all tasks.

### Searching for task - ```find```
Searches a task based on the word type.

### Existing the program - ```bye```
Exits the program.

### Getting help - ```help```
Lists down all the commands that can be used and its examples.

## Usage

### `todo` - Add todo task
Format: `todo task`
- task is the name of the task.

Example of usage: 

`todo do laundry`

### `deadline` - Add deadline task
Format: `deadline task /by dd/mm/yyyy tttt`
- task is the name of the task.
- datetime after /by is the due date of the task.

Example of usage: 

`deadline cs2102 project /by 22/09/2023 1200`

### `event` - Add event task
Format: `event task /from dd/mm/yyyy tttt /to dd/mm/yyyy tttt`
- task is the name of the task.
- datetime after /from is the start time.
- datetime after /to is the end time.

Example of usage:

`event zoom meeting /from 22/09/2023 1200 /to 22/09/2023 1400`

### `todotime` - Add todotime task
Format: `todotime task /for N [hours/minutes]`
- task is the name of the task.
- N is a number.
- [hours/minutes], can only be either.

Example of usage: 

`todotime read books /for 2 hours`

`todotime read books /for 45 minutes`

### `list` - Lists all the tasks
Format: `list`

Example of usage: 
`list`

### `delete` - Deletes task
Format: `delete index`
- index is the position of the task after `list` command.

Example of usage: 
`delete 3`

### `mark` - Marks tasks
Format: `mark index`
- index is the position of the task after `list` command.

Example of usage: 
`mark 2`

### `unmark` - Unmarks tasks
Format: `unmark index`
- index is the position of the task after `list` command.

Example of usage: 
`unmark 2`

### `find` - Finds tasks
Format: `find keyword`
- Keyword is the word/substring to be search for.

Examples of usage:

`find book`

`find bo`

### `help` - List available commands that can be used
Format: `help`

Example of usage: 
`help`

### `bye` - Exits program
Format: `bye`

Example of usage: 
`bye`
