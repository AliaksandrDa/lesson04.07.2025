public class IncorrectPinException extends Exception {
    private int attemptsLeft;

    public IncorrectPinException(int attemptsLeft) {
        this.attemptsLeft = attemptsLeft;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

}
