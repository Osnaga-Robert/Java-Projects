package User_Options;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Class_cart {
	private int stock;
	private int selected_category;
	private int position;
	private String name;
	JPanel fPanel;
	JPanel fhpanel;
	JPanel shpanel;
	TextField textField;
	JLabel ltext;
	JLabel nlabel;
	
	Class_cart(int stock,int selected_category,int position,String name){
		this.stock = stock;
		this.setSelected_category(selected_category);
		this.setPosition(position);
		this.setName(name);
	}

	public String getName() {
		return name;
	}
	
	public JPanel add_panel() {
		this.fPanel = new JPanel();
		this.fhpanel = new JPanel();
		this.shpanel = new JPanel();
		this.textField = new TextField();
		this.ltext = new JLabel("item(s)");
		ltext.setHorizontalAlignment(JLabel.LEFT);
		this.nlabel = new JLabel(this.name);
		this.nlabel.setHorizontalAlignment(JLabel.CENTER);
		this.fPanel.setMaximumSize(new Dimension(900,30));
		this.fPanel.setLayout(new GridLayout(1,2));
		this.fhpanel.setBackground(Color.YELLOW);
		this.shpanel.setBackground(Color.YELLOW);
		
		this.shpanel.setLayout(new GridLayout(1,2));
		
		this.fhpanel.add(this.nlabel);
		this.shpanel.add(this.ltext);
		this.shpanel.add(this.textField);
		
		this.fPanel.add(fhpanel);
		this.fPanel.add(shpanel);
		
		return fPanel;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getSelected_category() {
		return selected_category;
	}

	public void setSelected_category(int selected_category) {
		this.selected_category = selected_category;
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
