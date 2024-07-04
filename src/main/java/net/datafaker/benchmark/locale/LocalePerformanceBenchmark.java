package net.datafaker.benchmark.locale;

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

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * This benchmark tests the performance of the generation of most common values on different locales.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class LocalePerformanceBenchmark {
    private static final net.datafaker.Faker DATA_FAKER_ENGLISH = new net.datafaker.Faker(Locale.ENGLISH);
    private static final net.datafaker.Faker DATA_FAKER_UK = new net.datafaker.Faker(Locale.UK);

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LocalePerformanceBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void en_fullname(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER_ENGLISH.name().name());
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void en_gb_fullname(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER_UK.name().name());
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void en_streetAddress(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER_ENGLISH.address().streetAddress());
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void en_gb_streetAddress(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER_UK.address().streetAddress());
    }
}
