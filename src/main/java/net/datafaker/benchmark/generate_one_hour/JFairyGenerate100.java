package net.datafaker.benchmark.generate_one_hour;

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.company.Company;
import net.datafaker.benchmark.Person;

import java.time.ZoneOffset;
import java.util.Date;

public class JFairyGenerate100 {
    public static void main(String[] args) {
        Fairy faker = Fairy.create();
        System.out.println(new Date());
        long start = System.nanoTime();
        long end = start + 60 * 60L * 1000 * 1000 * 1000L;
        for (int i = 0; i < 1000_000_000; i++) {
            Person person = new Person();
            person.setLevel(i);
            final Company company = faker.company();
            final com.devskiller.jfairy.producer.person.Person  p = faker.person();
            person.setName(p.getFullName());
            person.setCompany(company.getDomain() + company.getName());
            person.setNation(p.getNationality().name());
            person.setPlace(p.getAddress().getAddressLine1());
            //person.setNation(p.getNationalIdentificationNumber());
            //person.setUniversity(p.getCompany().getName());
            //person.setJob(p.getCompany().getVatIdentificationNumber());
            person.setPhone(p.getMobileTelephoneNumber());
            person.setBirthday(Date.from(p.getDateOfBirth().atStartOfDay().toInstant(ZoneOffset.UTC)));
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
