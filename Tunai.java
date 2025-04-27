public class Tunai extends Pembayaran {

    public Tunai(double nominal, double pajak) {
        super(nominal, 0, 0, pajak);
    }

    @Override
    public boolean periksaPembayaran() {
        System.out.println("=> Pembayaran berhasil menggunakan Tunai.");
        return true; // Pembayaran tunai selalu berhasil
    }

    @Override
    public double hitungTotalBayar() {
        totalBayar = nominal;
        return totalBayar;
    }

    @Override
    public double hitungTotalBayarDenganPajak() {
        totalBayarDenganPajak = nominal + pajak;
        return totalBayarDenganPajak;
    }

    @Override
    public double getAdmin() {
        return admin;
    };

    @Override
    public double getDiskon() {
        return 0; // Tidak ada diskon
    }

    @Override
    public String getDeskripsi() {
        deskripsi = "Tunai";
        return deskripsi;
    }
}
