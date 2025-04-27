public class Emoney extends Pembayaran {
    private double saldo;

    public Emoney(double nominal, double saldo, double pajak) {
        super(nominal, 0.07, 20000, pajak);
        this.saldo = saldo;
    }

    @Override
    public boolean periksaPembayaran() {
        double totalBayar = hitungTotalBayarDenganPajak();
        if (saldo >= totalBayar) {
            saldo -= totalBayar;
            System.out.println("\n=> Pembayaran berhasil menggunakan eMoney.");
            return true;
        } else {
            System.out.println("\n=> Saldo tidak cukup untuk pembayaran menggunakan eMoney.");
            return false;
        }
    }

    @Override
    public double hitungTotalBayar() {
        totalBayar = nominal - (nominal * diskon) + admin;
        return totalBayar;
    }

    @Override
    public double hitungTotalBayarDenganPajak() {
        totalBayarDenganPajak = hitungTotalBayar() + pajak;
        return totalBayarDenganPajak;
    }

    @Override
    public double getDiskon() {
        return 0.07; // Diskon 7%
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public double getAdmin() {
        return admin;
    }

    @Override
    public String getDeskripsi() {
        deskripsi = "eMoney";
        return deskripsi;
    }
}
