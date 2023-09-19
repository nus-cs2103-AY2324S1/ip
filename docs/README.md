# User Guide for Leon

## Overview

Ever found yourself being swarmed by deadlines and not even know where to start? Or is your phone calendar just way too cluttered with tasks all throughout the day?

Don't worry, your personal assistant Leon is here to help! Some features include:

- Creation of 4 different tasks (Task, ToDo, Deadline, Event)
- Mark tasks as complete/incomplete
- Find tasks using keywords

And the best part? When you exit the chatbot, your tasks will be saved so you can come back to it later! How cool is that?? 🤪


## Features 

### Task, ToDo, Deadline, Event

Supports creation of a variety of tasks. You can input a due date and time for Deadlines, and start and end times for Events.

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
