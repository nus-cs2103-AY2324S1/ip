# Ip Bot User Guide

Ip Bot is a task management app that keeps tracks of 
your todo tasks, deadlines and events.

## Features 

### List all tasks: `list`

Lists all tasks in the database.
Can optionally specify a date in YYYY-MM-DD format 
to list the deadlines due on that day and the ongoing events.

Format: `list <date> (optional)`

Examples: `list`, `list 2023-09-22`

### Add a Todo task: `todo`

Adds a Todo task to the database with a description.

Format: `todo <description>`

Example: `todo buy flowers`

### Add a Deadline task: `deadline`

Adds a Deadline task to the database with a description 
and due time in YYYY-MM-DD HHMM format.

Format: `deadline <description> /by <due time>`

Example: `todo finish homework /by 2023-09-22 2359`

### Add an Event task: `event`

Adds an Event task to the database with a description,
start time and end time in YYYY-MM-DD HHMM format.

Format: `event <description> /from <start time> /to end time`

Example: `event birthday party /from 2024-02-29 1600 /to 2024-02-29 1800`

Tip: The order of `/from` and `/to` arguments do not matter.
Tip: The date specified by `/to` must not occur before 
the date specified by `/from`.

### Mark task: `mark`

Marks a task. Can be used to indicate completion.

Format: `mark <index>`

Example: `mark 1`

Tip: Use `list` to see of the index of the task to mark.

### Unmark task: `unmark`

Unmarks a task. Can be used to indicate incompletion.

Format: `unmark <index>`

Example: `unmark 1`

### Search task: `find`

Searches for tasks with a given search string as a substring.

Format: `find <search string>`

Example: `find homework`

### Recur task: `recur`

Adds recurrences of a task with a given recurrence frequency
for the given number of times.

Format: `recur <index> /year <year frequency> (optional) /month <month frequency> (optional)
/week <week frequency> (optional) /day <day frequency> (optional) /times <number of times>`

Example: `recur 1 /day 1 /times 2`

### Delete task: delete

Deletes a task.

Format: `delete <index>`

Example: `delete 1`

### Exit the app: bye

Format: `bye`
