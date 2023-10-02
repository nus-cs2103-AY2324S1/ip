# User Guide for **Alfred**  



Alfred is a To-Do List app which allows users to manage different types of tasks and mark them as complete or not. Alfred acts as your personal assistant to follow your commands to help you carry out your commands. 

## Different types of Tasks

To begin, here is an introduction of the tasks you can add to your To-Do List. 

### Todo 

The todo task is the simplest of all tasks and would only consist of the task description. 

Format: `todo TASK_DESCRIPTION`

Examples:
 
* `todo Wash the Car` 
* `todo Pack the bookshelves`
* `todo Fix the lights` 

### Deadline 

The deadline task consists of a description and a deadline to when the task should be completed by. 

Format: `deadline TASK_DESCRIPTION /by DEADLINE` 

DEADLINE Format: `YYYY-MM-DDTHH:MM:SS` 
(Y: Year, M: Month, D: Day, H: Hour, M: Minute, S: Second )

Examples: 

* `deadline CS2100 Assignment 1 /by 2023-09-18T12:00:00`
* `deadline Pay John back $10 /by 2024-01-10T23:30:00`
* `deadline Geography Homework pg 5 - 10 /by 2023-12-12T09:00:00`

### Event 

The event task consist of a description, a start date and an end date. 

Format: `event TASK_DESCRIPTION /from START /to END`

START/END Format: `YYYY-MM-DDTHH:MM:SS` 
(Y: Year, M: Month, D: Day, H: Hour, M: Minute, S: Second )

* `event Japan Trip /from 2023-12-01T09:00:00 /to 2023-12-14T09:00:00`
* `event CCA Camp /from 2023-11-01T06:00:00 /to 2023-11-04T18:00:00`

## Features 

### Listing all tasks: `list`

Shows a list of all the different types of tasks added before. 

Format: `list` 

Expected outcome: 

All the different tasks that have been added prior to the command. 

```
1. [T] [] History Homework pg 10 - 15
2. [T] [] Geography Homework pg 5 - 10
3. [D] [] Chemistry Quiz 4 (by: DECEMBER 12 2023, 23:59:59)

```

### Marking a task as complete: `mark`

Marks the task of the specified index as complete. 

Format : `mark INDEX` 

* Marks the task at the specified `INDEX` as complete.
* Index is based off the numbering when `list` is called.
* The index **must be a postive integer.** 
* By calling `list`, you can see if an item is already completed if it has an 'X' next to it.
* Nothing happens if a `mark` is called on a task that is already completed. 
* `mark` can be undone by calling `unmark` on the same INDEX. 

Examples: 

* If calling `list` gives : 
```
1. [T] [] History Homework pg 10 - 15
2. [T] [] Geography Homework pg 5 - 10
3. [D] [] Chemistry Quiz 4 (by: DECEMBER 12 2023, 23:59:59)
``` 
Calling `mark 1` gives: 
```
1. [T] [X] History Homework pg 10 - 15
2. [T] [] Geography Homework pg 5 - 10
3. [D] [] Chemistry Quiz 4 (by: DECEMBER 12 2023, 23:59:59)
``` 
Following that, calling `mark 3` gives: 
```
1. [T] [X] History Homework pg 10 - 15
2. [T] [] Geography Homework pg 5 - 10
3. [D] [X] Chemistry Quiz 4 (by: DECEMBER 12 2023, 23:59:59)
``` 
Expected Outcome: 
An 'X' will be added to the [] for the specified task in `list` to become `[X]`.
From `[]` to `[X]`

### Unmarking a task as incomplete: `unmark` 

Unmarks the task of the specfied index as incomplete.

Format : `unmark INDEX` 

* Unmarks the task at the specified `INDEX` as incomplete.
* Index is based off the numbering when `list` is called.
* The index **must be a postive integer.** 
* By calling `list`, you can see if an item is incomplete if it does not have an 'X' next to it.
* Nothing happens if an `unmark` is called on a task that is incomplete. 

Examples: 

* If calling `list` gives : 
```
1. [T] [X] History Homework pg 10 - 15
2. [T] [X] Geography Homework pg 5 - 10
3. [D] [] Chemistry Quiz 4 (by: DECEMBER 12 2023, 23:59:59)
``` 
Calling `unmark 1` gives: 
```
1. [T] [] History Homework pg 10 - 15
2. [T] [X] Geography Homework pg 5 - 10
3. [D] [] Chemistry Quiz 4 (by: DECEMBER 12 2023, 23:59:59)
``` 
Following that, calling `unmark 2` gives: 
```ss
1. [T] [] History Homework pg 10 - 15
2. [T] [] Geography Homework pg 5 - 10
3. [D] [] Chemistry Quiz 4 (by: DECEMBER 12 2023, 23:59:59)
``` 

