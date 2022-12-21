package Extent_report;





import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;




public class Working_with_Extent_report {
	WebDriver driver;
@Test
public void report() throws IOException {
	ExtentReporter reporter=new ExtentHtmlReporter("./Report/Execution.html");
	
	ExtentReports reports=new ExtentReports();
	reports.attachReporter(reporter);
	
	ExtentTest test=reports.createTest("Demo Webshop Regression");
	
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("https://demowebshop.tricentis.com");
	test.pass("Launch").addScreenCaptureFromPath(Capture_Screen_shot("Launch"));
	Capture_Screen_shot("Launch");
	driver.findElement(By.id("small-searchterms")).sendKeys("Books");
	driver.findElement(By.xpath("//input[@value='Search']")).click();
	test.log(Status.PASS, "Product search is successful");
	test.log(Status.INFO, "Search is completed");
	driver.close();
	reports.flush();
}
public String Capture_Screen_shot(String stepname) throws IOException {
	TakesScreenshot ts=(TakesScreenshot)driver;
	File source=ts.getScreenshotAs(OutputType.FILE);
	File destination=new File("./Snapshot/"+stepname+".png");
	String filepath="./Snapshot/"+stepname+".png";
	FileHandler.copy(source, destination);
	return "."+filepath;
}
}
