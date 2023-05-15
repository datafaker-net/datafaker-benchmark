package net.datafaker.benchmark.java_object_population.single_object;

import java.util.concurrent.TimeUnit;

import org.instancio.Instancio;
import org.instancio.generators.Generators;
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

import static org.instancio.Select.field;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class Instancio_JavaObjectPopulationBenchmark {

	public static void main(String[] args) throws RunnerException {

		Options opt = new OptionsBuilder()
				.include(Instancio_JavaObjectPopulationBenchmark.class.getSimpleName())
				.build();

		new Runner(opt).run();
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	public void instancioFakerTest(Blackhole blackhole) {
		for (int i = 0; i < 1000_000; i++) {
			blackhole.consume(Instancio.of(MyClass.class)
					.generate(field(MyClass::getName), Generators::string)
					.create()
			);
		}
	}

	public static class MyClass {
		private final String name;

		public MyClass(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "MyClass{" +
					"name='" + name + '\'' +
					'}';
		}
	}
}
