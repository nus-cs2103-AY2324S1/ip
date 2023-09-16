# Duke

## Features 
<div style="background-color: #4f0b87; padding: 5px;">

Add a task

Delete a task

Mark a task as done

Unmark a task as incomplete

List the tasks stored

Filter the tasks stored

Save the tasks onto local hard disk
</div>

## Adding a task: `todo` `deadline` `event`

**Adds a todo task into the list**

Format for todo: `todo ACTION`

Example of usage:

`todo borrow book`

****

**Adds a deadline task into the list**

Format for deadline: `deadline ACTION /by YYYY-MM-DD`

Example of usage:

`deadline return book /by 2019-01-15`

****

**Adds an event task into the list**

Format for event: `event ACTION /from YYYY-MM-DD /to YYYY-MM-DD`

Example of usage:

`event project meeting /from 2019-02-15 /to 2019-03-30`

****

## Deleting a task: `delete`

**Deletes a task (using its associated number) from the list**

Format for delete: `delete NUMBER`

Example of usage:

`delete 2`

****

## Marking a task: `mark`

**Marks a task (using its associated number) as done**

Format for mark: `mark NUMBER`

Example of usage:

`mark 1`

****

## Unmarking a task: `unmark`

**Unmarks a task (using its associated number) as incomplete**

Format for mark: `unmark NUMBER`

Example of usage:

`unmark 3`

****

## Listing tasks: `list`

**Lists the tasks stored in Duke**

Example of usage:

`list`

Example outcome:
```
Here are the tasks in your list:
  1.[T][ ] borrow book
  2.[D][ ] return book (by: Jan 15 2019)
  3.[E][ ] project meeting (from: Feb 15 2019 to: Mar 30 2019)
```

****

## Filtering tasks: `find`

**Finds all tasks whose names contain the user input**

Format for mark: `find NAME`

Example of usage:

`find reading`

****

## Saving tasks: `bye`

**Saves the tasks onto the local hard disk and exits Duke**

Example of usage:

`bye`

****
