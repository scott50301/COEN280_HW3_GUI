
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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.eltima.components.ui.DatePicker;

public class GUI {

	//static JList<CheckboxListItem> countrylist;
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
	    	String[] genres = new String[1];
	    	while (rs.next()) {
			  	String genre = rs.getString("GENRE");
			  	genredata.add(genre);
			  
			}
	    	DefaultListModel<JCheckBox> genremodel = new DefaultListModel<JCheckBox>();
	    	JCheckBoxList genrecheckBoxList = new JCheckBoxList(genremodel);
	    
	    	for(String s : genredata) {
	    		genremodel.addElement(new JCheckBox(s));
	    	}
	    
	    	
	    	//================================================= COUNTRY =================================================//
	    	List<String> countrydata = new ArrayList<>();
	    	List<String> clickedCountry = new ArrayList<>();
	    	String[] countires = new String[1];
	    	DefaultListModel<JCheckBox> country_model = new DefaultListModel<JCheckBox>();
	    	JCheckBoxList country_checkBoxList = new JCheckBoxList(country_model);
	    	
	    	
	    	//================================================= COUNTRY =================================================//
	    	List<String> tagdata = new ArrayList<>();
	    	List<String> clickedTag = new ArrayList<>();
	    	DefaultListModel<JCheckBox> tag_model = new DefaultListModel<JCheckBox>();
	    	JCheckBoxList tag_checkBoxList = new JCheckBoxList(tag_model);
	    	
	    	//================================================= BETWEEN ATTRIBUTES' VALUE =================================================//
	    	String[] listData = new String[]{"Select AND,OR","AND", "OR"};

	        
	        JComboBox<String> comboBox = new JComboBox<String>(listData);
	        String[] condition = new String[1];
	        condition[0] = "AND";

	        comboBox.setSelectedIndex(0);
	        
	        
			
	    	frame.setBounds(100, 100, 1250, 1000);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    	
			//Genre
	    	JLabel lb_genre = new JLabel("Genres");
	    	lb_genre.setBounds(20,10,100,50);
	    	lb_genre.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_genre);
	    	
	    	JScrollPane jp_genre = new JScrollPane(genrecheckBoxList);
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
	    	
	    	JScrollPane jp_country = new JScrollPane(country_checkBoxList);
	    	jp_country.setBounds(250, 50, 200, 500);
	    	frame.add(jp_country);
	    	
	    	//Tags
	    	JLabel lb_tags = new JLabel("Tags Ids and Values");
	    	lb_tags.setBounds(480,10,200,50);
	    	lb_tags.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_tags);
	    	
	    	JScrollPane jp_tag = new JScrollPane(tag_checkBoxList);
	    	jp_tag.setBounds(480, 50, 200, 500);
	    	frame.add(jp_tag);
	    	
	    	//Movie Result
	    	JLabel lb_movie_result = new JLabel("Movie Result");
	    	lb_movie_result.setBounds(1000,10,200,50);
	    	lb_movie_result.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_movie_result);
	    	
	    	JTextArea jt_movie_result = new JTextArea("", 10, 10);
	    	JScrollPane scroll_movie_result = new JScrollPane(jt_movie_result);
	    	scroll_movie_result.setBounds(900, 50, 300, 400);
	    	frame.add(scroll_movie_result);
	    	
	    	//User Result
	    	JLabel lb_user_result = new JLabel("User Result");
	    	lb_user_result.setBounds(1000,450,200,50);
	    	lb_user_result.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_user_result);
	    	
	    	JTextArea jt_user_result = new JTextArea("", 10, 10);
	    	JScrollPane scroll_user_result = new JScrollPane(jt_user_result);
	    	scroll_user_result.setBounds(900, 500, 300, 400);
	    	frame.add(scroll_user_result);
	    	
	    	//Between Attributes
	    	JLabel lb_bwtween_attributes = new JLabel("Search Between Attributes'value (Default is AND)");
	    	lb_bwtween_attributes.setBounds(20,570,600,50);
	    	lb_bwtween_attributes.setFont(new Font("Arial", Font.PLAIN, 24));
	    	frame.add(lb_bwtween_attributes);
	    	
	    	comboBox.setBounds(600,570,200,50);
	    	comboBox.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(comboBox);
	    	
	    	
	    	//sql Result
	    	JTextArea jt = new JTextArea("Query Result： \n", 10, 10);
	    	JScrollPane scroll = new JScrollPane(jt); 
	    	scroll.setBounds(20,650,800,130);
	    	frame.add(scroll);
	    	
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
	    	    	//String genres = "";
	    	    	String countries = "";
	    	    	String movie_result = "";
