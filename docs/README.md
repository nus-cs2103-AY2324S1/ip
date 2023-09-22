# Dukey o(^▽^)o
_Dukey can help you manage your tasks!_

##  Features

### list

List out all current tasks in the task list.

### mark

Mark a task as done.

### unmark

Unmark a task as not done.

### todo

Add a todo task into the task list.

### deadline

Add a deadline task into the task list.

### event

Add an event task into the task list.

### find

Find a task from the task list.

### delete

Delete a task from the task list.


## Usage

### `list` - List out all current tasks in the task list.


Example of usage: 

`list`

Expected outcome:

All current tasks in the list.

```
-------------------------------------------
Here are the tasks in your list:
 1. [T][X] sleep
 2. [E][ ] event (from:3pm to:5pm)
 3. [D][ ] finish homework (by: Sep 22 2023)
 
-------------------------------------------
```

### `mark` - Mark a task as done.

Format: `mark INDEX`

- Marks the task at the specified index as done.
- The index is required.
- The index refers to the index of the task shown in the task list.
- The index must be a positive integer and 
less than the total number of tasks in the list.

Example of usage:

`mark 2`

Expected outcome:

Task 2 is marked as done.

```
-------------------------------------------
Nice! I've marked this task as done:(≧▽≦)

[E][X] event (from:3pm to:5pm)
 
-------------------------------------------
```


### `unmark` - Unmark a task as not done.

Format: `unmark INDEX`

- Unmarks the task at the specified index as not done.
- The index is required.
- The index refers to the index of the task shown in the task list.
- The index must be a positive integer and 
less than the total number of tasks in the list.

Example of usage:

`unmark 2`

Expected outcome:

Task 2 is unmarked as not done.

```
-------------------------------------------
Ok, I've marked this task as not done yet:(≧▽≦)

[E][ ] event (from:3pm to:5pm)
 
-------------------------------------------
```

### `todo` - Add a todo task.

Adds a todo task into the task list and returns the current
number of tasks in the list.

Format: `todo DESCRIPTION`

- A description is required.
- No repeat tasks are allowed to be added into the list. 
(e.g. if `todo homework`) is already in the task list, it cannot be added again.

Example of usage:

`todo project work`

Expected outcome:

The task with the description 'project work' is added to the list.
Dukey returns with the new total number of tasks in the list.

```
-------------------------------------------
Got it. I've added this new todo:

[T][ ] project work
 
Now you have 4 tasks in the list.
-------------------------------------------
```

### `deadline` - Add a deadline task.

Adds a deadline task into the task list and returns the current
number of tasks in the list.

Format: `deadline DESCRIPTION /by DEADLINE`

- A description is required.
- The deadline for the task must be given. And the format of the 
date must be in the format `YYYY-MM-DD`.

Example of usage:

`deadline finish readings /by 2023-09-22`

Expected outcome:

The deadline task with the description 'finish readings'
is added to the list. Dukey returns with the new total number of 
tasks in the list.

```
-------------------------------------------
Got it. I've added this new deadline:

[D][ ] finish readings (by: Sep 22 2023)
 
Now you have 5 tasks in the list.
-------------------------------------------
```

### `event` - Add an event task.

Adds an event task into the task list and returns the current
number of tasks in the list.

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

- A description is required.
- A start time is required.
- An end time is required.

Example of usage:

`event showcase /from 2pm /to 5pm`

Expected outcome:

The event task with the description 'showcase'
is added to the list. Dukey returns with the new total number of tasks in the list.

```
-------------------------------------------
Got it. I've added this new event:

[E][ ] showcase (from:2pm to:5pm)
 
Now you have 6 tasks in the list.
-------------------------------------------
```

### `find` - find a task from the task list.

Finds the task(s) in the task list whose description contain the keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

- At least one keyword is required. `[MORE_KEYWORDS]` are optional.

Example of usage:

`find showcase`

Expected outcome:

Dukey returns a list of tasks whose description contains the keywords 
if there are tasks matching the keywords.

```
-------------------------------------------
Here are the matching tasks in your list:(≧▽≦)

[E][ ] showcase (from:2pm to:5pm)
 
-------------------------------------------
```

### `delete` - deletes a task from the task list.

Deletes a specified task in the task list.

Format: `delete INDEX`

- Deletes the task at the specified index.
- The index is required.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer and must be less than the 
total number of tasks in the list.

Example of usage:

`delete 6`

Expected outcome:

Dukey deletes the task from the task list and returns the new number
of tasks in the list.

```
-------------------------------------------
Noted, I've removed this task:

[E][ ] showcase (from:2pm to:5pm)

Now you have 5 tasks in the list
-------------------------------------------
```

### `bye` - say bye to Duke!

Duke will save the task list and reply. Dukey will still be usable afterwards.

Format: `bye`

- Dukey will reply

Example of usage:

`bye`

Expected outcome:

Dukey replies to the bye. Data file saved to hard drive.

```
Bye bye! Hope to see you again! o(^w^)o
```

