package net.datafaker.benchmark.password;

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
public class JavaToYamlName {
    private static final net.datafaker.Faker DATA_FAKER = new net.datafaker.Faker();
    private static final String expression = "PhoneNumber";
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(JavaToYamlName.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void javaNameToYamlName(Blackhole blackhole) {
        int cnt = 0;
        final int length = expression.length();
        for (int i = 0; i < length; i++) {
            if (Character.isUpperCase(expression.charAt(i))) {
                cnt++;
            }
        }
        final StringBuilder sb = new StringBuilder(length + cnt);
        int n = 0;
        for (int i = 0; i < length; i++) {
            if (cnt > 0) {
                final char c = expression.charAt(i);
                if (Character.isUpperCase(c)) {
                    if (n > 0) {
                        sb.append(expression, i - n, i);
                        n = 0;
                    }
                    if (sb.length() > 0) {
                        sb.append('_');
                    }
                    sb.append(Character.toLowerCase(c));
                    cnt--;
                } else {
                    n++;
                    //sb.append(c);
                }
            } else {
                sb.append(expression, i, length);
                break;
            }
        }

        blackhole.consume(sb.toString());
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void javaNameToYamlName2(Blackhole blackhole) {
        int cnt = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isUpperCase(expression.charAt(i))) {
                cnt++;
            }
        }
        final StringBuilder sb = new StringBuilder(expression.length() + cnt);
        for (int i = 0; i < expression.length(); i++) {
            if (cnt > 0) {
                final char c = expression.charAt(i);
                if (Character.isUpperCase(c)) {
                    if (sb.length() > 0) {
                        sb.append("_");
                    }
                    sb.append(Character.toLowerCase(c));
                    cnt--;
                } else {
                    sb.append(c);
                }
            } else {
                sb.append(expression.substring(i));
                break;
            }
        }
        blackhole.consume(sb.toString());
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void javaNameToYamlName3(Blackhole blackhole) {
        final int length = expression.length();
        final boolean firstLetterUpperCase = length > 0 && Character.isUpperCase(expression.charAt(0));
        int cnt = firstLetterUpperCase ? 1 : 0;
        for (int i = 1; i < length; i++) {
            if (Character.isUpperCase(expression.charAt(i))) {
                cnt++;
            }
        }
        final char[] res = new char[length + (firstLetterUpperCase ? cnt - 1 : cnt)];
        int pos = 0;
        for (int i = 0; i < length; i++) {
            final char c = expression.charAt(i);
            if (cnt > 0) {
                if (Character.isUpperCase(c)) {
                    if (pos > 0) {
                        res[pos++] = '_';
                    }
                    res[pos++] = Character.toLowerCase(c);
                    cnt--;
                } else {
                    res[pos++] = c;
                }
            } else {
                res[pos++] = c;
            }
        }

        blackhole.consume(new String(res));
    }

}
