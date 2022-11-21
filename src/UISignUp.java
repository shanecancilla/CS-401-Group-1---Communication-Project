import javax.swing.*;
import java.awt.event.*;
import java.net.Socket;

public class UISignUp extends UI
{
    String username;
    String password;

    @Override
    void buildGUI()
    {
        super.frame = new JFrame();

        JTextField signupUsername = new JTextField();
        JTextField signupPassword = new JTextField();

        JButton btnCancel = new JButton();
        JButton btnSignup = new JButton();

        btnCancel.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    frame.dispose();
                }
            }
        );

        btnSignup.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {

                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    
                }
            }
        );


    }
}