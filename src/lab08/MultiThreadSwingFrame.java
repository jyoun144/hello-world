package lab08;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 

public class MultiThreadSwingFrame extends JFrame
{
	private static final long serialVersionUID = 8284044802749048904L;	
	private static final String EMPTY_STRING = "";	
	private static final int FILL_CONSTRAINT_HORIZONTAL = GridBagConstraints.HORIZONTAL;	
	private final JButton	
	btnStartQuiz = new JButton("Start"),
	btnCancelQuiz = new JButton("Cancel");	
	private final JTextField txtMaxNumber = new JTextField(EMPTY_STRING);	
	private final JTextArea txtOutput = new JTextArea(5,30);	
	private final JRadioButton rb01 = new JRadioButton("1 thread", true);
	private final JRadioButton rb02 = new JRadioButton("2 threads");
	private final JRadioButton rb3 = new JRadioButton("3 threads");
	private final JRadioButton rb4 = new JRadioButton("4 threads");			
	private int numOfConsumerThreads = 1;	
    private SwingUpdater swingUpdater = null;   
	
	public MultiThreadSwingFrame()	{}	
	
	public void setMessage(String msg)
	{
		txtOutput.setText(msg);
	}	
	public void initializeFrame()
	{
		this.setListeners();	
		this.createButtonGroup();
		this.setGridBagLayout();
		this.setNumberInput();	
		this.setCalculationOutput();
		this.toggleRunButtons(true);
		this.txtMaxNumber.requestFocus();		
	}	
	public void toggleRunButtons(Boolean isReadyState)
	{
		btnStartQuiz.setEnabled(isReadyState);	
		btnCancelQuiz.setEnabled(!isReadyState);		
		if(isReadyState)
		{
			txtMaxNumber.requestFocus(isReadyState);
		}
		this.setRadioButtons(isReadyState);
	}	
	private void setGridBagLayout()
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = this.getConstraints(0, 0, 5, 1, 0, FILL_CONSTRAINT_HORIZONTAL);
		c.ipady = 75;
		c.ipadx = 50;
		this.add(txtOutput, c);
		GridBagConstraints g = this.getConstraints(0, 1, 1, 1, 10, FILL_CONSTRAINT_HORIZONTAL);			
		this.add(rb01, g);	
		GridBagConstraints h = this.getConstraints(1, 1, 1, 1, 10, FILL_CONSTRAINT_HORIZONTAL);			
		this.add(rb02, h);	
		GridBagConstraints i = this.getConstraints(2, 1, 1, 1, 10, FILL_CONSTRAINT_HORIZONTAL);			
		this.add(rb3, i);
		GridBagConstraints j = this.getConstraints(3, 1, 1, 1, 10, FILL_CONSTRAINT_HORIZONTAL);			
		this.add(rb4, j);		
		GridBagConstraints d = this.getConstraints(0, 2, 4, 1, 10, FILL_CONSTRAINT_HORIZONTAL);			
		this.add(txtMaxNumber, d);		
		GridBagConstraints e = this.getConstraints(0, 3, 4, 1, 10, FILL_CONSTRAINT_HORIZONTAL);			
		this.add(btnStartQuiz, e);
		GridBagConstraints f = this.getConstraints(0, 4, 4, 1, 10, FILL_CONSTRAINT_HORIZONTAL);		
		this.add(btnCancelQuiz, f);
	}	
	private void setListeners()
	{		
		btnStartQuiz.addActionListener((ae) ->
		{		
			String inputNumber = txtMaxNumber.getText().trim();
			
			if(inputNumber.matches("\\d+") )
			{				
				this.toggleRunButtons(false);	
				Long inputValue;				
				try
				{
					inputValue = Long.parseLong(inputNumber);
				
					if(inputValue >= 2L && inputValue <= Long.MAX_VALUE)
					{						
						final AtomicLong numOfPrimesFound = new AtomicLong(0);
						final AtomicLong numOfSearchedNumbers = new AtomicLong(0);
						ExecutorService threadPool = Executors.newFixedThreadPool(this.numOfConsumerThreads);
						this.swingUpdater = new SwingUpdater(this, inputValue, numOfPrimesFound,  numOfSearchedNumbers, Constants.PRIME_NUM_INTERVAL, threadPool, this.numOfConsumerThreads);
						this.swingUpdater.setName("ProducerThread");
						this.swingUpdater.start();				
					}
					else
					{
						this.setMessage(Constants.ERROR_MSG_INVALID_RANGE);
					}
					txtMaxNumber.requestFocus();
				}
				catch(NumberFormatException ne)
				{					
					this.setMessage(Constants.ERROR_MSG_NUM_FORMAT); 
					 this.toggleRunButtons(true);
				}
				catch(Exception ex)
				{
					this.setMessage(Constants.ERROR_MSG_GENERAL);
					 this.toggleRunButtons(true);
				}					
			}
			else
			{
				this.setMessage("******" + inputNumber + " IS NOT A VALID WHOLE NUMBER*****\n");												
				this.toggleRunButtons(true);
				txtMaxNumber.requestFocus();
			}});
		
		btnCancelQuiz.addActionListener((ae) ->
		{
			this.toggleRunButtons(true);	
			this.swingUpdater.interrupt();						
		});	
		this.setRadioButtonListeners();
	}
	private void setNumberInput()
	{		
		txtMaxNumber.setColumns(10);		
		txtMaxNumber.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtMaxNumber.setPreferredSize(new Dimension(20, 50));		
		txtMaxNumber.setEnabled(true);
		txtMaxNumber.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));				
	}	
	private void setCalculationOutput()
	{		
		txtOutput.setEditable(false);
		txtOutput.setText(Constants.INFO_MSG_INITIAL);		
		txtOutput.setFont(new Font("SansSerif", Font.PLAIN, 15));
	}
	private GridBagConstraints getConstraints(int gridX, int gridY, int gridWidth, int gridHeight, int insetTopValue, int fillConstraint)
	{
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = gridX;
		c.gridy = gridY;
		c.gridwidth = gridWidth;
		c.gridheight = gridHeight;			
		c.fill = fillConstraint;
		c.insets = new Insets(insetTopValue, 0, 0, 0);
		return c;		
	}
	private void createButtonGroup()
	{
		ButtonGroup group = new ButtonGroup();		
		group.add(rb01);
		group.add(rb02);
		group.add(rb3);
		group.add(rb4);			
	}
	private void setRadioButtonListeners()
	{
		rb01.addActionListener( e ->
		{
			actionPerformed(e);		
		});
		rb02.addActionListener( e ->
		{
			actionPerformed(e);		
		});
		rb3.addActionListener( e ->
		{
			actionPerformed(e);		
		});
		rb4.addActionListener( e ->
		{
			actionPerformed(e);		
		});			
	}
	 private void actionPerformed(ActionEvent event) {
		 
	        Object source = event.getSource();
	        if (source == rb01) {	    	
	        	this.numOfConsumerThreads = 1;
	 	    } else if (source == rb02) {
	 	    	this.numOfConsumerThreads = 2;
	 	    	
	 	    } else if (source == rb3) {
	 	    	this.numOfConsumerThreads = 3;  
	 	    } else
	 	    {
	 	    	this.numOfConsumerThreads = 4;	 	 	    	
	 	    }
	        System.out.println("Changed num of threads to: " + this.numOfConsumerThreads);	 
	    }
	 private void setRadioButtons(boolean isEnabled)
	 {
		 rb01.setEnabled(isEnabled);
		 rb02.setEnabled(isEnabled);
		 rb3.setEnabled(isEnabled);
		 rb4.setEnabled(isEnabled);	 	 
	 }
}
