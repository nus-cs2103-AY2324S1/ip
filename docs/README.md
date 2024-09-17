# BenBen
# User Guide

## Quick Start
BenBen is an interactive chatbot that helps you to keep track of your tasks!
There are three types of tasks that BenBen uses
- `Todo`which only has a description
- `Deadline` which has a description and deadline date
- `Event` which has a description, start time and end time

You can create, delete, mark, search and sort the tasks by typing the corresponding commands into the text box and hit return.

## Command Summary

| Command                                                               | Description                                                                                           |
|-----------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
| `todo [description]`                                                  | Create a new todo task with the given description                                                     |
| `deadline [description] /by [yyyy-mm-dd]`                             | Create a new deadline task with the given description and deadline date                               |
 | `event [description] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]` | Create a new deadline task with the given description start time and end time                         |
 | `list`                                                                | List all the tasks saved in the Task List and their status and print to screen                        |
 | `mark [index]`                                                        | Mark the task at a the index as done                                                                  |
| `unmark [index]`                                                      | Mark the task at the index as not done                                                                |
 | `delete [index]`                                                      | Delete the task at a given index                                                                      |
 | `sort [optional]`                                                     | Sort all or a particular type of task in the list. Optional keyword : `todo`, `deadline`, or `event`. |
 | `find [keyword]`                                                      | Find all tasks with the given keyword                                                                 |


## Features

## Add new tasks

Add new tasks to the existing list. The new task has to be of type Todo, Deadline or Event.

### Usage
### 1. Add a new todo task

#### Format :`todo [description]` 

create a new Todo task with the given description. The instruction has to follow the format `todo deascription`. The description is a string of length greater than 0.

Example of usage:
`todo watch lecture`

Expected outcome:

The task is printed with the relevant information and the number of tasks in the list.

```
Got it. I've added this task:
[T] [ ] watch lecture
Now you have 1 tasks in the list.


```
### 2. Add a new deadline task
#### Format `deadline [description] /by [yyyy-mm-dd]`

create a new Deadline task with the given description and deadline in format of yyyy/mm/dd is the date which the task needs to be completed by. The description is a string of length greater than 0.

Example of usage:
`deadline quiz /by 2023-09-30`

Expected outcome:

The task is printed with the relevant information and the number of tasks in the list.

```
Got it. I've added this task:
[D] [ ] quiz (by: SEPTEMBER 30 2023)
Now you have 2 tasks in the list.

```

### 3. Add a new event task
#### Format `event [description] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]` 

create a new Event task with the given description, start time and end time. yyyy/mm/dd hh:mm is the date and time in hours and minutes of the start and end time. The description is a string of length greater than 0. Note that the start time has to be later than the end time.

Example of usage:
`event group meeting /from 2023-09-24 10:00 /to 2023-09-24 12:00`

Expected outcome:

The task is printed with the relevant information and the number of tasks in the list.

```
Got it. I've added this task:
[E] [ ] group meeting (from: SEPTEMBER 24 2023 10:0 to: SEPTEMBER 24 2023 12:0)
Now you have 3 tasks in the list.

```

## List

List out all the features in the list with their status.

## Usage

#### Format `list` 

List all the tasks saved in the Task List and their status and print to screen

Example of usage:`list`

Expected outcome:

Print all the tasks to screen according to the order that they are being added. Tasks that are done will have a status of `[X]` while tasks that are not done has a status of `[ ]`.

```
Here are the tasks in your list:
1.[T] [ ] watch lecture
2.[D] [ ] quiz (by: SEPTEMBER 30 2023)
3.[E] [ ] group meeting (from: SEPTEMBER 24 2023 10:0 to: SEPTEMBER 24 2023 12:0)
```

## Manage status of the tasks

Manage status of the tasks by marking them as done or undone.

## Usage
### 1. Mark a task as done
#### Format `mark [index]` 

Mark the task at a given index, as seen from the output from `list`, as done. Index has to be a positive integer smaller than the length of the list.

Example of usage:`mark 1`

Expected outcome:

