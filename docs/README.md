# CR7 Chatbot User Guide

## Features 

### Adding and Deleting of new tasks

#### Adding tasks
The chatbot allows you to create various types of new tasks that you can store in the chatbot. The chatbot supports these task types:

1. ToDo tasks
   - Tasks with a description but no time constraints. To create a ToDo task, type _todo (description)_.
2. Deadline tasks
   - Tasks with a description and a deadline. To create a Deadline task, type _deadline (description) /by (deadline)_.
3. Event tasks
   - Tasks with a description and a start and end time. To create an Event task, type _event (description) /from (start time) /to (end time)_.
4. Fixed Duration tasks
   - Tasks with a description and a fixed duration. To create a Fixed Duration task, type _fixed (description) /for (duration)_.

***Note that for Deadline and Event tasks, the chatbot only supports entering the date and time in the following formats:***

   For Dates, 
   1. yyyy-MM-dd (e.g 2010-10-15)
   2. dd.MM.yyyy (e.g 27.04.2001)
   3. MM/dd/yyyy (e.g 11/23/2005)

   For Times,
   1. HH:mm (e.g 1632)
   2. HHmm (e.g 1632)
   3. hh:mm AM/PM (e.g 4:32 pm)
      
All task timings **MUST** include both date and time, starting with date followed by time.

#### Deleting tasks
To delete a task that you have created, type delete followed by the the number of the task in the task list (e.g _delete 2_). To check which number the task is in the task list, you may use the following feature below:

### Listing of tasks in the task list

The chatbot allows you to list out all the tasks that you have previously created in the order in which they are created.

To list out all your tasks, simply type the command _list_

### Marking and Un-marking of tasks 

The chatbot allows you to mark out tasks that you have already completed. To mark a task as complete, simple type mark followed by the number of the task in the task list (e.g _mark 2_). 

What if accidentally marked the wrong task, or realised it is not complete? No need to worry, you can also unmark the task in the same way that you have marked it by typing _unmark_ instead (e.g _unmark 2_).

### Searching for tasks

The chatbot also allows you to search for tasks matching a specific description. Suppose I want to search for a task with the keyword _run_ in the description:
   1. Type _search run_
   2. The chatbot will automatically list out all tasks with descriptions containing the keyword _run_
   3. If no tasks containing the _run_ keyword exists, the chatbot will return the following message: ***There are no tasks matching your description. Please try another search keyword***

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
