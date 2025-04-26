import java.util.InputMismatchException;
import java.util.Scanner;

public class KohiSop {

    private Minuman[] menuMinuman;
    private Makanan[] menuMakanan;
    private Pesanan[] daftarPesanan;
    private Scanner input;
    private int jumlahPesananMakanan;
    private int jumlahPesananMinuman;
    private int jumlahPesanan;
    private int nominalTransaksi;
    private double totalPajak;

    public KohiSop() {
        menuMinuman = new Minuman[] {
                new Minuman("ET1", "Es Teh", 18000),
                new Minuman("EJ2", "Es Jeruk", 18000),
                new Minuman("EC3", "Es Campur", 22000),
                new Minuman("CD4", "Es Cendol", 24000),
                new Minuman("TT5", "Teh Tarik", 20000),                
                new Minuman("LT6", "Lemon Tea", 20000),
                new Minuman("CL7", "Coffee Latte", 53000),
                new Minuman("IC8", "Iced Cappuccino", 51000),
                new Minuman("MC9", "Milk Chocolate", 53000),
                new Minuman("CM10", "Caramel Macchiato", 57000)
        };

        menuMakanan = new Makanan[] {
                new Makanan("MG1", "Mie Goreng Bangladesh", 30000),
                new Makanan("AG2", "Paket Nasi Ayam Goreng", 32000),
                new Makanan("AB3", "Paket Nasi Ayam Bakar", 32000),
                new Makanan("PM4", "Pisang Goreng Madu", 22000),
                new Makanan("TW5", "Tahu Walik Pedas", 20000),
                new Makanan("BS6", "Bakwan Sayur ebi", 23000),
                new Makanan("CCR7", "Chocolate Croissant", 56000),
                new Makanan("ACR8", "Almond Croissant", 59000),
                new Makanan("SW9", "Salted Caramel Waffles", 62000),
                new Makanan("CW10", "Chocolate Berry Waffles", 62000)
        };

        daftarPesanan = new Pesanan[10]; // maksimal 10 pesanan, 5 minuman dan 5 makanan
        input = new Scanner(System.in);
        jumlahPesananMakanan = 0;
        jumlahPesananMinuman = 0;
        jumlahPesanan = 0;
        nominalTransaksi = 0;
        totalPajak = 0;
    }

    public void pesan() {
        tambahItemPesanan("Minuman");           //BELUM SEMUA
        tambahItemPesanan("Makanan");

        for (int i = 0; i < jumlahPesanan; i++) {
            nominalTransaksi += daftarPesanan[i].getTotalHarga(); //BELUM
            totalPajak += daftarPesanan[i].getPajak(); //BELUM
        }

        tampilkanPerhitunganPajak();

        System.out.println("\n=> Nominal Transaksi (tanpa pajak): Rp" + nominalTransaksi);
        System.out.println("=> Total Pajak: Rp" + totalPajak);

        // memilih channel pembayaran
        Pembayaran channel = pilihChannelPembayaran(nominalTransaksi, totalPajak);

        // memilih mata uang
        MataUang mataUang = pilihMataUang();

        // hitung total tanpa pajak yang dikonversi ke mata uang pilihan
        double nominalDalamMataUang = hitungNominalMataUang(channel.hitungTotalBayar(), mataUang);

        // hitung total dengan pajak yang dikonversi ke mata uang pilihan
        // pajak tidak mendapat diskon channel pembayaran
        double nominalDalamMataUangDenganPajak = hitungNominalMataUang(channel.hitungTotalBayarDenganPajak(),
                mataUang);

        // buat objek tagihan
        Tagihan bill = new Tagihan(daftarPesanan, jumlahPesananMakanan, jumlahPesananMinuman, mataUang, channel,
                nominalDalamMataUang, nominalDalamMataUangDenganPajak);

        // tampilkan tagihan
        bill.tampilkanTagihan();
    }

