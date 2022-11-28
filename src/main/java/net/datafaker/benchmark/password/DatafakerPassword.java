package net.datafaker.benchmark.password;

import net.datafaker.benchmark.simplemethods.DatafakerSimpleMethods;
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
public class DatafakerPassword {
    private static final net.datafaker.Faker DATA_FAKER = new net.datafaker.Faker();
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(DatafakerPassword.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void password10(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.internet().password(10, 10));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void password10WithUpper(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.internet().password(10, 10, true));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void password10WithUpperLower(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.internet().password(10, 10, true, true));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void password10WithUpperLowerDigit(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.internet().password(10, 10, true, true, true));
    }
}
