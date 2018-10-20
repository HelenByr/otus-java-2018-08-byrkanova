#java -jar proguard\proguard-base-6.0.3.jar @L01.2.1.pro

-injars       target/byrkanova-1-Helen.jar
-outjars      target/DZ1-release.jar

-printmapping pgmapout.map
-dontwarn

-keep public class ru.otus.Main {public static void main(java.lang.String[]);}