    private void tampilkanMenu(String jenis) {
        ItemMenu[] itemMenu = null;
        if (jenis.equalsIgnoreCase("Makanan")) {
            // memastikan string item diawali huruf kapital dan sisanya huruf kecil
            jenis = "Makanan";
            itemMenu = menuMakanan;
        } else if (jenis.equalsIgnoreCase("Minuman")) {
            // memastikan string item diawali huruf kapital dan sisanya huruf kecil
            jenis = "Minuman";
            itemMenu = menuMinuman;
        } else {
            System.out.println("\n=> Item tidak valid");
            System.out.println("=> Item dalam menu adalah makanan atau minuman\n");
            return;
        }
        System.out.println("============================================");
        System.out.printf("|               Menu %s               |\n", jenis);
        System.out.println("============================================");
        System.out.printf("| %-4s | %-23s | %-7s |\n", "Kode", jenis, "Harga");
        System.out.println("--------------------------------------------");
        for (ItemMenu item : itemMenu) {
            System.out.printf("| %-4s | %-23s | Rp%-5d |\n", item.getKode(), item.getNama(), item.getHarga());
        }
        System.out.println("============================================");
    }

    private void tambahItemPesanan(String jenis) {
        boolean tambah = true;
        int jumlah;
        ItemMenu[] menu = null;
        if (jenis.equalsIgnoreCase("Makanan")) {
            jumlah = jumlahPesananMakanan;
            menu = menuMakanan;
        } else {
            jumlah = jumlahPesananMinuman;
            menu = menuMinuman;
        }
        while (tambah) {
            if (jumlah == 5) {
                System.out.printf("5 %s telah dipesan!\n", jenis.toLowerCase());
                return;
            }
            tampilkanMenu(jenis);
            System.out.printf("Inputkan kode %s yang ingin anda pesan: ", jenis.toLowerCase());
            String kode = input.nextLine(); //RAGU

            // jika s, maka skip
            if (kode.equalsIgnoreCase("S")) {
                // pesan yang ditampilkan
                if (jumlah == 0) {
                    System.out.printf("Tidak ada %s yang dipesan\n", jenis.toLowerCase());
                } else {
                    System.out.printf("\n=> Selesai memesan %s!\n", jenis.toLowerCase());
                    tampilkanPesanan();
                }
                tambah = false;
            } else if (kode.equalsIgnoreCase("CC")) {
                stopProgram();  //SAMPAI SINI
            } else {
                ItemMenu item = cariDiMenu(menu, kode);
                if (item != null) {
                    // jika ternyata item sudah ada di daftar pesanan
                    if (itemDipesan(item)) {
                        int kuantitas = inputKuantitas(item);
                        // jika kuantitas baru > 0
                        if (kuantitas > 0) {
                            // ganti kuantitas lama dengan kuantitas baru
                            daftarPesanan[cariIndexPesanan(item)].setKuantitas(kuantitas);
                            System.out.println("\n=> Berhasil menyimpan pesanan!");
                            tampilkanPesanan();
                            // jika kuantitas baru adalah 0, pesanan dibatalkan
                        } else {
                            System.out.println("\n=> Batal memesan " + item.getNama());
                            // hapus pesanan dari daftar dan rapikan array daftar pesanan
                            hapusItemPesanan(cariIndexPesanan(item));
                            jumlah--;
                            tampilkanPesanan();
                        }
                    } else {
                        int kuantitas = inputKuantitas(item);
                        if (kuantitas > 0) {
                            // polimorfisme disini
                            daftarPesanan[jumlahPesanan] = new Pesanan(item, kuantitas);
                            if (item instanceof Makanan) {
                                jumlahPesananMakanan++;
                            } else {
                                jumlahPesananMinuman++;
                            }
                            jumlah++;
                            jumlahPesanan++;
                            System.out.println("\n=> Berhasil menyimpan pesanan!\n");
                            tampilkanPesanan();
                        } else {
                            System.out.println("\n=> Batal memesan " + item.getNama());
                        }
                    }
                } else {
                    System.out.println("\n=> Kode yang anda masukkan tidak valid!\n");
                }
            }
        }
    }

