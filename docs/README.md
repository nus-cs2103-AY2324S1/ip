# ChatbotBro User Guide

## Features 

### Task management
Includes features such as:
- Add
- Delete
- Tag
- Mark as done/undone
- List
- Find

### Task storage
Automatically stores all tasks in current task list to a file.<p>
Tasks in the file will be automatically loaded when the program is restarted.

## Usage
> [!NOTE] **Note about the command format:**
> - Words in `UPPER_CASE` are parameters to be supplied by the user. <p> 
> in `add todo TODO_DESCRIPTION`, `TODO_DESCRIPTION` is a parameter which can be used as `add todo read`.

### `add todo` - Adds a todo

Adds a todo task to the task list.

Format: `add todo TODO_DESCRIPTION`

Example of usage: 

`add todo read`

Expected outcome:
```
Got it. I've added this task:
[T][ ] read
Now you have 1 tasks in the list.
```

### `add deadline` - Adds a deadline

Adds a deadline task to the task list.

Format: `add deadline DEADLINE_DESCRIPTION /by DEADLINE_DATE`
> [!NOTE] **Note:** Dates must be in `YYYY-MM-DD` format.

Example of usage:

`add deadline buy new bag /by 2023-09-25`

Expected outcome:
```
Got it. I've added this task:
[D][ ] buy new bag (by: Sep 25 2023)
Now you have 2 tasks in the list.
```

### `add event` - Adds an event

Adds an event task to the task list.

Format: `add event EVENT_DESCRIPTION /from EVENT_STARTDATE /to EVENT_ENDDATE`
> [!NOTE] **Note:** Dates must be in `YYYY-MM-DD` format.

Example of usage:

`add event study /from 2023-09-26 /to 2023-10-01`

Expected outcome:
```
Got it. I've added this task:
[E][ ] study (from: Sep 26 2023 to: Oct 1 2023)
Now you have 3 tasks in the list.
```

### `list` - Lists all tasks

Lists out all the tasks in the current task list, in order of creation.

Format: `list`

Example of usage:<p> 
`list`

Expected outcome:
```
Here are the tasks in your list:

1. [T][ ] read
2. [D][ ] buy new bag (by: Sep 25 2023)
3. [E][ ] study (from: Sep 26 2023 to: Oct 1 2023)
```

### `delete` - Deletes a task

Deletes the task with the specified task number from the task list.

Format: `delete /TASK_NUMBER`

Example of usage:<p>
`delete 3`

Expected outcome:
```
Noted. I've removed this task!
```

### `mark` - Marks a task as done

Marks the task with the specified task number as done.

Format: `mark /TASK_NUMBER`

Example of usage:<p>
`mark 2`

Expected outcome:
```
Nice! I've marked this task as done:
[D][X] buy new bag (by: Sep 25 2023)
```

### `unmark` - Marks a task as undone

Marks the task with the specified task number as undone.

Format: `unmark /TASK_NUMBER`

Example of usage:<p>
`unmark 2`

Expected outcome:
```
OK, I've marked this task as not done yet:
[D][ ] buy new bag (by: Sep 25 2023)
```

### `tag` - Tags a task

Tags the task with the specified task number, with the given tag. <p>
Each task can only have one tag. Tagging an already-tagged task will replace the previous tag.

Format: `tag /TASK_NUMBER /TAG`

Example of usage:<p>
`tag 1 fun`

Expected outcome:
```
Noted. I've tagged the task!
```

### `find` - Finds a task

Finds all the tasks containing the specified string in the task details (includes description, dates, and tag).

Format: `find /STRING_TO_CHECK`
> [!NOTE] **Note:** Dates are stored in task details using the `MMM d yyyy` format.<p>
> (eg. trying to find tasks with the associated date `2023-09-25` should be done using any part of `Sep`, `25`, and `2023`.)

Example of usage:<p>
`find Sep`

Expected outcome:
```
Here are the matching tasks in your list:

1. [D][ ] buy new bag (by: Sep 25 2023)
```
