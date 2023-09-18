# User Guide for GOAT

## Features 

### Todos, Deadlines and Events

GOAT supports 3 kinds of tasks.

### Task finding

GOAT supports finding by description via `find` or finding by date via `list`

### Task editing

GOAT supports task marking, unmarking and changing of dates

## Usage

### `list` - List out the tasks in GOAT

GOAT replies with the tasks in GOAT

`/before yyyy-MM-dd HHmm` - optional argument, GOAT filters the tasks which occur before the given date.

Example of usage:

`list /before 2023-09-16 0000`

Expected outcome:

Returns the tasks before Sep 16 2023 00:00AM

```
Here are the tasks in your list:
1.  [D][ ] return book (by: Jun 07 2023 02:00PM)
2.  [D][ ] return book (by: Sep 15 2023 12:00PM)
```

### `mark i` - Marks the ith task

GOAT marks the ith task

Example of usage:

`mark 1`

Expected outcome:

The 1st task is marked

```
Nice! I've marked this task as done:
  [T][X] read book
```

### `unmark i` - Unmarks the ith task

GOAT unmarks the ith task

Example of usage:

`unmark 1`

Expected outcome:

The 1st task is unmarked

```
Ok, I've marked this task as not done yet
  [T][ ] read book
```

### `todo task` - Creates a todo 

GOAT creates a todo with the given description

Example of usage:

`todo task one`

Expected outcome:

A todo, task one is created

```
Got it. I've added this task:
  [T][ ] task one
Now you have 7 tasks in the list.
```

### `deadline task` - Creates a deadline

GOAT creates a deadline with the given description and by

`/by yyyy-MM-dd HHmm` - compulsory argument. Sets the by of the deadline to the given date

Example of usage:

`deadline task two /by 2023-09-18 1230`

Expected outcome:

A deadline, task two by Sep 18 2023 12:30PM is created

```
Got it. I've added this task:
  [D][ ] task two (by: Sep 18 2023 12:30PM)
Now you have 8 tasks in this list.
```

### `event task` - Creates an event

GOAT creates an event with the given description, from and to

`/from yyyy-MM-dd HHmm` - compulsory argument. Sets the from of the event to the given date
`/to yyyy-MM-dd HHmm` - compulsory argument. Sets the to of the event to the given date

Example of usage:

`event task three /from 2023-09-19 1300 /to 2023-09-19 1400`

Expected outcome:

An event, task three, from Sep 19 2023 01:00PM to Sep 19 2023 02:00PM is created.

```
Got it. I've added this task:
  [E][ ] task three (from: Sep 19 2023 01:00PM to: Sep 19 2023 02:00PM)
Now you have 9 tasks in the list.
```

### `delete i` - Deletes the ith task

GOAT deletes the ith task.

Example of usage: 

`delete 1`

Expected outcome:

The 1st task is deleted

```
Noted. I've removed this task:
  [T][ ] read book
Now you have 8 tasks in the list.
```

### `find x` - Lists the tasks with x

GOAT lists tasks with x in the task description.

Example of usage:

`find ask`

Expected outcome:

Tasks with 'ask' in the description are listed

```
Here are the tasks in you list:
1.  [T][ ] task one 
2.  [D][ ] task two (by: Sep 18 2023 12:30PM)
3.  [E][ ] task three (from: Sep 19 2023 01:00PM to: Sep 19 2023 02:00PM)
```

### `snooze i` - Snoozes the ith task

GOAT changes the time field of the ith task.

`/by yyyy-MM-dd HHmm` - compulsory argument for deadline. Changes the by of the deadline to the given date

`/from yyyy-MM-dd HHmm` - argument for event. Changes the from of the event to the given date
`/to yyyy-MM-dd HHmm` - argument for event. Changes the to of the event to the given date

Example of usage:

`snooze 1 /by 2023-09-20 1000`

Expected outcome:

Snoozes the 1st task to Sep 20 2023 10:00AM

```
Ok! I've edited this task:
  [D][ ] reutrn book (by: Sep 20 2023 10:00AM)
```

