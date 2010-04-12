package RMIClient.gui;

import RMIClient.RehearsalController;


import rehearsalServer.RehearsalRMIDTO;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DebugGraphics;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;


/**
 * CHECK THE GUI SOURCE CODE PROVIDED AS A REFERENCE FEEL FREE TO ADD OR
 * ORGANIZE THE GUI AS YOU PLEASE KEEP IT SIMPLE
 * 
 */

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/

public class RMIClientGUI extends JFrame implements Observer, WindowListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RehearsalController controller;
	
	private JPanel jPanel;
	private JPanel jPanelLogin;
	private JPanel jPanelSeats;
	private JLabel jLabelUser;
	private JTextField jTextFieldUser;
	private JLabel jLabelPass;
	private JTextField jTextFieldPass;
	private JLabel jLabelSN;
	private JTextField jTextFieldSN;
	private JButton jButtonLogin;	
	private JTable jTable;
	private JButton jButtonRehearsals;
	private JButton jButtonRS;
	private JButton jButtonEx;
	private JLabel statusBar;

	public RMIClientGUI(RehearsalController controller)
	{
		//inicializar la ventana---lo podemos hacer en una funcion fuera
		this.controller = controller;
		this.controller.addLocalObserver(this);
		init();
	}
	

	private void init ()
	{
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Rehearsal Reservation Management");
			this.addWindowListener(this);
			{
				jPanel = new JPanel();
				GroupLayout jPanelLayout = new GroupLayout((JComponent)jPanel);
				jPanel.setLayout(jPanelLayout);
				{
					jPanelLogin = new JPanel();
					GroupLayout jPanelUpLayout = new GroupLayout((JComponent)jPanelLogin);
					jPanelLogin.setLayout(jPanelUpLayout);
					jPanelLogin.setBorder(BorderFactory.createTitledBorder("Student Details"));
					jPanelLogin.setEnabled(false);
					{
						jLabelUser = new JLabel();
						BoxLayout jLabel2Layout = new BoxLayout(jLabelUser, javax.swing.BoxLayout.X_AXIS);
						jLabelUser.setText("Username:");
						jLabelUser.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
						jLabelUser.setLayout(jLabel2Layout);
						jLabelUser.setFocusTraversalPolicyProvider(true);
					}
					{
						jTextFieldUser = new JTextField();
						BoxLayout jTextField1Layout = new BoxLayout(jTextFieldUser, javax.swing.BoxLayout.X_AXIS);
						jTextFieldUser.setLayout(jTextField1Layout);
					}
					
					{
						jTextFieldPass = new JTextField();
					}
					{
						jButtonLogin = new JButton();
						jButtonLogin.setText("Login");
						jButtonLogin.addActionListener(this);
					}
					{
						jLabelSN = new JLabel();
						jLabelSN.setText("Student name:");
					}
					{
						jTextFieldSN = new JTextField();
					}
					{
						jLabelPass = new JLabel();
						jLabelPass.setText("Password:");
					}
						jPanelUpLayout.setHorizontalGroup(jPanelUpLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanelUpLayout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, jPanelUpLayout.createSequentialGroup()
						        .addComponent(jLabelUser, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
						    .addGroup(GroupLayout.Alignment.LEADING, jPanelUpLayout.createSequentialGroup()
						        .addPreferredGap(jLabelUser,jLabelPass, LayoutStyle.ComponentPlacement.INDENT)
						        .addComponent(jLabelPass, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
						.addGap(7)
						.addGroup(jPanelUpLayout.createParallelGroup()
						    .addComponent(jTextFieldUser, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jTextFieldPass, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanelUpLayout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, jPanelUpLayout.createSequentialGroup()
						        .addComponent(jLabelSN, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						        .addComponent(jTextFieldSN, 0, 180, Short.MAX_VALUE))
						    .addGroup(GroupLayout.Alignment.LEADING, jPanelUpLayout.createSequentialGroup()
						        .addPreferredGap(jLabelSN, jButtonLogin, LayoutStyle.ComponentPlacement.INDENT)
						        .addComponent(jButtonLogin, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 162, Short.MAX_VALUE)))
						.addContainerGap());
						jPanelUpLayout.setVerticalGroup(jPanelUpLayout.createSequentialGroup()
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanelUpLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(jTextFieldUser, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jButtonLogin, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jLabelUser, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
						.addGap(0, 18, Short.MAX_VALUE)
						.addGroup(jPanelUpLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(jTextFieldPass, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jLabelSN, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jTextFieldSN, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jLabelPass, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(21, 21));
				}
				{
					jPanelSeats = new JPanel();
					GroupLayout jPanelDownLayout = new GroupLayout((JComponent)jPanelSeats);
					jPanelSeats.setLayout(jPanelDownLayout);
					jPanelSeats.setBorder(BorderFactory.createTitledBorder("Final Rehearsal Seat Availabily"));
					jPanelSeats.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
					jPanelSeats.setEnabled(false);
					{
						jButtonRehearsals = new JButton();
						jButtonRehearsals.setText("Get Scheduled Rehearsals");
						jButtonRehearsals.setDefaultCapable(false);
						jButtonRehearsals.addActionListener(this);
					}
					{
						jButtonRS = new JButton();
						jButtonRS.setText("Reserve Seat");
						jButtonRS.addActionListener(this);
					}
					{
						jButtonEx = new JButton();
						jButtonEx.setText("Exit");
						jButtonEx.addActionListener(this);
					}
					{
						TableModel jTableModel = 
							new DefaultTableModel(
									new String[][] { { "Opera House Name", "Opera Name", "Rehearsal Date", "Availability" }, { "5", "6", "7", "8" }, { "9", "10", "11", "12" }, { "13", "14", "15", "16" } },
									new String[] { "Opera House Name", "Opera Name", "Rehearsal Date", "Availability" });
						jTable = new JTable();
						jTable.setModel(jTableModel);
					}

						jPanelDownLayout.setHorizontalGroup(jPanelDownLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanelDownLayout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, jPanelDownLayout.createSequentialGroup()
						        .addComponent(jButtonRehearsals, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
						        .addGap(25)
						        .addComponent(jButtonRS, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						        .addGap(22)
						        .addComponent(jButtonEx, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 34, Short.MAX_VALUE))
						    .addGroup(jPanelDownLayout.createSequentialGroup()
						        .addComponent(jTable, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap(32, 32));
						jPanelDownLayout.setVerticalGroup(jPanelDownLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jTable, 0, 113, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanelDownLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(jButtonRehearsals, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jButtonRS, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						    .addComponent(jButtonEx, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGap(6));
				}
				{
					statusBar = new JLabel();
					statusBar.setText("statusBar");
				}

						jPanelLayout.setHorizontalGroup(jPanelLayout.createParallelGroup()
					.addComponent(statusBar, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 604, GroupLayout.PREFERRED_SIZE)
					.addGroup(jPanelLayout.createSequentialGroup()
					    .addGap(29)
					    .addGroup(jPanelLayout.createParallelGroup()
					        .addGroup(jPanelLayout.createSequentialGroup()
					            .addComponent(jPanelLogin, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE))
					        .addGroup(jPanelLayout.createSequentialGroup()
					            .addComponent(jPanelSeats, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE)))
					    .addContainerGap(19, Short.MAX_VALUE)));
						jPanelLayout.setVerticalGroup(jPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jPanelLogin, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(jPanelSeats, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addComponent(statusBar, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
			}
						thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
							.addComponent(jPanel, 0, 362, Short.MAX_VALUE));
						thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addComponent(jPanel, GroupLayout.PREFERRED_SIZE, 584, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(20, Short.MAX_VALUE));
			pack();
			setSize(620, 400);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void update(java.util.Observable o, Object arg) {

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		if (arg0.getSource().equals(jButtonEx))
		{
			controller.deleteLocalObserver(this);
			try {
				controller.exit();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}