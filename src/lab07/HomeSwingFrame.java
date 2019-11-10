package lab07;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Random;

public class HomeSwingFrame extends JFrame
{
	private static final long serialVersionUID = 8284044802749048904L;
	private static final String ANSWER_PLACE_HOLDER_TEXT = "Input a large whole number";
	private static final String EMPTY_STRING = "";	
	private static final int FILL_CONSTRAINT_HORIZONTAL = GridBagConstraints.HORIZONTAL;
	private static final int FILL_CONSTRAINT_NONE = GridBagConstraints.HORIZONTAL;
	private final JButton	
	btnStartQuiz = new JButton("Start Quiz"),
	btnCancelQuiz = new JButton("Cancel");	
	private final JTextField txtMaxNumber = new JTextField(EMPTY_STRING);	
	private final JTextArea txtOutput = new JTextArea(10,20);	
	private SwingWorker worker = null;
	
	public HomeSwingFrame(){}	
	public void setMessage(String msg)
	{
		txtOutput.setText(msg);
	}	
	public void initializeFrame()
	{
		this.setGridBagLayout();
		this.setListeners();
		this.txtMaxNumber.requestFocus();
	}
	public void requestFocusTxt()
	{
		txtMaxNumber.requestFocus();
	}
	private void setGridBagLayout()
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = this.getConstraints(0, 0, 4, 1, 0, FILL_CONSTRAINT_HORIZONTAL);		
		txtOutput.setEditable(false);
		txtOutput.setText("Input a large whole number into \nthe blue-bordered text box \nand then click the start button.");
		this.add(new JScrollPane(txtOutput), c);
		
		
		setNumberInput();
		GridBagConstraints d = this.getConstraints(1, 1, 2, 1, 10, FILL_CONSTRAINT_NONE);			
		this.add(txtMaxNumber, d);
		
		GridBagConstraints e = this.getConstraints(0, 2, 4, 1, 10, FILL_CONSTRAINT_HORIZONTAL);		
			
		this.add(btnStartQuiz, e);
		GridBagConstraints f = this.getConstraints(0, 3, 4, 1, 10, FILL_CONSTRAINT_HORIZONTAL);
		
		this.add(btnCancelQuiz, f);
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
	 
	public void toggleRunButtons(Boolean isReadyState)
	{
		btnStartQuiz.setEnabled(isReadyState);	
		btnCancelQuiz.setEnabled(!isReadyState);
	}	
	private void setListeners()
	{		
		btnStartQuiz.addActionListener((ae) ->
		{			
			//txtMaxNumber.setEnabled(true);			
			//this.toggleQuizButtons(false);			
			txtMaxNumber.setBackground(Color.GREEN);
			String inputNumber = txtMaxNumber.getText().trim();
			if(inputNumber.matches("\\d+") )
			{
				this.toggleRunButtons(false);		
				txtOutput.setText("Processing.......\n");
				this.worker = new SwingWorker(this, Long.parseLong(inputNumber));
				this.worker.start();
			}
			else
			{				
				txtOutput.setText("NOT A VALID WHOLE NUMBER\n");
				this.toggleRunButtons(true);
			}});		
		btnCancelQuiz.addActionListener((ae) ->
		{
			this.worker.interrupt();
			this.toggleRunButtons(true);			
			txtMaxNumber.setEnabled(true);			
			txtMaxNumber.setBackground(Color.RED);
			txtMaxNumber.setText(EMPTY_STRING);			
		});				
		txtMaxNumber.addActionListener((ae) ->
		{
			String parsedText = txtMaxNumber.getText().trim();
			if(!parsedText.contentEquals(EMPTY_STRING))
			{				
				txtMaxNumber.setText(EMPTY_STRING);				
			}
		});
		
		txtMaxNumber.addFocusListener(new FocusListener() {
		      public void focusGained(FocusEvent e) {
		        if(txtMaxNumber.getText().equals(ANSWER_PLACE_HOLDER_TEXT))
		        {
		        	txtMaxNumber.setText(EMPTY_STRING);
		        } }
		      public void focusLost(FocusEvent e) {}
		});		
	}
	private void setNumberInput()
	{		
		txtMaxNumber.setColumns(10);		
		txtMaxNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaxNumber.setFont(new Font("SansSerif", Font.BOLD, 30));
		txtMaxNumber.setPreferredSize(new Dimension(30, 50));		
		txtMaxNumber.setEnabled(true);
		txtMaxNumber.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
		// txtMaxNumber.setBackground(Color.RED);		
		this.toggleRunButtons(true);		
	}	
	
}
