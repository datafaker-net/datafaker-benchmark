package net.datafaker.benchmark.simplemethods;

import com.github.javafaker.Faker;
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
public class JavafakerSimpleMethods {
    private static final Faker JAVA_FAKER = new Faker();

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(JavafakerSimpleMethods.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void firstname(Blackhole blackhole) {
        blackhole.consume(JAVA_FAKER.name().firstName());
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void fullname(Blackhole blackhole) {
        blackhole.consume(JAVA_FAKER.name().name());
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void streetAddress(Blackhole blackhole) {
        blackhole.consume(JAVA_FAKER.address().streetAddress());
    }
}
