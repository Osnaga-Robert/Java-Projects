package User_Options;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Class_cart {
	private int stock;
	private int selected_category;
	private int position;
	private String name;
	JPanel fPanel;
	JPanel fhpanel;
	JPanel shpanel;
	JTextField textField;
	JLabel ltext;
	JLabel nlabel;
	JButton cancel;
	
	Class_cart(int stock,int selected_category,int position,String name){
		this.stock = stock;
		this.setSelected_category(selected_category);
		this.setPosition(position);
		this.setName(name);
		this.fPanel = new JPanel();
		this.fhpanel = new JPanel();
		this.shpanel = new JPanel();
		this.textField = new JTextField("1");
		this.ltext = new JLabel("item(s)");
		this.cancel = new JButton("X");
		this.ltext.setHorizontalAlignment(JLabel.CENTER);
		this.nlabel = new JLabel(this.name);
		this.nlabel.setHorizontalAlignment(JLabel.CENTER);
		this.fPanel.setMaximumSize(new Dimension(900,30));
		this.fPanel.setLayout(new GridLayout(1,2));
		this.fhpanel.setBackground(new Color(169,169,169));
		this.shpanel.setBackground(new Color(169,169,169));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();
			if( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
				e.consume();
			}
		}
	});
		
		this.shpanel.setLayout(new GridLayout(1,3));
		
		this.fhpanel.add(this.nlabel);
		this.shpanel.add(this.textField);
		this.shpanel.add(this.ltext);
		this.shpanel.add(this.cancel);
		
		this.fPanel.add(this.fhpanel);
		this.fPanel.add(this.shpanel);
	}

	public String getName() {
		return name;
	}
	
	public JPanel add_panel() {
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
