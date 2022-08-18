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
	
	JLabel label_categories = new JLabel("Categories");
	JLabel label_products = new JLabel("Products");
	
	JScrollPane scategories = new JScrollPane(fpanel);
	
	ArrayList <Categories> acategories = new ArrayList <Categories>();
	
	JButton new_category = new JButton("New category");
	JButton new_product = new JButton("New product");
	JButton delete_category = new JButton("Delete category");
	
	JComboBox search_bar = new JComboBox();
	
	int selected_category = 0;
	
	Frame(){
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,800);
		first();	
	}
	
	public void first() {
		label_categories.setFont(new Font("Serif", Font.PLAIN, 20));
		fpanel.setLayout(new BoxLayout(fpanel,BoxLayout.Y_AXIS));
		controls_categories.setMaximumSize(new Dimension(800,30));
		controls_categories.setLayout(new BoxLayout(controls_categories,BoxLayout.LINE_AXIS));
		text_intro.setMaximumSize(new Dimension(800,30));
		new_category.addActionListener(this);
		
		for(int i = 0 ; i < acategories.size() ; i++) {
			fpanel.add(acategories.get(i).pcategory);
			acategories.get(i).bcancel.addActionListener(this);
		}
		
		fpanel.add(text_intro);
		fpanel.add(controls_categories);
		text_intro.add(label_categories);
		
		controls_categories.add(new_category);
		controls_categories.add(Box.createHorizontalGlue());
		controls_categories.add(search_bar);
		this.getContentPane().add(scategories);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == new_category) {
			add_category();
		}
		if(e.getSource() == new_product) {
			String product = JOptionPane.showInputDialog("New name for product");
			acategories.get(selected_category).add_product(product);
			fpanel.add(acategories.get(selected_category).get_panel_at(acategories.get(selected_category).size_products() - 1));
			acategories.get(selected_category).get_cancel_button(acategories.get(selected_category).size_products() - 1).addActionListener(this);
			acategories.get(selected_category).get_stock_button(acategories.get(selected_category).size_products() - 1).addActionListener(this);
			acategories.get(selected_category).get_details_button(acategories.get(selected_category).size_products() - 1).addActionListener(this);
			SwingUtilities.updateComponentTreeUI(this);
		}
		
		for(int i = 0; i < acategories.get(selected_category).size_products() ; i++) {
			if(e.getSource() == acategories.get(selected_category).get_cancel_button(i)) {
				fpanel.remove(acategories.get(selected_category).get_panel_at(i));
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
					 JOptionPane.showMessageDialog(null, "CEVA SMBPL",getTitle(), JOptionPane.INFORMATION_MESSAGE) ;
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
				fpanel.remove(acategories.get(i).pcategory);
				acategories.remove(i);
				SwingUtilities.updateComponentTreeUI(this);
			}
		}
	}
	
	public void add_category() {
		String category = JOptionPane.showInputDialog("New name for category");
		acategories.add(new Categories(category));
		fpanel.add(acategories.get(acategories.size() - 1).pcategory);
		acategories.get(acategories.size() - 1).bcancel.addActionListener(this);
		acategories.get(acategories.size() - 1).bcategory.addActionListener(this);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void select_category() {
		fpanel.removeAll();
		controls_products.setMaximumSize(new Dimension(800,30));
		controls_products.setLayout(new BoxLayout(controls_products,BoxLayout.LINE_AXIS));
		text_intro_products.setMaximumSize(new Dimension(800,30));
		new_product.addActionListener(this);
		
		controls_products.add(new_product);
		controls_products.add(Box.createHorizontalGlue());
		controls_products.add(search_bar);
		
		text_intro_products.add(label_products);
		fpanel.add(text_intro_products);
		fpanel.add(controls_products);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
}
