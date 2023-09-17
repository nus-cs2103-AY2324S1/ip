# Duke User Guide

## Features 

### Adding a new Todo task: `todo`

- Adds a new Todo task to the chatbot.


- Format: `todo TASK_DESCRIPTION`


- Examples: `todo eat` Adds a Todo task with a task description "eat".

<br>

### Adding a new Deadline task: `deadline`

- Adds a new deadline task to the chatbot.


- Format: `deadline TASK_DESCRIPTION /by dd/MM/yyyy`


- Examples: `deadline eat /by 17/9/2023` Adds a Deadline task with a description "eat" 
<br>
and a deadline of 17/9/2023.

<br>

### Adding a new Event task: `event`

- Adds a new Event task to the chatbot.


- Format: `event TASK_DESCRIPTION /from dd/MM/yyyy /to dd/MM/yyyy`


- Examples: `deadline exams /from 17/9/2023 /to 20/9/2023` Adds an Event task 
  <br>
  with description "exams", a start date of 17/9/2023 and an end date of 20/9/2023.

<br>

### Deleting a task: `delete`
- Deletes the specified task from the tasks list.


- Format: `delete INDEX`
    - INDEX must be greater than 0 and less than the total number of tasks


- Examples: `delete 2` Delete the second task in the tasks list.

<br>

### Listing all tasks: `list`
- Shows all the task that has been added.


- Format: `list`

<br>

### Marking a task: `mark`
- Marks a test as completed.


- Format: `mark INDEX`
    - INDEX must be greater than 0 and less than the total number of tasks


- Examples: `mark 2` Marks the second task in the tasks list as completed.

<br>

### Unmarking a task: `unmark`
- Marks a test as not completed.


- Format: `unmark INDEX`
  - INDEX must be greater than 0 and less than the total number of tasks


- Examples: `unmark 2` Marks the second task in the tasks list as not completed.

<br>

### Finding a specific task: `find`
- Finds all the tasks matching the input.


- Format: `find EXPRESSION`


- Examples: `find eat` Finds all the task which contains "eat" in the description.

<br>

### Undoing commands: `undo`
- Undo the most recent commands made in the current session that changes the tasks list.


- Format: `undo NUMBER_OF_COMMANDS`
  - NUMBER_OF_COMMANDS must be greater than or equal to 0 
  <br>
     and less than the number of new changes made.


- Example: `undo 2` Undo the first two commands that was made.

<br>

### Exiting the application: `bye`
- Quits the application


- Format: `bye`




