# User Guide
Duke is a desktop application for managing your tasks via a Command Lind Interface (CLI) while still having the 
benefits of a Graphical User Interface (GUI)!

## Quick Start
1. Ensure you have Java 11 installed in your computer. Else, you can install it from oracle. 
2. Download the latest duke.jar from release tab in this repo. 
3. Move file to the folder you want to use as the home folder for your Duke. 
4. Open your terminal. Navigate into the folder the jar file in, and run the command `java -jar duke.jar` to run the 
application. A GUI similar to the below should appear in a few seconds.

## Features 

### Viewing help: `help`

Shows a message explaning how to access the help page. <br>
Format: `help`

### Listing all tasks: `list`

Shows a list of all tasks in the task list. <br>
Format: list

### Adding a todo: `todo`
Adds a todo task to the taskList.
Format: `todo [task]`

Examples:
* `todo CS2103t ip`
* `todo laundry`

Expected outcome: 
```
Added task (task description), you now have 3 tasks.
```

### Adding a deadline: `deadline`
Adds a deadline task to the taskList.
Format: `deadline [task] /by [YYYY-MM-DD HH:mm]`

Examples:
* `deadline return book /by 2019-03-22 20:00`
* `deadline finish up ip /by 2023-09-22 23:59`

Expected outcome:
```
Added task (task description), you now have 3 tasks.
```

### Adding an event: `event`
Adds an event to the taskList.
Format: `event [task] /from [YYYY-MM-DD HH:mm] /to [YYYY-MM-DD HH:mm]`

Examples:
* `event Lauv concert /from 2018-03-10 20:00 /to 2018-03-11 20:00`
* `event reservist /from 2018-05-23 20:00 /to 2018-05-26 20:00`

Expected outcome:
```
Added task (task description), you now have 3 tasks.
```

### Marking a task: `mark`
Marks a task in taskList as done.
Format: `mark [task number]`

Marks the task of the specified task number.
The task number refers to the index number shown in the displayed task list.
The index must be a positive integer starting from 1.

Examples:
* `mark 1`
* `mark 3`

Expected outcome:
```
Marked the task as done!
```

### Un-marking a task: `unmark`
Un-marks an existing task as not done.
Format: `unmark [task number]`

Un-marks the task of the specified task number.
The task number refers to the index number shown in the displayed task list.
The index must be a positive integer starting from 1.

Examples:
* `unmark 1`
* `unmark 3`

Expected outcome:
```
Unmarked the task!
```
### Locating a task: find
Find tasks whose description or date contain the given keyword.
Format: `find [keyword]`

Examples:
* `find groceries` returns `[T][X] groceries`
* `find toy` returns `[D][ ] return toy (by: 1pm 10 Mar 2019)`

### Deleting a task: delete
Deletes the task at the specified task number from the task list.
Format: `delete [task number]`

Deletes the task at the specified task number
The task number refers to the index number shown in the displayed task list.
The index must be a positive integer starting from 1.

Examples:
* `delete 1`
* `delete 2`

Expected outcome:
```
Deleted the task (description)
```
### Exiting the program: bye
Exits the program after 1.5seconds.
Format: `bye`