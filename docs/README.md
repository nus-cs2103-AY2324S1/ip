# Haste

Haste is a Command Line Interface (CLI) based application that allows users to easily track their reminders, while still having the benefits of a Graphical User Interface (GUI).
## Getting Started

1. Ensure you have Java  `11`  or above installed in your Computer.
2. Download the latest  `haste.jar`  from  [here](https://github.com/yihfei/ip/releases).
3.  Copy the file to the folder you want to use as the  _home folder_  for your AddressBook.

4.  Open a command terminal,  `cd`  into the folder you put the jar file in, and use the  `java -jar haste.jar`  command to run the application.  
    A GUI similar to the below should appear in a few seconds.

## Features

-   Words in  `UPPER_CASE`  are the parameters to be supplied by the user.  
    e.g. in  `todo DESCRIPTION`,  `DESCRIPTION`  is a parameter which can be used as  `todo DESCRIPTION`.
- `DATETIME` parameters have the following format: `YYYY-MM-DD HHMM`
- `INDEX` refers to the task index in `list`. e.g
  ![image](https://user-images.githubusercontent.com/89378503/269198078-7cf41c0d-84a1-473c-94bd-cf1d0180722d.png)
  `todo: read`  has a index of 5.

### Adding a task: `todo`
Adds a todo to the list of tasks.

Format: `todo DESCRIPTION`

Example: `todo read`


### Adding a task: `deadline`
Adds a deadline to the list of tasks.

Format: `deadline DESCRIPTION /by DATETIME`

Example: `deadline return book /by 2023-06-11 0900`

### Adding a task: `event`
Adds an event to the list of tasks.

Format: `event DESCRIPTION /from DATETIME /to DATETIME`

Example: `event project meeting /from 2023-02-21 1600 /to 2023-02-21 1700`

### Getting list of tasks:
Gets the current list of tasks.

Format: `list`

### Marking a task as done: `mark`
Marks a task as done.

Format: `mark INDEX`

Example: `mark 1`

### Marking a task as undone: `unmark`
Marks a task as undone.

Format: `unmark INDEX`

Example: `unmark 1`


### Deleting a task: `delete`
Deletes the task from the list.

Format: `delete INDEX`

Example: `delete 1`

### Filtering tasks by keyword: `find`

Filters tasks whose descriptions contains the keyword.

- Keyword is case-sensitive. e.g `Plan` will not match `plan`
- Keyword does not need to match full word. e.g `pl` will match `plan`

Format: `find KEYWORD`

Example: `find p`
![Screenshot 2023-09-20 at 3 49 45 PM](https://user-images.githubusercontent.com/89378503/269204799-cf2fa76f-2c7e-4cc8-98dc-39bc9d5d2877.png)

### Ending the program: `bye`
Saves the current list of tasks and end the program.

Format: `bye`