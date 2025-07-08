import java.util.Scanner;

public class Menu {
    private MenuOption[] options = new MenuOption[10];

    private int optionsCount;

    private boolean sessionStopped;

    public void addOption(MenuOption option) {
        options [optionsCount] = option;              // можно написать [optionsCount++]
        optionsCount++;
    }

    public void hide() {
        sessionStopped = true;
    }

    public void show(){
        while (!sessionStopped) {
            // 1. отобразить на экране пункты меню (menu options)
            for (int i = 0; i < optionsCount; i++) {
                MenuOption opt = options[i];
                System.out.print(opt.getNumber());
                System.out.println(". " + opt.getName());
            }
            // 2. попросить пользователя выбрать пункт меню (ввести его номер)
            Scanner sc = new Scanner(System.in);
            int n;
            try {
                n = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Повторите попытку:");
                continue;
            }
            // 3. найти пункт меню по введенному номеру
            MenuOption selectedOption = null;
            for (int i = 0; i < optionsCount; i++) {
                MenuOption opt = options[i];
                if (n == opt.getNumber()) {
                    selectedOption = opt;
                    break;
                }
            }
            if (selectedOption == null) {
                System.out.println("Выбран неверный пункт меню");
                continue;
            }
            // 4. выполнить выбранный пункт меню
            selectedOption .execute();


        }
    }

}
