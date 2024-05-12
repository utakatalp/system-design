public class User {
    private String apartmentNumber;
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
    public User(String apartmentNumber, String name, String password, double balance, int ID) {
        this.apartmentNumber = apartmentNumber;
        this.name = name;
        this.password = password;
        this.balance =balance;
        this.ID=ID;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
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
