package Lab06;
import javax.swing.*;
import java.awt.*;
import javax.swing.JLabel;
import java.awt.Dimension;
// import java.awt.font.*;
import javax.swing.Timer;

public class HomeTestFrame extends JFrame
{		
	private static final long serialVersionUID = -858003067748243711L;
	private static final String QUIZ_TOTAL_TIME = "30";
	private static final String ZERO_TEXT = "0";
	private JButton	
	btnStartQuiz = new JButton("Start Quiz"),
	btnCancelQuiz = new JButton("Cancel");
	private JLabel
	lblCorrectAnswers = new JLabel(),
	lblWrongAnswers = new JLabel(),
	lblTimeRemaining = new JLabel(),
	lblQuestion = new JLabel("Full Amino Acid Name");
	private JTextField txtAnswer = new JTextField();
	private Timer timer = new Timer(1000, null);
	static
	{
		
	}
	
	public HomeTestFrame()
	{	
		timer.addActionListener((ae)   ->
		{
			int remainingTime = Integer.parseInt(lblTimeRemaining.getText()) - 1;
			if(remainingTime >= 0)
			{
				lblTimeRemaining.setText(Integer.toString(remainingTime));
			}	
			else
			{
				timer.stop();
			}
		}
				);
		setFrameLayout();
		btnStartQuiz.addActionListener((ae) ->
		{
			timer.start();
			this.clearFields();
			this.toggleQuizButtons(false);			
		});
		
		btnCancelQuiz.addActionListener((ae) ->
		{
			this.toggleQuizButtons(true);
			timer.stop();			
		});				
		txtAnswer.addActionListener((ae) ->
		{
			lblQuestion.setText(lblTimeRemaining.getText());
			txtAnswer.setText("");
		});
		
		this.setLayout(new GridLayout(4,1));
		this.add(this.getTopPanel());
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
	private JPanel getTopPanel()
	{		
		JPanel panel = new JPanel();		
		panel.setLayout(new FlowLayout());
		panel.add(lblCorrectAnswers);
		panel.add(lblTimeRemaining);	
		panel.add(lblWrongAnswers);	
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
	private void clearFields()
	{
		lblCorrectAnswers.setText(ZERO_TEXT);
		lblWrongAnswers.setText(ZERO_TEXT);
		lblTimeRemaining.setText(QUIZ_TOTAL_TIME);		
	}
	private void setFrameLayout()
	{
		lblCorrectAnswers.setText(ZERO_TEXT);
		lblWrongAnswers.setText(ZERO_TEXT);
		lblTimeRemaining.setText(QUIZ_TOTAL_TIME);
		lblCorrectAnswers.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorrectAnswers.setPreferredSize(new Dimension(100, 50));
		lblWrongAnswers.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeRemaining.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnswer.setColumns(5);		
		txtAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnswer.setFont(new Font("SansSerif", Font.BOLD, 30));
		txtAnswer.setPreferredSize(new Dimension(30, 50));		
		lblWrongAnswers.setPreferredSize(new Dimension(100, 50));
		lblTimeRemaining.setPreferredSize(new Dimension(100, 50));	
		lblCorrectAnswers.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
		lblWrongAnswers.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
		lblTimeRemaining.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
		lblQuestion.setPreferredSize(new Dimension(500, 70));
		lblQuestion.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setFont(new Font("SansSerif", Font.BOLD, 35));		
		this.toggleQuizButtons(true);		
	}
	

}
