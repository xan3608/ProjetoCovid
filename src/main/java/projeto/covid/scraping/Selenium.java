package projeto.covid.scraping;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import recursos.DiretorioTemp;
import recursos.PreferenciasBrowser;

public class Selenium {

	public void downloadDados(DiretorioTemp diretorioBrowser) {

		PreferenciasBrowser option = new PreferenciasBrowser();
		WebDriver driver = new FirefoxDriver(option.carregarPreferencias(diretorioBrowser));

		try {
			String linkInicial = "https://covid.saude.gov.br/";
			driver.get(linkInicial);
			WebDriverWait wait = new WebDriverWait(driver, 120);
			WebElement botao = wait.until(ExpectedConditions
					.elementToBeClickable(By.cssSelector("div.no-shadow:nth-child(2) > ion-button:nth-child(1)")));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", botao);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro na execução");
		} finally {
			driver.quit();
		}
	}
}
