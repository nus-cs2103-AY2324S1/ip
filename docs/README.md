# Rat User Guide

Welcome to the user guide for `Rat`. 

Rat is your personal task manager that your tasks and notes. 

`Rat` is built on the ~~latest~~ version of everyone's _favourite_ programming language, Java 11! 

## Summary
- Announcements
- Getting Started
- Features
- Glossary

## Announcements
### New in v1.1
- Note-taking functionality
- Improved GUI

## Getting Started
`Rat` is a GUI chatbot that runs locally on your machine. 
### Prerequisites
- Java 11 installed
- JavaFX libraries installed

Follow the guide [here](https://nus-cs2103-ay2324s1.github.io/website/admin/programmingLanguages.html) to find out how 
to install the correct version of Java and JavaFX.

### Storage Management
`Rat` will store your tasks and notes in a local file with the pathname `data/rat.txt`. This folder will automatically 
be generated in the root folder where `Rat-1.1.jar` is installed.
> You may want to move `Rat-1.1.jar` to another location in your computer for cleaner management of your files.

### Installation
Install the latest `Rat` release by clicking 
[here](https://github.com/keaganpzh/ip/releases/download/A-BetterGui/Rat-1.1.jar).

### Running the Application
Open a new Terminal instance at folder in which your download is located. Then run the command:
```shell
java -jar Rat-1.1.jar
```
The application should launch in a separate window, like this:
![Ui](Ui.png)
>Note: this image shows what the UI looks like after some commands have been inputted.

## Definitions
### Types of Tasks
| Task Name | Name | Completion | Start Time | End Time | Remarks                                                 |
|-----------|:----:|:----------:|:----------:|:--------:|---------------------------------------------------------|
| To do     |  ✅   |     ✅      |            |          | A todo task has only a name                             |   
| Deadline  |  ✅   |     ✅      |     ✅      |          | A deadline task has both a name and a time              |   
| Event     |  ✅   |     ✅      |     ✅      |    ✅     | An event task has a name, a start time, and an end time |  
### Notes
Notes consist of only its body text.
### Formatting Date and Time
#### Input Date and Time
`Rat` takes in dates and times in the format `DD/MM/YYYY HH:mm`.

e.g. `01/01/2023 23:59`
> **Warning:** It is not possible to enter a date without the time, nor is it possible to enter a time without the date.

#### Response Date and Time
`Rat` echoes the input date and time in the format `EEE, d MMM yyyy HH:mm`

e.g `Sun, 1 Jan 2023 23:59`

## Features 
Rat supports both task management and note-taking functionalities.

### Display all commands
This displays all the commands supported by `Rat`, together with how to use them.
#### Usage
```shell
help
```
#### Expected Outcome
```text
Hello! I'm Rat, your personal task manager.
Here are the commands you can use:

help: show this list of commands
list: list all tasks
mark <index>: mark task at <index> as done
unmark <index>: mark task at <index> as not done
todo <name>: add a todo task with <name>
deadline <name> /by <deadline>: add a deadline task with <name> and <deadline>
event <name> /from <start> /to <end>: add an event task with <name>, <start> and <end>
delete <index>: delete task at <index>
delete all: delete all tasks
find <keyword>: find all tasks with <keyword>
note /add <body>: add a note with <body>
note /delete <index>: delete note at <index>
notes: list all notes
save: save all tasks and notes
bye: exit the program

built by @keaganpzh
```

### `list` all tasks
This displays all tasks tracked by the task manager at any point in time.
#### Usage
```text
list
```
#### Expected Outcome
If there are no tasks tracked by `Rat`:
```text
You have no tasks in the list.
```
If there are tasks tracked by `Rat`:
```text
Here are the tasks in your list:
{TASK}
{TASK}
...

You have {NUM_TASKS} in the list.
```
`{TASK}` => A particular task being tracked by `Rat`

`{NUM_TASKS}` => The total number of tasks being tracked.

### Add a `todo` task
This adds a todo task to the task manager.
#### Usage
```shell
todo {TASK_NAME}
```
`{TASK_NAME}` => The name of the to do task.
#### Examples
```shell
todo eat lunch
```
```shell
todo call my mum
```
#### Expected Outcome
The response to a successful command will be:
```text
Got it. I've added this to do:
[T][] {TASK_NAME}
Now you have {NUM_TASKS} in the list.
```

`{TASK_NAME}` => The name of the task added.

`{NUM_TASKS}` => The number of tasks in the list.

### Add a `deadline` task
This adds a deadline task to the task manager.
#### Usage
```shell
deadline {TASK_NAME} /by {DEADLINE_TIME}
```
`{TASK_NAME}` => The name of the deadline task.

`{DEADLINE_TIME}` => The date and time of the due date of the task, formatted for input.
#### Example
```text
deadline CS2103T Quiz 5 /by 20/09/2023 23:59
```
#### Expected Outcome
The response to a successful command will be:
```text
Got it. I've added this Deadline:
[D][] {TASK_NAME} (by: {RESPONSE_TIME})
Now you have {NUM_TASKS} in the list.
```

`{TASK_NAME}` => The name of the task added.

`{NUM_TASKS}` => The number of tasks in the list.

`{RESPONSE_TIME}` => The date and time of the due date of the task, formatted for response.

### Add an `event` task
This adds an event task to the task manager.
#### Usage
```shell
event {TASK_NAME} /from {START_TIME} /to {END_TIME}
```
`{TASK_NAME}` => The name of the event task.

`{START_TIME}` => The date and time of the start of the task, formatted for input.

`{END_TIME}` => The date and time of the end of the task, formatted for input.
#### Example
```text
event CS2103T Group Meeting /from 18/09/2023 14:00 /to 18/09/2023 15:30
```
#### Expected Outcome
The response to a successful command will be:
```text
Got it. I've added this Event:
[E][] {TASK_NAME} (from: {RESPONSE_START} to: {RESPONSE_END})
Now you have {NUM_TASKS} in the list.
```

`{TASK_NAME}` => The name of the task added.

`{NUM_TASKS}` => The number of tasks in the list.

`{RESPONSE_START}` => The date and time of the start of the task, formatted for response.

`{RESPONSE_END}` => The date and time of the end of the task, formatted for response.


