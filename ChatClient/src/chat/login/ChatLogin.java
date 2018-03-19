package chat.login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Properties;

import javax.lang.model.type.ErrorType;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import chat.client.ChatRoom;
import chat.function.ClientBean;
import chat.util.ChatUtil;

public class ChatLogin extends JFrame
{
	private static final long serialVersionUID = -3184908995664260146L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	public static HashMap<String, ClientBean> onlines;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new  Runnable()
		{
			public void run()
			{
				try
					{
						ChatLogin frame = new ChatLogin();
						frame.setVisible(true);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
			}
		});
	}
	
public ChatLogin()
{
	setTitle("登录\n");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(350, 250, 450, 300);
	contentPane = new JPanel()
			{
				private static final long serialVersionUID = 1L;
				@Override
				protected void paintComponent(Graphics g)
				{
					super.paintComponent(g);
				}
			};
	
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			textField  = new JTextField();
			textField.setForeground(Color.red);
			textField.setBounds(128, 153, 104, 24);
			textField.setOpaque(false);
			contentPane.add(textField);
			textField.setColumns(10);
			
			passwordField = new JPasswordField();
			passwordField.setForeground(Color.red);
			passwordField.setEchoChar('$');
			passwordField.setOpaque(false);
			passwordField.setBounds(128, 189, 104, 25);
			contentPane.add(passwordField);
			
			final JButton btnNewButton = new JButton();
			//btnNewButton.setIcon(arg0);
			btnNewButton.setText("登陆");
			btnNewButton.setBounds(246, 227, 70, 25);
			getRootPane().setDefaultButton(btnNewButton);//用来设置默认的按钮，回车后直接触发
			contentPane.add(btnNewButton);
			
			final JButton btnNewButton_1 = new JButton();
			//btnNewButton_1.setIcon(defaultIcon);
			btnNewButton_1.setText("注册");
			btnNewButton_1.setBounds(330,227,70,25);
			contentPane.add(btnNewButton_1);
			
			//information
			final JLabel lblNewLabel = new JLabel();
			lblNewLabel.setBounds(60, 220, 151, 21);
			lblNewLabel.setForeground(Color.red);
			getContentPane().add(lblNewLabel);
			
			//listener
			btnNewButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					Properties userInfo = new Properties();
					File file = new File("Users.properties");
					ChatUtil.loadPro(userInfo,file);
					String u_name = textField.getText();
					if(file.length()!=0)
						{
							if(userInfo.containsKey(u_name))
								{
									String u_pwd = new String(passwordField.getPassword());
									if(u_pwd.equals(userInfo.getProperty(u_name)))
										{
											try
												{
													Socket client = new Socket("localhost", 8520);
													btnNewButton.setEnabled(false);
													ChatRoom frame = new ChatRoom(u_name, client);
													frame.setVisible(true);//显示聊天界面
													setVisible(false);//影藏登录界面
												} catch (UnknownHostException e2)
												{
													e2.printStackTrace();
													errorTip("The connection with the server is interrupted, please login again");
												}catch (IOException e2) {
													e2.printStackTrace();
													errorTip("The connection with the server is interrupted, please login again");
												}
										}
									else//pwd error
										{
											lblNewLabel.setText("密码错误！");
											textField.setText("");
											passwordField.setText("");
											textField.requestFocus();
										}
									
								}
							else //username error
								{
									lblNewLabel.setText("账户不存在");
									textField.setText("");
									passwordField.setText("");
									textField.requestFocus();	
								}				
						}
				}
			});
			
			//register
			btnNewButton_1.addActionListener(new ActionListener()
			{	
				@Override
				public void actionPerformed(ActionEvent e)
				{
					btnNewButton_1.setEnabled(false);
					ChatResign frame = new ChatResign();
					frame.setVisible(true);//显示注册界面
					setVisible(false);//隐藏登录界面
				}
			});
		
}

protected void errorTip(String str)
{
		JOptionPane.showMessageDialog(contentPane, str, "ERROR", JOptionPane.ERROR_MESSAGE);
		textField.setText("");
		passwordField.setText("");
		textField.requestFocus();
}
			

}
