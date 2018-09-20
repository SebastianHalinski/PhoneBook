package Controller;

import DAO.DBConnection;
import View.PhoneBookView;

public class PhoneBookController {
    private boolean isRunning = true;
    private PhoneBookView view = new PhoneBookView();
    private DBConnection dbdao = new DBConnection();


    public void startPhoneBookPanel(){
        while(isRunning){
            String choice = view.getInput("Choose yur option");
            switch (choice){
                case "0":
                    dbdao.closeConnection();
                    isRunning = false;
                    break;
                case "1":
                    //addPerson();
                    break;
                case "2":
                    //editPerson();
                    break;
                case "3":
                    //deetePerson();
                    break;
                default:
                    break;
            }
        }
    }
}
