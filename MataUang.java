public abstract class MataUang {
    protected double nilaiTukar;

    public double getNilaiTukar() {
        return nilaiTukar;
    };

    abstract String getSimbol(); // Mengembalikan simbol mata uang
}