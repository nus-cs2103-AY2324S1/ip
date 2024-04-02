# Luke User Guide 
##### for v0.2.0-limited-release

**~Luke~** is a chatbot built upon the basic template of Duke, 
with a **CLI** (*Command Line Interface*) design. The chatbot is
designed to help manage tasks in an easy-to-use format.

## Features 

### Add tasks to a list
Luke saves lists to a file, allowing you to carry over information
across different sessions.

### Categorize and mark tasks
Luke allows you to use different types of tasks to manage them better, or
mark them to indicate completion.
### User-friendly
Luke provides **help interfaces** to guide new users, and allows 
**rebinding of commands** for advanced users who want
to save keystrokes when typing.

## Quickstart
### To use:
1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest duke.jar release. (Check that the user guide is also of the same version!)

3. Copy the file to the folder you want to use as the home folder for your Luke chatbot.
4. Double-click to start the bot!
### On startup:
The bot will on startup create a folder named `data` in the directory 
that the .jar file is placed in, to save tasks to a file.
Thereafter, the bot will display a short introductory message and show its logo.

## Usage
### General commands
#### `help`  - Provides a help message
Displays a help message containing all the commands, the syntax to use them, and
their functionalities.
Also provides a link to this page, and the current version.

Format: `help`

#### `list`  - Displays the current list
Displays the current list and its contents.

Format: `list`

```
list

Here's the list so far.
1. [T][] Buy groceries
2. [D][X] Submit CS2103 IP (by: Sep 22 2023)
3. [E][] Go on SEP, hopefully (from: Aug 12 2024 to: Jan 12 2025)
```
```
list

No list, silly!
```
#### `bye` - Shuts down the bot
This calls the bot to shut down and close the interface.

Format: `bye`
### Task addition and deletion
For all task types, names can contain spaces, but **_cannot_**
contain the delimiter `%!%`, since this is used in the file format when tasks are saved.
Dates **_must_** be written in the format `YYYY-MM-DD`.

Tasks all have an associated name, and a complete/incomplete status.

#### `todo`  - Adds a todo task 
Adds a todo task with its name to the list.

Format: `todo (String name)`

Todos are a form of task with only a name.
```
todo Buy Groceries

Added the task [T][] Buy Groceries to the list.
```
#### `deadline`  - Adds a deadline task
Adds a deadline task to the list with its name and due date.

Format: `deadline (String name) /by (Date deadline)`

Deadlines are a class of task that have a deadline associated with them.
```
deadline Submit CS2103 IP /by 2023-09-22

Added the task [D][] Submit CS2103 IP (by: Sep 22 2023) to the list.
```
#### `event`  - Adds an event task
Adds an event task to the list with its name, start and end dates.

Format: `event (String name) /from (Date start) /to (Date end)`

Events are a class of task that lasts from one date to another.
*The two dates must be chronological in order (the event cannot start after it ends).*
```
event Go on SEP, hopefully /from 2024-08-12 /to 2025-01-12

Added the task [E][] Go on SEP, hopefully (from: Aug 12 2024 to: Jan 12 2025) to the list.
```
#### `delete`  - Deletes the indicated task
Deletes the task as indicated by the index.

Format: `delete (int index)`

If the index is not an integer or is an invalid index, the bot will inform the user.

```
list

Here's the list so far.
1. [T][] Buy groceries

delete 1

deleted task indexed at 1 successfully

list

No list, silly!
```
```
delete -1

Failure to execute command; Out of list index
```
#### `clear`  - Clears the list
Clears all tasks from the list.

Format: `clear`

```
list
Here's the list so far.
1. [T][] Buy groceries

clear 

Cleared!

list

No tasks, silly!
```

### Marking and unmarking
#### `mark`  - mark the indicated task
Marks the task as indicated by the index.

Format: `mark (int index)`

If the index is not an integer or is an invalid index, the bot will inform the user.

```
list

Here's the list so far.
1. [T][] Buy groceries

mark 1

marked task 1

list

Here's the list so far.
1. [T][X] Buy groceries
```
```
mark -1

Failure to execute command; Out of list index
```
#### `unmark`  - unmarks the indicated task
Unmarks the task as indicated by the index.

Format: `unmark (int index)`

If the index is not an integer or is an invalid index, the bot will inform the user.

```
list

Here's the list so far.
1. [T][X] Buy groceries

unmark 1

unmarked task 1

list

Here's the list so far.
1. [T][] Buy groceries
```
```
unmark -1

Failure to execute command; Out of list index
```
### Utility and convenience commands
#### `find`  - finds tasks with matching terms
Searches the tasklist for tasks that match the keyword as indicated.

Format: `find (String keyword)`

The search function discriminates between uppercase and lowercase. 
Tasks are searched as according to their user interface representations
(i.e. when you display them as in the command `list`).
The indexes returned do not indicate their index in the  list.
```
list

Here's the list so far.
1. [T][] Buy groceries
2. [D][X] Submit CS2103 IP (by: Sep 22 2023)
3. [E][] Go on SEP, hopefully (from: Aug 12 2024 to: Jan 12 2025)

find Sub

Here are the matching items. 
1. [E][] Go on SEP, hopefully (from: Aug 12 2024 to: Jan 12 2025)

find [X]

Here are the matching items. 
1. [D][X] Submit CS2103 IP (by: Sep 22 2023)

find Sep 22

Here are the matching items. 
1. [E][] Go on SEP, hopefully (from: Aug 12 2024 to: Jan 12 2025)
```

```
find Buy Bananas

Failure to execute command; Could not find task

find 2024-09-12

Failure to execute command; Could not find task
```
#### `rebind`  - adds a binding for a command to a new term
For any of the functions in this list, this command allows you to rebind the command
identifier to any contiguous string sequence of your choice.

*Note: Binding can only be done when the term does not contain 
any whitespaces or special characters. 
The binding must also not be already in use, or reserved by the bot.*

Format: `rebind (String sourceBinding) /to (String customBinding)`

Bindings can include and discriminate between upper- and lower-case letters.
Digits are allowed in bindings.
```
list

No list, silly!

rebind list /to l

Adding of custom binding "l" to the source binding "list" was successful.

l 

No list, silly!
```

```
rebind help /to list
Adding of custom binding "list" to the source binding "help" is unsuccessful. 
Reason: The custom binding "list" is already bound.
```

#### `unbind`  - removes a custom binding
Removes any custom binds that were invoked by the previous function.

*You cannot unbind commands that are prebuilt into the bot.*

Format: `unbind (String customBinding)`

```
list

No list, silly!

rebind list /to l

Adding of custom binding "l" to the source binding "list" was successful.

l 

No list, silly!

unbind l

Removal of custom binding "l" was successful.

l

Unknown command given; Unrecognized command!
```

```
unbind help

Removal of custom binding "help" was unsuccessful. 
Reason: This binding is not removable!
```