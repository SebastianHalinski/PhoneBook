package View;

import java.util.Scanner;

public class PhoneBookView {
    private Scanner reader = new Scanner(System.in);

    public String getInput(String text){
        System.out.println(text);
        String input = reader.nextLine();
        return input;
    }
}
