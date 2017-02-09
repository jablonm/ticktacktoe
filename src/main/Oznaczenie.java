package main;

public class Oznaczenie {
	
	private char ozn;

	public Oznaczenie(char j){
		this.ozn=j;
	}

	public char getOznaczenie(){
		return ozn;
	}

	public void nextPlayer(){
		if(this.ozn=='X')
			this.ozn='O';
		else
			this.ozn='X';
	}

}
