package net.datafaker.benchmark.kotlinfakerbenchmark;

import net.datafaker.Faker;
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
public class Datafaker_KotlinFakerBenchmark {
    private static final String str = "";
    private static final String str10 = "0123456789";
    private static final String strabc = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args) throws RunnerException {

       Options opt = new OptionsBuilder()
                .include(Datafaker_KotlinFakerBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void kotlinFakerTest(Blackhole blackhole) {
        Faker faker = new Faker();
        for (int i = 0; i < 1000_000; i++) {
            blackhole.consume(faker.name().name());
        }
    }
}
