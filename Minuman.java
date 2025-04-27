public class Minuman extends ItemMenu {
    public Minuman(String kode, String nama, double harga) {
        super(kode, nama, harga);
        setPersenPajak();
    }

    @Override
    final protected void setPersenPajak() {
        if (harga < 50000) {
            pajak = 0;
        } else if (harga > 55000) {
            pajak = 0.11;
        } else {
            pajak = 0.08;
        }
    }
}
