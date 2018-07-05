package Clases;

import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LlenarHoras {
	private WebDriver driver;
	private WebElement btnEnviar;
	private WebElement tablHoras;
	private WebElement almanaque;
	private WebElement txtFecha;
	private WebElement txtHoras;
	private WebElement lnkLogOut;
	private WebElement FechaInhabilitada;
	private WebElement FechaFutura;
	private WebElement btnLogout;

	public LlenarHoras(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	private void buscarElementos() {
		// Campo de texto para ingresar las horas en Ejecucuón de pruebas
		txtHoras = driver.findElement(
				By.xpath("//*[(@id=\'effortAssign20\') and (@class=\'ng-pristine ng-valid textBoxclass\')]"));

		// Se captura el elemento por su ruta de identificación
		btnEnviar = driver.findElement(By.xpath("//*[(@value=\'Submit\') and (@class=\'button buttonClass\')]"));

	}

	public void cerrarSesion() {
		lnkLogOut = driver.findElement(By.xpath("//*[@id=\"applicationContainer\"]/div[1]/div[2]/div/div[5]/a"));
		btnLogout = driver.findElement(By.xpath("//*[@id=\"logoutbutton\"]"));
		lnkLogOut.click();
		btnLogout.click();
	}

	public void Llenado() throws IOException, InterruptedException {
		Datos Horas = new Datos();
		String fechaCal = null;
		String XpathPersonalizado = "";
		// String XpathFuturo = "";
		// String XpathNoValido = "";
		// int diasNohabiles;
		//
		// boolean compararXpath;

		// Instancia de clase que permite acceder al método scrol
		Actions mover = new Actions(driver);

		// Por si nos tenemos que devolver de pantalla
		// String strPantallaActual = webDriver.getWindowHandle();
		PantallaActual();
		// diasNohabiles = 1;
		// try {
		// while (diasNohabiles <= 5) {
		for (int i = 1; i < 32; i++) {
			Thread.sleep(2000);

			// Metodo capturador de fecha
			fechaCal = ArmarFecha(i);

			// Se arma el Xpath para identificar eld ia a llenar en el calendario con las
			// fechas requeridas
			XpathPersonalizado = "//*[(@class=\'notFilledEffort\') and (@id=\'" + fechaCal + "-E\')]";

			while (!findBy(XpathPersonalizado)) {
				i++;
				fechaCal = ArmarFecha(i);
			}
	
			// Esperar 20 segundos que abra la página TimeSheet y proceder a dar click en el
			// calendario
			almanaque = (new WebDriverWait(driver, 10))
					.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(XpathPersonalizado))));

			// Esperar 20 segundos que abra la página TimeSheet y proceder a dar click en el
			// calendario
			txtFecha = driver.findElement(By.id(fechaCal));
			// ("//*td[@id='02/04/2018']/div*[@id='02/04/2018-E']"));
			txtFecha.click();

			tablHoras = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(
					(By.xpath("//*[(@class=\'ng-pristine ng-valid textBoxclass\') and (@id=\'effortAssign20\') ]"))));

			buscarElementos();

			// Se realiza el desplazamiento en la página hasta el elemento encontrado
			// anteriormente
			mover.moveToElement(txtHoras).click().build().perform();

			// mover.keyDown(horas, Keys.CONTROL);
			txtHoras.clear();
			txtHoras.sendKeys(Horas.getHoras());

			// Se reistran las horas llenadas
			btnEnviar.click();

		}

		JOptionPane.showMessageDialog(null, "Llenado Exitoso:" + fechaCal);
		cerrarSesion();
	}

	public void PantallaActual() {
		Set<String> setPantallas = driver.getWindowHandles();
		for (String item : setPantallas) {
			if (driver.switchTo().window(item).getCurrentUrl().contains("timesheet")) {
				break;
			}
		}
	}

	public String ArmarFecha(int i) {
		// Metodo capturador de fecha
		Calendar calendario = Calendar.getInstance();
		int dia;
		int mes;
		int anio;
		String fechaCal;

		dia = calendario.get(Calendar.DAY_OF_MONTH);
		mes = calendario.get(Calendar.MONTH) + 1;
		anio = calendario.get(Calendar.YEAR);
		fechaCal = "";

		dia = i;
		if ((dia < 10) && (mes < 10)) {
			fechaCal = "0" + dia + "/" + "0" + mes + "/" + anio;
		} else if ((dia < 10) && (mes > 9)) {
			fechaCal = "0" + dia + "/" + mes + "/" + anio;
		} else if ((dia > 9) && (mes < 10)) {
			fechaCal = dia + "/" + "0" + mes + "/" + anio;
		} else {
			fechaCal = dia + "/" + mes + "/" + anio;
		}
		return fechaCal;
	}
}
