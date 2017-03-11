package UniversalServiceBrowser;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class DiceService implements Service {

	JLabel label;
	JComboBox numOfDice;
	
	@Override
	public JPanel getGuiPanel() {
		JPanel panel = new JPanel();
		
		JButton button = new JButton("Roll 'em!");
		button.addActionListener(new RollEmListener());
		
		String[] choices = {"1","2","3","4","5",};
		this.numOfDice = new JComboBox(choices);
		
		this.label = new JLabel("dice values here");
		
		panel.add(numOfDice);
		panel.add(button);
		panel.add(label);
		
		return panel;
	}

	public class RollEmListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//roll the dice
			
			String diceOutput = "";
			
			String selection = (String) numOfDice.getSelectedItem();
			int numOfDiceToRoll = Integer.parseInt(selection);
			
			for (int i = 0; i < numOfDiceToRoll; i++) {
				int r = (int) ((Math.random() * 6)+1);
				diceOutput += ("" + r);
			}
			label.setText(diceOutput);
		}
	}
	
}
