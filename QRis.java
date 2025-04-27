public class QRis extends Pembayaran {
    private double saldo;

    public QRis(double nominal, double saldo, double pajak) {
        super(nominal, 0.05, 0, pajak);
        this.saldo = saldo;
    }

    @Override
    public boolean periksaPembayaran() {
        totalBayar = hitungTotalBayarDenganPajak();
        if (saldo >= totalBayar) {
            saldo -= totalBayar;
            System.out.println("\n=> Pembayaran berhasil menggunakan QRIS.");
            return true;
        } else {
            System.out.println("\n=> Saldo tidak cukup untuk pembayaran menggunakan QRIS.\n");
            return false;
        }
    }

    @Override
    public double hitungTotalBayar() {
        totalBayar = nominal - (nominal * diskon);
        return totalBayar;
    }

    @Override
    public double hitungTotalBayarDenganPajak() {
        totalBayarDenganPajak = hitungTotalBayar() + pajak;
        return totalBayarDenganPajak;
    }

    @Override
    public double getDiskon() {
        return diskon;
    }

    @Override
    public double getAdmin() {
        return admin;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String getDeskripsi() {
        deskripsi = "QRIS";
        return deskripsi;
    }
}
