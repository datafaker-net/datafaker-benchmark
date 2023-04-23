package net.datafaker.benchmark.java_object_population;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import io.github.serpro69.kfaker.Faker;
import io.github.serpro69.kfaker.provider.misc.RandomClassProvider;
import io.github.serpro69.kfaker.provider.misc.RandomProviderConfig;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;
import net.datafaker.benchmark.kotlinfakerbenchmark.Datafaker_KotlinFakerBenchmark;
import org.jetbrains.annotations.NotNull;
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

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class Kotlinfaker_RandomDataGeneratorBenchmark {

	public static void main(String[] args) throws RunnerException {

		Options opt = new OptionsBuilder()
				.include(Datafaker_KotlinFakerBenchmark.class.getSimpleName())
				.build();

		new Runner(opt).run();
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	public void kotlinFakerTest(Blackhole blackhole) {
		Faker faker = new Faker();
		KClass<MyClass> clazz = JvmClassMappingKt.getKotlinClass(MyClass.class);
		RandomClassProvider randomProvider = faker.getRandomProvider();
		RandomProviderConfig config = new RandomProviderConfig();
		Method method = getMethod(randomProvider);

		for (int i = 0; i < 1000_000; i++) {
			blackhole.consume(generateObject(clazz, randomProvider, config, method));
		}
	}

	private static MyClass generateObject(KClass<MyClass> clazz, RandomClassProvider randomProvider, RandomProviderConfig config, Method method){
		try {
			return (MyClass) method.invoke(randomProvider, clazz, config);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@NotNull
	private static Method getMethod(RandomClassProvider randomProvider) {
		try {
			Method method = randomProvider.getClass().getDeclaredMethod("randomClassInstance", KClass.class, RandomProviderConfig.class);
			method.setAccessible(true);
			return method;
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	public static class MyClass {
		private final String name;

		public MyClass(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "MyClass{" +
					"name='" + name + '\'' +
					'}';
		}
	}
}
