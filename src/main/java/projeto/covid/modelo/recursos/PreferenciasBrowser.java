package projeto.covid.modelo.recursos;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class PreferenciasBrowser {

	private FirefoxOptions option;
	private FirefoxProfile profile;

	public FirefoxOptions carregarPreferencias(DiretorioTemp diretorioBrowser) {

		System.setProperty("webdriver.gecko.driver", diretorioBrowser.getBrowserDriver().toString());
//		System.setProperty("webdriver.firefox.bin", diretorioBrowser.getBrowserBinary().toString());

		profile = new FirefoxProfile();
//		profile.setPreference("browser.download.dir", diretorioBrowser.getBrowserDownload().toString());
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

		option = new FirefoxOptions();
		option.setBinary(diretorioBrowser.getBrowserBinary());
		option.setProfile(profile);

		return option;
	}
}
