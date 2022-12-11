public class Main {
    public static void main(String[] args) {
        double[][] tabl = new double[8][4];     //в первый аргумент массива нужно указывать сколько будет шагов
        tabl[0][2] = 0;
        tabl[0][3] = 1;                         //эти переменные не трогать
        double ps1 = 0.2;                       //здесь изменяем под себя
        double ps2 = 0.4;
        double ps3 = 0.1;
        double ps4 = 0.3;
        tabl[1][0] = ps4;
        tabl[2][0] = ps3;
        tabl[3][0] = ps2;
        tabl[4][0] = ps4;
        tabl[5][0] = ps2;
        tabl[6][0] = ps2;
        tabl[7][0] = ps1;

        double q1 = 0;                         //меняем под себя
        double q2 = 0.2;
        double q3 = 0.6;
        double q4 = 0.7;
        tabl[1][1] = q4;
        tabl[2][1] = q3;
        tabl[3][1] = q2;
        tabl[4][1] = q4;
        tabl[5][1] = q2;
        tabl[6][1] = q2;
        tabl[7][1] = q1;

        for (int i = 1; i < tabl.length; i++) {
            tabl[i][2] = fSik(tabl[i-1][2], tabl[i][1], tabl[i-1][3]);
            tabl[i][3] = gSik(tabl[i-1][3], tabl[i][0]);
        }

        System.out.println("Кодирование");
        for (int i = 0; i < tabl.length; i++) {
            for (int j = 0; j < tabl[i].length; j++) {
                System.out.print(round(tabl[i][j], 10) + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("L = [-log2 "+ tabl[tabl.length-1][3] +"] + 1  посчитать в калькуляторе");
        double x = tabl[7][2] + (tabl[7][3]/2);
        System.out.println("x = bin("+ round(x,10) +")");

        System.out.println();
        System.out.println("Декодирование");
        // Переводим bin обратно

        double[][] tabl2 = new double[8][4];
        for (int i = 1; i < tabl2.length; i++) {
            tabl2[i][0] = fSik(tabl[i-1][2], q1, tabl[i-1][3]);
            tabl2[i][1] = fSik(tabl[i-1][2], q2, tabl[i-1][3]);
            tabl2[i][2] = fSik(tabl[i-1][2], q3, tabl[i-1][3]);
            tabl2[i][3] = fSik(tabl[i-1][2], q4, tabl[i-1][3]);
        }

        for (int i = 0; i < tabl2.length; i++) {
            for (int j = 0; j < tabl2[i].length; j++) {
                System.out.print(round(tabl2[i][j], 10) + " ");
            }
            System.out.println();
        }
    }

    public static double round(double value, int places){
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
    public static double fSik(double lastfSik, double q, double lastgSik){
        return lastfSik + q * lastgSik;
    }

    public static double gSik(double lastgsik, double psi){
        return lastgsik * psi;
    }
}