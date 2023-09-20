# User Guide
Johnny is an intelligent chatbot that can help you manage your tasks. Simply input
the proper commands and enjoy your saved time!

## Features 
Notes on command format: Input the desired command followed by the necessary fields 
(designated with `CAPITAL LETTERS`) with a space separating them. 
To input time, use the `DD/MM/YYYY` followed by 24 hour time format e.g. `1800`.

### Add Tasks 
There are 3 types of tasks that can be added, each with their own specific fields:
`todo TASK_TITLE`
`deadline TASK_TITLE /by DUE_TIME`
`event TASK_TITLE /from START_TIME /to END_TIME`

### Delete tasks
You can delete tasks from your list using the corresponding index:
`delete INDEX`

### Mark/Unmark tasks
You can mark or unmark tasks on your list as complete using the corresponding index:
`mark INDEX`
`unmark INDEX`

### Update tasks
You can update your desired field of your tasks using the corresponding index and fields:
`update INDEX FIELD NEW_EDIT`
> 💡 Tip: You can only update fields for a task that has that field! You cannot update the deadline of
>  a ToDo task with no deadline!

### Find tasks
You can search for a task in your list with a query:
`find QUERY`

### List
Displays all current tasks in your list:
`list`

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
