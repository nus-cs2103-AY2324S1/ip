# User Guide for Thorndike

__Thorndike__ is a chat-bot application for keeping track of your tasks.

![Sample screenshot](Ui.png)

<br>

## Usage

### Notes about the format
* Words in angle brackets are parameters to be supplied by you. 
For example, in `todo <description>`, `<description>` is to be replaced by your parameter as `todo eat dinner`

* Items in square brackets are optional.
For example, in `todo <description> [/priority <priority>]`, you can choose to supply the priority as `todo eat dinner /priority 1`, or choose to leave it out as `todo eat dinner`.

<br>

### `list` - List task

List out all the tasks that you have added.

Format: 
`list`

<br>

### `todo` - Add a to-do task

Add to a new to-do task that you would like to keep track of.

Format: 
`todo <description> [/priority <priority>]`
* `<priority>` can any integer between 0 to 5 inclusive.

Example of usage: 
`todo eat dinner`, `todo eat dinner /priority 1`

<br>

### `deadline` - Add a task with a deadline

Add to a new task with a deadline that you would like to keep track of.

Format: 
`deadline <description> /by <deadline> [/priority <priority>]`
* `<priority>` can any integer between 0 to 5 inclusive.

Example of usage: 
`deadline submit UG /by 10-10-2020 0900 /priority 1`

<br>

### `event` - Add a task with a start and end date

Add to a new task with a start and end date that you would like to keep track of.

Format: 
`event <description> /from <start date> /to <end date> [/priority <priority>]`
* `<priority>` can any integer between 0 to 5 inclusive.

Example of usage: 
`event attend wedding /from 10-10-2020 0900 /to 11-10-2020 1200`

<br>

### `delete` - Delete a task

Remove a task that you no longer want to keep track of.

Format: 
`delete <index of task in list>`

Example of usage: 
`delete 1`

<br>

### `mark` - Mark a task as done

Mark a task that you have completed.

Format: 
`mark <index of task in list>`

Example of usage: 
`mark 1`

<br>

### `unmark` - Mark a task as not done

Mark a task as not done.

Format: 
`unmark <index of task in list>`

Example of usage: 
`unmark 1`

<br>

### `find` - Find a task

Find a task by providing a keyword, then show a list of task that have description that match the keyword that you have provided.

Format: 
`find <keyword>`

Example of usage: 
`find eat`

<br>

### `priority` - Change priority

Change the priority of a task.

Format: 
`priority <index of task in list> /set <priority>`
* `<priority>` can any integer between 0 to 5 inclusive.

Example of usage: 
`priority 2 /set 3`

<br>

### `toggle` - Switch dark/light mode.

Change the GUI to dark or light mode.

Format: 
`toggle`

<br>

### `help` - Get help with a command

Get the details and usage guide on a particular command.

Format: 
`help <command>`

Example of usage: 
`help todo`, `help toggle`

<br>

### `bye` - Exit program

Exit the program.

Format: 
`bye`
