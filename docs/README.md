# User Guide

## Table of Contents

- [Getting Started](#Getting-Started)
- [Features](#Features)
- [Summary](#Summary)

## Getting Started

1. Ensure you have Java 11 Installed
2. Download Jar file
3. Run java -jar duke.jar to begin
4. Duke ChatBot should appear, happy chatting!

## Features 

### Add a task: `todo`, `deadline`, `event`

Add a new task into your task list. 

Example of usage: 

`todo taskDescription`

`deadline taskDescription /by 630am 29june`

`deadline taskDescription /by 06:30:00 2023-04-24`

`event taskDescription /from 630am 29june /to 730am 29june`

`event taskDescription /from 06:30:00 2015-04-24 /to 07:30:00 2023-06-29`



Expected outcome:

`Got it. I've added this task:`

`[T][ ] taskDescription`

`Now you have 1 tasks in the list`


`Got it. I've added this task:`

`[D][ ] taskDescription (by: 06:30 2023-06-29)`

`Now you have 1 tasks in the list`


`Got it. I've added this task:`

`[E][ ] taskDescription (by: 06:30 2023-06-29 to: 07:30 2023-06-29)`

`Now you have 1 tasks in the list`


Description of the outcome:

Adds task to list

[T] denotes task, [D] denotes deadline, [E] denotes event

Deadline and Event accepts two timeformats as specified above.


### Delete a task: delete

Example of usage:

`delete 1`

Expected outcome:

`Noted. I've removed this task:`

`[T][ ] taskDescription`

`Now you have 0 tasks in the list.`


Description of the outcome:

Deletes task in list at specified position.

Note that we can only delete from the list.




### Mark and unmark a task: mark, unmark
Example of usage:

`mark 1`

`unmark 1`


Expected outcome:

`Nice! I've marked this task as done:`

`[T][X] taskDescription`

`Nice! I've marked this task as not done yet:`

`[T][ ] taskDescription`


Description of the outcome:

Mark task in list at specified position.

Note that we can only mark and unmark from the list.


### List all tasks: list

Example of usage:

`list`


Expected outcome:

Here are the task in your list:

1. [T][ ] taskDescription1

2. [T][ ] taskDescription2


Description of the outcome:

Lists all task in task list.


### Find a task: find

Example of usage:

`find taskDescription`


Expected outcome:

`1. [T][ ] taskDescription`


Description of the outcome:

Finds all tasks containing an exact match for keyword provided.




### Archvie and Unarchive a task: archive, unarchive

Example of usage:

`archive 1`

`unarchive 1`


Expected outcome:

`Stored task into archive folder (archive)`

`[T][ ] taskDescription`

`Retrieved task from folder (unarchive)`

`[T][ ] taskDescription`


Description of the outcome:

Archive task into a list for later.

Unarchive task from archive to retrieve the task.




### List all tasks in archive: archivelist

Example of usage:

`archivelist`

Expected outcome:

`Here are the task in your archive list:`

`[T][ ] taskDescription1`

`[T][ ] taskDescription2`


Description of the outcome:

Lists all tasks in the archive.




### Delete task in archive: archivedelete

Example of usage:

`archivedelete 1`


Expected outcome:

Noted. I've removed this task:

[T][ ] taskDescription

Now you have 1 tasks in the archive.


Description of the outcome:

Deletes task in list at specified position.

Note that we can only delete from the list.




### Exit system: bye

Example of usage:

`bye`


Expected outcome:
``

Description of the outcome:

Exits the system.




### Summary
Action | Format
------ | -------
todo | todo taskDescription
deadline | deadline taskDescription /by 630am 29june
event | event taskDescription /from 630am 29june /to 9am 30june
delete | delete idx
mark | mark idx
unmark | unmark idx
list | list
find | find keyword
archive | archive idx
unarchive | unarchive idx
archivelist | archivelist
archivedelete | archivedelete idx
bye | bye

