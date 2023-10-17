# Duke: User Guide

Duke is a chatbot designed to be a task manager and assistant for users.

![Duke Screenshot](./Ui.png)

---

- [Quick Start](#quick-start)
- [Features](#features)
    - [Task Management](#task-management)
    - [Task Completion](#task-completion)
    - [Search Descriptions](#search-descriptions)
    - [Task Persistence](#task-persistence)
    - [Loading Tasks](#loading-tasks)
- [Commands](#commands)
    - [Todo](#todo)
    - [Deadline](#deadline)
    - [Event](#event)
    - [List](#list)
    - [Mark](#mark)
    - [Unmark](#unmark)
    - [Delete](#delete)
    - [Find](#find)
    - [Load](#load)
    - [Bye](#bye)

## Quick Start

1. Ensure that you have Java 11 installed.
2. Download the latest jar file [here](https://github.com/dickongwd/ip/releases/tag/A-Release).
3. Run `java -jar duke.jar`
4. Start managing your tasks!

## Features

### Task Management

Duke allows users to input tasks, providing a brief description of each task. Users can also specify deadlines, or
events with a start and end date if needed.

### Task Completion

Duke enables users to mark tasks as "done" when they are completed. This helps users keep track of their progress.

### Search Descriptions

Duke includes a search feature that allows users to search for keywords in their tasks. This makes it easy to find
specific tasks.

### Task Persistence

Tasks are automatically saved after every change. This ensures that users do not lose their list of tasks even if they
close their session with Duke.

### Loading Tasks

Duke allows users to specify which data files they would like to load, allowing them to share their task list with
others, or simply continue using the same task list on another device by copying over the data file.

## Commands

### `Todo`

Creates a "todo" task with a description.

Usage:

`todo <task description>`

Examples:

- `todo borrow book`
- `todo drink water`

### `Deadline`

Creates a task with a deadline.

Usage:

`deadline <task description> /by <date>`

Note that `<date>` is specified in `YYYY-MM-DD` format.

Examples:

- `deadline return book /by 2023-09-22`
- `deadline finish assignment /by 2023-11-11`

### `Event`

Creates an event with a specified start and end date.

Usage:

`event <description> /from <date> /to <date>`

Note that `<date>` is specified in `YYYY-MM-DD` format.

Examples:

- `event camp /from 2023-11-01 /to 2023-11-11`

### `List`

Lists all tasks.

Usage:

`list`

Example output:

```text
1. [T][X] borrow book
2. [D][ ] return book (by: Sep 22 2023)
3. [E][ ] camp (from: Nov 01 2023 to: Nov 11 2023)
```

### `Mark`

Marks a specified task as completed.

Usage:

`mark <task number>`

Note that task numbers must be positive integers e.g. 1, 2, 3

### `Unmark`

Removes a mark from a completed task.

Usage:

`unmark <task number>`

Note that task numbers must be positive integers e.g. 1, 2, 3

### `Delete`

Removes a specified task from the task list.

Usage:

`delete <task number>`

Note that task numbers must be positive integers e.g. 1, 2, 3

### `Find`

Searches for task descriptions using a keyword.

- The search is case-sensitive e.g `hans` will not match `Hans`
- The order of the keywords matters e.g. `Hans Bo` will match `Bo Hans`

Usage:

`find book`

### `Load`

Loads tasks from a specified file. Duke loads tasks from `.duke-storage.txt` by default.

Usage:

`load <file path>`

### `Bye`

Ends the session with Duke.

Usage:

`bye`

