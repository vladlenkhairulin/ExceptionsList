import Domain.Exceptions.FIOException;
import Domain.Exceptions.GenderException;
import Domain.Exceptions.PhoneNumberException;
import Service.Logger;
import View.ViewUser;


/**
 * НАЗВАНИЕ ПРОЕКТА: "СПИСОК ЛЮДЕЙ"
 * ОПИСАНИЕ: программа принимает от пользователя строку с данными о человеке и сохраняет их в файл
 * Однофамильцы записываются в один файл.
 * ДАТА СОЗДАНИЯ: 11.04.2024
 * АВТОР: Хайрулин Владлен
 * ВЕРСИЯ: 1.0.
 */
public class App {
    public static void main(String[] args) {
        Logger logger = new Logger();
        ViewUser view = new ViewUser(logger);
        try {
            view.run();
        } catch (GenderException e) {
            throw new RuntimeException(e);
        } catch (PhoneNumberException e) {
            throw new RuntimeException(e);
        } catch (FIOException e) {
            throw new RuntimeException(e);
        }
    }
}
