public class LowMoneyException extends Exception {
    private long allowedAmount;

    public LowMoneyException(long allowedAmount) {
        this.allowedAmount = allowedAmount;
    }

    public long getAllowedAmount() {
        return allowedAmount;
    }
}
