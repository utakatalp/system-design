public class Debt {
    private double fee;
    private int id;
    private int userid;
    private int month;

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Debt(double fee, int id, int month) {
        this.fee = fee;
        this.id = id;

        this.month = month;
    }

    @Override
    public String toString() {
        return month + ". Ay Aidatı: " + fee +" TL";
    }
}
