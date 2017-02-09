package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Plansza {

	private int iloscTrafien;
	private JFrame base;
	private JPanel plansza;
	private char[] tablica;
	private JToggleButton buttons[];
	private Oznaczenie j; 
	private boolean empate;
	private static int reg[]={0,0,0};

	public Plansza(){
		start();
	}
	
	private void start(){
		empate=true;
		iloscTrafien=0;
		buttons=new JToggleButton[9];
		j=new Oznaczenie('X');
		tablica=new char[9];
		
		base=new JFrame(" Tic tac toe ");
		base.setSize(350,350);
		base.setLocation(400,100);
		base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		plansza=new JPanel();
		plansza.setLayout(new GridLayout(3,3));

		for(int i=0;i<9;i++){
			buttons[i]=new JToggleButton();
			buttons[i].setFont(new Font("Lucida Sans Unicode",Font.BOLD,80));
			buttons[i].addActionListener(new EventoListener());
			plansza.add(buttons[i]);
		}
		
		base.getContentPane().add(plansza);
		base.setVisible(true);
	}
	
	class EventoListener extends WindowAdapter implements ActionListener {
		
		public void windowClosing(WindowEvent e) {
			restart();
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof JToggleButton)
				verificar((JToggleButton)e.getSource());
		}

		
	}
	
	private void verificar(JToggleButton source) {
		// TODO Auto-generated method stub
		
		char[][] board = new char[3][3];
		//inicjalizacja
//		for (int x=0; x<=2; x++) {
//			for (int y=0; y<=2; y++) {
//				board[x][y] = null;
//			}
//		}

		int pos=-1;
		for(int i=0;i<9 && pos==-1;i++){
			if(buttons[i]==source)
				pos=i;
		}
		
		switch (pos) {
		case 0 : board[0][0] = j.getOznaczenie(); break;
		case 1 : board[0][1] = j.getOznaczenie(); break;
		case 2 : board[0][2] = j.getOznaczenie(); break;
		case 3 : board[1][0] = j.getOznaczenie(); break;
		case 4 : board[1][1] = j.getOznaczenie(); break;
		case 5 : board[1][2] = j.getOznaczenie(); break;
		case 6 : board[2][0] = j.getOznaczenie(); break;
		case 7 : board[2][1] = j.getOznaczenie(); break;
		case 8 : board[2][2] = j.getOznaczenie(); break;
		}
		
		source.setText(j.getOznaczenie()+"");
		
//		for (int x=0; x<=2; x++) {
//			for (int y=0; y<=2; y++) {
//				if (board[x][y] != ) {
//					if (board[x][y] == board[x][y+1]) {
//						if (board[x][y+1] == board[x][y+2]) {
//							System.out.println("tak1");
//							board[x][y] = null;
//							break;
//						} else {
//							break;
//						}
//					} else {
//						break;
//					}
//				}
//			}
//		}
		
		j.nextPlayer();
	}
	
	private void restart(){

		if(empate==true)
			reg[2]++;
		
		base.setVisible(false);
		
		base=null;
		plansza=null;
		buttons=null;
		tablica=null;
		j=null;
		
		start();
	}
	
	public static void main(String args[]){
		new Plansza();
	}
	
private void zwyciestwo(char c){
		
		if(c=='X')
			reg[0]++;
		else
			reg[1]++;
		
		empate=false;
		
		JFrame panel=new JFrame(" Tic tac to");
		
		JPanel gpanel=new JPanel();
		gpanel.setLayout(new GridLayout(4,0));
		gpanel.setBackground(Color.white);

		JLabel l0=new JLabel();
		l0.setFont(new Font("Lucida Sans Unicode",Font.BOLD,14));
		l0.setText(" Oznaczenie "+c+" wygral!");
		
		JLabel l1=new JLabel();
		l1.setFont(new Font("Lucida Sans Unicode",Font.BOLD,14));
		l1.setText(" Oznaczenie X: "+reg[0]+" zwyciestw. ");
		
		JLabel l2=new JLabel();
		l2.setFont(new Font("Lucida Sans Unicode",Font.BOLD,14));
		l2.setText(" Oznaczenie O: "+reg[1]+" zwyciestw. ");

		JLabel l3=new JLabel();
		l3.setFont(new Font("Lucida Sans Unicode",Font.BOLD,14));
		l3.setText(" Podsumowanie: "+reg[2]);
		
		gpanel.add(l0);
		gpanel.add(l1);
		gpanel.add(l2);
		gpanel.add(l3);

		panel.getContentPane().add(gpanel);
		
		panel.setSize(250,100);
		panel.setLocation(900,500);
		
		base.setEnabled(false);
		panel.setVisible(true);
		
		panel.addWindowListener(new EventoListener());
	}
}
