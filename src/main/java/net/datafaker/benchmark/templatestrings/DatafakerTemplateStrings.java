package net.datafaker.benchmark.templatestrings;

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
public class DatafakerTemplateStrings {
    private static final net.datafaker.Faker DATA_FAKER = new net.datafaker.Faker();

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(DatafakerTemplateStrings.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void numerify(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.numerify("123###"));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void bothify(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.bothify("foo???bar###", false));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void letterify(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.letterify("foo???", true));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void regexify(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.regexify("'\\.\\*\\?\\+'"));
    }
}
