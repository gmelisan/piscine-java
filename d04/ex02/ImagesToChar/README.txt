piscine-java, d04, ex02
----------------------------

DOWNLOADING LIBS

$ mkdir lib
$ curl -so lib/jcommander-1.81.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.81/jcommander-1.81.jar
$ curl -so lib/JCOLOR-5.0.1.jar https://repo1.maven.org/maven2/com/diogonunes/JColor/5.0.1/JColor-5.0.1.jar

COMPILATION

$ mkdir target
$ cp -r src/resources target
$ cd target ; jar xf ../lib/jcommander-1.81.jar com ; cd - >/dev/null
$ cd target ; jar xf ../lib/JCOLOR-5.0.1.jar com ; cd - >/dev/null
$ javac -cp lib/jcommander-1.81.jar:lib/JCOLOR-5.0.1.jar -d target src/java/edu/school21/printer/*/*

PACKAGING TO JAR

$ jar cmf src/manifest.txt target/images-to-chars-printer.jar -C target edu -C target com -C src resources

EXECUTION

$ java -jar target/images-to-chars-printer.jar <WHITE_CHAR> <BLACK_CHAR>
