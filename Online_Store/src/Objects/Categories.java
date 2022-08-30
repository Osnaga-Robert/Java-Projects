package Objects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Categories implements Serializable{
	private String name;
	private ArrayList <Products> products = new ArrayList <Products> ();
	transient public JPanel pcategory;
	transient public JButton bcategory;
	transient public JButton bcancel;
	
	public Categories(String name) {
		this.name = name;
	}
	
	public void categories_panel_admin() {
		this.pcategory = new JPanel();
		this.bcancel = new JButton("X");
		this.bcategory = new JButton(name);
		this.bcancel.setFocusable(false);
		this.bcategory.setFocusable(false);
		this.pcategory.setLayout(new BorderLayout());
		this.pcategory.add(this.bcategory,BorderLayout.CENTER);
		this.pcategory.add(this.bcancel,BorderLayout.EAST);
		this.pcategory.setMaximumSize(new Dimension(800,30));
	}
	
	public void categories_panel_user() {
		this.pcategory = new JPanel();
		this.bcategory = new JButton(name);
		this.bcategory.setFocusable(false);
		this.pcategory.setLayout(new BorderLayout());
		this.pcategory.add(this.bcategory,BorderLayout.CENTER);
		this.pcategory.setMaximumSize(new Dimension(900,30));
	}
	
	public String getName() {
		return name;	
	}
	
	public void changeName(String newname) {
		this.name = newname;
	}
	
	public void add_product(String name_product) {
		Products product = new Products(name_product);
		product.changeName(name_product);
		product.changeCount(0);
		this.products.add(product);
	}
	
	public JPanel get_panel_at(int i) {
		return products.get(i).pproduct;
	}
	
	public int size_products() {
		return products.size();
	}
	
	public JButton get_cancel_button(int i) {
		return products.get(i).bcancel;
	}
	
	public void delete_product (int i) {
		products.remove(i);
	}
	
	public JButton get_details_button(int i) {
		return products.get(i).details;
	}
	
	public JButton get_stock_button(int i) {
		return products.get(i).add_stock;
	}
	
	public void add_stock(int number, int i) {
		products.get(i).addCount(number);
	}
	
	public Products getProduct(int i) {
		return products.get(i);
	}
}
