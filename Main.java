import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
import java.sql.*;
 
public class Main {
 
    public static void main(String[] args) {
        final JFrame frame = new JFrame("JFileChooser Demo");
 
        final JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        fc.setCurrentDirectory(new File("C:\\tmp"));
 
        
        JButton btn2 = new JButton("Show Open Dialog");
        btn2.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                int retVal = fc.showOpenDialog(frame);
                if (retVal == JFileChooser.APPROVE_OPTION) {
                    //File selectedfiles = fc.getSelectedFile();
			String sb = fc.getSelectedFile().toString();
			int aa1 = sb.lastIndexOf("\\");
			String mainf = sb.substring(aa1+1);
                     //sb = new StringBuilder();
                    /*for (int i = 0; i < selectedfiles.length; i++) {
                        sb.append(selectedfiles[i].getName() + "\n");
                    }*/
                    JOptionPane.showMessageDialog(frame, mainf);
		    try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				System.out.println("1");
				Connection con = DriverManager.getConnection("jdbc:odbc:test");
				System.out.println("2");
				String sql = "select * from table1 where file1=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1,mainf);
				ResultSet rs = ps.executeQuery();
				System.out.println("3");
				if(rs.next())
				{
					try {
         // Open an audio input stream.
         URL url = this.getClass().getClassLoader().getResource(rs.getString("val"));
	System.out.println(rs.getString("val"));
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
      } catch (UnsupportedAudioFileException e4) {
         e4.printStackTrace();
      } catch (IOException e5) {
         e5.printStackTrace();
      } catch (LineUnavailableException e6) {
         e6.printStackTrace();
      }
				}

			}
			catch(ClassNotFoundException e1)
			{}
			catch(Exception e2)
			{}
                }
 
            }
        });
        JButton btn3 = new JButton("Show Save Dialog");
        btn3.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                fc.showSaveDialog(frame);
 
            }
        });
 
        Container pane = frame.getContentPane();
        pane.setLayout(new GridLayout(3, 1, 10, 10));
        //pane.add(btn1);
        pane.add(btn2);
        pane.add(btn3);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}