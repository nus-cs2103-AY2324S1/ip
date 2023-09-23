# Chatty 👾

Chatty is a task management application, that allows you to keep track of you tasks. The 3 main tasks supported are ToDo, Deadlines and Events. Instructions will be given on how you can use Chatty:

## Quick Start
1. Ensure that you have Java `11` or above stored in your computer
2. Download the latest `duke.jar` from [here](https://github.com/AlyssaPng/ip/releases/tag/A-Release)
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar duke.jar` command to run the application. The Chatty GUI should appear in a few seconds.
4. Type the command in the command box and press Enter to execute it.  e.g. typing `list` and pressing Enter will display all the tasks in the task list.
5. Here are some example commands:
   1. `todo read book`: Adds a ToDo task with the description: "read book".
   2. `deadline CS2103T project /by 2019-10-15 1800` : Adds a Deadline task with the description: "CS2103T IP" and due date: "Oct 15 2019 6:00pm"
   3. `list`: Displays all the tasks in the task list and their respective task index.
   4. `delete 1`: Removes the first task in the task list.
   5. `mark 3`: Marks the third task in the task list as done.
   6. `find project`: Displays the tasks which description contains the String "project".
   7. `bye`: Exits the application.

## Things to Note:
1. Chatty automatically saves your task list into a `./data/duke.txt` file which is updated everytime task list is changed.
   1. `./data/duke.txt` file is located in the same directory as your `duke.jar` file
2. Chatty will tell you if you have already added a task into you task list, preventing duplicates!

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
* `BY` is the due date of the deadline task.
* The format for input `BY` should be  `yyyy-MM-dd HHmm`

Example(s):
- `deadline finish work /by 2020-10-15 2359`
- `deadline CS2103T project /by 2019-10-15 1800`

### Adding Event Task: `event`
Add a Event task to the task list with a description, start date and end date.

Format: `event DESCRIPTION /from FROM /to TO`
* `DESCRIPTION` is the description of the event task.
* `FROM` is the start date of the event task.
* `TO` is the end date of the event task.
* The format for inputs `FROM` and `TO` should be  `yyyy-MM-dd HHmm`

Example(s):
- `event project meeting /from 2019-10-15 1400 /to 2019-10-15 1600`
- `event football match /from 2019-05-15 1800 /to 2019-10-15 1930`

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

### Marking a Task as undone: `unmark`
Mark a task as undone on the task list using the task's index.

Format: `unmark TASKINDEX`
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
1.[E][ ] project meeting (from: Oct 15 2019 2:00pm to: Oct 15 2019 4:00pm)
2.[D][ ] CS2103T project (by: Oct 15 2019 6:00pm)
```

### Listing all the tasks: `list`
List all the task(s) in the task list.

Format: `list`

Example(s):
- `list` will return
```
Here are the tasks in your list:
1.[T][X] return book
2.[E][ ] project meeting (from: Oct 15 2019 2:00pm to: Oct 15 2019 4:00pm)
3.[D][ ] CS2103T project (by: Oct 15 2019 6:00pm)
4.[E][ ] football match (from: May 15 2019 6:00pm to: Oct 15 2019 7:30pm)
```

### Exiting the application: `bye`
Exit the application when you are done using it.

Format: `bye`

