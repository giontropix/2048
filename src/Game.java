import java.util.concurrent.ThreadLocalRandom;

public class Game {
    Box[][] grid = new Box[4][4];
    private int point;

    public Game(){
        fillGrid();
        insertBox();
        insertBox();
    }

    public int getPoint() {
        return this.point;
    }

    private void fillGrid(){
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                this.grid[i][j] = new Box(0, false);
            }
        }
    }

    public void insertBox() {
        int row = ThreadLocalRandom.current().nextInt(0, 4);
        int column = ThreadLocalRandom.current().nextInt(0, 4);
        if (this.grid[row][column].getValue() == 0) {
            this.grid[row][column] = new Box(2, false);
        } else insertBox();
    }

    private boolean isIntoGrid(int i, int j){
        return (i < this.grid.length) && (j < this.grid[0].length) && ((i >= 0) && (j >= 0));
    }

    public void resetAlreadyAdded(){
        for (Box[] boxes : this.grid) {
            for (Box box : boxes) {
                if (box.isAlreadyAdded())
                    box.setAlreadyAdded(false);
            }
        }
    }

    public void moveBoxes(int rowForDirection, int columnForDirection, int direction){
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                switch (direction){
                    case 2: rowForDirection = i + 1;
                            columnForDirection = j;
                    break;
                    case 4: rowForDirection = i;
                            columnForDirection = j - 1;
                    break;
                    case 8: rowForDirection = i - 1;
                            columnForDirection = j;
                    break;
                    case 6: rowForDirection = i;
                            columnForDirection = j + 1;
                    break;
                }
                if ((this.grid[i][j].getValue() != 0) && (isIntoGrid(rowForDirection, columnForDirection))){
                    if (this.grid[rowForDirection][columnForDirection].getValue() == 0){
                        int value = this.grid[i][j].getValue();
                        this.grid[rowForDirection][columnForDirection].setValue(value);
                        this.grid[i][j].setValue(0);
                        switch (direction) {
                            case 2: moveBoxes(rowForDirection + 1, columnForDirection, 2);
                            break;
                            case 4: moveBoxes(rowForDirection, columnForDirection - 1, 4);
                            break;
                            case 8: moveBoxes(rowForDirection - 1, columnForDirection, 8);
                            break;
                            case 6: moveBoxes(rowForDirection, columnForDirection + 1, 6);
                            break;
                        }
                    }
                    if((grid[rowForDirection][columnForDirection].getValue() == this.grid[i][j].getValue()) && (!this.grid[i][j].isAlreadyAdded() && !this.grid[rowForDirection][columnForDirection].isAlreadyAdded())){
                        int value = this.grid[rowForDirection][columnForDirection].getValue();
                        this.grid[rowForDirection][columnForDirection].setValue(this.grid[i][j].getValue() + value);
                        this.grid[rowForDirection][columnForDirection].setAlreadyAdded(true);
                        this.point += this.grid[i][j].getValue() + value;
                        this.grid[i][j].setValue(0);
                    }
                }
            }
        }
    }

    public boolean isWin(){
        for (Box[] boxes : this.grid) {
            for (Box box : boxes) {
                if ((box.getValue() == 2048))
                    return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Box[] boxes : this.grid) {
            result.append("[");
            for (Box box : boxes) {
                if (box.getValue() == 0)
                    result.append("[").append(" ").append("]");
                if (box.getValue() == 2)
                    result.append("[").append("\u001B[32m2\u001B[0m").append("]");
                if (box.getValue() == 4)
                    result.append("[").append("\u001B[33m4\u001B[0m").append("]");
                if (box.getValue() == 8)
                    result.append("[").append("\u001B[31m8\u001B[0m").append("]");
                if (box.getValue() == 16)
                    result.append("[").append("\u001B[36m16\u001B[0m").append("]");
                if (box.getValue() == 32)
                    result.append("[").append("\u001B[36m32\u001B[0m").append("]");
                if (box.getValue() == 64)
                    result.append("[").append("\u001B[35m64\u001B[0m").append("]");
                if (box.getValue() == 128)
                    result.append("[").append("\u001B[31m128\u001B[0m").append("]");
            }
            result.append("]\n");
        }
        return result.toString();
    }
}
