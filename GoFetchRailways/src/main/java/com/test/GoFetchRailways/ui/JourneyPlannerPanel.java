package com.test.GoFetchRailways.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.test.GoFetchRailways.core.Graph;
import com.test.GoFetchRailways.core.GraphFactory;

/**
 * Create form for Journal Planner module.
 * 
 * @author	Alexandr Shirokov
 */
public class JourneyPlannerPanel extends JPanel implements ActionListener {
	private JTextField from,to,stops;
	private JCheckBox exact;
	private JTextArea result;
	private Graph g;
	public JourneyPlannerPanel(Graph graph) {
		super();
		g = graph;
		JLabel fromLabel = new JLabel("Enter the start station:");
		add(fromLabel);
		from = new JTextField();
		from.setPreferredSize(new Dimension(150, 20));
		add(from);
		JLabel toLabel = new JLabel("Enter the end station:");
		add(toLabel);
		to = new JTextField();
		to.setPreferredSize(new Dimension(150, 20));
		add(to);
		JLabel stopsLabel = new JLabel("Enter the maximum number of stops:");
		add(stopsLabel);
		stops = new JTextField();
		stops.setPreferredSize(new Dimension(150, 20));
		add(stops);
		exact = new JCheckBox("Exact");
		add(exact);
		JButton submit = new JButton("Find");
		submit.addActionListener(this);
		add(submit);
		result = new JTextArea();
		add(result);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int max = Integer.parseInt(stops.getText());
		List res = GraphFactory.journeyPlanner(g, from.getText(), to.getText(), max, exact.isSelected());
		if(res.size() > 0) {
			int rnum = 0;
			StringBuilder sb = new StringBuilder();
			for(Object r:res) {
				sb.append("route"+(++rnum)+"="+r+"\n");
			}
			result.setText("Founded routes:\n"+sb.toString());
		} else {
			result.setText("No route founded");
		}
	}

}
