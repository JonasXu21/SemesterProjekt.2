package presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private CheckBox ourCheckBox;
    @FXML
    private TextField name;
    @FXML
    private Button searchButton, historicButton;
    @FXML
    private Label myLabel;
    @FXML
    public void initialize(){// to disable the buttons and this method
        // is auto called when executing the code, so it is needed for apps that do something initially
        historicButton.setDisable(true);
        searchButton.setDisable(true);
    }

    @FXML
    public void onButtonClick(ActionEvent e){
        if(e.getSource().equals(searchButton)) {
            System.out.println("hello " + name.getText());
        }else{
            System.out.println("bye  " + name.getText());
        }
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try{
                    String s = Platform.isFxApplicationThread() ? "Ui thread" : " background thread";
                    System.out.println(" i am going to sleep on :" + s);
                    Thread.sleep(10000); // we put the background in sleep and then use runlater
                    //because it will crash if it tries to update after sleep for 10 seconds
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String s = Platform.isFxApplicationThread() ? "Ui thread" : " background thread";
                            System.out.println(" I am updating the label on the :" + s);
                            myLabel.setText(" we did something here  ");
                        }
                    });
                } catch (InterruptedException ex) {
                    // not important at the moment
                }
            }
        };
        new Thread(task).start();
        if(ourCheckBox.isSelected()){
            name.clear();
            searchButton.setDisable(true);
            historicButton.setDisable(true);
        }
    }
    @FXML
    public void handleKeyRelease(){
        String text = name.getText(); // a variable to store the input text from a user
        boolean disableButton = text.isEmpty() || text.trim().isEmpty(); // we use trim to remove
        // white space if it exists & isEmpty method we return a true or false if there is any input
        // now we are going to disable the button if now text provided
        searchButton.setDisable(disableButton);// true if there is text then the button we be enabled or disabled
        historicButton.setDisable(disableButton);}
    @FXML
    public void handleChange(){
        System.out.println("  this checkBox is :" + (ourCheckBox.isSelected() ? " select " : " not select"));
    }
}
