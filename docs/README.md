# User Guide for Ekud Chatbot

Thank you for using the Ekud Chatbot! As a chatbot, my aim is to help you organise
your schedule with a task list.

## Features 



### 1. Display task list

Lists out all of the tasks in your task list.

### 2. Add Todo Task to task list

Adds ToDo task to task list. (ToDo tasks are tasks that have no specific deadline, or time range.)


### 3. Add Deadline Task to task list

Adds Deadline task to task list. (Deadline tasks are tasks need to be completed by a specific end date.)

### 4. Add Event Task to task list

Adds Event task to task list. (Event tasks are tasks have a specified start and end date.)


### 5. Mark Task as complete

Marks a task as complete in task list

### 6. Delete Task

Removes a task from task list

### 7. Find tasks by keyword

Finds tasks relating to a particular keyword


### 8. Find tasks by time frame

Finds tasks that either occur or have a deadline during a given time period.

### 9. Close chatbot

Exits the chatbot and closes the user interface.


## Usage


### `list` - Lists tasks in task list

Lists out all of the tasks in your task list.

Syntax: `list`

Expected outcome:
A list containing all recorded tasks will be shown.

```
------------------------------------------------------------
1. [T][X] eat supper
2. [E][X] lunch appointment (from: 12-12-2023 12:00 to: 12-12-2023 14:00)
------------------------------------------------------------
```


### `todo` - Add Todo Task

Adds ToDo task to task list. (ToDo tasks are tasks that have no specific deadline, or time range.)

Syntax: `todo <Name of task>`

Example of usage: `todo homework`

Expected outcome:

Todo task will be added to task list.

```
------------------------------------------------------------
Got it. I've added this task: 
  [T][ ] homework
Now you have 3 tasks in the list.
------------------------------------------------------------
```

### `deadline` - Add Deadline Task

Adds Deadline task to task list. (Deadline tasks are tasks need to be completed by a specific end date.)

Syntax: `deadline <Name of task> /by <Due date and time>`

Example of usage: `deadline submit homework /by 20 Sep 2023 2359`

Expected outcome:
Deadline task will be added to task list.

```
------------------------------------------------------------
Got it. I've added this task: 
  [D][ ] submit homework (by: 20-09-2023 23:59)
Now you have 4 tasks in the list.
------------------------------------------------------------
```

### `event` - Add Event Task

Adds Event task to task list. (Event tasks are tasks have a specified start and end date.)

Syntax: `event <name of task> /from <Date and time of event start> /to <Date and time of event end>`

Example of usage: `event dinner appointment /from 20 Sep 2023 1800 /to 20 Sep 2023 1900`

Expected outcome: Event task will be added to task list.
```
------------------------------------------------------------
Got it. I've added this task: 
  [E][ ] dinner appointment (from: 20-09-2023 18:00 to: 20-09-2023 19:00)
Now you have 5 tasks in the list.
------------------------------------------------------------
```

### `mark` - Mark Task as done

Marks a task as done.

Syntax: `mark <The task number in the list>`

Example of usage: `mark 3`

Expected outcome: Task will be marked as done.
```
------------------------------------------------------------
Nice! I've marked this task as done: 
  [T][X] homework
------------------------------------------------------------
```

### `delete` - Delete Task from task list
Removes task from task list.

Syntax: `delete <The task number in the list>`

Example of usage: `delete 1`

Expected outcome: Task will be removed from task list
```
------------------------------------------------------------
Noted. I've removed this task: 
  [T][X] eat supper
Now you have 4 tasks in the list.
------------------------------------------------------------
```

### `find` - Find all tasks with a given keyword
Finds tasks relating to a particular keyword

Syntax: `find <Keyword to search for>`

Example of usage: `find appointment`

Expected outcome: A list of tasks containing the keyword will be displayed
```
Here are the matching tasks in your list:
------------------------------------------------------------
1. [E][X] lunch appointment (from: 12-12-2022 12:00 to: 12-12-2022 14:00)
2. [E][ ] dinner appointment (from: 20-09-2023 18:00 to: 20-09-2023 19:00)
------------------------------------------------------------
```


### `view schedule` - Lists all tasks in a given time interval
Finds tasks that either occur or have a deadline during a given time period.

Syntax: `view schedule /from <Start of time frame> /to <End of time frame>`

Example of usage: `view schedule /from 20 Sep 2023 0000 /to 20 Sep 2023 2359`

Expected outcome: A list of event and deadline tasks that occur during the specified time frame will be shown
```
Here are the matching tasks in your list:
------------------------------------------------------------
1. [E][ ] dinner appointment (from: 20-09-2023 18:00 to: 20-09-2023 19:00)
2. [D][ ] submit homework (by: 20-09-2023 23:59)
------------------------------------------------------------
```

### `bye` - Exits the platform

Exits the chatbot and closes the user interface.

Syntax: `bye`

Expected outcome: A goodbye message is shown, and the user interface will then close by itself
```
------------------------------------------------------------
Bye. Hope to see you again soon!
------------------------------------------------------------
```


