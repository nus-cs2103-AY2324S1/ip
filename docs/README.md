# User Guide
Obi_wan is a **desktop app for managing your daily Tasks via a Command Line Interface (CLI)**. Obi_wan also has a Graphical
User Interface to allow users to easily visualise their tasks. 

## Quick Start
1. Ensure you have `Java 11` installed on the computer.
2. Download `obi_wan.jar` from [here](https://github.com/Kb-Tay/ip/releases/tag/v0.2).
3. Save the `.jar` file to a folder you want. `cd` to folder and run the command `java -jar obi_wan.jar`  

## Features 
> Notes about the command format:
> * Words in `UPPER_CASE` are parameters to be supplied by the user. 
> * Certain commands have additional parameters denoted by `/`. 
> * Some task have date fields, input format should be `yyyy-dd-MM h:mma`.
> * Task list will be saved locally on a `duke.txt` file. Changes to task list will automatically be saved after each command.

### List: `List`
List all tasks in task list. 
Format: `list`

Expected outcome:
```
Here are the tasks in your list:
1.[T][X] borrow book
2.[D][ ] Assignment Submission (by: Sep 20 2023 12.00PM)
3.[E][ ] Dinner (from: Sep 22 2023 7.00PM to: Sep 15 2023 9.00PM)
```

### Adding a todo: `todo`
Adds a todo task to task list. <br>
Format: `todo TASK_DESC` <br>
Example: `todo borrow book` <br>
Expected Outcome: 
```
Got it. I've added this task:
[T][ ] borrow book
Now you have 6 tasks in the list.
```


### Adding a deadline: `deadline`
> Deadline are tasks that have a specified end date.

Adds a deadline task to task list. <br>
Format: `deadline TASK_DESC /by DATE` <br>
Example: `deadline do homework /by 2023-05-01 18:00` <br>
Expected Outcome:
```
Got it. I've added this task:
[D][ ] do homework (by: May 1 2023 6.00PM)
Now you have 2 tasks in the list.
```


### Adding a event: `event` 
> Event are tasks that have a specified start and end date. 

Adds a event task to task list. <br>
Format: `event TASK_DESC /from DATE /to DATE` <br> 
Example: `event friend party /from 2023-05-01 18:00 /to 2023-05-01 21:00` <br>
Expected Outcome:
```
Got it. I've added this task:
[E][ ] friend party (from: May 1 2023 6.00PM to: May 1 2023 9.00PM)
Now you have 4 tasks in the list.
```


### Mark a task by index `mark`

Mark a task as completed. <br>
Format: `mark TASK_IND` <br> 
Example: `mark 1` <br>
Expected Outcome: 
```
Nice! I've marked this task as done:
[T][X] borrow book
```

### Unmark a task by index `unmark`
Unmark a task from completion. <br>
Format: `unmark TASK_IND` <br>
Example: `unmark 1`
```
OK, I've marked this task as not done yet:
[T][ ] borrow book
```

### Deleting a task by index `Delete`
Delete a task from task list. <br>
Format: `delete TASK_IND` <br>
Example: `delete 1` <br>
Expected Outcome:
```
Noted. I've removed this task:
[T][ ] borrow book
Now you have 2 tasks in the list.
```

### Locating task by description `find`
Find a list task whose description contains any of the given keyword. <br>
Format: `find KEYWORD` <br>
Example: `find book` <br>
Expected outcome:
```
Here are the matching tasks in your list:
1.[T][X] borrow book
```

### Checking for upcoming incomplete tasks `remind`
Reminds users of upcoming task that have not been marked as completed. <br>
Format: `remind` <br>
Expected outcome:
```
Here is a list of upcoming tasks due:
1.[D][ ] Assignment Submission (by: Sep 20 2023 12.00PM)
2.[E][ ] Dinner (from: Sep 22 2023 7.00PM to: Sep 15 2023 9.00PM)
```

### Existing program `bye`
Exits Obi_wan Chat Bot.  <br>
Format: `bye`
