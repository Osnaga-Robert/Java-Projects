package User_Options;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Objects.*;

public class user_interface extends JFrame implements ActionListener{
	
	ArrayList<Categories> acategories = new ArrayList<Categories>();
	
	JPanel fpanel = new JPanel();
	JPanel ctpanel = new JPanel();
	JPanel ccontrols = new JPanel();
	JPanel pcontrols = new JPanel();
	JPanel ptpanel = new JPanel();
	
	JLabel ctlabel = new JLabel("Categories");
	JLabel ptlabel = new JLabel("Products");
	
	JButton cart = new JButton("Your char");
	JButton back = new JButton("<<-");
	
	JComboBox<String> search_bar_categories = new JComboBox<String>();
	JComboBox<String> search_bar_products = new JComboBox<String>();
	
	int selected_category = 0;
	
	public user_interface() {
		frame_settings();
		load_info();
		intro();
		only_once_settings();
	}
	
	public void frame_settings() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,800);
		//this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(fpanel);
	}
	
	public void load_info() {
		try {
			FileInputStream fis = new FileInputStream("Savings/Save.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			acategories = (ArrayList) ois.readObject();
			
			ois.close();
			fis.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
			return;
		}
		catch (ClassNotFoundException e) {
			System.out.println("Class not found");
			e.printStackTrace();
			return;
		}
	}
	
	public void intro() {
		fpanel.setLayout(new BoxLayout(fpanel,BoxLayout.Y_AXIS));
		
		ctlabel.setFont(new Font("Serif", Font.PLAIN, 20));
		ctpanel.setMaximumSize(new Dimension(800,30));
		
		ccontrols.setLayout(new BoxLayout(ccontrols,BoxLayout.LINE_AXIS));
		ccontrols.setMaximumSize(new Dimension(800,30));
		ccontrols.add(cart);
		ccontrols.add(Box.createHorizontalGlue());
		ccontrols.add(search_bar_categories);
		search_bar_categories.addItem("--Search an item--");
		cart.addActionListener(this);
		cart.setFocusable(false);
		
		category_page();
		//SwingUtilities.updateComponentTreeUI(this);
		
	}
	
	public void only_once_settings() {
		ptlabel.setFont(new Font("Serif", Font.PLAIN, 20));
		ptpanel.setMaximumSize(new Dimension(800,30));
		
		pcontrols.setLayout(new BoxLayout(pcontrols,BoxLayout.LINE_AXIS));
		pcontrols.setMaximumSize(new Dimension(800,30));
		pcontrols.add(back);
		pcontrols.add(Box.createHorizontalGlue());
		pcontrols.add(search_bar_products);
		search_bar_products.addItem("--Search an item--");
		back.addActionListener(this);
		back.setFocusable(false);
		
		for(int i = 0 ; i < acategories.size(); i++) {
			for(int j = 0 ; j < acategories.get(i).size_products() ; j++) {
				search_bar_categories.addItem(acategories.get(i).getProduct(j).getName());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0 ; i < acategories.size() ; i++) {
			if(e.getSource() == acategories.get(i).bcategory) {
				selected_category = i;
				select_category();
			}
		}
		
		if(e.getSource() == back) {
			fpanel.removeAll();
			search_bar_products.removeAllItems();
			category_page();
			SwingUtilities.updateComponentTreeUI(this);
		}
	}
	
	public void select_category() {
		fpanel.removeAll();
		search_bar_products.addItem("--Search an item--");
		fpanel.add(ptpanel);
		fpanel.add(pcontrols);
		ptpanel.add(ptlabel);
		
		for(int i = 0 ; i < acategories.get(selected_category).size_products() ; i++) {
			acategories.get(selected_category).getProduct(i).products_panel_user();
			fpanel.add(acategories.get(selected_category).get_panel_at(i));
			search_bar_products.addItem(acategories.get(selected_category).getProduct(i).getName());
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void category_page() {
		fpanel.add(ctpanel);
		fpanel.add(ccontrols);
		ctpanel.add(ctlabel);
		for(int i = 0 ; i < acategories.size(); i++) {
			acategories.get(i).categories_panel_user();
			fpanel.add(acategories.get(i).pcategory);
			acategories.get(i).bcategory.addActionListener(this);
		}
	}
}
