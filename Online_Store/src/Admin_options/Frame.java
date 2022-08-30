package Admin_options;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import Objects.Categories;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PublicKey;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import Objects.*;


public class Frame extends JFrame implements ActionListener{
	
	JPanel text_intro = new JPanel();
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	JPanel fpanel = new JPanel();
	JPanel controls_categories = new JPanel();
	JPanel controls_products = new JPanel();
	JPanel test = new JPanel();
	JPanel text_intro_products = new JPanel();
	JPanel pup = new JPanel();
	Object[] options = {"OK","Add description"};
	
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
	
	ImageIcon iframe = new ImageIcon("Icons/Jframe_icon.png");
	ImageIcon plus = new ImageIcon("Icons/Plus.png");
	Image image = plus.getImage(); // transform it 
	Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
	
	ImageIcon details = new ImageIcon("Icons/Details.png");
	ImageIcon stock_img = new ImageIcon("Icons/Add_stock_img.png");
	
	int selected_category = 0;
	
	Frame(){
		resize_icons();
		frame_settings();
		get_input();
		introduction();
		settings_introduction();	
	}
	
	public void resize_icons() {
		plus = new ImageIcon(newimg);
		image = details.getImage();
		newimg = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		details = new ImageIcon(newimg);
		
		image = stock_img.getImage();
		newimg = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		stock_img = new ImageIcon(newimg);
	}
	
