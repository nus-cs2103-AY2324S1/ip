# Barbie project template

This is an individual CS2103T greenfield Java project. It is forked from the project template Duke, which is named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Barbie.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   ______________________________
   Hi Barbie! Hi Ken!
   
   I'm
        ____             _
       |  _ \           | | 
       | |_| |_____,_ ,_| |,___  _  ___
       |  _ /|  _  | ` _|  __\ \| |/ _  \
       | |_| | |_| | |  | |__/ /| |  ___/  
       |____/ \__,_|_|  |_|\__/ |_|\___/
   
   What can I do for you today?
   ______________________________
   [you]:
   
   ```
