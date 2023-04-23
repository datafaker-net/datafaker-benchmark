### Comparing Object Population in Java by Datafaker and Kotlin Faker

(for different libs there should be different classes, for more details look in the code):
`net.datafaker.benchmark.java_object_population`

| Project      | Java Version                                        | Mode | Cnt | Score               | Units |
|:-------------|:----------------------------------------------------|:-----|:----|:--------------------|:------|
| Datafaker    | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 902.891 ± 46.091    | ms/op |
| Kotlin Faker | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 745.148 ± 49.614    | ms/op |

**ATTENTION!** In this test we measure not throughput but the average time of one operation => lower is better.
