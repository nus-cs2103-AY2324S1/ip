# User Guide

## Features

### Feature - Add tasks
> #### todo 
A todo is a task that has a description, but no deadlines

**Usage** - `todo {task description}`

**For example:**

`todo finish homework`

**Expected Output:**

```
Added 1 new task
    [T][] finish homework
Now you have 1 tasks in your task scheduler
```

>#### deadline

A deadline is a task that has a description and a deadline.

**Usage** - `deadline {description/deadline}`

**For example:**

`deadline finish project/2023-12-12 01:12`

**Expected Output:**

```
Added 1 new task
    [D][] finish project (by: Dec 12,2023 01:12)
 Now you have 2 tasks in your task scheduler
```

>#### event

An event is a task that has a description, from date and to date.

**Usage** - `event {description/from/to}`

**For example:**

`event travel/2023-12-12 00:00/2023-12-31 00:00`

**Expected Output:**

```
Added 1 new task
    [E][] travel (from: Dec 12,2023 00:00 to: Dec 31,2023 00:00)
 Now you have 3 tasks in your task scheduler
```

### Feature - Find tasks
>#### find

find function can index any task that may or may not contain the full keyword. It is flexible with inputs.

**Usage** - `find {description}`

**For example:**

`find travel` or `find tra` 

**Expected Output:**

```
Tasks that may match your search result...
Entries on memory...
1.[E] [] travel (from: Dec 12,2023 00:00 to: Dec 31,2023 00:00)
```
### Feature - list tasks
>#### list

list function prints out all the tasks on memory.

**Usage** - `list`

**For example:**

`list`

**Expected Output:**

```.
Entries on memory...
1.[T][] finish homework
2.[D][] finish project (by: Dec 12,2023 01:12)
3.[E] [] travel (from: Dec 12,2023 00:00 to: Dec 31,2023 00:00)
```
### Feature - Delete tasks
>#### delete

delete function can delete any task based on the item number.

**Usage** - `delete {item number}`

**For example:**

`delete 1`

**Expected Output:**

```
This task was removed...
[T][] finish homework
 Now you have 2 tasks in your task scheduler
```

### Feature - mark tasks
>#### mark

mark function can mark any task (with an X) based on the item number.
Tasks that are already marked, cannot be marked again.

**Usage** - `mark {item number}`

**For example:**

`mark 1`

**Expected Output:**

```
Nice! Task complete successfully!
[D][X] finish project (by: Dec 12,2023 01:12)
```

### Feature - unmark tasks
>#### unmark

unmark function can mark any task (with an X) based on the item number.
Tasks that are already unmarked, cannot be unmarked again.

**Usage** - `unmark {item number}`

**For example:**

`unmark 1`

**Expected Output:**

```
Sure! Task status unchecked!
[D][] finish project (by: Dec 12,2023 01:12)
```

### Feature - save tasks
>#### save

save function can save all the actions committed since application opening.

**Usage** - `save`

**For example:**

`save`

**Expected Output:**

```
Tasks have been saved to disk!
```