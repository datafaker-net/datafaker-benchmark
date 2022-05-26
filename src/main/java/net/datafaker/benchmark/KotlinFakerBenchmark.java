package net.datafaker.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class KotlinFakerBenchmark {
    private static final io.github.serpro69.kfaker.Faker KOTLIN_FAKER = new io.github.serpro69.kfaker.Faker();
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(DatafakerBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void kotlinFaker_initialization(Blackhole blackhole) {
        blackhole.consume(new io.github.serpro69.kfaker.Faker());
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void dataFaker_initialization(Blackhole blackhole) {
        blackhole.consume(new net.datafaker.Faker());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void kotlinFaker_originalTest(Blackhole blackhole) {
        io.github.serpro69.kfaker.Faker faker = new io.github.serpro69.kfaker.Faker();
        for (int i = 0; i < 1000_000; i++) {
            faker.getName().name();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void kotlinFaker_directMethodCall(Blackhole blackhole) {
        blackhole.consume(KOTLIN_FAKER.getRandom().nextInt(10, 1000));
    }

}
