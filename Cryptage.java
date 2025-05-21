package escapeGame;

public class Cryptage {
	private String message;
	private int decalage;
	
	public Cryptage(String message, int decalage) {
		this.message=message;
		this.decalage=decalage;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public int getDecalage() {
		return this.decalage;
	}
	
	
	public String decalageModifier() {
		char [] chaine = this.message.toCharArray();
		for(int i=0;i<message.length();i++) {
			if (chaine[i]!=' ') {
				if ((int)chaine[i]+decalage>122) {
					chaine[i] = (char)((int)chaine[i]+(decalage-26)%26);
				}
				else {
					chaine[i] = (char)((int)chaine[i]+decalage%26);
				}
			}
		}
		String chaineCodee = new String (chaine);
		return chaineCodee;
		
	}
}
