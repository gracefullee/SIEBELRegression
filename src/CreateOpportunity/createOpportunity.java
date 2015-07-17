package CreateOpportunity;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jxl.*; 
import jxl.read.biff.BiffException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class createOpportunity{

	private static java.io.File file;
	private static String username = "";
	private static String password = "";
	private static String SiebelURL = "http://sblqa.corp.oakwood.com/callcenter_oui_enu/start.swe";
	private static WebDriver driver;
	protected static Window window;
	private static Workbook workbook;
	private static Sheet testData;
	private static Object lock = new Object();
	private static int rowIndex = 0;
	
	@Test
	public static void OpenWebsite(String URL) throws InterruptedException {
		
	  // Optional, if not specified, WebDriver will search your path for chromedriver.
	  System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
	   driver = new ChromeDriver();
	   driver.get(URL);
	   driver.manage().window().maximize();
	  Thread.sleep(1000);
	}
	
	
	
	public static void LogIn(String username, String password)
	{
		try {
			WebElement userIDBox = driver.findElement(By.name("SWEUserName"));
			userIDBox.sendKeys(username);
			WebElement passwordBox = driver.findElement(By.name("SWEPassword"));
			passwordBox.sendKeys(password);
			WebElement loginButton = driver.findElement(By.className("siebui-login-btn"));
			loginButton.click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // Let the user actually see something!
	}
	
	
	
	public static void createOpty_Form()
	{
		try{
			
			Cell [] testLine = testData.getRow(rowIndex);
			
			//Navigate to Opportunities Tab
			List<WebElement> topTabs = driver.findElements(By.cssSelector("[class*='ui-state-default ui-corner-top']"));
			WebElement opportunitiesTab = topTabs.get(3);
			opportunitiesTab.click();
			Thread.sleep(10000);
			
			//Create New Opportunity
			WebElement newButton = driver.findElement(By.id("s_2_1_11_0_Ctrl"));
			newButton.click();
			Thread.sleep(5000);
			
			//Add corp number & name
			driver.findElement(By.id("s_1_1_121_0_icon")).click();
			Thread.sleep(3000);
				
		    driver.findElement(By.name("s_3_1_197_0")).clear();
			driver.findElement(By.name("s_3_1_197_0")).sendKeys(testLine[0].getContents());
		    	//Click Search
			driver.findElement(By.id("s_3_1_198_0_Ctrl")).click();
			Thread.sleep(2000);
		    	//Check if Corporate Name is found and click OK
			WebElement OKBtn = driver.findElement(By.id("s_3_1_199_0_Ctrl"));
			OKBtn.click();
			Thread.sleep(2000);
			
			
			//Fill in Guest Information
				//Click on Guest Needs
			List<WebElement> secondaryTabs = driver.findElements(By.cssSelector("[class*='ui-state-default ui-corner-top']"));
			secondaryTabs.get(14).click();
			Thread.sleep(3000);
				//First Name & Last Name
			driver.findElement(By.name("s_2_1_86_0")).sendKeys(testLine[1].getContents());
			driver.findElement(By.name("s_2_1_88_0")).sendKeys(testLine[2].getContents());
				//Address
			driver.findElement(By.name("s_2_1_22_0")).sendKeys(testLine[3].getContents());
			driver.findElement(By.name("s_2_1_23_0")).sendKeys(testLine[4].getContents());
			driver.findElement(By.name("s_2_1_26_0")).sendKeys(testLine[5].getContents()+"\n");
			driver.findElement(By.name("s_2_1_30_0")).sendKeys(testLine[6].getContents());
			driver.findElement(By.name("s_2_1_24_0")).sendKeys(testLine[7].getContents()+"\n");
				//Phone Number
					//Work Phone
			driver.findElement(By.name("s_2_1_90_0")).sendKeys(testLine[8].getContents());
					//Home Phone
			driver.findElement(By.name("s_2_1_87_0")).sendKeys(testLine[9].getContents());
					//Mobile Phone
			driver.findElement(By.name("s_2_1_89_0")).sendKeys(testLine[10].getContents());
				//Guest Email
			driver.findElement(By.name("s_2_1_84_0")).sendKeys(testLine[11].getContents());
				//Guest Title
			driver.findElement(By.name("s_2_1_27_0")).sendKeys(testLine[12].getContents());
				//Department
			driver.findElement(By.name("s_2_1_25_0")).sendKeys(testLine[13].getContents()+"\n");
				//Dates (Automatically fills in ITV Date)
			driver.findElement(By.name("s_2_1_64_0")).sendKeys(testLine[14].getContents()+"\n");
			driver.findElement(By.name("s_2_1_31_0")).sendKeys(testLine[15].getContents()+"\n");
					//If HARD ITV Date
			if(testLine[16].getContents().equals("Y"))
				driver.findElement(By.name("s_2_1_9_0")).click();
				//Budget
			driver.findElement(By.name("s_2_1_51_0")).sendKeys(testLine[17].getContents()+"\n");
				//Location Desired
			driver.findElement(By.name("s_2_1_0_0")).sendKeys(testLine[18].getContents()+"\n");
				//Region
			driver.findElement(By.name("s_2_1_2_0")).sendKeys(testLine[19].getContents()+"\n");
				//Lease Term
			driver.findElement(By.name("s_2_1_60_0")).sendKeys(testLine[20].getContents()+"\n");
				//Pets
			if(testLine[21].getContents().equals("Y"))
			{
				driver.findElement(By.name("s_2_1_65_0")).click();
				driver.findElement(By.name("s_2_1_71_0")).sendKeys("0 Cats and 0 Dogs.  Other pets (if any): "+testLine[22].getContents());
				driver.findElement(By.name("s_2_1_66_0")).sendKeys("0 Cats and 0 Dogs.  Other pets (if any): "+testLine[22].getContents());
			}
				//Apartment Preference
					//Size/Type
			driver.findElement(By.name("s_2_1_46_0")).sendKeys(testLine[23].getContents()+"\n");
					//Home Services Package
			driver.findElement(By.name("s_2_1_57_0")).sendKeys(testLine[24].getContents()+"\n");
					//Housekeeping
			driver.findElement(By.name("s_2_1_62_0")).sendKeys(testLine[25].getContents()+"\n");
				//Reason
			driver.findElement(By.name("s_2_1_81_0")).sendKeys(testLine[26].getContents()+"\n");
				//Fin Responsibility
			driver.findElement(By.name("s_2_1_91_0")).sendKeys(testLine[27].getContents()+"\n");
				//Number of People
					//Adults
			driver.findElement(By.name("s_2_1_20_0")).clear();
			driver.findElement(By.name("s_2_1_20_0")).sendKeys(testLine[28].getContents());
					//Children Under 18
			driver.findElement(By.name("s_2_1_21_0")).clear();
			driver.findElement(By.name("s_2_1_21_0")).sendKeys(testLine[29].getContents());
				//Quantity
			driver.findElement(By.name("s_2_1_74_0")).clear();
			driver.findElement(By.name("s_2_1_74_0")).sendKeys(testLine[30].getContents());
				//Internet Service
			driver.findElement(By.name("s_2_1_15_0")).sendKeys(testLine[31].getContents()+"\n");
			
			//Return back to Opportunities List
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void waitForPageLoad() {
	    ExpectedCondition<Boolean> pageLoadCondition = new
	        ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
	    WebDriverWait wait = new WebDriverWait(driver, 30);
	    wait.until(pageLoadCondition);
	}
	
	public static void createOpty_Tab()
	{
		try{
			Cell [] testLine = testData.getRow(rowIndex);
			
			List<WebElement> topTabs = driver.findElements(By.cssSelector("[class*='ui-state-default ui-corner-top']"));
			WebElement opportunitiesTab = topTabs.get(3);
			opportunitiesTab.click();
			Thread.sleep(3000);
			WebElement newButton = driver.findElement(By.id("s_2_1_11_0_Ctrl"));
			newButton.click();
			
			//Find the first item on the updated list
			Thread.sleep(3000);
			driver.findElement(By.id("1Account")).click();
			driver.findElement(By.id("1_Account")).sendKeys(testLine[0].getContents()+"\n");
			Thread.sleep(3000);
			driver.findElement(By.id("s_3_1_159_0_Ctrl")).click();
			
			//Move-In Date & Length of Stay
			driver.findElement(By.id("1OAK_Move-In_Date")).click();
			driver.findElement(By.id("1_OAK_Move-In_Date")).sendKeys(testLine[14].getContents()+"\n");
			driver.findElement(By.id("1OAK_Stay_Length")).click();
			driver.findElement(By.id("1_OAK_Stay_Length")).sendKeys(testLine[15].getContents());
			
			//Name & Email
			driver.findElement(By.id("1OAK_Resident_Last_Name")).click();
			driver.findElement(By.id("1_OAK_Resident_Last_Name")).sendKeys(testLine[2].getContents());
			driver.findElement(By.id("1OAK_Resident_First_Name")).click();
			driver.findElement(By.id("1_OAK_Resident_First_Name")).sendKeys(testLine[1].getContents()+"\t");
			driver.findElement(By.id("1OAK_Resident_Email")).click();
			driver.findElement(By.id("1_OAK_Resident_Email")).sendKeys(testLine[11].getContents());
			
			//Title & Department
			driver.findElement(By.id("1OAK_Resident_title")).click();
			driver.findElement(By.id("1_OAK_Resident_Title")).sendKeys(testLine[12].getContents());
			driver.findElement(By.id("1OAK_Resident_Department")).click();
			driver.findElement(By.id("1_OAK_Resident_Department")).sendKeys(testLine[13].getContents());
			
			//Location Desired
			driver.findElement(By.id("1OAKLocationDesired")).click();
			driver.findElement(By.id("1_OAKLocationDesired")).sendKeys(testLine[18].getContents());
			//Reason for Stay
			driver.findElement(By.id("1OAK_Reason_for_Move")).click();
			driver.findElement(By.id("1_OAK_Reason_for_Move")).sendKeys(testLine[26].getContents());
			//Home Services
			driver.findElement(By.id("1OAK_Home_Service_Package")).click();
			driver.findElement(By.id("1_OAK_Home_Service_Package")).sendKeys(testLine[24].getContents());
			//Housekeeping
			driver.findElement(By.id("1OAK_Maid_Services")).click();
			driver.findElement(By.id("1_OAK_Maid_Services")).sendKeys(testLine[25].getContents());
			//Internet Service
			driver.findElement(By.id("1OAK_Internet_Service_Flag")).click();
			driver.findElement(By.id("1_OAK_Internet_Service_Flag")).sendKeys(testLine[31].getContents());
			//Lease Term
			driver.findElement(By.id("1OAK_Lease_Term")).click();
			driver.findElement(By.id("1_OAK_Lease_Term")).sendKeys(testLine[20].getContents());
			//Budget
			driver.findElement(By.id("1OAK_Budget")).click();
			driver.findElement(By.id("1_OAK_Budget")).sendKeys(testLine[17].getContents());
			
			driver.findElement(By.id("1OAK_Selling_Office")).click();
			
			Thread.sleep(3000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
	
	

	
	public static void main(String [] args) throws BiffException, IOException
	{
			
		try {
			
	        window = new Window();
	        
	        Thread t = new Thread() {
	            public void run() {
	                synchronized(lock) {
	                    while (window.isVisible())
	                        try {
	                            lock.wait();
	                        } catch (InterruptedException e) {
	                            e.printStackTrace();
	                        }
	                }
	            }
	        };
	        t.start();
	        
	        window.addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent arg0) {
	                synchronized (lock) {
	                    window.setVisible(false);
	                    lock.notify();
	                }
	            }

	        });

	        t.join();
	        
			//Open Website and Log In
	        username = window.gui.UN;
	        password = window.gui.PW;
	        file = window.gui.file;
	        workbook = Workbook.getWorkbook(new File(file.getPath()));
	        testData = workbook.getSheet(0);
	        if(window.gui.header)
	        	rowIndex++;
	        while(rowIndex!=testData.getRows()){
	        	OpenWebsite(SiebelURL);
				LogIn(username, password);
				Thread.sleep(2000);
				createOpty_Tab();
				driver.quit();
				rowIndex++;
	        }
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			driver.quit();
		}
	}
	
}
