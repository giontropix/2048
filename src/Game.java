import java.util.concurrent.ThreadLocalRandom;

public class Game {
    Box[][] grid = new Box[4][4];

    public Game(){
        fillGrid();
        insertBox();
        insertBox();
    }

    private void fillGrid(){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Box(0);
            }
        }
    }

    public void insertBox() {
        int row = ThreadLocalRandom.current().nextInt(0, 4);
        int column = ThreadLocalRandom.current().nextInt(0, 4);
        if (grid[row][column].getValue() == 0) {
            grid[row][column] = new Box(2);
        } else insertBox();
    }

    private boolean isIntoGridController(int i, int j){
        return (i < grid.length) && (j < grid[0].length) && ((i >= 0) && (j >= 0));
    }

    public void moveBoxes(int row, int column, int direction){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                switch (direction){
                    case 2: row = i + 1;
                            column = j;
                    break;
                    case 4: row = i;
                            column = j - 1;
                    break;
                    case 8: row = i - 1;
                            column = j;
                    break;
                    case 6: row = i;
                            column = j + 1;
                    break;
                }
                if ((grid[i][j].getValue() != 0) && (isIntoGridController(row, column))){
                    if (grid[row][column].getValue() == 0){
                        int value = grid[i][j].getValue();
                        grid[row][column].setValue(value);
                        grid[i][j].setValue(0);
                        switch (direction) {
                            case 2: moveBoxes(row + 1, column, 2);
                            break;
                            case 4: moveBoxes(row, column - 1, 4);
                            break;
                            case 8: moveBoxes(row - 1, column, 8);
                            break;
                            case 6: moveBoxes(row, column + 1, 6);
                            break;
                        }
                    }
                    if(grid[row][column].getValue() == grid[i][j].getValue()){
                        int value = grid[row][column].getValue();
                        grid[row][column].setValue(grid[i][j].getValue() + value);
                        grid[i][j].setValue(0);
                    }
                }
            }
        }
    }

    public boolean isWin(){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if ((grid[i][j].getValue() == 2048))
                    return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            result.append("[");
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getValue() == 0)
                    result.append("[").append(" ").append("]");
                if (grid[i][j].getValue() == 2)
                    result.append("[").append("\u001B[32m2\u001B[0m").append("]");
                if (grid[i][j].getValue() == 4)
                    result.append("[").append("\u001B[33m4\u001B[0m").append("]");
                if (grid[i][j].getValue() == 8)
                    result.append("[").append("\u001B[31m8\u001B[0m").append("]");
                if (grid[i][j].getValue() == 16)
                    result.append("[").append("\u001B[36m16\u001B[0m").append("]");
                if (grid[i][j].getValue() == 32)
                    result.append("[").append("\u001B[36m32\u001B[0m").append("]");
                if (grid[i][j].getValue() == 64)
                    result.append("[").append("\u001B[35m64\u001B[0m").append("]");
                if (grid[i][j].getValue() == 128)
                    result.append("[").append("\u001B[31m128\u001B[0m").append("]");
            }
            result.append("]\n");
        }
        return result.toString();
    }
}
