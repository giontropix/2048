import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(game.toString());
        do{
            System.out.println("Seleziona la direzione");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            switch (choice){
                case 2: game.moveBoxes(0, 0, 2);
                    break;
                case 4: game.moveBoxes(0, 0, 4);
                    break;
                case 8: game.moveBoxes(0, 0, 8);
                    break;
                case 6: game.moveBoxes(0, 0, 6);
                    break;
            }
            System.out.println("Punti totali: " + game.getPoint());
            game.insertBox();
            System.out.println(game.toString());
        }while (!game.isWin());
    }
}
