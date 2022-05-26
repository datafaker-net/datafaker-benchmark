package net.datafaker.benchmark;

import com.devskiller.jfairy.Fairy;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JFairyBenchmark {
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(DatafakerBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }


    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void jFairy_initialization(Blackhole blackhole) {
        blackhole.consume(Fairy.create());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void jFairy_originalTest(Blackhole blackhole) {
        Fairy fairy = Fairy.create();
        for (int i = 0; i < 1000_000; i++) {
            blackhole.consume(fairy.person().getFullName());
        }
    }

}
