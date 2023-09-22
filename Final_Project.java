import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Final_Project extends Application {
  
    private String playerName; 
    private Label playerLabel; 
    private Label objectLabel1; 
    private Label objectLabel2;
    private Label opLabel; 
    private Button answerButton1;
    private Button answerButton2; 
    private Button answerButton3;
    private Button answerButton4; 
    @Override
    public void start(Stage primaryStage) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("");
        inputDialog.setContentText("Please enter player's name:");
        
        Optional<String> response = inputDialog.showAndWait();
        if (response.isPresent())
        {
            playerName = response.get();
        }
      
        
        playerLabel = new Label("Player's name: "+playerName);
        objectLabel1 = new Label("10");
        objectLabel2 = new Label("2");
        opLabel = new Label("+");
        Button answerButton1 = new Button();
        Button answerButton2 = new Button();
        Button answerButton3 = new Button();
        Button answerButton4 = new Button();
        answerButton1.setText("7");
        answerButton2.setText("12");
        answerButton3.setText("11");
        answerButton4.setText("9");
        answerButton1.setOnAction(new EventHandler<ActionEvent>() {
          
            @Override
            public void handle(ActionEvent event) {
                String op1 = objectLabel1.getText();
                String op2 = objectLabel2.getText();
                try{
                     int num1 = Integer.parseInt(op1);
                     int num2 = Integer.parseInt(op2);
                     int result = num1 + num2;
                     int answer = Integer.parseInt(answerButton1.getText());
                     if(result == answer)
                         System.out.println("Right answer");
                     else
                         System.out.println("Wrong answer");
                }
                catch(NumberFormatException e)
                {
                   System.out.println("Invalid input");
                }              
            }
        });
      
        answerButton2.setOnAction(new EventHandler<ActionEvent>() {
          
            @Override
            public void handle(ActionEvent event) {
                String op1 = objectLabel1.getText();
                String op2 = objectLabel2.getText();
                try{
                     int num1 = Integer.parseInt(op1);
                     int num2 = Integer.parseInt(op2);
                     int result = num1 + num2;
                     int answer = Integer.parseInt(answerButton2.getText());
                     if(result == answer)
                         System.out.println("Right answer");
                     else
                         System.out.println("Wrong answer");
                }
                catch(NumberFormatException e)
                {
                   System.out.println("Invalid input");
                }
            }
        });
      
        answerButton3.setOnAction(new EventHandler<ActionEvent>() {
          
            @Override
            public void handle(ActionEvent event) {
                String op1 = objectLabel1.getText();
                String op2 = objectLabel2.getText();
                try{
                     int num1 = Integer.parseInt(op1);
                     int num2 = Integer.parseInt(op2);
                     int result = num1 + num2;
                     int answer = Integer.parseInt(answerButton3.getText());
                     if(result == answer)
                         System.out.println("Right answer");
                     else
                         System.out.println("Wrong answer");
                }
                catch(NumberFormatException e)
                {
                   System.out.println("Invalid input");
                }
            }
        });
      
        answerButton4.setOnAction(new EventHandler<ActionEvent>() {
          
            @Override
            public void handle(ActionEvent event) {
                String op1 = objectLabel1.getText();
                String op2 = objectLabel2.getText();
                try{
                     int num1 = Integer.parseInt(op1);
                     int num2 = Integer.parseInt(op2);
                     int result = num1 + num2;
                     int answer = Integer.parseInt(answerButton4.getText());
                     if(result == answer)
                         System.out.println("Right answer");
                     else
                         System.out.println("Wrong answer");
                }
                catch(NumberFormatException e)
                {
                   System.out.println("Invalid input");
                }
            }
        });
      
        GridPane root = new GridPane();
        
        root.setVgap(5);
        root.setHgap(5);
        root.add(playerLabel,0,0);
        root.add(objectLabel1,0,1);
        root.add(opLabel,1,1);
        root.add(objectLabel2,2,1);
        root.add(answerButton1,0,2);
        root.add(answerButton2,1,2);
        root.add(answerButton3,2,2);
        root.add(answerButton4,3,2);
        Scene scene = new Scene(root, 300, 250);
      
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
  
}