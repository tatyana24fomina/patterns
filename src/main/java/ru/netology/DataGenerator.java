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
            DataGenerator.UserInfo user = new DataGenerator.UserInfo();
            DataGenerator generator = new DataGenerator();
            user.setCity(generator.generateCity(locale));
            user.setName(generator.generateName(locale));
            user.setPhone(generator.generatePhone(locale));
            return user;
        }
    }

    public static class UserInfo {
        private UserInfo() {}
            String city;
            String name;
            String phone;
            public String getCity() {
                return city;
            }
            public void setCity(String city) {
                this.city = city;
            }

            public String getName() {
                return name;
            }
            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

        }
    }
