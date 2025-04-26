public class USD extends MataUang {
    public USD() {
        this.nilaiTukar = 1.0/15000;
    }

    @Override
    public String getSimbol() {
        return "USD";
    }
}