# BoxBox

## Features 

### Feature 1 - Add tasks

Users are able to add three different type of tasks into the list by using the `todo`, `deadline` and `event` keywords.

### Feature 2 - Remove tasks

Users are able to remove tasks from the list using `delete`.

### Feature 3 - Mark and unmark tasks

Users are able to mark tasks as done or mark tasks as undone using `mark` and `unmark` respectively.

### Feature 4 - Search tasks

Users are able to search for tasks by providing specific keywords using `find`.

### Feature 5 - Set priority

Users are able to assign priority value to tasks using `rank`.

### Feature 6 - Load data

BoxBox loads in data from the hard disk automatically after launching. There is no need to load manually.

### Feature 7 - Save data 

BoxBox's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Usage

### `list` - Listing all tasks

Shows a list of tasks ranked from the highest priority to the lowest

Format: `list`

<p>&nbsp;</p>

### `todo` - Add todo tasks into the list

Add a todo task into the task list.

Format: `todo [description]`

Example of usage:

`todo read book`

<p>&nbsp;</p>

### `deadline` - Add deadline tasks into the list

Add a deadline task into the task list.

Format: `deadline [description] /by [date] [time]`

- date should have the format **YYYY-MM-DD**
- time is *optional*, if provided, should have the format **HH:mm**


Example of usage:

`deadline read book /by 2023-12-31 14:00`

`deadline read book /by 2023-12-31`

<p>&nbsp;</p>

### `event` - Add event tasks into the list

Add an event task into the task list.

Format: `event [description] /from [date] [time] /to [date] [time]`

- date should have the format **YYYY-MM-DD**
- time is *optional*, if provided, should have the format **HH:mm**

Example of usage:

`event read book /from 2023-12-31 14:00 /to 2024-01-12 15:00`

`event read book /from 2023-12-31 /to 2024-01-12`

<p>&nbsp;</p>

### `delete` -Delete tasks

Removes the task.

Format: `delete [index]`

- index refers to the position of the task in the list.

Example of usage:

`delete 1`

<p>&nbsp;</p>

### `mark` - Marking tasks

Marks task as done

Format: `mark [index]`

- index refers to the position of the task in the list.

Example of usage:

`mark 1`

<p>&nbsp;</p>

### `unmark` - Unmarking tasks

Marks task as undone

Format: `unmark [index]`

- index refers to the position of the task in the list.

Example of usage:

`unmark 2`

<p>&nbsp;</p>

### `find` - Locating tasks

Finds task from the list that matches the keyword.

Format: `find [keyword]`

Example of usage:

`find shopping` returns `go shopping (by:Mar 20 2023)`

<p>&nbsp;</p>

### `rank` - Prioritise tasks

Rank a task's priority level.

Format: `rank [index] /level [priority level]`

- index refers to the position of the task in the list.

Example of usage:

`rank 1 /level 100`

<p>&nbsp;</p>

### `bye` - Exiting the program

Exits the program

Format: `bye`
