# User Guide

CheeChat is an application that is used for managing tasks. You can use this application to store your tasks in an organised order.

## Add Tasks
There are several types of tasks to add:
- Todo
- Deadline
- Event

### Add Todo Tasks: `todo`
Add a todo task into the list of tasks stored. <br>
Format: `todo DESCRIPTION` <br>
- Adds a todo task named `DESCRIPTION` <br>
  Example:
- `todo assignment`
- `todo buy groceries`

### Add Deadline Tasks: `deadline`
Add a deadline task into the list of tasks stored. <br>
Format: `deadline DESCRIPTION /by: DATE`
- Adds a deadline task named `DESCRIPTION` and a due date set to `DATE`
- Key in the deadline date starting with `/by: `
- `DATE` should be in the format YYYY-MM-DD <br>
  Example:
- `deadline homework /by: 2023-09-01`
- `deadline internship application /by: 2023-09-24`

### Add Event Tasks: `event`
Add an event task into the list of tasks stored. <br>
Format: `event DESCRIPTION /from: START_DATE /to: END_DATE`
- Adds an event task named `DESCRIPTION`, a starting date `START_DATE` and an ending date `END_DATE`
- Key in the `START_DATE` starting with `/from` and `END_DATE` with `/to: `
- `START_DATE` and `END_DATE` should be in the format YYYY-MM-DD <br>
  Example:
- `event 2023 vote /from: 2023-09-01 /to: 2023-09-02`
- `event soc walk-in interview /from: 2023-09-26 /to: 2023-09-27`

## Delete Tasks: `delete`
Delete a task from the list of tasks stored. <br>
Format: `delete INDEX`
- Deletes the task at the specified `INDEX`
- The `INDEX` refers to the index number shown in the displayed task list. `INDEX` must be a positive integer 1, 2, 3, …​
- Existing values will be updated to the input values. <br>
  Example:
- `delete 2`

## Listing all tasks: `list`
Shows a list of all tasks stored in the task list in the order of the set priority. <br>
Format: `list`

## Mark a test as done: `mark`
Mark a current task stored in the task list as done. <br>
Format: `mark INDEX`
- Marks the task at the specified `INDEX`
- The `INDEX` refers to the index number shown in the displayed task list. `INDEX` must be a positive integer 1, 2, 3, …​ <br>
  Example:
- `mark 3`

## Unmark a test as done: `unmark`
Unmark a current task stored in the task list as done. <br>
Format: `unmark INDEX`
- Unmarks the task at the specified `INDEX` if it was previously marked as done.
- The `INDEX` refers to the index number shown in the displayed task list. `INDEX` must be a positive integer 1, 2, 3, …​ <br>
  Example
- `unmark 1`

## Find a task: `find`
Find a task in the current task list by searching for a keyword. <br>
Format: `find KEYWORD`
- Shows a list of tasks containing `KEYWORD` <br>
  Example:
- `find book`

## Set priority: `priority`
Set a task to have `HIGH`, `NORMAL`, or `LOW` priority <br>
Format: `priority PRIORITY INDEX`
- `PRIORITY` should be set to either `HIGH`, `NORMAL` or `LOW`
- Set the priority of the task at the specified `INDEX`.
- The `INDEX` refers to the index number shown in the displayed task list. `INDEX` must be a positive integer 1, 2, 3, …​ <br>
  Example:
- `priority HIGH 2`

## Exit program: `bye`
Exit the current program <br>
Format: `bye` <br>

## Saving the data
CheeChat data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Command Summary

| Action       | Summary                                                                                                          |
|--------------|------------------------------------------------------------------------------------------------------------------|
| Add Todo     | `todo DESCRIPTION` <br> eg. `todo assignment`                                                                    |
| Add Deadline | `deadline DESCRIPTION /by: DATE` <br> eg. `deadline homework /by: 2023-09-01`                                    |
| Add Event    | `event DESCRIPTION /from: START_DATE /to: END_DATE` <br> eg. `event 2023 vote /from: 2023-09-01 /to: 2023-09-02` |
| Delete Task  | `delete INDEX` <br> eg. `delete 2`                                                                               |
| List Tasks | `list`                                                                                                           |
| Mark Task | `mark INDEX` <br> eg. `mark 2`                                                                                   |
| Unmark Task | `unmark INDEX` <br> eg. `unmark 2`                                                                               |
| Find Task | `find KEYWORD` <br> eg. `find book`                                                                              |
| Set Priority | `priority PRIORITY INDEX` <br> eg. `priority HIGH 2`                                                              |
| Exit program | `bye`                                                                                                            |




