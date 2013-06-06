package com.test.GoFetchRailways.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.test.GoFetchRailways.core.Graph;
import com.test.GoFetchRailways.core.GraphFactory;
import com.test.GoFetchRailways.core.RouteDetails;

/**
 * Create form for Shortest Route module.
 * 
 * @author	Alexandr Shirokov
 */
public class ShortestRoutePanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7284281139326629780L;
	private JTextField from,to;
	private JLabel result;
	private Graph g;
	public ShortestRoutePanel(Graph graph) {
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
		JButton submit = new JButton("Find Shortest Route");
		submit.addActionListener(this);
		add(submit);
		result = new JLabel();
		add(result);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RouteDetails res = GraphFactory.findShortestRoute(g, from.getText(), to.getText());
		result.setText("Result: Distance="+res.getDistance()+" Path="+res.getPath());		
	}

}
