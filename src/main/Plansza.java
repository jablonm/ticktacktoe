package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;

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
	public Hashtable<Integer, Character> wypelnionaPlansza = new Hashtable<Integer, Character>();

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
			wypelnionaPlansza.put(i, ' ');
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
		for (int x=0; x<=2; x++) {
			for (int y=0; y<=2; y++) {
				if (board[x][y] != 'X' || board[x][y] != 'Y') {
					board[x][y] = ' ';
				}
			}
		}

		int pos=-1;
		for(int i=0;i<9 && pos==-1;i++){
			if(buttons[i]==source)
				pos=i;
				wypelnionaPlansza.put(pos, j.getOznaczenie());
		}
	
		source.setText(j.getOznaczenie()+"");
	
		if (wypelnionaPlansza.get(0) != ' ') {
			if (wypelnionaPlansza.get(0) == wypelnionaPlansza.get(1) 
					&& wypelnionaPlansza.get(0) == wypelnionaPlansza.get(2)) {
				System.out.println("Wygrywa: 1, 2, 3");
			} else if (wypelnionaPlansza.get(0) == wypelnionaPlansza.get(3) 
					&& wypelnionaPlansza.get(0) == wypelnionaPlansza.get(6)) {
				System.out.println("Wygrywa: 1, 4, 7");
			} else if (wypelnionaPlansza.get(0) == wypelnionaPlansza.get(4) 
					&& wypelnionaPlansza.get(0) == wypelnionaPlansza.get(8)) {
				System.out.println("Wygrywa: 1, 5, 9");
			}
		}
		if (wypelnionaPlansza.get(1) != ' ') {
			if (wypelnionaPlansza.get(1) == wypelnionaPlansza.get(4) 
					&& wypelnionaPlansza.get(1) == wypelnionaPlansza.get(7)) {
				System.out.println("Wygrywa: 2, 5, 8");
			}
		}
		if (wypelnionaPlansza.get(2) != ' ') {
			if (wypelnionaPlansza.get(2) == wypelnionaPlansza.get(4) 
					&& wypelnionaPlansza.get(2) == wypelnionaPlansza.get(6)) {
				System.out.println("Wygrywa: 2, 5, 8");
			}
			if (wypelnionaPlansza.get(2) == wypelnionaPlansza.get(5) 
					&& wypelnionaPlansza.get(2) == wypelnionaPlansza.get(8)) {
				System.out.println("Wygrywa: 2, 5, 8");
			}
		}
		if (wypelnionaPlansza.get(3) != ' ') {
			if (wypelnionaPlansza.get(3) == wypelnionaPlansza.get(4) 
					&& wypelnionaPlansza.get(3) == wypelnionaPlansza.get(5)) {
				System.out.println("Wygrywa: 4, 5, 6");
			}
		}
		if (wypelnionaPlansza.get(6) != ' ') {
			if (wypelnionaPlansza.get(6) == wypelnionaPlansza.get(7) 
					&& wypelnionaPlansza.get(6) == wypelnionaPlansza.get(8)) {
				System.out.println("Wygrywa: 7, 8, 9");
			}
		}
		
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
