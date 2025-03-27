package TestcasesByEpics;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public class generator {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		randomAlphaNumericTokens(60);
		
//		System.out.println("Numeric: "+RandomValueGenerator(10, "Numeric"));
//		System.out.println("Character: "+RandomValueGenerator(10, "Character"));
//		System.out.println("Alpha-Numeric: "+RandomValueGenerator(10, "alphanumeric"));
//		System.out.println("Default: "+RandomValueGenerator(10, "alphanumericsss"));
		
		PropertyFileReader();

	}
	
	static void PropertyFileReader() throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\config.properties");
		properties.load(fis);
		String browser= properties.getProperty("browser");
		System.out.println("Browser: "+browser);
		String url = properties.getProperty("URL");
		System.out.println("URL: "+url);
	}
	
	/*
	 * static String randomCharacters(int lengthOfString) { String reqRandom=
	 * UUID.randomUUID().toString();
	 * 
	 * reqRandom = reqRandom.replace("-", ""); reqRandom= reqRandom.substring(0,
	 * lengthOfString).toUpperCase(); //System.out.println(reqRandom); return
	 * reqRandom; }
	 */
	
	static void randomAlphaNumericTokens(int length) {
		String token= RandomStringUtils.random(length, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
		System.out.println("Token: "+token);		
	}
	
	static String RandomValueGenerator(int length, String type) {
		SecureRandom random = new SecureRandom();		
		type=type.toLowerCase();
		if(length<=0) {
			return "Invalid Length";
		}
		
		switch (type) {
		//Numeric Value Generator
		case "numeric":
			String numbers = "0123456789";
			StringBuilder numericSB = new StringBuilder();
			for(int i=0; i<length; i++) {
				int randomValue=random.nextInt(numbers.length());
				numericSB.append(numbers.charAt(randomValue));
			}
			//System.out.println("Random Number: "+numericSB.toString());
			return numericSB.toString();
		//Characters Value Generator	
		case "character":
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			StringBuilder characterSB = new StringBuilder();
			for(int i=0; i<length; i++) {
				int randomValue= random.nextInt(characters.length());
				characterSB.append(characters.charAt(randomValue));
			}
			//System.out.println("Random Number: "+characterSB.toString());
			return characterSB.toString();
		//Alpha-Numeric Value Generator		
		case "alphanumeric":
			String alphanumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			StringBuilder alphanumericSB = new StringBuilder();
			for(int i=0; i<length; i++) {
				int randomValue= random.nextInt(alphanumeric.length());
				alphanumericSB.append(alphanumeric.charAt(randomValue));
			}
			//System.out.println("Random Number: "+alphanumericSB.toString());
			return alphanumericSB.toString();
			
		default:
			return "Invalid Type Provided";
		}
		
		
	}

}
