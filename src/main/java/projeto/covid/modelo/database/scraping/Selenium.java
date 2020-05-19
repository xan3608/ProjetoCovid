package projeto.covid.modelo.database.scraping;

import java.nio.file.Path;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import projeto.covid.modelo.database.temporario.DiretorioTemp;

public class Selenium {
	private WebDriver driver;
	private Path downloadPath;
	private String downloadName;

	public Selenium(DiretorioTemp diretorio) {
		System.setProperty("webdriver.gecko.driver", diretorio.getBrowserDriver().toString());
		this.downloadPath = diretorio.getBrowserDownload();

		FirefoxOptions option = new FirefoxOptions();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.dir", downloadPath.toString());
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;");
		option.setProfile(profile);
		option.setHeadless(false);
		this.driver = new FirefoxDriver(option);
	}

	public void downloadDados() {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get("https://covid.saude.gov.br/");

		String botaoDownloadSelector = "ion-button.btn-white.md.button.button-solid.button-has-icon-only.ion-activatable.ion-focusable.hydrated";
		WebElement botaoDownload = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(botaoDownloadSelector)));

		wait.until(ExpectedConditions.elementToBeClickable(botaoDownload));
		js.executeScript("arguments[0].click();", botaoDownload);
		esperarDownload(wait, js);
		driver.quit();
	}

	private void esperarDownload(WebDriverWait wait, JavascriptExecutor js) {
		js.executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("about:downloads");

		String downlaodselector = "richlistitem.download > hbox > vbox > ";
		WebElement donwloadProgress = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(downlaodselector + "progress")));
		System.out.println("Comecando download");
		this.downloadName = driver.findElement(By.cssSelector(downlaodselector + "description")).getAttribute("value");
		System.out.println("Esperando download");
		wait.until(ExpectedConditions.invisibilityOf(donwloadProgress));
		System.out.println("Download concluido");
		driver.close();
	}

	public String getDownloadName() {
		return downloadName;
	}
}
