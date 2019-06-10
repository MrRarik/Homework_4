/**
 * Level 1, lesson 4
 * @author Maluy Yaroslav
 * @version data 10.06.19
 */

import java.util.*;

class TicTacToe {

    final int SIZE = 3;
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    final char DOT_EMPTY = '.';
    char[][] map;
    Scanner sc;
    Random rand;

    public static void main(String[] args) {
        new TicTacToe().game();
    }

    TicTacToe() {
        map = new char[SIZE][SIZE];
        sc = new Scanner(System.in);
        rand = new Random();
    }

    void game() {
        initMap();
        while (true) {
            printMap();
            humanTurn();
            if (SIZE == 3) {
                if (checkWin(DOT_X)) {
                    System.out.println("YOU WON!");
                    break;
                }
            if (isMapFull()) {
                System.out.println("SORRY DRAW!");
                break;
            }
           aiTurn();
            if (checkWin(DOT_O)) {
                System.out.println("AI WON!");
                break;
            }
            if (isMapFull()) {
                System.out.println("SORRY DRAW!");
                break;
            }
        }
            if (SIZE == 5) {
                if (checkWin2(DOT_X)) {
                    System.out.println("YOU WON!");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("SORRY DRAW!");
                    break;
                }
                aiTurn();
                if (checkWin2(DOT_O)) {
                    System.out.println("AI WON!");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("SORRY DRAW!");
                    break;
                }
            }
        }
        printMap();
    }

    void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[j][i] + " ");
            }
            System.out.println();
        }
    }

    boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    void humanTurn() {
        int x, y;
        do {
            System.out.println("Enter X space Y coord [1..3]:");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[x][y] = DOT_X;
    }

    void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[x][y] = DOT_O;
    }
   //1 - Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов.
    boolean checkWin(char dot) {

        for (int i = 0; i < SIZE; i++) {
            // check horizontals
            if (map[0][i] == dot && map[1][i] == dot && map[2][i] == dot) return true;
            // check verticals
            if (map[i][0] == dot && +map[i][1] == dot && map[i][2] == dot) return true;
        }
        // check diagonals
        for (int j = 0, k = 2; j < SIZE - 1; j = j + 2, k = k - 2) {
            if (map[j][0] == dot && map[1][1] == dot && map[k][2] == dot) return true;
        }
        return false;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[x][y] == DOT_EMPTY;
    }
   //3. - * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4.
   // Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
    boolean checkWin2(char dot) {
        for (int i = 0; i < SIZE; i++) {
            // check horizontals
            if (map[0][i] == dot && map[1][i] == dot && map[2][i] == dot && map[3][i] == dot) return true;
            if (map[1][i] == dot && map[2][i] == dot && map[3][i] == dot && map[4][i] == dot) return true;
            // check verticals
            if (map[i][0] == dot && map[i][1] == dot && map[i][2] == dot && map[i][3] == dot) return true;
            if (map[i][1] == dot && map[i][2] == dot && map[i][3] == dot && map[i][4] == dot) return true;
        }
        //Я хотел написать таким способом но он не работает можете сказать почему.
        //for (int j = 0; j == 1; j++) {
            //if (map[0 + j][3] == dot && map[1 + j][2] == dot && map[2 + j][1] == dot && map[3 + j][0] == dot) return true;
            //if (map[1 - j][4] == dot && map[2 - j][3] == dot && map[3 - j][2] == dot && map[4 - j][1] == dot) return true;
            //if (map[0][1 - j] == dot && map[1][2 - j] == dot && map[2][3 - j] == dot && map[3][4 - j] == dot) return true;
            //if (map[1][0 + j] == dot && map[2][1 + j] == dot && map[3][2 + j] == dot && map[4][3 + j] == dot) return true;
        //}

        // check diagonals
        if (map[0][3] == dot && map[1][2] == dot && map[2][1] == dot && map[3][0] == dot) return true;
        if (map[1][4] == dot && map[2][3] == dot && map[3][2] == dot && map[4][1] == dot) return true;
        if (map[0][1] == dot && map[1][2] == dot && map[2][3] == dot && map[3][4] == dot) return true;
        if (map[1][0] == dot && map[2][1] == dot && map[3][2] == dot && map[4][3] == dot) return true;
        if (map[0][4] == dot && map[1][3] == dot && map[2][2] == dot && map[3][1] == dot) return true;
        if (map[1][3] == dot && map[2][2] == dot && map[3][1] == dot && map[4][0] == dot) return true;
        if (map[0][0] == dot && map[1][1] == dot && map[2][2] == dot && map[3][3] == dot) return true;
        if (map[1][1] == dot && map[2][2] == dot && map[3][3] == dot && map[4][4] == dot) return true;
        return false;
    }
}