package Lab06;
import javax.swing.*;
import java.awt.*;
import javax.swing.JLabel;
import java.util.List;
import javax.swing.Timer;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Random;

public class HomeTestFrame extends JFrame
{		
	private static final long serialVersionUID = -858003067748243711L;
	private static final String QUIZ_TOTAL_TIME = "30";
	private static final String ZERO_TEXT = "0";
	private static final String ANSWER_PLACE_HOLDER_TEXT = "Symbol";
	private static final String EMPTY_STRING = "";
	private static List<AminoAcidSource> aminoAcidList;
	private static int aminoAcideListSize;
	private Random random = new Random();
	private int currentIndex;	
	private JButton	
	btnStartQuiz = new JButton("Start Quiz"),
	btnCancelQuiz = new JButton("Cancel");
	private JLabel
	lblCorrectAnswers = new JLabel(),
	lblWrongAnswers = new JLabel(),
	lblTimeRemaining = new JLabel(),
	lblQuestion = new JLabel("*****Start Quiz*****");
	private JTextField txtAnswer = new JTextField(ANSWER_PLACE_HOLDER_TEXT);		
	private Timer timer = new Timer(1000, null);
	
	static
	{
		aminoAcidList = AminoAcidSource.getAminoAcids();
		aminoAcideListSize = aminoAcidList.size();		
	}
	
	public HomeTestFrame(){}	
	public void initializeFrame()
	{
		setFrameLayout();
		setListeners();		
		setPageLayout();		
	}
	private void setPageLayout()
	{
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
	private void setListeners()
	{
		timer.addActionListener((ae) ->
		{
			int remainingTime = Integer.parseInt(lblTimeRemaining.getText()) - 1;
			if(remainingTime >= 0)
			{
				lblTimeRemaining.setText(Integer.toString(remainingTime));
			}	
			else
			{
				timer.stop();
				txtAnswer.setEnabled(false);
				txtAnswer.setText(EMPTY_STRING);
				txtAnswer.setBackground(Color.RED);
				txtAnswer.setText(ANSWER_PLACE_HOLDER_TEXT);
				lblQuestion.setText("*****TIME EXPIRED*****");
				this.toggleQuizButtons(true);						
			}
		}
				);
		
		btnStartQuiz.addActionListener((ae) ->
		{			
			timer.start();
			txtAnswer.setEnabled(true);			
			this.clearFields();
			this.toggleQuizButtons(false);
			lblQuestion.setText(getRandomAminoAcid());
			txtAnswer.setBackground(Color.GREEN);
		});
		
		btnCancelQuiz.addActionListener((ae) ->
		{
			this.toggleQuizButtons(true);
			timer.stop();	
			txtAnswer.setEnabled(false);			
			txtAnswer.setBackground(Color.RED);
			txtAnswer.setText(ANSWER_PLACE_HOLDER_TEXT);
			lblQuestion.setText("*****QUIZ CANCELLED*****");
		});				
		txtAnswer.addActionListener((ae) ->
		{
			String parsedText = txtAnswer.getText().trim();
			if(!parsedText.contentEquals(EMPTY_STRING))
			{
				this.accessAnswer(txtAnswer.getText());
				txtAnswer.setText(EMPTY_STRING);
				lblQuestion.setText(getRandomAminoAcid());		
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
		txtAnswer.setEnabled(false);
		txtAnswer.setBackground(Color.RED);
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
	private String getRandomAminoAcid()
	{		
		currentIndex = random.nextInt(aminoAcideListSize);
		return aminoAcidList.get(currentIndex).getFullName();		
	}
	private void accessAnswer(String answer)
	{
		String parsedInput = answer.trim().toUpperCase();
		if(aminoAcidList.get(currentIndex).getSingLetterSym().equals(parsedInput))
			{
				lblCorrectAnswers.setText(Integer.toString(Integer.parseInt(lblCorrectAnswers.getText()) + 1));				
			}
		else
		{
			lblWrongAnswers.setText(Integer.toString(Integer.parseInt(lblWrongAnswers.getText()) + 1));			
		}		
	}
}
