import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import com.eltima.components.ui.DatePicker;

public class GUI {

	
	public static void main(String [ ] args) throws SQLException{
			
			Connection con = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:scott50301","system","csk820814");  
			Statement stmt = null;
	    	String query = "SELECT GENRE FROM MOVIE_GENRES GROUP BY GENRE ORDER BY GENRE";
	    	stmt = con.createStatement();
	    	ResultSet rs = stmt.executeQuery(query);
	    
			JFrame frame = new JFrame();
			Container cp = frame.getContentPane();
			cp.setLayout(null); 
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	      	// Create a list containing CheckboxListItem's
	    	
	    	//================================================= GENRE =================================================//
	    	List<String> genredata = new ArrayList<>();
	    	List<String> clickedGenre = new ArrayList<>();
	    	while (rs.next()) {
			  	String genre = rs.getString("GENRE");
			  	genredata.add(genre);
			  
			}
	    	CheckboxListItem[] genrecheckboxlist = new CheckboxListItem[genredata.size()];
	    	int index = 0;
	    	for(String s : genredata) {
	    		genrecheckboxlist[index++] = new CheckboxListItem(s);
	    	}
	  
	    	JList<CheckboxListItem> genrelist = new JList<CheckboxListItem>(genrecheckboxlist);
	    	
	      	// Use a CheckboxListRenderer (see below)
	      	// to renderer list cells
	 
	    	genrelist.setCellRenderer(new CheckboxListRenderer());
	    	genrelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	 
	      	// Add a mouse listener to handle changing selection
	    
	    	
	    	//================================================= COUNTRY =================================================//
	    	query = "SELECT COUNTRY FROM MOVIE_COUNTRIES GROUP BY COUNTRY ORDER BY COUNTRY";
	    	rs = stmt.executeQuery(query);
	    	List<String> countrydata = new ArrayList<>();
	    	List<String> clickedCountry = new ArrayList<>();
	    	while (rs.next()) {
			  	String country = rs.getString("COUNTRY");
			  	country = country.trim();
			  	if (country.length() > 0) {
			  		System.out.println(country);
			  		countrydata.add(country);
			  	}
			  	
			  
			}
	    	CheckboxListItem[] countrycheckboxlist = new CheckboxListItem[countrydata.size()];
	    	index = 0;
	    	for(String s : countrydata) {
	    		countrycheckboxlist[index++] = new CheckboxListItem(s);
	    	}
	  
	    	JList<CheckboxListItem> countrylist = new JList<CheckboxListItem>(countrycheckboxlist);
	    	
	      	// Use a CheckboxListRenderer (see below)
	      	// to renderer list cells
	 
	    	countrylist.setCellRenderer(new CheckboxListRenderer());
	    	countrylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	 
	    	
	    	//================================================= COUNTRY =================================================//
	    	String[] listData = new String[]{"Select AND,OR","AND", "OR"};

	        // 创建一个下拉列表框
	        final JComboBox<String> comboBox = new JComboBox<String>(listData);
	        String[] condition = new String[1];
	        // 添加条目选中状态改变的监听器
	        comboBox.addItemListener(new ItemListener() {
	            @Override
	            public void itemStateChanged(ItemEvent e) {
	                // 只处理选中的状态
	                if (e.getStateChange() == ItemEvent.SELECTED) {
	                	if (comboBox.getSelectedIndex() != 0) {
	                		 condition[0] = comboBox.getSelectedItem().toString();
	                	}
	                    
	                }
	            }
	        });

	        // 设置默认选中的条目
	        comboBox.setSelectedIndex(0);
	        
	        //
	        JTextArea jt = new JTextArea(10, 10); 

	        
	    	
	    	
			
	    	frame.setBounds(100, 100, 1250, 1000);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    	
			//Genre
	    	JLabel lb_genre = new JLabel("Genres");
	    	lb_genre.setBounds(20,10,100,50);
	    	lb_genre.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_genre);
	    	
	    	JScrollPane jp_genre = new JScrollPane(genrelist);
	    	jp_genre.setBounds(20, 50, 200, 350);
	    	frame.add(jp_genre);
	    	
