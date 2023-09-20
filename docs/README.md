# ___Respironix User Guide___

## Features

### Task Types

- **ToDo**: A simple task.
- **Deadline**: A task that needs to be done before a specific date/time.
- **Event**: A task that starts and ends at a specific date/time.

## Usage

> **Note**
The _datetime_ follows the format: `YYYY-MM-DD`

### `todo` - Creating simple task

Creates a new todo task and adds it to the tasklist. Todo tasks do not have any date/time attached.

Format: `todo [DESCRIPTION]`

Example of usage: 

`todo borrow book`

Expected outcome:

Description of the outcome.

```
Got it!. I've added this task:
  [T][ ] borrow book
Now you have 1 tasks in the list
```

### `deadline` - Creating a deadline

Creates a new deadline task and adds it to the tasklist. Deadlines are tasks that need to be done before a specific date/time.

Format: `deadline [DESCRIPTION] /by [DATETIME]`

Example of usage:

`deadline return book /by 2019-01-01`

Expected outcome:

Description of the outcome.

```
Got it!. I've added this task:
  [D][ ] return book (by: Jan 1 2019 00:00)
Now you have 1 tasks in the list
```

### `event` - Creating an Event

Creates a new event task and adds it to the tasklist. Event tasks start at a specific date/time and end at a specific date/time.

Format: `deadline [DESCRIPTION] /from [DATETIME] /to [DATETIME]`

Example of usage:

`event project meeting /from 2023-04-04 14:00 /to 2023-04-05 16:00`

Expected outcome:

Description of the outcome.

```
Got it!. I've added this task:
  [E][ ] project meeting (from: Apr 4 2023 14:00 to Apr 5 2023 16:00)
Now you have 1 tasks in the list
```

### `delete` - Deleting a Task

Removes a task from the tasklist based on the index given.

Format: `delete [INDEX]`

Example of usage:

`delete 1`

Expected outcome:

Description of the outcome.

```
Noted... I've removed this task:
  [D][ ] go to event (by: Jan 1 2023 18:00)
Now you have 8 tasks in the list
```

### `find` - Searching for a task by keyword

Searches for a task that contains a specified keyword in the description.

Format: `find [KEYWORD]`

Example of usage:

`find book`

Expected outcome:

Description of the outcome.

```
Here are your list of tasks:
  1 [T][ ] borrow book
  2 [T][ ] borrow book
  3 [D][ ] return book (by: Jan 1 2019 00:00)
```

### `list` - Displaying all the tasks

Shows a list of all tasks.

Format: `list`

Example of usage:

`list`

Expected outcome:

Description of the outcome.

```
Here are your list of tasks:
  1 [T][ ] leave the library
  2 [T][ ] do 2103
  3 [T][ ] borrow book
  4 [T][ ] sleep
  5 [T][ ] wake up
  6 [T][ ] borrow book
  7 [D][ ] return book (by: Jan 1 2019 00:00)
  8 [E][ ] project meeting (from: Apr 4 2023 14:00 to Apr 5 2023 16:00)
```

### `mark` - Marking a task as done

Marks the task specified at the index provided as completed.

Format: `mark [INDEX]`

Example of usage:

`mark 1`

Expected outcome:

Description of the outcome.

```
Great job completing the task! I've marked it as done.
  [T][X] leave the library
```

### `unmark` - Marking a task as not done

Marks the task specified at the index provided as not completed.

Format: `unmark [INDEX]`

Example of usage:

`unmark 1`

Expected outcome:

Description of the outcome.

```
Oops... Did you mark it incorrectly?
  [T][ ] leave the library
```

### `check` - Checking for tasks on a datetime

Checks for tasks scheduled on a specific date and time.

Format: `check [DATETIME]`

Example of usage:

`check 2019-01-01`

Expected outcome:

Description of the outcome.

```
Here are your list of tasks:
  1 [D][ ] return book (by: Jan 1 2019 00:00)
```

## Summary
| Command           | Description                                    | Format                                       | Example                                       |
|-------------------|------------------------------------------------|----------------------------------------------|-----------------------------------------------|
| `todo`            | Create a simple task                           | `todo [DESCRIPTION]`                        | `todo borrow book`                           |
| `deadline`        | Create a task with a deadline                  | `deadline [DESCRIPTION] /by [DATETIME]`    | `deadline return book /by 2019-01-01`       |
| `event`           | Create an event task                           | `event [DESCRIPTION] /from [DATETIME] /to [DATETIME]` | `event project meeting /from 2023-04-04 14:00 /to 2023-04-05 16:00` |
| `delete`          | Delete a task                                  | `delete [INDEX]`                           | `delete 1`                                   |
| `find`            | Search for tasks by keyword                   | `find [KEYWORD]`                           | `find book`                                  |
| `list`            | Display all tasks                              | `list`                                       | `list`                                       |
| `mark`            | Mark a task as done                            | `mark [INDEX]`                             | `mark 1`                                     |
| `unmark`          | Mark a task as not done                        | `unmark [INDEX]`                           | `unmark 1`                                   |
| `check`           | Check tasks on a specific date and time       | `check [DATETIME]`                         | `check 2019-01-01`                          |