Expected Outcome: 
Assuming the task is complete, an 'X' will be removed from the `[X]` for the specified task in `list` and becomes `[]`.
From `[X]` to `[]`

### Deleting a task: `delete`

Deletes the specfied task from the list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`
* Index is based off the numbering when `list` is called.
* The index **must be a positive integer.** 
* If a task is deleted, all the tasks with a larger `INDEX` than the deleted task will be reduced by 1. Those with smaller `INDEX` remains unchanged. 

Examples: 

* If calling `list` gives : 
```
1. [T] [X] History Homework pg 10 - 15
2. [T] [X] Geography Homework pg 5 - 10
3. [D] [] Chemistry Quiz 4 (by: DECEMBER 12 2023, 23:59:59)
4. [E] [] Japan Trip (from: MARCH 15 2023, 08:00 to: MARCH 25 2023, 08:00)
``` 
Calling `delete 2` gives : 
```
1. [T] [X] History Homework pg 10 - 15
2. [D] [] Chemistry Quiz 4 (by: DECEMBER 12 2023, 23:59:59)
3. [E] [] Japan Trip (from: MARCH 15 2023, 08:00 to: MARCH 25 2023, 08:00)
``` 
Calling `delete 3` gives: 
```
1. [T] [X] History Homework pg 10 - 15
2. [D] [] Chemistry Quiz 4 (by: DECEMBER 12 2023, 23:59:59)
```
### Locating tasks by task description: `find`
 
Finds tasks whose task description contain the given keyword. 

Format: `find KEYWORD` 

* The search is case-sensitive. e.g `find Physics` will not match with `physics`
* The task description, date and time are all searched. `find MARCH` will match with `Japan Trip (from MARCH 10 2023, 08:00 to: MARCH 25 2023, 08:00)`
* Keyword does not have to be a full word. e.g `find Q` will match with `Chemistry Quiz` and `Physics Quiz`.

Examples: 
* In a list of : 
```
1. [T] [X] History Homework pg 10 - 15
2. [T] [X] Geography Homework pg 5 - 10
3. [D] [] Chemistry Quiz 4 (by: DECEMBER 12 2023, 23:59:59)
4. [E] [] Japan Trip (from: MARCH 15 2023, 08:00 to: MARCH 25 2023, 08:00)
``` 
`find History` will give : 
```
Here are the matching tasks in your list: 
    [T] [X] History Homework pg 10 - 15
```
`find Homework` will give : 
```
Here are the matching tasks in your list: 
    [T] [X] History Homework pg 10 - 15
    [T] [X] Geography Homework pg 5 - 10
```
`find H` will give : 
```
Here are the matching tasks in your list:
    [T] [X] History Homework pg 10 - 15
    [T] [X] Geography Homework pg 5 - 10
    [E] [] Japan Trip (from: MARCH 15 2023, 08:00 to: MARCH 25 2023, 08:00)
``` 

### Sorting the list of tasks : `sort` 

Sorts the list in the task list according to Tag then Dates. 

Format: sort 

* The list is firstly sorted by their Tag. T comes first, then D then E. 
* Within the tags themselves, they are further sorted by their dates. 
* For deadline tasks, the earliest deadline will be first and latest deadline will be last.
* For event tasks, they will be sorted based on their start date. The earliest start date will be first and latest start date will be last. (End dates do not affect the order at all). 

Examples: 
* In a list of: 
```
1. [E] [] Japan Trip (from MARCH 10, 2023, 08:00 to: MARCH 25, 08:00)
2. [D] [] Physics Quiz 4 (by: JANUARY 2024, 23:59:59)
3. [T] [] Geography Homework pg 5 
4. [D] [] Chemistry Quiz 3 (by: DECEMBER 2023, 23:59:59)
5. [T] [] History Homework pg 17 
6. [E] [] CCA Camp (from APRIL 10, 2023, 09:00 to: APRIL 13, 2023, 09:00)
``` 
Calling `sort` gives :
``` 
1. [T] [] History Homework pg 17 
2. [T] [] Geography Homework pg 5 
3. [D] [] Chemistry Quiz 3 (by: DECEMBER 2023, 23:59:59)
4. [D] [] Physics Quiz 4 (by: JANUARY 2024, 23:59:59)
5. [E] [] Japan Trip (from MARCH 10, 2023, 08:00 to: MARCH 25, 08:00)
6. [E] [] CCA Camp (from APRIL 10, 2023, 09:00 to: APRIL 13, 2023, 09:00)
```

### Exiting the program: `bye` 

Exits the program.

Format: `Bye`

Expected Outcome: 

```
Goodbye. Hope to be of service again soon!
```
