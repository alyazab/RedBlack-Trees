package Dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import RedBlackTrees.RBTree;

public class EnDictionary {

	public static RBTree<String> Dictionary;
	private BufferedReader br;

	public EnDictionary() {
		
	}
	
	public void load(String fileName) {
		try {
			loadFileToTree(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int printSize() {
		return Dictionary.printTreeSize();
	}
	
	public int printHeight() {
		return Dictionary.printTreeHeight();
	}
	
	public boolean insertWord(String word) {
		return(Dictionary.Insert(word));	
	}
	
	public boolean searchWord(String Word) {
		if(Dictionary.searchRB(Word) != null)
			return true;
		return false;
	}
	
	public void loadFileToTree(String fileName) throws FileNotFoundException {
		br = new BufferedReader(new FileReader(fileName + ".txt"));
		try {
			StringBuilder sb = new StringBuilder();
			String word = br.readLine();
			
			sb.append(word);
			sb.append(System.lineSeparator());
			
			word = br.readLine();			

			Dictionary = new RBTree<String>(word);
			
			sb.append(word);
			sb.append(System.lineSeparator());
			
			word = br.readLine();
			
			while(word != null) {
				Dictionary.Insert(word);

				sb.append(word);
				sb.append(System.lineSeparator());
				
				word = br.readLine();				
			}
			
		} catch (Exception e) {
			   e.printStackTrace();
		}
	}
}
