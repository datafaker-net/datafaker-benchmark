package net.datafaker.benchmark.templatestrings;

import com.github.javafaker.Faker;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JavafakerTemplateStrings {

    private static final Faker JAVA_FAKER = new Faker();
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(JavafakerTemplateStrings.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void numerify(Blackhole blackhole) {
        blackhole.consume(JAVA_FAKER.numerify("123###"));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void bothify(Blackhole blackhole) {
        blackhole.consume(JAVA_FAKER.bothify("foo???bar###", false));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void letterify(Blackhole blackhole) {
        blackhole.consume(JAVA_FAKER.letterify("foo???", true));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void regexify(Blackhole blackhole) {
        blackhole.consume(JAVA_FAKER.regexify("'\\.\\*\\?\\+'"));
    }


}
