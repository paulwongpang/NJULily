package ui.differui.salesman.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dataenum.FindTypeClient;
import dataenum.ResultMessage;
import blservice.clientblservice.ClientBLService;
import businesslogic.clientbl.ClientController;
import ui.commonui.exitfinish.ExitFinishFrame;
import ui.commonui.myui.MyColor;
import ui.commonui.myui.MyComboBox;
import ui.commonui.myui.MyJButton;
import ui.commonui.myui.MyTable;
import ui.commonui.myui.MyTextField;
import ui.commonui.warning.WarningFrame;
import vo.client.ClientVO;

public class ClientManagementUI extends JLabel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	MyJButton button_return, button_add, button_cam, button_del, button_search;
	MyTable table;
	public static MyJButton button_showAll;
	public static JButton button_delete;
	MyComboBox comboBox;
	MyTextField textField;
	
	String deleteID = "";
		
	ClientBLService controller;
	
	public ClientManagementUI(){
		this.setLayout(null);
		this.setBounds(0, 0, 1280, 720);
		
		controller = new ClientController();
		
		Color foreColor = Color.WHITE;
		Color backColor = MyColor.getColor();
		
		JLabel infoBar = new JLabel("客户管理");
		infoBar.setFont(new Font("华文细黑", Font.BOLD, 18));
		infoBar.setBounds(80, 14, 1100, 20);
		infoBar.setForeground(Color.WHITE);
		infoBar.setOpaque(false);
		this.add(infoBar);
		
		//add a combo box (for choosing the selected way)
		String[] comboBoxStr = {"-------请选择一种搜索方式-------", "模糊查找"
				, "客户编号(ID)", "客户星级", "客户分类", "客户名称", "默认业务员"};
		comboBox = new MyComboBox(75, 70 - 10, 200, 25,comboBoxStr);
		comboBox.setBackground(backColor);
		comboBox.setForeground(foreColor);
		this.add(comboBox);
		
		//add a text field (for typing the selected way)
		textField = new MyTextField(300, 70 - 10, 200, 25);
		textField.setText("  在此输入搜索关键字");
		textField.setBackground(backColor);
		textField.setForeground(foreColor);
		this.add(textField);
		
		//add a button for starting the searching process
		button_search = new MyJButton("搜索");
		button_search.setBounds(525, 70 - 10, 130, 25);
		button_search.addActionListener(this);
		button_search.setBackground(backColor);
		button_search.setForeground(foreColor);
		this.add(button_search);		
		
		//add a button for showing all the client to the table
		button_showAll = new MyJButton("显示全部客户");
		button_showAll.setBounds(1070, 70 - 10, 130, 25);
		button_showAll.addActionListener(this);
		button_showAll.setBackground(backColor);
		button_showAll.setForeground(foreColor);
		this.add(button_showAll);	
		
		button_delete = new JButton();
		button_delete.addActionListener(this);
		this.add(button_delete);
		
		//add a table for showing the information of the clients(the table is contained in a scroll pane)
		String[] headers = {"客户编号","客户分类","客户星级"
				,"客户名称","默认业务员","应收付差额","应收","应付","应付额度"};
		table = new MyTable(headers);
		
		JScrollPane jsp=new JScrollPane(table);
		JTableHeader head = table.getTableHeader();
		head.setBackground(backColor);
		head.setForeground(foreColor);
		jsp.setBounds(75, 120 - 10, 1125, 450);
		jsp.getViewport().setBackground(new Color(0,0,0,0.3f));
		jsp.setOpaque(false);
		jsp.setBorder(BorderFactory.createEmptyBorder());
		jsp.setVisible(true);
		this.add(jsp);
		
		//add a button for adding a new client
		button_add = new MyJButton("新增一名客户");
		button_add.setBounds(25 + 420 + 125 + 140, 610 - 26 - 10, 130, 25);
		button_add.setBackground(backColor);
		button_add.setForeground(foreColor);
		button_add.addActionListener(this);
		this.add(button_add);	
		
		//add a button for deleting a selected client
		button_del = new MyJButton("删除所选客户");
		button_del.setBounds(165 + 420 + 125 + 140, 610 - 26 - 10, 130, 25);
		button_del.addActionListener(this);
		button_del.setBackground(backColor);
		button_del.setForeground(foreColor);
		this.add(button_del);	
		
		//add a button for checking and modifying the information of a selected client
		button_cam = new MyJButton("修改或查看所选客户详细信息");
		button_cam.setBounds(305 + 420 + 125 + 140, 610 - 26 - 10, 210, 25);
		button_cam.addActionListener(this);
		button_cam.setBackground(backColor);
		button_cam.setForeground(foreColor);
		this.add(button_cam);	
				
		// the background
		this.setBackground(null);
		
	}
	
	public void actionPerformed(ActionEvent events) {
			
		if(events.getSource() == button_add){
			ClientAddingUI window_add = new ClientAddingUI();
			window_add.setVisible(true);
		}
		
		/////////////////////////////FUNCTION SHOWALL////////////////////////////
		
		if(events.getSource() == button_showAll){
			
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		
			int rowCount = table.getRowCount();
			
			for(int i = 0; i < rowCount; i++)
				tableModel.removeRow(0);
			
			controller = new ClientController();
			
			rowCount = controller.show().size();
			
			for(int i = 0; i < controller.show().size(); i++){
				ClientVO cvo = controller.show().get(i);
				
				String[] str = {cvo.ID, cvo.category.value, cvo.level.value
						, cvo.name,cvo.salesman,String.valueOf(cvo.receivable - cvo.payable)
						,String.valueOf(cvo.receivable), String.valueOf(cvo.payable),String.valueOf(cvo.receivableLimit)};
				tableModel.addRow(str);
			}		
		}
		
		/////////////////////////////FUNCTION DEL////////////////////////////
		
		if(events.getSource() == button_del){
				
			if(table.getSelectedRow() < 0){
				WarningFrame wf = new WarningFrame("请选择要进行删除的客户！");
				wf.setVisible(true);
			}else{
				deleteID = (String) table.getValueAt(table.getSelectedRow(), 0);
				ExitFinishFrame ef = new ExitFinishFrame("Delete a Client");
				ef.setVisible(true);
			}
		}
		
		if(events.getSource() == button_delete){
			
			controller = new ClientController();
			
			ResultMessage rm = controller.deletClient(deleteID);
			
			if(rm.equals(ResultMessage.SUCCESS)){
				WarningFrame wp = new WarningFrame("已成功删除该客户！");
				wp.setVisible(true);
				ClientManagementUI.button_showAll.doClick();
			}else{
				WarningFrame wp = new WarningFrame("无法删除该客户！");
				wp.setVisible(true);
			}
		}
		
		/////////////////////////////FUNCTION CAM////////////////////////////

		if(events.getSource() == button_cam){
			
			controller = new ClientController();
			
			if(table.getSelectedRow() < 0){
				WarningFrame wf = new WarningFrame("请选择要进行查看或修改的客户！");
				wf.setVisible(true);
			}else{
				
				ClientVO cvo = controller.findClient((String) table.getValueAt(table.getSelectedRow(), 0), FindTypeClient.ID).get(0);
				
				ClientDetailUI window_cam = new ClientDetailUI(cvo);
				window_cam.setVisible(true);
			}			
		}
		
		/////////////////////////////SEARCH////////////////////////////
		if(events.getSource() == button_search){
			if(comboBox.getSelectedIndex() == 0){
				
				WarningFrame wf = new WarningFrame("请选择一种搜索方式");
				wf.setVisible(true);
				
			}else{
				
				controller = new ClientController();
				ArrayList<ClientVO> list = controller.findClient(textField.getText(), getType(comboBox.getSelectedIndex()));
				
				if(list.size() == 0){
					WarningFrame wf = new WarningFrame("没有符合条件的客户！");
					wf.setVisible(true);
				}else{
					
					DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
					
					int rowCount = table.getRowCount();
					
					for(int k = 0; k < rowCount; k++)
						tableModel.removeRow(0);
														
					for(int i = 0; i < list.size(); i++){
										
						ClientVO cvo = list.get(i);
						
						String[] str = {cvo.ID, cvo.category.value, cvo.level.value
								, cvo.name,cvo.salesman,String.valueOf(cvo.receivable - cvo.payable)
								,String.valueOf(cvo.receivable), String.valueOf(cvo.payable),String.valueOf(cvo.receivableLimit)};
						tableModel.addRow(str);
						
					}	
								
					WarningFrame wf = new WarningFrame("共有  " + list.size() + "  名客户符合条件！");
					wf.setVisible(true);
				}
				
				
			}
		}
	}
	
	// "模糊查找", "客户编号(ID)", "客户星级", "客户分类", "客户名称", "默认业务员"
	
	private FindTypeClient getType(int i){
		switch(i){
			case 1: return null;
			case 2: return FindTypeClient.ID;
			case 3: return FindTypeClient.LEVEL;
			case 5: return FindTypeClient.NAME;
			case 6: return FindTypeClient.SALESMAN;
			default: return FindTypeClient.KIND;
		}
	}
	
	


}
