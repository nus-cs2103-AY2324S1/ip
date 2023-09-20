# Rat User Guide

Welcome to the user guide for `Rat`. 

Rat is your personal task manager that your tasks and notes. 

`Rat` is built on the ~~latest~~ version of everyone's _favourite_ programming language, Java 11! 

## Quick Links
#### [Announcements](#announcements)
#### [Getting Started](#getting-started)
#### [Definitions](#definitions)
#### [Features](#features-)
- [Display all commands](#display-all-commands)
- [List all tasks](#list-all-tasks)
- [Add a todo task](#add-a-todo-task)
- [Add a deadline task](#add-a-deadline-task)
- [Add an event task](#add-an-event-task)
- [Delete a task](#delete-a-task)
- [Mark a task as done](#mark-a-task-as-done)
- [Unmark a task as done](#unmark-a-task-as-done)
- [Find a task](#find-a-task)
- [Add a note](#add-a-note)
- [Delete a note](#delete-a-note)
- [Save all data](#save-all-data)
- [Exit the program](#exit-the-program)

#### [Summary](#summary)

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
|-----------|------|------------|------------|----------|---------------------------------------------------------|
| To do     | x    | x          |            |          | A todo task has only a name                             |   
| Deadline  | x    | x          | x          |          | A deadline task has both a name and a time              |   
| Event     | x    | x          | x          | x        | An event task has a name, a start time, and an end time | 
A task is represented as `[type][done] name`. 

`[type]` denotes the type of task, i.e. `[T]` for `todo`, `[D]` for `deadline`, and `[E]` for `event`.

`[done]` denotes the completion status of the task, i.e. `[]` for not done, and `[X]` for done.

`name` denotes the name of the task.

### Notes
Notes consist of only its body text.

A note is represented as `[Note] body`.

`body` denotes the body text of the note.

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

### `delete` a task
This deletes a task from the task manager, specified by its index in the list. The index is decided
by order of addition; i.e. the first task added will have index `1`, and so on. `delete` also works
to delete all tasks, by specifying the index as `all`.
#### Usage
```text
delete {TASK_INDEX}
```
`{TASK_INDEX}` => The index of the task in range `1` to `n`, where `n` is the number of tasks, or 
`all` to delete all tasks
#### Example
```text
delete 1
```
```text
delete all
```
#### Expected Outcome
The response to a successful deletion of `1` task will be:
```text
Noted. I removed this task:
{TASK}
Now you have {NUM_TASKS} in the list.
```
`{TASK}` => The task that was deleted.

`{NUM_TASKS}` => The number of tasks remaining in the list.

The response to the successful deletion of `all` tasks will be:
```text
Noted. I've removed all tasks.
```

### `mark` a task as done
This sets a task as completed, specified by its index in the list.
#### Usage
```text
mark {TASK_INDEX}
```
`{TASK_INDEX}` => The index of the task in range `1` to `n`, where `n` is the number of tasks.
#### Example
```text
mark 1
```
#### Expected Outcome
The response to a successful command will be:
```text
Nice! I marked this task as done: {TASK}
```
`{TASK}` => The task that was marked as done. Note the `[x]` identifier in the task.


### `unmark` a task as done
This sets a task as not done, specified by its index in the list.
#### Usage
```text
unmark {TASK_INDEX}
```
`{TASK_INDEX}` => The index of the task in range `1` to `n`, where `n` is the number of tasks.
#### Example
```text
unmark 1
```
#### Expected Outcome
The response to a successful command will be:
```text
Ok, I've marked this task as not done yet: {TASK}
```
`{TASK}` => The task that was marked as done. Note the `[]` identifier in the task.


### `find` a task
This searches the task manager for a task using a specified `keyword`. Returns all tasks that
contain the `keyword` in its name.
#### Usage
```text
find {KEYWORD}
```
`{KEYWORD}` => The search term to find tasks with names containing it.
#### Example
```text
find meeting
```
#### Expected Outcome
If there are task(s) matching the `keyword`, the response will be:
```text
Here are the matching tasks in your list:
1. {TASK}
...
```
`{TASK}` => A particular task that contains the `keyword`

If there are no tasks matching the `keyword`, the response will be:
```text
No tasks found matching the keyword '{KEYWORD}'
```
`{KEYWORD}` => The keyword specified in the command.

### Add a `note`
This adds a note to be tracked by `Rat`.
#### Usage
```text
note /add {NOTE_BODY}
```
`{NOTE_BODY}` => The body text of the note to be added.
#### Example
```text
note /add The mitochondria is the powerhouse of the cell.
```
#### Expected Outcome
The response to a successful command will be:
```text
Got it. I've added this note:
{NOTE}
Now you have {NUM_NOTES} in the list.
```
`{NOTE}` => The note that was added to the list.
`{NUM_NOTES}` => The number of notes in the list.

### Delete a `note`
This deletes a note in the list, specified by its index in the list. The index is decided
by order of addition; i.e. the first note added will have index `1`, and so on. This also works
to delete all notes, by specifying the index as `all`.
#### Usage
```text
note /delete {NOTE_INDEX}
```
`{NOTE_INDEX}` => The index of the note to be deleted in range `1` to `n`, 
where `n` is the number of notes in the list, or `all` to delete all notes.
#### Example 
```text
note /delete 1
```
```text
note /delete all
```
#### Expected Outcome:
The response to a successful deletion of `1` note will be:
```text
Noted. I've removed this note:
{NOTE}
Now you have {NUM_NOTES} in the list.
```
`{NOTE}` => The note that was deleted.

`{NUM_NOTES}` => The number of notes remaining in the list.

The response to the successful deletion of `all` notes will be:
```text
Ok. I've removed all notes.
```
### List all `notes`
This displays all notes being tracked by `Rat`.
#### Usage
```text
notes
```
#### Expected Outcome
The response to a successful command will be:
```text
Here are the notes in your list:
{NOTE}
...

Now you have {NUM_NOTES} in your list.
```
`{NOTE}` => A particular note in the list.

`{NUM_NOTES}` => The number of notes in the list.

### `save` all data
This saves all current tasks and notes to local storage. The current tracked tasks and notes
will be written into the file `data/rat.txt`. 
#### Usage
```text
save
```
#### Expected Outcome
```text
Tasks saved to file. Notes saved to file.
```

### Exit the Program
This saves all tracked tasks and notes to local storage, the exits the program.
No response from `Rat` will be given.
#### Usage
```text
bye
```

## Summary
Here is a summary of all commands:

| Command        | Description                    |
|----------------|--------------------------------|
| `todo`         | Add a todo task                |
| `deadline`     | Add a deadline task            |
| `event`        | Add an event task              |
| `delete`       | Delete task(s)                 |
| `mark`         | Mark a task as completed       |
| `unmark`       | Unmark a task as completed     |
| `find`         | Finds tasks matching a keyword |
| `note /add`    | Add a note                     |
| `note /delete` | Delete note(s)                 |
| `save`         | Saves data to local storage    |
| `bye`          | Saves data and exits program   |

 
 
