@echo off
::test the emulator-5556 already started
setlocal enabledelayedexpansion
set rm=9
for /f "delims=" %%a in ('call adb -s emulator-5556 shell getprop sys.boot_completed') ^
do ( set rb=%%a )
if not 1==%rb% (
	echo can not connect to emulator 5556
	echo please make sure that the emulator already started.
	echo the title of the emulator must begin with "5556:".
	pause
	exit 1
)

echo connect to 5556 success

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


:developmentPart
::go to development directory
cd ../cmpt371project/

::clean the old development apk file
if exist bin/*.apk (call ant clean)

::compile the development codes
call android update project -p .\ -t 1
call ant debug
if not errorlevel 0 (
	echo compile development codes error
	echo is it the original project path name "cmpt371project"
	echo is it the project folder and the test folder under the same directory
	pause
	exit 1
)

::install the development code
call adb -s emulator-5556 install -r  bin\cmpt371project-debug.apk
if not errorlevel 0 (
	echo cannot install the original project apk to emulator 5556
	echo or go to and run \cmpt371project\development.cmd 
	echo the batch script calling has some problem 
	pause 
	exit 1
)

echo development code finish compile and install

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


:testPart
::go to smoke test directory
cd ../cmpt371project_Smoke/

::clean the old test apk file
if exist bin/*.apk (call ant clean)

::compile the test codes
call android update test-project -p .\ -m ..\cmpt371project\
call ant debug
if not errorlevel 0 (
	echo compile test codes error
	echo is it the original project path name "cmpt371project"
	echo is it the project folder and the test folder under the same directory
	pause
	exit 1
)

::install the test codes
call adb -s emulator-5556 install -r bin\cmpt371project_SmokeTest-debug.apk
if not errorlevel 0 (
	echo cannot install the test apk to emulator 5556
	pause 
	exit 1
)

echo test code finish compile and install

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

:runSmokeTest
::run the smoke test
::call adb -s emulator-5556 shell am instrument -w com.example.cmpt371project.test/android.test.InstrumentationTestRunner
::if not errorlevel 0 (
::	echo the development codes did not pass the smoke test
::	pause 
::	exit 1
::)

call adb -s emulator-5556 shell am instrument -e class com.example.cmpt371project.test.LocationEditTest ^
-w com.example.cmpt371project.test/android.test.InstrumentationTestRunner > smoketestdata.txt
set file=smoketestdata.txt
set /a cnt=0
for /f %%a in ('type "%file%"^|find "" /v /c') do set /a cnt=%%a
if not 8==%cnt% (
	echo fail in location edit test
	pause 
	exit 1
)


:TestCases
:: admin test
call adb -s emulator-5556 shell am instrument -e class com.example.cmpt371project.test.AdminTest ^
-w com.example.cmpt371project.test/android.test.InstrumentationTestRunner > smoketestdata.txt
set file=smoketestdata.txt
set /a cnt=0
for /f %%a in ('type "%file%"^|find "" /v /c') do set /a cnt=%%a
if not 8==%cnt% (
	echo fail in admin test
	echo error log in smoketestdata.txt
	pause 
	exit 1
) 

:: children list test
call adb -s emulator-5556 shell am instrument -e class com.example.cmpt371project.test.ChildrenListTest ^
-w com.example.cmpt371project.test/android.test.InstrumentationTestRunner > smoketestdata.txt
set file=smoketestdata.txt
set /a cnt=0
for /f %%a in ('type "%file%"^|find "" /v /c') do set /a cnt=%%a
if not 8==%cnt% (
	echo fail in children list test
	echo error log in smoketestdata.txt
	pause 
	exit 1
)

:: children option test
call adb -s emulator-5556 shell am instrument -e class com.example.cmpt371project.test.ChildrenOptionTest ^
-w com.example.cmpt371project.test/android.test.InstrumentationTestRunner > smoketestdata.txt
set file=smoketestdata.txt
set /a cnt=0
for /f %%a in ('type "%file%"^|find "" /v /c') do set /a cnt=%%a
if not 8==%cnt% (
	echo fail in children option test
	echo error log in smoketestdata.txt
	pause 
	exit 1
)

:: location edit test
call adb -s emulator-5556 shell am instrument -e class com.example.cmpt371project.test.LocationEditTest ^
-w com.example.cmpt371project.test/android.test.InstrumentationTestRunner > smoketestdata.txt
set file=smoketestdata.txt
set /a cnt=0
for /f %%a in ('type "%file%"^|find "" /v /c') do set /a cnt=%%a
if not 8==%cnt% (
	echo fail in location edit test
	echo error log in smoketestdata.txt
	pause 
	exit 1
)

:: location list test
call adb -s emulator-5556 shell am instrument -e class com.example.cmpt371project.test.LocationListTest ^
-w com.example.cmpt371project.test/android.test.InstrumentationTestRunner > smoketestdata.txt
set file=smoketestdata.txt
set /a cnt=0
for /f %%a in ('type "%file%"^|find "" /v /c') do set /a cnt=%%a
if not 8==%cnt% (
	echo fail in location list test
	echo error log in smoketestdata.txt
	pause 
	exit 1
)

:: login test
call adb -s emulator-5556 shell am instrument -e class com.example.cmpt371project.test.LoginTest ^
-w com.example.cmpt371project.test/android.test.InstrumentationTestRunner > smoketestdata.txt
set file=smoketestdata.txt
set /a cnt=0
for /f %%a in ('type "%file%"^|find "" /v /c') do set /a cnt=%%a
if not 8==%cnt% (
	echo fail in login test
	echo error log in smoketestdata.txt
	pause 
	exit 1
)

:: survey option test
call adb -s emulator-5556 shell am instrument -e class com.example.cmpt371project.test.SurveyOptionTest ^
-w com.example.cmpt371project.test/android.test.InstrumentationTestRunner > smoketestdata.txt
set file=smoketestdata.txt
set /a cnt=0
for /f %%a in ('type "%file%"^|find "" /v /c') do set /a cnt=%%a
if not 8==%cnt% (
	echo fail in survey option test
	echo error log in smoketestdata.txt
	pause 
	exit 1
)
exit 0