Marks the first task in the list as done and changes its status displayed.

```
OK, I've marked this task as done:
    [T] [X] watch lecture
```
### 2. Mark a task as not done
#### Format `unmark [index]` 

Mark the task at a given index, as seen from the output from `list`, as NOT done. Index has to be a positive integer smaller than the length of the list.

Example of usage: `unmark 1`

Expected outcome:

Marks the first task in the list as not done and changes its status displayed.

```
OK, I've marked this task as not done yet:
    [T] [ ] watch lecture
```
## Delete a task from the list

delete a task from the list and the storage

## Usage

#### Format`delete [index]` 

Remove the task at a given index, as seen from the output from `list`. Index has to be a positive integer smaller than the length of the list.

Example of usage: `list`

Expected outcome:

Print a confirmation message with the information of the deleted task and the number of remaining tasks.

```
Noted. I've removed this task:
[T] [ ] watch lecture
Now you have 2 tasks in the list.
```

## Sort the tasks

Sort the tasks in the list in a particular order

## Usage
#### Format `sort [optional]`
The optional keyword can be `todo`, `deadline`, or `event`.

This function sorts all tasks in the list, or a particular type of task in their respective orders. When sorting all tasks and Todo tasks, alphabetical order is followed. Deadline and event will be sorted in chronological order by their deadline date and event start time. Note that this does not change the order of tasks saved.

### 1. Sort all tasks
Example of usage: `sort`

Expected outcome:

Print out all the tasks sorted in alphabetical order. 

```
Here are the sorted tasks in your list:Sorted all Tasks by description
1.[E] [ ] group meeting (from: SEPTEMBER 24 2023 10:0 to: SEPTEMBER 24 2023 12:0)
2.[T] [ ] mid-semester survey
3.[D] [ ] project proposal (by: OCTOBER 1 2023)
4.[D] [ ] quiz (by: SEPTEMBER 30 2023)
5.[E] [ ] seminar (from: OCTOBER 2 2023 14:0 to: OCTOBER 2 2023 18:0)
6.[T] [ ] watch lecture
```
### 2. Sort all todo tasks

Example of usage: `sort todo`

Expected outcome:

Print out all the Todo tasks sorted in alphabetical order.

```
Here are the sorted tasks in your list:Sorted Todos by description
1.[T] [ ] mid-semester survey
2.[T] [ ] watch lecture
```
### 3. Sort all deadline tasks
Example of usage: `sort deadline`

Expected outcome:

Print out all the Deadline tasks sorted in chronological order by deadline date.

```
Here are the sorted tasks in your list:Sorted Deadlines by deadline
1.[D] [ ] project proposal (by: OCTOBER 1 2023)
2.[D] [ ] quiz (by: SEPTEMBER 30 2023)
```
### 4. Sort all event tasks
Example of usage: `sort event`

Expected outcome:

Print out all the Event tasks sorted in chronological order by event start date.

```
Here are the sorted tasks in your list:Sorted Events by start date
1.[E] [ ] seminar (from: OCTOBER 2 2023 14:0 to: OCTOBER 2 2023 18:0)
2.[E] [ ] group meeting (from: SEPTEMBER 24 2023 10:0 to: SEPTEMBER 24 2023 12:0)
```
## Search for tasks with a keyword

Serach for all tasks in the list that has a particular keyword.

## Usage

### Format: `find [keyword]` 

Find all tasks with the given keyword.

Example of usage:`find group`

Expected outcome:

Print all the tasks that contains the given keyword in their descriptions.
```
Here are the matching tasks in your list:
1.[E] [ ] group meeting (from: SEPTEMBER 24 2023 10:0 to: SEPTEMBER 24 2023 12:0)
2.[T] [ ] group formation

```
If there is no matching tasks an error message will be printed instead. 
```
There is no matching tasks! Try another keyword?

```
## Save the data
The list of tasks are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Edit the data file
The data of tasks is saved automatically as a txt file tasks.txt. Users are advised not to directly edit the file as it might cause corruption of the data.
