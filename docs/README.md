# Adam User Guide

## Features

### Add Tasks
There are three types of tasks:
- Todo
- Deadline
- Event

### Find Tasks
You can find tasks based ontheir description

### Complete Tasks
You can complete and uncomplete tasks when you want to

### Tag Tasks
You can tag tasks

### List Tasks
You can list all the tasks you have


## Usage

### Notes about the command format

- Words in `UPPER_CASE` are the parameters that is inputted in by the user
<br/> e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Homework`.
- The specific word `NUM` is used to indicate the integer parameter that is inputted by the user
</br> `Take Note` that the `NUM` integer should always start at 1 instead of 0 and should never exceed the amount of task.
<br/> e.g in `mark NUM`, `NUM` is a parameter which can be used as `mark 1`.
- The specific `YYYY-MM-DD` format is used to indicate the date parameter that is inputted by the user.
<br/> e.g in `deadline TEST /by YYYY-MM-DD`, `YYYY-MM-DD` is a parameter which can be used as `deadline test /by 2023-12-30`.
- Parameters are strictly in order
<br/> e.g if the command specifies `/start YYYY-MM-DD /end YYYY-MM-DD` then only this formatting is acceptable.

### Adding a todo task: `todo`
Adds a todo type task to the task list.
<br/> Format: `todo TEST`
<br/> Examples:
- `todo homework CS2103T`
- `todo walk the dog`

### Adding a Deadline task:`deadline`
Adds a task that has a deadline to the task list.
<br/> Format: `deadline TEST /by YYYY-MM-DD`
<br/> Examples:
- `deadline homework CS2103T /by 2023-11-22`
- `deadline apply internship /by 2023-09-22`

### Adding an Event task:`event`
Adds a task that specifies when an event starts and ends to the task list. <br/>
Format: `event TEST /from YYYY-MM-DD /to YYYY-MM-DD`
<br/> Examples:
- `event career fair /from 2023-09-17 /to 2023-09-20`
- `event midterms /from 2023-09-27 /to 2023-09-30`

### Marking a task to make it complete: `mark`
Marks a task as completed when the mark command is followed by the index of the task.
<br/> Format: `mark NUM`
<br/> Examples:
- `mark 1` Marks the first task in the task list as complete
- `mark 3` Marks the third task in the task list as complete

### Unmarking a task to make it incomplete: `unmark`
Unmarks a task as incompleted when the unmark command is followed by the index of the task.
<br/> Format: `unmark NUM`
<br/> Examples:
- `unmark 1` Unmarks the first task in the task list as incomplete
- `unmark 3` Unmarks the third task in the task list as incomplete

### Tag a task: `tag`
Tag a task that does not have a tag yet or chang ean existing tag to the one according ot  the user input
<br/> Format: `tag NUM TEXT`
<br/> Examples:
- `tag 1 Homework` Tag the first task as Homework
- `tag 2 Exercise` Tag the second task as Exercise

### Deleting a Task: `delete`
Deletes a task according to the index inputted by the user
<br/> Format: `delete NUM`
<br/> Examples:
- `delete 1` Deletes the first task in the task list
- `delete 3` Deletes the third task in the task list

### Finding a Task by the description:`find`
Shows a list of all the tasks that contains the string that is inputted by the user
<br/>Format: `find TEST`
<br/> Examples:
- `find homework` List out all the tasks that has the word homework in it


### Listing all Tasks: `list`
Shows a list of all the tasks in the task list.
<br/>Format: `list`

### Exits the program: `bye`
Exits the program
<br/>Format: `bye`
