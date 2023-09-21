# User Guide

Je-O Chatbot is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest jeo.jar from here.
3. Copy the file to the folder you want to use as the home folder for your Je-O Chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.  
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

<img src = "Ui.png" width = "300px">

## Features 

- Add a todo task: `todo`
- Add a task with deadline: `deadline`
- Add an event: `event`
- Mark a task as done: `mark`
- Mark a task as not done: `unmark`
- Delete a task: `delete`
- List all tasks: `list`
- Find tasks by keyword: `find`
- Sort tasks by date: `sort`
- Close the chatbot: `bye`

#### Notes about the command format:
- Words inside `<>` are the parameters to be supplied by the user.  
  e.g. in `todo <TASK_NAME>`, `<TASK_NAME>` is a parameter which can be used as `todo Read`.

### Add a todo task: `todo`

Add a todo task to the task list.

Format: `todo <TASK_NAME>`
- `<TASK_NAME>` should not be empty

Example: 
- `todo Read a book`
- `todo Grade assignments`
- `todo Watch a movie`

### Add a task with deadline: `deadline`

Add a task with deadline to the task list.

Format: `deadline <TASK_NAME> /by <DEADLINE>`
- `<TASK_NAME>` should not be empty
- `<DEADLINE>` should be in the format `YYYY-MM-DD`
  
Example:
- `deadline Assignment /by 2023-10-10`
- `deadline Homework /by 2023-09-30`
- `deadline Quiz /by 2023-10-03`

### Add an event: `event`

Add an event to the task list.

Format: `event <TASK_NAME> /from <START_DATE> /to <END_DATE>`
- `<TASK_NAME>` should not be empty
- `<START_DATE>` and `<END_DATE>` should be in the format `YYYY-MM-DD`
  
Example:
- `event Final Exam /from 2023-12-01 /to 2023-12-07`
- `event F1 Japan /from 2023-09-22 /to 2023-09-24`
- `event MPL Tournament /from 2023-08-01 /to 2023-10-01`

### Mark a task as done: `mark`

Mark a specified task in the task list as done. 

Format: `mark <TASK_NUMBER>`
- `<TASK_NUMBER>` should be between 1 and the number of tasks in the task list.
  
Example:
- `mark 1`

### Mark a task as not done: `unmark`

Mark a specified task in the task list as not done. 

Format: `unmark <TASK_NUMBER>`
- `<TASK_NUMBER>` should be between 1 and the number of tasks in the task list.
  
Example:
- `unmark 1`

### Delete a task: `delete`

Delete a specified task from the task list. 

Format: `delete <TASK_NUMBER>`
- `<TASK_NUMBER>` should be between 1 and the number of tasks in the task list.
  
Example:
- `delete 1`

### List all tasks: `list`

List all of the tasks in the task list.

Format: `list`

### Find tasks by keyword: `find`

List the tasks that contains a specified keyword. 

Format: `find <KEYWORD>`
- `<KEYWORD>` should not be empty.
  
Example:
- `find Read`
- `find Assignment`

### Sort tasks by date: `sort`

Sort all of the tasks in the task list.
Deadlines are sorted by the deadline date, while events are sorted by the start date, then the end date.
Tasks are listed in the following order: ToDo, Deadline, Event

Format: `sort`

### Close the chatbot: `bye`

Close the chatbot and write the tasks to a file.

Format: `bye`

## Command Summary

| Action                        | Format, Examples                                                                                                     |
|-------------------------------|----------------------------------------------------------------------------------------------------------------------|
| **Add a todo task**           | `todo <TASK_NAME>` <br /> e.g., `todo Read a book`                                                                   |
| **Add a task with deadline**  | `deadline <TASK_NAME> /by <DEADLINE>` <br /> e.g. `deadline Assignment /by 2023-10-10`                               |
| **Add an event**              | `event <TASK_NAME> /from <START_DATE> /to <END_DATE>` <br /> e.g. `event Final Exam /from 2023-12-01 /to 2023-12-07` |
| **Mark a task as done**       | `mark <TASK_NUMBER>` <br /> e.g., `mark 1`                                                                           |
| **Mark a task as not done**   | `unmark <TASK_NUMBER>` <br /> e.g., `unmark 1`                                                                       |
| **Delete a task**             | `delete <TASK_NUMBER>` <br /> e.g., `delete 1`                                                                       |
| **List all tasks**            | `list`                                                                                                               |
| **Find tasks by keyword**     | `find <KEYWORD>` <br /> e.g., `find Read`                                                                            |
| **Sort tasks by date**        | `sort`                                                                                                               |
| **Close the chatbot**        | `bye`                                                                                                                |
