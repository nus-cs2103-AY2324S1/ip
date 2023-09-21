# User Guide
***Ren*** is a **desktop chatbot assistant that can help you manage and store your tasks or notes**. It is **optimised for use
via a Command Line Interface (CLI), _benefitting fast typists_**, while still having the benefits of a Graphical User Interface (GUI).

Manage your tasks effectively with the help of Ren! 🤩

![Image of ](Ui.png)

## Features 
_Read this User Guide from top to bottom for a good flow!_
> ℹ️ **Notes about the command format:**
>- Words in `UPPER_CASE` are the parameters to be supplied by the user.
>  eg. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Read a Book`.


### ⚡ Add a todo task: `todo`
Adds a todo task to the tasks list.

Format: `todo DESCRIPTION`

Example: `todo Read a Book`

Expected Outcome:
```
Ren helped you add:
[T][ ] Read a Book

Now you have 1 tasks in the list.
```

### ⚡ Add a deadline task: `deadline`
Adds a deadline task to the tasks list.

Format: `deadline DESCRIPTION /by DD-mm-YYYY HHmm`

Example: `deadline Submit iP /by 22-09-2023 2359`

Expected Outcome:
```
Ren helped you add:
[D][ ] Submit iP (by: Sep 22 2023 23:59PM)

Now you have 2 tasks in the list.
```

### ⚡ Add an event task: `event`
Adds an event task to the tasks list.

Format: `event DESCRIPTION /from DD-mm-YYYY HHmm /to DD-mm-YYYY HHmm`

Example: `event tP Meeting /from 17-09-2023 1200 /to 17-09-2023 1300`

Expected Outcome:
```
Ren helped you add:
[E][ ] tP Meeting (from: Sep 17 2023 12:00PM to: Sep 17 2023 13:00PM)

Now you have 3 tasks in the list.
```

### ⚡ List all tasks: `list`
Lists all the tasks in the tasks list.

Format: `list`

Example: `list`

Expected Outcome:
```
Here are the tasks in your list:
1. [T][ ] Read a Book
2. [D][ ] Submit iP (by: Sep 22 2023 23:59PM)
3. [E][ ] tP Meeting (from: Sep 17 2023 12:00PM to: Sep 17 2023 13:00PM)
```

### ⚡ Mark a task: `mark`
Marks the unmarked task at the index in the tasks list as done.

Format: `mark INDEX`

Example: `mark 1`

Expected Outcome:
- Marks the task at the specified `INDEX` as done.
- The index refers to the index number of task in the list and must be positive (ie. 1, 2, 3, ...)

```
Nice! Ren marked this task as done:
[T][X] Read a Book
```

### ⚡ Unmark a task: `unmark`
Unmarks the marked task at the index in the tasks list as not done yet.

Format: `unmark INDEX`

Example: `unmark 1`

Expected Outcome:
- Unmarks the task at the specified `INDEX` as not dont yet.
- The index refers to the index number of task in the list and must be positive (ie. 1, 2, 3, ...)

```
Nice! Ren marked this task as not done yet  :
[T][ ] Read a Book
```

### ⚡ Delete a task: `delete`
Deletes a task at that index from the tasks list.

Format: `delete INDEX`

Example: `delete 1`

Expected Outcome:
- Deletes the task at the specified `INDEX` from the tasks list.
- The index refers to the index number of task in the list and must be positive (ie. 1, 2, 3, ...)

```
Ren removed the task:
[T][ ] Read a Book

Now you have 2 tasks in the list.
```

### ⚡ Find tasks: `find`
Finds all task that matches the target in the tasks list.

Format: `find TARGET`

Example: `find Submit`

Expected Outcome:
- Finds all tasks containing TARGET.

```  
Here are the matching tasks in your list
1. [D][ ] Submit iP (by: Sep 22 2023 23:59PM)
```

### ⚡ Add a note: `note`
Adds a note to the notes list.

Format: `note DESCRIPTION`

Example: `note SLAP means Single Level of Abstraction Principle`

Expected Outcome:
```
Ren helped you add a new note:
Slap means Single Level of Abstraction Principle
```

### ⚡ List all notes: `notes`
Lists all the notes in the notes list.

Format: `notes`

Example: `notes`

Expected Outcome:
```
Here are the notes in your list:
1. SLAP means Single Level of Abstraction Principle
```

### ⚡ Exit the program: `bye`
Exits the program.

Format: `bye`

Example: `bye`

Expected Outcome:
```
I understand, I will excuse myself...
```

### ⚡ Save data
Ren saves the data automatically in local storage as `tasks.txt` and `notes.txt` for tasks and notes respectively.
