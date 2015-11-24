echo off
set TARGET=colopl
set KEYWORD="白猫プロジェクト"
set JAR_NAME=websrh.jar

SET /P ANSWER=キーワード%KEYWORD%で検索を実施します。よろしいですか (Y/N)?
if /i {%ANSWER%}=={y} (goto :YES)
if /i {%ANSWER%}=={yes} (goto :YES)
if /i {%ANSWER%}=={n} (goto :NO)
if /i {%ANSWER%}=={no} (goto :NO)

:YES
"jre6\bin\java.exe" -jar %JAR_NAME% %TARGET% %KEYWORD%
pause
:NO
