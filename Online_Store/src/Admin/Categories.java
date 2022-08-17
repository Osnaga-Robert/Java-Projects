package Admin;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Categories {
	private String name;
	private ArrayList <Products> products = new ArrayList <Products> ();
	
	public Categories(String name) {
		this.name = name;
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
}
