# User Guide

## Overview
Bob is a chatbot that helps people track different types of tasks by typing simple commands.
Just like Bob the Builder, Bob the Chatbot aims to help people fix their issues and frustrations with task tracking
through a simple, easy-to-use interface.

## Quick Start
1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest `duke.jar` from [here](https://github.com/brein62/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your personal Bob chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar duke.jar` command to
   run the application. A GUI similar to the below should appear in a few seconds. The chatbot will contain some sample
   data, as `list`ed out in the screenshot below:
   ![A screenshot of Bob the Chatbot](Ui.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing `list` and pressing Enter will list 
   out all the current tasks.
Here are some example commands you can try:
   - `todo something`: Bob will add a `todo` task called `something` into the to-do list.
   - `list`: Bob will reply with a list of all tasks in the to-do list.
   - `mark 3`: Bob will mark the 3rd task shown in the list as completed.
   - `unmark 3`: Bob will mark the 3rd task shown in the list as not completed yet.
   - `delete 2`: Bob will delete the 2nd task shown in the list.
   - `bye`: Exits the app *Bob the Chatbot*.
6. Refer to the [Features](#features) section below for more detailed information on each command.

## Features 

- Any parameters marked between square brackets are variables that you can specify.
- Any feature with an asterisk (\*) at the end do not contain any parameters. Any extra command parameters will be
  ignored by Bob.
- However, features **without** an asterisk (\*) at the end contain a specific number of parameters and any extra
  command parameters may not be interpreted correctly by Bob. Bob will not run the command and will reply with an error 
  message to remind you.

### List all tasks: `list` \*

Shows a list of all tasks in the to-do list.

**Format:** `list`

### Add a todo task: `todo`

Adds a todo task into the list with a specified description `[desc]`.

**Format:** `todo [desc]`

**Example:** `todo Study for exam`

### Add a deadline task: `deadline`

Adds a deadline task into the list with a specified description `[desc]` and deadline date `[date]`. Take note that the
`[date]` must be in the format `YYYY-MM-DDThh:mm:SS`, as in the example below.

**Format:** `deadline [desc] /by [date]`

**Example:** `deadline Study for exam /by 2023-11-25T16:00:00`

### Add an event task: `event`

Adds an event task into the list with a specified description `[desc]`, start date `[start]`, and end date `[end]`. Take
note that dates `[start]` and `[end]` must be in the format `YYYY-MM-DDThh:mm:SS`, as in the example below. Ensure that
the `[start]` date is **before** the `[end]` date, else the event will not be accepted.

**Format:** `event [desc] /from [start] /to [end]`

**Example:** `event Exam /from 2023-11-25T16:00:00 /to 2023-11-25T17:30:00`

#### Usage Notes

If the `/from` and `/to` parameters are swapped, Bob will understand and parse the command correctly. However, ensure 
that there is **exactly one** `/from` and `/to` parameter each for Bob to understand (else Bob will complain).

### Mark task as completed: `mark`

Marks a specified task as completed, based on its position/index `[pos]` in the to-do list.

**Format:** `mark [pos]`

**Example:** `mark 3` - marks the 3rd task in the list as completed

#### Usage Notes

- If the task to mark has already been marked as completed, the `mark` command will still keep the task as completed.
- `[pos]` must be a valid integer position in the list. Bob will complain if the value is not a valid integer or out of
  range.

### Mark task as not completed: `unmark`

Marks a specified task as not completed yet, based on its position/index `[pos]` in the to-do list.

**Format:** `unmark [pos]`

**Example:** `unmark 3` - marks the 3rd task in the list as not completed yet.

#### Usage Notes

- If the task to unmark is not marked as completed, the `unmark` command will still keep the task as not completed.
- `[pos]` must be a valid integer position in the list. Bob will complain if the value is not a valid integer or out of
  range.

### Find task in the to-do list: `find`

Finds all tasks in the to-do list that contains the specified substring `[msg]` in their description, then lists out the
tasks that fit the specified criteria.

**Format:** `find [msg]`

**Example:** `find something` - lists out all tasks which has descriptions that contain the term `something`.

### Clone task in the to-do list: `clone`

Clones the task in the to-do list as specified by the index/position in the to-do list `[pos]` and adds the new task to 
the end of the to-do list (just like when new tasks are added).

**Format:** `clone [pos]`

**Example:** `clone 3` - Clones the 3rd task in the to-do list and adds a new task with the same details as the 3rd
task.

#### Usage Notes

- `[pos]` must be a valid integer position in the list. Bob will complain if the value is not a valid integer or out of
  range.
- The new task created will have the **same** description, start/deadline date and end date; but will always be marked
  as not completed yet. If you want to mark the new cloned task as completed, you can use the `mark` command.

### Update a task: `update`

Updates a specific task's details based on the position in the to-do list `[pos]`. You can change a single detail (e.g.
description/start date/deadline/end date) with a single `update` command.

**Parameters:**
- `[pos]`: The position of the task to update in the to-do list.
- `[command]`: The command to perform in the to-do list.
  - **Description** *(supported by todo, deadline, event)*: `message`, `msg`, `description`
  - **Deadline/Start date** *(supported by deadline, event)*: `date1`, `from`, `/from`, `deadline`, `/deadline`, `by`, 
    `/by`
  - **End date** *(supported by event)*: `date2`, `to`, `/to`
  - **Notes:**
    - If the `[command]` is not one of these above commands, Bob will be angry and produce an error.
    - If the `[command]` does not match the type of task, Bob will warn you that the task does not support the specific
      update command. For example, `update 3 deadline 2023-10-10T12:34:56` will cause Bob to reply with an error message
      if the 3rd task is a todo.
- `[newdata]`: The new, updated data. For dates, it must be expressed in `YYYY-MM-DDThh:mm:SS` format.

**Format:** `update [pos] [command] [newdata]`

**Examples:**
- `update 2 message Hello world` changes the message of the 2nd task in the list to `Hello world`
- `update 3 deadline 2023-10-10T12:34:56` changes the deadline of the 3rd task in the list to `2023-10-10T12:34:56`.
- `update 4 /to 2023-10-10T12:34:56` changes the end date of the 4th task in the list to `2023-10-10T12:34:56`.

### Exit the app: `bye` \*

Exits the *Bob the Chatbot* app.

You may also quit the app using the *window close button* on the top right (Windows) or top left (Mac) like any other
GUI app.

**Format:** `bye`

### Saving the data

Bob the Chatbot will automatically save the tasks into a file `[JAR file location]/data/dukeGui.txt`. You don't have to
reload the tasks every time the app runs as a result.

#### Save format
For more advanced users who would like to edit the data file manually, here is the format:

`task type | completed? | description( | start/deadline datetime | end datetime)`.

**Parameters:**
- *Task type*: Can be either `T` (todo), `D` (deadline), or `E` (event).
- *Completed?*: `1` if task is completed, `0` is task is not completed.
- *Description*: The description/message of the task.
- *Start/Deadline*: The deadline (deadline tasks) or start date (event tasks) for the task, in `YYYY-MM-DD hh:mm:SS`
  format.
- *End*: The end date for event tasks, in `YYYY-MM-DD hh:mm:SS` format.

**Examples:**
- Todo: `T | 1 | Todo`
- Deadline: `D | 0 | Deadline | 2023-10-10T12:34:56`
- Event: `E | 0 | Event | 2023-10-10T12:34:56 | 2023-10-10T14:00`

----

## FAQ

**Q:** How do I transfer my tasks to another computer?

**A:** Install the app in the other computer and replace the content of the data file in 
`[JAR file location]/data/dukeGui.txt` with the data from your previous data file.

----

## Known issues

1. The app has not been configured to be resized. If you resize the window containing the app, the app may not look as
   expected. However, the app will function as normal, just with the components weirdly placed. The chat window will
   still be the same size. It is best to use the app in its optimal screen resolution.

----

## Command Summary
1. List - `list`
2. Add todo - `todo [desc]` (e.g. `todo Study for exam`)                                                                       
3. Add deadline - `deadline [desc] /by [date]`<br/>(e.g. `deadline Assignment /by 2023-10-10T23:59:59`)                            
4. Add event - `event [desc] /from [start] /to [end]`<br/>(e.g. `event Exam /from 2023-10-05T11:00:00 /to 2023-10-05T13:00:00`) 
5. Mark - `mark [pos]` (e.g. `mark 3`)                                                                                     
6. Unmark - `unmark [pos]` (e.g. `unmark 4`)                                                                               
7. Find - `find [msg]` (e.g. `find Tutorial`)                                                                             
8. Clone - `clone [pos]` (e.g. `clone 3`)                                                                                  
9. Update - `update [pos] [command] [newdata]`<br/>(e.g. `update 2 msg Hello world!`)                                        
10. Exit - `bye`                                                                                                            
