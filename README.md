# POPOOH 🐻 Chatbot User Guide

Popooh is a desktop chatbot app that can help you keep track of you todo lists, deadlines, and events.

## Features

### Listing out the all the tasks `list`

Returns you the tasks you have

Command format: `list()`

### Adding Todo task `todo`

Adds a new Todo task in your task list

Command format: `todo TASK_DESCRIPTION`

Example: `todo make dinner`

### Adding Deadline task `deadline`

Adds a new Deadline task in your task list

Command format: `deadline TASK_DESCRIPTION /by DATE_TIME`


DATE_TIME should be in the format of `YYYY-MM-ddThh:mm:ss`, where:

- YYYY: year

- MM: month
   
- dd: date
   
- hh: hour (in 24hr format)
   
- mm: minute
   
- ss: second

Example: `deadline CS2103T iP /by 2023-09-22T23:59:00`

### Adding Event task `event`

Adds a new Event task in your task list

Command format: `deadline TASK_DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME`

All DATE_TIME should be in the format of `YYYY-MM-ddThh:mm:ss`, where:

- YYYY: year

- MM: month
   
- dd: date
   
- hh: hour (in 24hr format)
   
- mm: minute
   
- ss: second

Example: `event John's birthday party /from 2023-09-20T15:00:00 /to 2023-09-20T19:00:00`

### Mark as done `mark`

Marks a task as done

Command format: `mark TASK_INDEX`

Example: `mark 1`

### Mark as not done `unmark`

Marks a task as not done

Command format: `unmark TASK_INDEX`

Example: `unmark 1`

### Delete task `delete`

Deletes a task based on the index given

Command format: `delete TASK_INDEX`

Example: `delete 1`

### Set reminder `reminder`

Set task as a reminder

These tasks will be printed everytime when running POPOOH

Command format: `reminder TASK_INDEX`

Example: `reminder 1`

### Find task `find`

Returns a list of tasks with matching keyword

Command format: `find TASK_KEYWORD"

Example: `find dinner`

### Find all commands `help`

Returns all the possible commands and features of POPOOH

Command format: `help`
