public class Purchase {
    private String title;
    private String date;
    private int sum;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", sum=" + sum +
                '}';
    }
}
