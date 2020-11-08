package tester;
//Imports to a work with awt on its own
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Imports to handle FileIO
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

class Motivation extends JPanel implements ActionListener {

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		Random rand = new Random();
	    String quoteText = findQuote();
	    JLabel quote = new JLabel(quoteText);
	    JButton newQuote = new JButton("New Quote");

	    public Motivation(){

	        quote.setForeground(Color.WHITE);
	        setLayout(new BorderLayout());
	        
	        add(newQuote, BorderLayout.WEST);
	        
	        add(quote);
	        
	        newQuote.addActionListener(this);
	        setBackground(Color.BLACK);
            repaint();
	        
	    }
	        
	    public String findQuote(){
	    	int random_int = (int)(Math.random() * (30 - 0 + 1) + 0);
	        int lineNum =  rand.nextInt(random_int);
	        try {
	            String quoteText = Files.readAllLines(Paths.get("/Users/Nilu/eclipse-workspace/ReMind/src/quotes.txt")).get(lineNum);
	            repaint(); 
	            return quoteText+"    ";
	               
	        }catch (FileNotFoundException e) {

	            return ("Quote Error: No Quote Found");
	        }catch (IOException e){
	            return ("IO Error: Coundn't Read Quote");
	        }
	        
	    }
	    public void paint(Graphics g){
	        
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        double width = screenSize.getWidth();

	        quote.setFont(new Font("Courier New", Font.PLAIN, (int)width/110));
	        super.paint(g);
	        repaint();
	    }

	    @Override
	        public void actionPerformed(ActionEvent e) {
	            String newQuote = findQuote();
	            quote.setText(newQuote);
	            add(quote);

	            repaint();
	        }
	
}
