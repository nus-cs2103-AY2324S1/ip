# Botty User Guide

Botty is your all-in-one companion for remembering things you need to do. It's
- CLI based
- Easy to learn
- ~~Fast~~ _SUPER_ FAST to use

All you need to do is,
1. Download it from [here](https://nus-cs2103-ay2324s1.github.io/website/schedule/week4/project.html#3-decide-on-an-overall-project-direction-user-profile-problem-addressed-before-the-tutorial)
2. Double-click it
3. Add you tasks
4. Let it manage your tasks for you!😉

And it is **ABSOLUTELY FREE**

## Features 

### Feature-`list`

Lists the tasks in your tasklist. 

Example of usage:`list`

Expected outcome:

```
list
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][X] read book
     2.[D][X] return book (by: June 6th)
    ____________________________________________________________
```

### Feature-`mark`

Add the ability to mark tasks as done.

Example of usage:`mark (index)`

Expected outcome:

```
mark 1
    ____________________________________________________________
     Nice! I've marked this task as done:
     [T][X] go home
    ____________________________________________________________
```

### Feature-`unmark`

Add the ability to unmark tasks as done.

Example of usage:`unmark (index)`

Expected outcome:

```
unmark 1
    ____________________________________________________________
     Ok, I've marked this task as not done yet:
     [T][] go home
    ____________________________________________________________
```

### Feature-`todo`

Add tasks without any date/time attached to it.

Example of usage:`todo (description)`

Expected outcome:

```
todo cs2101
    ____________________________________________________________
     Got it. I've added this task:
       [T][]cs2101
     Now you have 3 tasks in the list.
    ____________________________________________________________
```

### Feature-`deadline`

Add tasks that need to be done before a specific date/time.

Example of usage:`deadline (description) /by (date)`

Expected outcome:

```
deadline return book /by 2019-10-15
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] return book (by: Oct 15 2019)
     Now you have 6 tasks in the list.
    ____________________________________________________________
```

### Feature-`event`

Add tasks that start at a specific date/time and ends at a specific date/time.

Example of usage:`event (description) /from (date) /to (date)`

Expected outcome:

```
event project meeting /from Mon 2pm /to 4pm
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] project meeting (from: Mon 2pm to: 4pm)
     Now you have 7 tasks in the list.
    ____________________________________________________________
```
### Feature-`delete`

Delete tasks.

Example of usage:`delete (index)`

Expected outcome:

```
list
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][X] read book
     2.[D][X] return book (by: June 6th)
     3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
     4.[T][X] join sports club
     5.[T][ ] borrow book
    ____________________________________________________________

delete 3
    ____________________________________________________________
     Noted. I've removed this task:
       [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
     Now you have 4 tasks in the list.
    ____________________________________________________________
```
### Feature-`find`

Give users a way to find a task by searching for a keyword.

Example of usage:`find (description)`

Expected outcome:

```
find book
    ____________________________________________________________
     Here are the matching tasks in your list:
     1.[T][X] read book
     2.[D][X] return book (by: June 6th)
    ____________________________________________________________
```
### Feature-`undo`

Undo the most recent command.

Example of usage:`undo`

Expected outcome:

```
undo
    ____________________________________________________________
     Task has been undone!
    ____________________________________________________________
```


Acknowledgements

CS2103T Teaching Team

