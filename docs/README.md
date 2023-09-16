# EnigmaBot

EnigmaBot is a chat-bot capable of storing and tracking tasks for the user. EnigmaBot is optimized for use via a **Command Line Interface** (CLI) while still having the benefits of a **Graphical User Interface** (GUI). The faster you can type, the faster you can manage and complete your tasks!

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `EnigmaBot.jar` from [here](https://github.com/Jonyxzx/ip/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your chat-bot.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar EnigmaBot.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds.<br>
   ![Ui](Ui.png)

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features 

**Notes about the command format:**<br>

* Words in `UPPER_CASE` and square brackets are the parameters to be supplied by the user.<br>
  e.g. in `todo [n/DESC]`, `[n/DESC]` is a parameter which can be used as `todo n/eat apple`.

* Extraneous parameters for commands that do not take in parameters (such as `list`, `undo` and `bye`) will be ignored.

* Chain the command with `+` will allow the chat-bot to accept execute multiple command at once.<br>
  e.g. `todo eat apple + todo eat banana` will create 2 todo tasks into the storage.

### Creating a todo : `todo`

Creates a 'todo' task with the description given.

Format: `todo [DESC]`

Examples:
* `todo eat apple` will create a task of todo with the description of `eat apple`

### Creating a deadline: `deadline`

Creates a 'deadline' task with the description and date given.

Format: `deadline [DESC] /by [YYYY-MM-DD HHHH]`

* if time HHHH is not given, it will be auto filled as 2359 hours.

Examples:
* `deadline eat apple /by 2023-12-12 1800` will create a task of deadline with the description of `eat apple` with the date by `2023-12-12 1800`

### Creating a event: `event`

Creates a 'event` task with the description and date given.

Format: `event [DESC] /from [YYYY-MM-DD HHHH] /to [YYYY-MM-DD HHHH]`

* if time HHHH is not given in `/from`, it will be auto filled as 0000 hours.
* if time HHHH is not given in `/to`, it will be auto filled as 2359 hours.

Examples:
* `event eat apple /from 2023-12-12 1800 /to 2023-12-12 1900` will create a task of event with the description of `eat apple` with the date from `2023-12-12 1800` to `2023-12-12 1900`

### Listing all tasks : `list`

Shows a list of all tasks in the storage.

Format: `list`

### Finding some tasks : `find`

Finds all tasks in the storage contain any of the given keywords.

Format: `find [KEYWORD]`

* The search is case-sensitive. e.g `apple` will match `apple`
* The order of the keywords does matter. e.g. `eat apple` will not match `apple eat`
* Only the description is searched.
* Only full words will be matched e.g. `apple` will not match `apples`

Example:
* `find apple` will return task of `eat apple` description.

### Undoing a command : `undo`

Undoes a previous valid command.

Format: `undo`

* The undo will only undoes one previous valid command.
* Consecutive use of undo will undo all the previous valid commands until there is no command left to undo
* The undo command cannot be undone.

### Deleting a task : `delete`

Deletes the specified task from the storage.

Format: `delete [INDEX]`

* Deletes the task at the specified `INDEX`
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

### Marking a task : `mark`

Marks the specified task from the storage as completed.

Format: `mark [INDEX]`

* Marks the task at the specified `INDEX`
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

### Unmarking a task : `unmark`

Unmarks the specified task from the storage as not completed.

Format: `unmark [INDEX]`

* Marks the task at the specified `INDEX`
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

EnigmaBot data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: Why is the chat-bot not responding to some commands?<br>
**A**: The chat-bot only accepts the listed commands with strict format so do follow them accordingly.
