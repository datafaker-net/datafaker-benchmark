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
public class ToJavaName {
    private static final String string = "phone_number";
    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(ToJavaName.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void toJavaName(Blackhole blackhole) {
        final int length;
        if (string == null || (length = string.length()) == 0) {
            blackhole.consume(string);
            return;
        }
        int cnt = 0;
        for (int i = 0; i < length; i++) {
            if (string.charAt(i) == '_') {
                cnt++;
            }
        }
        if (cnt == 0 && Character.isUpperCase(string.charAt(0))) blackhole.consume(string);
        final char[] res = new char[length - cnt];
        int pos = 0;
        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            if (i == 0 && Character.isLetter(c)) {
                res[pos++] = Character.toUpperCase(c);
            } else if (c == '_') {
                if (i < length - 1) {
                    final char next = string.charAt(i + 1);
                    if (Character.isLetter(next)) {
                        res[pos++] = Character.toUpperCase(next);
                        i++;
                    }
                }
            } else {
                res[pos++] = c;
            }
        }
        blackhole.consume(new String(res));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void toJavaName2(Blackhole blackhole) {
        if (string == null || string.isEmpty()) {
            blackhole.consume(string);
        }
        StringBuilder res = new StringBuilder(string.length());
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (i == 0 && Character.isLetter(c)) {
                res.append(Character.toUpperCase(c));
            } else if (c == '_' && i < string.length() + 1 && Character.isLetter(string.charAt(i + 1))) {
                res.append(Character.toUpperCase(string.charAt(i + 1)));
                i++;
            } else {
                res.append(c);
            }
        }
        blackhole.consume(res.toString());
    }

}
