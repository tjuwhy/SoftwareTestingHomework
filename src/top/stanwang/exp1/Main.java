package top.stanwang.exp1;

public class Main {

    public static boolean takeOut(int x) {
        int m[] = {50, 20, 10, 5, 1};
        int n[] = {1, 1, 1, 2, 3};
        int i = 0;
        while (i < m.length) {
            if (x == 0) {
                return true;
            }
            if (m[i] > x) {
                i++;
            } else {
                x -= m[i];
                n[i]--;
                if (n[i] == 0) {
                    i++;
                }
            }
        }
        return false;
    }
}
