# User Guide
DukeyBoy is a desktop app for task management, optimised for use
via a Command Line Interface (CLI) while still having the benefits
of a Graphical User Interface (GUI). DukeyBoy can help you keep track
of all the important things you have going on in your life!

## Features 
> [!NOTE]
> Dates should be written in the YYYY-MM-DD format for all
> functions

>[!NOTE]
> All functions are case sensitive

### Getting help: `help`
Shows all the functions that DukeyBoy has to offer

Format: `help`

### Viewing all your tasks: `list`

Displays all the tasks that you have.

Format: `list`

### Adding a todo: `todo`

Adds a TODO to the task list.

Format: `todo (description)`

Examples: 
- `todo Read a book this week!`
- `todo Watch the 2103 lecture`

### Adding a deadline: `deadline`

Adds a task with a deadline to the list.

Format: `deadline (description) /by (date)`

Examples:
- `deadline submit 2103 quiz /by 2023-10-10`
- `deadline complete 2101 slides /by 2023-09-10`

### Adding an event: `event`

Adds a task of type event to the list. An event has a start and
an end date.

Format: `event (description) /from (date) /to (date)`

Examples:
- `event recess week /from 2023-09-23 /to 2023-10-01`


### Marking/UnMarking tasks: `mark/unmark`

Marks/UnMarks a task as completed.

Format: `mark/unmark (task number as indicated on list)`

Example: 
- `mark 2`

### Delete a task: `delete`

Deletes a task from the list.

Format: `delete (task number as indicated on list)`

Example:
- `delete 2`

### Find a task: `find`

Finds a task that contains the keyword provided.

Format: `find (keyword)`

Example:
- `find submit`

>[!NOTE] 
> The keyword in find is not case-sensitive

### Exiting the application: `bye`

Saves the list and exits the program.

Format: `bye`

### Saving the date

DukeyBoy will save the list in the hard disk automatically when
you exit the program using `bye`.

## Command Summary

| Action            | Format                                        |
|-------------------|-----------------------------------------------|
| **Help**          | `help`                                        |
| **List**          | `list`                                        |
| **Add todo**      | `todo (description)`                          |
| **Add deadline**  | `deadline (decription) /by (date)`            |
| **Add event**     | `event (description) /from (date) /to (date)` |
| **Mark / Unmark** | `mark (pos) / unmark (pos)`                   |
| **Delete**        | `delete (pos)`                                |
| **Find**          | `find (keyword)`                              |
| **Exit**          | `bye`                                          |
