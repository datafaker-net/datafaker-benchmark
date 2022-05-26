package net.datafaker.benchmark;

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
public class JavaFakerBenchmark {
    private static final Faker JAVA_FAKER = new Faker();
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(DatafakerBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void javaFaker_initialization(Blackhole blackhole) {
        blackhole.consume(new Faker());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void javaFaker_originalTest(Blackhole blackhole) {
        Faker faker = new Faker();
        for (int i = 0; i < 1000_000; i++) {
            blackhole.consume(faker.name().name());
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void directMethodCall(Blackhole blackhole) {
        blackhole.consume(JAVA_FAKER.number().numberBetween(10, 1000));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void optionsExpression(Blackhole blackhole) {
        blackhole.consume(JAVA_FAKER.expression("#{options.option 'a','b','c','d'}"));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void letterifyExpression(Blackhole blackhole) {
        blackhole.consume(JAVA_FAKER.expression("#{letterify '????','true'}"));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void regexifyExpression(Blackhole blackhole) {
        blackhole.consume(JAVA_FAKER.expression("#{regexify '\\.\\*\\?\\+'}"));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void bothifyExpression(Blackhole blackhole) {
        blackhole.consume(JAVA_FAKER.expression("#{bothify '????','false'}"));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void oneMethodCallExpression(Blackhole blackhole) {
        blackhole.consume(JAVA_FAKER.expression("#{number.number_between '1','10'}"));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void threeMethodsCallExpression(Blackhole blackhole) {
        blackhole.consume(JAVA_FAKER.expression("#{Name.first_name} #{Name.first_name} #{Name.last_name}"));
    }

}
