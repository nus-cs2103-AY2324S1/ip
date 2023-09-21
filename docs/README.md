# User Guide
### Chatbot Name: Benedict Cucumber Badge
### Chatbot Description:   
A chatbot that helps you keep track of your tasks.
By allowing you to create different task type, you can keep track of your tasks easily.

## Features 

### Create a To-Do task: `todo`

Creates a To-Do task with a description.  
Format: `todo <DESCRIPTION>`  
Example: `todo read book`  
**Result**: `[T][ ] read book`

### Create a Deadline task: `deadline`
Creates a Deadline task with a description and a deadline.  
Format: `deadline <DESCRIPTION> /by <DEADLINE>`  
Example: `deadline return book /by Sunday`   
**Result**: `[D][ ] return book (by: Sunday)`

> Note: The deadline specified must be in the format of  
> {dd/mm/yyyy hhmm, d/mm/yyyy hhmm}  
> mm means that month must be a double digit number  
> Example: **21/09/2023 1600** and **1/09/2023 2359** are valid deadlines


### Create an Event task: `event`
Creates an Event task with a description, a start time and an end time.  
Format: `event <DESCRIPTION> /from <START TIME> /to <END TIME>`  
Example: `event project meeting /from 21/09/2023 1600 /to 21/09/2023 1800`
**Result**:   
`[E][ ] project meeting (from: Sep-21-2023 1600PM to: Sep-21-2023 1800PM)`

> Note: The start time and end time specified must be in the format of  
> {dd/mm/yyyy hhmm, d/mm/yyyy hhmm}  
> mm means that month must be a double-digit number  
> Example: **21/09/2023 1600** and **1/09/2023 2359** are valid start and end times 

### List all tasks: `list`
Lists all tasks in the task list.  
Format: `list`  
Example: `list`  
**Result**:
```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Sunday)
3. [E][ ] project meeting (from: Sep-21-2023 1600PM to: Sep-21-2023 1800PM)
```
### Mark a task as done: `mark`
Marks a task as done.  
Format: `mark <TASK NUMBER>`  
Example: `mark 1`  
**Result**:  
```
Nice I've marked this task as done: 
[T][X] Do optional quiz for CS2103T
```

>Note: The task number is the number shown beside the task when `list` is called.      
> If mark is called on a task that is already done, the task will remain unchanged.

### Unmark a task as done: `unmark`
Unmarks a task as done.   
Format: `unmark <TASK NUMBER>`  
Example: `unmark 1`  
**Result**:  
```
OK, I've unmarked this task as not done yet:
[T][ ] Do optional quiz for CS2103T
```
>Note: The task number is the number shown beside the task when `list` is called.  
> If unmark is called on a task that is not done, the task will remain unchanged.

### Delete a task: `delete`
Deletes a task.
Format: `delete <TASK NUMBER>`  
Example: `delete 1`  
**Result**:
```
Noted. I've removed this task:
[T][X] Do optional quiz for CS2103T
Now you have 2 tasks in the list.
```
>Note: The task number is the number shown beside the task when `list` is called.

### Find tasks: `find`
Finds tasks that contain the keyword.  
Format: `find <KEYWORD>`  
Example: `find book`  
**Result**:
```
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Sunday)
```

>Note: The keyword is case-sensitive.

### Tag a task: `tag`
Tags a task with a tag.  
Format: `tag <TASK NUMBER> <TAG>`  
Example: `tag 1 IMPORTANT`
**Result**:  
```
1. [T][ ] read book | 
tags: IMPORTANT
```

>Note: The task number is the number shown beside the task when `list` is called.

### Show all tags: `showtags`
Shows all tags.
Format: `showtags`
Example: `showtags`
**Result**:  
```
Here are the tags in your list: 
1. [T][ ] read book |
tags: IMPORTANT
```

### Remove a tag from a task: `removetag`
Removes a tag from a task.
Format: `removetag <TASK NUMBER> <TAG>`
Example: `removetag 1 IMPORTANT`
**Result**:  
```
1. [T][ ] read book |
tags:
```

>Note: The task number is the number shown beside the task when `list` is called.


### Exit the program: `bye`
Exits the program.  
Format: `bye`  
Example: `bye`  
**Result**:  
```
Bye. Hope to see you again soon!
```
## Usage

### `todo` - Creates a To-Do task
### `deadline` - Creates a Deadline task
### `event` - Creates an Event task
### `list` - Lists all tasks
### `mark` - Marks a task as done
### `unmark` - Unmarks a task as done
### `delete` - Deletes a task
### `find` - Finds tasks that contain the keyword
### `tag` - Tags a task with a tag
### `showtags` - Shows all tags
### `removetag` - Removes a tag from a task
### `bye` - Exits the program

If in trouble, follow the feedback given by the GUI, it is meant to help you!  
Follow the formatting exactly to the T, or you will get an error message.

If you are still in trouble, you can contact the developer at:  
```
sk2001git
```
