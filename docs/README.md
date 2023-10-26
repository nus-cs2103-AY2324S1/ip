# Duke User Guide


This chatbot's name is Duke. You can tell him what ever 
tasks you plan to do, and he will help you remember them!

In addition, it **_automatically saves your list on your computer even 
if you accidentally close it_**. So, your tasks are "safe" and you can
get back to it anytime.

## Command Summary

add todo task: `todo [task name]`

add deadline task: `deadline [task name] /by [yyyy-mm-dd]`

add event task: `event [task name] /from [yyyy-mm-dd] /to [yyyy-mm-dd]`

show the whole list: `list`.

mark task as done: `mark [index]`

mark task as undone: `unmark [index]`

delete task: `delete [index]`

find task: `find [substring of the task name you want to find]`

show statistics: `stats`

sort the list: `sort by start date` or `sort by end date`

say goodbye: `bye`

## Command details

### "todo":

Format: `todo [task name]`

Example use: `todo eat`

Use this command to add a todo task which has no start date nor end
date.

Expected outcome:

    Got it. I've added this task:
    [T][ ] eat
    Now you have 4 tasks in the list.
    

### "deadline":

Format: `deadline [task name] /by [yyyy-mm-dd]`

Example use: `deadline read book /by 2022-01-01`

Use this command to add a deadline task which has only end
date, but does not have a start date.

Expected outcome:
    
    Got it. I've added this task:
    [D][ ] read book (by: 12-29-2023)
    Now you have 4 tasks in the list.

### "event":

Format: `event [task name] /from [yyyy-mm-dd] /to [yyyy-mm-dd]`

Example use: `event sleep /from 2022-01-01 /to 2022-01-02`

Use this command to add an event task which has both start and end
date.

Expected outcome:

    Got it. I've added this task:
    [E][ ] sleep (from 12-10-2023 to: 12-11-2023) 
    Now you have 4 tasks in the list.

### "list":

Format: `list`.

Example use: `list`

Use this command to view all the tasks currently in your list.

Expected outcome:

**Duke will show the whole list**

### "mark":

Format: `mark [index]`

Example use: `mark 1`

Use this command to mark the task at given index as "done".

Expected outcome:

    Following task is marked as done:
    1. [T][X] eat

### "unmark":

Format: `unmark [index]`

Example use: `unmark 1`

Use this command to mark the task at given index as "undone".

Expected outcome:

    Following task is marked as undone:
    1. [T][ ] eat

### "delete":

Format: `delete [index]`

Example use: `delete 1`

Use this command to delete the task at given index.

Expected outcome:

    Noted. I've removed this task:
    [T][ ] read book
    Now you have 4 tasks in the list.

### "find":

Format: `find [substring of the task name you want to find]`

Example use: `find book`

Use this command to search tasks which contain given substring.
In the example given above, Duke shall return "read book" and "return
book" if these two tasks are in your list.

Expected outcome:

    Here are the matching tasks in your list:
    1. [T][ ] read book

### "stats":

Format: `stats`

Example use: `stats`

Use this command to see how many each type of task are in your list.

Expected outcome:

    You have:
    ~ 1 todo.
    ~ 1 deadline.
    ~ 2 event.

### "sort":

Format: `sort by start date` or `sort by end date`

Example use: `sort by start date` or `sort by end date`

Use this command to sort your list in ascending order of start date
or end date

Expected outcome:

**Duke will display the new sorted list.**

### "bye":

Format: `bye`

Example use: `bye`

Duke will also say goodbye to you :).

Expected outcome:

    Bye. Hope to see you again soon!


