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
import simubank.bankapi.repositories.AccountRepository;

import java.util.Arrays;


@Controller
public class DashboardController {
    @Autowired
    AccountRepository accountsRepository;

    @FXML
    private TableView<Account> accountsTable;

    @FXML
    private void initialize() {
        loadAccounts();
    }

    public void loadAccounts() {
        for(int i = 0; i < 10; i++) {
            accountsRepository.save(new Account("Account " + i, Account.Type.CREDIT, 100));
        }
        ObservableList<Account> accountsList = toObservableList(accountsRepository.findAll());
        TableColumn<Account, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        accountsList.addAll();
        accountsTable.getColumns().add(nameCol);
        accountsTable.setItems(accountsList);
        System.out.println(accountsTable.getColumns());
    }

    public static <T> ObservableList<T> toObservableList(Iterable<T> iterable) {
        ObservableList<T> observableList = FXCollections.observableArrayList();
        for (T element : iterable) {
            observableList.add(element);
        }
        return observableList;
    }
}
