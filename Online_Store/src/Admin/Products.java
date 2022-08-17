package Admin;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Products {
	private String name;
	private int count = 0;
	public JButton bproduct;
	public JPanel pproduct;
	public JButton bcancel;
	
	public Products(String name) {
		this.name = name;
		this.count = count;
		this.bproduct = new JButton(name);
		this.pproduct = new JPanel();
		this.bcancel = new JButton("X");
		this.pproduct.setLayout(new BorderLayout());
		this.pproduct.setMaximumSize(new Dimension(800,30));
		this.pproduct.add(bcancel,BorderLayout.EAST);
		this.pproduct.add(bproduct,BorderLayout.CENTER);
	}
	
	public String getName() {
		return name;
	}
	
	public int getCount() {
		return count;
	}
	
	public void changeName(String newname) {
		this.name = newname;
	}
	
	public void changeCount(int newcount) {
	this.count = newcount;
	}
}
