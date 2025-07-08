//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        try {
            atm.requestCardNumber();
            atm.requestPin();
        } catch (CardBlockedException e) {
            System.out.println("Ваша карта заблокирована. Обратитесь в банк");
            return;
        } catch (InvalidCardException e) {
            System.out.println("Неверный номер карты.");
            return;
        }
        atm.showMainMenu();
    }
}