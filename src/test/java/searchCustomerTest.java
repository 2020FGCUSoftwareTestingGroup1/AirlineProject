import static org.junit.jupiter.api.Assertions.*;

import database.Database;
import database.IDatabase;
import model.Customer;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
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
          "AJ24", "123 First Street", "1/1/1990", "Male",
          200, null);

      Mockito.when(mockDatabase.getCustomer("CS001")).thenReturn(customer);

      window.menuItem("customerRootMenu").click();
      window.menuItem("searchCustomer").click();
      window.textBox("customerIdInput").setText("CS001");
      window.button("findButton").click();

      Mockito.verify(mockDatabase).getCustomer(Mockito.any());
    }

    @AfterEach
    void cleanup() {
      window.cleanUp();
    }

}