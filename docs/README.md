# CC ChatBot User Guide

## Features

### Feature - Add tasks
> #### todo 
A todo is a task that has a description, but no deadlines

**Usage** - `todo {task description}`

**For example:**

`todo work out`

**Expected Output:**

```
Got it. I've added 1 this task:
    [T][ ] work out
Now you have 1 tasks in the list.
```

>#### deadline

A deadline is a task that has a description and a deadline.

**Usage** - `deadline {description} by: {deadline}`

**For example:**

`deadline CS2103T ip by: 2023-09-22`

**Expected Output:**

```
Got it. I've added 1 this task:
    [D][ ] CS2103T ip by: Sept 22 2023
Now you have 2 tasks in the list.
```

>#### event

An event is a task that has a description, from date and to date.

**Usage** - `event {description} from {starting date} to {ending date}`

**For example:**

`event Anna's birthday from Mon to Fri`

**Expected Output:**

```
Got it. I've added 1 this task:
    [E][ ] Anna's birthday from Mon to Fri
Now you have 3 tasks in the list.
```

### Feature - Find tasks
>#### find

find function can find specific tasks' description that contains the the key word.

**Usage** - `find {description}`

**For example:**

`find birthday`

**Expected Output:**

```
Here are the matching tasks in your list:  
1.[E][ ] Anna's birthday from Mon to Fri
```
### Feature - list tasks
>#### list

list function prints out all the tasks on memory.

**Usage** - `list`

**For example:**

`list`

**Expected Output:**

```.
Here are the tasks in your list:
1.[T][ ] work out
2.[D][ ] CS2103T ip by: Sept 22 2023
3.[E][ ] Anna's birthday from Mon to Fri
```
### Feature - Delete tasks
>#### delete

delete function can delete any task based on the item number.

**Usage** - `delete {item number}`

**For example:**

`delete 1`

**Expected Output:**

```
Noted. I've removed this task:
[T][ ] work out
Now you have 2 tasks in the list.
```

### Feature - mark tasks
>#### mark

mark function can mark any task as done based on the item number.
Tasks that are already marked, cannot be marked again.  

**Usage** - `mark {item number}`

**For example:**

`mark 1`

**Expected Output:**

```
Nice! I've marked this task as done:
[D][ ] CS2103T ip by: Sept 22 2023
```

**Try to mark it again:**

`mark 1`

**Expected Output:**

```
This task has been marked already.
```


### Feature - unmark tasks
>#### unmark

unmark function can mark any task as undone based on the item number.
Tasks that are already unmarked, cannot be unmarked again.

**Usage** - `unmark {item number}`

**For example:**

`unmark 1`

**Expected Output:**

```
OK, i've marked this task as not done yet:  
[D][ ] CS2103T ip by: Sept 22 2023
```

**Try to unmark it again:**

`unmark 1`

**Expected Output:**

```
This task has been marked as undone already.
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