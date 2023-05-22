### Comparing Object Population in Java by Datafaker and Kotlin Faker and Instancio
(for different libs there should be different classes, for more details look in the code):
`net.datafaker.benchmark.java_object_population.single_object`

| Project      | Java Version                                        | Mode | Cnt | Score                | Units |
|:-------------|:----------------------------------------------------|:-----|:----|:---------------------|:------|
| Datafaker    | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 925.698 ± 131.938    | ms/op |
| Kotlin Faker | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 3481.494 ± 232.195   | ms/op |
| Instancio    | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 26881.156 ± 730.465  | ms/op |

**ATTENTION!** In this test we measure not throughput but the average time of one operation => lower is better.


### Comparing Sequence Objects Population in Java by Datafaker and Instancio

(for different libs there should be different classes, for more details look in the code):
`net.datafaker.benchmark.java_object_population.sequence`

| Project      | Java Version                                        | Mode | Cnt | Score                    | Units |
|:-------------|:----------------------------------------------------|:-----|:----|:-------------------------|:------|
| Datafaker    | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 1024.540 ± 121.902       | ms/op |
| Instancio    | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 1349.173 ± 75.254        | ms/op |

**ATTENTION!** In this test we measure not throughput but the average time of one operation => lower is better.
