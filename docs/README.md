# PaimonBot's User Guide

PaimonBot is your friendly and ever-curious companion in the digital world. She's here 
to assist travellers in **managing their tasks** (todos, deadlines and events). 
Optimised for use via a **Command-Line Interface (CLI)**, while still retaining the
advantages of a **Graphical User Interface (GUI)**. 

## Features 

### 1. Adding a task
Adds a task to the list of tasks. There are 3 types of tasks that can be added:
* Todos
  * A description of the task needs to be provided
* Deadlines
  * A description of the task needs to be provided
  * A date and time needs to be given to specify the deadline
* Events
  * A description of the task needs to be provided
  * A date and time needs to be given to specify the start of the event
  * A date and time needs to be given to specify the end of the event

### 2. Deleting a task
Deletes the task from the list. To delete a task, you need to specify the index of the
task in the list.

### 3. Marking a task
Marks a task as done in the list. To mark a task, you need to specify the index of the
task in the list. 

### 4. Unmarking a task
Marks a task as undone in the list. To unmark a task, you need to specift the index of the
task in the lsit. 

### 5. Listing tasks
Shows the list of the current tasks in the list. 

### 6. Finding tasks by keyword
Searches the task(s) in the list with the given keyword. 

### 7. Sorting tasks
Sorts the tasks in ascending order of their dates and times. **Only applies to deadlines 
and events**. 

### 8. Exiting the program
Exits the chatbot. 

## Usage

### 1. `todo` - Adding a todo

Format: 

`todo DESCRIPTION`

Example of usage: 

`todo complete commissions`

Expected outcome:

```
Got it. Task successfully added: 
[T][] complete commissions 
Now you have 8 tasks in the list. 
```

### 2. `deadline` - Adding a deadline

Format: 

`deadline DESCRIPTION /by yyyy-MM-dd HH:mm`

Example of usage:

`deadline clear spiral abyss /by 2023-09-20 23:59`

Expected outcome:

```
Got it. Task successfully added: 
[D][] clear spiral abyss (by: 2023-09-20 23:59) 
Now you have 8 tasks in the list. 
```

### 3. `event` - Adding an event
Format:

`event DESCRIPTION /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm`

Example of usage:

`event new wish banner /from 2023-08-23 18:00 /to 2023-09-10 08:00`

Expected outcome:

```
Got it. Task successfully added: 
[E][] yelan wish banner (from: 2023-08-23 18:00 to: 2023-09-10 08:00) 
Now you have 8 tasks in the list. 
```

### 4. `delete` - Deleting a task

Format:

`delete INDEX`

Example of usage:

`delete 8`

Expected outcome:

```
Noted. I've removed this task: 
[T][] complete commissions 
Now you have 7 tasks in the list. 
```

### 5. `mark` - Marking a task

Format: 

`mark INDEX`

Example of usage: 

`mark 8`

Expected outcome: 

```
Nice! I've marked this task as done:
[T][X] complete commissions`
```

### 6. `unmark` - Unmarking a task

Format:

`unamrk INDEX`

Example of usage: 

`unmark 8`

Expected outcome:

```
Nice! I've un-marked this task:
[T][] complete commissions
```

### 7. `list` - Listing out the current tasks

Format: 

`list`

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][X] complete commissions
2. [D][] clear spiral abyss (by: 2023-09-20 23:59)
3. [E][X] yelan wish banner (from: 2023-08-23 18:00 to: 2023-09-10 08:00)
```

### 8. `find` - Finding the task(s) which satisfies the keyword

Format: 

`find KEYWORD`

Example of usage: 

`find yelan`

Expected outcome:

```
Here are the tasks with "yelan": 
1. [E][X] yelan wish banner (from: 2023-08-23 18:00 to: 2023-09-10 08:00)
```

### 9. `sort` - Sorting the tasks

Format:

`sort TASKs`

Example of usage: 

`sort deadlines`

Expected outcome: 

```
Here are your sorted deadlines in ascending order:
1. [D][X] farm crystals (by: 2023-06-10 17:00)
2. [D][] finish new event (by: 2023-09-20 14:30) 
3. [D][] clear spiral abyss (by: 2023-09-20 23:59) 
```

### 10. `bye` - Exiting the chatbot

Format: 

`bye`

Example of usage: 

`bye`

Expected outcome : 

```
Bye Bye Traveller! See you soon :D
```
