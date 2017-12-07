@ECHO OFF
ECHO %1
set newDataFile=%1
set modelFile=%2

svm-predict.exe %newDataFile% %modelFile% out.txt >> predictResults.txt 2>&1
java -jar ScatterPlot.jar

del out.txt