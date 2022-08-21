package Admin;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;  

public class Frame extends JFrame implements ActionListener{
	
	JPanel text_intro = new JPanel();
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	JPanel fpanel = new JPanel();
	JPanel controls_categories = new JPanel();
	JPanel controls_products = new JPanel();
	JPanel test = new JPanel();
	JPanel text_intro_products = new JPanel();
	JPanel pup = new JPanel();
	
	JLabel label_categories = new JLabel("Categories");
	JLabel label_products = new JLabel("Products");
	
	JScrollPane scategories = new JScrollPane(fpanel);
	
	ArrayList <Categories> acategories = new ArrayList <Categories>();
	
	JButton new_category = new JButton("New category");
	JButton new_product = new JButton("New product");
	JButton delete_category = new JButton("Delete category");
	JButton back = new JButton("<<-");
	
	JComboBox<String> search_bar_categories = new JComboBox<String>();
	JComboBox<String> search_bar_products = new JComboBox<String>();
	
	int selected_category = 0;
	
	Frame(){
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,800);
		
		controls_products.setMaximumSize(new Dimension(800,30));
		controls_products.setLayout(new BoxLayout(controls_products,BoxLayout.LINE_AXIS));
		text_intro_products.setLayout(new BorderLayout());
		text_intro_products.setMaximumSize(new Dimension(800,30));
		new_product.addActionListener(this);
		new_product.setFocusable(false);
		back.addActionListener(this);
		back.setFocusable(false);
		label_products.setHorizontalAlignment(JLabel.CENTER);
		label_products.setFont(new Font("Serif", Font.PLAIN, 20));
	
		
		controls_products.add(new_product);
		controls_products.add(Box.createHorizontalGlue());
		controls_products.add(search_bar_products);
		
		search_bar_categories.addActionListener(this);
		search_bar_products.addActionListener(this);
		
