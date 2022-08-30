package Objects;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.login.AppConfigurationEntry;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Products implements Serializable{
	transient SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private String name;
	private int count = 0;
	private String dstock ;
	private String dseeDate ;
	private int sales = 0;
	private String sdetails;
	transient public JLabel lproduct;
	transient public JPanel pproduct;
	transient public JButton add_char;
	transient public JButton bcancel;
	transient public JButton details;
	transient public JButton add_stock;
	transient public JButton add_description;
	transient public JPanel pcontrols = new JPanel();
	transient public JLabel stock = new JLabel();
	transient public Date date = new Date();
	
	public Products(String name) {
		this.name = name;
		this.count = count;
	}
	
	public void products_panel_admin() {
		this.pcontrols = new JPanel();
		this.stock = new JLabel();
		this.lproduct = new JLabel(name + "->" + this.count + "item(s) left");
		this.pproduct = new JPanel();
		this.bcancel = new JButton("X");
		this.details = new JButton("Details");
		this.add_stock = new JButton("Stock-in");
		this.add_description = new JButton("Add description");
		this.pproduct.setLayout(new GridLayout(1,2));
		this.pproduct.setMaximumSize(new Dimension(800,30));
		this.lproduct.setFont(new Font("Serif", Font.PLAIN, 14));
		this.lproduct.setHorizontalAlignment(JLabel.CENTER);
		this.pproduct.setBackground(Color.YELLOW);
		this.pcontrols.setLayout(new GridLayout(1,4));
		this.stock.setFont(new Font("Serif", Font.PLAIN, 14));
		this.stock.setHorizontalAlignment(JLabel.CENTER);
		this.pcontrols.setBackground(Color.YELLOW);
		if(this.sdetails == null)
			this.sdetails = "No description yet";
		
		setstock();
		
		lproduct.setText(name + "->" + this.count + "item(s) left");
		this.pcontrols.add(stock);
		this.pcontrols.add(details);
		this.pcontrols.add(add_stock);
		this.pcontrols.add(bcancel);
		
		
		this.pproduct.add(lproduct,BorderLayout.CENTER);
		this.pproduct.add(pcontrols);
	}
	
	public void products_panel_user() {
		this.pcontrols = new JPanel();
		this.stock = new JLabel();
		this.lproduct = new JLabel();
		this.pproduct = new JPanel();
		this.details = new JButton("Details");
		this.add_char = new JButton("Add-char");
		
		this.pproduct.setLayout(new GridLayout(1,2));
		this.pproduct.setMaximumSize(new Dimension(900,30));
		this.lproduct.setFont(new Font("Serif", Font.PLAIN, 14));
		this.lproduct.setHorizontalAlignment(JLabel.CENTER);
		this.lproduct.setText(this.name);
		this.pproduct.setBackground(Color.YELLOW);
		this.pcontrols.setLayout(new GridLayout(1,4));
		this.stock.setFont(new Font("Serif", Font.PLAIN, 14));
		this.stock.setHorizontalAlignment(JLabel.CENTER);
		this.pcontrols.setBackground(Color.YELLOW);
		if(this.sdetails == null)
			this.sdetails = "No description yet";

		setstock();
		lproduct.setText(name);
		this.pcontrols.add(this.stock);
		this.pcontrols.add(this.details);
		this.pcontrols.add(this.add_char);
		
		this.pproduct.add(this.lproduct);
		this.pproduct.add(this.pcontrols);

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
	
	public void addCount(int addcount) {
		this.count += addcount;
	}
	
	public void setCount(int setcount) {
		this.count = setcount;
	}
	
	public int getSales () {
		return this.sales;
	}
	
	public void addSales(int adding) {
		this.sales += adding;
	}
	
	public void setDateStock() {
		this.dstock = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
	}
	
	public String getDateStock() {
		return this.dstock;
	}
	
	public void setDatesee() {
		this.dseeDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
	}
	
	public String getDatesee() {
		return this.dseeDate;
	}
	
	
	public void setstock() {
		if(this.count == 0) {
			stock.setText("No stock");
			stock.setForeground(Color.RED);
		}
		else {
			stock.setText("In stock");
			stock.setForeground(Color.GREEN);
		}
	}

	public String getSdetails() {
		return sdetails;
	}

	public void setSdetails(String sdetails) {
		this.sdetails = sdetails;
	}
	
}
