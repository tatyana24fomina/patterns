package ru.netology;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {

    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(String locale) {
        Faker fakerGenerator = new Faker(new Locale(locale));
        return fakerGenerator.address().city();
    }

    public static String generateName(String locale) {
        Faker fakerGenerator = new Faker(new Locale(locale));
        return fakerGenerator.name().fullName();
    }

    public static String generatePhone(String locale) {
        Faker fakerGenerator = new Faker(new Locale(locale));
        return fakerGenerator.phoneNumber().cellPhone();
    }

    public static class Registration {
        private Registration() {
        }

        public static DataGenerator.UserInfo generateUser(String locale) {
            String city = generateCity(locale);
            String name = generateName(locale);
            String phone = generatePhone(locale);
            DataGenerator.UserInfo user = new DataGenerator.UserInfo(city, name, phone);
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
