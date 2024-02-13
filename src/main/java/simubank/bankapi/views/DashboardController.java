package simubank.bankapi.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import simubank.bankapi.models.Account;
import simubank.bankapi.models.Card;
import simubank.bankapi.models.Tpe;
import simubank.bankapi.repositories.AccountRepository;
import simubank.bankapi.repositories.CardRepository;
import simubank.bankapi.repositories.TpeRepository;

import java.lang.reflect.Field;


@Controller
public class DashboardController {
    @Autowired
    AccountRepository accountsRepository;
    @Autowired
    CardRepository cardsRepository;
    @Autowired
    TpeRepository tpeRepository;

    @FXML
    private TableView<Account> accountsTable;

    @FXML
    private TableView<Card> cardsTable;

    @FXML
    private TableView<Tpe> TPEsTable;

    @FXML
    private SubScene addAccountModal;

    @FXML
    private void initialize() {
        loadAccounts();
        loadCards();
        loadTPEs();
    }

    public void loadAccounts() {
        ObservableList<Account> accountsList = toObservableList(accountsRepository.findAll());
        Field[] fields = Account.class.getDeclaredFields();
        for (Field field : fields) {
            TableColumn<Account, String> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            accountsTable.getColumns().add(column);
        }
        accountsTable.setItems(accountsList);
    }


    public void loadCards() {
        ObservableList<Card> cardList = toObservableList(cardsRepository.findAll());
        Field[] fields = Card.class.getDeclaredFields();
        for (Field field : fields) {
            TableColumn<Card, String> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            cardsTable.getColumns().add(column);
        }
        cardsTable.setItems(cardList);
    }

    public void loadTPEs() {
        ObservableList<Tpe> TPEList = toObservableList(tpeRepository.findAll());
        Field[] fields = Tpe.class.getDeclaredFields();
        for (Field field : fields) {
            TableColumn<Tpe, String> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            TPEsTable.getColumns().add(column);
        }
        TPEsTable.setItems(TPEList);
    }

    public static <T> ObservableList<T> toObservableList(Iterable<T> iterable) {
        ObservableList<T> observableList = FXCollections.observableArrayList();
        for (T element : iterable) {
            observableList.add(element);
        }
        return observableList;
    }

    public void showAddAccountDialog(ActionEvent actionEvent) {

    }
}
