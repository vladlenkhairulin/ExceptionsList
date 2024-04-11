package View;
import Domain.Exceptions.FIOException;
import Domain.Exceptions.GenderException;
import Domain.Exceptions.PhoneNumberException;
import Domain.Person;
import Service.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ViewUser {
    private Logger logger;

    public ViewUser(Logger logger) {
        this.logger = logger;
    }

    /**
     * @throws GenderException введён неизвестный символ пола (должен быть "m"/"f")
     * @throws PhoneNumberException в телефоне присутсвуют буквы или посторонние символы
     * @throws FIOException в ФИО есть цифры или посторонние символы
     */
    public void run() throws GenderException, PhoneNumberException, FIOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Хотите выйти из программы? (yes/no)");
            String answer = scanner.nextLine();
            if (answer.equals("yes")) {
                break;
            } else if (answer.equals("no")) {
                try {
                    System.out.println("Введите данные через пробел (Фамилия Имя Отчество Дата_рождения Номер_телефона Пол):" +
                            "(Формат даты: ДД.ММ.ГГГГ)");
                    String input = scanner.nextLine();

                    String[] data = input.split(" ");
                    if (data.length != 6) {
                        System.out.println("Неверное количество аргументов. Пожалуйста, введите полную информацию о человеке.");
                        continue;
                    }


                    String lastName = data[0];
                    String firstName = data[1];
                    String middleName = data[2];
                    String stringDateOfBirth = data[3];
                    String stringPhoneNumber = data[4];

                    // Проверка фамилии, имени и отчества. Нашёл способ проверки с "\\p{L}+", вызывается ошибка, если в имени человека есть любые символы, отличные от  букв.
                    if (!lastName.matches("\\p{L}+") || !firstName.matches("\\p{L}+") || !middleName.matches("\\p{L}+")) {
                        throw new FIOException("Фамилия, имя и отчество должны состоять только из букв.");
                    }

                    final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                    Date dateOfBirth; // пока что это пустая переменная. Она получит своё значение, только если не возникнет ошибка ParseException
                    try {
                        dateOfBirth = format.parse(stringDateOfBirth);
                    } catch (ParseException e) {
                        System.out.println("Неверный формат даты рождения. Используйте формат ДД.ММ.ГГГГ.");
                        continue;
                    }

                    // Перменная пока что пуста. Она получит значение, если номер телефона пройдет проверку.
                    long phoneNumber;
                    try {
                        phoneNumber = Long.parseLong(stringPhoneNumber);
                        if (phoneNumber <= 0) {
                            throw new PhoneNumberException("Номер телефона должен быть положительным числом.");
                        }
                    } catch (NumberFormatException e) {
                        throw new PhoneNumberException("Номер телефона должен быть целым числом.");
                    }
                    char gender = data[5].charAt(0);
                    char lowercaseGender = Character.toLowerCase(gender);
                    if (lowercaseGender != 'f' && lowercaseGender != 'm') {
                        throw new GenderException("Пол должен быть указан одним символом: 'f' или 'm'.");
                    }

                    Person person = new Person(lastName, firstName, middleName, dateOfBirth, phoneNumber, gender);
                    logger.log(person);
                }
                catch (IOException | ParseException e) {
                    System.out.println("Ошибка при чтении/записи данных в файле. Стектрейс ошибки: " );
                    e.printStackTrace();
                };
            } else {
                System.out.println("Некорректный ввод. Введите 'yes' или 'no'.");
            }

        }
    }
}