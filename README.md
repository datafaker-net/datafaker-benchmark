# datafaker-benchmark
Tests for https://github.com/datafaker-net/datafaker/pull/173

Exampe of running from cli
```
mvn clean package exec:exec -Dbenchmarks="DatafakerSimpleMethods" -Ddatafaker.version=2.2.3-SNAPSHOT
```
Or with different java version 
```
mvn clean package exec:exec -Dbenchmarks="DatafakerSimpleMethods" -Ddatafaker.version=2.2.3-SNAPSHOT -Djava.version=21 -DexecutableJava=$JAVA_HOME/bin/java
```
