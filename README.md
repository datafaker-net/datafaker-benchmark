Originally Data-faker has been started as a fork of java-faker. In internet there are complaints about java-faker poor performance.
Now there has been already 1.4.0 version released. Let's see what we can say about performance improvements.
For that we are going to use JMH benchmarks. All the code is available at <TO BE DONE> as a separate project because of license reasons.
Since it's hardly possible and reasonable to compare each method's performance let's see what exactly we are going to compare within this article.
As it could be seen below Data-faker is about 10x-100x times faster for some cases.

1. Initialization. It's worse to measure since initially during initialization of Faker object it requires to initialise all the providers objects and read all the yaml files for providers.

| Benchmark                | Java Version                              | Mode  | Cnt | Score             | Units  |
|--------------------------|-------------------------------------------|:------|:----|:------------------|:-------|
| Datafaker initialization | openjdk-1.8.0.332.b09-1.fc36.x86_64       | thrpt | 10  | 2397.423 ± 51.308 | ops/ms |
| Datafaker initialization | openjdk-11.0.15.0.10-1.fc36.x86_64        | thrpt | 10  | 3072.228 ± 59.418 | ops/ms |
| Datafaker initialization | openjdk-18.0.1.0.10-1.rolling.fc36.x86_64 | thrpt | 10  | 4060.552 ± 77.858 | ops/ms |
| Javafaker initialization | openjdk-1.8.0.332.b09-1.fc36.x86_64       | thrpt | 10  | 22.530 ± 0.941    | ops/ms |
| Javafaker initialization | openjdk-11.0.15.0.10-1.fc36.x86_64        | thrpt | 10  | 24.619 ± 1.043    | ops/ms |
| Javafaker initialization | openjdk-18.0.1.0.10-1.rolling.fc36.x86_64 | thrpt | 10  | 29.978 ± 0.559    | ops/ms |


2. Performance of direct calls without resolution of methods like `numberbetween`

| Benchmark                  | Java Version                              | Mode  | Cnt | Score              | Units  |
|----------------------------|-------------------------------------------|:------|:----|:-------------------|:-------|
| Datafaker directMethodCall | openjdk-1.8.0.332.b09-1.fc36.x86_64       | thrpt | 10  | 3910.248 ± 36.207  | ops/ms |
| Datafaker directMethodCall | openjdk-11.0.15.0.10-1.fc36.x86_64        | thrpt | 10  | 3885.144 ± 21.652  | ops/ms |
| Datafaker directMethodCall | openjdk-18.0.1.0.10-1.rolling.fc36.x86_64 | thrpt | 10  | 3991.314 ± 274.753 | ops/ms |
| Javafaker directMethodCall | openjdk-1.8.0.332.b09-1.fc36.x86_64       | thrpt | 10  | 2578.922 ± 29.198  | ops/ms |
| Javafaker directMethodCall | openjdk-11.0.15.0.10-1.fc36.x86_64        | thrpt | 10  | 3158.523 ± 29.006  | ops/ms |
| Javafaker directMethodCall | openjdk-18.0.1.0.10-1.rolling.fc36.x86_64 | thrpt | 10  | 3616.073 ± 77.453  | ops/ms |

3. Performance of letterify, bothify, numerify, regexify

