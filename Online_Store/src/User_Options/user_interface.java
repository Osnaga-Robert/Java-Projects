package User_Options;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.IntrospectionException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	JPanel spanel = new JPanel();
	JPanel emptypanel = new JPanel();
	
	JScrollPane scategories = new JScrollPane(fpanel);
	
	JLabel ftext = new JLabel("Name");
	JLabel stext = new JLabel("Number of items");
	JLabel ctlabel = new JLabel("Categories");
	JLabel ptlabel = new JLabel("Products");
	JLabel ytlabel = new JLabel("Your cart");
	JLabel emptylabel = new JLabel("Your cart is empty");
	
	JButton cart = new JButton("Your char");
	JButton back = new JButton("<<-");
	JButton back1 = new JButton("<<-");
	JButton sbutton = new JButton("Submit your order");
	
	JComboBox<String> search_bar_categories = new JComboBox<String>();
	JComboBox<String> search_bar_products = new JComboBox<String>();
	
	ArrayList<Class_cart> acart = new ArrayList<Class_cart>();
	
	ImageIcon icart = new ImageIcon("Icons/Cart.png");
	ImageIcon istock = new ImageIcon("Icons/Add_stock_img.png");
	ImageIcon idetails = new ImageIcon("Icons/Details.png");
	ImageIcon isubmit = new ImageIcon("Icons/Submit.png");
	ImageIcon iempty_cart = new ImageIcon("Icons/Empty_cart.png");
	Image image;
	Image newimg;
	
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
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
				FileOutputStream fos = new FileOutputStream("Savings/Save.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(acategories);
				oos.close();
				fos.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
				return;
			}
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
			
			}
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
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
		SwingUtilities.updateComponentTreeUI(this);
		this.getContentPane().add(scategories);
		ccontrols.setLayout(new BoxLayout(ccontrols,BoxLayout.LINE_AXIS));
		ccontrols.setMaximumSize(new Dimension(900,30));
		ccontrols.add(cart);
		ccontrols.add(Box.createHorizontalGlue());
		ccontrols.add(search_bar_categories);
		search_bar_categories.addItem("--Search an item--");
		cart.addActionListener(this);
		cart.setFocusable(false);
		category_page();
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
		
		image = istock.getImage();
		newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
		istock = new ImageIcon(newimg);
		image = idetails.getImage();
		newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
		idetails = new ImageIcon(newimg);
		image = iempty_cart.getImage();
		newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
		iempty_cart = new ImageIcon(newimg);
		
		back1.setBackground(new Color(105,105,105));
		back1.setForeground(Color.BLACK);
		back1.setFocusable(false);
		cart.setFocusable(false);
		back.setFocusable(false);
		sbutton.setFocusable(false);
		back1.setBorderPainted(false);
		fPanel.setLayout(new GridLayout(1,2));
		fPanel.setMaximumSize(new Dimension(900,30));
		fhpanel.setBackground(new Color(105,105,105));
		shpanel.setBackground(new Color(105,105,105));
		ftext.setHorizontalAlignment(JLabel.CENTER);
		stext.setHorizontalAlignment(JLabel.CENTER);
		ytlabel.setFont(new Font("Serif", Font.PLAIN, 20));
		ytlabel.setHorizontalAlignment(JLabel.CENTER);
		ytpanel.setMaximumSize(new Dimension(900,30));
		ytpanel.setLayout(new BorderLayout());
		ytpanel.add(back1,BorderLayout.WEST);
		back1.addActionListener(this);
		spanel.setMaximumSize(new Dimension(900,30));
		spanel.setBackground(new Color(105,105,105));
		spanel.setLayout(new BorderLayout());
		spanel.add(sbutton,BorderLayout.EAST);
		sbutton.setOpaque(false);
		sbutton.setContentAreaFilled(false);
		sbutton.setBorderPainted(false);
		sbutton.addActionListener(this);
		ytpanel.add(ytlabel,BorderLayout.CENTER);
		emptylabel.setFont(new Font("Serif", Font.PLAIN, 20));
		emptylabel.setHorizontalAlignment(JLabel.CENTER);
		emptypanel.setMaximumSize(new Dimension(900,30));
		emptypanel.add(emptylabel);
		search_bar_categories.addActionListener(this);
		search_bar_products.addActionListener(this);
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
		
		if(e.getSource() == back || e.getSource() == back1) {
			fpanel.removeAll();
			search_bar_products.removeAllItems();
			category_page();
			SwingUtilities.updateComponentTreeUI(this);
		}
		
		if(e.getSource() == sbutton) {
			submit_button();
		}
		for(int i = 0 ; i < acategories.get(selected_category).size_products(); i++) {
			if(e.getSource() == acategories.get(selected_category).getProduct(i).details) {
				JOptionPane.showOptionDialog(null,"Name of product: "+ acategories.get(selected_category).getProduct(i).getName() + "\n" +
												  "Description: " + acategories.get(selected_category).getProduct(i).getSdetails(),
						"Details", JOptionPane.DEFAULT_OPTION ,JOptionPane.INFORMATION_MESSAGE, idetails, null, null) ;
			}
			if(e.getSource() == acategories.get(selected_category).getProduct(i).add_char) {
				if(check_selected(selected_category,i) == true && acategories.get(selected_category).getProduct(i).getCount() != 0) {
					Class_cart aux = new Class_cart(acategories.get(selected_category).getProduct(i).getCount(),selected_category,i,acategories.get(selected_category).getProduct(i).getName());
					acart.add(aux);
					acart.get(acart.size() - 1).cancel.addActionListener(this);
				}
				else if(check_selected(selected_category, i) == false)
					JOptionPane.showMessageDialog(null, "Your item is already in the cart", "", 0, icart);
				else if(acategories.get(selected_category).getProduct(i).getCount() == 0)
					JOptionPane.showMessageDialog(null, "The item selected is not in stock", "", 0, istock);
				
			}
		}
		for(int i = 0 ; i < acart.size() ; i++) {
			if(e.getSource() == acart.get(i).cancel) {
				fpanel.remove(acart.get(i).add_panel());
				acart.remove(i);
			}
			if(acart.size() == 0) {
				fpanel.remove(spanel);
				fpanel.add(emptypanel);
				fpanel.add(spanel);
			}
			SwingUtilities.updateComponentTreeUI(this);
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
		if(e.getSource() == search_bar_products) {
			String nameString = (String) search_bar_products.getSelectedItem();
			for(int i = 0 ; i < acategories.get(selected_category).size_products() ; i++) {
				if(acategories.get(selected_category).getProduct(i).getName().equals(nameString)) {
					JOptionPane.showOptionDialog(null,"Name of product: "+ acategories.get(selected_category).getProduct(i).getName() + "\n" +
							  "Description: " + acategories.get(selected_category).getProduct(i).getSdetails(),
							  "Details", JOptionPane.DEFAULT_OPTION ,JOptionPane.INFORMATION_MESSAGE, idetails, null, null) ;
					search_bar_products.setSelectedIndex(0);
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
			acategories.get(selected_category).getProduct(i).setstock();
			fpanel.add(acategories.get(selected_category).getProduct(i).pproduct);
			search_bar_products.addItem(acategories.get(selected_category).getProduct(i).getName());
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void category_page() {
		fpanel.add(ctpanel);
		fpanel.add(ccontrols);
		ctpanel.add(ctlabel);
		search_bar_categories.setSelectedIndex(0);
		for(int i = 0 ; i < acategories.size(); i++) {
			acategories.get(i).categories_panel_user();
			fpanel.add(acategories.get(i).pcategory);
			acategories.get(i).bcategory.addActionListener(this);
		}
	}
	
	public void cart_button() {
		fpanel.removeAll();
		fpanel.add(ytpanel);
		fpanel.add(fPanel);
		for(int i = 0; i < acart.size(); i++) {
			fpanel.add(acart.get(i).add_panel());
		}
		if(acart.size() == 0)
			fpanel.add(emptypanel);
		fpanel.add(spanel);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void submit_button() {
		int check = check_quantity();
		if(acart.size() == 0)
			JOptionPane.showMessageDialog(null, "Your cart is empty", "", 0, iempty_cart);
		else {
			if(check == -1) {
				for(int i = 0 ; i < acart.size() ; i++) {
					int number = Integer.parseInt(acart.get(i).textField.getText());
					int new_stock = acategories.get(acart.get(i).getSelected_category()).getProduct(acart.get(i).getPosition()).getCount() - number;
					acategories.get(acart.get(i).getSelected_category()).getProduct(acart.get(i).getPosition()).setCount(new_stock);
					acategories.get(acart.get(i).getSelected_category()).getProduct(acart.get(i).getPosition()).addSales(number);
				}
				JOptionPane.showMessageDialog(null, "Your items will be at you in a few days", "", 0, isubmit);
				acart.removeAll(acart);
				fpanel.removeAll();
				category_page();
				SwingUtilities.updateComponentTreeUI(this);
			}
			else {
				JOptionPane.showMessageDialog(null, "We dont have enough items in stock for "+ acart.get(check).getName(), "", 0, istock);
			}
		}
	}
	
	public boolean check_selected(int j,int k) {
		for(int i = 0 ; i < acart.size(); i++) {
			if(acart.get(i).getName().equals(acategories.get(j).getProduct(k).getName())) {
				return false;
			}
		}
		return true;
	}
	
	public int check_quantity() {
		for(int i = 0 ; i < acart.size() ; i++) {
			int number = Integer.parseInt(acart.get(i).textField.getText());
			if(number > acart.get(i).getStock()) {
				return i;
			}
		}
		return -1;
	}
	
}
