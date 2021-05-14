import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jdk.internal.org.objectweb.asm.commons.SerialVersionUIDAdder;


public class ElectronicStoreApp extends Application {
    private ElectronicStore model;
    private ElectronicStoreView view;
    private Stage stage;

    public void start(Stage primaryStage) {
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);
        stage = primaryStage;

        view.getStoreStockListView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update();
            }
        });

        view.getAddToCart().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                handleAddToCart();
            }
        });

        view.getRemoveFromCart().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                handleRemoveFromCart();
            }
        });

        view.getCompleteSale().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                handleCompleteSale();
            }
        });

        view.getCurrentCartListView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                view.update();
            }
        });

        view.getResetScore().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                handleResetStage();
            }
        });

        primaryStage.setTitle("Electronic Store Application - Watts Up Electronics");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, 800, 400));
        primaryStage.show();
        view.update();


    }



    public static void main(String[] args) {
        launch(args);
    }

    public void handleAddToCart() {
        model.addToCart(view.getStoreStockListView().getSelectionModel().getSelectedItem());
        view.update();
    }

    public void handleRemoveFromCart() {
        model.removeFromCart(view.getCurrentCartListView().getSelectionModel().getSelectedItem());
        view.update();
    }

    public void handleCompleteSale() {
        model.checkout();
        view.update();

    }

    public void handleResetStage() {
        start(stage);
    }


}
