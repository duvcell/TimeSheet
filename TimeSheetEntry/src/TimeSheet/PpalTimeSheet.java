package TimeSheet;
//import java.awt.event.InputEvent;
//import java.awt.Robot;
//import java.awt.event.InputEvent;
//import javax.swing.JOptionPane;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import java.awt.AWTException;
import Clases.Home;
import Clases.LlenarHoras;

public class PpalTimeSheet {
	
	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		WebDriver driver;
		// ----------------------------------------
		Home loginHome = new Home();
		driver = loginHome.loguearUsuario();
		LlenarHoras llenarHoras = new LlenarHoras(driver);
		llenarHoras.Llenado();

		// ----------------------------------------
	}
}