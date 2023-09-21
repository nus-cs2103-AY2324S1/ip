# Bobi User Guide

## Features 

### Stores a list of to-dos

3 types of tasks you can add into your to-do list:
1. Normal to-do task
2. Task with deadline
3. Important event

You can also delete them from the list anytime!

### Track status of tasks

Once a task is completed, mark them as done. (Don't worry, they can also be un-marked! >.<)

### Personalized reminders

You no longer need to check deadlines manually, Bobi will remind you about any overdue and upcoming tasks for the week.

### Task search

Search for tasks that coincides with the given keyword, this way you will never lose track of your tasks, 
no matter how long your list gets!

## Usage

### `list` - Lists all tasks

Lists all tasks from your to-do list in numerical order, 
including their task type (To-do, Deadline, Event) and completion status.

Example of usage: 

`list`

Expected outcome:
```
Things you need to do:
1. [T][ ] to-do task
2. [D][X] deadline task (by: Jan 1 2023 at 00:00)
3. [E][ ] event task (from: Sep 24 2023 at 00:00 to: Sep 25 2023 at 00:00)
```
### `todo` - Adds new To-do task

Adds new To-do task (un-marked by default), and informs you the total number of tasks currently in your list.

Format for usage:

`todo TASK_NAME`

Example of usage:

`todo dummy task`

Expected outcome:
```
New task added to your list:
[T][ ] dummy task
You currently have 1 tasks to do.
```
### `deadline` - Adds new Deadline task

Adds new Deadline task (un-marked by default) with its deadline. Informs you the total number of tasks currently in your list.

Format for usage: 

`deadline TASK_NAME /by yyyy-mm-dd HHmm`

Example of usage:

`deadline dummy task /by 2023-01-01 0000`

Expected outcome:
```
New task added to your list:
[D][ ] dummy task (by: Jan 1 2023 at 00:00)
You currently have 2 tasks to do.
```
### `event` - Adds new Event task

Adds new Event task (un-marked by default) with its start/end date and time. Informs you the total number of tasks currently in your list.

Format for usage: 

`event EVENT_NAME /from yyyy-mm-dd HHmm /to yyyy-mm-dd HHmm`

Example of usage:

`event dummy task /from 2023-01-01 0000 /to 2023-01-02 0000`

Expected outcome:
```
New task added to your list:
[E][ ] dummy task (from: Jan 1 2023 at 00:00 to: Jan 2 2023 at 00:00)
You currently have 3 tasks to do.
```
### `mark` - Marks a task

Changes the status of the task to complete.

Correct format: 

`mark TASK_NUMBER`

Example of usage:

`mark 1`

Expected outcome:
```
OK! Your task has now been updated to:
[T][X] dummy task
```
### `unmark` - Un-marks a task

Changes the status of the task to incomplete.

Correct format:

`unmark TASK_NUMBER`

Example of usage:

`unmark 1`

Expected outcome:
```
OK! Your task has now been updated to:
[T][ ] dummy task
```
### `delete` - Deletes a task

Deletes a task from the to-do list and updates you of the number of tasks currently in the list.

Correct format:

`delete TASK_NUMBER`

Example of usage:

`delete 1`

Expected outcome:
```
Alright! I have deleted this task from the list:
[T][ ] to-do task
You currently have 2 tasks to do.
```
### `find` - Search for task with keyword

Returns all tasks that matches with the given keyword.

Correct format:

`find KEYWORD`

Example of usage:

`find bobi`

Expected outcome:
```
Found it! Here are the matching tasks in your list:
1.[D][ ] bobi homework (by: Sep 23 2023 at 23:59)
```
### `remind` - Reminder for tasks with higher priority

Reminds you about overdue tasks and tasks that are due the coming week.


Example of usage:

`remind`

Expected outcome:
```
You have 1 tasks overdue:
1.[D][ ] dummy 1 (by: Jan 1 2020 at 00:00)

You have 1 tasks due this week:
1.[E][ ] dummy 2 (from: Sep 24 2023 at 00:00 to: Oct 10 2023 at 00:00)
```
### `bye` - Exits Bobi

Bobi bids you farewell and closes automatically.

Example of usage:

`bye`

Expected outcome:
```
Bye! Hope you have a good day today. :)
```