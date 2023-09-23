# Remy User Guide

Ever wanted to get insulted by a task manager that doesn't get much done at all? Remy is here to fill that niche.

_Note: This User Guide has been adapted from
the [AB3 User Guide](https://se-education.org/addressbook-level3/UserGuide.html#features)._

* [Quick Start](#quick-start)
* [Features](#features)
  * [Adding a todo](#adding-a-todo--todo)
  * [Adding a deadline](#adding-a-deadline--deadline)
  * [Adding an event](#adding-an-event--event)
  * [Marking a task as complete](#marking-a-task-as-completed--mark)
  * [Marking a task as incomplete](#marking-a-task-as-incomplete--unmark)
  * [Listing all tasks](#listing-all-tasks--list)
  * [Searching for a task](#locating-tasks-by-description--find)
  * [Deleting a task](#deleting-a-task--delete)
  * [Exiting the program](#exiting-the-program--exit)
  * [Saving the data](#saving-the-data)
* [FAQ](#faq)
* [Command Summary](#command-summary)

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `remy.jar` from [here](https://github.com/p-xp/ip/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Remy.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar remy.jar` command to
   run the application
5. In the text box of the window that appears, type a valid command and press enter to execute it. You may refer
   to [Features](#features) below for details of each command.

---

## Features

> **Information about the command format**<br>
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
    e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be specified (`todo read book`).
> - Items in square brackets are optional.<br>
    e.g `todo DESCRIPTION [/p PRIORITY]` can be used as `todo read book` or as `todo read book /p high`.
> - Parameters must be typed in the prescribed order.<br>

### Adding a todo: `todo`

Adds a todo to the task list.

Format: `todo DESCRIPTION [/p HIGH/MEDIUM/LOW]`

Examples:

* `todo read book`
* `todo read book /p high`
* `todo read book /p HiGh`
* `todo read book /p low`

### Adding a deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline DESCRIPTION /by DUEDATE [/p HIGH/MEDIUM/LOW]`

* The date **must be** in YYYY-MM-DD format.

Examples:

* `deadline return book /by 2021-02-11`
* `deadline return book /by 2021-02-11 /p high`
* `deadline return book /by 2021-02-11 /p HiGh`
* `deadline return book /by 2021-02-11 /p low`

### Adding an event: `event`

Adds an event to the task list.

Format: `event DESCRIPTION /from STARTDATE /to ENDDATE [/p HIGH/MEDIUM/LOW]`

* The dates **must be** in YYYY-MM-DD format.

Examples:

* `event hackathon /from 2021-02-11 /to 2023-02-13`
* `event hackathon /from 2021-02-11 /to 2023-02-13 /p high`
* `event sabah trip /from 2021-02-11 /to 2023-02-13 /p hiGH`
* `event sabah trip /from 2021-02-11 /to 2023-02-13 /p low`

### Marking a task as completed: `mark`

Marks an indicated task as completed.

Format: `mark INDEX`

* The index refers to the index number shown in the task list upon using `list`.
* The index **must be a positive integer** 1, 2, 3, ...

Example:

* `mark 1` marks the first item in the task list as complete.

### Marking a task as incomplete: `unmark`

Marks an indicated task as incomplete.

Format: `unmark INDEX`

* The index refers to the index number shown in the task list upon using `list`.
* The index **must be a positive integer** 1, 2, 3, ...

Example:

* `unmark 1` marks the first item in the task list as incomplete.

### Listing all tasks : `list`

Shows a list of all tasks in the task list.

Format: `list`

### Searching for a task using description: `find`

Find tasks whose descriptions contain any of the given keywords.

Format: `find KEYWORDS`

Example:
* `find read`
* `find return book`

### Deleting a task : `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

* The index refers to the index number shown in the task list upon using `list`.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:

* `delete 2` deletes the second person in the task list.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Remy's data is saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous Remy home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action       | Format                                                             |                                                                                                                                                     
|--------------|--------------------------------------------------------------------|
| **Todo**     | `todo DESCRIPTION /p HIGH/MEDIUM/LOW`                              |                                                                                                  | 
| **Deadline** | `deadline DESCRIPTION /by DUEDATE /p HIGH/MEDIUM/LOW`              |
| **Event**    | `event DESCRIPTION /from STARTDATE /to ENDDATE /p HIGH/MEDIUM/LOW` |
| **Mark**     | `mark INDEX`                                                       |
| **Unmark**   | `unmark INDEX`                                                     |
| **List**     | `list`                                                             |
| **Find**     | `find KEYWORD`                                                     |
| **Delete**   | `delete INDEX`                                                     |

