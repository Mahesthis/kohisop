public class Tagihan {
        private Pesanan[] daftarPesanan;
        private int jmlMakanan;
        private int jmlMinuman;
        private MataUang mataUang;
        private Pembayaran channel;
        private double nominalAkhir;
        private double nominalAkhirDenganPajak;
        private double totalSemuaPesanan = 0;
        private double totalSemuaPesananDenganPajak = 0;
        private double totalPajak = 0;

        public Tagihan(Pesanan[] daftarPesanan, int jmlMakanan, int jmlMinuman, MataUang mataUang,
                        Pembayaran channel, double nominalAkhir, double nominalAkhirDenganPajak) {
                this.daftarPesanan = daftarPesanan;
                this.jmlMakanan = jmlMakanan;
                this.jmlMinuman = jmlMinuman;
                this.mataUang = mataUang;
                this.channel = channel;
                this.nominalAkhir = nominalAkhir;
                this.nominalAkhirDenganPajak = nominalAkhirDenganPajak;
        }

        public void tampilkanTagihan() {
                System.out
                                .println("================================================================================");
                System.out.printf("|%78s|\n", "");
                System.out.printf("|%35s KohiSop %35s\n", "", "|");
                System.out.printf("|%78s|\n", "");

                System.out
                                .println("================================================================================");

                totalSemuaPesanan = 0;
                totalSemuaPesananDenganPajak = 0;
                totalPajak = 0;

                if (jmlMakanan > 0) {
                        System.out.printf("| %-4s | %-23s | %-10s | %-3s | %-11s | %-10s |\n", "Kode", "Makanan",
                                        "Harga", "Qty", "Total Harga", "Pajak");
                        System.out.println(
                                        "--------------------------------------------------------------------------------");
                        for (int i = jmlMinuman; i < daftarPesanan.length; i++) {
                                Pesanan pesanan = daftarPesanan[i];
                                if (pesanan != null) {
                                        String kode = pesanan.getItemMenu().getKode();
                                        String nama = pesanan.getItemMenu().getNama();
                                        double harga = pesanan.getItemMenu().getHarga();
                                        int kuantitas = pesanan.getKuantitas();
                                        double totalHarga = pesanan.getTotalHarga();
                                        double pajak = pesanan.getPajak();
                                        totalPajak += pajak;
                                        totalSemuaPesanan += totalHarga;
                                        totalSemuaPesananDenganPajak += totalHarga + pajak;
                                        System.out.printf("| %-4s | %-23s | Rp%-8.2f | %-3s | Rp%-9s | Rp%-8s |\n",
                                                        kode,
                                                        nama, harga, kuantitas, totalHarga, pajak);
                                }
                        }
                }

                if (jmlMinuman > 0) {
                        if (jmlMakanan > 0) {
                                System.out.println(
                                                "--------------------------------------------------------------------------------");
                        }
                        System.out.printf("| %-4s | %-23s | %-10s | %-3s | %-11s | %-10s |\n", "Kode", "Minuman",
                                        "Harga", "Qty", "Total Harga", "Pajak");
                        System.out.println(
                                        "--------------------------------------------------------------------------------");
                        for (int i = 0; i < jmlMinuman; i++) {
                                Pesanan pesanan = daftarPesanan[i];
                                if (pesanan != null) {
                                        String kode = pesanan.getItemMenu().getKode();
                                        String nama = pesanan.getItemMenu().getNama();
                                        double harga = pesanan.getItemMenu().getHarga();
                                        int kuantitas = pesanan.getKuantitas();
                                        double totalHarga = pesanan.getTotalHarga();
                                        double pajak = pesanan.getPajak();
                                        totalPajak += pajak;
                                        totalSemuaPesanan += totalHarga;
                                        totalSemuaPesananDenganPajak += totalHarga + pajak;
                                        System.out.printf("| %-4s | %-23s | Rp%-8.2f | %-3s | Rp%-9.2f | Rp%-8.2f |\n",
                                                        kode,
                                                        nama, harga, kuantitas, totalHarga, pajak);
                                }
                        }
                }

                System.out
                                .println("--------------------------------------------------------------------------------");
                System.out.printf("| %-43s : Rp%-28.2f |\n", "Total Harga Tanpa Pajak", totalSemuaPesanan);
                System.out.printf("| %-43s : Rp%-28.2f |\n", "Total Pajak", totalPajak);
                System.out.printf("| %-43s : Rp%-28.2f |\n", "Total Harga Dengan Pajak", totalSemuaPesananDenganPajak);
                System.out.printf("| %-43s : %-30s |\n", "Mata Uang", mataUang.getSimbol());
                System.out.printf("| %-43s : %-30s |\n", "Channel Pembayaran", channel.getDeskripsi());
                if (channel instanceof Emoney) {
                        System.out.printf("| %-43s : Rp%-28.2f |\n", "Biaya Admin", channel.getAdmin());
                }
                System.out.printf("| %-43s : %d%%%28s |\n", "Diskon Channel Pembayaran (%)",
                                (int) (channel.getDiskon() * 100), "");
                System.out.printf("| %-43s : Rp%-28.2f |\n", "Diskon Channel Pembayaran",
                                (channel.getDiskon() * totalSemuaPesanan));
                System.out.printf("| %-43s : %-4s%-26.2f |\n", "Tagihan Akhir Tanpa Pajak", mataUang.getSimbol(),
                                nominalAkhir);
                System.out.printf("| %-43s : %-4s%-26.2f |\n", "Tagihan Akhir Dengan Pajak", mataUang.getSimbol(),
                                nominalAkhirDenganPajak);

                System.out.println("================================================================================");
                System.out.printf("|%78s|\n", "");
                System.out.printf("|%19s Terima kasih dan silakan datang kembali %19s\n", "", "|");
                System.out.printf("|%78s|\n", "");
                System.out.println("================================================================================");
        }
}
