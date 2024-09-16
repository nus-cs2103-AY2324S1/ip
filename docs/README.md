# User Guide for Bob
Welcome to Bob, your personal task manager bot! Bob can help you keep track and manage your tasks, be it todos, deadlines or events~

## Features 

### Adding a todo task: `todo`

Adds a Todo task to your list of tasks.

Format: `todo TASK_NAME`

Examples:
- `todo borrow a book`
- `todo drink lots of water`

Expected outcome:

A similar message as the following should be displayed.
```
Eyyy. I've added this task:
[T][ ] borrow a book
Now you have 1 tasks in the list.
```

### Adding a deadline task: `deadline`

Adds a Deadline task with a due date to your list of tasks.

Format: `deadline TASK_NAME /by YYYY-MM-DD`

Examples:
- `deadline return a book to the library /by 2023-09-09`
- `deadline complete math assignment /by 2023-09-08`

Expected outcome:

A similar message as the following should be displayed.
```
Eyyy. I've added this task:
[D][ ] return a book to the library (by: 9 Sep 2023)
Now you have 2 tasks in the list.
```

### Adding a event task: `event`

Adds an Event task with a start and end date to your list of tasks.

Format: `event TASK_NAME /from YYY-MM-DD /to YYYY-MM-DD`

Examples:
- `event orientation camp /from 2023-08-08 /to 2023-08-11`
- `event art convention /from 2023-07-12 /to 2023 07-13`

Expected outcome:

A similar message as the following should be displayed.
```
Eyyy. I've added this task:
[E][ ] orientation camp (from: 8 Aug 2023 to: 11 Aug 2023)
Now you have 3 tasks in the list.
```

### Marking a task as done: `mark`

Marks a task as complete.

Format: `mark TASK_INDEX`

Examples:
- `mark 1` Marks the third task in your list as completed.

Expected outcome:

A similar message as the following should be displayed if your marking is successful.
```
Ey! I've marked this task as done:
[T][ ] borrow a book
```

### Deleting a task: `delete`

Deletes a task from the list.

Format: `delete TASK_INDEX`

Examples:
- `delete 1` Deletes the first task in your list.

Expected outcome:

A similar message as the following should be displayed if your deletion is successfull.
```
Okeyy. I've removed this task:
[T][X] borrow a book
Now you have 2 tasks in the list.
```

### Finding a task: `find`

Deletes a task from the list.

Format: `find KEYWORD`

Examples:
- `find borr` Finds all the tasks in your list that contains the keyword "borr".

Expected outcome:

A similar message as the following should be displayed if there are tasks that match your keyword.
```
Here are the matching tasks in your list eyyy:
1. [T][X] borrow a book
```

### Rescheduling a task: `reschedule`

Reschedules either a Deadline or Event task according to the new dates provided.

Format: `reschedule TASK_INDEX /by YYYY-MM-DD` or `reschedule TASK_INDEX /from YYYY-MM-DD /to YYYY-MM-DD`

Examples:
- `reschedule 2 /by 2024-01-01` Reschedules the due date of the second task in the list (which is a Deadline task) to 1 Jan 2024.
- `reschedule 4 /from 2023-08-08 /to 2023-08-10` Reschedules the start and end date of the fourth task in the list (which is an Event task) to 8 Aug 2023 and 10 Aug 2023 respectively.

Expected outcome:

A similar message as the following should be displayed if you successfully rescheduled a task.
```
Okeyy. I've rescheduled this task:
[D][ ] return a book to the library (by: Jan 1 2024)
```

### Listing all tasks: `list`

Lists all the current tasks in the list, along with its complletion status and dates if applicable.

Format: `list`

Expected outcome:

A similar message as the following should be displayed depending on your list of tasks.
```
Here are the tasks in your list eyy:
1. [T][X] borrow a book
2. [D][ ] return a book to the library (by: Jan 1 2024)
3. [E][ ] orientation camp (from: Aug 8 2023 to: Aug 11 2023)
```

### Exiting the bot: `bye`

Exits the bot.

Format: `bye`

### Saving the data

Data of your tasks is automatically saved in the hard disk when you exit the bot.
