# User Guide for Leon

## Overview

Ever found yourself being swarmed by deadlines and not even know where to start? Or is your phone calendar just way too
cluttered with tasks all throughout the day?

Don't worry, your personal assistant Leon is here to help! Some features include:

- Creation of 4 different tasks (Task, ToDo, Deadline, Event)
- Mark tasks as complete/incomplete
- Find tasks using keywords

And the best part? When you exit the chatbot, your tasks will be saved so you can come back to it later! How cool is
that?? 🤪

## Note for Linux Users

Please run the following commands in your terminal to install JDK 11 and OpenJFX respectively:

`sudo apt-get install openjdk-11-jdk`
`sudo apt install openjfx`

## Features

### Task, ToDo, Deadline, Event

Supports creation of a variety of tasks. You can input a due date and time for Deadlines, and start and end times for
Events.

### List

View your current tasks and completion status.

### Mark and Unmark

Mark a task as complete, and incomplete, respectively.

### Delete

Delete a task from the list.

### Find

Find all tasks containing a given keyword.

### Detect date and time anomalies

Leon is able to detect the following anomalies and raise an error:

- When the deadline due time, or the event end time, is before the current system time.
- When the start time of an event is after its end time.
- When you attempt to create an event that overlaps with another event.

### Check duplicates

Leon will raise an error when you try to create a duplicate task. He doesn't want you to confuse yourself!

## Usage

### `task` - Create a new task

The user inputs the keyword `task`, followed by the details of the task. The task will then be added to the list.

Example of usage:

- User: `task`
- Leon: `Input task details.`
- User: `clean the room`
- Leon: `Don't expect me to clean the room for you!`

Expected outcome:

Task is added to the list.

```
Don't expect me to clean the room for you!
```

### `todo` - Create a new Todo

The user inputs the keyword `todo`, followed by the details of the Todo. The Todo will then be added to the list.

Example of usage:

- User: `todo`
- Leon: `Input todo details.`
- User: `finish cs2103 project`
- Leon: `Stop talking to me! Go and finish cs2103 project!`

Expected outcome:

Todo is added to the list.

```
Stop talking to me! Go and finish cs2103 project!
```

### `deadline` - Create a new Deadline

The user inputs the keyword `deadline`, followed by the details and due time of the deadline. The Deadline will then be
added to the list.

Example of usage:

- User: `deadline`
- Leon: `Input deadline details.`
- User: `CS2100 Assignment`
- Leon: `Input deadline due date. (Required format: YYYY-MM-DD)`
- User: `2023-09-22`
- Leon: `Input deadline due time. (Required format: HH:MM)`
- User: `23:59`
- Leon: `Just saying, better do CS2100 Assignment now. Not like it's my problem if you don't.`

Expected outcome:

Deadline is added to the list.

```
Just saying, better do CS2100 Assignment now. Not like it's my problem if you don't.
```

### `event` - Create a new Event

The user inputs the keyword `event`, followed by the details, start time and end time of the event. The Event will then
be added to the list.

Example of usage:

- User: `event`
- Leon: `Input event details.`
- User: `Career Fair`
- Leon: `Input event start date. (Required format: YYYY-MM-DD)`
- User: `2023-10-01`
- Leon: `Input event start time. (Required format: HH:MM)`
- User: `11:00`
- Leon: `Input event end date. (Required format: YYYY-MM-DD)`
- User: `2023-10-01`
- Leon: `Input event end time. (Required format: HH:MM)`
- User: `17:00`
- Leon: "Wow, you have a Career Fair? Uhh, n-not like I wanna join you!"

Expected outcome:

Event is added to the list.

```
Wow, you have a Career Fair? Uhh, n-not like I wanna join you!
```

### `list` - View your current tasks and completion status

The user inputs the keyword `list`. Leon will then display all tasks in the list and their completion status. (Tasks
that are completed will be marked with an [X])

Example of usage:

`list`

Expected outcome:

All tasks are printed in the list, with their respective completion statuses.

```
list

You have 4 tasks. (0 complete, 4 incomplete)
1. [ ] clean the room
2. [T] [ ] finish cs2103 project
3. [D] [ ] CS2100 Assignment (by: Fri, 22 Sep 2023, 23:59)
4. [E] [ ] Career Fair (from: 01 Oct 2023 11:00 to: 01 Oct 2023 17:00)
```

### `mark` - Mark a task as complete

The user inputs the keyword `mark`, followed by the task number. Leon will then mark the corresponding task as complete.

Example of usage:

- User: `mark`
- Leon: `Please input the task number you wish to mark.` (List of tasks will be provided for reference)
- User: `1`
- Leon: `Task 1 set as complete.`

