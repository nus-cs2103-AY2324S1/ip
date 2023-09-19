# User Guide

Siren is an **easy-to-use desktop application to track your tasks** in an organised manner.

- [Getting Started](#getting-started)
- [Features](#features)
  - [Tasks Tracking](#tasks-tracking)
  - [Tasks Storage](#tasks-storage)
- [Usage](#usage)
  - [todo](#todo---adds-a-todo-type-of-task)
  - [deadline](#deadline---adds-a-deadline-type-of-task)
  - [event](#event---adds-an-event-type-of-task)
  - [delete](#delete---deletes-a-task-from-the-tasks-list)
  - [list](#list---list-the-tasks-in-the-tasks-list)
  - [mark](#mark---marks-a-task-in-the-task-lists)
  - [unmark](#unmark---unmarks-a-task-in-the-task-lists)
  - [find](#find---finds-tasks-in-the-tasks-list)
  - [remind](#remind---lists-tasks-that-are-due-or-upcoming)
  - [bye](#bye---exits-the-application)
- [Command Summary](#command-summary)

<!--Getting Started portion inspired by https://se-education.org/addressbook-level3/UserGuide.html --->
## Getting Started
1. Ensure that you have Java `11` or above installed
2. Download the latest `siren.jar` from [here](https://github.com/brendanneojw/ip/releases) to a desired folder
3. Open your command prompt/terminal, `cd` into the folder you put the jar file in, and type the following command to run the application: `java -jar siren.jar`
4. Start using Siren!

## Features

### Tasks Tracking

Siren tracks your tasks and displays to you whenever you want! 

You can:
1. **Add** a task of a specific type (todo, deadline or event)
2. **Delete** a task in the tasks list
3. **List** out the tasks that are in the tasks list
4. **Mark** a task to show that it has completed
5. **Unmark** a task to show that it has yet to be completed
6. **Find** tasks based on matching words
7. **Remind** yourself of which task is due or upcoming within 7 days
8. **Exit** the application and open it back whenever you feel like it

### Tasks Storage

Siren **stores** your tasks into a file in your local file system so everytime you run the application, it automatically loads it up!

Rest assured if it's your first time running the application, Siren will create the necessary directory and file!

## Usage

### `todo` - Adds a _todo_ type of task

Adds a _todo_ type of task into your tasks list and displays what you have just added.

Format: `todo DESCRIPTION`

- Adds the _todo_ task with the given `DESCRIPTION`

Example of usage: `todo assignment`

Expected outcome:
```
DINGDONG GOT IT! I've added this task:
[T][] assignment
Now you have 1 task in the list.
```
`[T]` represents it is a todo type and `[]` is to indicate if the task is marked. By default, it will not be marked.
### `deadline` - Adds a _deadline_ type of task

Adds a _deadline_ type of task into your tasks list and displays what you have just added.

Format: `deadline DESCRIPTION /by DATE TIME`

- Adds the _deadline_ task with the given `DESCRIPTION` and `DATE TIME`
- Accepted formats for `DATE TIME`
  - `DATE`: **dd-mm-yyyy** or **yyyy-mm-dd** or **dd/mm/yyyy** or **yyyy/mm/dd**
  - `TIME`: **HH:MM** or **HHMM**

Example of usage: `deadline assignment /by 2023-09-22 23:59`

Expected outcome:
```
DINGDONG GOT IT! I've added this task:
[D][] assignment (by: Sep 22 2023 11:59PM)
Now you have 2 tasks in the list.
```
- `[D]` represents it is a deadline type and `[]` is to indicate if the task is marked. By default, it will not be marked.
- The bracket after the description is the date time given by you displayed in a more readable manner!

### `event` - Adds an _event_ type of task

Adds a _deadline_ type of task into your tasks list and displays what you have just added.

Format: `event DESCRIPTION /from DATE TIME /to DATE TIME`

- Adds the _event_ task with the given `DESCRIPTION` and `DATE TIME`
- Accepted formats for `DATE TIME`
    - `DATE`: **dd-mm-yyyy** or **yyyy-mm-dd** or **dd/mm/yyyy** or **yyyy/mm/dd**
    - `TIME`: **HH:MM** or **HHMM**

Example of usage: `event training /from 2023-09-20 1800 /to 2023/09/20 20:00`

Expected outcome:
```
DINGDONG GOT IT! I've added this task:
[E][] training (from: Sep 20 2023 06:00PM to: Sep 20 2023 08:00PM)
Now you have 3 tasks in the list.
```
`[E]` represents it is an _event_ type and `[]` is to indicate if the task is marked. By default, it will not be marked.

### `delete` - Deletes a task from the tasks list

Deletes a task from your tasks list and displays what you have deleted.

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`. The index refers to the number shown in the tasks list. The index **must be in the range of the list**.

Example of usage: `delete 1`

Expected outcome:
```
ALRIGHTY! I've removed this task:
[T][] assignment
Now you have 2 tasks in the list.
```
### `list` - List the tasks in the tasks list

List out all the tasks that are in the tasks list.

Format: `list`

Expected outcome:
```
WHEET WHEET WHEET! Here are the tasks in your list:
1.[D][] assignment (by: Sep 22 2023 11:59PM)
2.[E][] training (from: Sep 20 2023 06:00PM to: Sep 20 2023 08:00PM)
```
### `mark` - Marks a task in the task lists

Marks a task in your tasks lists as completed and display what you have just marked.

Format: `mark INDEX`

- Marks the task at the specified `INDEX`. The index refers to the number shown in the tasks list. The index **must be in the range of the list**.

Example of usage: `mark 1`

Expected outcome:
```
GOTHYA! I've marked this task as done!
[D][X] assignment (by: Sep 22 2023 11:59PM)
```
`[X]` indicates the task as completed

### `unmark` - Unmarks a task in the task lists

Unmarks a task in your tasks lists as not completed and display what you have just unmarked.

Format: `unmark INDEX`

- Unmarks the task at the specified `INDEX`. The index refers to the number shown in the tasks list. The index **must be in the range of the list**.

Example of usage: `unmark 1`

Expected outcome:
```
GOTHYA! I've marked this task as done!
[D][] assignment (by: Sep 22 2023 11:59PM)
```
### `find` - Finds tasks in the tasks list

Finds tasks in your tasks list and display the results of it.

Format: `find KEYWORD`

- Finds tasks in your tasks list that matches the specified `KEYWORD`

> [!NOTE]
> Tasks with words that contains `KEYWORD` partially are also displayed.

Example of usage: `find training`

Expected outcome:
```
HOOOOOYEAAAAA! Here are the matching tasks in your list:
1.[E][] training (from: Sep 20 2023 06:00PM to: Sep 20 2023 08:00PM)
```
### `remind` - Lists tasks that are due or upcoming

> [!NOTE]
> The `remind` command automatically executes whenever you start the application and the results will be displayed at the top.

List out all the tasks that are due or upcoming within 7 days.

Format: `remind`

Expected outcome:
```
ATTENTIONNNNNNNNNNNN!!! You have task(s) that are due/upcoming within 7 days:
1.[D][] assignment (by: Sep 22 2023 11:59PM)
2.[E][] training (from: Sep 20 2023 06:00PM to: Sep 20 2023 08:00PM)
```
### `bye` - Exits the application

Exits the application.

Format: `bye`

Expected outcome:
```
WEEEWOOWEEWOO GOODBYE! Hope to see you again soon!
```
## Command Summary

 Action   | Format                                            
---------|---------------------------------------------------
 todo    | `todo DESCRIPTION`                                
 deadline | `deadline DESCRIPTION /by DATE TIME`              
 event   | `event DESCRIPTION /from DATE TIME /to DATE TIME` 
 delete  | `delete INDEX`                                    
 list    | `list`                                            
 mark    | `mark INDEX`                                      
 unmark  | `unmark INDEX`                                    
 find    | `find KEYWORD`                                    
 remind  | `remind`                                          