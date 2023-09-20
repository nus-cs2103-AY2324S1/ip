# Kiera
***
## Table of Contents
* [Quick Start](#quick-start)
* [User Guide](#user-guide)
  * [Features](#features)
    * [Adding a task](#adding-a-task)
      * [Adding a todo](#adding-a-todo-todo)
      * [Adding a deadline](#adding-a-deadline-deadline)
      * [Adding an event](#adding-an-event-event)
    * [Deleting a task](#deleting-a-task-delete)
    * [Listing tasks](#listing-tasks-list)
    * [Filtering tasks](#filtering-tasks)
      * [Filtering tasks by date](#filtering-tasks-by-date-deadline-date--event-date)
      * [Filtering tasks for today](#filtering-tasks-for-today-deadline-today--event-today)
      * [Filtering tasks by keyword](#filtering-tasks-by-keyword-find)
    * [Marking a task as done](#marking-task-as-done-mark)
    * [Marking a task as undone](#marking-task-as-undone-unmark)
    * [Sorting tasks](#sorting-tasks)
      * [Sorting tasks by date](#sorting-tasks-by-date-sort-deadline-date--sort-event-date)
      * [Sorting events by time](#sorting-events-by-time-sort-event-time)

## Guick Start
1. Ensure that Java 11 or above installed.
2. Download the latest `keira.jar` from [here](https://github.com/ylyma/ip/releases/tag/v0.3).
3. Copy the file into the folder you want to use as the *home folder* for your chatbot.
4. Open a command terminal, `cd` into the folder you put the `.jar` file in, and use `java -jar kiera.jar` to run the application.
5. Type the command into the text box and press Enter to execute.
6. Refer to the [Features](#features) below for more details.

## User Guide

### Features 

#### Adding a task
Adds a task to the list of tasks. There are 3 types of tasks: todo, deadline, event.

###### Adding a todo: `todo`
Adds a simple todo to the list of tasks.
> *Format:* `todo TITLE`

*Examples:*
* `todo read book`

###### Adding a deadline: `deadline`
Adds a deadline to the list of tasks.
> *Format:* `deadline TITLE /by DEADLINE`
* Deadline should be written in the form yyyy-mm-dd.

*Examples:*
* `deadline return book /by 2023-05-05`

###### Adding an event: `event`
Adds an event to the list of tasks.
> *Format:* `event TITLE /from DATE START_TIME /to END_TIME`
* Date should be written in the form yyyy-mm-dd.
* Start time and end time should be written in the 24-hour format, e.g. 1400.

*Examples:*
* `event project meeting /from 2023-04-05 1400 /to 1600`

#### Deleting a task: `delete`
Deletes a task from the task list.
> *Format:* `delete INDEX`
* Deletes the task at the specified index. The index refers to the index number shown in the displayed task list.
  * Index must be a positive integer 1, 2, 3...

*Examples:*
* `delete 1`

#### Listing tasks: `list`
Displays all tasks in list.
> *Format:* `list`

#### Filtering tasks
###### Filtering tasks by date: `deadline-date` / `event-date`
Filters all deadlines due on specified date, or events occurring on the specified date.
> *Format:* `deadline-date DATE` / `event-date DATE`
* Date should be written in the form yyyy-mm-dd.

*Examples:*
* `deadline-date 2023-05-05`
* `event-date 2023-04-05`

###### Filtering tasks for today: `deadline-today` / `event-today`
Filters for all deadlines due on today's date, or events occurring on today's date.
> *Format:* `deadline-today` / `event-today`

###### Filtering tasks by keyword: `find`
Filters all tasks to show tasks containing specified keyword.
> *Format:* `find KEYWORD`

*Examples:*
* `find book`

#### Marking task as done: `mark`
Marks a task as done in the task list.
>*Format:* `mark INDEX`
* Marks task at the specified index as done. The index refers to the index number shown in the displayed task list.
  * Index must be a positive integer 1, 2, 3...

*Examples:*
* `mark 1`

#### Marking task as undone: `unmark`
Marks a task as undone in the task list.
>*Format:* `unmark INDEX`
* Marks task at the specified index as done. The index refers to the index number shown in the displayed task list.
  * Index must be a positive integer 1, 2, 3...

*Examples:*
* `unmark 1`

#### Sorting tasks
Sort tasks by date or time.

###### Sorting tasks by date: `sort-deadline-date` / `sort-event-date`
Sorts deadlines by due date, or events by occurrence date.
>*Format:* `sort-deadline-date` / `sort-event-date`

###### Sorting events by time: `sort-event-time`
Sorts event occurring today by start time.
>*Format:* `sort-event-time`





