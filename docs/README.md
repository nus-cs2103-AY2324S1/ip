# EepyBot User Guide

## Features 

### List all tasks: `list`

Lists all the tasks in the list.

Format: `list`

### Add todo: `todo`

Adds a todo task to the task list.

Format: `todo <NAME>`

Example:
- `todo cook`
- `todo read a book`

Expected output:
```
Got it. I've added this task:
[T][ ] read a book
You now have 4 task(s) in the list.
```

### Add deadline: `deadline`

Adds a task with a deadline to the task list.

Format: `deadline <NAME> /by <DATE>`
- `DATE` should be in yyyy-MM-dd format

Example:
- `deadline CS2100 tutorial /by 2023-09-25`
- `deadline iP /by 2023-09-22`

Expected output:
```
Got it. I've added this task:
[D][ ] iP (by: Sep 22 2023)
You now have 5 task(s) in the list.
```

### Add event: `event`

Adds an event to the task list.

Format: `deadline <NAME> /from <FROM> /to <TO>`
- There is no specified format for the `FROM` and `TO` fields
- All fields have to be present and valid for the event to be added

Example:
- `event business lunch /from 1pm /to 3pm`
- `event malaysia trip /from Jan 1 /to Jan 10`

Expected output:
```
Got it. I've added this task:
[E][ ] malaysia trip (from: Jan 1 to: Jan 10)
You now have 6 task(s) in the list.
```

### Delete task: `delete`

Deletes task from the task list.<br>
***\*WARNING: This is irreversible***

Format: `delete <INDEX>`

Example:
`delete 4`

Expected output:
```
Noted. I've removed this task:
[T][ ] read a book
Now you have 5 task(s) in the list.
```

### Mark task as done: `mark`

Marks task as done.

Format: `mark <INDEX>`

Example: 
- `mark 5`

Expected output:
```
Nice! I've marked this task as done:
[D][X] iP (by: Sep 22 2023)
```

### Mark task as undone: `unmark`

Marks task as undone.

Format: `unmark <INDEX>`

Example:
- `unmark 5`

Expected output:
```
OK, I've marked this task as not done yet:
[D][ ] iP (by: Sep 22 2023)
```

### Tag task: `tag`

Adds a tag to a task.

Format: `tag <INDEX> <TAG>`
- `TAG` can only take **one word**

Example:
- `tag 6 excited!`

Expected output:
```
Noted. I've tagged this task:
[E][ ] malaysia trip (from: Jan 1 to: Jan 10) #excited!
```

### Find tasks: `find`

Finds all tasks containing the substring. This function is **case-sensitive**.

Format: `find <SUBSTRING>`

Example:
- `find book`

Expected output:
```
Here are the matching tasks in your list:
1.[T][ ] read a book
2.[T][ ] write a book
```

## Command Summary

| Action         | Format                                     |
|----------------|--------------------------------------------|
| List tasks     | `list`                                     |
| Add todo       | `todo <NAME>`                              |
| Add deadline   | `deadline <NAME> /by <DATE IN YYYY-MM-DD>` |
| Add event      | `event <NAME> /from <FROM> /to <TO>`       |
| Delete         | `delete <INDEX>`                           |
| Mark as done   | `mark <INDEX>`                             |
| Mark as undone | `unmark <INDEX>`                           |
| Tag task       | `tag <INDEX> <TAG>`                        |
| Find tasks     | `find <SUBSTRING>`                         |


