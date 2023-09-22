# DuckBot User Guide

[//]: # (<body style="background-color: black; color: white; font-family: Arial, sans-serif; padding: 20px;"> </body>)

## <font color = "darkgrey"> Introduction </font>

 Welcome to DuckBot, 

your friendly chatbot for managing tasks and deadlines effortlessly. 

DuckBot is designed to help you keep track of your tasks, events,and deadlines efficiently. 

This user guide will walk you through all the amazing features DuckBot offers 

and provide you with clear instructions on how to use them effectively.</font>


## <font style = "Ariel" color="darkgrey"> Features </font> 



### <font color = "darkcyan"> Managing Tasks </font>


Three types of tasks are handles by DuckBot : 


1) **<font color = "darkcyan">Deadline</font>** - These tasks have specific due dates or deadlines. DuckBot helps you keep track of them.

example- Returning a book on 23rd September 2023 by 12:15.



2) **<font color = "darkcyan">ToDo</font>**  - These tasks don't have specific due dates or deadlines.

example- Todo laundry
    



3) **<font color = "darkcyan">Events</font>**  - These are the events that happen on a prticular date-time and have an end date-time also.

example- Go on a holiday from 25th September 2023 1200 to 28th September 2023 1200






## <font style = "Ariel" color="darkgrey"> Usage </font>


### `todo` -  <font color = "darkcyan" size = "3"> Add a ToDo Task </font>


Adds a ToDo Task


**Example of usage:** ```todo project```



**Expected outcome:** 


Adds a task to the list and displays a message upon succesful addition.




```

Got it. I've added this task:

[T][] project


Now you have 3 tasks in the list.

```


### `deadline` -  <font color = "darkcyan" size = "3"> Add a Deadline Task </font>


Adds a Deadline Task


**Example of usage:** ```deadline return book /by 2023-09-22 2359```



**Expected outcome:**


Adds a Deadline task to the list with date-time and displays a message upon succesful addition.




```

Got it. I've added this task:

[D][] return book (by: Sep 2022 22 23:59)


Now you have 4 tasks in the list.

```

### `event` -  <font color = "darkcyan" size = "3"> Add an Events Task </font>


Adds an Events Task, the date and time entered should follow the format YYYY-MM-DD hhMM


**Example of usage:** ```event attend a meeting /from 2023-09-23 1000 /to 2023-09-23 1200```



**Expected outcome:**


Adds an event task to the list and displays a message upon succesful addition.


```

Got it. I've added this task:

[E][] attend a meeting (from: Sep 23 2023 10:00 AM to: 12:00 PM)


Now you have 5 tasks in the list.

```


### `find` -  <font color = "darkcyan" size = "3"> Find a relevant task </font>


Searches the particular task.


**Example of usage:** ```find project```



**Expected outcome:** 

Displays the relevant task with a message.

``` 
Here are the matching tasks in your list:

1.[T][] project

```
### `view` -  <font color = "darkcyan" size = "3"> view a task specified by date  </font>


Searches the particular task with the specified date.


**Example of usage:** ```view 2023-09-23```



**Expected outcome:**

Displays the relevant task with a message.

``` 
Hi!, Following are your scheduled Tasks for the day

1.[D][] return book (by: Sep 23 2023 12:20)

```

### `mark` -  <font color = "darkcyan" size = "3"> marks the task as done </font>


Marks the completed task as done.


**Example of usage:** ```mark 2```



**Expected outcome:**

Marks the task at 2nd index number with a message.

``` 
Nice! I've marked this task as done:

1.[T][X] project

```

### `unmark` -  <font color = "darkcyan" size = "3"> marks the task as undone </font>


Marks the completed task as undone.


**Example of usage:** ```unmark 2```



**Expected outcome:**

Unmarks the task at 2nd index number with a message.

``` 
I've marked this task as undone:

1.[T][] project

```

### `delete` -  <font color = "darkcyan" size = "3"> deletes a Task specified by index number </font>


Marks the completed task as undone.


**Example of usage:** ```delete 2```



**Expected outcome:**

Deletes the task at index 2 with a message.

``` 
Noted. I've removed this task:

 [T][] project

Now you have 4 tasks in the list. 

```

### `list` -  <font color = "darkcyan" size = "3"> Displays the TaskList </font>


Shows the List of Tasks

**Example of usage:** ```list```



**Expected outcome:**

Prints the Tasks present in the List with the relevant completion status along with Date and Time wherever applicable.

``` 
Here are all the tasks in your list:

 1.[D][] submit IP (by: Sep 22 2023 23:59)
 2.[T][X] Project


```



### `bye` -  <font color = "darkcyan" size = "3"> Quits the DuckBot </font>


Exits the application and the tasks are stored in the file.


**Example of usage:** ```bye```



**Expected outcome:**

Exits the application with a message.

``` 
Bye. Hope to see you again soon!

```






