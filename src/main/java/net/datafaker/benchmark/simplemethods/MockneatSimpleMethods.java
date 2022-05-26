package net.datafaker.benchmark.simplemethods;

import net.datafaker.fileformats.Format;
import net.datafaker.providers.base.Name;
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

import static net.andreinc.mockneat.unit.seq.IntSeq.intSeq;
import static net.andreinc.mockneat.unit.text.CSVs.csvs;
import static net.andreinc.mockneat.unit.user.Names.names;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class MockneatSimpleMethods {
    private static final net.datafaker.Faker DATA_FAKER = new net.datafaker.Faker();
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(MockneatSimpleMethods.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void firstname(Blackhole blackhole) {
        blackhole.consume(csvs()
                .column(intSeq())
                .column(names().first())
                .column(names().last())
                .separator("|")
                .accumulate(10, "\n")
                .get());
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void fullname(Blackhole blackhole) {
        blackhole.consume(Format.toCsv(
                        DATA_FAKER.<Name>collection()
                                .suppliers(DATA_FAKER::name)
                                .build())
                .headers(() -> "first_name", () -> "last_name")
                .columns(Name::firstName, Name::lastName)
                .separator(" ; ")
                .limit(10).build().get());
    }

}
