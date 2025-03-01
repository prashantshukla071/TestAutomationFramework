package nopCommerce.locators;

public class locators {
	//showcase of ENCAPSULATION implementation, where variable, their methods are grouped in a single entity

	private String productPage = "//span[normalize-space()='Product']";
	private String featuresInProduct = "//span[normalize-space()='Features']";
	
	
	
	//Getter methods to access instance variables
	public String getProductPage() {
		return productPage;
	}
	public String getFeaturesInProduct() {
		return featuresInProduct;
	}
	
	}