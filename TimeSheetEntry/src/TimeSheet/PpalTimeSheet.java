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
/*
	public static void ClickVentana() throws AWTException {
		Robot r = new Robot();
	    r.mouseMove(390,390);
	    r.mousePress( InputEvent.BUTTON1_MASK );
	    r.mouseRelease( InputEvent.BUTTON1_MASK );
	    try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    r.mousePress( InputEvent.BUTTON1_MASK );
	    r.mouseRelease( InputEvent.BUTTON1_MASK );
	}
	*/
}