    private boolean itemDipesan(ItemMenu item) {
        for (int i = 0; i < jumlahPesanan; i++) {
            if (item.getNama() == daftarPesanan[i].getItemMenu().getNama()) {
                return true;
            }
        }
        return false;
    }

    private void hapusItemPesanan(int index) {
        if (daftarPesanan[index].getItemMenu() instanceof Makanan) {
            jumlahPesananMakanan--;
        } else {
            jumlahPesananMinuman--;
        }
        for (int i = index; i < jumlahPesanan; i++) {
            daftarPesanan[i] = daftarPesanan[i + 1];
        }
        daftarPesanan[jumlahPesanan - 1] = null;
        jumlahPesanan--;
    }

    private int cariIndexPesanan(ItemMenu item) {
        for (int i = 0; i < jumlahPesanan; i++) {
            if (item.getNama() == daftarPesanan[i].getItemMenu().getNama()) {
                return i;
            }
        }
        return -1;
    }

    private ItemMenu cariDiMenu(ItemMenu[] menu, String kode) {
        for (ItemMenu item : menu) {
            if (item.getKode().equalsIgnoreCase(kode)) {
                return item;
            }
        }
        return null;
    }

    private int inputKuantitas(ItemMenu item) {
        while (true) {
            System.out.print("Inputkan kuantitas " + item.getNama() + " : ");
            String stringKuantitas = input.nextLine();
            if (stringKuantitas.equalsIgnoreCase("CC")) {
                stopProgram();
            }
            if (stringKuantitas.equalsIgnoreCase("S")) {
                System.out.println("Batal memesan " + item.getNama());
                return 0;
            }
            if (stringKuantitas.equalsIgnoreCase("")) {
                // jika pengguna tidak input apapun (hanya enter saja) maka kuantitasnya
                // default, yaitu 1
                System.out.println("\n=> Menyimpan pesanan dengan kuantitas default");
                return 1;
            }
            // tidak perlu else karena jika if dijalankan akan langsung return 0, yang bawah
            // tidak dijalankan
            // pakai try catch karena bisa saja input bukan angka
            try {
                int kuantitas = Integer.parseInt(stringKuantitas);
                if ((item instanceof Minuman && kuantitas >= 0 && kuantitas <= 3)
                        || (item instanceof Makanan && kuantitas >= 0 && kuantitas <= 2)) {
                    return kuantitas;
                } else {
                    System.out.println("\n=> Jumlah pesanan tidak valid!");
                    if (item instanceof Makanan) {
                        System.out.println("=> Kuantitas maksimal untuk tiap makanan adalah 2!\n");
                    } else {
                        System.out.println("=> Kuantitas maksimal untuk tiap minuman adalah 3!\n");
                    }
                }
            } catch (NumberFormatException e) {
                invalidInput();
            }
        }
    }

