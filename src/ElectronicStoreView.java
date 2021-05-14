import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.ListView;
import javafx.scene.control.*;

public class ElectronicStoreView extends Pane {

    //Initialize everything
    private TextField salesField, revenueField, moneyPerSaleField;
    private Label storeSummary, storeStock, currentCart, numberOfSales, revenue, moneyPerSale, mostPopularItems;
    private ListView<String> mostPopularItemsListView, storeStockListView, currentCartListView;
    private Button resetScore, addToCart, removeFromCart, completeSale;
    ElectronicStore model;

    public ElectronicStoreView(ElectronicStore initModel) {
        model = initModel;

        //Buttons
        resetScore = new Button("Reset Score");
        resetScore.relocate(20, 355);
        resetScore.setPrefSize(125, 40);

        addToCart = new Button("Add to Cart");
        addToCart.relocate(250, 355);
        addToCart.setPrefSize(125, 40);

        removeFromCart = new Button("Remove from Cart");
        removeFromCart.relocate(500, 355);
        removeFromCart.setPrefSize(125, 40);

        completeSale = new Button("Complete Sale");
        completeSale.relocate(650, 355);
        completeSale.setPrefSize(125, 40);

        //ListViews
        mostPopularItemsListView = new ListView<>();
        mostPopularItemsListView.relocate(10, 200);
        mostPopularItemsListView.setPrefSize(150, 150);

        storeStockListView = new ListView<>();
        storeStockListView.relocate(175, 50);
        storeStockListView.setPrefSize(300, 300);

        currentCartListView = new ListView<>();
        currentCartListView.relocate(485, 50);
        currentCartListView.setPrefSize(300, 300);

        //TextFields
        salesField = new TextField();
        salesField.relocate(70, 50);
        salesField.setPrefSize(70, 20);

        revenueField = new TextField();
        revenueField.relocate(70, 80);
        revenueField.setPrefSize(70, 20);

        moneyPerSaleField = new TextField();
        moneyPerSaleField.relocate(70, 110);
        moneyPerSaleField.setPrefSize(70, 20);

        //Labels
        storeSummary = new Label("Store Summary: ");
        storeSummary.relocate(50,10);
        storeSummary.setPrefSize(100, 30);

        storeStock = new Label("Store Stock:");
        storeStock.relocate(280, 10);
        storeStock.setPrefSize(100, 30);

        currentCart = new Label();
        currentCart.relocate(580,10);
        currentCart.setPrefSize(150, 30);

        numberOfSales = new Label("# Sales:");
        numberOfSales.relocate(20, 45);
        numberOfSales.setPrefSize(100, 30);

        revenue = new Label("Revenue:");
        revenue.relocate(12, 75);
        revenue.setPrefSize(100, 30);

        moneyPerSale = new Label("$ / Sale:");
        moneyPerSale.relocate(18, 105);
        moneyPerSale.setPrefSize(100, 30);

        mostPopularItems = new Label("Most Popular Items:");
        mostPopularItems.relocate(30, 160);
        mostPopularItems.setPrefSize(130, 30);

        //Add all the components to the window
        getChildren().addAll(storeSummary, storeStock, currentCart, numberOfSales, revenue, moneyPerSale, mostPopularItems); //Labels
        getChildren().addAll(salesField, revenueField, moneyPerSaleField); //TextFields
        getChildren().addAll(mostPopularItemsListView, storeStockListView, currentCartListView); //ListViews
        getChildren().addAll(resetScore, addToCart, removeFromCart, completeSale); //Buttons
    }

    //Get Buttons
    public Button getResetScore() {
        return resetScore;
    }
    public Button getAddToCart() {
        return addToCart;
    }
    public Button getRemoveFromCart() {
        return removeFromCart;
    }
    public Button getCompleteSale() {
        return completeSale;
    }

    //Get ListViews
    public ListView<String> getCurrentCartListView() {
        return currentCartListView;
    }
    public ListView<String> getStoreStockListView() {
        return storeStockListView;
    }

    public void update() {
        currentCartListView.setItems(FXCollections.observableArrayList(model.getCart()));
        storeStockListView.setItems(FXCollections.observableArrayList(model.getStock()));
        mostPopularItemsListView.setItems(FXCollections.observableArrayList(model.getMostPopularItems()));

        salesField.setText(String.valueOf(model.getNumberOfSales()));
        revenueField.setText(String.valueOf(model.getRevenue()));
        moneyPerSaleField.setText(String.valueOf(model.getDollarsPerSale()));

        currentCart.setText("Current Cart: " + "($" + model.getCartValue() + ")");

        if (currentCartListView.getSelectionModel().getSelectedIndex() == -1) {
            getRemoveFromCart().setDisable(true);
        }
        else {
            getRemoveFromCart().setDisable(false);
        }

        if (storeStockListView.getSelectionModel().getSelectedIndex() == -1) {
            getAddToCart().setDisable(true);
        }
        else {
            getAddToCart().setDisable(false);
        }

        if (currentCartListView.getItems().isEmpty()) {
            completeSale.setDisable(true);
        }
        else {
            completeSale.setDisable(false);
        }

    }
}
