# User Guide for ChatterBot

This is a simple chatbot to help you keep track of your tasks to do, deadlines and events! No matter how busy or distracted you are, ChatterBot will be able to help you organise your upcoming activities!

## Quick Start

1. Ensure you have Java 11 or above installed in your computer.
2. Download `chatterbot.jar` [here](https://github.com/hyc17003/ip/releases/tag/JAR-Release).
3. Open a command terminal, `cd` into the folder the jar file is located in, and use the `java -jar chatterbot.jar` command to run the application.
4. Type the command into the command box and press Enter to execute it.

## Features 

### Add Todo feature: `todo`

Adds a 'Todo' task (a task with a description).

Format: `todo TASK`

Examples:
- `todo homework`
- `todo chores`


### Add Deadline feature: `deadline`

Adds a 'Deadline' task (a task with both a description and a due date).

Format: `deadline TASK /by DATE`. The date should be in YYYY-MM-dd format.

Examples:
- `deadline math assignment /by 2023-09-24`
- `deadline application /by 2023-09-26`

### Add event feature: `event`

Adds an 'Event' task (a task with a description and a start and end time).

Format: `event TASK /from START_TIME /to END_TIME`

Examples:
- `event birthday party /from 2pm /to 4pm`
- `event staycation /from Sept 24 /to Sept 26`

### Listing all tasks: `list`

Shows a numbered list of all tasks.

Format: `list`

### Mark/Unmark task feature: `mark`/`unmark`

Indicates whether the task has been completed, with a check.

Format: `mark TASK_INDEX`/`unmark TASK_INDEX`

### Delete task feature: `delete`

Deletes the specified task.

Format: `delete TASK_INDEX`

### Find task feature: `find`

Displays task(s) with descriptions that contain the specified keyword.

Format: `find KEYWORD`

### Saving the data

Chatterbot data are saved in the hard drive automatically after any command that changes the data. There is no need to save manually.


## Usage

### `mark` - Indicates the progress of a task

Upon entering the command, an 'X' will appear next to the specified task.

Example of usage: 

`mark 1`

Expected outcome:

The task will have a cross mark next to it. This indicates that the task has been completed.

Expected output:
```
1. [T][X] math homework
```