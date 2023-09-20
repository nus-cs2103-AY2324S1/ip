# Chatty 👾

Chatty is a task management application, that allows you to keep track of you tasks. The 3 main tasks supported are ToDo, Deadlines and Events. Instructions will be given on how you can use Chatty:

## Quick Start
1. Ensure that you have Java `11` or above stored in your computer
2. Download the latest `duke.jar` from [here](https://github.com/AlyssaPng/ip/releases/tag/A-Release)
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar duke.jar` command to run the application. The Chatty GUI should appear in a few seconds.
4. Similarly, you can download the code and run the application on Intellij. Follow the instructions [here](https://alyssapng.github.io/ip/#setting-up-in-intellij)
5. Type the command in the command box and press Enter to execute it.  e.g. typing `list` and pressing Enter will display all the tasks in the task list.
6. Here are example commands:
    1. `todo read book`: Adds a ToDo task with the description: "read book".
    2. `deadline return book /by Sunday` : Adds a Deadline task with the description: "project" and the due date: "Sunday"
    3. `event project meeting /from Mon 2pm /to 4pm`: Adds an Event task with the description: "project meeting", start date/time: "Mon 2pm" and end date/time: "4pm"
    4. `list`: Displays all the tasks in the task list and their respective task index.
    5. `delete 1`: Removes the first task in the task list.
    6. `unmark 2`: Marks the second task in the task list as undone.
    7. `mark 3`: Marks the third task in the task list as done.
    8. `find project`: Displays the tasks which description contains the String "project".
    9. `bye`: Exits the application.

## Things to Note: 
1. Chatty automatically saves your task list into a `./data/duke.txt` file which is updated everytime task list is changed.
   1. `./data/duke.txt` file is located in the same directory as your `duke.jar` file
2. Chatty will tell you if you have already added a task into you task list, preventing duplicates!

## Setting up in Intellij
Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
    1. Click `Open`.
    2. Select the project directory, and click `OK`.
    3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After setting up the environment, locate the `src/main/java/duke/Launcher.java` file, right click it and chose `Run Launcher.main()`.If the setup was successful, the GUI should appear in a few seconds.

## Features
### Adding ToDo Task: `todo`
Add a Todo task to the task list with a description.

Format: `todo DESCRIPTION`
* `DESCRIPTION` is the description of the to-do task.

Example(s):
- `todo return book`
- `todo buy lunch`

### Adding Deadline Task: `deadline`
Add a Deadline task to the task list with a description and due date.

Format: `deadline DESCRIPTION /by BY`
* `DESCRIPTION` is the description of the deadline task.
* `BY` is the due date of the deadline task consisting of:
    * **Date**: String, M/d/yyyy, MM/dd/yyyy or yyyy-MM-dd
    * Time(optional): String, HHmm

Example(s):
- `deadline return book /by 2/12/2019 1800`
- `deadline finish lab /by 4/12/2019`
- `deadline finish homework /by Sunday`

### Adding Event Task: `event`
Add a Event task to the task list with a description, start date and end date.

Format: `event DESCRIPTION /from FROM /to TO`
* `DESCRIPTION` is the description of the event task.
* `FROM` is the start date of the event task consisting of:
    * **Date**: String, M/d/yyyy, MM/dd/yyyy or yyyy-MM-dd
    * Time(optional): String, HHmm
* `TO` is the end date of the event task consisting of:
    * **Time**: String, HHmm

Example(s):
- `event project meeting /from 2019-10-15 1400 /to 1600`
- `event football match /from 2019-5-15 1800 /to 4pm`

### Deleting a Task: `delete`
Remove a task from the task list using the task's index.

Format: `delete TASKINDEX`
* Removes the task with the given `TASKINDEX`. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3, …

Example(s):
- `delete 1`


### Marking a Task as done: `mark`
Mark a task as done on the task list using the task's index.d
Format: `mark TASKINDEX`
* Marks the task with the given `TASKINDEX` as done. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3, …

Example(s):
- `mark 1`

### Marking a Task as undone: `mark`
Mark a task as undone on the task list using the task's index.

Format: `mark TASKINDEX`
* Marks the task with the given `TASKINDEX` as undone. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3, …

Example(s):
- `unmark 1`

### Finding task(s): `find`
Find task(s) with a matching given keyword

Format: `find KEYWORD`
* find task(s) with the matching `KEYWORD` in their description.

Example(s):
- `find project` will return
```
Here are the matching tasks in your list:
1.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
2.[E][ ] project call (from: Oct 15 2019 2pm to: 4pm)
```

### Listing all the tasks: `list`
List all the task(s) in the task list.

Format: `list`

Example(s):
- `list` will return
```
Here are the tasks in your list:
1.[T][X] read book
2.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
3.[T][ ] borrow book
```

### Exiting the application: `bye`
Exit the application when you are done using it.

Format: `bye`

