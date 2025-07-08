public class MenuOption {
    private int number;
    private String name;

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public MenuExecutor executor;


    public MenuOption(int number, String name, MenuExecutor executor) {
        this.number = number;
        this.name = name;
        this.executor = executor;
    }

    public void execute() {
        executor.execute();
    }






}
