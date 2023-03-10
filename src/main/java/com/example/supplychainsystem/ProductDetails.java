package com.example.supplychainsystem;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {

  public TableView<Product> productTable;//= new TableView<>();
//    Product product= new Product();

    public Pane getAllProducts(){
        TableColumn id= new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name= new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price= new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        final ObservableList<Product> data= FXCollections.observableArrayList();
        data.add(new Product(1,"lenovo",8383));
        data.add(new Product(2,"Mac",89383));



        ObservableList<Product>Items = Product.getAllProduct();

        //String searchName;
        //ObservableList<Product>Items = Product.getProductByName(searchName);

        productTable= new TableView<>();

        productTable.setItems(Items);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SuppyChain.width,SuppyChain.height);

        Pane tablePane= new Pane();
        tablePane.setMinSize(SuppyChain.width,SuppyChain.height);
        tablePane.getChildren().add(productTable);
        return tablePane;

    }

    public  Pane getProductsByName(String searchName){
        TableColumn id= new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name= new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price= new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));



        ObservableList<Product>items = Product.getProductByName(searchName);
        productTable= new TableView<>();

        productTable.setItems(items);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.getColumns().addAll(id,name,price);
//        productTable.setMinSize(SuppyChain.width,SuppyChain.height);

        Pane tablePane= new Pane();
//        tablePane.setMinSize(SuppyChain.width,SuppyChain.height);
        tablePane.getChildren().add(productTable);
        return tablePane;
    }

    public Product getSelectedProduct(){
        if(productTable==null){
            System.out.println("Table obeject not found");
            return null;
        }
        if(productTable.getSelectionModel().getSelectedIndex()!=-1){
            Product selectedProduct=productTable.getSelectionModel().getSelectedItem();
            System.out.println(selectedProduct.getId()+" "+
                    selectedProduct.getName()+" "+
                    selectedProduct.getPrice());
            return selectedProduct;
        }
        else {
            System.out.println(("Nothing selected"));
            return null;
        }
    }

}