/*
					for (int i = 0; i < clickedGenre.size(); i++) {
						if (i == 0) {
							genres += "And ";
							if (condition[0].equals("OR"))
								genres += "(";
							genres += "m.id in (Select m.id from MOVIE_GENRES mg, MOVIE m WHERE mg.movieID = m.id  \r\n" +
								"And mg.GENRE =  '"+clickedGenre.get(i)+"') ";
						}
						else {
							genres += condition[0]+" m.id in (Select m.id from MOVIE_GENRES mg, MOVIE m WHERE mg.movieID = m.id  \r\n" + 
									"And mg.GENRE =  '"+clickedGenre.get(i)+"') ";
							
						}
							
					}
					*/
					for (int i = 0; i < clickedCountry.size(); i++) {					
						countries += "And mc.country ='"+clickedCountry.get(i)+"' ";
					}

					String query = "SELECT m.id, m.title \n" + 
							"FROM MOVIE_COUNTRIES mc,  MOVIE_GENRES mg, MOVIE m \n" + 
							"WHERE mg.movieID = m.id \n" + 
							"AND mc.movieID = m.id \n" + 
							genres[0]+"\n";
					if (condition[0].equals("OR"))
							query += ")";
							query += countries+"\n"+
							"GROUP BY m.id, m.title \n"+
							"ORDER BY m.id ";
					
		            try {
						ResultSet excute_movie_query_rs = con.createStatement().executeQuery(query);
						while (excute_movie_query_rs.next()) {
						  	String mid = excute_movie_query_rs.getString("ID");
						  	String title = excute_movie_query_rs.getString("TITLE");
						  	movie_result += mid+"   "+title+"\n"; 
						  
						}
						Font f = new Font("Serif", Font.BOLD, 20); 
						jt.setText("");
			            jt.setFont(f);
			            jt.append(condition[0]+"\n"+query); 
			            
			            
						jt_movie_result.setText("");
						jt_movie_result.setFont(new Font("Serif", Font.BOLD, 20));
						jt_movie_result.append(movie_result);
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
	    	    }
	    	});
	    	
	    	
	    	genrecheckBoxList.addMouseListener(new MouseAdapter() {
	    		public void mouseClicked(MouseEvent event) {
	 
	    			JCheckBoxList list_genre = (JCheckBoxList) event.getSource();
	    			//JList<CheckboxListItem> list_genre = (JList<CheckboxListItem>) event.getSource();
	    			
	    			
	    			// Get index of item clicked
	 
	            	int index = list_genre.locationToIndex(event.getPoint());
	            	JCheckBox item = list_genre.getModel().getElementAt(index);
	 
	            	// Toggle selected state
	 
	            	item.setSelected(!item.isSelected());
	            	
	            	if (item.isSelected()) {
	            		clickedGenre.add(item.getLabel());
	            		
	            	}
	            	else {
	            		clickedGenre.remove(item.getLabel());
	            		
	            	}
	            	
	            	genres[0] = "";
	            	for (int i = 0; i < clickedGenre.size(); i++) {
						if (i == 0) {
							genres[0] += "And ";
							if (condition[0].equals("OR"))
								genres[0] += "(";
							genres[0] += "m.id in (Select m.id from MOVIE_GENRES mg, MOVIE m WHERE mg.movieID = m.id  \r\n" +
								"And mg.GENRE =  '"+clickedGenre.get(i)+"') ";
						}
						else {
							genres[0] += condition[0]+" m.id in (Select m.id from MOVIE_GENRES mg, MOVIE m WHERE mg.movieID = m.id  \r\n" + 
									"And mg.GENRE =  '"+clickedGenre.get(i)+"') ";
							
						}
							
					}
	            	String query = "SELECT mc.country \n" + 
							"FROM MOVIE_COUNTRIES mc,  MOVIE_GENRES mg, MOVIE m \n" + 
							"WHERE mg.movieID = m.id \n" + 
							"AND mc.movieID = m.id \n" + 
							genres[0]+"\n";
					if (condition[0].equals("OR"))
							query += ")";
							query += "GROUP BY mc.country \n"+
							"ORDER BY mc.country ";
	            	
					
						
					try {
						ResultSet GetCountries = con.createStatement().executeQuery(query);
						countrydata.clear();
						while (GetCountries.next()) {
						  	String country = GetCountries.getString("COUNTRY");
						  	country = country.trim();
						  	if (country.length() > 0) {
						  		countrydata.add(country);
						  	}
						}
						
						country_model.removeAllElements();
						
						//jp_country.revalidate();
						//country_checkBoxList.revalidate();
						
						for (String country : countrydata) {
							country_model.addElement(new JCheckBox(country));
						}
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	            	// Repaint cell
	 
					list_genre.repaint(list_genre.getCellBounds(index, index));
	            	
	            	
	    		}
				
	      	});
	    	
	    	country_checkBoxList.addMouseListener(new MouseAdapter() {
	    		public void mouseClicked(MouseEvent event) {
	 

	    			JCheckBoxList list_country = (JCheckBoxList) event.getSource();
	    			//JList<CheckboxListItem> list_genre = (JList<CheckboxListItem>) event.getSource();
	    			
	    			
	    			// Get index of item clicked
	 
	            	int index = list_country.locationToIndex(event.getPoint());
	            	JCheckBox item = list_country.getModel().getElementAt(index);
	 
	            	// Toggle selected state
	 
	            	item.setSelected(!item.isSelected());
	            	
	            	if (item.isSelected()) {
	            		clickedCountry.add(item.getLabel());
	            	}
	            	else {
	            		clickedCountry.remove(item.getLabel());
	            	}
	    			
	            	countires[0] = "";
	            	for (int i = 0; i < clickedCountry.size(); i++) {
						if (i == 0) {
							countires[0] += "And ";
							if (condition[0].equals("OR"))
								countires[0] += "(";
							countires[0] += " m.id in (Select m.id from MOVIE_COUNTRIES mc, MOVIE m WHERE mc.movieID = m.id  \r\n" +
								"And mc.COUNTRY =  '"+clickedCountry.get(i)+"') ";
						}
						else {
							countires[0] += condition[0]+" m.id in (Select m.id from MOVIE_COUNTRIES mc, MOVIE m WHERE mc.movieID = m.id  \r\n" + 
									"And mc.COUNTRY =  '"+clickedCountry.get(i)+"') ";
							
						}
	            	}
						
	            	String query = "SELECT t.id, t.value \n" + 
							"FROM MOVIE_COUNTRIES mc,  MOVIE_GENRES mg, MOVIE m, TAGS t, MOVIE_TAGS mt \n" + 
							"WHERE mg.movieID = m.id \n" + 
							"AND mc.movieID = m.id \n" + 
							"AND mt.movieID = m.id \n"+
							"AND mt.TAGID = t.id \n"+
							genres[0]+"\n";
					if (condition[0].equals("OR")) {
							query += ")";
					}
							
							query += countires[0]+"\n";
					if (condition[0].equals("OR")) {
							query += ")";	
					}
								
							query += "GROUP BY t.id, t.value \n"+
							"ORDER BY t.id ";
							
					try {
						ResultSet GetTags = con.createStatement().executeQuery(query);
						tagdata.clear();
						while (GetTags.next()) {
						  	String tagid = GetTags.getString("id");
						  	String tagvalue = GetTags.getString("value");
						  	
						  	tagdata.add(tagid+"  "+tagvalue);
						  	
						}
						
						tag_model.removeAllElements();
						
						//jp_country.revalidate();
						//country_checkBoxList.revalidate();
						
						for (String tag : tagdata) {
							tag_model.addElement(new JCheckBox(tag));
						}
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
	            	

	            	// Repaint cell
	 
	            	list_country.repaint(list_country.getCellBounds(index, index));
	            	
	            	
	    		}
				
	      	});
	    	
	    	// 添加条目选中状态改变的监听器
	        comboBox.addItemListener(new ItemListener() {
	            @Override
	            public void itemStateChanged(ItemEvent e) {
	                // 只处理选中的状态
	            	
	                if (e.getStateChange() == ItemEvent.SELECTED) {
	                	if (comboBox.getSelectedIndex() != 0) {
	                		 condition[0] = comboBox.getSelectedItem().toString();
	                	}
	                	else {
	                		condition[0] = "AND";
	                	}
	                    
	                }
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
		    //datepick.setHightlightdays(hilightDays, Color.red);
		    // 设置一个月份中不需要的日子，呈灰色显示
		    //datepick.setDisableddays(disabledDays);
		    // 设置国家
		    datepick.setLocale(Locale.US);
		    // 设置时钟面板可见
		    datepick.setTimePanleVisible(true);
		    return datepick;
		}
	}
	 
	class JCheckBoxList extends JList<JCheckBox> {
	  protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
	  public JCheckBoxList() {
	    setCellRenderer(new CellRenderer());
	    addMouseListener(new MouseAdapter() {
	      public void mousePressed(MouseEvent e) {
	        int index = locationToIndex(e.getPoint());
	        /*
	        if (index != -1) {
	          JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
	          checkbox.setSelected(!checkbox.isSelected());
	          repaint();
	        }
	        */
	      }
	    });
	    
	    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	  }
	  private String label;
	  public JCheckBoxList(ListModel<JCheckBox> model){
	    this();
	    setModel(model);
	    
	  }
	  private boolean isSelected = false;
		 
	  
	 
	   public boolean isSelected() {
	      return isSelected;
	   }
	 
	   public void setSelected(boolean isSelected) {
	      this.isSelected = isSelected;
	   }
	 
	   public String toString() {
	      return label;
	   }
	
	 
	 
	  protected class CellRenderer implements ListCellRenderer<JCheckBox> {
	    public Component getListCellRendererComponent(
	        JList<? extends JCheckBox> list, JCheckBox value, int index,
	        boolean isSelected, boolean cellHasFocus) {
	      JCheckBox checkbox = value;

	      //Drawing checkbox, change the appearance here
	      checkbox.setBackground(isSelected ? getSelectionBackground()
	          : getBackground());
	      checkbox.setForeground(isSelected ? getSelectionForeground()
	          : getForeground());
	      checkbox.setEnabled(isEnabled());
	      checkbox.setFont(getFont());
	      checkbox.setFocusPainted(false);
	      checkbox.setBorderPainted(true);
	      checkbox.setBorder(isSelected ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);
	      return checkbox;
	    }
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
	
	