	    	//Movie Year
	    	JLabel lb_year = new JLabel("Movie Year");
	    	lb_year.setBounds(20,410,200,50);
	    	lb_year.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_year);
	    	
	    	JLabel lb_year_from = new JLabel("from");
	    	lb_year_from.setBounds(20,450,50,50);
	    	lb_year_from.setFont(new Font("Arial", Font.PLAIN, 16));
	    	frame.add(lb_year_from);
	    	
	    	DatePicker start_date = getDatePicker();
	    	start_date.setBounds(70, 460, 150, 30);
	        frame.add(start_date);
	        
	        JLabel lb_year_to = new JLabel("to");
	        lb_year_to.setBounds(20,500,50,50);
	        lb_year_to.setFont(new Font("Arial", Font.PLAIN, 16));
	    	frame.add(lb_year_to);
	    	
	        DatePicker end_date = getDatePicker();
	        end_date.setBounds(70, 510, 150, 30);
	        frame.add(end_date);
	        
	        //Country
	    	JLabel lb_country = new JLabel("Country");
	    	lb_country.setBounds(250,10,100,50);
	    	lb_country.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_country);
	    	
	    	JScrollPane jp_country = new JScrollPane(countrylist);
	    	jp_country.setBounds(250, 50, 200, 500);
	    	frame.add(jp_country);
	    	
	    	//Movie Result
	    	JLabel lb_movie_result = new JLabel("Movie Result");
	    	lb_movie_result.setBounds(1000,10,200,50);
	    	lb_movie_result.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_movie_result);
	    	
	    	JScrollPane movie_result = new JScrollPane();
	    	movie_result.setBounds(1000, 50, 200, 500);
	    	frame.add(movie_result);
	    	
	    	//Between Attributes
	    	JLabel lb_bwtween_attributes = new JLabel("Search Between Attributes'value");
	    	lb_bwtween_attributes.setBounds(1000,10,200,50);
	    	lb_bwtween_attributes.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_bwtween_attributes);
	    	
	    	
	    	//Button Excute Movie Query
	    	JButton excute_movie_query = new JButton("Excute Movie Query");
	    	excute_movie_query.setBounds(50, 800, 200, 100);
	    	frame.add(excute_movie_query);
	    	
	    	//frame.pack();
	    	frame.setVisible(true);
	    	
	    	
	    	excute_movie_query.addActionListener(new ActionListener() {

	    	    @Override
	    	    public void actionPerformed(ActionEvent e) {
	    	        //your actions
	    	    	String genres = "";
	    	    	String countries = "";
					for (int i = 0; i < clickedGenre.size(); i++) {		
						genres += "And mg.GENRE = '"+clickedGenre.get(i)+"' ";
					}
					for (int i = 0; i < clickedCountry.size(); i++) {					
						countries += "And mc.country ='"+clickedCountry.get(i)+"' ";
					}
					System.out.println("SELECT m.id, m.title " + 
								"FROM MOVIE_COUNTRIES mc,  MOVIE_GENRES mg, MOVIE m " + 
								"WHERE mg.movieID = m.id " + 
								"AND mc.movieID = m.id " + 
								genres+
								countries+
								"GROUP BY m.id, m.title "+
								"ORDER BY m.id ");
					String query = "SELECT m.id, m.title " + 
							"FROM MOVIE_COUNTRIES mc,  MOVIE_GENRES mg, MOVIE m " + 
							"WHERE mg.movieID = m.id " + 
							"AND mc.movieID = m.id " + 
							genres+
							countries+
							"GROUP BY m.id, m.title "+
							"ORDER BY m.id ";
					try {
						ResultSet excute_movie_query_rs = con.createStatement().executeQuery(query);
						while (excute_movie_query_rs.next()) {
						  	String mid = excute_movie_query_rs.getString("ID");
						  	String title = excute_movie_query_rs.getString("TITLE");
						  	
						  
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    	    }
	    	});
	    	
	    	
	    	genrelist.addMouseListener(new MouseAdapter() {
	    		public void mouseClicked(MouseEvent event) {
	 
	    			JList<CheckboxListItem> list_genre = (JList<CheckboxListItem>) event.getSource();
	    			
	    			
	    			// Get index of item clicked
	 
	            	int index = list_genre.locationToIndex(event.getPoint());
	            	CheckboxListItem item = (CheckboxListItem) list_genre.getModel().getElementAt(index);
	 
	            	// Toggle selected state
	 
	            	item.setSelected(!item.isSelected());
	            	
	            	if (item.isSelected()) {
	            		clickedGenre.add(item.toString());
	            	}
	            	else {
	            		clickedGenre.remove(item.toString());
	            	}
	            	
	            	
					
	            	
	            	// Repaint cell
	 
					list_genre.repaint(list_genre.getCellBounds(index, index));
	            	
	            	
	    		}
				
	      	});
	    	
	    	countrylist.addMouseListener(new MouseAdapter() {
	    		public void mouseClicked(MouseEvent event) {
	 
	    			JList<CheckboxListItem> list_country = (JList<CheckboxListItem>) event.getSource();
	    			
	    			
	    			// Get index of item clicked
	 
	            	int index = list_country.locationToIndex(event.getPoint());
	            	CheckboxListItem item = (CheckboxListItem) list_country.getModel().getElementAt(index);
	 
	            	// Toggle selected state
	 
	            	item.setSelected(!item.isSelected());
	            	
	            	if (item.isSelected()) {
	            		clickedCountry.add(item.toString());
	            	}
	            	else {
	            		clickedCountry.remove(item.toString());
	            	}
	            	
					
					
	            	
	            	// Repaint cell
	 
	            	list_country.repaint(list_country.getCellBounds(index, index));
	            	
	            	
	    		}
				
	      	});
	    	
	   }

		private static DatePicker getDatePicker() {
		    final DatePicker datepick;
		    // 格式
		    String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
		    // 当前时间
		    Date date = new Date();
		    // 字体
		    Font font = new Font("Times New Roman", Font.BOLD, 14);
		
		    Dimension dimension = new Dimension(177, 24);
		
		    int[] hilightDays = { 1, 3, 5, 7 };
		
		    int[] disabledDays = { 4, 6, 5, 9 };
		    //构造方法（初始时间，时间显示格式，字体，控件大小）
		    datepick = new DatePicker(date, DefaultFormat, font, dimension);
		
		    datepick.setLocation(137, 83);//设置起始位置
		    /*
		    //也可用setBounds()直接设置大小与位置
		    datepick.setBounds(137, 83, 177, 24);
		    */
		    // 设置一个月份中需要高亮显示的日子
		    datepick.setHightlightdays(hilightDays, Color.red);
		    // 设置一个月份中不需要的日子，呈灰色显示
		    datepick.setDisableddays(disabledDays);
		    // 设置国家
		    datepick.setLocale(Locale.US);
		    // 设置时钟面板可见
		    datepick.setTimePanleVisible(true);
		    return datepick;
		}
	}
	 



	// Represents items in the list that can be selected
	 
	class CheckboxListItem {
	   private String label;
	   private boolean isSelected = false;
	 
	   public CheckboxListItem(String label) {
	      this.label = label;
	   }
	 
	   public boolean isSelected() {
	      return isSelected;
	   }
	 
	   public void setSelected(boolean isSelected) {
	      this.isSelected = isSelected;
	   }
	 
	   public String toString() {
	      return label;
	   }
	}
	 
	// Handles rendering cells in the list using a check box
	 
	class CheckboxListRenderer extends JCheckBox implements
	      ListCellRenderer<CheckboxListItem> {
	 
	   @Override
	   public Component getListCellRendererComponent(
	         JList<? extends CheckboxListItem> list, CheckboxListItem value,
	         int index, boolean isSelected, boolean cellHasFocus) {
	      setEnabled(list.isEnabled());
	      setSelected(value.isSelected());
	      setFont(list.getFont());
	      setBackground(list.getBackground());
	      setForeground(list.getForeground());
	      setText(value.toString());
	      return this;
	   }
	}
	
	