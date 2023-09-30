# User Guide

Veneto is an app that can remember things you need to do


## Features 

### Add task

Adds a task to Veneto's tasklist.
Format: 
```
toDo [TASK]
deadline [TASK] /by [DEADLINE(YYYY-MM-DD)]
event [TASK] /from [START_TIME(YYYY-MM-DD)] /to [END_TIME(YYYY-MM-DD)]
```
Examples: 
`event meeting A /from 2001-01-01 /to 2003-01-01`


### List tasks

Lists all tasks from Veneto's tasklist.
Format: 
`list`


### Mark task

Marks the i<sup>th</sup> task as done.
Format: 
```
mark [TASK_ID]
```
Examples: 
`mark 1`


### Unmark task

Unmarks the i<sup>th</sup> task as undone.
Format: 
```
unmark [TASK_ID]
```
Examples: 
`unmark 1`


### Find task

Finds the task(s) that contains specific keyword(s).
Format: 
```
find [KEYWORD(S)]
```
Examples: 
`find borrow book`


### Delete task

Deletes the i<sup>th</sup> task.
Format: 
```
delete [TASK_ID]
```
Examples: 
`delete 1`
