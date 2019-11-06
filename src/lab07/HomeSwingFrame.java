package lab07;
import javax.swing.*;
import java.awt.*;
import javax.swing.JLabel;
import java.util.List;
import javax.swing.Timer;
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
	private final JLabel  lblQuestion = new JLabel("*****Start Quiz*****");
	private JTextField txtAnswer = new JTextField(ANSWER_PLACE_HOLDER_TEXT);	
	
	public HomeSwingFrame(){}	
	public void appendMessage(String msg)
	{
		lblQuestion.setText(msg);
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
		this.add(this.getQuestionPanel());
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
	private JPanel getCenterPanel()
	{
		JPanel panel = new JPanel();		
		panel.setLayout(new FlowLayout());	
		panel.add(txtAnswer);			
		return panel;
	}	
	private JPanel getQuestionPanel()
	{		
		JPanel panel = new JPanel();		
		panel.setLayout(new FlowLayout());
		panel.add(lblQuestion);		
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
			lblQuestion.setText("Place Holder");
			txtAnswer.setBackground(Color.GREEN);
		});
		
		btnCancelQuiz.addActionListener((ae) ->
		{
			this.toggleQuizButtons(true);			
			txtAnswer.setEnabled(true);			
			txtAnswer.setBackground(Color.RED);
			txtAnswer.setText(ANSWER_PLACE_HOLDER_TEXT);
			lblQuestion.setText("*****QUIZ CANCELLED*****");
		});				
		txtAnswer.addActionListener((ae) ->
		{
			String parsedText = txtAnswer.getText().trim();
			if(!parsedText.contentEquals(EMPTY_STRING))
			{				
				txtAnswer.setText(EMPTY_STRING);
				lblQuestion.setText("Place Holder");		
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
		lblQuestion.setPreferredSize(new Dimension(500, 70));
		lblQuestion.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setFont(new Font("SansSerif", Font.BOLD, 35));		
		this.toggleQuizButtons(true);		
	}	
}
