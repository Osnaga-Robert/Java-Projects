package Admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Categories {
	private String name;
	private ArrayList <Products> products = new ArrayList <Products> ();
	public JPanel pcategory;
	public JButton bcategory;
	public JButton bcancel;
	
	public Categories(String name) {
		this.name = name;
		this.pcategory = new JPanel();
		this.bcancel = new JButton("X");
		this.bcategory = new JButton(name);//+ " " + this.products.get(this.products.size() - 1).getCount() + "item(s) left");
		this.pcategory.setLayout(new BorderLayout());
		this.pcategory.add(this.bcategory,BorderLayout.CENTER);
		this.pcategory.add(this.bcancel,BorderLayout.EAST);
		this.pcategory.setMaximumSize(new Dimension(800,30));
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
