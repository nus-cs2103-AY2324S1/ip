# Ben

Ben is a Command Line Interface (CLI) based application that allows users to easily track their reminders, while still having the benefits of a Graphical User Interface (GUI).
## Getting Started

1. Ensure you have Java  11  or above installed in your Computer.
2. Download the latest  `ben.jar`  from  [here](https://github.com/lambraydon/ip/releases/tag/A-Release).
3. Open a command terminal,  `cd`  into the folder you put the jar file in, and use the  `java -jar ben.jar`  command to run the application.  

## Features

- `DESCRIPTION` is the parameter that the user inputs that describes the task
- `DATETIME` is the paramater that represents date & time in the following format: `DD/MM/YYYY HHMM`
- `INDEX` refers to the task number in the list of the task

### Adding a task: `todo`
Adds a todo to the list of tasks.

Format: todo DESCRIPTION

Shortcut: td DESCRIPTION

Example: todo tutorial


### Adding a task: `deadline`
Adds a deadline to the list of tasks.

Format: deadline DESCRIPTION /by DATETIME

Shortcut: dl DESCRIPTION /by DATETIME

Example: deadline return book /by 2/9/2023 1200

### Adding a task: `event`
Adds an event to the list of tasks.

Format: event DESCRIPTION /from DATETIME /to DATETIME

deadline e DESCRIPTION /from DATETIME /to DATETIME

Example: event conference /from 2/9/2023 1200 /to 2/9/2023 1300

### Getting list of tasks:
Gets the current list of tasks.

Format: list

Shortcut: l

### Marking a task: `mark`
Marks a task as completed.

Format: mark INDEX

Shortcut: m INDEX

Example: m 1

### Unmarking a task: `unmark`
Unmarks a task as uncompleted.

Format: unmark INDEX

Shortcut: um INDEX

Example: um 1


### Deleting a task: `delete`
Deletes the task from the list.

Format: delete INDEX

Shortcut: d INDEX

Example: delete 1

### Filtering tasks by keyword: `find`

Filters tasks whose descriptions contains the keyword.

Format: find KEYWORD

Shortcut: f KEYWORD

Example: f H

### Exiting the program: `bye`
Saves the current list of tasks and end the program.

Format: bye

Shortcut: b