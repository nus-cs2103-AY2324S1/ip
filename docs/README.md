# User Guide
Dookie is a chatbot that helps you **keep track of your tasks** and **update them seamlessly**.

## Features 
View all tasks: `list`  
Adding a task with no deadline: `todo`  
Adding a task with a deadline: `deadline`  
Adding a task with a duration: `event`  
Mark a task as completed: `mark`  
Mark a task as not completed: `unmark`  
Delete a task: `delete`  
Find a task by a keyword: `find`  
Change the deadline/duration of a task: `postpone`  
Exit the chatbot: `bye`

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest addressbook.jar from [here](https://github.com/jordankanghm/ip/releases).

3. Copy the file to the folder you want to use as the home folder for your AddressBook.

4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar addressbook.jar command to run the application.

Refer to the Features below for details of each command.

### View all tasks: `list`
Displays the accumulated tasks.  
Format: `list`

### Adding a task with no deadline: `todo`
Adds a ToDo task into the current list of tasks.   
Format: `todo TASKNAME`  
* If there is no TASKNAME, no task will be added. 

Examples:  
* `todo read books`
* `todo return books`

### Adding a task with a deadline: `deadline`
Adds a Deadline task into the current list of tasks.   
Format: `deadline TASKNAME /by DD-MM-YYYY TTTT`
* The task will not be successfully added in the following cases.
  * There is nothing after the deadline command.
  * There is nothing after the TASKNAME.
  * /by is not specified explicitly.
  * The date provided is not in the given format.   

Examples:
* `deadline return books /by 23-9-2023 1800`
  `deadline read books /by 24-9-2023 1900`

### Adding a task with a duration: `event`
Adds an Event task into the current list of tasks.   
Format: `event TASKNAME /from DD-MM-YYYY TTTT /to DD-MM-YYYY TTTT`
* The task will not be successfully added in the following cases.
  * There is nothing after the event command.
  * There is nothing after the TASKNAME.
  * /from or /to are not specified explicitly
  * The dates provided are not in the given format 

Examples:
* `event read books /from 23-9-2023 1800 /to 24-9-2023 1900`
* `event return books /from 24-9-2023 1900 /to 25-9-2023 2000`

### Mark a task as completed: `mark`
Marks a task as completed.   
Format:  `mark INTEGER`
* The task will not be marked successfully in the following cases.
  * There is nothing after the mark command.
  * There is no task number corresponding to the given integer.

Examples:
* `mark 1`

### Mark a task as not completed: `unmark`
Marks a task as not completed.   
Format:  `unmark INTEGER`
* The task will not be unmarked successfully in the following cases.
    * There is nothing after the unmark command.
    * There is no task number corresponding to the given integer.

Examples:
* `unmark 1`

### Delete a task: `delete`
Deletes a task.  
Format:  `delete INTEGER`
* The task will not be deleted successfully in the following cases.
    * There is nothing after the delete command
    * There is no task number corresponding to the given integer.

Examples:
* `delete 1`

### Find a task by a keyword: `find`
Finds all tasks that have the keyword argument within it.   
Format: `find KEYWORD`
* If there is nothing after the find command, no tasks will be returned.

Examples:
* `find borrow`
* `find return`

### Change the deadline/duration of a task: `postpone`
Changes the deadline or duration based on the user's input.   
Format: `postpone INTEGER DEADLINE/DURATION`
* The deadline argument must follow the same format as the /by specifier when creating a Deadline task.
* The duration argument must follow the same format as the /from and /to specifiers when creating an Event task.
* The deadline/duration arguments must comply with the task at index INTEGER specified.
* If any of the arguments after the postpone command are missing or in the wrong format, the task will not be postponed.

Examples:
* `postpone 1 /by 23-9-2023 1800`
* `postpone 2 /from 23-9-2023 1800 /to 24-9-2023 1900`