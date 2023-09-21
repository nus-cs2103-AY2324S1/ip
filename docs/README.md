# User Guide

## Features 

### Addition and deletion of tasks

Ability to add/delete tasks from the task list

### List out tasks

Show all tasks in the task list

### Mark or Unmark tasks

Can mark tasks as completed or unmark them

### Search tasks

Find all tasks that contain a keyword or tasks that are overdue or due by a certain date

### Save

Saving of tasklist at specified location

### Load

Read from previously saved tasklist

## Usage

### `todo` - Creates a ToDo task

A ToDo task will be created and added to the task list

Format: 
`todo <Task Description>`

Expected output:
```
The Mind has added a new task
[T][ ] <Task Description>
There are now <Size of taskList> tasks left to complete
```

### `deadline` - Creates a Deadline task

A Deadline task will be created and added to the task list
Enter a proper Date in the deadline to enable checking for overdue tasks or tasks to be done by a certain date
Possible formats: YYYY-MM-DD, DD-MM-YYYY, DD-MMM-YYYY, YYYY-MMM-DD, separated by either '-' or '/'

Format: 
`deadline <Task Description> /by <Deadline>`

Expected output:
```
The Mind has added a new task
[D][ ] <Task Description> (by: <Deadline>)
There are now <Size of taskList> tasks left to complete
```

### `event` - Creates an Event task

An event task will be created and added to the task list

Format: 
`event <Task Description> /from <Start Time> /to <End Time>`

Expected output:
```
The Mind has added a new task
[E][ ] <Task Description> (from: <Start Time> to: <End Time>)
There are now <Size of taskList> tasks left to complete
```

### `list` - Lists out tasks in the list

All the tasks in the list will be displayed

Format: 
`list`

Expected output:
```
1. <Task 1>
2. <Task 2>
...
```

### `mark` - Marks a task as completed

A task will be marked as completed with a [X]

Format: 
`mark <index>`

Expected output:
```
The Mind sees that this task is completed
[T/D/E][X] <Task Description>
```

### `unmark` - Removes the X mark that indicates completion

A task will be unmarked and will not display with [X]

Format: 
`unmark <index>`

Expected output:
```
The Mind sees that this task is not yet completed
[T/D/E][ ] <Task Description>
```

### `delete` - Delete a task from the list

The task will be removed from the list

Format: 
`delete <index>`

Expected output:
```
The Mind has eradicated the task
[T/D/E][ ] <Task Description>
There are now <Size of new list> tasks left to complete
```

### `overdue` - Shows all tasks that are past the deadline

Displays tasks that are both past the deadline and not completed

Format: 
`overdue`

Expected output:
```
1. <Task 1>
2. <Task 2>
```

### `dueby` - Shows all tasks that have to be done by the specified time

Displays tasks that both have to be done by the time stated and not yet completed
Possible formats: YYYY-MM-DD, DD-MM-YYYY, DD-MMM-YYYY, YYYY-MMM-DD, separated by either '-' or '/'

Format: 
`dueby <DateTime>`

Expected output:
```
1. <Task 1>
2. <Task 2>
```

### `find` - Shows all tasks that contain the keyword

Displays tasks that contain the keyword specified

Format: 
`find <Keyword>`

Expected output:
```
1. <Task 1>
2. <Task 2>
```

### `save` - Saves task list to a text file

Saves task list to the specified file path. If a txt file is specified, rewrites that txt file. Else will create an EpochMind.txt file at the directory

Format: 
`save <FilePath>`

Expected output:
```
The Mind has saved the tasks to
<FilePath>
```

### `load` - Load task list from the text file

Loads task list from the specified file path.

Format: 
`load <FilePath>`

Expected output:
```
The Mind has recalled the stored tasks
```
