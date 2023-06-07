
// package 2048;
import java.util.Random;
import java.util.Scanner;


// todo

public class Game2048 {
    public static void main(String[] args) {
        clearScreen();
        Table game = new Table();
        Scanner input = new Scanner(System.in);
        String userInput = "";

        while (userInput != "q" && userInput != "Q") {
            game.update();
            game.show();
            userInput = input.next();
            game.input(userInput);
            clearScreen();
        }

        input.close();
        input = null;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

enum Display {
    Normal(""), Null("  .");

    private String display;

    Display(String displayString) {
        this.display = displayString;
    }

    public String getDisplay() {
        return display;
    }
}

class Table {
    Random random = new Random();
    Cell[][] table = new Cell[4][4];

    public Table() {
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                this.table[row][col] = new Cell(row, col);
            }
        }
    }

    public void UP() {
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (this.table[row][col].amount != 0) {
                    if (row != 0) {
                        for (int move = row-1; move >= 0; move--) {
                            if (this.table[move][col].amount == 0 ) {
                                this.table[move][col].amount = this.table[row][col].amount;
                                this.table[row][col].amount = 0;
                                row = move;
                            } else if (this.table[move][col].amount == this.table[row][col].amount ) {
                                this.table[move][col].amount = this.table[move][col].amount*2;
                                this.table[row][col].amount = 0;
                                row = move;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void DOWN() {
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (this.table[row][col].amount != 0) {
                    if (row != 3) {
                        for (int move = row+1; move <= 3; move++) {
                            if (this.table[move][col].amount == 0 ) {
                                this.table[move][col].amount = this.table[row][col].amount;
                                this.table[row][col].amount = 0;
                                row = move;
                            } else if (this.table[move][col].amount == this.table[row][col].amount ) {
                                this.table[move][col].amount = this.table[move][col].amount*2;
                                this.table[row][col].amount = 0;
                                row = move;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void Left() {
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (this.table[row][col].amount != 0) {
                    if (col != 0) {
                        for (int move = col-1; move >= 0; move--) {
                            if (this.table[row][move].amount == 0 ) {
                                this.table[row][move].amount = this.table[row][col].amount;
                                this.table[row][col].amount = 0;
                                col = move;
                            } else if (this.table[row][move].amount == this.table[row][col].amount ) {
                                this.table[row][move].amount = this.table[row][move].amount*2;
                                this.table[row][col].amount = 0;
                                col = move;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void Right() {
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (this.table[row][col].amount != 0) {
                    if (col != 3) {
                        for (int move = col+1; move <= 3; move++) {
                            if (this.table[row][move].amount == 0 ) {
                                this.table[row][move].amount = this.table[row][col].amount;
                                this.table[row][col].amount = 0;
                                col = move;
                            } else if (this.table[row][move].amount == this.table[row][col].amount ) {
                                this.table[row][move].amount = this.table[row][move].amount*2;
                                this.table[row][col].amount = 0;
                                col = move;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void input(String input) {
        switch (input) {
            case "w":
                this.UP();
                break;
            case "a":
                this.Left();
                break;
            case "s":
                this.DOWN();

                break;
            case "d":
                this.Right();
                break;

            default:
                break;
        }
    }

    // public int[][] getNull() {
    // int[][] result = new int[1][2];
    // int idx = 0;
    // for (int row = 0; row < table.length; row++) {
    // for (int col = 0; col < table[row].length; col++) {
    // if (table[row][col].amount == 0) {
    // result[idx][0] = row;
    // result[idx][1] = col;
    // idx++;
    // }
    // }
    // }
    // return result;
    // }
    public int[][] getNull() {
        // Assuming table is defined and initialized appropriately

        // Determine the number of null elements
        int count = 0;
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (table[row][col].amount == 0) {
                    count++;
                }
            }
        }

        // Create the result array with the correct size
        int[][] result = new int[count][2];

        // Fill the result array with the row and column indices of null elements
        int idx = 0;
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (table[row][col].amount == 0) {
                    result[idx][0] = row;
                    result[idx][1] = col;
                    idx++;
                }
            }
        }

        return result;
    }

    public void update() {
        int[][] src = this.getNull();
        int[] random_value = src[this.random.nextInt(src.length)];
        this.table[random_value[0]][random_value[1]].amount = 2;
    }

    public void show() {
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (table[row][col].amount == 0) {
                    System.out.print(Display.Null.getDisplay() + " ");
                } else {
                    System.out.printf("%3d ", table[row][col].amount);
                }
            }
            System.out.println();
        }

        System.out.println();
    }
}

class Cell {
    int amount = 0;
    int[] position;

    public Cell(int row, int col) {
        position = new int[] { row, col };
    }

    public Cell(int amount) {
        this.amount = amount;
    }

    public void update() {

    }
}
