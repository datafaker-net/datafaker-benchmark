package net.datafaker.benchmark.java_object_population.single_object;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import net.datafaker.Faker;
import net.datafaker.annotations.FakeForSchema;
import net.datafaker.providers.base.BaseFaker;
import net.datafaker.service.RandomService;
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

import static net.datafaker.transformations.Field.field;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class Datafaker_JavaObjectPopulateBenchmark {

	public static void main(String[] args) throws RunnerException {

		Options opt = new OptionsBuilder()
				.include(Datafaker_JavaObjectPopulateBenchmark.class.getSimpleName())
				.build();

		new Runner(opt).run();
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	public void javaObjectPopulate(Blackhole blackhole) {
		for (int i = 0; i < 1000_000; i++) {
			blackhole.consume(BaseFaker.populate(MyClass.class));
		}
	}

	@FakeForSchema("defaultSchema")
	public static class MyClass {
		private String name;
	}

	static Faker faker = new Faker(Locale.forLanguageTag("fr-en"), new RandomService(new Random(1)));

	public static Schema<Object, String> defaultSchema() {
		return Schema.of(field("name", () -> faker.name().fullName()));
	}
}
