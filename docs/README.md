# User Guide for Hong

I am your personal assistant and will help you to keep track of your tasks!

## Features 

### List all tasks `list`

Lists all the tasks that are currently stored.

### add a todo task `todo`

Adds a todo task

### add an event task `event`

A event task will be added to the task list. DateTimes are processed in YYYY-MM-DD 24HRS format.

### add a deadline task `deadline`

A deadline task will be added to the task list. DateTimes are processed in YYYY-MM-DD 24HRS format.

### snooze an event/deadline `snooze`

Snoozes a deadline or event. For deadlines, the due date is increased by 1 day. For events, the end date is increased by 1 day.

### delete a task `delete`

Deletes the task at that specific index.

### mark a task as done `mark`

Marks a task as done.

### find a task `find`

Find all the similar tasks.

## Usages

### List all tasks `list`

Example of usage: 

`list`

Expected outcome:

A list of all the tasks that are currently stored will be printed.

```
------------------------------------
Here are the tasks in your list:
1.[T][ ] Eat breakfast
2.[D][ ] call mum (by Oct 30 2019 6PM)
------------------------------------
```

### add a todo task `todo`

Example of usage: 

`todo buy chicken rice`

Expected outcome:

A todo task will be added to the task list.

```
------------------------------------
Got it. I've added this task:
[T][ ] buy chicken rice
Now you have 3 tasks in the list.
------------------------------------
```

### add an event task `event`

Example of usage: 

`event run away /from 2019-12-30 1800 /to 2020-01-03 0800`

Expected outcome:

A event task will be added to the task list.

```
------------------------------------
Got it. I've added this task:
[E][ ] run away (from: Dec 30 2019 6PM to: Jan 3 2020 8AM)
Now you have 3 tasks in the list.
------------------------------------
```
### add a deadline task `deadline`

Example of usage: 

`deadline buy bread /by 2019-10-10 0800`

Expected outcome:

A deadline task will be added to the task list.

```
------------------------------------
Got it. I've added this task:
[D][ ] buy bread (by: Oct 10 2019 8AM)
Now you have 3 tasks in the list.
------------------------------------
```

### snooze an event/deadline `snooze`

Example of usage: 

`snooze 1`
`snooze 2`

Expected outcome:

The task will be snoozed.

```
------------------------------------
I have snoozed your deadline! It is now due by Oct 31 2019 6PM
------------------------------------
```
```
------------------------------------
I have snoozed your event's end date! Your event is now from: May 10 2020 9AM to: May 14 2020 4PM
------------------------------------
```
### delete a task `delete`

Example of usage: 

`delete 1`

Expected outcome:

That specific task will be deleted.

```
------------------------------------
Noted. I've removed this task:
[T][ ] buy chicken rice
Now you have 2 tasks in the list.
------------------------------------
```
### mark a task as done `mark`

Example of usage: 

`mark 1`

Expected outcome:

The task will be marked as done.

```
------------------------------------
Nice! I've marked this task as done:
[T][X] run away
------------------------------------
```
### find a task `find`

Example of usage: 

`find run`

Expected outcome:

Lists all the tasks that have the substring present in their task name.

```
------------------------------------
Here are the matching tasks in your list:
1.[T][X] run away
2.[T][ ] run
------------------------------------
```
