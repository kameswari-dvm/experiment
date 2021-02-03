package files;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindingTradusPROID {

	static ArrayList<String> tradus1 = new ArrayList<String>();
	static ArrayList<String> Hexon = new ArrayList<String>();
	static ArrayList<String> sites = new ArrayList<String>();
	static ArrayList<String> href = new ArrayList<String>();
	static ArrayList<String> href1 = new ArrayList<String>();
	static String client;
	static String TPROID;
	static String mk;
	static String md;
	static String images;
	public static WebDriver driver = null;
	public static int i = 0;

	static {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public static void main(String[] args) throws Exception {
		wronglinks();
		StorringData();
		finaloutput();
	}

	private static void wronglinks() {
		String site[] = {"https://www.tradus.com/en/transport/trucks/tipper-trucks/man/man-12-240-meiller-kipper-heck-kran-hmf-403-k2-2007-4301323","https://www.tradus.com/en/transport/trucks/tipper-trucks/man/man-12-240-meiller-kipper-heck-kran-hmf-403-k2-2007-4301321","https://www.tradus.com/en/transport/trucks/tipper-trucks/daf/daf-cf-75-310-fan-kraancontainer-kabelsysteem-losse-container-2008-4143200","https://www.tradus.com/en/transport/trucks/tipper-trucks/daf/daf-cf85-360-fan-z-kraan-kipper-schrott-metall-glas-recycling-2008-4143227","https://www.tradus.com/en/transport/trucks/tipper-trucks/daf/daf-cf85-360-fan-z-kraan-kipper-schrott-metall-glas-recycling-2008-4143235","https://www.tradus.com/en/transport/trucks/tipper-trucks/daf/daf-cf85-360-fan-z-kraan-kipper-schrott-metall-glas-recycling-2008-4143229","https://www.tradus.com/en/transport/trucks/tipper-trucks/mercedes-benz/mercedes-benz-2550-sk-6x2-eps-hub-reduction-hmf-3620-1993-4299992","https://www.tradus.com/en/transport/trucks/tipper-trucks/mercedes-benz/mercedes-benz-1224-k-meiller3seiten-u-ladekran-xs077cl-2013-4299957","https://www.tradus.com/en/transport/trucks/tipper-trucks/other/strohbach-deckel-container-10-cbm-symetrisch-2002-4299955","https://www.tradus.com/en/transport/trucks/tipper-trucks/brantner/brantner-2a-2s-kipper-4296094","https://www.tradus.com/en/transport/trucks/tipper-trucks/brantner/brantner-2a-2s-kipper-4296095","https://www.tradus.com/en/transport/trucks/tipper-trucks/mercedes-benz/mercedes-benz-actros-3248-8x4-euro5-retarder-leasing-nur-163-k-2013-4299896","https://www.tradus.com/en/transport/trucks/tipper-trucks/other/gebracht-abrollcontainer-7-3-m-m3-43-5-2005-4298983","https://www.tradus.com/en/transport/trucks/tipper-trucks/other/gebracht-abrollcontainer-6-5-m-m3-43-5-2005-4298982","https://www.tradus.com/en/transport/trucks/tipper-trucks/brantner/brantner-e-8041-25-km-h-2019-4291603","https://www.tradus.com/en/transport/trucks/tipper-trucks/mercedes-benz/mercedes-benz-1833-2005-4302902","https://www.tradus.com/en/transport/trucks/tipper-trucks/ginaf/ginaf-x4241-s-8x4-crane-hiab-1220s-x-p-2007-4270818","https://www.tradus.com/en/transport/trucks/tipper-trucks/brantner/brantner-ta-14045-2xxl-40-km-h-2019-4296119","https://www.tradus.com/en/transport/trucks/tipper-trucks/mercedes-benz/mercedes-benz-actros-3244-8x4-e5-ahk-analog-tacho-leasing-2006-4292818","https://www.tradus.com/en/transport/trucks/tipper-trucks/man/man-tgs-26-400-6x2-4-bl-meiller-abrollkipper-ak-16-t-2013-4292832","https://www.tradus.com/en/transport/trucks/tipper-trucks/man/man-19-322-atlas-teleskop-absetzer-1993-4292642","https://www.tradus.com/en/transport/trucks/tipper-trucks/man/man-l-90-18-264-lak-2-achs-allradkipper-kran-1997-4292449","https://www.tradus.com/en/transport/trucks/tipper-trucks/brantner/brantner-einachs-dreiseitenkipper-e8041-2019-4294447"};
		for (String sit : site) {
			sites.add(sit);
		}
	}

	private static void finaloutput() {
		System.out.println(tradus1);
		System.out.println(Hexon);
		System.out.println(href);
		System.out.println(href1);
	}

	private static void StorringData() throws Exception {
		logintobackoff(driver);
		logintoadmin();
		for (String site : sites) {
			try {
				loadurl(site);
				String name1 = getText(
						By.xpath("//div/p[@class='h1 search-form-header__title search-form-header__title--compact']"));
				if (name1 != "") {
					csvFile("TradusCompany", site);
				}
				images = getAttribute(By.xpath("//div[@class='fixed-ratio__content']/img"), "src");
				String image = getAttribute(By.xpath("//li//div[@class='fixed-ratio__content']/img[@src]"), "src");
				if (name1 == "") {
					if (!image.contains("/assets/offer-result")) {
						String namess = getText(By.xpath("//h2[@class='h3 offer-seller__title']"));
						String name = decode(namess);
						mk = getText(By.xpath("//dd[@itemprop='manufacturer']")).replace(" ", "-");
						String md1 = getText(By.xpath("//dd[@itemprop='model']"));
						md = decode(md1);
						loadurl("https://admin-www.tradus.com/offer/"
								+ site.substring(site.lastIndexOf("-") + 1, site.length()));
						loginfailstoadmin();
						String seller = getAttribute(By.xpath("//tr[8]/td/a"), "href")
								.replace("https://admin-www.tradus.com/seller/", "");
						loadurl("https://admin-www.tradus.com/seller/" + seller);
						String name3 = getText(By.xpath("//tr[4]/td[contains(.,'@')]"));
						String name2 = decode(name3);
						try {
							if (!name2.isEmpty()) {
								loadurl("https://pro.tradus.com/backoffice/users?sort_by=sign_up&origin=&userType=&client_id=&content_origin_id=&search_string="
										+ name2);
							} else {
								sendKeys(By.xpath("//*[@id='search_string']"), name);
								acionclick(By.xpath("//*[@id='search_submit']"));
							}
						} catch (Exception e) {
						}
						Thread.sleep(1000);
						TPROID = getText(By.xpath("//*[@id='datatable']/tbody/tr[contains(.,'" + seller + "')]/td[1]"));
						loadurl("https://pro.tradus.com/backoffice/users/" + TPROID + "?no_menu=1");
						client = getText(By.xpath("//div[2]/dl[@class='dl-horizontal']//dd[4]"));
						Thread.sleep(1000);
						loginfailstobackoff();
						Traduspro();
						hexon();
						sunfrausers();
					}
				}
			} catch (Exception e) {
			}
			System.out.println(sites.get(i));
			i++;
		}

	}

	private static void loginfailstoadmin() {
		try {
			String admin = getText(By.xpath("//div/button[@type='submit']"));
			if (admin.contains("Login")) {
				logintoadmin();
			}
		} catch (Exception e) {
		}
	}

	private static void hexon() throws IOException {
		// TODO Auto-generated method stub
		if (client.contains("hexon")) {
			Hexon.add(client + "-------" + sites.get(i));
			csvFile("Hexon", client + "-------" + sites.get(i) + "-------" + images);
		}
	}

	private static void loginfailstobackoff() {
		try {
			String backoff = getText(By.xpath("//div/button[contains(.,'Login')]"));
			if (backoff.contains("Login")) {
				logintobackoff(driver);
			}
		} catch (Exception e) {
		}
	}

	private static void Traduspro() throws IOException {
		// TODO Auto-generated method stub
		if (client.contains("Tradus PRO")) {
			csvFile("TradusPRO", client + "-------" + sites.get(i) + "-------" + images);
		}
	}

	private static void sunfrausers() throws InterruptedException, IOException, AWTException {
		// TODO Auto-generated method stub
		if (client.contains("Sunfra")) {
			wai();
			loadurl("https://pro.tradus.com/backoffice/login-as-user/" + TPROID);
			try {
				if (!mk.equalsIgnoreCase("other")) {
					loadurl("https://pro.tradus.com/en/frontend/items?category=&make=" + mk + "&model=" + md);
				} else if (mk.equalsIgnoreCase("other")) {
					loadurl("https://pro.tradus.com/en/frontend/items?category=&make=&model=" + md);
				}
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			List<WebElement> urls = getWebElements(By.xpath("//div[@class='advert-header__name']/a"));
			List<WebElement> Turl = getWebElements(By.xpath("//*[text()='Tradus']/following-sibling::div/a"));
			try {
				String add = getText(By.xpath("//div/span[@class='num-adverts-by-status']"));
				int addc = Integer.parseInt(add);
				/*
				 * if (addc == 0) { loadurl("https://pro.tradus.com/en/frontend/items"); if
				 * (!mk.equalsIgnoreCase("other")) { sendKeys(By.xpath("//*[@id='make']"), mk);
				 * } Thread.sleep(1000); sendKeys(By.xpath("//*[@id='model']"), md.replace("+",
				 * "%2B")); Thread.sleep(1000); acionclick(By.xpath(
				 * "/html/body/div[1]/div[2]/section[2]/div[1]/form/div/div[3]/span"));
				 * href.add(client + "---" + sites.get(i) + "---" +
				 * "Extra offer in Tradus need to be deleted"); csvFile("Deletedoffers", client
				 * + "---" + sites.get(i) + "---" + "Extra offer in Tradus need to be deleted" +
				 * "-------" + images); }
				 */
			} catch (Exception e) {
			}
			while (true) {
				for (int j = 0; j < urls.size(); j++) {
					String url = urls.get(j).getAttribute("href");
					String tradus = Turl.get(j).getAttribute("href");
					href1.add(tradus + "-------" + url.replace("https://pro.tradus.com/frontend/items/", ""));
					Thread.sleep(10);
					if (tradus.contains(sites.get(i))) {
						tradus1.add(client + "-------" + tradus + "-------"
								+ url.replace("https://pro.tradus.com/frontend/items/", ""));
						/*
						 * System.out.println(client+"-------"+tradus+"-------"+url.replace(
						 * "https://pro.tradus.com/frontend/items/", ""));
						 */
						csvFile("SunfraUsers", client + "-------" + tradus + "-------"
								+ url.replace("https://pro.tradus.com/frontend/items/", "") + "-------" + images);
					}
				}
				try {
					String next = getText(By.xpath("//div//a[@rel='next']"));
					if (next.contains("Next")) {
						acionclick(By.xpath("//div//a[@rel='next']"));
					} else
						break;
					Thread.sleep(300);
				} catch (Exception e) {/* e.printStackTrace(); */
					break;
				}
			}
		}
	}

	private static void wrongurllink(String Data) throws IOException {
		// TODO Auto-generated method stub
		maximize();
		loadurl(Data);
		while (true) {
			List<WebElement> url2 = getWebElements(By.xpath("//div//h3//a[@itemprop='url']"));
			for (int i = 0; i < url2.size(); i++) {
				String url1 = url2.get(i).getAttribute("href");
				csvFile("sitesurls", url1);
				sites.add(url1);
			}
			try {
				String next = getText(By.xpath("//div/a[@title='Next']/span[1]"));
				if (next.contains("Next")) {
					acionclick(By.xpath("//div/a[@title='Next']"));
					Thread.sleep(300);
				} else
					break;
			} catch (Exception e) {
				break;
			}
		}
	}

	private static void loadurl(String Data) {
		try {
			driver.get(Data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<WebElement> getWebElements(By by) {
		List<WebElement> lis = driver.findElements(by);
		return lis;
	}

	public static void acionclick(By by) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(by)).click().perform();
	}

	private static void logintoadmin() throws Exception {
		loadurl("https://admin-www.tradus.com/login");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//fieldset/input[@id='username']"))));
		sendKeys(By.xpath("//fieldset/input[@id='username']"), "raghu@sunfra.com");
		sendKeys(By.xpath("//fieldset/input[@id='user-password']"), "123123");
		click(By.xpath("//*[@type='submit']"));
	}

	private static void logintobackoff(WebDriver driver) throws Exception {
		loadurl("https://pro.tradus.com/backoffice/login");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//*[@class='panel-heading']"))));
		sendKeys(By.xpath("//*[@id='email']"), "indraja.mandem@olx.com");
		sendKeys(By.xpath("//*[@id='password']"), "Indu#429");
		click(By.xpath(".//*[@type='submit']"));
	}

	public static String getAttribute(WebElement ele, String attribute) {
		try {
			return ele.getAttribute(attribute).trim();
		} catch (Exception e) {
			return "";
		}
	}

	public static String getAttribute(By by, String attribute) {
		try {
			return getWebElement(by).getAttribute(attribute).trim();
		} catch (Exception e) {
			return "";
		}
	}

	public static void csvFile(String FileName, String data) throws IOException {
		String rootDir = System.getProperty("user.home") + "\\Desktop\\CSV\\";
		File fila = new File(rootDir);
		if (!fila.isDirectory()) {
			fila.mkdirs();
		}
		if (FileName.contains(".")) {
			FileName = FileName.replaceAll("\\.", "_");
		}
		File file = new File(rootDir + FileName + ".csv");
		if (file.exists()) {

		} else
			try {
				file.createNewFile();
			} catch (Exception e) {
			}
		try {
			File newTextFile = new File(rootDir + FileName + ".csv");
			String content = new String(Files.readAllBytes(Paths.get(newTextFile.toString())));
			FileWriter fw = new FileWriter(newTextFile, true);
			fw.write(data + "\r\n");
			fw.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}

	public static void maximize() {
		driver.manage().window().maximize();
	}

	public static String decode(String url) {
		try {
			String encodeURL = URLEncoder.encode(url, "UTF-8");
			return encodeURL;
		} catch (UnsupportedEncodingException e) {
			return "Issue while encoding" + e.getMessage();
		}
	}

	public static void wai() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_NUM_LOCK);
		r.keyRelease(KeyEvent.VK_NUM_LOCK);
	}

	public static WebElement getWebElement(By by) {
		WebElement ele = driver.findElement(by);
		return ele;
	}

	public static String getText(WebElement ele) {
		try {
			return ele.getText().trim();
		} catch (Exception e) {
			return "";
		}
	}

	public static void click(By by) throws Exception {
		int count = 1;
		for (int i = 1; i <= 1; i++) {
			try {
				if (count > 2)
					throw new Exception();
				getWebElement(by).click();
			} catch (StaleElementReferenceException e) {
				--i;
				count++;
			} catch (ElementNotVisibleException e) {
				--i;
				count++;
			} catch (WebDriverException e) {
				--i;
				count++;
			}
		}

	}

	public static String getText(By by) {
		try {
			return getWebElement(by).getText().trim();
		} catch (Exception e) {
			return "";
		}
	}

	public static boolean sendKeys(By by, String data) {
		try {
			getWebElement(by).clear();
			getWebElement(by).sendKeys(data);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}