    private void tampilkanPesanan() {
        if (jumlahPesanan == 0) {
            return;
        }
        System.out.println("==============================================");
        System.out.println("|               Daftar Pesanan               |");
        System.out.println("==============================================");
        if (jumlahPesananMinuman > 0) {
            System.out.printf("| %-4s | %-23s | %-9s |\n", "Kode", "Minuman", "Kuantitas");
            System.out.println("----------------------------------------------");
            for (int i = 0; i < jumlahPesanan; i++) {
                // hanya item berupa minuman yang dicetak
                if (daftarPesanan[i].getItemMenu() instanceof Minuman) {
                    Pesanan pesanan = daftarPesanan[i];
                    System.out.printf("| %-4s | %-23s | %-9d |\n", pesanan.getItemMenu().getKode(),
                            pesanan.getItemMenu().getNama(), pesanan.getKuantitas());
                }
            }
            if (jumlahPesananMakanan > 0) {
                System.out.println("----------------------------------------------");
            }
        }
        if (jumlahPesananMakanan > 0) {
            System.out.printf("| %-4s | %-23s | %-9s |\n", "Kode", "Makanan", "Kuantitas");
            System.out.println("----------------------------------------------");
            for (int i = 0; i < jumlahPesanan; i++) {
                // hanya item berupa makanan yang dicetak
                if (daftarPesanan[i].getItemMenu() instanceof Makanan) {
                    Pesanan pesanan = daftarPesanan[i];
                    System.out.printf("| %-4s | %-23s | %-9d |\n", pesanan.getItemMenu().getKode(),
                            pesanan.getItemMenu().getNama(), pesanan.getKuantitas());
                }
            }

        }
        System.out.println("==============================================\n");
    }

    private void tampilkanPerhitunganPajak() {
        if (jumlahPesanan == 0) {
            System.out.println("Tidak ada pesanan untuk dihitung pajaknya.");
            return;
        }

        System.out.println("===========================================================================");
        System.out.println("|                       Perhitungan Pajak Tiap Item                       |");
        System.out.println("===========================================================================");
        System.out.printf("| %-23s | %-3s | %-7s | %-7s | %-10s |\n", "Kode", "Nama Item", "Qty", "Harga",
                "Pajak (%)", "Pajak");
        System.out.println("---------------------------------------------------------------------------");

        for (int i = 0; i < jumlahPesanan; i++) {
            Pesanan pesanan = daftarPesanan[i];
            ItemMenu item = pesanan.getItemMenu();
            int pajak = (int) (pesanan.getPajak());
            int persenPajak = (int) (pesanan.getPersenPajak() * 100);
            System.out.printf("| %-23s | %-3s | Rp%-5d | %-7d%%  | Rp%-8d |\n",
                    item.getNama(), pesanan.getKuantitas(), item.getHarga(), persenPajak, pajak);
        }

        System.out.println("===========================================================================");
    }

    public Pembayaran pilihChannelPembayaran(double nominal, double totalPajak) {
        Pembayaran pembayaran = null;

        while (true) {
            System.out.println("\n==============================");
            System.out.printf("%-2s Pilih Channel Pembayaran %2s\n", "|", "|");
            System.out.println("==============================");
            System.out.printf("| %4s | %-19s |\n", "Kode", "Channel Pembayaran");
            System.out.println("------------------------------");
            System.out.printf("| %-4d | %-19s |\n", 1, "Tunai");
            System.out.printf("| %-4d | %-19s |\n", 2, "QRIS");
            System.out.printf("| %-4d | %-19s |\n", 3, "eMoney");
            System.out.println("==============================");
            int pilihan = 0;
            boolean inputSalah = true;

            while (inputSalah) {
                while (true) {
                    System.out.print("Masukkan kode channel pilihan Anda: ");
                    try {
                        pilihan = input.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        invalidInput();
                        input.nextLine();
                    }
                }

                switch (pilihan) {
                    case 1:
                        pembayaran = new Tunai(nominal, totalPajak);
                        inputSalah = false;
                        break;
                    case 2:
                        System.out.println("\n--------------------------------------------");
                        System.out.println("                    QRIS                    ");
                        System.out.println("--------------------------------------------");
                        System.out.printf("%-30s%s%.2f\n", "Nominal pembayaran", ": Rp", (nominal + totalPajak));

                        double saldoQRIS = 0;
                        while (true) {
                            try {
                                System.out.printf("%-30s%s", "Masukkan saldo QRIS Anda", ": Rp");
                                saldoQRIS = input.nextDouble();
                                break;
                            } catch (Exception e) {
                                System.out.println("\n=> Input tidak valid!");
                                System.out.println("=> Mohon masukkan nominal (angka) saldo Anda\n");
                                input.nextLine();
                            }
                        }
                        pembayaran = new QRis(nominal, saldoQRIS, totalPajak);
                        inputSalah = false;
                        break;
                    case 3:
                        System.out.println("\n--------------------------------------------");
                        System.out.println("                   eMoney                   ");
                        System.out.println("--------------------------------------------");
                        System.out.printf("%-30s%s%.2f\n", "Nominal pembayaran", ": Rp", (nominal + totalPajak));
                        System.out.printf("%-30s%s\n", "Biaya admin", ": Rp20000");

                        double saldoEMoney = 0;
                        while (true) {
                            try {
                                System.out.printf("%-30s%s", "Masukkan saldo eMoney Anda", ": Rp");
                                saldoEMoney = input.nextDouble();
                                break;
                            } catch (Exception e) {
                                System.out.println("\n=> Input tidak valid!");
                                System.out.println("=> Mohon masukkan nominal (angka) saldo Anda\n");
                                input.nextLine();
                            }
                        }
                        pembayaran = new Emoney(nominal, saldoEMoney, totalPajak);
                        inputSalah = false;
                        break;
                    default:
                        invalidInput();
                        continue;
                }

            }

            if (pembayaran != null) {
                if (pembayaran.periksaPembayaran()) {
                    System.out.println("Diskon yang diterapkan: " + ((int) (pembayaran.getDiskon() * 100)) + "%");
                    System.out.println("--------------------------------------------");
                    break; // Keluar dari loop jika pembayaran berhasil
                } else {
                    System.out.println("Silakan pilih channel pembayaran lain atau tambah saldo Anda.");
                }
            }
        }
        return pembayaran;
    }

