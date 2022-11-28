package net.datafaker.benchmark;

import net.datafaker.providers.base.Text;
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
public class Text2 {
    private static final net.datafaker.Faker DATA_FAKER = new net.datafaker.Faker();
    private static final String ruLowerCase = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String customSpecialSymbols = "!@#$%^&*;'][{}";
    static final Text.TextRuleConfig config10 = Text.TextSymbolsBuilder.builder()
            .len(10)
            .with(ruLowerCase, 3)
            .with(customSpecialSymbols, 3).build();
    static final Text.TextRuleConfig config100 = Text.TextSymbolsBuilder.builder()
            .len(100)
            .with(ruLowerCase, 3)
            .with(customSpecialSymbols, 3).build();
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(Text2.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void password10(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.text().text(config10));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void password100(Blackhole blackhole) {
        blackhole.consume(DATA_FAKER.text().text(config100));
    }

}
