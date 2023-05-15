### Comparing Object Population in Java by Datafaker and Kotlin Faker and Instancio
(for different libs there should be different classes, for more details look in the code):
`net.datafaker.benchmark.java_object_population.single_object`

| Project      | Java Version                                        | Mode | Cnt | Score                    | Units |
|:-------------|:----------------------------------------------------|:-----|:----|:-------------------------|:------|
| Datafaker    | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 585.520 ± 25.153         | ms/op |
| Kotlin Faker | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 3093.343 ± 305.379       | ms/op |
| Instancio    | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 31353.509 ± 2389.731     | ms/op |

**ATTENTION!** In this test we measure not throughput but the average time of one operation => lower is better.


### Comparing Sequence Objects Population in Java by Datafaker and Instancio

(for different libs there should be different classes, for more details look in the code):
`net.datafaker.benchmark.java_object_population.sequence`

| Project      | Java Version                                        | Mode | Cnt | Score                   | Units |
|:-------------|:----------------------------------------------------|:-----|:----|:------------------------|:------|
| Datafaker    | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 769.770 ± 172.887        | ms/op |
| Instancio    | JDK 17.0.5, OpenJDK 64-Bit Server VM, 17.0.5+8      | avgt | 10  | 1290.259 ± 50.212       | ms/op |

**ATTENTION!** In this test we measure not throughput but the average time of one operation => lower is better.
