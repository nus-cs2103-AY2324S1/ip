# User Guide

Je-O Chatbot is a chatbot that manages todo tasks, deadlines, and events with a Graphical User Interface (GUI). 

## Features 

- Adding a todo task: `todo`
- Adding a task with deadline: `deadline`
- Adding an event: `event`
- Mark a task as done: `mark`
- Mark a task as not done: `unmark`
- Delete a task: `delete`
- List all tasks: `list`
- Find tasks by keyword: `find`
- Sort tasks by date: `sort`
- Close the chatbot: `bye`

### Adding a todo task: `todo`

Add a todo task to the task list.

Format: `todo TASK_NAME`
- TASK_NAME should not be empty

Example: `todo Read a book`

### Adding a task with deadline: `deadline`

Add a task with deadline to the task list.

Format: `deadline TASK_NAME /by DEADLINE`
- TASK_NAME should not be empty
- DEADLINE should be in the format YYYY-MM-DD
  
Example: `deadline Assignment /by 2023-10-10`

### Adding an event: `event`

Add an event to the task list.

Format: `event TASK_NAME /from START_DATE /to END_DATE`
- TASK_NAME should not be empty
- START_DATE and END_DATE should be in the format YYYY-MM-DD
  
Example: `event Final Exam /from 2023-12-01 /to 2023-12-07`

### Mark a task as done: `mark`

Mark a specified task in the task list as done. 

Format: `mark TASK_NUMBER`
- TASK_NUMBER should be between 1 and the number of tasks in the task list.
  
Example: `mark 1`

### Mark a task as not done: `unmark`

Mark a specified task in the task list as not done. 

Format: `unmark TASK_NUMBER`
- TASK_NUMBER should be between 1 and the number of tasks in the task list.
  
Example: `unmark 1`

### Delete a task: `delete`

Delete a specified task from the task list. 

Format: `delete TASK_NUMBER`
- TASK_NUMBER should be between 1 and the number of tasks in the task list.
  
Example: `delete 1`

### List all tasks: `list`

List all of the tasks in the task list.

Format: `list`

### Find tasks by keyword: `find`

List the tasks that contains a specified keyword. 

Format: `find KEYWORD`
- KEYWORD should not be empty.
  
Example: `find Read`

### Sort tasks by date: `sort`

Sort all of the tasks in the task list.
Deadlines are sorted by the deadline date, while events are sorted by the start date, then the end date.
Tasks are listed in the following order: ToDo, Deadline, Event

Format: `sort`

### Close the chatbot: `bye`

Close the chatbot and write the tasks to a file.

Format: `bye`