	public void frame_settings() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,800);
		this.setResizable(false);
		this.setIconImage(iframe.getImage());
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
	
	public void get_input() {
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
	
	public void introduction() {
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
	}
	
	public void settings_introduction() {
		
		label_categories.setFont(new Font("Serif", Font.PLAIN, 20));
		fpanel.setLayout(new BoxLayout(fpanel,BoxLayout.Y_AXIS));
		controls_categories.setMaximumSize(new Dimension(800,30));
		controls_categories.setLayout(new BoxLayout(controls_categories,BoxLayout.LINE_AXIS));
		text_intro.setMaximumSize(new Dimension(800,30));
		new_category.addActionListener(this);
		new_category.setFocusable(false);
		
		
		fpanel.add(text_intro);
		fpanel.add(controls_categories);
		text_intro.add(label_categories);
		
		
		for(int i = 0 ; i < acategories.size() ; i++) {
			acategories.get(i).categories_panel_admin();
			fpanel.add(acategories.get(i).pcategory);
			acategories.get(i).bcancel.addActionListener(this);
			acategories.get(i).bcategory.addActionListener(this);
			for(int j = 0 ; j < acategories.get(i).size_products() ; j++) {
				acategories.get(i).getProduct(j).products_panel_admin();
				acategories.get(i).getProduct(j).bcancel.addActionListener(this);
				acategories.get(i).getProduct(j).details.addActionListener(this);
				acategories.get(i).getProduct(j).add_stock.addActionListener(this);
			}
		}
		
		controls_categories.add(new_category);
		controls_categories.add(Box.createHorizontalGlue());
		controls_categories.add(search_bar_categories);
		SwingUtilities.updateComponentTreeUI(this);
		this.getContentPane().add(scategories);
		search_bar_categories.addItem("--Search a product--");
		
		for(int i = 0 ; i < acategories.size() ; i++) {
			for(int j = 0 ; j < acategories.get(i).size_products() ; j++) {
				search_bar_categories.addItem(acategories.get(i).getProduct(j).getName());
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == new_category) {
			add_category();
		}
		if(e.getSource() == new_product) {
			String product = (String) JOptionPane.showInputDialog(null, "New name for product", "Add product", JOptionPane.OK_CANCEL_OPTION,plus , null, 0);
			if((product != null) && (product.length() > 0) && (product.charAt(0) != ' ')) {
				if(check_products(product) == false) {
					acategories.get(selected_category).add_product(product);
					acategories.get(selected_category).getProduct(acategories.get(selected_category).size_products() - 1).products_panel_admin();
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
			if(acategories.get(selected_category).size_products() != 0 ) {
				if (e.getSource() == acategories.get(selected_category).get_stock_button(i)) {
					String number =(String) JOptionPane.showInputDialog(null,"Insert a number","Add stock",JOptionPane.OK_CANCEL_OPTION,stock_img,null,0);
					//String product = (String) JOptionPane.showInputDialog(null, "New name for product", "Add product", JOptionPane.OK_CANCEL_OPTION,plus , null, 0);
					if(number != null) {
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
				}
				if (e.getSource() == acategories.get(selected_category).get_details_button(i)) {
					 int a = JOptionPane.showOptionDialog(null, "Stock: " + acategories.get(selected_category).getProduct(i).getCount() + "item(s) left \n"
					 											+ "Sales: " + acategories.get(selected_category).getProduct(i).getSales() + "item(s) sold \n"
					 											+ "Description: " + acategories.get(selected_category).getProduct(i).getSdetails() + "\n"
					 											+ "Last time adding stock: " + acategories.get(selected_category).getProduct(i).getDateStock() + " \n"
					 											+ "Last time see details: " + acategories.get(selected_category).getProduct(i).getDatesee() + " \n"
					 											,"Details", JOptionPane.OK_OPTION ,JOptionPane.INFORMATION_MESSAGE, details, options, null) ;
					 acategories.get(selected_category).getProduct(i).setDatesee();
					 if(a == JOptionPane.NO_OPTION) {
						 String description = JOptionPane.showInputDialog("Add description");
						 if(description != null) {
							 acategories.get(selected_category).getProduct(i).setSdetails(description);
						 }
					 }
					}
				}
			if(e.getSource() == acategories.get(selected_category).get_cancel_button(i)) {
				fpanel.remove(acategories.get(selected_category).get_panel_at(i));
				search_bar_products.removeItem(acategories.get(selected_category).getProduct(i).getName());
				search_bar_categories.removeItem(acategories.get(selected_category).getProduct(i).getName());
				acategories.get(selected_category).delete_product(i);
				SwingUtilities.updateComponentTreeUI(this);
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
		if(e.getSource() == search_bar_products) {
			String nameString = (String) search_bar_products.getSelectedItem();
			for(int i = 0 ; i < acategories.get(selected_category).size_products() ; i++) {
				if(acategories.get(selected_category).getProduct(i).getName().equals(nameString)) {
					JOptionPane.showOptionDialog(null, "Stock: " + acategories.get(selected_category).getProduct(i).getCount() + "item(s) left \n"
								+ "Sales: " + acategories.get(selected_category).getProduct(i).getSales() + "item(s) sold \n"
								+ "Last time adding stock: " + acategories.get(selected_category).getProduct(i).getDateStock() + " \n"
								+ "Last time see details: " + acategories.get(selected_category).getProduct(i).getDatesee() + " \n","Details", JOptionPane.DEFAULT_OPTION ,JOptionPane.INFORMATION_MESSAGE, details, null, null) ;
					acategories.get(selected_category).getProduct(i).setDatesee();
				}
			}
		}
	}
	
	public void add_category() {
		String category = (String)JOptionPane.showInputDialog(null,"New name for category","New category",JOptionPane.OK_CANCEL_OPTION,plus,null,0);
		if((category != null) && (category.length() > 0) && (category.charAt(0) != ' ')) {
			if(check_categoeies(category) == false) {
				acategories.add(new Categories(category));
				acategories.get(acategories.size() - 1).categories_panel_admin();
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
		text_intro_products.add(label_products,BorderLayout.CENTER);
		label_products.setText("Categories->" + acategories.get(selected_category).getName());
		fpanel.add(text_intro_products);
		fpanel.add(controls_products);
		
		for(int i = 0 ; i < acategories.get(selected_category).size_products() ; i++) {
			fpanel.add(acategories.get(selected_category).get_panel_at(i));
			search_bar_products.addItem(acategories.get(selected_category).getProduct(i).getName());
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
		for(int i = 0 ; i < acategories.size(); i++) {
			for(int j = 0 ; j < acategories.get(i).size_products(); j++) {
				if(acategories.get(i).getProduct(j).getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}
}