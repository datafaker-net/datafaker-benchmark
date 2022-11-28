package net.datafaker.benchmark.csvjson;

import net.datafaker.sequence.FakeSequence;
import net.datafaker.transformations.CsvTransformer;
import net.datafaker.transformations.Schema;
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

import static net.datafaker.transformations.Field.field;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class DatafakerCsvjson {
    private static final net.datafaker.Faker DATA_FAKER = new net.datafaker.Faker();
    private static final Schema<Integer, ?> schema = Schema.of(
            field("Number", integer -> integer),
            field("Password", integer -> DATA_FAKER.name().firstName())
    );
    private static final FakeSequence<Integer> fakeSequence = DATA_FAKER.<Integer>stream()
            .suppliers(() -> DATA_FAKER.number().randomDigit())
            .len(10)
            .build();
    private static final CsvTransformer<Integer> transformer =
            new CsvTransformer.CsvTransformerBuilder<Integer>().header(true).separator(",").build();
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(DatafakerCsvjson.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void csvJson(Blackhole blackhole) {

        blackhole.consume(transformer.generate(fakeSequence, schema));
    }
}
