# User Guide

## Features

### Adding a todo - `todo`

Adds a todo to the list.

Format: `todo DESCRIPTION`

Example: `todo read book`

Expected output: 
```
Got it. I've added this task:
    [][T] read book
Now you have 1 tasks in the list.
```

### Adding a deadline - `deadline`

Adds a deadline to the list

Format: `deadline DESCRIPTION /by DUE DATE`

Example: `deadline return book /by 2023-09-22`

Expected output:
```
Got it. I've added this task:
    [][D] return book /by 2023-09-22
Now you have 2 tasks in the list.
```

### Adding an event - `event`

Adds an event to the list.

Format: `event DESCRIPTION /from START DATE /tp END DATE`

Example" `event holiday /from 2023-12-20 /to 2023-12-25`

Expected output:
```
Got it. I've added this task:
    [][E] return holidy /from 2023-12-20 /to 2023-12-25
Now you have 3 tasks in the list.
```

### Printing the list - `list`

Prints a numbered list of tasks.

Format: `print`

Expected output:
```
Here are the tasks in your list:
    1.[][T] read book
    2.[][D] return book (by: 29/09/2023)
    3.[][E] holiday (from: 20/12/2023 to: 25/12/2023)
```

### Marking a task as done - `mark`

Marks the task with an 'X' when the list is printed.

Format: `mark TASK INDEX`

Example: `mark 2`

Expected output:
```
Nice! I've marked this task as done:
    [X][D] return book (by: 29/09/2023)
```

### Marking a task as not done - `unmark`

Removes the 'X' when the list is printed.

Format: `unmark TASK INDEX`

Example: `unmark 2`

Expected output:
```
OK, I've marked this task as not done yet:
    [][D] return book (by: 29/09/2023)
```

### Deleting a task - `delete`

Deletes the task from the list.

Format: `delete TASK INDEX`

Example: `delete 1`

Expected output:
```
Noted. I've removed this task.
    [][T] read book
Now you have 2 tasks in the list.
```

### Finding in list - `find`

Finds and lists all tasks containing the keyword or phrase.

Format: `find KEYWORD`

Example: `find book`

Expected output:
```
Here are the matching tasks in your list:
    1.[X][D] return book (by 29/09/2023)
```

### Counting types of each task - `stats`

Displays the number of each type of task.

Format: `stats`

Expected output:
```
Your tasklist consists of:
    - 0 todos
    - 1 deadlines
    - 1 events
```

### Counting completed tasks - `completed`

Displays the number of completed tasks.

Format: `completed`

Expected output:
```
Congratulations! You have completed 1 of 2 tasks!
```

### Counting uncompleted tasks - `uncompleted`

Displays the number of uncompleted tasks.

Format: `uncompleted`

Expected output:
```
You still have 1 of 2 tasks uncompleted. Jia you!
```

### Exiting the program - `bye`

Displays exit message and quits program.

Format: `bye`

Expected output:
```
Bye. Hope to see you again soon!
```

## Command Summary

- `todo DESCRIPTION`
- `deadline /by DUE DATE`
- `event /from START DATE /to END DATE`
- `list`
- `mark TASK INDEX`
- `unmark TASK INDEX`
- `delete TASK INDEX`
- `find KEYWORD`
- `completed`
- `uncompleted`
- `stats`
- `bye`