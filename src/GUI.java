
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.eltima.components.ui.DatePicker;

public class GUI {
	
	//static JList<CheckboxListItem> countrylist;
	public static void main(String [ ] args) throws SQLException{
			datepicker datepicker = new datepicker();
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
	    
	    	//================================================= MOVIE YEAR =================================================//
	    	String[] date_from = new String[] {""};
	    	String[] date_to = new String[] {""};
	    	
	    	//================================================= COUNTRY =================================================//
	    	List<String> countrydata = new ArrayList<>();
	    	List<String> clickedCountry = new ArrayList<>();
	    	String[] countires = new String[1];
	    	DefaultListModel<JCheckBox> country_model = new DefaultListModel<JCheckBox>();
	    	JCheckBoxList country_checkBoxList = new JCheckBoxList(country_model);
	    	
	    	
	    	//================================================= CAST =================================================//

	    	List<String> actorlistData = new ArrayList<>();
	    	List<String> chosenactors = new ArrayList<>();
	    	List<String> directorlistData = new ArrayList<>();
	    	List<String> chosendirector = new ArrayList<>();
	    	DefaultComboBoxModel<String> actor1_model = new DefaultComboBoxModel<String>();
	    	DefaultComboBoxModel<String> actor2_model = new DefaultComboBoxModel<String>();
	    	DefaultComboBoxModel<String> actor3_model = new DefaultComboBoxModel<String>();
	    	DefaultComboBoxModel<String> actor4_model = new DefaultComboBoxModel<String>();
	    	DefaultComboBoxModel<String> director_model = new DefaultComboBoxModel<String>();
	        JComboBox<String> actor1comboBox = new JComboBox<String>(actor1_model);
	        JComboBox<String> actor2comboBox = new JComboBox<String>(actor2_model);
	        JComboBox<String> actor3comboBox = new JComboBox<String>(actor3_model);
	        JComboBox<String> actor4comboBox = new JComboBox<String>(actor4_model);
	        JComboBox<String> directorcomboBox = new JComboBox<String>(director_model);
	      
        	actor1_model.addElement("");
			actor2_model.addElement("");
			actor3_model.addElement("");
			actor4_model.addElement("");
			director_model.addElement("");

	    	//================================================= TAG =================================================//
	    	List<String> tagdata = new ArrayList<>();
	    	List<String> clickedTag = new ArrayList<>();
	    	DefaultListModel<JCheckBox> tag_model = new DefaultListModel<JCheckBox>();
	    	JCheckBoxList tag_checkBoxList = new JCheckBoxList(tag_model);
	    	
	    	String[] tagsweightlistData = new String[]{"=,>,<", "=", ">", "<"};
	    	JComboBox<String> tagsweightcomboBox = new JComboBox<String>(tagsweightlistData);
	        String[] tags_weight = new String[1];
	        tags_weight[0] = "";
	        tagsweightcomboBox.setSelectedIndex(0);
	        
	        JTextField tagsweighttextField = new JTextField(16); 
	    	//================================================= BETWEEN ATTRIBUTES' VALUE =================================================//
	    	String[] attributelistData = new String[]{"Select AND, OR","AND", "OR"};
	        
	        JComboBox<String> conditioncomboBox = new JComboBox<String>(attributelistData);
	        String[] condition = new String[1];
	        condition[0] = "AND";

	        conditioncomboBox.setSelectedIndex(0);
	        
	      //================================================= MOVIE RESULT =================================================//
	        List<String> movieresultdata = new ArrayList<>();
	    	List<String> clickedMovieresult = new ArrayList<>();
	    	String[] movieresults = new String[1];
	    	DefaultListModel<JCheckBox> movieresult_model = new DefaultListModel<JCheckBox>();
	    	JCheckBoxList movieresult_checkBoxList = new JCheckBoxList(movieresult_model);
			
	    	frame.setBounds(100, 50, 1350, 1000);
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
	    	
	    	datepicker startDateChooser = datepicker.getInstance("yyyy-MM-dd");
			JTextField showStartDate = new JTextField("1903-01-01");
			date_from[0] = "1903";
			startDateChooser.register(showStartDate);
			showStartDate.setEditable(false);
			showStartDate.setBounds(70, 460, 150, 30);
	        frame.add(showStartDate);

	    	/*
	    	DatePicker start_date = getDatePicker();
	    	start_date.setBounds(70, 460, 150, 30);
	        frame.add(start_date);
	        */
	    	
	        JLabel lb_year_to = new JLabel("to");
	        lb_year_to.setBounds(20,500,50,50);
	        lb_year_to.setFont(new Font("Arial", Font.PLAIN, 16));
	    	frame.add(lb_year_to);
	    	
	    	datepicker endDateChooser = datepicker.getInstance("yyyy-MM-dd");
			JTextField showEndDate = new JTextField("2019-03-09");
			date_to[0] = "2019";
			showEndDate.setEditable(false);
			endDateChooser.register(showEndDate);
			showEndDate.setBounds(70, 510, 150, 30);
	        frame.add(showEndDate);

	    	/*
	        DatePicker end_date = getDatePicker();
	        end_date.setBounds(70, 510, 150, 30);
	        frame.add(end_date);
	        */
	        //Country
	    	JLabel lb_country = new JLabel("Country");
	    	lb_country.setBounds(250,10,100,50);
	    	lb_country.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_country);
	    	
	    	JScrollPane jp_country = new JScrollPane(country_checkBoxList);
	    	jp_country.setBounds(250, 50, 200, 500);
	    	frame.add(jp_country);
	    	
