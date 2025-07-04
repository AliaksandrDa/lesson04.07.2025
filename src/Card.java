public class Card {
    private long number;
    private short pin;
    private long amount;

    private int failPinCounter;

    private boolean isBlocked;

    public Card(long number, short pin, long amount) {
        this.amount = amount;
        this.pin = pin;
        this.number = number;
    }


    public long getNumber() {
        return number;
    }

    public short getPin() {
        return pin;
    }

    public long getAmount() {
        return amount;
    }

    public long changeAmount(long request) throws LowMoneyException {
        if (request > amount) {
            throw new LowMoneyException(amount);
        }
        amount -= request;
        return amount;
    }

    public int getFailPinCounter() {
        return failPinCounter;
    }

    public void setFailPinCounter(int failPinCounter) {
        this.failPinCounter = failPinCounter;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
