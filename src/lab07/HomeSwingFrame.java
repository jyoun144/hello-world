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
	private static final String ANSWER_PLACE_HOLDER_TEXT = "Symbol";
	private static final String EMPTY_STRING = "";	
	private JButton	
	btnStartQuiz = new JButton("Start Quiz"),
	btnCancelQuiz = new JButton("Cancel");	
	private JTextField txtAnswer = new JTextField(EMPTY_STRING);	
	private JTextArea txtOutput = new JTextArea(20,40);
	private PrimeUtil util = null;
	
	public HomeSwingFrame(){}	
	public void appendMessage(String msg)
	{
		txtOutput.append(msg);
	}
	public void initializeFrame()
	{
		setFrameLayout();
		setListeners();		
		setPageLayout();		
	}
	private void setPageLayout()
	{
		this.setLayout(new GridLayout(3,1));		
		this.add(this.getTopPanel());
		this.add(this.getCenterPanel());
		this.add(this.getBottomPanel());	
	}
	private JPanel getBottomPanel()
	{
		JPanel panel = new JPanel();		
		panel.setLayout(new FlowLayout());
		panel.add(btnStartQuiz);		
		panel.add(btnCancelQuiz);
		return panel;
	}
	private JPanel getTopPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		txtOutput.setEditable(false);
		panel.add(new JScrollPane(txtOutput));
		return panel;
		
	}
	private JPanel getCenterPanel()
	{
		JPanel panel = new JPanel();		
		panel.setLayout(new FlowLayout());	
		panel.add(txtAnswer);		
		return panel;
	}	
	private void toggleQuizButtons(Boolean isReadyState)
	{
		btnStartQuiz.setEnabled(isReadyState);	
		btnCancelQuiz.setEnabled(!isReadyState);
	}	
	private void setListeners()
	{	
		
		btnStartQuiz.addActionListener((ae) ->
		{			
			txtAnswer.setEnabled(true);			
			this.toggleQuizButtons(false);			
			txtAnswer.setBackground(Color.GREEN);
			util = new PrimeUtil(100, this);
			util.execute();
			
		});
		
		btnCancelQuiz.addActionListener((ae) ->
		{
			this.toggleQuizButtons(true);			
			txtAnswer.setEnabled(true);			
			txtAnswer.setBackground(Color.RED);
			txtAnswer.setText(EMPTY_STRING);			
		});				
		txtAnswer.addActionListener((ae) ->
		{
			String parsedText = txtAnswer.getText().trim();
			if(!parsedText.contentEquals(EMPTY_STRING))
			{				
				txtAnswer.setText(EMPTY_STRING);				
			}
		});
		
		txtAnswer.addFocusListener(new FocusListener() {
		      public void focusGained(FocusEvent e) {
		        if(txtAnswer.getText().equals(ANSWER_PLACE_HOLDER_TEXT))
		        {
		        	txtAnswer.setText(EMPTY_STRING);
		        } }
		      public void focusLost(FocusEvent e) {}
		});		
	}
	private void setFrameLayout()
	{		
		txtAnswer.setColumns(5);		
		txtAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnswer.setFont(new Font("SansSerif", Font.BOLD, 30));
		txtAnswer.setPreferredSize(new Dimension(30, 50));		
		txtAnswer.setEnabled(true);
		txtAnswer.setBackground(Color.RED);		
		this.toggleQuizButtons(true);		
	}	
}
