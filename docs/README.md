# User Guide
**Chewy** is a personalised chatbot  for managing your tasks, optimized for use via 
a **Command Line Interface (CLI)** while still having the benefits of a **Graphical User Interface (GUI)**. 
Chewy takes on the personality of the lovely Chewbacca from Star Wars.
## Features 

### Todo

Chewy can add a todo task to your task list.
Format: `todo <description>`
* `<description>` can be any string

Example: `todo homework`

Expected Output:
```
Got it. I've added this task:
[T][ ] homework
```

### Deadline

Chewy can add a deadline task to your task list.\
Format: `deadline <description> /by <time>`
* `<time>` must be in the format `dd/MM/yyyy`
* `<description>` can be any string

Example: `deadline homework /by 12/09/2021`

Expected Output:
```
Got it. I've added this task:
[D][ ] homework (by: 12/09/2021)
```

### Event

Chewy can add an event task to your task list.\
Format: `event <description> /from <time> /to <time>`
* `<time>` must be in the format `dd/MM/yyyy HH:mm a`
* `<description>` can be any string

Example: `event meeting /from 12/09/2021 3:00 PM /to 12/09/2021 4:00 PM`

Expected Output:
```
Got it. I've added this task:
[E][ ] meeting (from 12/09/2021 3:00 PM to 12/09/2021 4:00 PM)
```

### DoAfter

Chewy can add a doafter task to your task list.\
Format: `doafter <description> /after <time>`
* `<time>` must be in the format `dd/MM/yyyy HHmm`
*  `<description>` can be any string

Example: `doafter homework /after 12/09/2021 1800`

Expected Output:
```
Got it. I've added this task:
[DA][ ] homework (after: 12/09/2021 1800)
```

### Mark as done

Chewy can mark a task as done.\
Format: `mark <task_id>`
* `<task_id>` must be a valid index of a task in the task list

Example: `mark 1`\
Special Note: For `doafter` tasks, Chewy will mark the task as 
done only if the current time is after the time specified in the `/after` field.

Expected Output:
```
Nice! I've marked this task as done:
[T][X] homework
```

### Unmark as done

Chewy can unmark a task as done.\
Format: `unmark <task_id>`
* `<task_id>` must be a valid index of a task in the task list

Example: `unmark 1`

Expected Output:
```
Noted. I've marked this task as undone:
[T][ ] homework
```

### Delete

Chewy can delete a task from the task list.\
Format: `delete <task_id>`
* `<task_id>` must be a valid index of a task in the task list

Example: `delete 1`

Expected Output:
```
Noted. I've removed this task:
[T][ ] homework
```

### Find

Chewy can find tasks in the task list that match a keyword.\
Format: `find <keyword>`
* `<keyword>` can be any string

Example: `find homework`

Expected Outputs:
```
Here are the matching tasks in your list:
1. [T][ ] homework
```
```
There are no matching tasks in your list.
```