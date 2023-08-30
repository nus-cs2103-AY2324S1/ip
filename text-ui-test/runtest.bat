@ECHO OFF
REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM Set the path to the source code directory
SET SRC_PATH=C:\Users\chang\Downloads\CS2103\ip\src\main\java

REM Set the path to the bin directory
SET BIN_PATH=C:\Users\chang\Downloads\CS2103\ip\bin

REM Delete output from previous run
DEL ACTUAL.TXT

REM Compile the code into the bin folder
javac -cp %SRC_PATH% -Xlint:none -d %BIN_PATH% %SRC_PATH%\*.java
IF ERRORLEVEL 1 (
    ECHO ********** BUILD FAILURE **********
    EXIT /B 1
)
REM No error here, errorlevel == 0

REM Run the program, feed commands from input.txt file and redirect the output to ACTUAL.TXT
java -classpath %BIN_PATH% Duke < input.txt > ACTUAL.TXT

REM Compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

