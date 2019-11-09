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
	private JTextField txtMaxNumber = new JTextField(EMPTY_STRING);	
	private JTextArea txtOutput = new JTextArea(20,40);	
	private SwingWorker worker = null;
	
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
		this.setLayout(new GridLayout(3,2));		
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
		panel.add(txtMaxNumber);		
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
			txtMaxNumber.setEnabled(true);			
			this.toggleQuizButtons(false);			
			txtMaxNumber.setBackground(Color.GREEN);
			String inputNumber = txtMaxNumber.getText().trim();
			if(inputNumber.matches("\\d+") )
			{
			 txtOutput.append("Processing.......\n");
			  this.worker = new SwingWorker(txtOutput, Long.parseLong(inputNumber));
			 this.worker.start();
			}
			else
			{
			txtOutput.append("NOT A NUMBER\n");
			this.toggleQuizButtons(true);
			}});		
		btnCancelQuiz.addActionListener((ae) ->
		{
			this.worker.interrupt();
			this.toggleQuizButtons(true);			
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
	private void setFrameLayout()
	{		
		txtMaxNumber.setColumns(10);		
		txtMaxNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaxNumber.setFont(new Font("SansSerif", Font.BOLD, 30));
		txtMaxNumber.setPreferredSize(new Dimension(30, 50));		
		txtMaxNumber.setEnabled(true);
		txtMaxNumber.setBackground(Color.RED);		
		this.toggleQuizButtons(true);		
	}	
}
