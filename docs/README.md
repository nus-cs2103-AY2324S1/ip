# Uke User Guide
Uke tracks your tasks for you. 

## Features 
_Words in UPPER CASE are the parameters to be supplied by the user._

### Adding todo: todo

Adds a new todo task.

Format: todo DESCRIPTION

Example: 
* todo Learn to use Uke


### Adding task with deadline: deadline

Adds a new task which has a deadline.

Format: deadline DESCRIPTION /by DEADLINE
* DEADLINE **must** be in the form DD/MM/YYYY HH:MM

Example:
* deadline Add tasks to Uke /by 01/10/2023 23:59


### Adding event with start and end time: event

Adds a new event which has start and end times.

Format: event DESCRIPTION /from START /to END
* START **must** be in the form DD/MM/YYYY HH:MM
* END **must** be in the form DD/MM/YYYY HH:MM

Example:
* event Uke's Party /from 21/09/2023 10:00 /to 21/09/2023 12:00


### Deleting a task: delete

Deletes the task corresponding to the index given.

Format: delete INDEX
* INDEX refers to the index number shown in the task list.
* INDEX **must** be a positive integer.

Example:
* delete 1 deletes the 1st task in the task list.


### Marking a task as completed: mark

Marks the task corresponding to the index given as completed.

Format: mark INDEX
* INDEX refers to the index number shown in the task list.
* INDEX **must** be a positive integer.

Example:
* mark 3 marks the 3rd task in the task list as completed.


### Marking a task as uncompleted: unmark

Marks the task corresponding to the index given as uncompleted.

Format: unmark INDEX
* INDEX refers to the index number shown in the task list.
* INDEX **must** be a positive integer.

Example:
* unmark 5 marks the 5th task in the task list as uncompleted.


### Listing all tasks: list

Shows a list of all tasks added.

Format: list


### Finding all tasks which contains a given keyword: find

Shows a list of all tasks which contains the given keyword.

Format: find KEYWORD

Example:
* find Uke returns a list of tasks which contains the word "Uke".


### Viewing all tasks on a given day: view

Shows a list of all tasks which starts on or is due by the given day.

Format: view DAY
* DAY **must** be in the form DD/MM/YYYY

Example:
* view 01/10/2023 returns a list of tasks which starts on or is due by 01/10/2023.


### Exiting the program: bye

Exits the program.

Format: bye


### Saving the data

Data is saved automatically after any command that changes the data. There is no need to save manually.

