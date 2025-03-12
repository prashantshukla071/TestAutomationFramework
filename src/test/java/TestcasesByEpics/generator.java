package TestcasesByEpics;

import java.util.UUID;

public class generator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String token= randomCharacters(15);
		System.out.println("Token: "+token);

	}
	
	static String randomCharacters(int lengthOfString) {
		String reqRandom= UUID.randomUUID().toString();
		
		reqRandom = reqRandom.replace("-", "");
		reqRandom= reqRandom.substring(0, lengthOfString).toUpperCase();
		//System.out.println(reqRandom);
		return reqRandom;
	}

}
