public class User {
    private String userName;
    private String name;
    private String password;
    private double balance;


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getID() {
        return ID;
    }

    private int ID;
    public User(String userName, String name, String password, double balance,int ID) {
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.balance =balance;
        this.ID=ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
