# User Guide

# Harvard

Harvard is a command-line application for managing tasks.

## Features

### Add a Todo Task : `todo`

  Adds a Todo task to the task list.

  **Format:** `todo DESCRIPTION`

  _Example:_ `todo Buy groceries`


### Add a Deadline Task : `deadline`

  Adds a Deadline task to the task list.

  **Format:** `deadline DESCRIPTION /by DATE TIME`

  _Example:_ `deadline Submit report /by 20/09/2023 1800`


### Add an Event Task : `event`

  Adds an Event task to the task list.

  **Format:** `event DESCRIPTION /from DATE TIME /to DATE TIME`

  _Example:_ `event Team meeting /from 20/09/2023 1800 /to 21/09/2023 1300`


### Mark a Task as Done : `mark`

  Marks a task as completed.

  **Format:** `mark INDEX`

  _Example:_ `mark 1`


### Unmark a Task : `unmark`

  Marks a task as not done.

  **Format:** `unmark INDEX`

  _Example:_ `unmark 1`


### List all Tasks : `list`

  Shows a list of all tasks in the task list.


### Remove a Task : `remove`

  Deletes the specified task from the task list.

  **Format:** `remove INDEX`

  _Example:_ `remove 1`


### Clear All Tasks : `clear`

  Clears all tasks from the task list of the specified type.

  **Format:** `clear TYPE`

  _Example:_ `clear T`


### Exit the Program : `bye`

  Exits the program.

## Usage

1. Run the program.
2. Enter commands to manage your tasks.

## Saving Data

Task data is saved automatically in a local file.

## Command Summary

| Action    | Format, Examples                                  |
| --------- | ------------------------------------------------- |
| Todo      | `todo DESCRIPTION`                                |
| Deadline  | `deadline DESCRIPTION /by DATE TIME`              |
| Event     | `event DESCRIPTION /from DATE TIME /to DATE TIME` |
| Mark      | `mark INDEX`                                      |
| Unmark    | `unmark INDEX`                                    |
| List      | `list`                                            |
| Remove    | `remove INDEX`                                    |
| Clear All | `clear TYPE`                                      |
| Exit      | `bye`                                             |

## FAQ

1. **What is the format for the date and time?**

   The date and time format should be in `d/M/yyyy HHmm`. For example, `15/9/2023 1800` represents the 15th of September 2023, at 6:00 PM.

2. **What are the types for clear?**

   The `clear` command removes all tasks from the task list. It takes types `T` `D` `E` to represent Todo, Deadline and Event tasks.

   **Format:** `clear TYPE`