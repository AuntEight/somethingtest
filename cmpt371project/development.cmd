@echo off
::clean the old development apk file
if exist bin\*.apk ( call ant clean )

::compile the development codes
call android update project -p .\ -t 1 
call ant debug
if not errorlevel 0 (
	echo is it the original project path name "cmpt371project"
	echo is it the project folder and the test folder under the same directory
	pause
	exit 1
)