		first();	
	}
	
	public void first() {
		
		label_categories.setFont(new Font("Serif", Font.PLAIN, 20));
		fpanel.setLayout(new BoxLayout(fpanel,BoxLayout.Y_AXIS));
		controls_categories.setMaximumSize(new Dimension(800,30));
		controls_categories.setLayout(new BoxLayout(controls_categories,BoxLayout.LINE_AXIS));
		text_intro.setMaximumSize(new Dimension(800,30));
		new_category.addActionListener(this);
		new_category.setFocusable(false);
		
		for(int i = 0 ; i < acategories.size() ; i++) {
			fpanel.add(acategories.get(i).pcategory);
			acategories.get(i).bcancel.addActionListener(this);
		}
		
		fpanel.add(text_intro);
		fpanel.add(controls_categories);
		text_intro.add(label_categories);
		
		controls_categories.add(new_category);
		controls_categories.add(Box.createHorizontalGlue());
		controls_categories.add(search_bar_categories);
		this.getContentPane().add(scategories);
		search_bar_categories.addItem("--Search a product--");
		//SwingUtilities.updateComponentTreeUI(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == new_category) {
			add_category();
		}
		if(e.getSource() == new_product) {
			String product = JOptionPane.showInputDialog("New name for product");
			if((product != null) && (product.length() > 0) && (product.charAt(0) != ' ')) {
				if(check_products(product) == false) {
					acategories.get(selected_category).add_product(product);
					fpanel.add(acategories.get(selected_category).get_panel_at(acategories.get(selected_category).size_products() - 1));
					acategories.get(selected_category).get_cancel_button(acategories.get(selected_category).size_products() - 1).addActionListener(this);
					acategories.get(selected_category).get_stock_button(acategories.get(selected_category).size_products() - 1).addActionListener(this);
					acategories.get(selected_category).get_details_button(acategories.get(selected_category).size_products() - 1).addActionListener(this);
					acategories.get(selected_category).get_cancel_button(acategories.get(selected_category).size_products() - 1).setFocusable(false);
					acategories.get(selected_category).get_stock_button(acategories.get(selected_category).size_products() - 1).setFocusable(false);
					acategories.get(selected_category).get_details_button(acategories.get(selected_category).size_products() - 1).setFocusable(false);
					SwingUtilities.updateComponentTreeUI(this);
					search_bar_categories.addItem(acategories.get(selected_category).getProduct(acategories.get(selected_category).size_products() - 1).getName());
					search_bar_products.addItem(acategories.get(selected_category).getProduct(acategories.get(selected_category).size_products() - 1).getName());
				}
			}
		}
		
		if(e.getSource() == back) {
			fpanel.removeAll();
			fpanel.add(text_intro);
			fpanel.add(controls_categories);
			selected_category = 0;
			for(int i = 0 ; i < acategories.size() ; i++) {
				fpanel.add(acategories.get(i).pcategory);
			}
			search_bar_products.removeAllItems();
			SwingUtilities.updateComponentTreeUI(this);
		}
	if(acategories.size() != 0) {
		for(int i = 0; i < acategories.get(selected_category).size_products() ; i++) {
			if(e.getSource() == acategories.get(selected_category).get_cancel_button(i)) {
				fpanel.remove(acategories.get(selected_category).get_panel_at(i));
				search_bar_products.removeItem(acategories.get(selected_category).getProduct(i).getName());
				search_bar_categories.removeItem(acategories.get(selected_category).getProduct(i).getName());
				acategories.get(selected_category).delete_product(i);
				SwingUtilities.updateComponentTreeUI(this);
			}
			
			if(acategories.get(selected_category).size_products() != 0 ) {
				if (e.getSource() == acategories.get(selected_category).get_stock_button(i)) {
					String number = JOptionPane.showInputDialog("Insert a number");
				while (number.matches("[0-9]+") == false) {
					number = JOptionPane.showInputDialog("Insert a number");
				}
				int num = 0;
				 try{
					 num = Integer.parseInt(number);
			        }
			        catch (NumberFormatException ex){
			            ex.printStackTrace();
			        }
				 	acategories.get(selected_category).add_stock(num, i);
				 	acategories.get(selected_category).getProduct(i).setstock();
				 	acategories.get(selected_category).getProduct(i).setDateStock();
				}
				if (e.getSource() == acategories.get(selected_category).get_details_button(i)) {
					 JOptionPane.showOptionDialog(null, "Stock: " + acategories.get(selected_category).getProduct(i).getCount() + "item(s) left \n"
					 											+ "Sales: " + acategories.get(selected_category).getProduct(i).getSales() + "item(s) sold \n"
					 											+ "Last time adding stock: " + acategories.get(selected_category).getProduct(i).getDateStock() + " \n"
					 											+ "Last time see details: " + acategories.get(selected_category).getProduct(i).getDatesee() + " \n","Details", JOptionPane.DEFAULT_OPTION ,JOptionPane.INFORMATION_MESSAGE, null, null, null) ;
						 acategories.get(selected_category).getProduct(i).setDatesee();
					}
				}
			}
	}
		
		for(int i  = 0 ; i < acategories.size() ; i++) {
			if(e.getSource() == acategories.get(i).bcategory) {
				selected_category = i;
				select_category();
				SwingUtilities.updateComponentTreeUI(this);
			}
			if(e.getSource() == acategories.get(i).bcancel) {
				for(int j = 0 ; j < acategories.get(i).size_products() ; j++) {
					search_bar_categories.removeItem(acategories.get(i).getProduct(j).getName());
				}
				fpanel.remove(acategories.get(i).pcategory);
				acategories.remove(i);
				SwingUtilities.updateComponentTreeUI(this);
			}
		}
		if(e.getSource() == search_bar_categories) {
			String name = (String) search_bar_categories.getSelectedItem();
			for(int i = 0 ; i < acategories.size() ; i++) {
				for(int j = 0 ; j < acategories.get(i).size_products() ; j++) {
					if (acategories.get(i).getProduct(j).getName() == name) {
						selected_category = i;
						select_category();
					}
				}
			}
		}
	}
	
	public void add_category() {
		String category = JOptionPane.showInputDialog("New name for category");
		if((category != null) && (category.length() > 0) && (category.charAt(0) != ' ')) {
			if(check_categoeies(category) == false) {
				acategories.add(new Categories(category));
				fpanel.add(acategories.get(acategories.size() - 1).pcategory);
				acategories.get(acategories.size() - 1).bcancel.addActionListener(this);
				acategories.get(acategories.size() - 1).bcancel.setFocusable(false);;
				acategories.get(acategories.size() - 1).bcategory.addActionListener(this);
				acategories.get(acategories.size() - 1).bcategory.setFocusable(false);
				SwingUtilities.updateComponentTreeUI(this);
			}
		}
	}
	
	public void select_category() {
		
		fpanel.removeAll();
		search_bar_products.addItem("--Search a product--");
		text_intro_products.add(back,BorderLayout.WEST);
		//label_products.add(Box.createHorizontalGlue());
		text_intro_products.add(label_products,BorderLayout.CENTER);
		
		fpanel.add(text_intro_products);
		fpanel.add(controls_products);
		
		for(int i = 0 ; i < acategories.get(selected_category).size_products() ; i++) {
			fpanel.add(acategories.get(selected_category).get_panel_at(i));
			search_bar_products.addItem(acategories.get(selected_category).getProduct(i).getName());
			//System.out.println(acategories.get(selected_category).getProduct(i).getName());
		}
		
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public boolean check_categoeies(String name) {
		for(int i = 0 ; i < acategories.size(); i++) {
			if(acategories.get(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean check_products(String name) {
		for(int i = 0 ; i < acategories.get(selected_category).size_products(); i++) {
			if(acategories.get(selected_category).getProduct(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
}