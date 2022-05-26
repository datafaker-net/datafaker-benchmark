package net.datafaker.benchmark;


import io.github.serpro69.kfaker.Faker;
import io.github.serpro69.kfaker.provider.Company;
import io.github.serpro69.kfaker.provider.Name;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class KotlinFakerGenerate100 {
    public static void main(String[] args) {
        final Faker faker = new Faker();
        final Company company = faker.getCompany();
        final Name name = faker.getName();
        System.out.println(new Date());
        long start = System.nanoTime();
        long end = start + 60 * 60L * 1000 * 1000 * 1000L;
        for (int i = 0; i < 1000_000_000; i++) {
            Person person = new Person();
            person.setLevel(i);
            person.setName(name.name());
            person.setCompany(company.industry() + company.buzzwords());
            person.setName(faker.getNation().nationality());
            person.setPlace(faker.getAddress().fullAddress());
           // person.setBlood(name.bloodGroup());
            person.setJob(faker.getJob().title());
            person.setPhone(faker.getPhoneNumber().cellPhone());
            person.setBirthday(birthday(faker));
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

    public static Timestamp birthday(Faker faker, int minAge, int maxAge) {
        LocalDate n = LocalDate.now();

        int currentYear = n.getYear();
        int currentMonth = n.getMonthValue();
        int currentDay = n.getDayOfMonth();

        LocalDate from = LocalDate.of(currentYear - maxAge, currentMonth, currentDay);
        LocalDate to = LocalDate.of(currentYear - minAge, currentMonth, currentDay);

        long offsetMillis = faker.getRandom().nextLong(86400L * (to.toEpochDay() - from.toEpochDay()));
        return new Timestamp(from.toEpochDay() * 86400 + offsetMillis);
    }

    public static Timestamp birthday(Faker faker) {
        return birthday(faker, 18, 65);
    }

    public static Timestamp between(Faker faker, Timestamp from, Timestamp to) throws IllegalArgumentException {
        if (to.before(from)) {
            throw new IllegalArgumentException("Invalid date range, the upper bound date is before the lower bound.");
        }

        if (from.equals(to)) {
            return from;
        }

        long offsetMillis = faker.getRandom().nextLong(to.getTime() - from.getTime());
        return new Timestamp(from.getTime() + offsetMillis);
    }
}
