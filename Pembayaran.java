public abstract class Pembayaran {
    protected double nominal;
    protected double diskon;
    protected double admin;
    protected double totalBayar;
    protected double totalBayarDenganPajak;
    protected String deskripsi;
    protected double pajak;

    public Pembayaran(double nominal, double diskon, double admin, double pajak) {
        this.nominal = nominal;
        this.diskon = diskon;
        this.admin = admin;
        this.pajak = pajak;
    }

    // Metode abstrak untuk memproses pembayaran
    public abstract boolean periksaPembayaran();

    // Metode abstrak untuk mengembalikan diskon
    public abstract double getDiskon();

    public abstract double getAdmin();

    public abstract String getDeskripsi();

    // Getter untuk nominal
    public double getNominal() {
        return nominal;
    }

    public double getTotalBayar() {
        return totalBayar;
    }

    public double getTotalBayarDenganPajak() {
        return totalBayarDenganPajak;
    }

    public abstract double hitungTotalBayar();

    public abstract double hitungTotalBayarDenganPajak();
}