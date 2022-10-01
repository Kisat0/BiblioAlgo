package com.example.Utils;

public class Convertion {

    public static String reverse(String str) {
        char[] character = str.toCharArray();
	    int début = 0;
	    int fin = character.length-1;
	    char temp;
	    while(fin>début){
	        temp = character[début];
	        character[début] = character[fin];
	        character[fin] = temp;
	        fin--;
	        début++;
	    }
        String reverseString = new String (character);
        return reverseString;
    }

	public static boolean isNotNumeric(char a) {
		try {
			Character.getNumericValue(a);
			return false;
		} catch (Exception e) {
			return true;
		}
	}

}
