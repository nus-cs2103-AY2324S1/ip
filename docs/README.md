# Glendasaurus User Guide

Glendasaurus is a Personal Assistant Chatbot that helps you keep track of various tasks!

## Getting Started
1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest `glendasaurus.jar` from [here](https://github.com/GlendaChong/ip/releases)
3. Copy the file to the folder you want to use as the home folder for Glendasaurus.
4. Open your command terminal and use the `cd` command to navigate to the home folder for Glendasaurus.
5. Type `java jar -glendasaurus.jar` and press Enter to execute it. 
6. You may now begin to use the app!
6. Refer to the [Features](#features) below for details of each command.

## Features 
- [Add a `Todo` task](#add-a-todo-task)
- [Add a `Deadline` task](#add-a-deadline-task)
- [Add an `Event` task](#add-an-event-task)
- [Add a `FixedDuration` task](#add-a-fixedduration-task)
- [List all tasks](#list-all-tasks)
- [Mark a task as done](#mark-a-task-as-done)
- [Mark a task as undone](#mark-a-task-as-undone)
- [Delete a task](#delete-a-task)
- [Find tasks](#find-tasks)
- [Exit the program](#exit-the-program)

### Add a `Todo` task
A `Todo` task is one that does not have any date/time attached to it. 

#### Usage
`todo [task description]`

#### Examples
- `todo read book`
- `todo return book`
- `todo borrow book`

#### Expected Outcome 
If successful, the task will be added to the task list.
Glendasaurus will respond with the following message:

```
Got it. I've added this task:
[T][ ] read book
Now you have 1 task(s) in the list.
```

#### Possible Errors
If the task description is empty, Glendasaurus will respond with the following message:
``` 
OOPS!!! The description of a todo cannot be empty.
``` 

### Add a `Deadline` task
A `Deadline` task is one that needs to be done before a specific date/time. 

#### Usage
`deadline [task description] /by [DD/MM/YYYY] [HHMM]`

#### Examples
- `deadline return book /by 18/09/2020 2359`
- `deadline homework /by 20/09/2020 1059`

#### Expected Outcome
If successful, the task will be added to the task list.
Glendasaurus will respond with the following message:

```
Got it. I've added this task:
[D][ ] return book (by Sep 18 2020 11:59pm)
Now you have 2 task(s) in the list.
```

#### Possible Errors
If the task is not in the correct format, Glendasaurus will respond with the following message:

```
OOPS!!! The format of a deadline task is 
"deadline TASK_DESCRIPTION /by DD/MM/YYYY 24H_TIME\""
```
If the date/time is not valid, Glendasaurus will respond with the following message:

```
OOPS!!! Invalid date or time format in deadline task.
```

### Add an `Event` task
An `Event` task is one that starts at a specific date/time and ends at a specific date/time.

#### Usage
`event [task description] /from [start] /to [end]`

#### Examples
- `event project meeting /from 18/09/2020 1400 /to 18/09/2020 1500`
- `event AI workshop /from 20 Sep /to 21 Sep`

#### Expected Outcome
If successful, the task will be added to the task list.
Glendasaurus will respond with the following message:

```
Got it. I've added this task:
[E][ ] project meeting (from Sep 18 2020 2:00pm to Sep 18 2020 3:00pm)
Now you have 3 task(s) in the list.
```

#### Possible Errors
If the task is not in the correct format, Glendasaurus will respond with the following message:

``` 
OOPS!!! The format of an event task is "event TASK_DESCRIPTION /from START /to END""
```

### Add a `FixedDuration` task

#### Description
A `FixedDuration` task is one that is unscheduled but has a fixed duration. 
It does not have a fixed start/end time. 

#### Usage
`fixedDuration [task description] /needs [duration] [units]`
- duration must be an integer
- units must be one of the following: `hours`, `minutes`

#### Examples
- `fixedDuration iP submission /needs 2 hours`
- `fixedDuration homework /needs 30 minutes`

#### Expected Outcome
If successful, the task will be added to the task list.
Glendasaurus will respond with the following message:

```
Got it. I've added this task:
[F][ ] iP submission (needs 2 hours)
Now you have 4 task(s) in the list.
```

#### Possible Errors
If the task is not in the correct format, Glendasaurus will respond with the following message:

```
OOPS!!! The format of an event task is 
fixedDuration TASK_DESCRIPTION /needs DURATION UNITS(minutes OR hours)
```

If the units is not valid, Glendasaurus will respond with the following message:

```
OOPS!!! The units of a fixed duration task must be either "hours" or "minutes".
```


### List all tasks
Lists all tasks in the task list.

#### Usage
`list`

#### Expected Outcome
Glendasaurus will respond with the following message:

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by Sep 18 2020 11:59pm)
3. [E][ ] project meeting (from Sep 18 2020 2:00pm to Sep 18 2020 3:00pm)
4. [F][ ] iP submission (needs 2 hours)
```

### Mark a task as done
Marks a task as done.

#### Usage
`mark [task number]`

#### Examples
- `mark 1`

#### Expected Outcome
If successful, the task will be marked as done.
Glendasaurus will respond with the following message:

```
Great! I've completed this task!
[T][X] read book
```

#### Possible Errors
If the command is in an invalid format, Glendasaurus will respond with the following message:

 ```
OOPS!!! The format of unmark command is "unmark TASK_NUMBER". 
Task number must exist in the task list.
```

If the task number is not valid, Glendasaurus will respond with the following message:

```
OOPS!!! Task 5 does not exist.
```

### Mark a task as undone
Marks a task as undone.

#### Usage
`unmark [task number]`

#### Examples
- `unmark 1`

#### Expected Outcome
If successful, the task will be marked as undone.
Glendasaurus will respond with the following message:

```
Okay, I have not yet completed this task: 
[T][ ] read book
```

#### Possible Errors
If the command is in an invalid format, Glendasaurus will respond with the following message:

 ```
OOPS!!! The format of unmark command is "unmark TASK_NUMBER".
Task number must exist in the task list.
```

If the task number is not valid, Glendasaurus will respond with the following message:

```
OOPS!!! Task 5 does not exist.
```

### Delete a task
Deletes a task from the task list.

#### Usage
`delete [task number]`

#### Examples
- `delete 1`

#### Expected Outcome
If successful, the task will be deleted from the task list.
Glendasaurus will respond with the following message:

```
Okay, I've removed this task:
[T][ ] read book
Now you have 3 task(s) in the list.
```

#### Possible Errors
If the command is in an invalid format, Glendasaurus will respond with the following message:

 ```
OOPS!!! The format of delete command is "delete TASK_NUMBER". 
Task number must exist in the task list.
```

If the task number is not valid, Glendasaurus will respond with the following message:

```
OOPS!!! Task 5 does not exist.
```

### Find tasks
Finds tasks that contain the matching keyword.

#### Usage
`find [keyword]`

#### Examples
- `find book`
- `find project`

#### Expected Outcome
If successful, the tasks that contain the keyword will be listed.
Glendasaurus will respond with the following message:

```
Here are the matching tasks in your list:
1. [D][ ] return book (by Sep 18 2020 11:59pm)
```

#### Possible Errors
If the command is in an invalid format, Glendasaurus will respond with the following message:
```
OOPS!!! The description of a find command cannot be empty.
```


### Exit the program
Exits Glendasaurus easily once done!

#### Usage
`bye`

#### Expected Outcome
Glendasaurus will respond with the following message:

```
Bye. Hope to see you again soon!
```

## Command Summary
The following table provides a quick summary of all the commands that Glendasarus can understand:

| Command             | Description, Examples                                                                                                          |
|---------------------|--------------------------------------------------------------------------------------------------------------------------------|
| `todo`        | Adds a `Todo` task to the task list. <br> e.g. `todo read book`                                                                |
| `deadline`    | Adds a `Deadline` task to the task list. <br> e.g. `deadline return book /by 18/09/2020 2359`                                  |
| `event`      | Adds an `Event` task to the task list. <br> e.g. `event project meeting /from 18/09/2020 1400 /to 18/09/2020 1500`              |
| `fixedDuration` | Adds a `FixedDuration` task to the task list. <br> e.g. `fixedDuration iP submission /needs 2 hours`                            |
| `list`        | Lists all tasks in the task list.                                                                                              |
| `mark`        | Marks a task as done. <br> e.g. `mark 1`                                                                                       |
| `unmark`      | Marks a task as undone. <br> e.g. `unmark 1`                                                                                   |
| `delete`      | Deletes a task from the task list. <br> e.g. `delete 1`                                                                        |
| `find`        | Finds tasks that contain the keyword. <br> e.g. `find book`                                                                    |
| `bye`         | Exits the program.                                                                                                             |





