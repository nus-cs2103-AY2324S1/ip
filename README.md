# User Guide for NiHao

## Features 

### Managing task
There is three type of task in this program:
1. Todo task: A task with description.
2. Deadline task: A task with deadline and description.
3. Event task: A task with start date, end date and description.
<p>
User can add and delete these task.<br />
User can mark a task as done and unmark a task as not done.<br />
</p>


### Saving the data
Task list data are saved in the hard disk automatically after any command that changes the data.<br />
There is __no__ need to save manually.

### Searching task
User can find a task with certain keyword or list out all the tasks.

### Detect duplicate
User are not allowed to add the exact same task into task list.<br />

## Usage

### Adds a todo task: `todo`
Adds a todo task into task list. <br />
Format: `todo DESCRIPTION` <br />
Example of usage: <br /> 
`todo read book`<br />
Expected outcome:
```
Got it, I've added this task:
    [T][] read book
Now you have 6 tasks in the list.
```

### Adds a deadline task: `deadline`
Adds a deadline task into task list.<br />
Format: `deadline DESCRIPTION /by YYYY-MM-DD`<br />
Examples of usage:<br />
`deadline return book /by 2023-09-03`<br />
Expected outcome:
```
Got it, I've added this task:
    [D][] return book (by: Sep 3 2023)
Now you have 7 tasks in the list.
```

   

### Adds an event task: `event`
Adds a event task into task list.<br />
Format: `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`<br />
Example of usage: </br>
`event exam /from 2023-09-03 /to 2023-09-13`<br />
Expected outcome:
```
Got it, I've added this task:
    [E][] exam (from: Sep 3 2023 to: Sep 13 2023)
Now you have 8 tasks in the list.
```

### Find a task: `find`
Finds task where its description contain any of the given keywords.<br />
Format: `find KEYWORD`<br />
Example of usage: <br />
`find book`<br />
Expected outcome:
```
Here are the matching tasks in your list:
1.[T][] read book
2.[D][] return book (by: Sep 3 2023)
```

### Lists all the tasks: `list`
Lists all the tasks that you have add inside the task list.<br />
Format: `list`<br />
Expected outcome:
```
Here are the tasks in your list:
1.[T][] read book
2.[D][] return book (by: Sep 3 2023)
3.[E][] exam (from: Sep 3 2023 to: Sep 13 2023)
```

### Mark a task: `mark`
Mark the specified task from task list as done.<br />
Format: `mark INDEX`<br />
Example of usage:<br />
`mark 1`<br />
Expected outcome:
```
Nice! I've marked this task as done:
    [T][] read book
```

### Unmark a task: `unmark`
Unmark the specified task from task list as not done.<br />
Format: `unmark INDEX`<br />
Example of usage:<br />
`unmark 2`<br />
Expected outcome:
```
OK, I've marked this task as not done yet:
    [T][] read book
```

### Delete a task: `delete`
Delete the specified task from task list.<br />
Format: `delete INDEX`<br />
Example of usage:<br />
`delete 2`<br />
Expected outcome:
```
Noted. I've removed this task:
    [T][] read book
Now you have 7 tasks in the list.
```

### Exiting the program: `exit`
Exits the program.<br />
Format: `exit`

