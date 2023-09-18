# ToDoIst User Guide

TodoIst is a desktop app for managing your tasks. 

_Keep track of your tasks using ToDoIst!_

## Features
> Notes:
> * Words in < > are the parameters to be supplied by the user.
>   * E.g. in todo <task>, task is a parameter which can be used as todo eat
> * Commands are case-sensitive.
>   * e.g. todo quiz is acceptable, but TODO quiz and Todo quiz are unacceptable command formats.
> * Some commands do not take in parameters (such as list, bye). As such, extraneous parameters for these commands are unsupported.
>  * e.g. list 123 is not an acceptable format.

### Types of Tasks
1. **ToDos**: basic task with just a description
1. **Deadlines**: tasks with a due date
1. **Events**: task with a specific start and end time
1. **Contact**: task with a person’s contact details

## Usage
**Adding a ToDo** : ``todo <description>``
Adds a todo to the task list.

Example of usage:

``todo call Nellie``

Expected output:

```
Okie! I've added this Todo to your task list! 
T | 0 | call Nellie 
Now you've got 1 task in your list.
```

**Adding an Event** : ``event <description> /from <start> /to <end>``

Adds an event to the task list.

Example of usage:

``event training /from Mon 6pm /to Mon 9pm``

Expected output:

```
Okie! I've added this Event to your task list! 
E | 0 | training | Mon 6pm to Mon 9pm 
Now you've got 1 task in your list.
 ```

**Adding a Deadline** : ``deadline <description> /by <date>``

Adds a deadline to the task list.

> Note: It only supports <date> inputs with the format YYYY-MM-DD or a specific day of the week (e.g. 2023-10-10, Sunday).

Example of usage:

``deadline return book /by 2023-10-10``

Expected output:

``` 
Okie! I've added this Deadline to your task list! 
D | 0 | return book | Sunday
Now you've got 1 task in your list.
```

**Adding a Contact task** : ``contact <name> /@ <mode of contact> /for <reason>``

Adds a task to contact an individual to the task list.

Example of usage:

``contact Joy /@ foojnjoy@gmail.com /for follow-up checkup``

Expected output:

```
Okie! I've added this Deadline to your task list! 
C | 0 | Joy | foojnjoy@gmail.com | follow-up checkup
Now you've got 1 task in your list.
 ```

**Listing all tasks** : ``list``

Shows a list of all tasks in the task list.

Example of usage:

``list``

Expected output:

``` 
1. [T][] call Nellie
2. [E][] training (from: Mon 6pm to: Mon 9pm)
3. [D][] return book (by: Sunday)
4. [C][] Joy @ foojnjoy@gmail.com for follow-up checkup
```

**Marking a task as done** : ``mark <index>``

Marks the tasks at the specific index as ‘done’.

> Note: Marking a 'done' task as 'done' is not allowed.

Example of usage:

``mark 3``

Expected outcome:

``` 
Nice! I've marked this task as done:
[D][X] return book (by: Sunday)
```

**Marking a task as undone** : ``unmark <index>``

Marks the tasks at the specific index as ‘undone’.

> Note: Marking an undone task as 'undone' is not allowed

Example of usage:

``unmark 3``

Expected outcome:

``` 
Nice! I've marked this task as undone:
[D][] return book (by: Sunday)
```

**Deleting a task** : ``delete <index>``

Deletes the tasks at the specified index.

> Note: Deleting a task that doens't exist is not allowed. 

Example of usage:

``delete 1``

Expected outcome:

``` 
Deleted the following task:
[T][] call Nellie
```

**Finding tasks by keyword** : ``find <keyword>``

Finds tasks in the task list that match or contain the keyword.

Example of usage:

``find training``

Expected outcome:

```
Here are the relevant tasks:
1.[E][] training (from: Mon 6pm to: Mon 9pm)
 ```

**Exiting the program**: ``bye``

Exits the program.

Example of usage:

``bye``

Expected outcome:
 
``` 
Bye! CU again!
```
