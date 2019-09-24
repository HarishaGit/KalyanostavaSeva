import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;

public class Calculator 
{

	//METHODS -- all 4.
	private void add() 
	{
		System.out.println("add");
	}
	
	public void sub()
	{
		System.out.println("sub");
	}
	
	public void mul()
	{
		System.out.println("Mul");
	}
	
	public void Div()
	{
		System.out.println("Div");
	}
	
	public static void CallSleep()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void TTD_Kalyantostava_Code()//Incomplete Code. on 23 September 2019.
	{
		
		Calculator calc = new Calculator();
		System.out.println("Hi, Selenium Learning");
		System.out.println("JI");
		System.out.println("ide aythu");
		calc.sub();
		calc.add();
		calc.mul();
		
		//Just Create an OBJECT from the CLASS FirefoxDriver.
		//We just need to call respective Class/Objects 
		//Selenium is not a TOOL.
		//its a Framework/API.
		
		FirefoxDriver harisha = new FirefoxDriver();
		harisha.get("https://ttdsevaonline.com/#/login");
		System.out.println(harisha.getTitle());
		CallSleep();
		//form-control u_name ng-pristine ng-invalid ng-invalid-required ng-valid-pattern ng-valid-maxlength ng-touched
		//input{@name="uName"]
		harisha.findElementByXPath("//input[contains(@placeholder,'Enter your registered Email ID')]").click();
		WebElement uName = harisha.findElementByXPath("//input[contains(@placeholder,'Enter your registered Email ID')]");
		uName.sendKeys(new String[]{"xyz@gmail.com"});
		harisha.findElementByXPath("//input[contains(@name,'pwd')]").click();
		harisha.findElementByXPath("//input[contains(@name,'pwd')]").sendKeys(new String[]{"xyz"});
		harisha.findElementByXPath("//button[@id='login_sub']").click();
		//Thread.sleep(1000);
		CallSleep();
		harisha.get("https://ttdsevaonline.com/#/sevaCal");
		CallSleep();
		//harisha.findElementByXPath("//select[@id='SevaList']").click();
		//harisha.findElementByXPath("//option[contains(@value,'number:12')]").click();
		CallSleep();
		//harisha.findElementByXPath("//calendar[@id='cal3']").click();
		CallSleep();
		//harisha.findElementByXPath("//calendar[@id='cal2']//div[3]").click();
		CallSleep();
		
		
		//List topics = (List)harisha.findElementByXPath("//span[@class='topic']");
//		
		//List<WebElement> elements = harisha.findElementsByXPath("//calendar[@id='cal3']//div[3]//span");
		//String ji = harisha.findElementsByXPath("//calendar[@id='cal3']//div[3]//span").getText();
		Integer iCou, iFin;
	    String sColo, sDateColor, ko;
	    
		for (iCou=3; iCou< 8; ++iCou)
		{
			List<WebElement> spans = harisha.findElementsByXPath("//calendar[@id='cal3']//div["+iCou+"]//span");
			for (WebElement span : spans)
			{
			    String text = span.getText();
			    
			    System.out.println(text);
			    iFin = Integer.parseInt(text);
			    
			    sDateColor = harisha.findElement(By.xpath("//calendar[@id='cal3']//div["+iCou+"]//span[.='"+ text +"']")).getCssValue("background-color");
			    ko = harisha.findElement(By.xpath("//calendar[@id='cal3']//div["+iCou+"]//span[.='"+ text +"']")).getAttribute("class");
			    		System.out.println(ko);
			    		System.out.println(sDateColor);

			    sColo = Color.fromString(sDateColor).asHex();
			    System.out.println(sColo);
			    
			    if ( iFin >= 31)
			    	{
			    		break;
			    	}
			}
		}


		
		
		
		
		
		
		
		
		//***************************IN-Complete *****************
		//Here u have got all details of dates -- now u need to read each of its CLASS and make sure Available is there. Then get the info.
		//
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//System.out.println(elements);
		
		harisha.findElementByXPath("//input[contains(@id='acmRoom0')]").click();
		CallSleep();
		harisha.findElementByXPath("//input[contains(@name,'tickets')]").click();
		harisha.findElementByXPath("//input[contains(@name,'tickets')]").sendKeys(new String[]{"2"});
		CallSleep();
		harisha.findElementByXPath("//button[@id='sevacal_continue']").click();
		CallSleep();
		harisha.findElementByXPath("//div[contains(@class,'table_tr ng-scope')]//input[contains(@placeholder,'Enter Name')]").click();
		harisha.findElementByXPath("//div[contains(@class,'table_tr ng-scope')]//input[contains(@placeholder,'Enter Name')]").sendKeys(new String[]{"Gururaja Rao"});
		CallSleep();
		harisha.findElementByXPath("//input[contains(@name,'age1')]").click();
		harisha.findElementByXPath("//input[contains(@name,'age1')]").sendKeys(new String[]{"65"});
		CallSleep();
		harisha.findElementByXPath("//select[@id='proofS1']").click();
		harisha.findElementByXPath("//option[contains(@value,'string:AadharCard')]").click();
		harisha.findElementByXPath("//input[@id='proofId1']").click();
		harisha.findElementByXPath("//input[@id='proofId1']").sendKeys(new String[]{"233713154124"});
		CallSleep();
		harisha.findElementByXPath("//button[@id='sevapilgrim_continue']").click();
		CallSleep();
		harisha.findElementByXPath("//input[@id='HDFC']").click();
		harisha.findElementByXPath("//button[@id='sevapay_paynow']").click();
		
	}
	
	
	//MAIN Method.
	public static void main(String[] args)
	{

		TTD_Kalyantostava_Code();
	}
}