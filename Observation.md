### Comparing Object Population in Java by Datafaker and Kotlin Faker

(for different libs there should be different classes, for more details look in the code):
`net.datafaker.benchmark.java_object_population`

| Project      | Java Version                                        | Mode | Cnt | Score                   | Units |
|:-------------|:----------------------------------------------------|:-----|:----|:------------------------|:------|
| Datafaker    | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 577.529 ± 11.157        | ms/op |
| Kotlin Faker | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 3093.343 ± 305.379      | ms/op |
| Instancio    | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 1267.219 ± 29.126       | ms/op |

**ATTENTION!** In this test we measure not throughput but the average time of one operation => lower is better.
