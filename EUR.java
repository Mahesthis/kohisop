public class EUR extends MataUang {
    public EUR() {
        this.nilaiTukar = 1.0 / 14000;
    }

    @Override
    public String getSimbol() {
        return "EUR";
    }
}