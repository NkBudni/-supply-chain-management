package com.example.supplychainsystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SuppyChain extends Application {

    public static final int height=600, width=700,upperLine=50;

    Pane bodyPane= new Pane();

    public  Login login= new Login();

    ProductDetails productDetails=new ProductDetails();

    boolean loggedIn=false;
    Label loginLable;

    Button orderButton;

    private GridPane hearBar(){
        GridPane gridPane= new GridPane();
        gridPane.setPrefSize(width,upperLine-5);
//        gridPane.setAlignment(Pos.CENTER);
//        gridPane.setHgap(5);

        TextField searchText= new TextField();
        searchText.setPromptText("Please search here");
        searchText.setMinWidth(250);
        loginLable = new Label("Please Login!");
        Button loginButton= new Button("Login");




        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(loggedIn==false){
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(loginPage());

                }




            }
        });

        Button searchButton = new Button("search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               if(loggedIn==true) {
                   bodyPane.getChildren().clear();
//                bodyPane.getChildren().add(productDetails.getAllProducts());
                   String search = searchText.getText();
                   bodyPane.getChildren().add(productDetails.getProductsByName(search));
               }
            }
        });
        gridPane.add(searchText,0,0);
        gridPane.add(searchButton,1,0);
        gridPane.add(loginLable,2,0);
        gridPane.add(loginButton,3,0);
//        gridPane.getChildren().remove(3,0);

        return gridPane;
    }


    private GridPane footerBar(){
        GridPane gridPane = new GridPane();
        orderButton = new Button("Buy Now");

        orderButton.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                productDetails.getSelectedProduct();
                if(loggedIn == false){
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(loginPage());
                }
                else{
                    Product product = productDetails.getSelectedProduct();
                    if(product != null){
                        String email = loginLable.getText();
                        email = email.substring(10);
                        System.out.println(email);
                        if(Order.placeSingleOrder(product,email)){
                            System.out.println("Order placed");
                        }
                        else {
                            System.out.println("Order Failed");
                        }
                    }
                    else{
                        System.out.println("Please select a product");
                    }
                }
            }
        }));

        gridPane.add(orderButton,0,0);
        gridPane.setMinWidth(width);
        gridPane.setTranslateY(height+80);
        return gridPane;
    }
     private GridPane loginPage() {
         Label emailLable = new Label("Email");
         Label passwordLable = new Label("Password");
         Label messegeLable= new Label("I am messege");

         TextField emailTextField = new TextField();
         emailTextField.setPromptText("Please enter email id");
         PasswordField passwordField = new PasswordField();
         passwordField.setPromptText("Please enter password");

         Button loginButton = new Button("Login");
         loginButton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                String email= emailTextField.getText();
                String password= passwordField.getText();
                if(login.customerLogin(email,password)){
                    loginLable.setText("Welcome : "+email);
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProducts());
                    loggedIn=true;



//                    messegeLable.setText("Login Successful");
                }
                else{
                    messegeLable.setText("Invalid User");
                }
//                messegeLable.setText("email - " + email + " && pass - "+password);
             }
         });

         GridPane gridPane= new GridPane();
         gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());

         gridPane.setVgap(10);
         gridPane.setHgap(10);

         gridPane.setAlignment(Pos.CENTER);

         //gridPane.setStyle("-fx-background-color: #C0C0C0;");



         gridPane.add(emailLable,0,0);
         gridPane.add(emailTextField,1,0);
         gridPane.add(passwordLable,0,1);
         gridPane.add(passwordField,1,1);
         gridPane.add(loginButton,0,3);
         gridPane.add(messegeLable,1,3);


         return gridPane;
     }
    Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width,height+upperLine+80);
        bodyPane.setTranslateY(upperLine);
        bodyPane.setPrefSize(width,height);
       // bodyPane.setStyle("-fx-background-color: #C0C9C0;");
        bodyPane.getChildren().addAll(productDetails.getAllProducts());
        root.getChildren().addAll(
                hearBar(),
                bodyPane,footerBar());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Suppy Chain System!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}