# Hachi User Guide

### A task list you'll want to go back to!

## Features 

### Three different types of tasks

You can add **Todos**, **Deadlines** and **Events** to keep track of 
those pesky tasks that you know you'll forget in an hour!

You can mark all three done, or undone.

What's more, **Deadlines** and **Events** allow you to include dates, for easy reference!

### Advanced search and filter options

You can easily list out all of the tasks, or filter them by certain keywords, or the date! Neat, isn't it?

### Text-based UI,  with a graphical twist

I know all of you out there love how typing makes all of the tasks really quick and easy to accomplish.

But we're adding that along with a beautiful GUI 
where a dog named "Hachi" will be handling all of your tasks. Sooo cute.

## Usage

### `bye` - Exits app

Exits the app.

### `list` - Lists tasks

Lists out the tasks, their names, dates, and completion status.
Add the keyword `sort name` to sort the results alphabetically by their names.

Example of usage:

`list`
or
`list sort name`

### `mark` - Marks completion

Marks a task as completed.

Example of usage: 

`mark (task number)`

### `unmark` - Marks non-completion

Marks a task as not completed.

Example of usage:

`unmark (task number)`

### `delete` - Deletes a task

Removes a task from existence.

Example of usage:

`delete (task number)`

### `todo` - Creates a Todo task

Adds a new Todo task, which stores its name.

Example of usage:

`todo (task name)`

### `deadline` - Creates a Deadline task

Adds a new Deadline task, which stores its name and deadline date.

Example of usage:

`deadline (task name) /by (deadline, in yyyy-mm-dd format)`

### `event` - Creates an Event task

Adds a new Event task, which stores its name, start date, and end date.

Example of usage:

`event (task name) /from (start date, in yyyy-mm-dd format) (end date, in yyyy-mm-dd format)`

### `search-date` - Filters tasks by date

Lists out the tasks which have a deadline before the specified date, or for
which the specified date falls between the start date and the end date. 

Example of usage:

`search-date (date, in yyyy-mm-dd format)`

### `find` - Filters tasks by name

Lists out the tasks which contains the exact given string of text.

Example of usage:

`find (name)`