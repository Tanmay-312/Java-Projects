import java.awt.event.*;
import javax.swing.*;

public class Word_Counter
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Word Counter");
		frame.setLayout(null);
		frame.setBounds(100,100,500,500);
		frame.setVisible(true);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(50,50,300,200);
		
		JButton button = new JButton("Check");
		button.setBounds(180,300,100,30);
		
		button.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						// get text
						String text = textArea.getText();
						
						// split string into array
						if(!text.equals(""))
								{
									String[] word = text.split("\\s");
									// display result
									JOptionPane.showMessageDialog(frame, "Total words are: "+word.length);
								}
					}
					
				}
		);
		
		frame.add(textArea);
		frame.add(button);
	}

}
