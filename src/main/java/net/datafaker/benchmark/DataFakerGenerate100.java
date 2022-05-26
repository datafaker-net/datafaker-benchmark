package net.datafaker.benchmark;


import net.datafaker.Company;
import net.datafaker.Faker;
import net.datafaker.Name;

import java.util.Date;

public class DataFakerGenerate100 {
        public static void main(String[] args) {
            final Faker faker = new Faker();
            final Company company = faker.company();
            final Name name = faker.name();
            System.out.println(new Date());
            long start = System.nanoTime();
            long end = start + 60 * 60L * 1000 * 1000 * 1000L;
            for (int i = 0; i < 1000_000_000; i++) {
                Person person = new Person();
                person.setLevel(i);
                person.setName(name.name());
                person.setCompany(company.industry() + company.buzzword());
                person.setName(faker.nation().nationality());
                person.setPlace(faker.address().streetAddress());
                person.setBlood(name.bloodGroup());
                person.setJob(faker.job().title());
                person.setUniversity(faker.university().name());
                person.setPhone(faker.phoneNumber().cellPhone());
                person.setBirthday(faker.date().birthday());
                if ((i + 1) % 1000_000 == 0) {
                    System.out.println(new Date() + " " + (i + 1));
                }
                if (System.nanoTime() >= end) {
                    System.out.println("total: " + (i + 1));
                    break;
                }
            }
            System.out.println(new Date());
        }
}
