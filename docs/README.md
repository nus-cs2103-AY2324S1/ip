# Ally's User Guide
What is Ally? Ally is an amazing application that is optimised for use via a Command Line Interface (CLI) while still having a Graphical User Interface(GUI), so it would not bore you out! As a fast typist with 200 wpm, Ally can manage you tasks faster than your normal GUI apps.
## Features 

### Adding a Todo: `todo`

Add a todo task to your list of tasks.

**Format**: `todo` DESCRIPTION 

**Examples**:
- `todo` do CS2103T iP
- `todo` CS2100 Tutorial

### Adding a deadline: `deadline`

Add a deadline to your list of tasks.

**Format**: `deadline` DESCRIPTION /by yyyy-MM-dd HHmm

**Examples**:
- `deadline` submit CS2103T iP /by 2023-09-22 2359
- `deadline` do tutorial /by 2023-02-03 1800
  
### Adding an event: `event`

Add an event to your list of tasks.

**Format**: `event` DESCRIPTION /from START_DATE /to END_DATE

**Example**:
- `event` taylor swift concert /from Sunday 2pm /to Monday 3pm
- `event` study date /from Monday 1pm /to 7pm

### Listing all tasks: `list`

Shows a list of all the tasks added.

**Format**: `list`

### Finding a task: `find`

Find tasks that contains the keyword in the description.

**Format**: `find` KEYWORD

**Examples**:
- `find` book - returns tasks with book as a keyword eg. return book, borrow book
- `find` concert - return tasks with concert as a keyword eg. rock concert, pop concert

### Deleting a task: `delete`

Deletes a task from the list.

**Format**: `delete` INDEX

**Examples**:
- `delete` 1
- `delete` 2

### Marking a task as done: `mark`

Marks a task from the list as done.

**Format**: `mark` INDEX

**Examples**:
- `mark` 1
- `mark` 2

### Marking a task as undone: `unmark`

Marks a task from the list as undone.

**Examples**:
- `unmark` 1
- `unmark` 2

### Sorting the deadlines in the list: `sort`

Sort the deadlines in the list chronologically.

**Format**: `sort`

### Closing the application: `bye`

Displays a goodbye message for a few seconds before closing the application.

**Format**: `bye`
