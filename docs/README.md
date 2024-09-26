# GlorifiedExcelSheet

## User Guide
### Introduction
- Welcome to **GlorifiedExcelSheet**
- Below are the **essential** features 
and **associated** commands to get you started. 

### Features
__________________________________________________________________________________________
## Creates a TODO task with a description:
Creates a todo task with a description.

**Syntax**:
```
todo [DESCRIPTION STRING]
```

**Example of usage**:
```
=> list

Here are the tasks in your list:
1. [T][X] read book

=> todo play football
```
**Expected output**:
```
Got it I've added this task:
 [T][ ] play football
Now you have 2 tasks in the list.
```
__________________________________________________________________________________________

## Creates a DEADLINE task with a deadline:
Creates a deadline task with a deadline.

**Syntax**:
```
todo [DESCRIPTION STRING] /by [DATE STRING]

DATE STRING FORMAT: YYYY-MM-DD
```

**Example of usage**:
```
=> list

Here are the tasks in your list:
1. [T][X] read book

=> deadline buy milk /by 2020-07-08
```
**Expected output**:
```
Got it I've added this task:
 [D][ ] buy milk (by: Jul 8 2020)
Now you have 2 tasks in the list.
```
__________________________________________________________________________________________

## Creates a EVENT task with to and from:
Creates an event task with to and from.

**Syntax**:
```
event [DESCRIPTION STRING] /from [DATE STRING] /to [DATE STRING]

DATE STRING FORMAT: YYYY-MM-DD
```

**Example of usage**:
```
=> list

Here are the tasks in your list:
1. [T][X] read book

=> event go for soccer practice /from 2020-07-08 /to 2020-07-09
```
**Expected output**:
```
Got it I've added this task:
 [E][ ] go for soccer practice (from: Jul 8 2020 to Jul 9 2020)
Now you have 2 tasks in the list.
```
__________________________________________________________________________________________

## Mark a task as done (by index):
Marks the task at an index in the task list.

**Syntax**:
```
mark [INDEX]
```

**Example of usage**:
```
=> list

Here are the tasks in your list:
1. [T][ ] read book

=> mark 1
```
**Expected output**:
```
Here are the tasks in your list:
1. [T][X] read book
```
__________________________________________________________________________________________

## Unmark a task as done (by index):
Un marks the task at an index in the task list.

**Syntax**:
```
unmark [INDEX]
```

**Example of usage**:
```
=> list

Here are the tasks in your list:
1. [T][X] read book

=> unmark 1
```
**Expected output**:
```
Here are the tasks in your list:
1. [T][ ] read book
```
__________________________________________________________________________________________

## Delete a task (by index):
Deletes a task at an index in the task list.

**Syntax**:
```
delete [INDEX]
```

**Example of usage**:
```
=> list

Here are the tasks in your list:
1. [T][X] read book

=> delete 1
```
**Expected output**:
```
Noted I've removed this task:
 [T][X] read book
Now you have 0 tasks in the list.
```
__________________________________________________________________________________________

## Find a task (by description):
Finds a task by description.

**Syntax**:
```
find [DESCRIPTION STRING]
```

**Example of usage**:
```
=> list

Here are the tasks in your list:
1. [T][X] read book
2. [T][ ] play football

=> find book
```
**Expected output**:
```
Here are the matching tasks in your list:
1.[T][X] read book
```
__________________________________________________________________________________________

## Exceptions:
### Delete index must be more than zero.

**Syntax**:
```
delete -7
```

**Example of usage**:
```
=> list

Here are the tasks in your list:
1. [T][X] read book
2. [T][ ] play football

=> delete -7
```
**Expected output**:
```
:( OOPS!!! Invalid line number :-(
```
__________________________________________________________________________________________
### Todo must be accompanied by a description. No description will throw customised error.
**Syntax**:
```
todo
```

**Example of usage**:
```
=> list

Here are the tasks in your list:
1. [T][X] read book
2. [T][ ] play football

=> todo
```
**Expected output**:
```
:( OOPS!!! todo command must be followed by a space and a string. 
   ERR: STRING INDEX OUT OF BOUNDS
```
__________________________________________________________________________________________
## Special features:

### Duplicate description handling:
- If a task with the same description is added, the task will not be added immediately.
- Instead, a prompt will be shown to the user to confirm if the user wants to add the task.
- If the user confirms with "yes", the task will be added.
- Else if the user types anything else, the task will be discarded.

**Syntax**:
```
todo read book
```

**Example of usage**:
```
=> list

Here are the tasks in your list:
1. [T][X] read book
2. [T][ ] play football

=> todo read book
```
**Expected output**:
```
:( OOPS!!! You already have a task with the same description in your list!
 
   [T][X] read book
   
   If you still want to proceed with adding your new task, type "yes".
   If something else is typed, the task will be discarded.
```
**Continuation of usage**:
```
=> yes
```
**Continuation of expected output**:
```
Got it, I've added this task:
 [T][ ] read book
Now you have 3 tasks in the list.
```
**Continuation of usage**:
```
=> list
```
**Continuation of expected output**:
```
Here are the tasks in your list:
1. [T][X] read book
2. [T][ ] play football
3. [T][ ] read book
```

__________________________________________________________________________________________

## Exiting the application:
Exits the application.

**Syntax**:
```
bye 
```

**Example of usage**:
```
=> list

Here are the tasks in your list:
1. [T][ ] read book

=> bye
```
**Expected output**:
```
<<SYSTEM PERFORMS EXIT AND CLOSES GUI>>
```
