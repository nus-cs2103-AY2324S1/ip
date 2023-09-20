# Philip User Guide

Welcome to Philip, your personal task manager! Philip is designed to help you keep track of your tasks, whether they are to-dos, deadlines, or events. This user guide will provide you with an overview of Philip's features and how to use them effectively.

## Quick start
1. Ensure you have Java `11` installed on your computer.
2. Download the latest `duke.jar`
3. Copy the file to the folder you want to use as the home folder for your Philip.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar duke.jar command to run the application. 
5. Type the command in the command box and press Enter to execute it.
6. Refer to Features below for details of each command.

## Features 

### Adding a to-do task: `todo`

Adds a to-do task to your list.

Format: `todo DESCRIPTION`
- `DESCRIPTION` is the description of your to-do task

Example(s):
- `todo Buy groceries`

### Adding a deadline task: `deadline`

Adds a deadline task to your list.

Format: `deadline DESCRIPTION /by DEADLINE`
- `DESCRIPTION` is the description of your deadline task
- `DEADLINE` is the deadline of your task in 
  - yyyy-mm-dd hhmm OR 
  - yyyy-mm-dd format

Example(s):
- `deadline CS2100 assignment /by 2023-09-18 2359`
- `deadline CS2100 assignment /by 2023-09-18`

### Adding an event task: `event`

Adds a event task to your list.

Format: `event DESCRIPTION /from START /to END`
- `DESCRIPTION` is the description of your event task
- `START` is the start of your event in 
  - yyyy-mm-dd hhmm OR
  - yyyy-mm-dd format
- `END` is the end of your event in
  - yyyy-mm-dd hhmm OR
  - yyyy-mm-dd format

Example(s):
- `event CS2103T tutorial /from 2023-09-20 1000 /to 2023-09-20 1100`

### Marking a task as done: `mark`

Marks a task as done.

Format: `mark INDEX`
- Marks task at specific `INDEX` as done

Example(s):
- `mark 3`

### Un-marking a task as done: `unmark`

Un-marks a task as done.

Format: `unmark INDEX`
- Un-marks task at specific `INDEX` as done

Example(s):
- `unmark 2`

### Deleting a task: `delete`

Deletes a task from your list.

Format: `delete INDEX`
- Deletes task at specific `INDEX`

Example(s):
- `delete 1`

### Finding tasks by a keyword: `find`

Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`
- Tasks matching the `KEYWORD` will be returned.
- The search is case-sensitive

Example(s):
- `find CS2103T` returns 
  ```
  `1. [E][ ] CS2103T tut (from: 8 Dec 2023 10:00AM)(to: 9 Dec 2023 10:00AM)` and 
  `2. [D][ ] CS2103T quiz (by: 18 Sep 2023 11:59PM)`
  ```

### Listing tasks: `list`

Lists all tasks in the list.

Format: `list`

Example:
```
Here are the tasks in your list:
1. [T][X] eat lunch
2. [E][ ] CS2103T tut (from: 8 Dec 2023 10:00AM)(to: 9 Dec 2023 10:00AM)
3. [D][X] CS2100 quiz (by: 18 Sep 2023 11:59PM)
4. [D][ ] CS2103T quiz (by: 18 Sep 2023 11:59PM)
```

### Sorting tasks by date: `sort`

Sorts tasks in list by date.

Format: `sort`
- Returns a sorted list of all tasks in the following format:
  - All the to-dos
  - Deadlines and events sorted by their deadlines and start times respectively
- Does not modify sequence of original list

Example:
```
Here are the tasks in your list:
1. [T][X] eat lunch
2. [D][X] CS2100 quiz (by: 18 Sep 2023 11:59PM)
3. [D][ ] CS2103T quiz (by: 18 Sep 2023 11:59PM)
4. [E][ ] CS2103T tut (from: 8 Dec 2023 10:00AM)(to: 9 Dec 2023 10:00AM)
```

### Exiting when you are done: `bye`

Exits the program when you are done using it. 

Format: `bye`

### Getting some love: `i love u`

Gives you some love.

Format: `i love u`

### Saving the data

Philip data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
