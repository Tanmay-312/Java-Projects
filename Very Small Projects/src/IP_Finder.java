import java.awt.*;
import java.awt.event.*;
import java.net.InetAddress;
import javax.swing.*;

public class IP_Finder
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("IP Finder");
		frame.setBounds(0, 0, 1000, 500);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		
		JLabel label = new JLabel("Enter URL: ");
		label.setBounds(50,70,150,20);
		
		JTextField textField = new JTextField();
		textField.setBounds(50, 100, 800, 100);
		
		JButton button = new JButton("Find IP");
		button.setBounds(300, 100, 80, 80);
		
		button.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String url = textField.getText();
				try
				{
					InetAddress ia = InetAddress.getByName(url);
					String ip = ia.getHostAddress();
					JOptionPane.showMessageDialog(frame, ip);
				}
				catch (java.net.UnknownHostException unknownHostException)
				{
					unknownHostException.printStackTrace();
				}
			}
			
		});
		
		frame.add(textField);
		frame.add(label);
		frame.add(button);
	}

}
