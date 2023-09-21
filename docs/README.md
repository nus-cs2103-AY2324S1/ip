# ChadBod User Guide

ChadBod is a Java application with a Graphical User Interface (GUI) that aids you in keeping track of your tasks
as well as their associated details and dates. You can ask the tool to add and delete tasks to/from a list,
display all tasks in said list, mark tasks as done or not done, or even search tasks containing a keyword.

___
## Features 

### Display task list: `list`/`ls`

Shows a list of all tasks that are currently stored.

Format: `list` OR `ls`

### Mark a task as done: `mark`/`mk`

Marks a task in the task list as done.

Format: `mark INDEX` OR `mk INDEX`

* Marks the stored task at the specified index. The index
refers to the index number associated with the task when the
task list is displayed. The index **must be a positive integer**
e.g. 1, 2, 3, ...

### Mark a task as undone: `unmark`/`umk`

Marks a task in the task list as undone.

Format: `unmark INDEX` OR `umk INDEX`

* Marks the stored task at the specified index. The index
  refers to the index number associated with the task when the
  task list is displayed. The index **must be a positive integer**
  e.g. 1, 2, 3, ...

### Adds a to-do task to the task list: `todo`/`td`

Adds a to-do task containing submitted details into the task list.

Format: `todo DETAILS` OR `td DETAILS`

Example:
* todo Finish CS2103/T iP

### Adds a deadline task to the task list: `deadline`/`dl`

Adds a deadline task containing submitted details 
and due time into the task list.

Format: `deadline DETAILS /by DUETIME` OR `dl DETAILS /by DUETIME`

Example:
* deadline Finish CS2103/T iP /by 2023-09-22T23:59:00

### Adds an event task to the task list: `event`/`ev`

Adds an event task containing submitted details, start time and 
end time into the task list.

Format: `event DETAILS /from STARTTIME /to ENDTIME` OR
`ev DETAILS /from STARTTIME /to ENDTIME`

Example:
* event Imaginary date /from 2023-09-23T17:00:00 /to 2023-09-23T21:00:00

### Delete a task: `delete`/`del`

Deletes a task in the task list.

Format: `delete INDEX` OR `del INDEX`

* Deletes the stored task at the specified index. The index
  refers to the index number associated with the task when the
  task list is displayed. The index **must be a positive integer**
  e.g. 1, 2, 3, ...

### Locate tasks by description: `find`/`f`

Displays all tasks whose descriptions contain the given keyword.

Format: `find KEYWORD` OR `f KEYWORD`

### Saying farewell: `bye`/`bb`

Sends you a farewell message that leaves you feeling
warm and fuzzy inside.

Format: `bye` OR `bb`

___
## Acknowledgements
SE-Edu: Guides for SE student projects
