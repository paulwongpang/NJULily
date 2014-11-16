package ui.commonui.exitprogram;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import ui.myui.MyJButton;
import ui.myui.MyPanel;

public class ExitProgramPanel extends MyPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	MyJButton button_yes, button_no;
	
	public ExitProgramPanel(){
		
		int width = 400;
		int height = 130;
		
		this.setBounds((1280 - width) / 2, (720 - height) / 2, width, height);
		
		//information bar
		JLabel infoBar = new JLabel("确认是否退出系统",JLabel.CENTER);
		infoBar.setBounds(0, 0, width, 20);
		infoBar.setOpaque(true);
		infoBar.setForeground(Color.black);
		infoBar.setBackground(new Color(0, 1, 1, 0.5f));
		this.add(infoBar);
		
		//button to choose to close the program
		button_yes = new MyJButton("是");
		button_yes.setBounds(40, 60, 120, 25);
		button_yes.setForeground(Color.BLACK);
		button_yes.setBackground(new Color(0, 1, 1, 0.65f));
		button_yes.addActionListener(this);
		this.add(button_yes);
		
		//button not to choose to close the program
		button_no = new MyJButton("否");
		button_no.setBounds(240, 60, 120, 25);
		button_no.setForeground(Color.BLACK);
		button_no.setBackground(new Color(0, 1, 1, 0.65f));
		button_no.addActionListener(this);
		this.add(button_no);
		
	}
	
	public void actionPerformed(ActionEvent events){
		if(events.getSource() == button_yes){
			System.exit(0);
		}
		
		if(events.getSource() == button_no){
			this.setVisible(false);
			ExitProgramFrame.button_close.doClick();
		}
	}
}