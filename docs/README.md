# User Guide

Duke is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

- [Quick start](#quick-start)
- [Features](#features)
  - [Viewing help : `help`](#viewing-help--help)
  - [Adding a todo : `todo`](#adding-a-todo--todo)
  - [Adding a deadline : `deadline`](#adding-a-deadline--deadline)
  - [Adding an event : `event`](#adding-an-event--event)
  - [Marking a task : `mark`](#marking-a-task--mark)
  - [Unmarking a task: `unmark`](#unmarking-a-task--unmark)
  - [Finding a task : `find`](#finding-a-task--find)
  - [Deleting a task: `delete`](#deleting-a-task--delete)
  - [Clearing all tasks: `clear`](#clearing-all-tasks--clear)
  - [Exiting the program : `bye`](#exiting-the-program--bye)
  - [Saving the data](#saving-the-data-)
- [Command summary](#command-summary)

## Quick start

1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `duke.jar` from [here](https://github.com/samuelim01/ip/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar duke.jar` commmand to run the application.
5. Type the command in the command box and press Enter to execute it. You may refer to the [Features](#features) below for the details of each command.


---

## Features

>ℹ️ Notes about the command format:
> - Words in UPPER_CASE are the parameters to be supplied by the user. e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo drink water`.
> - Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `bye` and `clear`) will be ignored.

### Viewing help : `help`

Shows a message describing all the available commands and their uses.

Format: `help`

### Adding a todo : `todo`

Adds a todo to the list of tasks.

Format: `todo DESCRIPTION`

Examples:
- `todo clean room`
- `todo update product website`

### Adding a deadline : `deadline`

Adds a deadline to the list of tasks.

Format: `deadline DESCRIPTION /by DATE`

- The date **must be** in the format YYYY-MM-DD.

Examples:
- `deadline CS2100 Assignment 1 /by 2023-09-18`
- `deadline CS2103 iP /by 2023-09-22`

### Adding an event : `event`

Adds an event to the list of tasks.

Format: `event DESCRIPTION /from START_DATE /to END_DATE`

- The dates **must be** in the format YYYY-MM-DD.

Examples:
- `event Recess Week /from 2023-09-25 /to 2023-10-01`

### Marking a task : `mark`

Marks a task as already done.

Format: `mark INDEX`

- Marks the task at the specified `INDEX`. The index refers to the index number shown in `list` command. The index **must be a positive integer**.

Examples:
- `mark 1`
- `mark 4`

### Unmarking a task : `unmark`

Marks a task as not done yet.

Format: `unmark INDEX`

- Marks the task at the specified `INDEX`. The index refers to the index number shown in `list` command. The index **must be a positive integer**.

Examples:
- `unmark 1`
- `unmark 4`

### Finding a task : `find`

Finds tasks whose description contains the keyword.

Format: `find KEYWORD`

- Finds the task with the given `KEYWORD`. 
- The keyword **must be a single word**.
- The search is case-sensitive. e.g. `room` will not match `ROOM`.

Examples:
- `find room`
- `find CS2100`

### Deleting a task : `delete`

Deletes the specified task from the list of tasks.

Format: `delete INDEX`

- Removes the task at the specified `INDEX`. The index refers to the index number shown in `list` command. The index **must be a positive integer**.

Examples
- `delete 1`
- `delete 5`

### Clearing all tasks : `clear`

Deletes all tasks from the list of tasks.

Format: `clear`

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

The program data is automatically saved in the hard disk when needed. There is no need to save the data manually. 

---

## Command summary

|    Action    | Format                                     |
|:------------:|--------------------------------------------|
|   Add Todo   | `todo DESCRIPTION`                         |
| Add Deadline | `deadline DESCRIPTION /by DATE`            |
|  Add Event   | `event DESCRIPTION /from DATE /to DATE`    |
|     Mark     | `mark INDEX`                               | 
|    Unmark    | `unmark INDEX`                             |
|     Find     | `find KEYWORD`                             |
|    Delete    | `delete INDEX`                             |
|    Clear     | `clear`                                    |
|     Help     | `help`                                     |
|     Exit     | `bye`                                      |

[Back to Start](#user-guide)