# Skog

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/duke/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
   
   Skog!
   ```
   
Here are some basic commands to use Skog:
```
todo / t
deadline / d
event / e
list / l
find / f
mark / m 
unmark / um
delete / del
bye
```

**todo:**
_A task with only a description field and no time constraints_


**deadline:**
_A task with an end-date to meet_ 

**event:**
_A task with a starting date and an ending date_

**list:**
_Lists out all added tasks_

**find:**
_Shows all the tasks with containing a specified word_

**mark:**
_Marks specified task as done_

**unmark:**
_Unmarks a specified task_

**delete:**
_Deletes a specified task from the list_

**bye:**
_Ends the chat bot and saves the data into a text file_