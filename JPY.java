public class JPY extends MataUang {
    public JPY() {
        this.nilaiTukar = 1.0/100;
    }

    @Override
    public String getSimbol() {
        return "JPY";
    }
}