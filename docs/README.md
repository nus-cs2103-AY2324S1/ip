# TrackerBot User Guide

TrackerBot is a **desktop checklist app for setting reminders of your day-to-day activities.**
It is optimized for use via a **Command Line Interface (CLI)**, but does not neglect its less
technical users by providing a **Graphical User Interface (GUI)** to display program output.

## Quickstart

1. Ensure you have Java `11` or above installed on your computer.
2. Download the latest version of `trackerbot.jar` from the releases page
   [here.](https://github.com/WZWren/ip/releases)
3. Move `trackerbot.jar` to your desired installation location. Note that TrackerBot will create a new
   folder to store its data files after its first launch.
4. Double-click to run!

## Features 
> **NOTE:** 
> 1. Words in UPPER CASE are parameters supplied by the user, and can have spaces between them.
>    1. In `todo DESCRIPTION`, `DESCRIPTION` is a user-supplied parameter.
> 2. Items in [Square Brackets] are optional.
>    1. In `DD/MM[/YYYY][ HHmm]`, the year and time are optional fields.
> 3. Parameters must be in a set order.
>    1. `... /from START /to END` is *NOT* equivalent to `... /to END /from START`.

### What is inside my list of tasks? - `list`

#### Format: `list`

Display a list of all tasks that are added to TrackerBot for tracking.

Expected Output:
```
> list
**** IF NO ITEMS IN LIST
No tasks have been added to the list yet.
**** IF ITEMS IN LIST
I am tracking these tasks:
1. [T][ ] ...
```

### How do I add tasks to my list? - `todo`, `deadline`, `event`

#### Format `todo DESCRIPTION`

Adds a basic reminder task to the list.

#### Format `deadline DESCRIPTION /by DD/MM[/YYYY][ HHmm]`

Adds a deadline reminder task, with an end-date in a specific time format. `HHmm` is in 24 hour format,
and `DD`, `MM` can be single digit if valid.

#### Format `event DESCRIPTION /from DD/MM[/YYYY][ HHmm] /to DD/MM[/YYYY][ HHmm]`

Adds an event reminder task, with an end-date in a specific time format. `HHmm` is in 24 hour format,
and `DD`, `MM` can be single digit if valid.

Expected Output:
```
> todo New todo here
I am tracking this task now:
  [T][ ] New todo here

> deadline New deadline /by <Valid Date>
I am tracking this task now:
  [D][ ] New deadline (by: <Valid Date>)

> event New event /from <Valid Date 1> /to <Valid Date 2>
I am tracking this task now:
  [E][ ] New event (from: <Valid Date 1> | to: <Valid Date 2>)
```

### What's the checkbox-like area in my `list`? - `mark`, `unmark`

#### Format `mark INDEX`/`unmark INDEX`

Attempts to check or uncheck the checkbox next to the task in your list.

Index is a number corresponding to the task inside your list, when you use the `list` command.

Expected Output:
```
> list
I am tracking these tasks:
1. [T][ ] 1
2. [T][X] 2

> mark 1
This task has been marked as completed.
  [T][X] 1

> unmark 2
The task has been marked as incomplete.
  [T][ ] 2

> list
I am tracking these tasks:
1. [T][X] 1
2. [T][ ] 2
```

### I want to remove a task. - `delete`

#### Format `delete INDEX`

Attempts to delete a task in your list.

Index is a number corresponding to the task inside your list, when you use the `list` command.

Expected Output:
```
> list
I am tracking these tasks:
1. [T][ ] 1
2. [T][X] 2

> delete 1
I have removed this task off of my list.
  [T][ ] 1
1 task(s) remain on my list.

> list
I am tracking these tasks:
1. [T][X] 2
```

### I want to search for a task. - `find`

#### Format `find SEARCH_STR`

Searches for a task inside the list. Matches the description to the given search string, and displays
all results that matches the search, if any.

Expected Output:
```
> list
I am tracking these tasks:
1. [T][ ] AAAAA
2. [T][ ] ABBBB
3. [T][X] AAABB

> find AAA
1. [T][ ] AAAAA
3. [T][X] AAABB

> find C
No results match your search.
```

### It's repetitive doing the same task over and over again! - `mass`

#### Format `mass COMMAND FIELDS;FOR;THIS;...`

Shorthands any specific command that you choose to do multiple operations at once for.

EXAMPLE: `mass todo FIELDS;FOR;THIS COMMAND;...` is equivalent to doing:
```
todo FIELDS
todo FOR
todo THIS COMMAND
```

`COMMAND` represents any of the above keywords to use a specific feature, ie `todo`, `mark`, etc.
Commands that do not take in additional parameters such as `list` will prevent the command from being
used. `COMMAND` only has to be written once for this.

`FIELDS;FOR;THIS` represents the parameters specific for the chosen feature specified by the keyword.
Separate each individual parameter group with a semi-colon (`;`).

The `mass` command always generates a success log and an error log on completion. This may be empty.


### That's great! How do I exit though? - `bye`/Closing the Window

#### Format `bye`

Closes the program and saves the data, if possible.

The data is saved relative to your `trackerbot.jar`, in a folder called `./TrackerBot`.