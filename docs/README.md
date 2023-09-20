# CR7 Chatbot User Guide

## Chatbot UI

![Ui](https://github.com/kohkaijie/ip/assets/122248866/875338bb-c5a8-4d75-96d7-e8e3a55bb39c)

## Setup Instructions
1. Download the CR7.jar file onto your desktop.
2. Open your command-line interface and `cd` to the folder where the jar file is located.
3. Run `java -jar CR7.jar` and wait for the chatbot to open.

## Features 

### Adding of new Tasks

#### ToDo tasks
Adds a todo task to your task list.

Format:
> _todo (description)_

Sample Run:
```
todo run
```

#### Deadline tasks
Adds a task with a deadline to your task list. 

Format:
> _deadline (description) /by (deadline)_

Sample Run:
```
deadline watch ronaldo highlights /by 2023-09-20 2100
```

#### Event tasks
Adds a task with a start time and end time to your task list.

Format:
> _event (description) /from (start time) /to (end time)_

Sample Run:
```
event siuuuu /from 2023-09-20 2100 /to 2023-09-20 2200
```

#### Fixed Duration tasks
Adds a task with a fixed duration to your task list.  

Format:
> _fixed (description) /for (duration)_

Sample Run:
```
fixed dillon brooks dance /for 2 hours
```
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

### Deleting tasks

Deletes a task of the given index from the task list.

Format:
> _delete (index)_

Sample Run:
```
delete 3
```
***Note that only numerical inputs are accepted (_delete three_ not allowed) and must be a task index from the list***

### Listing tasks

Lists out all tasks in the task list based on the order they are created.

Format:
> _list_

Sample Run:
```
list
```

### Marking of tasks 

Marks a task of the specified index as complete.

Format:
> _mark (index)_

Sample Run:
```
mark 2
```

### Un-marking of tasks

Marks a task of the specified index as incomplete.

Format:
> _unmark (index)_

Sample Run:
```
unmark 2
```
***Note that the numerical input for marking and unmarking command has the same specifications as the delete command***

### Searching for tasks

Searches for all tasks with a matching description.

Format:
> _find (description)_

Sample Run:
```
find run
```
***Note that empty descriptions are not allowed***


