package net.datafaker.benchmark.expressions;

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
public class DatafakerExpressions {
    private static final net.datafaker.Faker DATA_FAKER = new net.datafaker.Faker();

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(DatafakerExpressions.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void bothifyExpression(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.expression("#{bothify '????','false'}"));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void letterifyExpression(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.expression("#{letterify '????','true'}"));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void numerify(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.numerify("123###"));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void optionsExpression(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.expression("#{options.option 'a','b','c','d'}"));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void regexifyExpression(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.expression("#{regexify '\\.\\*\\?\\+'}"));
    }
}
