# botM

botM is a task manager app.

Commands:

## See all commands: `help`
Prints the list of commands.  
Format: `help`

## Print goodbye message: `bye`
Prints a goodbye message.  
Format: `bye`

## See all tasks: `list`
Prints the list of tasks you have.  
Format: `list`

## Set task as complete: `mark`
Marks the task given by the task number as complete.  
Format: `mark (task number)`  
Example: `mark 1`

## Remove task from task list: `delete`
Delete the task given by the task number.  
Format: `delete (task number)`  
Example: `delete 1`

## Add todo task: `todo`
Creates a todo task with a required title.  
Format: `todo <title>`  
Example: `todo Homework`

## deadline
Creates a deadline task with a required title and a optional deadline.  
Format: `deadline <title> /by <deadline>`  
Example: `deadline Promotion /by Sunday` 

## event
Creates a event task with a required title and a optional from time and to time.  
Format: `event <title> /from <fromTime> /to <toTime>`  
Example: `event Concert /from Sunday 2pm /to Sunday 4pm`

## find
Finds all tasks that match the keyword provided.  
Format: `find <keyword>`  
Example: `find chocolate` finds all tasks that have "chocolate" somewhere in their titles.
