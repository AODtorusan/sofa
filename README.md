# Java bindings for SOFA

This project provides a set of Java bindings for the International Astronomical Union's SOFA library. 

This library only provides a thin wrapper layer via BridJ and JNI the C-implementation of the SOFA library, and thus contains no actual code. For more information on the SOFA library and the available functions, see:

> http://www.iausofa.org

## Basic usage

Using this library in a maven project requires the following dependency:

```
<dependency>
	<groupId>be.angelcorp</groupId>
	<artifactId>sofa</artifactId>
    <version>1.0</version>
</dependency>
```

The artifacts are build and published to the following repository:

```
<repositories>
    <repository>
        <id>angelcorp</id>
        <name>AngelCorp Repository</name>
        <url>http://repository.angelcorp.be</url>
    </repository>
</repositories>
```

To use the actual SOFA functions simply import there definitions and call the same functions as the c version of SOFA defines.

```
import static be.angelcorp.sofa.SofaLibrary.*;

class Example {
    public static void example() {
        Pointer<Double> t1 = Pointer.allocateDouble();
        Pointer<Double> t2 = Pointer.allocateDouble();
        iauTaitt(2453750.5, 0.892482639, t1, t2);
    }
}
```
