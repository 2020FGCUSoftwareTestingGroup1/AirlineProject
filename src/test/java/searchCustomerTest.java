import static org.junit.jupiter.api.Assertions.*;

import database.Database;
import database.IDatabase;
import model.Customer;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import view.Main;
import view.searchCustomer;

class searchCustomerTest {

  FrameFixture window;

  @Mock
  IDatabase mockDatabase;

    @BeforeEach
    public void setup() {
      MockitoAnnotations.openMocks(this);

      Database.setDatabase(mockDatabase);

      Main frame = GuiActionRunner.execute(() -> new Main());
      window = new FrameFixture(frame);
      window.show();
    }

    @Test
    public void findValidCustomer() {
      Customer customer = new Customer("CS001", "Joseph", "Madre", "1",
          "AJ24", "123 First Street", "1990-01-01", "Male",
          200, null);

      Mockito.when(mockDatabase.getCustomer("CS001")).thenReturn(customer);

      window.menuItem("customerRootMenu").click();
      window.menuItem("searchCustomer").click();
      window.textBox("customerIdInput").setText("CS001");
      window.button("findButton").click();

      Mockito.verify(mockDatabase).getCustomer(Mockito.any());
    }

    @Test
    public void findNullCustomer() {
      Mockito.when(mockDatabase.getCustomer("CS001")).thenReturn(null);

      window.menuItem("customerRootMenu").click();
      window.menuItem("searchCustomer").click();
      window.textBox("customerIdInput").setText("CS001");
      window.button("findButton").click();
      window.dialog().optionPane().requireMessage("Record not Found");

      Mockito.verify(mockDatabase).getCustomer(Mockito.any());
    }

    @Test
    public void updateCustomer() {
      Customer customer = new Customer("CS001", "Joseph", "Madre", "1",
          "AJ24", "123 First Street", "1990-01-01", "Male",
          200, null);

      Mockito.when(mockDatabase.getCustomer("CS001")).thenReturn(customer);

      window.menuItem("customerRootMenu").click();
      window.menuItem("searchCustomer").click();
      window.textBox("customerIdInput").setText("CS001");
      window.button("findButton").click();

      window.textBox("firstNameInput").setText("Joseph");
      window.button("updateButton").click();

      Mockito.verify(mockDatabase).getCustomer(Mockito.any());

    }

    @Test
    public void updateCustomerGender() {
      Customer customer = new Customer("CS001", "Joseph", "Madre", "1",
          "AJ24", "123 First Street", "1990-01-01", "Male",
          200, null);

      Mockito.when(mockDatabase.getCustomer("CS001")).thenReturn(customer);

      window.menuItem("customerRootMenu").click();
      window.menuItem("searchCustomer").click();
      window.textBox("customerIdInput").setText("CS001");
      window.button("findButton").click();

      window.radioButton("femaleRadioButton").click();
      window.button("updateButton").click();

      Mockito.verify(mockDatabase).getCustomer(Mockito.any());
    }


    @AfterEach
    void cleanup() {
      window.cleanUp();
    }

}