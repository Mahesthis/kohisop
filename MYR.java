public class MYR extends MataUang {
    public MYR() {
        this.nilaiTukar = 1.0/4000;
    }

    @Override
    public String getSimbol() {
        return "MYR";
    }
}