# User Guide for Rocket

## Features 

### Add tasks

Add todos using `todo TASK` \
Add deadlines using `deadline TASK \by DEADLINE` \
Add events using `event TASK \from START_DATE \to END_DATE`

! Note: For dates, use this format "dd-MM-yyyy HH:mm". For example, `16-05-2021 09:24`

### List all tasks

List all tasks using `list`

### Mark and Unmark tasks

Mark tasks using `mark TASK_NUMBER` \
Unmark tasks using `unmark TASK_NUMBER`

### Delete tasks

Delete tasks using `delete TASK_NUMBER`

## Usage

### `delete` - Delete a task

Delete a task. The task will be deleted from the list

Example of usage:

`delete 2`

Expected outcome:

Task will no longer appear on the list

```
// initial list
[T][ ] borrow book
[T][ ] eat noodles
[D][ ] get good (by: Dec 10 2024, 11:59 PM)
[E][ ] building (from: Dec 07 2024, 11:59 PM to: Dec 09 2024, 11:59 PM)

// list after command
[T][ ] borrow book
[D][ ] get good (by: Dec 10 2024, 11:59 PM)
[E][ ] building (from: Dec 07 2024, 11:59 PM to: Dec 09 2024, 11:59 PM)
```
