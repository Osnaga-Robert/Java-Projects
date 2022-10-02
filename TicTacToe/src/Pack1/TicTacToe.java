package Pack1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TicTacToe implements ActionListener{
	
	int x = 0;
	int o = 0;
	int start = 0;
	int jucator1 = 1;
	int jucator2 = 0;
	int full = 0;
	boolean check = false;

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel scor_setari = new JPanel();
	JPanel butoane = new JPanel();
	JLabel scris = new JLabel();
	JPanel tabela = new JPanel();
	JLabel scris1 = new JLabel();
	JLabel scor = new JLabel();
	JButton reset = new JButton();
	JButton new_game = new JButton();
	JButton[] casete = new JButton[9];
	JPanel bara_jos = new JPanel();
	
	
	TicTacToe(){
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(750,750);
		
		panel.setBackground(new Color(100,65,165));
		panel.setLayout(new BorderLayout());
		
		panel1.setBackground(new Color(100,65,165));
		
		scor_setari.setBackground(new Color(117, 221, 221));
		
		butoane.setBackground(new Color(45,50,56));
		butoane.setLayout(new GridLayout());
		
		scris.setText("X turn");
		scris.setFont(new Font("Serif",Font.BOLD,25));
		scris.setHorizontalAlignment(JLabel.CENTER);
		scris.setForeground(Color.WHITE);
		
		tabela.setLayout(new GridLayout(3,3));
		
		scris1.setText("TIC TAC TOE");
		scris1.setFont(new Font("Serif",Font.BOLD,50));
		scris1.setForeground(Color.WHITE);
		
		scor.setText("X won " + x + " times 0 won " + o + " times");
		scor.setFont(new Font("Serif",Font.ITALIC,15));
		scor.setForeground(new Color(87,89,93));
		
		
		reset.setText("Reset");
		reset.setBorder(null);
		reset.setFocusable(false);
		reset.setBackground(new Color(100,65,165));
		reset.setForeground(Color.white);
		reset.setEnabled(false);
		reset.addActionListener(this);
		
		new_game.setText("New game");
		new_game.setBorder(null);
		new_game.setFocusable(false);
		new_game.setBackground(new Color(100,65,165));
		new_game.setForeground(Color.WHITE);
		new_game.addActionListener(this);
		new_game.setEnabled(false);
		
		for(int i = 0 ; i < 9 ; i++) {
			casete[i] = new JButton();
			casete[i].setFocusable(false);
			casete[i].setBackground(new Color(36, 196, 207));
			casete[i].addActionListener(this);
		}
		
		bara_jos.setLayout(new GridLayout(0,2));
		
		
		frame.add(panel,BorderLayout.NORTH);
		frame.add(bara_jos,BorderLayout.SOUTH);
		panel.add(panel1,BorderLayout.NORTH);
		frame.add(tabela);
		
		for(int i = 0 ; i < 9 ; i++) {
			tabela.add(casete[i]);
		}
		panel1.add(scris1);
		bara_jos.add(scor_setari);
		bara_jos.add(butoane);
		panel.add(scris);
		
		butoane.add(reset);
		butoane.add(new_game);
		scor_setari.add(scor);

		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0 ; i < 9 ; i++) {
			if(e.getSource() == casete[i]) {
				if(jucator1 == 1) {
					if(casete[i].getText() =="") {
						casete[i].setText("X");
						casete[i].setFont(new Font("Consolas",Font.PLAIN,60));
						scris.setText("0 turn");
						casete[i].setForeground(new Color(100,65,165));
						jucator1 = 0;
						jucator2 = 1;
						check();
					}
				}
				if(jucator2 == 1) {
					if(casete[i].getText() =="") {
						casete[i].setText("0");
						scris.setText("X turn");
						casete[i].setFont(new Font("Consolas",Font.PLAIN,60));
						casete[i].setForeground(new Color(255,255,255));
						jucator1 = 1;
						jucator2 = 0;
						check();
					}
				}
				
			}
		}
		if(e.getSource() == new_game) {
			for(int i = 0 ; i < 9 ; i++) {
				casete[i].setText("");
				casete[i].setEnabled(true);
				casete[i].setBackground(new Color(36, 196, 207));
			}
			if(jucator1 == 1) {
				scris.setText("X Turn");
			}
			if(jucator2 == 1) {
				scris.setText("0 Turn");
			}
			new_game.setEnabled(false);
			reset.setEnabled(false);
			check = false;
			full = 0;
		}
		if(e.getSource() == reset) {
			x = 0;
			o = 0;
			scor.setText("X won " + x + " times 0 won " + o + " times");
			jucator1 = 1;
			jucator2 = 0;
		}
	}
	
	public void WinX(int a,int b,int c) {
		scris.setText("X won");
		reset.setEnabled(true);
		new_game.setEnabled(true);
		x++;
		for(int i  = 0; i < 9 ; i++) {
			casete[i].setEnabled(false);
			if(i == a || i == b || i ==c) {
				casete[i].setBackground(Color.GREEN);
			}
			else {
				casete[i].setBackground(Color.GRAY);
			}
		}
		scor.setText("X won " + x + " times 0 won " + o + " times");
		jucator1 = 1;
		jucator2 = 0;
		check = true;
	}
	
	public void Win0(int a,int b,int c) {
		scris.setText("0 won");
		reset.setEnabled(true);
		new_game.setEnabled(true);
		o++;
		for(int i  = 0; i < 9 ; i++) {
			casete[i].setEnabled(false);
			if(i == a || i == b || i ==c) {
				casete[i].setBackground(Color.GREEN);
			}
			else {
				casete[i].setBackground(Color.GRAY);
			}
		}
		scor.setText("X won " + x + " times 0 won " + o + " times");
		jucator1 = 0;
		jucator2 = 1;
		check = true;
	}
	
	public void Remiza() {
		scris.setText("No one won");
		reset.setEnabled(true);
		new_game.setEnabled(true);
		for(int i = 0 ; i < 9 ; i++) {
			casete[i].setEnabled(false);
		}
	}
	
	public void check() {
		full++;

		if(casete[0].getText() =="X" && casete[1].getText() =="X"  && casete[2].getText() =="X") {
			WinX(0,1,2);
		}
		if(casete[3].getText() =="X" && casete[4].getText() =="X"  && casete[5].getText() =="X") {
			WinX(3,4,5);
		}
		if(casete[6].getText() =="X" && casete[7].getText() =="X"  && casete[8].getText() =="X") {
			WinX(6,7,8);
		}
		if(casete[0].getText() =="X" && casete[3].getText() =="X"  && casete[6].getText() =="X") {
			WinX(0,3,6);
		}
		if(casete[1].getText() =="X" && casete[4].getText() =="X"  && casete[7].getText() =="X") {
			WinX(1,4,7);
		}
		if(casete[2].getText() =="X" && casete[5].getText() =="X"  && casete[8].getText() =="X") {
			WinX(2,5,8);
		}
		if(casete[0].getText() =="X" && casete[4].getText() =="X"  && casete[8].getText() =="X") {
			WinX(0,4,8);
		}
		if(casete[2].getText() =="X" && casete[4].getText() =="X"  && casete[6].getText() =="X") {
			WinX(2,4,6);
		}
		
		if(casete[0].getText() =="0" && casete[1].getText() =="0"  && casete[2].getText() =="0") {
			Win0(0,1,2);
		}
		if(casete[3].getText() =="0" && casete[4].getText() =="0"  && casete[5].getText() =="0") {
			Win0(3,4,5);
		}
		if(casete[6].getText() =="0" && casete[7].getText() =="0"  && casete[8].getText() =="0") {
			Win0(6,7,8);
		}
		if(casete[0].getText() =="0" && casete[3].getText() =="0"  && casete[6].getText() =="0") {
			Win0(0,3,6);
		}
		if(casete[1].getText() =="0" && casete[4].getText() =="0"  && casete[7].getText() =="0") {
			Win0(1,4,7);
		}
		if(casete[2].getText() =="0" && casete[5].getText() =="0"  && casete[8].getText() =="0") {
			Win0(2,5,8);
		}
		if(casete[0].getText() =="0" && casete[4].getText() =="0"  && casete[8].getText() == "0") {
			Win0(0,4,8);
		}
		if(casete[2].getText() =="0" && casete[4].getText() =="0"  && casete[6].getText() == "0") {
			Win0(2,4,6);
		}
		if(full == 9 && check == false) {
			Remiza();
		}
	}
}
