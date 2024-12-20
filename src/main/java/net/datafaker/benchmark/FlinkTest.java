package net.datafaker.benchmark;

import net.datafaker.providers.base.Text;
import org.apache.flink.table.api.Schema;
import org.apache.flink.table.catalog.CatalogBaseTable;
import org.apache.flink.table.catalog.CatalogTable;
import org.apache.flink.table.catalog.CatalogTableImpl;
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
public class FlinkTest {

    public static final CatalogBaseTable table = CatalogTable.newBuilder().schema(Schema.newBuilder().build()).build();
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(FlinkTest.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void instanceOf(Blackhole blackhole) {
        blackhole.consume(table instanceof CatalogTable);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void get(Blackhole blackhole) {
        blackhole.consume(table.getTableKind());
    }

}