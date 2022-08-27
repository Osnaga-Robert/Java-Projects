package User_Options;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	JPanel ytpanel = new JPanel();
	JPanel ypanel = new JPanel();
	JPanel fPanel = new JPanel();
	JPanel fhpanel = new JPanel();
	JPanel shpanel = new JPanel();
	JLabel ftext = new JLabel("Name");
	JLabel stext = new JLabel("Number of items");
	
	JLabel ctlabel = new JLabel("Categories");
	JLabel ptlabel = new JLabel("Products");
	JLabel ytlabel = new JLabel("Your cart");
	
	JButton cart = new JButton("Your char");
	JButton back = new JButton("<<-");
	
	JComboBox<String> search_bar_categories = new JComboBox<String>();
	JComboBox<String> search_bar_products = new JComboBox<String>();
	
	ArrayList<Class_cart> acart = new ArrayList<Class_cart>();
	
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
		this.setSize(900,900);
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
		ctpanel.setMaximumSize(new Dimension(900,30));
		
		ccontrols.setLayout(new BoxLayout(ccontrols,BoxLayout.LINE_AXIS));
		ccontrols.setMaximumSize(new Dimension(900,30));
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
		ptpanel.setMaximumSize(new Dimension(900,30));
		
		pcontrols.setLayout(new BoxLayout(pcontrols,BoxLayout.LINE_AXIS));
		pcontrols.setMaximumSize(new Dimension(900,30));
		pcontrols.add(back);
		pcontrols.add(Box.createHorizontalGlue());
		pcontrols.add(search_bar_products);
		search_bar_products.addItem("--Search an item--");
		back.addActionListener(this);
		back.setFocusable(false);
		
		for(int i = 0 ; i < acategories.size(); i++) {
			for(int j = 0 ; j < acategories.get(i).size_products() ; j++) {
				acategories.get(i).getProduct(j).products_panel_user();
				search_bar_categories.addItem(acategories.get(i).getProduct(j).getName());
				acategories.get(i).getProduct(j).details.addActionListener(this);
				acategories.get(i).getProduct(j).add_char.addActionListener(this);
			}
		}
//		JPanel fPanel = new JPanel();
//		JPanel fhpanel = new JPanel();
//		JPanel shpanel = new JPanel();
//		JLabel ftext = new JLabel("Name");
//		JLabel stext = new JLabel("Number of items");
		
		fPanel.setLayout(new GridLayout(1,2));
		fPanel.setMaximumSize(new Dimension(900,30));
		fhpanel.setBackground(Color.YELLOW);
		shpanel.setBackground(Color.YELLOW);
		ftext.setHorizontalAlignment(JLabel.CENTER);
		stext.setHorizontalAlignment(JLabel.CENTER);
		fhpanel.add(ftext);
		shpanel.add(stext);
		fPanel.add(fhpanel);
		fPanel.add(shpanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0 ; i < acategories.size() ; i++) {
			if(e.getSource() == acategories.get(i).bcategory) {
				selected_category = i;
				select_category();
			}
		}
		
		if(e.getSource() == cart) {
			cart_button();
		}
		
		if(e.getSource() == back) {
			fpanel.removeAll();
			search_bar_products.removeAllItems();
			category_page();
			SwingUtilities.updateComponentTreeUI(this);
		}
		for(int i = 0 ; i < acategories.get(selected_category).size_products(); i++) {
			if(e.getSource() == acategories.get(selected_category).getProduct(i).details) {
				JOptionPane.showOptionDialog(null,"Name of product: "+ acategories.get(selected_category).getProduct(i).getName(),
						"Details", JOptionPane.DEFAULT_OPTION ,JOptionPane.INFORMATION_MESSAGE, null, null, null) ;
			}
			if(e.getSource() == acategories.get(selected_category).getProduct(i).add_char) {
				if(check_selected(selected_category,i) == true && acategories.get(selected_category).getProduct(i).getCount() != 0) {
					Class_cart aux = new Class_cart(acategories.get(selected_category).getProduct(i).getCount(),selected_category,i,acategories.get(selected_category).getProduct(i).getName());
					acart.add(aux);
				}
				
			}
		}
	}
	
	public void select_category() {
		fpanel.removeAll();
		search_bar_products.addItem("--Search an item--");
		fpanel.add(ptpanel);
		fpanel.add(pcontrols);
		ptpanel.add(ptlabel);
		
		for(int i = 0 ; i < acategories.get(selected_category).size_products() ; i++) {
			fpanel.add(acategories.get(selected_category).getProduct(i).pproduct);
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
	
	public void cart_button() {
		fpanel.removeAll();
		ytlabel.setFont(new Font("Serif", Font.PLAIN, 20));
		ytpanel.setMaximumSize(new Dimension(900,30));
		
		ytpanel.add(ytlabel);
		fpanel.add(ytpanel);
		fpanel.add(fPanel);
		for(int i = 0; i < acart.size(); i++) {
			fpanel.add(acart.get(i).add_panel());
		}
		
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public boolean check_selected(int j,int k) {
		for(int i = 0 ; i < acart.size(); i++) {
			if(acart.get(i).getName().equals(acategories.get(j).getProduct(k).getName())) {
				return false;
			}
		}
		return true;
	}
}
