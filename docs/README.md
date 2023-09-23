# User Guide
Jarvis is a chat bot for maintaining tasks.

## Features

### `list` - Listing all tasks

Shows a list of all tasks.

### `todo` - Adding a to-do task

Adds a to-do task.

`todo DESCRIPTION`

Example of usage:

`todo homework` Adds homework as a to-do task.

### `deadline` - Adding a task with a deadline

Adds a task with a deadline.

`deadline DESCRIPTION /by DATE`

+ Date is in the format of yyyy-mm-dd.

Example of usage:

`deadline homework /by 2023-10-01`

### `event` - Adding an event with a start and an end

Adds an event with a start and an end.

`event DESCRIPTION /from DATE /to DATE`

### `fixedduration` - Adding a task with a fixed duration

Adds a task with a fixed duration.

`fixedduration DESCRIPTION /for TIME`

### `mark` - Marking a task

Marks a task in the list.

`mark INDEX`

+ The index starts with 1.

Example of usage:

`mark 2` Marks the second task in the list.


### `delete` - Deleting a task

Deletes a task in the list.

`delete INDEX`

Example of usage:

`delete 3` Deletes the third task in the list.


### `find` - Searching for tasks

Finds the tasks in the list with a specific keyword.

`find KEYWORD`

### `bye` - Exiting

Exits the chat bot.

## Usage

### Auto saving and loading

Automatically saves your tasks and loads them next time.