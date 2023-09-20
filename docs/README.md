# User Guide for Max

## Features 

### Add tasks

You can add three types of task for Max to remember:
- <b>Todo</b>, which has a description
- <b>Deadline</b>, which has a description and a finish by date and time
- <b>Event</b>, which has a description, a start and an end date and time

### View the list of tasks

You can ask Max to show you the entire list of tasks.

### Mark and unmark tasks as done

You can tell Max to mark or unmark some task as done.

### Set priority for tasks

You can set three different priorities for each task:
- <b>LOW</b>
- <b>MEDIUM</b>
- <b>HIGH</b>

<b>LOW</b> is the default priority for each task.

### Find tasks

You can get Max to show you all the tasks containing a certain keyword.

### Delete tasks

You can ask Max to forget any of the tasks by deleting it.

## Usage

### `todo` - Add a todo

Adds a todo to the list of tasks.

Format:
```
todo DESC

where

DESC: description of the todo
```

Example of usage: 

```
todo finish documentation
```

Expected outcome:

```
Got it. I've added this task:
[T][ ][LOW] finish documentation
Now you have 8 tasks in the list.
```

### `deadline` - Add a deadline

Add a deadline to the list of tasks.

Format:
```
deadline DESC /by FIN_BY_DT

where

DESC: description of the deadline
FIN_BY_DATE: finish by date and time of the deadline
```

Specify finish by date and time as

`dd mmm yyyy - hh:mm`

Example of usage:

```
deadline submit ip /by 22 Sep 2023 - 23:59`
```

Expected outcome:

```
Got it. I've added this task:
[D][ ][LOW] submit ip (by: 22 Sep 2023 - 23:59)
Now you have 9 tasks in the list.
```

### `event` - Add an event

Add an event to the list of tasks.

Format:
```
event DESC /from FROM_DT /to TO_DT

where

DESC: description of the event
FROM_DT: start date and time of the event
TO_DT: end date and time of the event
```

Specify start and end date and time as

`dd mmm yyyy - hh:mm`

Example of usage:

```
event trading seminar /from 21 Sep 2023 - 10:00 /to 21 Sep 2023 - 12:00
```

Expected outcome:

```
Got it. I've added this task:
[E][ ][LOW] trading seminar (from: 21 Sep 2023 - 10:00 to: 21 Sep 2023 - 12:00)
Now you have 10 tasks in the list.
```

### `list` - Display list of tasks

Displays the list of all tasks.

Example of usage:

```
list
```

Expected outcome:

```
Here are the tasks in your list:
1.[T][X][LOW] read book
2.[D][ ][MEDIUM] return book (by: 06 Jun 2023 - 16:00)
3.[E][ ][MEDIUM] project meeting (from: 06 Aug 2023 - 14:00 to: 06 Aug 2023 - 16:00)
4.[T][X][LOW] join sports club
5.[T][ ][HIGH] borrow book
6.[D][ ][MEDIUM] return another book (by: 02 Sep 2023 - 16:00)
7.[E][ ][LOW] another project meeting (from: 30 Aug 2023 - 21:00 to: 30 Aug 2023 - 22:00)
```

### `mark` - Mark task as done

Mark a task as done by referencing its index in the list.

Format:
```
mark INDEX

where

INDEX: index of task to be marked as done
```

Example of usage:

```
mark 8
```

Expected outcome:

```
Nice! I've marked this task as done:
[T][X][LOW] finish documentation
```

### `unmark` - Unmark task as done

Unmark a task as done by referencing its index in the list.

Format:
```
unmark INDEX

where

INDEX: index of task to be unmarked as done
```

Example of usage:

```
unmark 8
```

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][ ][LOW] finish documentation
```

### `change priority` - Change priority of task

Change the priority of a task by referencing its index in the list.

Format:
```
change priority INDEX PRIORITY

where

INDEX: index of task to be change priority for
PRIORITY: new priority of the task which has one of the 
following values - {low, medium, high}
```

Example of usage:

```
change priority 9 high
```

Expected outcome:

```
Noted. I've changed the priority of this task:
[D][ ][HIGH] submit ip (by: 22 Sep 2023 - 23:59)
```

### `find` - Find tasks with keyword

Find all the tasks containing a specific keyword.

Format:
```
find KEYWORD

where

KEYWORD: keyword to find the tasks
```

Example of usage:

```
find book
```

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][X][LOW] read book
2.[D][ ][MEDIUM] return book (by: 06 Jun 2023 - 16:00)
3.[T][ ][HIGH] borrow book
4.[D][ ][MEDIUM] return another book (by: 02 Sep 2023 - 16:00)
```

### `delete` - Delete task

Delete a task by referencing its index in the list.

Format:
```
delete INDEX

where

INDEX: index of task to be marked as done
```

Example of usage:

```
delete 8
```

Expected outcome:

```
Noted. I've removed this task:
[T][ ][LOW] finish documentation
Now you have 9 tasks in the list.
```

### `bye` - Exit the Max bot

Exit the Max bot and save all the current tasks.

Example of usage:

```
bye
```

Expected outcome:

```
Bye. Hope to see you again soon!
```

