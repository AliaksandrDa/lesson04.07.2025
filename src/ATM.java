import java.util.Scanner;
import java.util.SortedMap;

public class ATM {
    private Menu menu;
    private long cardNumber;

    ATM() {
        menu = new Menu();
        menu.addOption(new MenuOption(1, "Выдача наличных", new MenuExecutor() {    //  на основании интерфейса можно создать "анонимный" объект

            public void execute() {
                while (true) {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("ВВедите суммы средств для снятия:");
                    long amount;
                    try {
                        amount = sc.nextLong();
                    } catch (Exception e) {
                        System.out.println("Некорректный запрос");
                        continue;
                    }
                    try {
                        long newAmount = Bank.takeCash(cardNumber, amount);
                        System.out.println("Остаток на вашем счете: ");
                        System.out.println(newAmount);
                        break;
                    } catch (CardBlockedException e) {
                        System.out.println("Ваша карта заблокирована. Обратитесь в банк.");
                        break;
                    } catch (InvalidCardException e) {
                        System.out.println("Банкомат неисправен. Мы работаем над этим");
                        break;
                    } catch (LowMoneyException e) {
                        System.out.println("Недостаточно средств на счете");
                        System.out.print("Доступно средств: ");
                        System.out.println(e.getAllowedAmount());
                    }

                }
            }
        }));
        menu.addOption(new MenuOption(2, "Остаток на счете", new MenuExecutor() {
            public void execute() {
                try {
                    long amount = Bank.getCardAmount(cardNumber);
                    System.out.print("Текущий остаток на счете: ");
                    System.out.println(amount);
                } catch (CardBlockedException e) {
                    System.out.println("Ваша карта заблокирована. Обратитесь в банк.");
                } catch (InvalidCardException e) {
                    System.out.println("Банкомат неисправен. Мы работаем над этим");
                }
            }
        }));
        menu.addOption(new MenuOption(3, "Завершение работы с банкоматом", new MenuExecutor() {
            public void execute() {
                menu.hide();
            }
        }));
    }

    public void showMainMenu() {
        menu.show();
    }

    public void requestCardNumber() throws CardBlockedException {
        while (true) {
            System.out.println("Вставьте карту (введите номер карты):");
            Scanner sc = new Scanner(System.in);
            try {
                cardNumber = sc.nextLong();
            } catch (Exception e) {
                System.out.println("Неверный номер карты");
                continue;
            }

            try {
                Bank.startCardSession(cardNumber);
            } catch (InvalidCardException e) {
                System.out.println("Карта не найдена");
                continue;
            } catch (CardBlockedException e) {
                throw e;
            }
            break;
        }
    }
    public void requestPin() throws InvalidCardException, CardBlockedException {
        while (true) {
            System.out.println("Введите ваш ПИН-код");
            Scanner sc = new Scanner(System.in);
            short pin;

            try {
                pin = sc.nextShort();
            } catch (Exception e) {
                System.out.println("Неверный ввод. Введите 4 цифры:");
                continue;
            }
            try {
                Bank.checkPin(cardNumber, pin);
                break;
            } catch (InvalidCardException e) {
                System.out.println("Банкомат неисправен. Мы работаем над этим");
                throw e;                                       // специально ломаем и передаем ошибку в Main
            } catch (IncorrectPinException e) {
                System.out.println("Неверный ПИН-код");
                System.out.print("Осталось попыток: ");
                System.out.println(e.getAttemptsLeft());
            } catch (CardBlockedException e) {
                throw e;
            }
        }
    }
}
