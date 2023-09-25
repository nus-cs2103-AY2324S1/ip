# Moira<sup>TM</sup>

A feisty chat bot to help you **manage your to-do list**! Optimized for use via a **Command Line Interface (CLI)**, simply type in a few simple commands and get Moira<sup>TM</sup> up to speed on your latest schedule so that you'll never forget a single thing again!

+ [Getting Started](https://github.com/wujy28/ip/edit/master/docs/README.md#getting-started)
+ [Features](https://github.com/wujy28/ip/edit/master/docs/README.md#features)
  + [Listing All Tasks: `list`](https://github.com/wujy28/ip/edit/master/docs/README.md#listing-all-tasks-list)
  + [Adding a ToDo Task: `todo`](https://github.com/wujy28/ip/edit/master/docs/README.md#adding-a-todo-task-todo)
  + [Adding a Deadline Task: `deadline`](https://github.com/wujy28/ip/edit/master/docs/README.md#adding-a-deadline-task-deadline)
  + [Adding an Event: `event`](https://github.com/wujy28/ip/edit/master/docs/README.md#adding-an-event-event)
  + [Deleting a Task: `delete`](https://github.com/wujy28/ip/edit/master/docs/README.md#deleting-a-task-delete)
  + [Marking a Task as Complete: `mark`](https://github.com/wujy28/ip/edit/master/docs/README.md#marking-a-task-as-complete-mark)
  + [Marking a Task as Incomplete: `unmark`](https://github.com/wujy28/ip/edit/master/docs/README.md#marking-a-task-as-incomplete-unmark)
  + [Finding Tasks by Keyword: `find`](https://github.com/wujy28/ip/edit/master/docs/README.md#finding-tasks-by-keyword-find)
  + [Sorting the Task List: `sort`](https://github.com/wujy28/ip/edit/master/docs/README.md#sorting-the-task-list-sort)
  + [Exiting the Application: `bye`](https://github.com/wujy28/ip/edit/master/docs/README.md#exiting-the-application-bye)
 + [Command Summary]()

# Getting Started

1. Download the latest build here.
   + Under 'Assets', click on 'moira.jar' to begin your download.
2. Locate the download and move it to a convenient location.
3. Launch the application by clicking on the file. 

# Features 

## Listing All Tasks: `list`

Shows a list of all tasks on the task list.

**Format:** `list`

## Adding a ToDo Task: `todo`

Adds a task without a deadline to the task list.

**Format:** `todo TASK_DESCRIPTION`

**Parameters:**

1. `TASK_DESCRIPTION` The description of the task

## Adding a Deadline Task: `deadline`

Adds a task with a deadline to the task list.

**Format:** `deadline TASK_DESCRIPTION /by DEADLINE`

**Parameters:**

1. `TASK_DESCRIPTION` The description of the task

2. `DEADLINE` The deadline of the task in the format, yyyy-MM-dd HH:mm

## Adding an Event: `event`

Adds an event with a start and end time to the task list.

**Format:** `event TASK_DESCRIPTION /from STARTING_TIME /to ENDING_TIME`

**Parameters:**

1. `TASK_DESCRIPTION` The description of the task

2. `STARTING_TIME` The starting time of the event in the format, yyyy-MM-dd HH:mm

3. `ENDING_TIME` The ending time of the event in the format, yyyy-MM-dd HH:mm

## Deleting a Task: `delete`

Deletes the specified task from the task list.

**Format:** `delete INDEX`

**Parameters:**

1. `INDEX` The task's index number shown in the displayed task list

## Marking a Task as Complete: `mark`

Marks the specified task on the task list as complete.

**Format:** `mark INDEX`

**Parameters:**

1. `INDEX` The task's index number shown in the displayed task list

## Marking a Task as Incomplete: `unmark`

Marks the specified task on the task list as incomplete.

**Format:** `unmark INDEX`

**Parameters:**

1. `INDEX` The task's index number shown in the displayed task list

## Finding Tasks by Keyword: `find`

Finds all tasks on the task list containing the specified keyword.

**Format:** `find KEYWORD`

**Parameters:**

1. `KEYWORD` The keyword (case-insensitive) to search by

## Sorting the Task List: `sort`

Sorts the task list by the specified property.

**Format:** `sort PROPERTY`

**Parameters:**

1. `PROPERTY` The property used to sort the task list. It can be one of the following:
   + `description` to sort by description in alphabetical order
   + `date` to sort by earliest deadline
   + `type` to sort by task type (ToDo, Deadline, Event)

## Exiting the Application: `bye`

Exits the application.

**Format:** `bye`
