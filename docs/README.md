# NOAC User Guide

## Features 

### Feature-TaskList

Able to add and delete 3 types of tasks: TODO, DEADLINE & EVENT.

Able to find tasks base on keyword or by date.

### Feature-Storage

Store all the current task to a file and load them the next time the program is started.

### Feature-Tagging

Add tags to any task and search task by tags.

## Usage

### `todo` - Adding a Todo Task

Adds new todo task to the list.

Format:

`todo DESCRIPTION`

Example of usage: 

`todo IP`

Expected outcome:

The todo "IP" is added to the task list.
```
Got it. I've added this task:
[T][ ] IP  
Now you have 1 tasks in the list.
```

### `Deadline` - Adding a Deadline Task

Adds new deadline task to the list.

Format:

`deadline DESCRIPTION /by DATE [TIME]`

DATE has the format of `yyyy-MM-dd`

TIME has the format of `HHmm`

Example of usage:

`deadline IP /by 2023-09-22 2359`

Expected outcome:

The deadline "IP" is added to the task list.
```
Got it. I've added this task:
[D][ ] IP (by: Sep 22 2023 2359)  
Now you have 1 tasks in the list.
```

### `Event` - Adding a Event Task

Adds new event task to the list.

Format:

`event DESCRIPTION /from DATE [TIME] /to DATE [TIME]`

DATE has the format of `yyyy-MM-dd`

TIME has the format of `HHmm`

Example of usage:

`event IP /from 2023-08-01 0000 /to 2023-09-22 2359`

Expected outcome:

The event "IP" is added to the task list.
```
Got it. I've added this task:
[E][ ] IP (from: Aug 01 2023 0000 to: Sep 22 2023 2359)  
Now you have 1 tasks in the list.
```


### `list` - List all the task.

Display a list of all the task in the task list.

Format:

`list`

Example of usage:

`list`

Expected outcome:

All the tasks are displayed in order of creation.
```
Here are the tasks in your list:
1.[T][ ] IP  
2.[D][ ] IP (by: Sep 22 2023 2359)  
3.[E][ ] IP (from: Aug 01 2023 0000 to: Sep 22 2023 2359) 
```

### `delete` - Deletes a task.

Deletes the task base on the task number. 

Format:

`delete TASK_NO`

Example of usage:

`delete 1`

Expected outcome:

Task number 1 is removed from the list.
```
Noted. I've removed this task:
[D][ ] IP (by: Sep 22 2023 2359)  
Now you have 2 tasks in the list. 
```

### `mark` - Mark a task as done.

Marks a task as done base on the task number.

Format:

`mark TASK_NO`

Example of usage:

`mark 1`

Expected outcome:

The deadline task is marked as done.
```
Nice! I've marked this task as done:
[D][X] IP (by: Sep 22 2023 2359)
```

### `unmark` - Unmark a task as not done.

Unmarks a task as not done base on the task number.

Format:

`unmark TASK_NO`

Example of usage:

`unmark 1`

Expected outcome:

The deadline task is unmarked as not done.
```
OK, I've marked this task as not done yet:
[D][ ] IP (by: Sep 22 2023 2359)  
```

### `on` - Displays the tasks on that date.

Displays a list of task that are due or starts on that date.

Format:

`on DATE`

Example of usage:

`on 2023-09-22`

Expected outcome:

A list of the tasks that starts or end on that date, in this case deadline IP and event IP.
```
The tasks on this date are:
[D][ ] IP (by: Sep 22 2023 2359)  
[E][ ] IP (from: Aug 01 2023 0000 to: Sep 22 2023 2359)  
```

### `tag` - Tag a task

Adds a tag to a task base on the task number.

Format:

`tag TASK_NO #TAG`

Example of usage:

`tag 1 #CS2103T`

Expected outcome:

The specified task has been tagged with #CS2103T
```
[D][ ] IP (by: Sep 22 2023 2359) #CS2103T has been tagged with #CS2103T
```

### `find` - Find a task

Finds all the task which contains a string or has that tag

Format:

`find SEARCH_STRING` or `find #TAG`

Example of usage:

`find I` or `find #CS2103T`

Expected outcome:

A list of all the task that contains the string or tag
```
Here are the matching tasks in your list:
[D][ ] IP (by: Sep 22 2023 2359) #CS2103T
[E][ ] IP (from: Aug 01 2023 0000 to: Sep 22 2023 2359) 
```
```
Here are the matching tasks in your list:
[D][ ] IP (by: Sep 22 2023 2359) #CS2103T 
```

### `bye` - Exits the program

Stops the program and disable user input.

Format:

`bye`

Example of usage:

`bye`

Expected outcome:

Goodbye message is displayed and user input disabled.
```
Bye. Hope to see you again soon! 
```
