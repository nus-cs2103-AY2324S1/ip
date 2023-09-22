# DukeBot: User Guide

DukeBot is a desktop app for managing tasks, optimized for use via a Command 
Line Interface, whilst maintaining a user-friendly Graphical User Interface (GUI). If you
are an experienced typer, DukeBot will be just for you!

![Screenshot of the GUI that the user should see when first
opening DukeBot](https://github.com/seraphimstreets/ip/blob/master/docs/Ui.png?raw=true)


## Quickstart

1. Ensure that you have Java 11 installed on your computer. 
2. Download the Duke.jar from the [latest releases](https://github.com/seraphimstreets/ip/releases). (v0.3 recommended)
3. Copy the file into the folder you want to use as the home folder for DukeBot 
and run the command `java -jar Duke.jar` in that directory.
4. A GUI should appear in a few seconds, and our Duke agent will greet you.

Note: A `data` folder will be created in that directory if it does not exist to store
Duke-related data. 

## Features

Duke classifies tasks into 3 categories: Todo, Deadline and Event. You can add, delete
and list tasks by typing commands in the text area at the bottom of the window and pressing
'Enter' to submit, after which you will receive a reply from Duke either confirming the action
has been taken or that there was an error in parsing the command. 

### Adding Todo tasks: `todo`

Adds a todo task to the task list. 

**Format:** `todo [task description]` 

**Example:**

`todo feed cat` adds a todo task with description "feed cat" into the task list.

### Adding Deadline tasks: `deadline`

Adds a deadline task to the task list. For the `datetime` bracket, only certain
datetime formats will be recognized by DukeBot. (For more information,
view [Accepted Datetime Formats](#accepted-datetime-formats) section)

**Format:** `deadline [task description] /by [datetime]`

**Example:**

`deadline submit homework /by 18/8/2023 1800` adds a deadline task 
with description "submit homework" into the task list, with a deadline of 18/8/2023 at 6pm. 

### Adding Event tasks: `event`

Adds a event task to the task list. For the `datetime` brackets, only certain
datetime formats will be recognized by DukeBot. (For more information, view 
[Accepted Datetime Formats](#accepted-datetime-formats) section)

**Format:** `event [task description] /from [datetime] /to [datetime] `

**Example:**

`event open house /from 2023-08-08 10:00 /to 2023-08-08 15:00` adds 
an event with description "open house" into the task list, which occurs from 2023/08/08 10am to 3pm.

### Listing tasks: `list`

Lists all tasks in the task list. A task has the following format when listed:

`1. [E][X] code review (from: 2023-08-08 17:00 to: 2023-08-08 18:00) [Prority: High]`

The number `1.` indicates its position in the list. The first box indicates the task type
(either `[T]`, `[D]` or `[E]`), the second box indicates whether it is marked as completed (`[X]` if marked, `[]` if not).
followed by the task description. This is followed by a parentheses block if there are the associated
datetimes of the task (only applicable for event and deadlines). This is followed by square bracket block 
indicating the task is of high priority if it has been marked as such. 

### Removing tasks: `delete`
Deletes a task from the task list, based on their 1-based index. 

**Format:** `delete [task_no]` where `task_no` is the 1-based index of the 
task in the list. 


### Marking tasks: `mark`
Marks a task on the task list as completed.

**Format:** `mark [task_no]` where `task_no` is the 1-based index of the
task in the list. 

### Unmarking tasks: `unmark`
Removes a competed mark on a task. (No effect if task has not been marke)

**Format:** `unmark [task_no]` where `task_no` is the 1-based index of the
task in the list.

### Finding tasks: `find`
Returns a list of all tasks whose description contains a given substring.

**Format:** `find [substring]`. 

**Example:** `find EXCO` returns all entries with the substring "EXCO" 
in their task description. 

![Screenshot of the the user using the find command to find all tasks
with a given substring](https://github.com/seraphimstreets/ip/blob/master/docs/findCommand.png?raw=true)

### Setting a task's priority: `priority`
Set a task's priority as either high (1) or normal (0). If it is set to high, 
the task will be marked with a `[Priority:High]` box when listed, otherwise, it will
be displayed normally. `priority_value` currently only takes a value of either 0 or 1.

**Format:** `priority [task_no] [priority_value]` where `task_no` is the 1-based index of the
task in the list.

**Example:** `priority 2 1` sets the priority of the 2nd item in the list as high, 
while `priority 3 0` sets the priority of the 3rd itme in the list as normal.


### Saving tasks
All data is saved after any command that changes a task or the task list 
has been successfully carried out. Manual saving is not needed.

### Exiting the program: `bye`
The command `bye` will prompt the program to exit and the GUI to close. 

### Accepted Datetime Formats

Dukebot accepts the following datetime formats for input:

`dd/mm/yyyy HHmm`
`yyyy-mm-dd HH:mm`

where 'mm' and 'dd' may only require 1 character if the number if a 
single digit (eg. `8/8/2023`) and HH and mm are both optional. However,
Dukebot will then represent the time as 00:00. 

Dukebot will always output the datetime as in the form 
`yyyy-mm-dd HH:mm`. 