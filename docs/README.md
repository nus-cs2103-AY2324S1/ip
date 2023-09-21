# User Guide for Duke
Add, update and delete your tasks with this application!

## Features 

### Save and exit
Save and exit your program by entering the `bye` command. That's all.

### Todos
Add todos to your list with the `todo` command.
Format: `todo <description of task>`

e.g. To add that you need to do homework, just type `todo homework` and press enter or send.

### Deadlines
Add deadlines to your list of tasks with the `deadline` command.
Format: `deadline <description> /by <time>`. The time is in `YYYY MM DD HHMM` format, although the `HHMM` is optional.

E.g. To add that you need to submit your assignment by 11 Oct 2023, 2359hrs, the command is:
`deadline assignment /by 2023 10 11 2359`

### Events
Add events to your list of tasks with the `event` command.
Format: `event <description> /from <time> /to <time>`. The time is in `YYYY MM DD HHMM` format, although the `HHMM` is optional.

E.g. To add an exam which happens on 11 Oct 2023 from 1000 to 1200hrs, the command is:
`event exam /from 2023 10 11 1000 /to 2023 10 11 1200`

### Listing your tasks
List your tasks with a simple `list` command. That's all.

## Find tasks
Find your tasks by description with the `find` command.
Format: `find <item>`.
Gives a list of items that contains the item in the description.

### Mark / Unmark tasks
Mark and unmark your tasks with the `mark` and `unmark` command.
Format: `mark <index in the list>` or `unmark <index in the list>`

E.g. you want to mark the 5th item in the list, use the command
`mark 5`

### Delete tasks
Delete your tasks with the `delete` command.
Format: `delete <index in the list>`

E.g. you want to delete the 5th item in the list, use the command:
`delete 5`

### Update tasks
Update your tasks with the `update` command.
Format: `update <index in the list> <attribute> <updated info>`


#### List of attributes: 

all tasks: `description`: referring to the description of the task

deadline: `by`: referring to the deadline of the task

event: `start`: referring to the start time of the event, `end`: referring to the end time of the event

E.g. The 5th task is an assignment with the deadline of 11 Oct 2023 2359hrs created with the `deadline assignment /by 2023 10 11 2359`

To change the description of this task to a CS2103T assignment, use the command:
`update 5 description CS2103T assignment`.

To change the deadline of the assignment, use the command:
`update 5 by 2023 10 12 2359`. Note the time format is the same as when creating a task.

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