	    	//Cast
	    	JLabel lb_casts = new JLabel("Cast");
	    	lb_casts.setBounds(470,10,200,50);
	    	lb_casts.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_casts);
	    	
	    	JLabel lb_actors = new JLabel("Actor / Actoress");
	    	lb_actors.setBounds(470,50,200,50);
	    	lb_actors.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_actors);
	    	
	    	actor1comboBox.setBounds(470,100,200,30);
	    	frame.add(actor1comboBox);
	    	
	    	actor2comboBox.setBounds(470,140,200,30);
	    	frame.add(actor2comboBox);
	    	
	    	actor3comboBox.setBounds(470,180,200,30);
	    	frame.add(actor3comboBox);
	    	
	    	actor4comboBox.setBounds(470,220,200,30);
	    	frame.add(actor4comboBox);
	    	
	    	JLabel lb_director = new JLabel("Director");
	    	lb_director.setBounds(470,270,200,50);
	    	lb_director.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_director);
	    	
	    	directorcomboBox.setBounds(470,320,200,30);
	    	frame.add(directorcomboBox);
	    	
	    	//Tags
	    	JLabel lb_tags = new JLabel("Tags Ids and Values");
	    	lb_tags.setBounds(680,10,200,50);
	    	lb_tags.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_tags);
	    	
	    	JScrollPane jp_tag = new JScrollPane(tag_checkBoxList);
	    	jp_tag.setBounds(680, 50, 200, 400);
	    	frame.add(jp_tag);
	    	
	    	JLabel lb_tags_weight = new JLabel("Tags Weight:");
	    	lb_tags_weight.setBounds(680,450,200,50);
	    	lb_tags_weight.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_tags_weight);
	    	
	    	tagsweightcomboBox.setBounds(680,500,100,30);
	    	tagsweightcomboBox.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(tagsweightcomboBox);
	    	
	    	tagsweighttextField.setBounds(785,500,95,30);
	    	frame.add(tagsweighttextField);
	    	
	    	//Movie Result
	    	JLabel lb_movie_result = new JLabel("Movie Result");
	    	lb_movie_result.setBounds(1020,10,200,50);
	    	lb_movie_result.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_movie_result);
	    	
	    	
	    	JScrollPane jp_movie_resulty = new JScrollPane(movieresult_checkBoxList);
	    	jp_movie_resulty.setBounds(920, 50, 400, 400);
	    	frame.add(jp_movie_resulty);
	    	
	    	/*	  
	    	JTextArea jt_movie_result = new JTextArea("", 10, 10);
	    	JScrollPane scroll_movie_result = new JScrollPane(jt_movie_result);
	    	scroll_movie_result.setBounds(920, 50, 400, 400);
	    	frame.add(scroll_movie_result);
	    	*/
	    	
	    	//User Result
	    	JLabel lb_user_result = new JLabel("User Result");
	    	lb_user_result.setBounds(1020,450,200,50);
	    	lb_user_result.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(lb_user_result);
	    	
	    	JTextArea jt_user_result = new JTextArea("", 10, 10);
	    	JScrollPane scroll_user_result = new JScrollPane(jt_user_result);
	    	scroll_user_result.setBounds(920, 500, 400, 400);
	    	frame.add(scroll_user_result);
	    	
	    	//Between Attributes
	    	JLabel lb_bwtween_attributes = new JLabel("Search Between Attributes'value (Default is AND)");
	    	lb_bwtween_attributes.setBounds(20,570,600,50);
	    	lb_bwtween_attributes.setFont(new Font("Arial", Font.PLAIN, 24));
	    	frame.add(lb_bwtween_attributes);
	    	
	    	conditioncomboBox.setBounds(600,570,200,50);
	    	conditioncomboBox.setFont(new Font("Arial", Font.PLAIN, 20));
	    	frame.add(conditioncomboBox);
	    	
	    	
	    	//sql Result
	    	JTextArea jt = new JTextArea("Query Result： \n", 10, 10);
	    	JScrollPane scroll = new JScrollPane(jt); 
	    	scroll.setBounds(20,650,800,130);
	    	frame.add(scroll);
	    	
	    	//Button Excute Movie Query
	    	JButton excute_movie_query = new JButton("Excute Movie Query");
	    	excute_movie_query.setBounds(100, 800, 200, 100);
	    	frame.add(excute_movie_query);
	    	
	    	
	    	//Button Excute User Query
	    	JButton excute_user_query = new JButton("Excute User Query");
	    	excute_user_query.setBounds(500, 800, 200, 100);
	    	frame.add(excute_user_query);
	    	
	    	//frame.pack();
	    	frame.setVisible(true);
	    	
	    	
	    	excute_movie_query.addActionListener(new ActionListener() {

	    	    @Override
	    	    public void actionPerformed(ActionEvent e) {
	    	        //your actions
	    	    	String genres = "";
	    	    	String movieyear = "";
	    	    	String countries = "";
	    	    	String actors = "";
	    	    	String directors = "";
	    	    	String tags = "";
	    	    	String tagsweights = "";
	    	    	String movie_result = "";
	    	    	String startdate = "";
	    	    	String enddate = "";
	    	    	clickedMovieresult.clear();
	    	    	movieresult_model.removeAllElements();
	    	    	movieresultdata.clear();
	    	    	//genres
					for (int i = 0; i < clickedGenre.size(); i++) {
						if (i == 0) {
							genres += "AND ";
							if (condition[0].equals("OR"))
								genres += "(";
							genres += "m.id in (SELECT mg.movieID FROM MOVIE_GENRES mg WHERE mg.GENRE =  '"+clickedGenre.get(i)+"') \n";
						}
						else {
							genres += condition[0]+" m.id IN (SELECT mg.movieID FROM MOVIE_GENRES mg WHERE  mg.GENRE =  '"+clickedGenre.get(i)+"') \n";
							
						}
							
					}
					//Year
					if (date_from[0].length() > 0) {
	            		movieyear += "AND m.year >=" + date_from[0].split("-")[0] + " \n";
	            	}
	            	if (date_to[0].length() > 0) {
	            		movieyear += "AND m.year <=" + date_to[0].split("-")[0] + " \n";
	            	}
					//countries
					for (int i = 0; i < clickedCountry.size(); i++) {
						if (i == 0) {
							countries += "AND ";
							if (condition[0].equals("OR"))
								countries += "(";
							countries += " m.id IN (SELECT mc.movieID FROM MOVIE_COUNTRIES mc WHERE mc.COUNTRY =  '"+clickedCountry.get(i)+"') \n";
						}
						else {
							countries += condition[0]+" m.id IN (SELECT mc.movieID FROM MOVIE_COUNTRIES mc WHERE  mc.COUNTRY =  '"+clickedCountry.get(i)+"') \n";
							
						}
	            	}
					
					//tags
					for (int i = 0; i < clickedTag.size(); i++) {
						String tagID = clickedTag.get(i).split(" ")[0];
						if (i == 0) {
							tags += "AND ";
							if (condition[0].equals("OR"))
								tags += "(";
							tags += " m.id IN (SELECT mt.movieID FROM MOVIE_TAGS mt WHERE mt.tagID = "+tagID+") \n";
						}
						else {
							tags += condition[0]+"  m.id IN (SELECT mt.movieID FROM MOVIE_TAGS mt WHERE mt.tagID = "+tagID+") \n";
						}
	            	}
					
					if (tagsweighttextField.getText().trim().length() > 0 && tags_weight[0].length() > 0){
						tagsweights += "AND mt.tagWeight " + tags_weight[0] + " "+ tagsweighttextField.getText() + " \n";
	    			}
					
					//Actors
					chosenactors.clear();
					if (actor1comboBox.getSelectedItem().toString().length() > 0) {
						chosenactors.add(actor1comboBox.getSelectedItem().toString());
					}
					if (actor2comboBox.getSelectedItem().toString().length() > 0) {
						chosenactors.add(actor2comboBox.getSelectedItem().toString());
					}
					if (actor3comboBox.getSelectedItem().toString().length() > 0) {
						chosenactors.add(actor3comboBox.getSelectedItem().toString());
					}
					if (actor4comboBox.getSelectedItem().toString().length() > 0) {
						chosenactors.add(actor4comboBox.getSelectedItem().toString());
					}
					for (int i = 0; i < chosenactors.size(); i++) {
						String actorName = chosenactors.get(i);
						if (i == 0) {
							actors += "AND ";
							if (condition[0].equals("OR"))
								actors += "(";
							actors += " m.id IN (SELECT ma.movieID FROM MOVIE_ACTORS ma WHERE ma.actorname =  '"+actorName+"') \n";
						}
						else {
							actors += condition[0]+" m.id IN (SELECT ma.movieID FROM MOVIE_ACTORS ma WHERE ma.actorname =  '"+actorName+"') \n";
						}
	            	}
					
					//Director
					chosendirector.clear();
					if (directorcomboBox.getSelectedItem().toString().length() > 0) {
						chosendirector.add(directorcomboBox.getSelectedItem().toString());
					}
					for (int i = 0; i < chosendirector.size(); i++) {
						String directorName = chosendirector.get(i);
						if (i == 0) {
							directors += "AND ";
							if (condition[0].equals("OR"))
								directors += "(";
							directors += " m.id IN (SELECT md.movieID FROM MOVIE_DIRECTORS md WHERE md.directorName =  '"+directorName+"') \n";
						}
						else {
							directors += condition[0]+" m.id IN (SELECT md.movieID FROM MOVIE_DIRECTORS md WHERE md.directorName =  '"+directorName+"') \n";
						}
	            	}
					
					
					//Built query string
					String query =	"SELECT m.id, m.title , m.year, m.rtAudienceRating,m. rtAudienceNumRatings, mg.genre, mc.country \n" + 
									"FROM MOVIES m, MOVIE_COUNTRIES mc,  MOVIE_GENRES mg";
					if (tags.length() > 0 || tagsweights.length() > 0 ) {
							query += ", TAGS t, MOVIE_TAGS mt ";
					} 
					if (actors.length() > 0) {
							query += ", MOVIE_ACTORS ma";
					}
					if (directors.length() > 0) {
						query += ", MOVIE_DIRECTORS md";
					}
							query += "\n WHERE mg.movieID = m.id \n" + 
									"AND mc.movieID = m.id \n";
					if (tags.length() > 0 || tagsweights.length() > 0 ) {
							query += "AND mt.movieID = m.id \n"+
									 "AND mt.TAGID = t.id \n";
					}
					if (actors.length() > 0 ) {
						query += "AND ma.movieID = m.id \n";
					}
					if (directors.length() > 0 ) {
						query += "AND md.movieID = m.id \n";
					}
									
							query += genres;
					
					if (condition[0].equals("OR") && genres.length() > 0) {
							query += ") \n";
					}
					
							query += countries;
							
					if (condition[0].equals("OR") && countries.length() > 0) {
							query += ") \n";
					}
					
							query += tags;
							
					if (condition[0].equals("OR") && tags.length() > 0) {
							query += ") \n";
					}
					
					if (tagsweights.length() > 0) {
							query += tagsweights;
					}
							query += actors;
					if (condition[0].equals("OR") && actors.length() > 0) {
							query += ") \n";
					}
							
							query += directors;
					if (condition[0].equals("OR") && directors.length() > 0) {
								query += ") \n";
					}
					
					if (clickedGenre.size() > 0) {
							query += "AND mg.genre in ("; 
							for (int i = 0; i < clickedGenre.size(); i++) {
								if (i == clickedGenre.size() - 1) {
									query += "'"+clickedGenre.get(i)+"') \n";
								}
								else {
									query += "'"+clickedGenre.get(i)+"', ";
								}
								
							}
					}
							query += movieyear+
									 "GROUP BY m.id, m.title , m.year, m.rtAudienceRating,m. rtAudienceNumRatings, mg.genre, mc.country \n"+
									 "ORDER BY m.id ";
							
							//System.out.println(query);	
		            try {
						ResultSet excute_movie_query_rs = con.createStatement().executeQuery(query);
						while (excute_movie_query_rs.next()) {
						  	String mid = excute_movie_query_rs.getString("id");
						  	String title = excute_movie_query_rs.getString("title");
						  	String year = excute_movie_query_rs.getString("year");
						  	String genre = excute_movie_query_rs.getString("genre");
						  	String country = excute_movie_query_rs.getString("country");
						  	String rtAudienceRating = excute_movie_query_rs.getString("rtAudienceRating");
						  	String rtAudienceNumRatings = excute_movie_query_rs.getString("rtAudienceNumRatings");
						  	movie_result = mid+", "+title+", "+genre+", "+year+", "+country+", "+rtAudienceRating+", "+rtAudienceNumRatings; 
						  	movieresultdata.add(movie_result);
						}
						
					
						jt.setText("");
			            jt.setFont(new Font("Serif", Font.BOLD, 20));
			            jt.append(query); 
			           
						
						for (String movieresult : movieresultdata) {
							movieresult_model.addElement(new JCheckBox(movieresult));
						
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
	    	    }
	    	});
	    	
	    	excute_user_query.addActionListener(new ActionListener() {

	    	    @Override
	    	    public void actionPerformed(ActionEvent e) {
	    	    	String movieResult = "";
	    	    	String tag = "";
	    	    	String userResult = "";
	    	    	String query = "";
	    	    	for (int i = 0; i < clickedMovieresult.size(); i++) {
						String movieID = clickedMovieresult.get(i);
						if (i == 0) {
							movieResult += "AND ";
							if (condition[0].equals("OR"))
								movieResult += "(";
							movieResult += "  ut.userID IN (SELECT ut.userID FROM USER_TAGGEDMOVIES ut WHERE ut.movieID =  "+movieID+") \n";
						}
						else {
							movieResult += condition[0]+" ut.userID IN (SELECT ut.userID FROM USER_TAGGEDMOVIES ut WHERE ut.movieID = "+movieID+") \n";
						}
	            	}
	    	    	
	    	    	for (int i = 0; i < clickedTag.size(); i++) {
						String tagID = clickedTag.get(i).split(" ")[0];
						if (i == 0) {
							tag += "AND ";
							if (condition[0].equals("OR"))
								tag += "(";
							tag += "  ut.userID IN (SELECT ut.userID FROM USER_TAGGEDMOVIES ut WHERE  ut.tagID = "+tagID+") \n";
						}
						else {
							tag += condition[0]+" ut.userID IN (SELECT ut.userID FROM USER_TAGGEDMOVIES ut WHERE  ut.tagID = "+tagID+") \n";
						}
	            	}
	    	    	
	    	    	if (clickedMovieresult.size() > 0 && clickedTag.size() > 0)	{
	    	    		query = "SELECt ut.userID \n"+ 
    	    					"FROM USER_TAGGEDMOVIES ut, MOVIES m , TAGS t \n"+
    	    					"WHERE ut.movieID = m.id  \n" +
    	    					"AND ut.tagID = t.id \n"+
    	    					movieResult;
		    	    	if (movieResult.length() > 0 && condition[0].equals("OR")) {
		    	    			query += ")";
		    	    	}
		    	    	query += tag;
		    	    	if (tag.length() > 0 && condition[0].equals("OR")) {
		    	    			query += ")";
		    	    	}
    	    			query += "GROUP BY ut.userID \n"+
    	    					 "ORDER BY ut.userID";
	    	    	//System.out.println(query);
	    	    	
	    	    			
	    	    	try {
						ResultSet GetUserResult = con.createStatement().executeQuery(query);
						while (GetUserResult.next()) {
							String userID = GetUserResult.getString("userID");
							userResult += userID + "\n";
;						}
						
						jt_user_result.setText("");
						jt_user_result.setFont(new Font("Serif", Font.BOLD, 20));
						jt_user_result.append(userResult);
						
						jt.setText("");
						jt.setFont(new Font("Serif", Font.BOLD, 20));
			            jt.append(query);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
	    	    	} else {
	    	    		jt_user_result.setText("");
						
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
	            	
	            	actorlistData.clear();
	            	chosenactors.clear();
	            	directorlistData.clear();
	            	chosendirector.clear();
	            	
					setcountry(con,clickedGenre, date_from[0], date_to[0],condition[0],clickedCountry,country_model,actor1_model,
							actor2_model,actor2_model,actor3_model,actor4_model,director_model);

	    		settag(con,clickedGenre, date_from[0], date_to[0],clickedCountry,clickedTag,actor1comboBox.getSelectedItem().toString(), 
						actor2comboBox.getSelectedItem().toString(),actor3comboBox.getSelectedItem().toString(),
						actor4comboBox.getSelectedItem().toString(),directorcomboBox.getSelectedItem().toString(),condition[0],tag_model);
	    		// Repaint cell

				list_genre.repaint(list_genre.getCellBounds(index, index));
	    		}
	      	});
	    	

	    
	    	showStartDate.getDocument().addDocumentListener(new DocumentListener() {
	    			public   void changedUpdate(DocumentEvent e) {
	    			  	//System.out.println(showStartDate.getText());
	    			}

					@Override
					public  void insertUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub
						if(!showStartDate.getText().equals("Click to select date")) {
							
							date_from[0] = showStartDate.getText();
						} else {
							date_from[0] = "";
							
						}
						setcountry(con,clickedGenre, date_from[0], date_to[0],condition[0],clickedCountry,country_model,actor1_model,
								actor2_model,actor2_model,actor3_model,actor4_model,director_model);
						settag(con,clickedGenre, date_from[0], date_to[0],clickedCountry,clickedTag, actor1comboBox.getSelectedItem().toString(), 
								actor2comboBox.getSelectedItem().toString(),actor3comboBox.getSelectedItem().toString(),
								actor4comboBox.getSelectedItem().toString(),directorcomboBox.getSelectedItem().toString(),condition[0],tag_model);
					}
					@Override
					public void removeUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub
						//System.out.println(showStartDate.getText());
					}
	    			  
	    	});
	    	
	    	showEndDate.getDocument().addDocumentListener(new DocumentListener() {
    			public   void changedUpdate(DocumentEvent e) {
    			  	//System.out.println(showStartDate.getText());
    			}

				@Override
				public  void insertUpdate(DocumentEvent arg0) {
					// TODO Auto-generated method stub
					if(!showEndDate.getText().equals("Click to select date")) {
						
						date_to[0] = showEndDate.getText();
					} else {
						date_to[0] = "";
						
					}
					setcountry(con,clickedGenre, date_from[0], date_to[0],condition[0],clickedCountry,country_model,actor1_model,
							actor2_model,actor2_model,actor3_model,actor4_model,director_model);
					settag(con,clickedGenre, date_from[0], date_to[0],clickedCountry,clickedTag, actor1comboBox.getSelectedItem().toString(), 
							actor2comboBox.getSelectedItem().toString(),actor3comboBox.getSelectedItem().toString(),
							actor4comboBox.getSelectedItem().toString(),directorcomboBox.getSelectedItem().toString(),condition[0],tag_model);
			
				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					// TODO Auto-generated method stub
					//System.out.println(showStartDate.getText());
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
	            	
	            	genres[0] = "";
	            	for (int i = 0; i < clickedGenre.size(); i++) {
						if (i == 0) {
							genres[0] += "And ";
							if (condition[0].equals("OR"))
								genres[0] += "(";
							genres[0] += "m.id in (Select m.id from MOVIE_GENRES mg, MOVIES m WHERE mg.movieID = m.id  \r\n" +
								"And mg.GENRE =  '"+clickedGenre.get(i)+"') ";
						}
						else {
							genres[0] += condition[0]+" m.id in (Select m.id from MOVIE_GENRES mg, MOVIES m WHERE mg.movieID = m.id  \r\n" + 
									"And mg.GENRE =  '"+clickedGenre.get(i)+"') ";
							
						}
	            	}
	            	
	            	settag(con,clickedGenre, date_from[0], date_to[0],clickedCountry,clickedTag, actor1comboBox.getSelectedItem().toString(), 
							actor2comboBox.getSelectedItem().toString(),actor3comboBox.getSelectedItem().toString(),
							actor4comboBox.getSelectedItem().toString(),directorcomboBox.getSelectedItem().toString(),condition[0],tag_model);
	            	actor1_model.removeAllElements();
	            	actor1_model.addElement("");
					actor2_model.removeAllElements();
					actor2_model.addElement("");
					actor3_model.removeAllElements();
					actor3_model.addElement("");
					actor4_model.removeAllElements();
					actor4_model.addElement("");
					director_model.removeAllElements();	
					director_model.addElement("");	
	            	if (clickedCountry.size() > 0) {
		            	countires[0] = "";
		            	for (int i = 0; i < clickedCountry.size(); i++) {
							if (i == 0) {
								countires[0] += "And ";
								if (condition[0].equals("OR"))
									countires[0] += "(";
								countires[0] += " m.id in (Select m.id from MOVIE_COUNTRIES mc, MOVIES m WHERE mc.movieID = m.id  \r\n" +
									"And mc.COUNTRY =  '"+clickedCountry.get(i)+"') ";
							}
							else {
								countires[0] += condition[0]+" m.id in (Select m.id from MOVIE_COUNTRIES mc, MOVIES m WHERE mc.movieID = m.id  \r\n" + 
										"And mc.COUNTRY =  '"+clickedCountry.get(i)+"') ";
								
							}
		            	}
							
		            	
		            		
						String actor_query = "Select ma.actorName \n" + 
								"from MOVIES m, MOVIE_COUNTRIES mc,  MOVIE_GENRES mg, MOVIE_ACTORS ma \n" + 
								"where mg.movieID = m.id \n" + 
								"AND mc.movieID = m.id \n" + 
								"AND ma.movieID = m.id \n" + 
								genres[0]+"\n";
								if (condition[0].equals("OR")) {
									actor_query += ")";
								}
										
								actor_query += countires[0]+"\n";
								if (condition[0].equals("OR")) {
									actor_query += ")";	
								}	
								actor_query += "GROUP BY ma.actorName \n" + 
								"ORDER BY ma.actorName ";
								//System.out.println(query2);
								
						String director_query = "Select md.directorName \n" + 
								"from MOVIES m, MOVIE_COUNTRIES mc,  MOVIE_GENRES mg, MOVIE_DIRECTORS md \n" + 
								"where mg.movieID = m.id \n" + 
								"AND mc.movieID = m.id \n" + 
								"AND md.movieID = m.id \n" + 
								genres[0]+"\n";
								if (condition[0].equals("OR")) {
									director_query += ")";
								}
										
								director_query += countires[0]+"\n";
								if (condition[0].equals("OR")) {
									director_query += ")";	
								}	
								director_query += "GROUP BY md.directorName \n" + 
								"ORDER BY md.directorName ";
								//System.out.println(query2);
						try {
							ResultSet GetActors = con.createStatement().executeQuery(actor_query);
							ResultSet GetDirector = con.createStatement().executeQuery(director_query);
							tagdata.clear();
							actorlistData.clear();
							chosenactors.clear();
							directorlistData.clear();
							chosendirector.clear();
						
							
							while (GetActors.next()) {
								String actor = GetActors.getString("actorName");
								if (actor != null && actor.length() > 0)
									actorlistData.add(actor);  	
							}
							
							while (GetDirector.next()) {
								String director = GetDirector.getString("directorName");
								if (director != null && director.length() > 0)
									directorlistData.add(director);  	
							}
							
							
							for (String actor : actorlistData) {
								actor1_model.addElement(actor);
								actor2_model.addElement(actor);
								actor3_model.addElement(actor);
								actor4_model.addElement(actor);
							}
							
							for (String director : directorlistData) {
								director_model.addElement(director);
							}
							
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            	
	            	}

	            	// Repaint cell
	 
	            	list_country.repaint(list_country.getCellBounds(index, index));
	            	
	            	
	    		}
				
	      	});
	    		
	    	tag_checkBoxList.addMouseListener(new MouseAdapter() {
	    		public void mouseClicked(MouseEvent event) {
	 

	    			JCheckBoxList list_tag = (JCheckBoxList) event.getSource();
	    			//JList<CheckboxListItem> list_genre = (JList<CheckboxListItem>) event.getSource();
	    			
	    			
	    			// Get index of item clicked
	 
	            	int index = list_tag.locationToIndex(event.getPoint());
	            	JCheckBox item = list_tag.getModel().getElementAt(index);
	 
	            	// Toggle selected state
	 
	            	item.setSelected(!item.isSelected());
	            	
	            	if (item.isSelected()) {
	            		clickedTag.add(item.getLabel());
	            	}
	            	else {
	            		clickedTag.remove(item.getLabel());
	            	}
	            	

	            	// Repaint cell
	 
	            	list_tag.repaint(list_tag.getCellBounds(index, index));
	            	
	            	
	    		}
				
	      	});
	    	
	    	movieresult_checkBoxList.addMouseListener(new MouseAdapter() {
	    		public void mouseClicked(MouseEvent event) {
	 

	    			JCheckBoxList list_movieresult = (JCheckBoxList) event.getSource();
	    			//JList<CheckboxListItem> list_genre = (JList<CheckboxListItem>) event.getSource();
	    			
	    			
	    			// Get index of item clicked
	 
	            	int index = list_movieresult.locationToIndex(event.getPoint());
	            	JCheckBox item = list_movieresult.getModel().getElementAt(index);
	 
	            	// Toggle selected state
	 
	            	item.setSelected(!item.isSelected());
	            	
	            	if (item.isSelected()) {
	            		clickedMovieresult.add(item.getLabel().split(",")[0]);
	            	}
	            	else {
	            		clickedMovieresult.remove(item.getLabel().split(",")[0]);
	            	}
	            	

	            	// Repaint cell
	 
	            	list_movieresult.repaint(list_movieresult.getCellBounds(index, index));
	            	
	            	
	    		}
				
	      	});
	    	// 
	    	conditioncomboBox.addItemListener(new ItemListener() {
	            @Override
	            public void itemStateChanged(ItemEvent e) {
	                // 
	            	
	                if (e.getStateChange() == ItemEvent.SELECTED) {
	                	if (conditioncomboBox.getSelectedIndex() != 0) {
	                		condition[0] = conditioncomboBox.getSelectedItem().toString();
	                		//System.out.println(condition[0]);
	                	}
	                	else {
	                		condition[0] = "AND";
	                	}
	                }
	            }
	        });
	    	
	        tagsweightcomboBox.addItemListener(new ItemListener() {
	            @Override
	            public void itemStateChanged(ItemEvent e) {
	                
	            	
	                if (e.getStateChange() == ItemEvent.SELECTED) {
	                	if (tagsweightcomboBox.getSelectedIndex() != 0) {
	                		tags_weight[0] = tagsweightcomboBox.getSelectedItem().toString();
	                		//System.out.println(tags_weight[0]);
	                	}
	                	else {
	                		tags_weight[0] = "";
	                	}
    
	                }
	            }
	        });
	        actor1comboBox.addItemListener(new ItemListener() {
	            @Override
	            public void itemStateChanged(ItemEvent e) {
	                
	            	
	                if (e.getStateChange() == ItemEvent.SELECTED) {
	                	if (actor1comboBox.getSelectedIndex() != 0) {
	                		//chosenactors.add(actor1comboBox.getSelectedItem().toString());
	                		//System.out.println(tags_weight[0]);
	                	}
	                	else {
	                		//chosenactors.remove(actor1comboBox.getSelectedItem().toString());
	                	}
	                	settag(con,clickedGenre, date_from[0], date_to[0],clickedCountry,clickedTag, actor1comboBox.getSelectedItem().toString(), 
								actor2comboBox.getSelectedItem().toString(),actor3comboBox.getSelectedItem().toString(),
								actor4comboBox.getSelectedItem().toString(),directorcomboBox.getSelectedItem().toString(),condition[0],tag_model);
	                }
	                
	            }
	        });
	        actor2comboBox.addItemListener(new ItemListener() {
	            @Override
	            public void itemStateChanged(ItemEvent e) {
	                
	            	
	                if (e.getStateChange() == ItemEvent.SELECTED) {
	                	if (actor2comboBox.getSelectedIndex() != 0) {
	                		//chosenactors.add(actor2comboBox.getSelectedItem().toString());
	                		//System.out.println(tags_weight[0]);
	                	}
	                	else {
	                		//chosenactors.remove(actor2comboBox.getSelectedItem().toString());
	                	}
	                	settag(con,clickedGenre, date_from[0], date_to[0],clickedCountry,clickedTag, actor1comboBox.getSelectedItem().toString(), 
								actor2comboBox.getSelectedItem().toString(),actor3comboBox.getSelectedItem().toString(),
								actor4comboBox.getSelectedItem().toString(),directorcomboBox.getSelectedItem().toString(),condition[0],tag_model);
	                }
	                
	            }
	        });
	        actor3comboBox.addItemListener(new ItemListener() {
	            @Override
	            public void itemStateChanged(ItemEvent e) {
	                
	            	
	                if (e.getStateChange() == ItemEvent.SELECTED) {
	                	if (actor3comboBox.getSelectedIndex() != 0) {
	                		//chosenactors.add(actor3comboBox.getSelectedItem().toString());
	                		//System.out.println(tags_weight[0]);
	                	}
	                	else {

	                		//chosenactors.remove(actor3comboBox.getSelectedItem().toString());
	                	}
	                	settag(con,clickedGenre, date_from[0], date_to[0],clickedCountry,clickedTag, actor1comboBox.getSelectedItem().toString(), 
								actor2comboBox.getSelectedItem().toString(),actor3comboBox.getSelectedItem().toString(),
								actor4comboBox.getSelectedItem().toString(),directorcomboBox.getSelectedItem().toString(),condition[0],tag_model);
	                }
	               
	            }
	        });
	        actor4comboBox.addItemListener(new ItemListener() {
	            @Override
	            public void itemStateChanged(ItemEvent e) {
	                
	            	
	                if (e.getStateChange() == ItemEvent.SELECTED) {
	                	if (actor4comboBox.getSelectedIndex() != 0) {
	                		//chosenactors.add(actor4comboBox.getSelectedItem().toString());
	                		//System.out.println(tags_weight[0]);
	                	}
	                	else {
	                		//chosenactors.remove(actor4comboBox.getSelectedItem().toString());
	                	}
	                	settag(con,clickedGenre, date_from[0], date_to[0],clickedCountry,clickedTag, actor1comboBox.getSelectedItem().toString(), 
								actor2comboBox.getSelectedItem().toString(),actor3comboBox.getSelectedItem().toString(),
								actor4comboBox.getSelectedItem().toString(),directorcomboBox.getSelectedItem().toString(),condition[0],tag_model);
	                }
	                
	            }
	        });
	        
	        directorcomboBox.addItemListener(new ItemListener() {
	            @Override
	            public void itemStateChanged(ItemEvent e) {
	                
	            	
	                if (e.getStateChange() == ItemEvent.SELECTED) {
	                	if (directorcomboBox.getSelectedIndex() != 0) {
	                		
	                		//chosendirector.add(directorcomboBox.getSelectedItem().toString());
	                		//System.out.println(tags_weight[0]);
	                	}
	                	else {
	                		//chosendirector.remove(directorcomboBox.getSelectedItem().toString());
	                	}
	                	settag(con,clickedGenre, date_from[0], date_to[0],clickedCountry,clickedTag, actor1comboBox.getSelectedItem().toString(), 
								actor2comboBox.getSelectedItem().toString(),actor3comboBox.getSelectedItem().toString(),
								actor4comboBox.getSelectedItem().toString(),directorcomboBox.getSelectedItem().toString(),condition[0],tag_model);
	                }
	                
	            }
	        });
	   }
		










		protected static void setcountry(Connection con, List<String> clickedGenre, String date_from, String date_to,
			String condition,List<String> clickedCountry, DefaultListModel<JCheckBox> country_model, DefaultComboBoxModel<String> actor1_model, DefaultComboBoxModel<String> actor2_model,
			DefaultComboBoxModel<String> actor2_model2, DefaultComboBoxModel<String> actor3_model, DefaultComboBoxModel<String> actor4_model, DefaultComboBoxModel<String> director_model) {
		// TODO Auto-generated method stub
			clickedCountry.clear();
			country_model.removeAllElements();
			actor1_model.removeAllElements();
			actor1_model.addElement("");
			actor2_model.removeAllElements();
			actor2_model.addElement("");
			actor3_model.removeAllElements();
			actor3_model.addElement("");
			actor4_model.removeAllElements();
			actor4_model.addElement("");
			director_model.removeAllElements();
			director_model.addElement("");
			String genres = "";
			String movieyear = "";
			List<String> countrydata = new ArrayList<>();
        	if (clickedGenre.size() > 0 && (date_from.length() > 0 || date_to.length() > 0)) {
            	genres = "";
            	for (int i = 0; i < clickedGenre.size(); i++) {
					if (i == 0) {
						genres += "And ";
						if (condition.equals("OR"))
							genres += "(";
						genres += "m.id in (Select m.id from MOVIE_GENRES mg, MOVIES m WHERE mg.movieID = m.id  \r\n" +
							"And mg.GENRE =  '"+clickedGenre.get(i)+"') ";
					}
					else {
						genres += condition+" m.id in (Select m.id from MOVIE_GENRES mg, MOVIES m WHERE mg.movieID = m.id  \r\n" + 
								"And mg.GENRE =  '"+clickedGenre.get(i)+"') ";
						
					}
						
				}
            	if (date_from.length() > 0) {
            		movieyear += "AND m.year >=" + date_from.split("-")[0] + " \n";
            	}
            	if (date_to.length() > 0) {
            		movieyear += "AND m.year <=" + date_to.split("-")[0] + " \n";
            	}
            	String query = "SELECT mc.country \n" + 
						"FROM MOVIE_COUNTRIES mc,  MOVIE_GENRES mg, MOVIES m \n" + 
						"WHERE mg.movieID = m.id \n" + 
						"AND mc.movieID = m.id \n" + 
						genres+"\n";
				if (condition.equals("OR"))
						query += ")";
						query += movieyear+ 
								"GROUP BY mc.country \n"+
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
					
					
					
					//jp_country.revalidate();
					//country_checkBoxList.revalidate();
					
					for (String country : countrydata) {
						country_model.addElement(new JCheckBox(country));
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
        	}
        	
        	
        	
		}
	



		protected static void settag(Connection con, List<String> clickedGenre, String date_from, String date_to, 
				 List<String> clickedCountry, List<String> clickedTag, String actor1, String actor2, String actor3, 
				String actor4, String director, String condition,DefaultListModel<JCheckBox> tag_model) {
		// TODO Auto-generated method stub
			String genres = "";
	    	String movieyear = "";
	    	String countries = "";
	    	String actors = "";
	    	String directors = "";
	    	List<String> chosenactors = new ArrayList<>();
	    	List<String> tagdata = new ArrayList<>();
	    	clickedTag.clear();
	    	tag_model.removeAllElements();
	    	if (clickedGenre.size() > 0 || date_from.length() > 0 || date_to.length() > 0) {
				for (int i = 0; i < clickedGenre.size(); i++) {
					if (i == 0) {
						genres += "AND ";
						if (condition.equals("OR"))
							genres += "(";
						genres += "m.id in (SELECT mg.movieID FROM MOVIE_GENRES mg WHERE mg.GENRE =  '"+clickedGenre.get(i)+"') \n";
					}
					else {
						genres += condition+" m.id IN (SELECT mg.movieID FROM MOVIE_GENRES mg WHERE  mg.GENRE =  '"+clickedGenre.get(i)+"') \n";
						
					}
						
				}
				//Year
				if (date_from.length() > 0 && !date_from.equals("Click to select date")) {
	        		movieyear += "AND m.year >=" + date_from.split("-")[0] + " \n";
	        	}
	        	if (date_to.length() > 0 && !date_to.equals("Click to select date")) {
	        		movieyear += "AND m.year <=" + date_to.split("-")[0] + " \n";
	        	}
				//countries
				for (int i = 0; i < clickedCountry.size(); i++) {
					if (i == 0) {
						countries += "AND ";
						if (condition.equals("OR"))
							countries += "(";
						countries += " m.id IN (SELECT mc.movieID FROM MOVIE_COUNTRIES mc WHERE mc.COUNTRY =  '"+clickedCountry.get(i)+"') \n";
					}
					else {
						countries += condition+" m.id IN (SELECT mc.movieID FROM MOVIE_COUNTRIES mc WHERE  mc.COUNTRY =  '"+clickedCountry.get(i)+"') \n";
						
					}
	        	}
				
				
				
				//Actors
				if (actor1.length() > 0) {
					chosenactors.add(actor1);
				}
				if (actor2.length() > 0) {
					chosenactors.add(actor2);
				}
				if (actor3.length() > 0) {
					chosenactors.add(actor3);
				}
				if (actor4.length() > 0) {
					chosenactors.add(actor4);
				}
				for (int i = 0; i < chosenactors.size(); i++) {
					String actorName = chosenactors.get(i);
					if (i == 0) {
						actors += "AND ";
						if (condition.equals("OR"))
							actors += "(";
						actors += " m.id IN (SELECT ma.movieID FROM MOVIE_ACTORS ma WHERE ma.actorname =  '"+actorName+"') \n";
					}
					else {
						actors += condition+" m.id IN (SELECT ma.movieID FROM MOVIE_ACTORS ma WHERE ma.actorname =  '"+actorName+"') \n";
					}
	        	}
				
				//Director
				
				for (int i = 0; i < director.length(); i++) {
					String directorName = director;
					if (i == 0) {
						directors += "AND ";
						if (condition.equals("OR"))
							directors += "(";
						directors += " m.id IN (SELECT md.movieID FROM MOVIE_DIRECTORS md WHERE md.directorName =  '"+directorName+"') \n";
					}
					else {
						directors += condition+" m.id IN (SELECT md.movieID FROM MOVIE_DIRECTORS md WHERE md.directorName =  '"+directorName+"') \n";
					}
	        	}
				
				
				//Built query string
				String query =	"SELECT t.id, t.value \n" + 
								"FROM  MOVIES m, TAGS t, MOVIE_TAGS mt " ;
				if (genres.length() > 0) {
					query += ", MOVIE_GENRES mg";
				}
				if (countries.length() > 0) {
					query += ", MOVIE_COUNTRIES mc";
				}
				  
				if (actors.length() > 0) {
					query += ", MOVIE_ACTORS ma";
				}
				if (directors.length() > 0) {
					query += ", MOVIE_DIRECTORS md";
				}
				query += "\n WHERE  mt.movieID = m.id \n"+
						"AND mt.TAGID = t.id \n";
						
				
				if (genres.length() > 0) {
					query += "AND mg.movieID = m.id \n";
				}	
				if (countries.length() > 0) {
					query += "AND mc.movieID = m.id \n";
				}
	
				if (actors.length() > 0 ) {
					query += "AND ma.movieID = m.id \n";
				}
				if (directors.length() > 0 ) {
					query += "AND md.movieID = m.id \n";
				}
								
						query += genres;
				
				if (condition.equals("OR") && genres.length() > 0) {
						query += ") \n";
				}
				
						query += countries;
						
				if (condition.equals("OR") && countries.length() > 0) {
						query += ") \n";
				}
	
						query += actors;
				if (condition.equals("OR") && actors.length() > 0) {
						query += ") \n";
				}
						
						query += directors;
				if (condition.equals("OR") && directors.length() > 0) {
							query += ") \n";
				}
						query += movieyear+
								 "GROUP BY t.id, t.value \n"+
								 "ORDER BY t.id, t.value ";
	        	//System.out.println(query);
	        	try {
					ResultSet GetTags = con.createStatement().executeQuery(query);
					
					while (GetTags.next()) {
					  	String tagid = GetTags.getString("id");
					  	String tagvalue = GetTags.getString("value");
					  	tagdata.add(tagid+" "+tagvalue);
					  	
					}
					
					
					
				  	
					for (String tag : tagdata) {
						tag_model.addElement(new JCheckBox(tag));
					}
					
					
	
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	
	    	}
		}



		private static DatePicker getDatePicker() {
		    final DatePicker datepick;
		    // 格式
		    String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
		    // 当前时间
		    Date date = null;
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
	 /*
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
	*/
	