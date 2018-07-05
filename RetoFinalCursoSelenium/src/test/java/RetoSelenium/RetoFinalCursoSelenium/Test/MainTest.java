package RetoSelenium.RetoFinalCursoSelenium.Test;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import RetoSelenium.RetoFinalCursoSelenium.Object.DatosObject;
import RetoSelenium.RetoFinalCursoSelenium.Pages.DatosGeneradosPage;
import RetoSelenium.RetoFinalCursoSelenium.Pages.FormularioPage;


public class MainTest {
	WebDriver driver;

	@Before
	public void abrirNavegador() {
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		//URL página principal
		driver.get("http://www.generatedata.com/?lang=es");
		
		//Ruta para paginas descargada
		//driver.get("file:///D:/SeleniumEjemplos/REto%20Curso%20Selenium/PaginaFormulario/generate.php.html");
	}
	
	@Test 
	/**
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void llenarFormulario() throws InterruptedException, IOException {
		DatosObject datosObject = new DatosObject();
		datosObject.leerArchivoXml();
		
		FormularioPage formularioPage = PageFactory.initElements(driver, FormularioPage.class);
		formularioPage.diligenciaFormulario();
		
		DatosGeneradosPage datosGeneradosPage =  PageFactory.initElements(driver, DatosGeneradosPage.class);
		datosGeneradosPage.setDriver(driver);
		datosGeneradosPage.verPantallaActual();
		datosGeneradosPage.traerContenidoHTML();
				
	}
	
}
