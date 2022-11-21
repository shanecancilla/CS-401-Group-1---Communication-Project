import javax.swing.*;
import java.awt.event.*;
import java.net.Socket;

public class UILogin extends UI {
    
    String username;
    String password;

    @Override
    void buildGUI() {
        super.frame = new JFrame();
        JTextField loginUsername = new JTextField();
        JTextField loginPassword = new JTextField();

        JButton btnCancel = new JButton();
        JButton btnLogin = new JButton();

        btnCancel.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    frame.dispose();
                }
            }
        );

        btnLogin.addActionListener(new ActionListener()
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

    UILogin()
    {
        super();
    }
}
