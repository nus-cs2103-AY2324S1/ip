# Eddie User Guide

## Features
 > **Notes about the command format:**
> - Words in `UPPER_CASE` are the parameters to be supplied by the user. E.g. in `deadline /by DATE`, DATE is a parameter which can be used as `deadline /by 2020-01-01`
> - All dates used in commands must be in `YYYY-MM-dd` format.
> - `<index>` refers to the index of the task as shown in the task list using the `list` command.
> 

### Adding a general task `todo`

Add a task to the list without a specified end date.  
Format: `todo TASKNAME`  
Example: `todo homework`

### Adding a task with a due date `deadline`

Add task with a specified due date to the list of tasks.  
Format: `deadline TASKNAME /by DATE`  
Example:`deadline project /by 2023-01-20`

### Add a task with start and end dates `event`

Adds a task to the list with a specified start and end date.  
Format: `event TASKNAME /from DATE /to DATE`  
Example: `event holiday /from 2020-01-02 /to 2020-01-03`



### Mark a task as done `mark`
Mark a task as done on the list. The task will be labeled as `[x]`.  
Format:`mark <index>`  
Example:`mark 1`

### Mark a task as undone `unmark`
Mark a task as undone on the list. The task will be labeled as `[ ]`  
Format:`unmark <index>`  
Example: `unmark 1`

### Remove a task `remove`
Removes a task from the task list. The task will no longer appear when `list` is called.  
Format:`remove <index>`   
Example:`remove 2`  

### Tag a task `tag`
Tags a specified task in the list to add information to the task. The tags will start with a hashtag `#tag`  
Format:`tag <index> TAG`  
Example:`tag 1 important`

### Search for a task `find`
Searches for a task using the specified string by name. Returns a list of tasks with the specified string in its name. 
Take note that the list should **not** be used as a reference for commands which require the task index.  
Format: `find SUBSTRING`  
Example: `find go`

### List all tasks `list`
Display the entire list of tasks, along with all the relevant information.  
The list should be printed in the following format:  
```
1. [T][] go home 
2. [D][x] team project (by: Jan 1 2020)
```
### Clear all tasks `clear`
Deletes all the tasks in the list, along with their information. Be careful when using this command!  

### Exit the bot `bye`
Closes Eddie, see you next time!