Expected outcome:

The task is marked as complete.

Leon will raise an error if:

- There are no tasks in the list.
- The task number input is invalid or outside the range 1 < taskNumber < totalNumberOfTasks.
- The corresponding task is already marked as complete.

```
mark

Please input the task number you wish to mark.
1. [ ] clean the room
2. [T] [ ] finish cs2103 project
3. [D] [ ] CS2100 Assignment (by: Fri, 22 Sep 2023, 23:59)
4. [E] [ ] Career Fair (from: 01 Oct 2023 11:00 to: 01 Oct 2023 17:00)

1

Task 1 set as complete.
```

### `unmark` - Mark a task as incomplete

The user inputs the keyword `unmark`, followed by the task number. Leon will then mark the corresponding task as
incomplete.

Example of usage:

- User: `unmark`
- Leon: `Please input the task number you wish to unmark.` (List of tasks will be provided for reference)
- User: `1`
- Leon: `Task 1 set as incomplete.`

Expected outcome:

The task is marked as incomplete.

Leon will raise an error if:

- There are no tasks in the list.
- The task number input is invalid or outside the range 1 < taskNumber < totalNumberOfTasks.
- The corresponding task is already marked as incomplete.

```
unmark

Please input the task number you wish to unmark.
1. [X] clean the room
2. [T] [ ] finish cs2103 project
3. [D] [ ] CS2100 Assignment (by: Fri, 22 Sep 2023, 23:59)
4. [E] [ ] Career Fair (from: 01 Oct 2023 11:00 to: 01 Oct 2023 17:00)

1

Task 1 set as incomplete.
```

### `delete` - Delete a task

The user inputs the keyword `delete`, followed by the task number. Leon will then delete the corresponding task from the
list.

Example of usage:

- User: `delete`
- Leon: `Please input the task number you wish to delete.` (List of tasks will be provided for reference)
- User: `1`
- Leon: `Task 1 deleted successfully. You now have 3 tasks.`

Expected outcome:

The task is deleted from the list.

Leon will raise an error if:

- There are no tasks in the list.
- The task number input is invalid or outside the range 1 < taskNumber < totalNumberOfTasks.

```
delete

Please input the task number you wish to delete.
1. [ ] clean the room
2. [T] [ ] finish cs2103 project
3. [D] [ ] CS2100 Assignment (by: Fri, 22 Sep 2023, 23:59)
4. [E] [ ] Career Fair (from: 01 Oct 2023 11:00 to: 01 Oct 2023 17:00)

1

Task 1 deleted successfully.
You now have 3 tasks.
```

### `find` or `search` - Find all tasks containing a given keyword

The user inputs either the keyword `find` or `search`. Leon will the prompt the user to input a keyword. Then, Leon will
return a list of all the tasks containing the keyword (case sensitive).

Example of usage:

- User: `find`
- Leon: `Please input search keyword.`
- User: `project`
- Leon: (Returns all tasks containing keyword `project`)

Expected outcome:

A list of all tasks containing the given keyword is returned.

Leon will raise an error if:

- There are no tasks in the list.
- The search keyword is empty.

```
find

Please input search keyword.

project

Here are the matching tasks in your list:
1. [T] [ ] finish cs2103 project
```

### `commands` - View all user commands and functions

When Leon is not currently executing a command, the user may input `command`. Leon will then provide the user with all
the available commands, and their respective functions.

Example execution:

```
commands

task - Create a new task
todo - Create a new todo
deadline - Create a new deadline
event - Create a new event
list - View your current tasks and completion status
mark - Mark a task as complete
unmark - Mark a task as incomplete
delete - Delete a task
search - Find all tasks containing a given keyword
bye - Exit the program
```

### `bye` - Exit the program

When the user inputs `bye`, the chatbot will close. All existing tasks will be saved, and the user will be able to
access them (including mark, unmark and delete) the next time they open the chatbot.

## Frequently Asked Questions

**Q: I can't create a Task, even though I filled in all fields.** <br>
A: Before you create a Task, you may wish to execute the `list` command and make sure that there are no existing tasks
of the same name, especially if it was saved from a previous session. In the case of Deadlines and Events, please check
that your date and time inputs are of the form `YYYY-MM-DD` and `HH:MM` respectively.

**Q: When I run the `find` command, I can't see the list of tasks with the keyword, even though I typed it correctly.** <br>
A: Currently, the keyword is case-sensitive, i.e. the keyword `finish` will correctly return a task
named `finish assignment`, but the keyword `Finish` will return nothing. We are working on allowing non-case-sensitive
inputs in a future update to enhance the user experience.
