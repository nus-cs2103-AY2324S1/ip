# User Guide for johnnythesnake bot

## Summary of bot capabilities
Johnnythesnake aims to help users:
1. Keep track of their tasks, such as todos, deadlines and events.
2. Add and delete tasks
3. Save their tasks to a storage and load their tasks to a storage
4. Mark the tasks as done or undone
## Features 

### Feature 1. Adding a Todo task into the task list
A todo task is simply a task that has to be done without deadlines

### Feature 2. Adding a Deadline task into the task list
A Deadline is a task that has a fixed deadline to be completed by.

### Feature 3. Adding an Event task into the task list
An Event is a task that has a start time and end time to it.

### Feature 4. Deleting tasks
Tasks can either be deleted one at a time or all at once

### Feature 5. Mark/Unmark tasks
Tasks can be marked/unmarked one at a time or all at once

### Feature 6. Finding tasks by specified keyword
Users can find tasks containing a certain keyword



## Usage

### `List` - Shows user their task list


Example of usage: 

`list`

Expected outcome:

A list of the tasks will appear

```
Here are the tasks in your list:
1.[D][ ] fly(by: Apr 01 2024 19:00)
```

### `Todo` - Adds a todo task into the tasklist


Example of usage:

`Todo cs2103 project`

Expected outcome:

a new Todo task called cs2103 project will be added to the list

```
Got it. I've added this task:
[T][ ] cs2103 project
Now you have 2 tasks in the list.
```

### `Deadline` - Adds a deadline task into the tasklist


Example of usage:

`Deadline BT3103 Homework /by 2023/09/05 23:59`

Expected outcome:

a new deadline task called BT3103 Homework will be added to the task list

```
Got it. I've added this task:
[D][ ] BT3103 Homework (by: Sep 05 2023 23:59)
Now you have 3 tasks in the list.
```

### `Event` - Adds an event task into the tasklist


Example of usage:

`Event My birthday /from 2023/10/04 00:00 /to 2023/10/04 23:59`

Expected outcome:

a new event task called My birthday will be added to the task list

```
Got it. I've added this task:
[E][ ] My birthday (from Oct 04 2023 00:00 to: Oct 04 2023 23:59)
Now you have 4 tasks in the list.
```

### `Mark` - marks a task as done
users can also mark all tasks at once

Example of usage:

`Mark 3`

Expected outcome:

The 3rd task in the list will be marked

```
Nice! I've marked this task as done:
[D][X] BT3103 Homework (by: Sep 05 2023 23:59)
```

### `Unmark` - unmarks a task 
users can also unmark all tasks at once

Example of usage:

`Unark 3`

Expected outcome:

The 3rd task in the list will be marked

```
Ok, I've marked this task as not done yet:
[D][ ] BT3103 Homework (by: Sep 05 2023 23:59)
```

### `Delete` - deletes 1 or all tasks from the task list
Users can delete either one or all task

Example of usage:

`Delete 3`

Expected outcome:

The 3rd task in the list will be deleted

```
Noted. I've removed this task:
[D][ ] BT3103 Homework (by: Sep 05 2023 23:59)
Now you have 3 tasks in the list.
```

### `Find` - Finds the task containing the keyword searched for

Example of usage:

`Find cs2103`

Expected outcome:

Tasks with the string "2103" will be shown to the users

```
Here are the matching tasks in your list:
1.[T][ ] cs2103 project
```

### `Bye` - Saves the current task list and exits the GUI

Example of usage:

`bye`

Expected outcome:

Current task list will be saved and GUI will close in 1 second.

```
Goodbye! Have a great day!
```