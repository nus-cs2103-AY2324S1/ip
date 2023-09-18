# DuckBot User Guide


## <font color = "darkgrey"> Introduction </font>

 Welcome to DuckBot, 

your friendly chatbot for managing tasks and deadlines effortlessly. 

DuckBot is designed to help you keep track of your tasks, events,and deadlines efficiently. 

This user guide will walk you through all the amazing features DuckBot offers 

and provide you with clear instructions on how to use them effectively.</font>


## <font style = "Ariel" color="darkgrey"> Features </font> 



### <font color = "darkcyan"> Managing Tasks </font>


Three types of tasks are handles by DuckBot : 


1) <font color = "darkcyan">**Deadline**</font> - These tasks have specific due dates or deadlines. DuckBot helps you keep track of them.



2) <font color = "darkcyan">**ToDo**</font>  - These tasks don't have specific due dates or deadlines.



3) <font color = "darkcyan">**Events**</font>  - These are the events that happen on a prticular date-time and have an end date-time also. eg - 

Attending a meeting.





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


