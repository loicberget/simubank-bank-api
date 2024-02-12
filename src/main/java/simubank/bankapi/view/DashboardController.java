package simubank.bankapi.view;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import simubank.bankapi.models.Account;
import simubank.bankapi.models.Card;
import simubank.bankapi.models.Tpe;
import simubank.bankapi.repositories.AccountRepository;
import simubank.bankapi.repositories.CardRepository;
import simubank.bankapi.repositories.TpeRepository;

import java.lang.reflect.Field;
import java.util.Arrays;


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
    private TableView<Tpe> tpeTable;

    @FXML
    private void initialize() {
        loadAccounts();
    }

    public void loadAccounts() {
        for (int i = 0; i < 10; i++) {
            accountsRepository.save(new Account("Account " + i, Account.Type.CREDIT, 100));
        }
        ObservableList<Account> accountsList = toObservableList(accountsRepository.findAll());
        Field[] fields = Account.class.getDeclaredFields();
        for (Field field : fields) {
            TableColumn<Account, String> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            accountsTable.getColumns().add(column);
        }
        accountsTable.setItems(accountsList);
    }

    public static <T> ObservableList<T> toObservableList(Iterable<T> iterable) {
        ObservableList<T> observableList = FXCollections.observableArrayList();
        for (T element : iterable) {
            observableList.add(element);
        }
        return observableList;
    }
}