| Benchmark                     | Java Version                              | Mode  | Cnt | Score             | Units  |
|-------------------------------|-------------------------------------------|:------|:----|:------------------|:-------|
| Datafaker bothifyExpression   | openjdk-1.8.0.332.b09-1.fc36.x86_64       | thrpt | 10  | 1918.381 ± 91.410 | ops/ms |
| Datafaker bothifyExpression   | openjdk-11.0.15.0.10-1.fc36.x86_64        | thrpt | 10  | 1868.298 ± 27.953 | ops/ms |
| Datafaker bothifyExpression   | openjdk-18.0.1.0.10-1.rolling.fc36.x86_64 | thrpt | 10  | 2169.941 ± 45.504 | ops/ms |
| Datafaker letterifyExpression | openjdk-1.8.0.332.b09-1.fc36.x86_64       | thrpt | 10  | 1821.056 ± 70.109 | ops/ms |
| Datafaker letterifyExpression | openjdk-11.0.15.0.10-1.fc36.x86_64        | thrpt | 10  | 1922.130 ± 16.922 | ops/ms |
| Datafaker letterifyExpression | openjdk-18.0.1.0.10-1.rolling.fc36.x86_64 | thrpt | 10  | 2104.585 ± 81.704 | ops/ms |
| Datafaker regexifyExpression  | openjdk-1.8.0.332.b09-1.fc36.x86_64       | thrpt | 10  | 1273.177 ± 8.818  | ops/ms |
| Datafaker regexifyExpression  | openjdk-11.0.15.0.10-1.fc36.x86_64        | thrpt | 10  | 1235.418 ± 11.646 | ops/ms |
| Datafaker regexifyExpression  | openjdk-18.0.1.0.10-1.rolling.fc36.x86_64 | thrpt | 10  | 1607.923 ± 34.328 | ops/ms |
| Javafaker bothifyExpression   | openjdk-1.8.0.332.b09-1.fc36.x86_64       | thrpt | 10  | 207.428 ± 2.923   | ops/ms |
| Javafaker bothifyExpression   | openjdk-11.0.15.0.10-1.fc36.x86_64        | thrpt | 10  | 208.868 ± 2.023   | ops/ms |
| Javafaker bothifyExpression   | openjdk-18.0.1.0.10-1.rolling.fc36.x86_64 | thrpt | 10  | 298.704 ± 4.315   | ops/ms |
| Javafaker letterifyExpression | openjdk-1.8.0.332.b09-1.fc36.x86_64       | thrpt | 10  | 205.280 ± 3.724   | ops/ms |
| Javafaker letterifyExpression | openjdk-11.0.15.0.10-1.fc36.x86_64        | thrpt | 10  | 208.239 ± 1.535   | ops/ms |
| Javafaker letterifyExpression | openjdk-18.0.1.0.10-1.rolling.fc36.x86_64 | thrpt | 10  | 316.426 ± 5.495   | ops/ms |
| Javafaker regexifyExpression  | openjdk-1.8.0.332.b09-1.fc36.x86_64       | thrpt | 10  | 120.409 ±  0.993  | ops/ms |
| Javafaker regexifyExpression  | openjdk-11.0.15.0.10-1.fc36.x86_64        | thrpt | 10  | 121.226 ± 0.670   | ops/ms |
| Javafaker regexifyExpression  | openjdk-18.0.1.0.10-1.rolling.fc36.x86_64 | thrpt | 10  | 158.919 ± 1.609   | ops/ms |

4. Performance of expression with one method call. Here there was added cache for parsing of methods.

| Benchmark                         | Java Version                              | Mode  | Cnt | Score             | Units  |
|-----------------------------------|-------------------------------------------|:------|:----|:------------------|:-------|
| Datafaker oneMethodCallExpression | openjdk-1.8.0.332.b09-1.fc36.x86_64       | thrpt | 10  | 1020.388 ± 12.270 | ops/ms |
| Datafaker oneMethodCallExpression | openjdk-11.0.15.0.10-1.fc36.x86_64        | thrpt | 10  | 1057.132 ± 27.611 | ops/ms |
| Datafaker oneMethodCallExpression | openjdk-18.0.1.0.10-1.rolling.fc36.x86_64 | thrpt | 10  | 1113.307 ± 21.496 | ops/ms |
| Javafaker oneMethodCallExpression | openjdk-1.8.0.332.b09-1.fc36.x86_64       | thrpt | 10  | 145.144 ± 2.523   | ops/ms |
| Javafaker oneMethodCallExpression | openjdk-11.0.15.0.10-1.fc36.x86_64        | thrpt | 10  | 147.812 ± 2.206   | ops/ms |
| Javafaker oneMethodCallExpression | openjdk-18.0.1.0.10-1.rolling.fc36.x86_64 | thrpt | 10  | 207.275 ± 3.522   | ops/ms |

 
5. Performance of expression with several method calls. Besides, cache of parsed methods there was also added cache for parsed args.

| Benchmark                            | Java Version                              | Mode  | Cnt | Score            | Units  |
|--------------------------------------|-------------------------------------------|:------|:----|:-----------------|:-------|
| Datafaker threeMethodsCallExpression | openjdk-1.8.0.332.b09-1.fc36.x86_64       | thrpt | 10  | 276.175 ± 6.503  | ops/ms |
| Datafaker threeMethodsCallExpression | openjdk-11.0.15.0.10-1.fc36.x86_64        | thrpt | 10  | 321.559 ± 12.098 | ops/ms |
| Datafaker threeMethodsCallExpression | openjdk-18.0.1.0.10-1.rolling.fc36.x86_64 | thrpt | 10  | 334.248 ± 12.733 | ops/ms |
| Javafaker threeMethodsCallExpression | openjdk-1.8.0.332.b09-1.fc36.x86_64       | thrpt | 10  | 33.567 ± 0.515   | ops/ms |
| Javafaker threeMethodsCallExpression | openjdk-11.0.15.0.10-1.fc36.x86_64        | thrpt | 10  | 36.055 ± 0.460   | ops/ms |
| Javafaker threeMethodsCallExpression | openjdk-18.0.1.0.10-1.rolling.fc36.x86_64 | thrpt | 10  | 49.014 ± 1.994   | ops/ms |
# datafaker-benchmark
