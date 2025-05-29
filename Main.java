import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Data pemain (tinggi, berat) Tim A
        ArrayList<int[]> timA = new ArrayList<>();
        timA.add(new int[]{168, 50});
        timA.add(new int[]{170, 60});
        timA.add(new int[]{165, 56});
        timA.add(new int[]{168, 55});
        timA.add(new int[]{172, 60});
        timA.add(new int[]{170, 70});
        timA.add(new int[]{169, 66});
        timA.add(new int[]{165, 56});
        timA.add(new int[]{171, 72});
        timA.add(new int[]{166, 56});

        // Data pemain (tinggi, berat) Tim B
        ArrayList<int[]> timB = new ArrayList<>();
        timB.add(new int[]{170, 66});
        timB.add(new int[]{167, 60});
        timB.add(new int[]{165, 59});
        timB.add(new int[]{166, 58});
        timB.add(new int[]{168, 58});
        timB.add(new int[]{175, 71});
        timB.add(new int[]{172, 68});
        timB.add(new int[]{171, 68});
        timB.add(new int[]{168, 65});
        timB.add(new int[]{169, 60});

        // a. Sorting tinggi badan ASC & DESC
        System.out.println("Tinggi ASC (Gabungan):");
        printSorted(timA, timB, 0, true);

        System.out.println("Tinggi DESC (Gabungan):");
        printSorted(timA, timB, 0, false);

        // b. Sorting berat badan ASC & DESC
        System.out.println("Berat ASC (Gabungan):");
        printSorted(timA, timB, 1, true);

        System.out.println("Berat DESC (Gabungan):");
        printSorted(timA, timB, 1, false);

        // c. Cari nilai max/min tinggi & berat tiap tim
        System.out.println("Max/Min Tim A:");
        cariMaxMin(timA);

        System.out.println("Max/Min Tim B:");
        cariMaxMin(timB);

        // d. Copy tim B ke tim C
        System.out.println("Copy Tim B ke Tim C:");
        ArrayList<int[]> timC = new ArrayList<>();
        for (int[] pemain : timB) {
            timC.add(new int[]{pemain[0], pemain[1]});
        }
        printTim(timC);

        // 2a. Sudah pakai ArrayList untuk simpan data tim

        // 2b. Binary search tinggi 168 & 160 di Tim B
        System.out.println("Binary Search Tinggi di Tim B:");
        cariJumlah(timB, 0, 168);
        cariJumlah(timB, 0, 160);

        // 2c. Binary search berat 56 & 53 di Tim A
        System.out.println("Binary Search Berat di Tim A:");
        cariJumlah(timA, 1, 56);
        cariJumlah(timA, 1, 53);

        // 2d. Cek kesamaan tinggi/berat antar Tim A dan B
        System.out.println("Cek Tinggi/Berat Sama antara Tim A dan Tim B:");
        cekSama(timA, timB);
    }

    // Sorting dan cetak gabungan tim A dan B berdasarkan kolom (0=tinggi, 1=berat)
    public static void printSorted(ArrayList<int[]> timA, ArrayList<int[]> timB, int kolom, boolean naik) {
        ArrayList<int[]> gabung = new ArrayList<>();
        for (int[] p : timA) gabung.add(p);
        for (int[] p : timB) gabung.add(p);

        // Bubble sort sederhana
        for (int i = 0; i < gabung.size() - 1; i++) {
            for (int j = i + 1; j < gabung.size(); j++) {
                if ((naik && gabung.get(i)[kolom] > gabung.get(j)[kolom]) ||
                    (!naik && gabung.get(i)[kolom] < gabung.get(j)[kolom])) {
                    int[] temp = gabung.get(i);
                    gabung.set(i, gabung.get(j));
                    gabung.set(j, temp);
                }
            }
        }

        for (int[] p : gabung) {
            System.out.println("Tinggi: " + p[0] + ", Berat: " + p[1]);
        }
    }

    // Cari max/min dari tim
    public static void cariMaxMin(ArrayList<int[]> tim) {
        int maxT = tim.get(0)[0], minT = tim.get(0)[0];
        int maxB = tim.get(0)[1], minB = tim.get(0)[1];
        for (int[] p : tim) {
            if (p[0] > maxT) maxT = p[0];
            if (p[0] < minT) minT = p[0];
            if (p[1] > maxB) maxB = p[1];
            if (p[1] < minB) minB = p[1];
        }
        System.out.println("Tinggi -> Max: " + maxT + ", Min: " + minT);
        System.out.println("Berat  -> Max: " + maxB + ", Min: " + minB);
    }

    // Print isi tim
    public static void printTim(ArrayList<int[]> tim) {
        for (int[] p : tim) {
            System.out.println("Tinggi: " + p[0] + ", Berat: " + p[1]);
        }
    }

    // Binary search manual hitung jumlah sama (tidak pakai Collections)
    public static void cariJumlah(ArrayList<int[]> tim, int kolom, int cari) {
        int jumlah = 0;
        for (int[] p : tim) {
            if (p[kolom] == cari) jumlah++;
        }
        if (jumlah > 0) {
            System.out.println(cari + " ditemukan sebanyak " + jumlah + " kali");
        } else {
            System.out.println(cari + " tidak ditemukan");
        }
    }

    // Cek apakah ada tinggi atau berat yang sama antara dua tim
    public static void cekSama(ArrayList<int[]> A, ArrayList<int[]> B) {
        boolean ada = false;
        for (int[] a : A) {
            for (int[] b : B) {
                if (a[0] == b[0]) {
                    System.out.println("Tinggi sama: " + a[0]);
                    ada = true;
                }
                if (a[1] == b[1]) {
                    System.out.println("Berat sama: " + a[1]);
                    ada = true;
                }
            }
        }
        if (!ada) System.out.println("Tidak ada tinggi atau berat yang sama.");
    }
}
