package integration.usercreation;

import database.Database;
import database.IDatabase;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import view.Main;

public class AddCustomerIntegrationTest {
    private FrameFixture window;

    @Mock
    IDatabase mockDatabase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        Database.setDatabase(mockDatabase);

        Main frame = GuiActionRunner.execute(() -> new Main());
        window = new FrameFixture(frame);
        window.show();
    }

    @AfterEach
    void teardown() {
        window.cleanUp();
    }

    @Test
    void displaysNextCustomerIdOnOpen() {
        Mockito.when(mockDatabase.getNextCustomerId()).thenReturn("CS001");

        window.menuItem("customerRootMenu").click();
        window.menuItem("addCustomerMenuItem").click();
        window.label("nextCustomerIdLabel").requireText("CS001");
    }

    @Test
    void errorShownWhenMissingField() {
        Mockito.when(mockDatabase.getNextCustomerId()).thenReturn("CS001");
        window.menuItem("customerRootMenu").click();
        window.menuItem("addCustomerMenuItem").click();
        window.button("addCustomerAddButton").click();
        window.dialog().requireModal().optionPane().requireMessage("Invalid customer:\n" +
                "- First Name cannot be empty\n" +
                "- Last Name cannot be empty\n" +
                "- Nic No must be a number\n" +
                "- Passport ID cannot be a number\n" +
                "- A Date of birth must be specified\n" +
                "- A gender must be selected\n" +
                "- Contact number cannot be empty\n" +
                "- A photo must be specified");
    }

    @Disabled
    @Test
    void createdCustomerAppearsWhenSearched() {
        window.menuItem("customerRootMenu").click();
        window.menuItem("addCustomerMenuItem").click();

        window.textBox("addCustomerFirstNameInput").setText("John");
        window.textBox("addCustomerFirstNameInput").setText("John");
        window.textBox("addCustomerFirstNameInput").setText("John");
        window.textBox("addCustomerFirstNameInput").setText("John");
        window.textBox("addCustomerFirstNameInput").setText("John");
        window.textBox("addCustomerFirstNameInput").setText("John");
        window.textBox("addCustomerFirstNameInput").setText("John");
        window.textBox("addCustomerFirstNameInput").setText("John");
        window.textBox("addCustomerFirstNameInput").setText("John");

        window.panel("addCustomerDateInput").textBox().setText("Apr 22, 2021");

    }

}
