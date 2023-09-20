# User Guide
### ForsakenX - An interactive Duke chatbot

## Features 
* `<UPPERCASE>` words are arguments supplied by the user.

<br>

### Adding a todo: `todo`

Adds a todo to the task list. 

Format: `todo <DESCRIPTION>`

Example: 
* `todo Do Tutorial 4`

<br>

### Adding a deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline <DESCRIPTION> /by <DATETIME>`

* DATETIME must be in `yyyy-MM-dd HH:mm` format. 

Example:
* `deadline Do MA3220 Assignment 2 /by 2023-10-02 22:00`

<br>

### Adding an event: `event`

Adds an event to the task list.

Format: `event <DESCRIPTION> /from <FROM_DATETIME> /to <TO_DATETIME>`

Example:
* `event HS2909 Midterm /from 21 Sep 16:00 /to 21 Sep 17:00`

<br>

### Viewing the task list: `list`

Shows a list of all tasks.

Format: `list`

<br>

### Deleting a task: `delete`

Deletes a task based on its index.
* Index is a positive integer, starting from 1.

Format: `delete <INDEX>`

Example:
* `delete 3` deletes the 3rd task in the list.

<br>

### Marking a task as done: `mark`

Marks a task as done based on its index.
* Index is a positive integer, starting from 1.
* Output icon: `...[X]...`.

Format: `mark <INDEX>`

Example: `mark 3` marks the 3rd task in the list as done.

<br>

### Marking a task as _not_ done: `unmark`

Marks a task as  _not_ done based on its index.
* Index is a positive integer, starting from 1.
* Output icon: `...[ ]...`.

Format: `unmark <INDEX>`

Example: `unmark 2` marks the 2nd task in the list as not done.

<br>

### Finding a task by keyword: `find`

Finds related tasks based on keyword.
* Will return ALL tasks with description containing the keyword, including partial words!
* Keyword is case-sensitive, so `all` will not match `ALL`.

Format: `find <KEYWORD>`

Example: `find CS2103` will return all tasks containing `CS2103`.
* `CS21030` will be matched.
* `CS2103 Homework` will be matched.
* `cs2103` will not be matched.

<br>

### Exiting the program: `bye`

Exits the program.

Format: `bye`

<br>

### Saving the data
All tasks will be saved in the hard disk automatically after any
commands that changes the data. No manual save is required.
* The file can be accessed via `[jarFileLocation]/data/duke.txt`
* Suggestion for beginners: Do not edit the `duke.txt` file in the `data` folder 
as any unexpected changes that makes the format invalid will cause 
a loading error.