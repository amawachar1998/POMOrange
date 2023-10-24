package setup;

import java.awt.Desktop;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SetUp {

	public WebDriver driver;
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	private String htmlfile = null;
	public static ExtentTest test;
	public String TestCase;

	@BeforeSuite
	public void setupExtentReport() {

		String day = new SimpleDateFormat("YYYY.dd.MM").format(new Date());
		String timeStamp = new SimpleDateFormat("_HH.mm").format(new Date());
		String filePath = System.getProperty("user.dir") + "./AutomationReports/Orange_" + day + timeStamp;

		htmlfile = (filePath + ".html");

		extent = new ExtentReports();
		spark = new ExtentSparkReporter(htmlfile);
		spark.config(ExtentSparkReporterConfig.builder().theme(Theme.DARK).documentTitle("MyReport").build());
		extent.attachReporter(spark);

		extent.setSystemInfo("Comapany Name", "Orange Demo");
		extent.setSystemInfo("Project Name", "HRM");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("Tester Name", "Akash Awachar");

		try {
			spark.loadXMLConfig("C:\\Users\\Dell\\eclipse-workspace\\POMOrange\\extent-report-config.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		extent.attachReporter(spark);
		System.out.println("Extent Test Setup");
	}

	@AfterSuite
	public void tearDownExtentReport() throws IOException, InterruptedException {
		extent.flush();
		System.out.println("Extent Test Collection");
		File file = new File(htmlfile);
		Thread.sleep(3000);
		Desktop.getDesktop().browse(file.toURI());
		System.out.println("Opening Extent Report");

	}

	@BeforeMethod
	@Parameters("browser")
	public void openBrowser(String browser) throws IOException {

//
//		String day = new SimpleDateFormat("YYYY.dd.MM").format(new Date());
//		String timeStamp = new SimpleDateFormat("_HH.mm").format(new Date());
//		String filePath = System.getProperty("user.dir") + "./AutomationReports/Orange_" + day + timeStamp;
//
//		htmlfile = (filePath + ".html");
//
//		extent = new ExtentReports();
//		spark = new ExtentSparkReporter(htmlfile);
//		spark.config(ExtentSparkReporterConfig.builder().theme(Theme.DARK).documentTitle("MyReport").build());
//		extent.attachReporter(spark);
//
//		extent.setSystemInfo("Comapany Name", "Orange Demo");
//		extent.setSystemInfo("Project Name", "HRM");
//		extent.setSystemInfo("Environment", "Automation Testing");
//		extent.setSystemInfo("Tester Name", "Akash Awachar");
//
//		try {
//			spark.loadXMLConfig("C:\\Users\\Dell\\eclipse-workspace\\POMOrange\\extent-report-config.xml");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		extent.attachReporter(spark);
//		

		FileReader fileReader = new FileReader(
				"C:\\Users\\Dell\\eclipse-workspace\\POMOrange\\src\\main\\java\\config.properties");

		Properties properties = new Properties();
		properties.load(fileReader);
		String url = properties.getProperty("url");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(url);

		System.out.println("Opening Browser");
	}

	@AfterMethod
	public void closeBrowser() throws IOException, InterruptedException {
		driver.close();
		System.out.println("Closing Browser");

//		extent.flush();
//
//		File file = new File(htmlfile);
//		Thread.sleep(3000);
//		Desktop.getDesktop().browse(file.toURI());
//		System.out.println("Before Suite");
//		System.out.println("Opening Extent Report");
	}

}
