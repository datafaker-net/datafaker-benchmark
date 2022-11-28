package net.datafaker.benchmark.generate_one_hour;

import com.arakelian.faker.service.RandomPerson;

import java.util.Date;

public class ArakelyanGenerate100 {
    public static void main(String[] args) {
        RandomPerson randomPerson = RandomPerson.get();
        System.out.println(new Date());
        long start = System.nanoTime();
        long end = start + 60 * 60L * 1000 * 1000 * 1000L;
        for (int i = 0; i < 1000_000_000; i++) {
            com.arakelian.faker.model.Person person = randomPerson.next();
            if ((i + 1) % 1000_000 == 0) {
                System.out.println(new Date() + " " + (i + 1) + " " + (System.nanoTime() - start));
            }
            if (System.nanoTime() >= end) {
                System.out.println("total: " + (i + 1));
                break;
            }
            //System.out.println(person);
        }
        System.out.println(new Date());
    }
}
