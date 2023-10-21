# Johnny User Guide
Johnny is an intelligent chatbot that can help you manage your tasks. Simply input
the proper commands and enjoy your saved time!

## Quick start
1. Ensure you have Java 11 installed on your computer.
1. Download the latest duke.jar
1. Copy the file to the folder you want to use as the home folder for your AddressBook.
1. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar duke.jar command to run the application.
1. Type the command in the command box and press Enter to execute it.
1. Refer to Features below for details of each command.

## Features 
Input the desired command followed by the necessary fields 
(designated with `CAPITAL_LETTERS`) with a space separating them. 
To input time, use the `DD/MM/YYYY`, followed by 24-hour time format e.g. `1800`.



### Add Tasks 
There are 3 types of tasks that can be added, each with their own specific fields:

`todo TASK_TITLE`

`deadline TASK_TITLE /by DUE_TIME`

`event TASK_TITLE /from START_TIME /to END_TIME`

Example:

* `todo hit the gym`
* `event powerlifting meet /from 04/04/2003 1800 /to 07/07/2003 1800`

### Delete tasks
You can delete tasks from your list using the corresponding index:

`delete INDEX`

Example:

* `delete 1` Deletes first task on the list

### Mark/Unmark tasks
You can mark or unmark tasks on your list as complete using the corresponding index:

`mark INDEX`

`unmark INDEX`

Example:

* `mark 2` Marks second task on the list
* `unmark 3` Unmarks third task on the list

### Update tasks
You can update your desired field of your tasks using the corresponding index and fields:

`update INDEX FIELD NEW_EDIT`

> 💡 Tip: You can only update fields for a task that has that field! You cannot update the deadline of
>  a ToDo task with no deadline!

Example:

* `update 1 title finish my homework` Updates the title of the first task
* `update 2 deadline 23/02/2003` Updates the deadline of the second task
  
### Find tasks
You can search for a task in your list with a query:

`find QUERY`

Example:

* `find gym` Locates all tasks with "gym" in the title 

### List
Displays all current tasks in your list:

`list`

```
[T] [x] go gym
[D] [ ] finish homework | DUE: 04 July 2003, 6:00 PM |

```

### Saving the data

Johnny data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Johnny data are saved automatically as a JSON file [JAR file location]/data/johnny.txt. Advanced users are welcome to update data directly by editing that data file.

> :exclamation: Caution: If your changes to the data file makes its format invalid, Johnny will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
