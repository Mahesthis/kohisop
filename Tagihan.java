public class Tagihan {
        Pesanan[] daftarPesanan; //BELUM
        int jmlMakanan;
        int jmlMinuman;
        MataUang mataUang; // BELUMM
        Pembayaran channel; //BELUM
        double nominalAkhir;
        double nominalAkhirDenganPajak;

        public Tagihan(Pesanan[] daftarPesanan, int jmlMakanan, int jmlMinuman, MataUang mataUang,
                        Pembayaran channel, double nominalAkhir, double nominalAkhirDenganPajak) { //BELUM
                this.daftarPesanan = daftarPesanan;
                this.jmlMakanan = jmlMakanan;
                this.jmlMinuman = jmlMinuman;
                this.mataUang = mataUang;                                           // BELUM SEMUA
                this.channel = channel;
                this.nominalAkhir = nominalAkhir;
                this.nominalAkhirDenganPajak = nominalAkhirDenganPajak;
        }

        public void tampilkanTagihan() {
                System.out
                                .println("===============================================================================");
                System.out.printf("|%77s|\n", "");
                System.out.printf("|%35s KohiSop%35s\n", "", "|");
                System.out.printf("|%77s|\n", "");

                System.out
                                .println("===============================================================================");

                int totalSemuaPesanan = 0;
                double totalSemuaPesananDenganPajak = 0;

                if (jmlMakanan > 0) {
                        System.out.printf("| %-4s | %-23s | %-9s | %-3s | %-11s | %-10s |\n", "Kode", "Makanan",
                                        "Harga", "Qty", "Total Harga", "Pajak");
                        System.out.println(
                                        "-------------------------------------------------------------------------------");
                        for (int i = jmlMinuman; i < daftarPesanan.length; i++) { //RAGU
                                Pesanan pesanan = daftarPesanan[i];
                                if (pesanan != null) {
                                        String kode = pesanan.getItemMenu().getKode();
                                        String nama = pesanan.getItemMenu().getNama();
                                        int harga = pesanan.getItemMenu().getHarga();
                                        int kuantitas = pesanan.getKuantitas();                                                         //Belum semua
                                        int totalHarga = pesanan.getTotalHarga();
                                        double pajak = pesanan.getPajak();
                                        totalSemuaPesanan += totalHarga;
                                        totalSemuaPesananDenganPajak += totalHarga + pajak;
                                        System.out.printf("| %-4s | %-23s | Rp%-7s | %-3s | Rp%-9s | Rp%-8s |\n", kode,
                                                        nama, harga, kuantitas,
                                                        totalHarga, pajak);
                                }
                        }
                }

                if (jmlMinuman > 0) {
                        if (jmlMakanan > 0) {
                                System.out.println(
                                                "-------------------------------------------------------------------------------");
                        }
                        System.out.printf("| %-4s | %-23s | %-9s | %-3s | %-11s | %-10s |\n", "Kode", "Minuman",
                                        "Harga",
                                        "Qty", "Total Harga", "Pajak");
                        System.out.println(
                                        "-------------------------------------------------------------------------------");
                        for (int i = 0; i < jmlMinuman; i++) {
                                Pesanan pesanan = daftarPesanan[i];
                                if (pesanan != null) {
                                        String kode = pesanan.getItemMenu().getKode();
                                        String nama = pesanan.getItemMenu().getNama();
                                        int harga = pesanan.getItemMenu().getHarga();
                                        int kuantitas = pesanan.getKuantitas();
                                        int totalHarga = pesanan.getTotalHarga();                                                      //BELUM SEMUA
                                        double pajak = pesanan.getPajak();
                                        totalSemuaPesanan += totalHarga;
                                        totalSemuaPesananDenganPajak += totalHarga + pajak;
                                        System.out.printf("| %-4s | %-23s | Rp%-7s | %-3s | Rp%-9s | Rp%-8s |\n", kode,
                                                        nama, harga, kuantitas,
                                                        totalHarga, pajak);
                                }
                        }
                }

                System.out
                                .println("-------------------------------------------------------------------------------");
                System.out.printf("| %-42s : Rp%-28d |\n", "Total Harga Tanpa Pajak", totalSemuaPesanan);
                System.out.printf("| %-42s : Rp%-28.2f |\n", "Total Harga Dengan Pajak", totalSemuaPesananDenganPajak);
                System.out.printf("| %-42s : %-30s |\n", "Mata Uang", mataUang.getSimbol());
                System.out.printf("| %-42s : %-30s |\n", "Channel Pembayaran", channel.getDeskripsi());
                if (channel instanceof Emoney) {
                        System.out.printf("| %-42s : Rp%-28s |\n", "Biaya Admin", channel.getAdmin());
                }
                System.out.printf("| %-42s : %d%%%28s |\n", "Diskon Channel Pembayaran",
                                (int) (channel.getDiskon() * 100), "");
                System.out.printf("| %-20s%-3s%-19s : %-4s%-26.2f |\n", "Tagihan Akhir dalam", mataUang.getSimbol(),
                                " Tanpa Pajak", mataUang.getSimbol(), nominalAkhir);
                System.out.printf("| %-20s%-3s%-19s : %-4s%-26.2f |\n", "Tagihan Akhir dalam", mataUang.getSimbol(),
                                " Dengan Pajak", mataUang.getSimbol(), nominalAkhirDenganPajak);

                System.out
                                .println("===============================================================================");
                System.out.printf("|%77s|\n", "");
                System.out.printf("|%19sTerima kasih dan silakan datang kembali!%19s\n", "", "|");
                System.out.printf("|%77s|\n", "");
                System.out
                                .println("===============================================================================");
        }
}
