package IHM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Chat.IChatRoom;
import Chat.IChatter;
import Chat.ITopicManager;
import Chat.TextChatter;
import Chat.TextTopicManager;
import Client.ClientGestTopics;


public class AcceuilIHMnoserver extends JPanel implements ActionListener, IChatter, KeyListener{
	public static void main(String[] args) {

		JFrame f=new JFrame("Bob");
		IChatter Gui = new TextChatter ("Gui");
		ITopicManager gt;

			gt = new TextTopicManager();
			JPanel a=new AcceuilIHMnoserver("bob",gt);
			f.setContentPane(a);
			f.setSize(800, 600);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);
			JFrame f2=new JFrame("george");
			JPanel b=new AcceuilIHMnoserver("george",gt);
			f2.setContentPane(b);
			f2.setSize(800, 600);
			f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f2.setVisible(true);



	}
	JButton ListParticipantb,ListTopicb, decob, createb,posterb, rejoindreb;
	JTextArea textCR;
	JTextField textEcris;
	String nom;
	ITopicManager gt;
	IChatRoom cr;
	boolean connect = false;
	AcceuilIHMnoserver(String nom, ITopicManager gt)
	{
		super( new BorderLayout());
		this.gt = gt;		
		this.nom=nom;
		JPanel spane=new JPanel();

		Box ButP=Box.createVerticalBox();
		ListTopicb=new JButton("Lister les topics");
		decob=new JButton("deconnection");
		createb=new JButton("create topic");
		posterb=new JButton("poster");
		rejoindreb = new JButton("Rejoindre");
		ListParticipantb = new JButton("Lister People");
		ButP.add(ListTopicb);
		ButP.add(ListParticipantb);
		ButP.add(decob);
		ButP.add(createb);
		this.add(ButP,BorderLayout.EAST);
		textCR=new JTextArea(30,50);
	
		JScrollPane areaScrollPane = new JScrollPane(textCR);
		textCR.setEditable(false);
		textEcris=new JTextField(50);
	
		this.add(areaScrollPane,BorderLayout.WEST);
		spane.add(textEcris);
		spane.add(posterb);
		posterb.setVisible(false);
		spane.add(rejoindreb);
		this.add(spane,BorderLayout.SOUTH);
		ListTopicb.addActionListener(this);
		decob.addActionListener(this);
		createb.addActionListener(this);
		posterb.addActionListener(this);
		rejoindreb.addActionListener(this);
		ListParticipantb.addActionListener(this);
		ListParticipantb.setVisible(false);
		textEcris.addKeyListener(this);


	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==ListTopicb){
			textCR.setText(gt.listerTopics().toString());
		}
		else if(e.getSource()==createb){
			gt.CreerTopic(textEcris.getText());			
		}
		else if(e.getSource()==rejoindreb){

			if((cr=gt.RejoindreTopic(textEcris.getText()))!=null){
				cr.rejoindre(this);
				connect = true; 
				textCR.setText(null);
				rejoindreb.setVisible(false);
				posterb.setVisible(true);
				ListParticipantb.setVisible(true);
			}
			else{
				textCR.append("\nCe topic n'existe pas biaaaaaaaatch");
			}
		}
		else if(e.getSource()==posterb){
			cr.poster(textEcris.getText(), this);	
		}
		else if(e.getSource()==decob){
			if(connect==true){
				cr.quitter(this);
				rejoindreb.setVisible(true);
				posterb.setVisible(false);
				ListParticipantb.setVisible(false);
				connect=false;
			}
		}		
		else if(e.getSource()==ListParticipantb){
			textCR.append("\n"+cr.listerparticipant());
		}
	}

	@Override
	public String getPseudo() {
		// TODO Auto-generated method stub
		return nom;
	}
	@Override
	public void recevoirMessage(String msg, IChatter c) {
		// TODO Auto-generated method stub
		textCR.append("\r\n"+"Msg de "+ c.getPseudo()+" : "+ msg );
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if(arg0.getSource()==textEcris)
			{
				if(connect==false)
				{
					if((cr=gt.RejoindreTopic(textEcris.getText()))!=null){
						cr.rejoindre(this);
						connect = true; 
						textCR.setText(null);
						rejoindreb.setVisible(false);
						posterb.setVisible(true);
						ListParticipantb.setVisible(true);
					}

					else{
						textCR.append("\nCe topic n'existe pas biaaaaaaaatch");
					}
				}
				else if(connect==true)
				{
					cr.poster(textEcris.getText(), this);	
				}
			}
			textEcris.setText(null);
		}

	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
