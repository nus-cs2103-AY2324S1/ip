# Kevin's User Guide

Kevin is a desktop application to manage your daily task, optimized for use through a text-based Command Line Interface 
(CLI) while retaining the advantages of a visual Graphical User Interface (GUI).

## Table of Contents

<!-- TOC -->

  * [Quick Start](#quick-start)
  * [Features](#features-)
    * [Adding a Todo task : `todo`](#adding-a-todo-task--todo)
    * [Adding a Deadline task : `deadline`](#adding-a-deadline-task--deadline)
    * [Adding a Event task : `event`](#adding-a-event-task--event)
    * [Listing All the Tasks : `list`](#listing-all-the-tasks--list)
    * [Marking Your Task Finished : `mark`](#marking-your-task-finished--mark)
    * [Marking Your Task Not Finished : `unmark`](#marking-your-task-not-finished--unmark)
    * [Deleting Your Task : `delete`](#deleting-your-task--delete)
    * [Filtering Your Tasks : `find`](#filtering-your-tasks--find)
    * [Exiting Kevin : `bye`](#exiting-kevin--bye)
    * [Viewing Help : `help`](#viewing-help--help)
  * [Command Summary](#command-summary)
<!-- TOC -->

## Quick Start

1. Make sure you have Java 11 installed in your computer.
2. Download the latest `kevin.jar` from [here](https://github.com/aliciamichellew/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your Kevin chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, 
and use the `java -jar kevin.jar` command to run the application.
5. A GUI similar to the below should appear in a few seconds. 
![](/Users/aliciamichellewang/Documents/Y3S1/CS2103T/ip/src/main/resources/images/startPage.png)
6. Type the command in the command box and press Send to execute it. Refer to the [Features](#features-) section to see the details 
of the command.

## Features 

### Adding a Todo task : `todo`

Adds a ToDo task to your TaskList.

Format: `todo DESCRIPTION`
- `DESCRIPTION` is the description of the todo task.

Example:
- `todo borrow book`: adds a Todo with borrow book as its description

### Adding a Deadline task : `deadline`

Adds a Deadline task to your TaskList.

Format: `deadline DESCRIPTION /by DATETIME`
- `DESCRIPTION` is the description of the todo task.
- `DATETIME` is the date of the deadline. 
> :memo: `DATETIME` must be written in the format of `d-MM-yyyy HHmm` or `dd-MM-yyyy HHmm`

Example:
- `deadline CS2103T iP /by 22/09/2023 2359`: adds a Deadline with CS2103T iP as its description and 22/09/2023 2359 as the deadline date.

### Adding a Event task : `event`

Adds a Event task to your TaskList.

Format: `event DESCRIPTION /from STARTTIME /to ENDTIME`
- `DESCRIPTION` is the description of the todo task.
- `STARTTIME` is the starting time of the event. 
- `ENDTIME` is the ending time of the event.

> [!NOTE]
> `STARTTIME` and `ENDTIME` can be anything, it does not have to be a date.

Example:
- `event project meeting /from Mon 2pm /to 4pm`: adds an Event with project meeting as its description,
Mon 2pm as the starting time of the event and 4pm as the ending time of the event.

### Listing All the Tasks : `list`

Displays all the tasks in your TaskList.

Format: `list`

### Marking Your Task Finished : `mark`

Marks the task you specified to be finished. 

Format: `mark INDEXNUMBER`
- `INDEXNUMBER` is the index of the task you want to mark as done. 

Example:
- `mark 1`: marks the task with index 1 as finished.

### Marking Your Task Not Finished : `unmark`

Marks the task you specified to be not finished.

Format: `unmark INDEXNUMBER`
- `INDEXNUMBER` is the index of the task you want to mark as not done.

Example:
- `unmark 1`: marks the task with index 1 as not finished.

### Deleting Your Task : `delete`

Deletes the task you specified from your taskList.

Format: `delete INDEXNUMBER`
- `INDEXNUMBER` is the index of the task you want to delete.

Example:
- `delete 1`: delete the task with index 1 from your task list.

> :warning: This action is irreversible! Be careful on what you are deleting!

### Filtering Your Tasks : `find`

Filters your tasks based on the keyword you specified.

Format: `find KEYWORD`
- `KEYWORD` is the keyword you want to use for filtering

Example:
- `find book`: filters all your task that containing the book keyword and displays them.

### Exiting Kevin : `bye`

Say bye to Kevin. 

Format: `bye`

> :bulb: Don't worry all your tasks will be saved!

### Viewing Help : `help`

Shows all the commands available in Kevin.

Format: `help`

## Command Summary
| Action       | Format                                          |
|--------------|-------------------------------------------------|
| Add ToDo     | `todo DESCRIPTION`                              |
| Add Deadline | `deadline DESCRIPTION /by DATETIME`             | 
| Add Event    | `event DESCRIPTION /from STARTTIME /to ENDTIME` |
| List         | `list`                                          |
| Mark         | `mark INDEXNUMBER`                              |
| Unmark       | `unmark INDEXNUMBER`                            |
| Delete       | `delete INDEXNUMBER`                            |
| Find         | `find KEYWORD`                                  |
| Exit         | `bye`                                           |
| Help         | `help`                                          |


