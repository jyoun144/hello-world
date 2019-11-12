package lab07;
import javax.swing.*;
import java.awt.*;

public class HomeSwingFrame extends JFrame
{
	private static final long serialVersionUID = 8284044802749048904L;	
	private static final String EMPTY_STRING = "";	
	private static final int FILL_CONSTRAINT_HORIZONTAL = GridBagConstraints.HORIZONTAL;	
	private static final String ERROR_MSG_NUM_FORMAT = "******NUMBER FORMAT EXCEPTION*****\n*****ENTER A VALID WHOLE NUMBER*****";
	private static final String ERROR_MSG_GENERAL = "******GENERAL SYSTEM EXCEPTION*****\n*****ENTER A VALID WHOLE NUMBER*****";
	private static final String ERROR_MSG_INVALID_RANGE = "******INVALID NUMBER ENTRY*****\n*****ENTER A WHOLE NUMBER BETWEEN 2 AND 9223372036854775807*****";
	private static final String INFO_MSG_INITIAL = "1. Input a whole number between 2 and 9223372036854775807 \n into the blue-bordered text box.  \n\n2. Click the 'Start' button to calculate the number of prime \nnumbers between 2 and the input number.";
	private final JButton	
	btnStartQuiz = new JButton("Start"),
	btnCancelQuiz = new JButton("Cancel");	
	private final JTextField txtMaxNumber = new JTextField(EMPTY_STRING);	
	private final JTextArea txtOutput = new JTextArea(10,30);	
	private SwingWorker worker = null;
	
	public HomeSwingFrame()	{}	
	
	public void setMessage(String msg)
	{
		txtOutput.setText(msg);
	}	
	public void initializeFrame()
	{
		this.setListeners();
		this.setGridBagLayout();
		setNumberInput();		
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
	}	
	private void setGridBagLayout()
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = this.getConstraints(0, 0, 4, 1, 0, FILL_CONSTRAINT_HORIZONTAL);		
		txtOutput.setEditable(false);
		txtOutput.setText(INFO_MSG_INITIAL);		
		this.add(txtOutput, c);
		GridBagConstraints d = this.getConstraints(0, 1, 4, 1, 10, FILL_CONSTRAINT_HORIZONTAL);			
		this.add(txtMaxNumber, d);		
		GridBagConstraints e = this.getConstraints(0, 2, 4, 1, 10, FILL_CONSTRAINT_HORIZONTAL);			
		this.add(btnStartQuiz, e);
		GridBagConstraints f = this.getConstraints(0, 3, 4, 1, 10, FILL_CONSTRAINT_HORIZONTAL);		
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
						this.worker = new SwingWorker(this, inputValue);
						this.worker.start();
					}
					else
					{
						this.setMessage(ERROR_MSG_INVALID_RANGE);
					}
					txtMaxNumber.requestFocus();
				}
				catch(NumberFormatException ne)
				{					
					this.setMessage(ERROR_MSG_NUM_FORMAT); 
				}
				catch(Exception ex)
				{
					this.setMessage(ERROR_MSG_GENERAL); 				
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
			this.worker.interrupt();
			this.toggleRunButtons(true);			
		});		
	}
	private void setNumberInput()
	{		
		txtMaxNumber.setColumns(10);		
		txtMaxNumber.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtMaxNumber.setPreferredSize(new Dimension(20, 50));		
		txtMaxNumber.setEnabled(true);
		txtMaxNumber.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));		
		this.toggleRunButtons(true);		
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
}
