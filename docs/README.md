# Fluke User Guide

This guide aims to explain how to use Fluke.

## Features 

### Read, Create and Delete Tasks

You can display, create, and delete tasks. There are 3 different types of tasks - Todo, Deadline, and Event.

### Mark Tasks as Done / Not Done

You can mark tasks as done. You can also mark "done" tasks as not done.

### Search for Tasks

You can search for tasks using a keyword.

## Usage

### `todo <description>` - Create a Todo Task

Creates a Todo task with a description.

Example of usage: 

`todo do homework`

Expected outcome:

Creates a Todo task with the description "do homework".


### `deadline <description> /by <date>` - Create a Deadline Task

Creates a Deadline task with a description and deadline. The date needs to be formatted in "YYYY-MM-DD".

Example of usage:

`deadline do homework /by 2023-09-21`

Expected outcome:

Creates a Deadline task with the description "do homework" with a deadline of "2023-09-21"

### `event <description> /from <date> /to <date>` - Create an Event Task

Creates an Event task with a description and time frame which the event will be held. The dates need to be formatted in "YYYY-MM-DD".

Example of usage:

`event do homework /from 2023-09-21 /to 2023-09-22`

Expected outcome:

Creates an Event task with the description "do homework" with a time frame from "2023-09-21" to "2023-09-22".

### `list` - Lists all Tasks

Displays a list of all tasks.

Example of usage:

`list`

Expected outcome:

A list of all tasks with their relevant details will be shown.

### `mark <number>` - Mark a Task as Done

Marks a task as done. The number corresponds to the task's number on the task list. Use the `list` command to list out all tasks.

Example of usage:

`mark 1`

Expected outcome:

Marks the first task as done.

### `unmark <number>` - Mark a Task as Not Done

Marks a task as not done. The number corresponds to the task's number on the task list. Use the `list` command to list out all tasks.

Example of usage:

`unmark 1`

Expected outcome:

If the first task is done, it will undo the mark and mark it as not done.

### `delete <number>` - Delete a Task

Deletes a task. The number corresponds to the task's number on the task list. Use the `list` command to list out all tasks.

Example of usage:

`delete 1`

Expected outcome:

Deletes the first task.

### `find <keyword>` - Find tasks with a certain keyword

Displays a list of tasks containing the keyword.

Example of usage:

`find homework`

Expected outcome:

Shows a list of tasks containing the word "homework".

### `bye` - Says bye to Fluke.

Says bye to Fluke.

Example of usage:

`bye`

Expected outcome:

Fluke will say bye to you.