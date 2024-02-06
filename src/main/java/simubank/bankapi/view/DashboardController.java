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
    @FXML
    private TableView<Account> accountsTable;

    @FXML
    private void initialize() {
        loadAccounts();
    }

    public void loadAccounts() {
        ObservableList<Account> accounts = FXCollections.observableArrayList();
        TableColumn<Account, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        for (int i = 0; i < 100; i++) {
            accounts.add(new Account("Eric", Account.Type.CREDIT, 100.0));
        }
        accountsTable.getColumns().add(nameCol);
        accountsTable.setItems(accounts);
        System.out.println(accountsTable.getColumns());
    }
}
