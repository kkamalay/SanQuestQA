package BhaktaPackage;

import java.util.List;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;


public class WebTable {

	static WebDriver bhakta;
	
	@Given("^website \"([^\"]*)\"$")
	public void website(String url) throws Throwable {
		
		WebDriverManager.chromedriver().setup();
		bhakta=new ChromeDriver();
		
		bhakta.manage().window().fullscreen();
		bhakta.manage().deleteAllCookies();
		bhakta.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	   bhakta.get(url);//this is the string (argument)url from feature file.no need to hard qoute and argument is always in qoutation
	}

	@When("^i pass \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_pass_and(String userN, String pass) throws Throwable {
	   
		bhakta.findElement(By.name("userName")).sendKeys(userN);
		bhakta.findElement(By.name("password")).sendKeys(pass);
		bhakta.findElement(By.name("login")).click();;
	}

	@Then("^i should get homepage \"([^\"]*)\"$")
	public void i_should_get_homepage(String Epectedtitle) throws Throwable {
	   
		String Ptitle=bhakta.getTitle();
		
		//Assert.assertEquals(Epectedtitle, Ptitle);
		System.out.println(Ptitle);
	}

	@When("^i pass the below data on homePage$")
	public void i_pass_the_below_data_on_homePage(DataTable table) throws Throwable {
	    
	    List<List<String>> ls=table.raw();
	    
	   
	    
	    WebElement dropPassenger= bhakta.findElement(By.name("passCount")); 
	    //.sendKeys(ls.get(0).get(0)) by datatable for above code
	    //did not use because any way way passing value from datatable
	    
	    Select drop=new Select(dropPassenger);
	    drop.selectByVisibleText(ls.get(0).get(0));
	    
	  bhakta.findElement(By.name("fromPort")).sendKeys(ls.get(0).get(1));
	  bhakta.findElement(By.name("fromMonth")).sendKeys(ls.get(0).get(2));
	  bhakta.findElement(By.name("toPort")).sendKeys(ls.get(0).get(3));
	  bhakta.findElement(By.name("toMonth")).sendKeys(ls.get(0).get(4));
	  
	  //right here trouble on using datatable//no use of argument
	  bhakta.findElement(By.xpath("//input[@value='Business']")).click();;
	  
	  
	  
	  bhakta.findElement(By.name("toPort")).sendKeys(ls.get(0).get(3));
	   bhakta.findElement(By.xpath("//select[@name='airline']")).sendKeys(ls.get(0).get(6));
	   
	 
	   Thread.sleep(5000);
	   
	 
	  bhakta.findElement(By.name("findFlights")).click();
	  bhakta.close();
	}


	
	
}
