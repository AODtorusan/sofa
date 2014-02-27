# Building the SOFA native dynamic libraries

For most standard platforms, to binaries are automatically build via a maven (via a bash script and cmake). One can simply execute ```mvn install``` to build, and package the natives into the sofa-platform jar.

For in case this process fails, one can manually compile the dynamic libraries, and place them in the src/main/resources/lib/???/ folder. The exact sub-folder is dependent on the platform and can be found in the BridJ documentation:

> https://github.com/ochafik/nativelibs4java
> https://github.com/ochafik/nativelibs4java/blob/master/libraries/BridJ/src/main/java/org/bridj/Platform.java

The final sofa-platform jar can then be installed via:

```
mvn install -DskipBuild=true
```