    public MataUang pilihMataUang() {
        System.out.println("\n===========================");
        System.out.printf("%-5s Pilih Mata Uang %5s\n", "|", "|");
        System.out.println("===========================");
        System.out.printf("| %4s | %-16s |\n", "Kode", "Mata Uang");
        System.out.println("---------------------------");
        System.out.printf("| %-4d | %-16s |\n", 1, "USD");
        System.out.printf("| %-4d | %-16s |\n", 2, "JPY");
        System.out.printf("| %-4d | %-16s |\n", 3, "MYR");
        System.out.printf("| %-4d | %-16s |\n", 4, "EUR");
        System.out.println("===========================");

        int pilihan = 0;
        MataUang mataUang = null;
        boolean inputSalah = true;

        while (inputSalah) {
            while (true) {
                System.out.print("Masukkan kode mata uang pilihan Anda: ");
                try {
                    pilihan = input.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    invalidInput();
                    input.nextLine();
                }
            }

            switch (pilihan) {
                case 1:
                    mataUang = new USD();
                    inputSalah = false;
                    break;
                case 2:
                    mataUang = new JPY();
                    inputSalah = false;
                    break;
                case 3:
                    mataUang = new MYR();
                    inputSalah = false;
                    break;
                case 4:
                    mataUang = new EUR();
                    inputSalah = false;
                    break;
                default:
                    invalidInput();
            }
        }
        System.out.println("Berhasil memilih mata uang!");
        System.out.printf("Mata uang : %s\n\n", mataUang.getSimbol());
        return mataUang;
    }

    public double hitungNominalMataUang(double nominal, MataUang mataUang) {
        double totalDalamMataUang = nominal * mataUang.getNilaiTukar();
        return totalDalamMataUang; // Kembalikan nominal dalam mata uang yang dipilih
    }

    private void stopProgram() {
        System.out.println("\n=> Pesanan dibatalkan");
        System.out.println("=> Program berhenti...\n");
        System.exit(0);
    }

    private void invalidInput() {
        System.out.println("\n=> Pilihan tidak valid.");
        System.out.println("=> Mohon inputkan kode yang tersedia\n");
    }
}