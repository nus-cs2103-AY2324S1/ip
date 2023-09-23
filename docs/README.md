# Zenith User Guide

## Features

### Adding tasks

Users can add tasks of 3 kinds,<br>
a TODO task that contains a description,<br>
a DEADLINE task that contains a description and a deadline,<br>
an EVENT task that contains a description, an event start date and an event end date.


### Removing tasks

Users are able to remove tasks if the task is no longer needed.

### Marking tasks as done

Users are able to mark/unmark a task as finished/ not finished.

### Finding tasks

Users are able to find tasks using its descriptioneffectively.

### Saving tasks

Tasks added to Zenith will be saved automatically.

### Loading tasks

Tasks that were previously saved will be loaded up automatically after launching.

## Usage

### `list` - List out all your tasks.

Zenith will list out all your tasks that is being tracked automatically.

Format: `list`

Example of usage: `list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ] Study
2.[D][ ] Attend classes (by: April 3 2021)
3.[E][X] Submit papers (from: April 10 2022 to: April 11 2022)
```

### `mark` - Mark your task as done.

Zenith will mark your task specified by its index(1-indexed) as done.<br>

Format: `mark <index of task to be marked>`

Example of usage: `mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [T][X] Study
```

### `unmark` - Mark your task as done.

Zenith will mark your task specified by its index(1-indexed) as done.<br>

Format: `unmark <index of task to be unmarked>`

Example of usage: `unmark 3`

Expected outcome:

```
OK, I've marked this task as not done yet:
    [E][ ] Learn Chinese (from: April 4 2023 to: April 5 2023)
```

### `todo` - Add a TODO task.

Zenith will add a TODO task with the given description.<br>

Format: `todo <description of your TODO task>`

Example of usage: `todo I am adding a TODO`

Expected outcome:

```
Got it. I've added this task:
    [T][ ] Study
Now you have 7 tasks in the list.
```

### `deadline` - Add a DEADLINE task.

Zenith will add a DEADLINE task with the given description and deadline.<br>

Format: `deadline <description> /by <deadline in the format yyyy/mm/dd>`

Example of usage: `deadline submission /by 2023/09/03`

Expected outcome:

```
Got it. I've added this task:
    [D][ ] submission (by: September 1 2022)
Now you have 5 tasks in the list.
```

### `event` - Add an EVENT task.

Zenith will add an EVENT task with the given description, event start date and event end date.<br>

Format: `event <description> /from <start date in the format yyyy/mm/dd> /to <end date in the format yyyy/mm/dd>`

Example of usage: `event attend carnival /from 2023-05-06 /to 2023-05-08`

Expected outcome:

```
Got it. I've added this task:
    [E][ ] learn piano (from: May 6 2023 to: May 8 2023)
Now you have 6 tasks in the list.
```

### `delete` - Delete a task.

Zenith will delete the task specified by its index(1-indexed).<br>

Format: `delete <index of task to be deleted>`

Example of usage: `delete 2`

Expected outcome:

```
Noted! I've removed this task:
    [D][ ] submit homework (by: September 9 2021)
Now you have 20 tasks in the list.
```

### `find` - Find a task.

Zenith will find a task using the given description and wil return the list of tasks that has matched or partially matched the description.<br>

Format: `find <description to be searched>`

Example of usage: `find study`

Expected outcome:

```
Here are the matching tasks in your list:
2.[E][X] study Mandarin (from: April 4 2023 to: April 5 2023)
5.[E][ ] study python (from: April 4 2023 to: April 5 2023)
```

### `bye` - Quits the application.

Zenith will quit the application and close the window.<br>

Format: `bye`

Example of usage: `bye`

Expected outcome: The application window will close.