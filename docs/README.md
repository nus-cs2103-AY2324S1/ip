# User Guide

## Overview

**Quack-NKN** bot is made to help you with task management.
You can use this bot to add tasks to your list,
modify or delete the tasks, mark tasks as done or not done,
to suit your needs.
The product suits users with fast typing.
Commands are available through standard inputs in text UI version,
and text input in GUI version.
Tasks are stored in a local file in human-readable form,
hence users with familiarity of the syntax of the storage
can easily modify the data for your own use.

## Setup

1. Ensure you have Java 11 installed on your computer.
1. Download the latest `duke.jar` from [here](https://github.com/nknguyenhc/ip/releases).
1. Open a command terminal, `cd` into the folder you put the jar file in.
1. Run the jar file using one of the commands specified under [usage](#usage).
1. To execute your command, in the GUI, 
type a command and press Enter or the Send button;
in the text UI, type a command and press Enter.

Refer to the list of available commands [below](#features).

## Features

Generally, if you need to indicate date/time, 
please adhere to the following format:

`[date] [time]`, where
* `date` can either be:
    * `today`: Indicates today.
    * `tmr` or `tomorrow`: Indicates tomorrow.
    * `DD/MM/YYYY`: Indicates a specific date with day, month and year.
* `time` can be either:
    * `HH:MM`: Indicates time with 24-hour format.
    * `HH[am/pm]`: Indicates time with hours only.
    * `HH:MM[am/pm]`: Indicates time with 12-hour format.

Please also take note that your data is not automatically saved.
You need to run the save command to save data to hard disk.

### List out tasks: `list [options]`

Display the current list of tasks, with filters if options are indicated.

Available options:
* `-d`: Excludes tasks that are already marked done.
* `[date]`: Filters in deadlines before/on the date and events happening on the date.
* `todo`: Includes only tasks of type todo.
This must be indicated as the first option.
* `deadline`: Includes only tasks of type deadline.
This must be indicated as the first option.
* `event`: Includes only tasks of type event.
This must be indicated as the first option.

Example usage:

`list deadline 22/9/2023 -d`

Expected outcome:

The list of deadlines not done and are before/on 22/9/2023. For example:

```
Alright, here is your list of deadlines before 22/9/2023:
1. [D][ ] finish CS2103T IP (by: 22/9/2023 11:59pm)
```

### Mark a task as done: `mark [index]`

Mark the task with the given index as done.
The `index` provided must be a positive integer,
and must be a valid index in the task list.
The index can be retrieved from the corresponding number
of the task when `list` command is run.

Example usage:

`mark 1`

Expected outcome:

The task with index 1 should be marked as done.

```
Nice! I've marked this task as done:
[T][X] complete CS2109S algo for contest
```

### Mark a task as not done: `unmark [index]`

Mark the task with the given index as not done.
The `index` provided must be a positive integer,
and must be a valid index in the task list.
The index can be retrieved from the corresponding number
of the task when `list` command is run.
Note that when a task is added, it is not done by default.
Hence you do not need to run this command
to mark the task as not done upon adding the task to the list.

Example usage:

`unmark 1`

Expected outcome:

The task with index 1 should be marked as not done.

```
OK, I've marked this task as not done yet:
[T][ ] complete CS2109S algo for contest
```

### Add a new todo task: `todo [name]`

Add a new todo task to the task list.
Note that a todo task has no end time nor start time.

Example usage:

`todo complete CS2109S algo for contest`

Expected outcome:

A task with the name `complete CS2109S algo for contest` is added to the list.

```
Got it, I've added this task to the list:
[T][ ] complete CS2109S algo for contest
Now you have 1 in the list.
```

### Add a new deadline: `deadline [name] /by [datetime]`

Add a new deadline task to the task list.
Note that a deadline must have an end time.
It must be indicated after the `/by` keyword in the correct format.
(See [datetime formatting](#features))

Example usage:

`deadline finish CS2103T IP /by 22/9/2023 23:59`

Expected outcome:

A deadline called `finish CS2103T IP` is added to the task list
with the end time being at 22 Sept 2023, 2359.

```
Got it, I've added this task to the list:
[D][ ] finish CS2103T IP (by: 22/9/2023 11:59pm)
Now you have 2 in the list.
```

### Add a new event: `event [name] /from [start] /to [end]`

Add a new event to the task list.
Take note that an event must have a start time and an end time.
The start time must be indicated between the `/from` and `/to` keywords.
The end time must be indicated after the `/to` keyword.
Both start time and end time must follow the 
[datetime formatting](#features)

Example usage:

`event GDG Level 1 game initiation /from 21/9/2023 7:30pm /to 21/9/2023 9:30pm`

Expected outcome:

A new event called `GDG Level 1 game initiation` is added,
with the start time being 21 Sept 2023 7:30pm to 21 Sept 2023 9:30pm.

```
Got it, I've added this task to the list:
[E][ ] GDG Level 1 game initiation (from: 21/9/2023 07:30pm to: 21/9/2023 09:30pm)
Now you have 3 in the list.
```

### Update a task's property: `update [property] [index] [new-value]`

Update the property of the task with the given index,
with the given new value.
The `index` provided must be a positive integer,
and must be a valid index in the task list.
The index can be retrieved from the corresponding number
of the task when `list` command is run.
`property` must hold one of the following values,
and correspondingly, `new-value` must follow the following formats:
* `name`: Updates the name of a task.
`new-value` is the new name of the task.
* `deadline`: Updates the end time of a deadline.
`new-value` must follow [datetime formatting](#features).
* `starttime`: Updates the start time of an event.
`new-value` must follow [datetime formatting](#features).
* `endtime`: Updates the end time of an event.
`new-value` must follow [datetime formatting](#features).

Example usage:

`update endtime 3 21/9/2023 10pm`

Expected outcome:

The end time of the event at index 3 is changed to
21 Sept 2023 10pm.

```
Alright, the following task has been updated:
[E][ ] GDG Level 1 game initiation (from: 21/9/2023 07:30pm to: 21/9/2023 10:00pm)
```

### Delete a task: `delete [index]`

Delete the task with the given index.
The `index` provided must be a positive integer,
and must be a valid index in the task list.
The index can be retrieved from the corresponding number
of the task when `list` command is run.

Example usage:

`delete 3`

Expected outcome:

The task at index 3 is deleted.

```
Noted, I've removed this task:
[E][ ] GDG Level 1 game initiation (from: 21/9/2023 07:30pm to: 21/9/2023 10:00pm)
```

### Find tasks: `find [query]`

Find tasks that has `query` as substring.
Take note that the query is case-sensitive.

Example usage:

`find CS2109`

Expected outcome:

The interface displays tasks that match the query `CS2109`.

```
Here are the tasks that match "CS2109"
1. [T][ ] complete CS2109S algo for contest
```

### Save data: `save`

Save data to hard disk. 
The file saved is named `task-list.txt`
and is in the same folder that holds the bot jar file.

Expected outcome:

Data is saved to hard disk.
Without making any modification to the generated file,
upon the next time you open the bot, task list from the last session should be loaded.

```
Saving data ...
Done saving.
```

### Exit the programme: `bye` or `exit`

Exits the programme.

Take note that only the text UI shows an exit message.
The GUI version simply closes the programme.

```
Bye. Hope to see you again soon!
```

## Usage

### `java -jar duke.jar` - Run the GUI of the bot

Runs the GUI of the bot.

The GUI consists of the text message space,
and a text-box for you to key in your command.

![GUI example](/Ui.png)

### `java -jar duke.jar text` - Run the text UI of the bot

Runs the text UI of the bot. The GUI version will not be initiated.

```
____________________________________________________________
Loading data from hard disk ...
Done loading.
____________________________________________________________
Hello from Quack-NKN
What can I do for you?
____________________________________________________________
```
