public class ShipManager {
    public static void main(String[] args) {
        UserInterface ui;
        try {
            ui = new UserInterface();
            ui.menu();
        }
        catch(Exception e) {
            System.out.println("Oh no! Something went wrong: " + e.getMessage());
        }
    }
}