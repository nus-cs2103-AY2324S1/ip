# Bob Bot User Guide

## Features
1. Help
1. Adding Task
1. Deleting Task
1. Marking Task
1. Unmarking Task
1. List out your tasks
1. Finding tasks by word (regex)

### Help
Direct users to the user guide.

### Adding Tasks
Users can add 3 types of tasks (Todo, Deadline and Event) into their task list.

### Deleting Tasks
Users can delete all of their tasks

### Marking and Unmark task
User can mark and unmark all of their tasks

### Listing out of tasks
Users can list out their tasks to visualize it.

### Find tasks via word
Users can find tasks with word

---
## Usage

### `help`: Direct users to the Help guide
To use:
`help`
This prints the URL for the User Guide.
---
### `todo`:  Creating a Task
Syntax:
`task [item]`

To use:
`task cook`
This schedules a task called cook
---
### `deadline`: Creating a Task with Deadline
Syntax:
`deadline [item] /by [time]`
Note: `time` accepts input in `YYYY-MM-DD`

To use:
`deadline Cook /by 2000-12-12`
This prints a task called cook, with due on 12 Dec, 2000
---
### `event`: Creating a Event with start and end date
Syntax:
`event [event name] /from [time] /to [time]`

To use:
`event JCF /from today /to 1 week later`
Schedule an event called JCF, from today to 1 week later
---
### `list`: List out all the task
Syntax:
`list`

To use:
`list`
Prints out a list of tasks
---
### `find`: find the task by word
Syntax:
`find [item]`

To use:
`find cat`
To find a task called cat
---
### `mark`: mark task as done
Syntax:
`mark [index]`

To use:
`mark 1`
Mark the task at index 1 as done (1-indexing)
---
### `unmark`: mark task as undone
Syntax:
`unmark [index]`

To use:
`unmark 1`
Mark the task at index 1 as undone (1-indexing)
---
### `find`: delete the task
Syntax:
`delete [task]`

To use:
`find cats`
Finding a task called cats
---
### `delete`: delete the task
Syntax:
`delete [index]`

To use:
`delete 1`
Delete the task at index 1 (1 indexing)
---
### `bye`: exit the program
Syntax:
`bye`

To use:
`bye`
Exits the program
---