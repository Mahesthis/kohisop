public class Pesanan {
    private ItemMenu itemMenu;
    private int kuantitas;
    private double totalHarga;
    private double pajak;

    public Pesanan(ItemMenu itemMenu, int kuantitas) {
        this.itemMenu = itemMenu;
        this.kuantitas = kuantitas;
        // langsung dihitung begitu pesanan dibuat
        hitungTotalHarga();
    }

    public ItemMenu getItemMenu() {
        return itemMenu;
    }

    public int getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(int kuantitas) {
        this.kuantitas = kuantitas;
        // hitung ulang total harga setelah kuantitas berubah
        hitungTotalHarga();
        System.out.println("\n=> Berhasil menyimpan pesanan!");
    }

    private void hitungTotalHarga() {
        totalHarga = itemMenu.getHarga() * kuantitas;
        // pajak dihitung setelah total harga dihitung
        hitungPajak();
    }

    public double getTotalHarga() {
        hitungTotalHarga();
        return totalHarga;
    }

    public void hitungPajak() {
        pajak = itemMenu.getPersenPajak() * totalHarga;
    }

    public double getPajak() {
        return pajak;
    }

    public double getPersenPajak() {
        return itemMenu.getPersenPajak();
    }
}
