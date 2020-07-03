package Dictionary;

import java.util.Scanner;

public class Console {

	
	private static Scanner myObj;
	private static EnDictionary Dictionary = new EnDictionary();
    private static String fileName;
    private static String option;

	public static void main(String[] args) {
		load();
		
	    int size = Dictionary.printSize();
	    int height = Dictionary.printHeight();

	    System.out.println("\t\t\t\t\t\tDictionary: " + fileName +"\n\n\t\t\t\t\t\tHeight: " + height + " levels\n\n\t\t\t\t\t\tSize: " + size + " words\n\n\n***********************************************************************************************************************\n\n");
	    while(true) {
	    	System.out.println("(1)\tLoad another dictionary\n(2)\tLook up a word\n(3)\tInsert word\n(4)\tExit\n\nPlease choose a number\n");
	    	option = myObj.nextLine();
	    	switch(option) {
	    		case("1"): load(); break;
	    		case("2"): search(); break;
	    		case("3"): insert(); break;
	    		case("4"): return;
	    		
	    		default: System.out.println("Unavailable option\n");
	    	}
	    	size = Dictionary.printSize();
		    height = Dictionary.printHeight();
	    	
		    System.out.println("\n\t\t\t\t\t\tHeight: " + height + " levels\n\t\t\t\t\t\tSize: " + size + " words\n");
	    }
	}
	
	public static void load() {
		System.out.println("\nPlease enter file name:\n");
	    myObj = new Scanner(System.in);
	    
	    fileName = myObj.nextLine();
	    Dictionary.load(fileName);
	}
	
	public static void search() {
		System.out.println("\nPlease enter a word to search:\n");
	    
		myObj = new Scanner(System.in);
	    fileName = myObj.nextLine();
	    
	    if(Dictionary.searchWord(fileName))
	    	System.out.println("\nWord Found Successfully\n");
	    else
	    	System.out.println("\nWord Not Found\n");
	}	
	
	public static void insert() {
		System.out.println("\nPlease enter a word to insert:\n");
	    
		myObj = new Scanner(System.in);
	    fileName = myObj.nextLine();
	    
	    if(Dictionary.insertWord(fileName))
	    	System.out.println("\nWord already found\n");
	    else
	    	System.out.println("\nWord inserted Successfully\n");
	}
}