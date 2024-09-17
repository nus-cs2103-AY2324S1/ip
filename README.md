# PRTS User Guide

### What is PRTS?

Ever felt overwhelmed by all the things you need to keep track of? Well, PRTS is here to help! Part chatbot, part to-do-
list, part companion, PRTS is here to help share that cognitive load and assist you through those long nights!

So, what *can* PRTS do?

## Features

General notes about giving commands:
- PRTS is flexible! Lowercase or uppercase, **casing of commands does not matter**!
- Additionally, whitespaces will also be ignored by PRTS - **except the very first word**! Ensure you do not start your
command with a whitespace!
- Unless otherwise stated, PRTS will not attempt to parse your descriptions, and they will be taken verbatim.
- All fields **must** be present! You cannot leave a field blank, or PRTS will complain to you.
- All commands that modify the Task List will automatically save the Task List to a file on your hard disk.
  - These commands are: `todo`, `deadline`, `event`, `delete`, `mark`, `unmark`, and `undo`.

### Adding a To-Do: `todo`
Adds a To-Do item to the Task List.
Format: `todo [description]`
- The `description` will be displayed in the Task List verbatim.

Example:
- `todo buy groceries`

### Adding a Deadline: `deadline`
Adds a Deadline item to the Task List.
Format: `deadline [description] /by [deadline]`
- The `description` will be displayed in the Task List verbatim.
- PRTS will attempt to parse the `deadline` as a date, but if it is unable to do so, the string input will be stored
verbatim.

Examples:
- `deadline submit quiz /by tomorrow`
- `deadline finish assignment /by 25 Sep 2023`

### Adding an Event: `event`
Adds a Deadline item to the Task List.
Format: `event [description] /from [startDate] /to [endDate]`
- The `description` will be displayed in the Task List verbatim.
- PRTS will attempt to parse the `startDate` and `endDate` as dates, but if it is unable to do so, the string input
will be stored verbatim.

Examples:
- `event birthday party /from 1 Oct 12pm /to 5pm`
- `event vacay /from september /to october ???`

### Listing all tasks: `list`
Lists all Tasks currently in the Task List.
Format: `list`
- This command must *strictly* be used with no further input.

### Deleting a task: `delete`
Deletes a specified task from the Task List.
Format: `delete [index | "all"]`
- Either a number or the word `all` should be specified.
- If a number is specified, ensure that it is parseable as an integer, and is between 1 and the size of the Task List.
- If `all` is specified, the entire Task List will be deleted.

Examples:
- `delete 3`
- `delete all`

### Marking a task as complete: `mark`
Marks a specified task in the Task List as complete. If the Task is already marked as complete, PRTS will notify you.
Format: `mark [index | "all"]`
- Either a number or the word `all` should be specified.
- If a number is specified, ensure that it is parseable as an integer, and is between 1 and the size of the Task List.
- If `all` is specified, the entire Task List will be deleted.

Examples:
- `mark 3`
- `mark all`

### Marking a task as incomplete: `mark`
Marks a specified task in the Task List as incomplete. If the Task is already marked as incomplete, PRTS will notify you.
Format: `mark [index | "all"]`
- Either a number or the word `all` should be specified.
- If a number is specified, ensure that it is parseable as an integer, and is between 1 and the size of the Task List.
- If `all` is specified, the entire Task List will be deleted.

Examples:
- `unmark 3`
- `unmark all`

### Finding a search term: `find`
Searches the Tasks in the Task List for a given search term.
Format: `find [searchTerm]`
- For Deadlines and Events, this *will* search the date fields as well.
- `[searchTerm]` can be of any length, but will not be parsed for individual words - returned results must contain the 
entire term verbatim.

### Undo last command: `undo`
Undoes the last one or more commands input by the user that affected the state of the Task List.
Format: `undo [count | "all"]`
- The `count | "all"` field is *optional*.
  - If this field is omitted, i.e. the command provided is `undo`, it will be treated as equivalent to `undo 1`.
- `count` indicates the number of commands to revert.
- Using the word `all` will undo ***all*** commands since the start of the session!
  - **There is no way to undo an undo, so be careful about using this!**

### Exiting the program: `bye`
Exits the program immediately. The Task List will be saved.
- As long as the command begins with the word `bye`, this command will execute, even if this is not the only word in 
that input.

### Flavor text:
There are some keywords currently implemented that will cause PRTS to respond in unique ways! See if you can find them!
There will be more to come!