public abstract class ItemMenu {
    protected String kode;
    protected String nama;
    protected double harga;
    protected double pajak;

    public ItemMenu(String kode, String nama, double harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        // setPersenPajak();
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    protected void setPersenPajak() {
    }

    public double getPersenPajak() {
        return pajak;
    }
}
