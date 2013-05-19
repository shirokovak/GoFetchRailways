package com.test.GoFetchRailways;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CalculateDistancePanel extends JPanel implements ActionListener {
	private JTextField stops;
	private JLabel result;
	private Graph g;
	public CalculateDistancePanel(Graph graph) {
		super();
		g = graph;
		JLabel stopsLabel = new JLabel("Enter the route, names of stops delimetered by '-':");
		add(stopsLabel);
		stops = new JTextField();
		stops.setPreferredSize(new Dimension(150, 20));
		add(stops);
		JButton submit = new JButton("Calculate Distance");
		submit.addActionListener(this);
		add(submit);
		result = new JLabel();
		add(result);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int res = GraphFactory.calcDistance(g, stops.getText());
		if(res<0) 
			result.setText("The route is invalid");
		else
			result.setText("Result: "+res);		
	}

}
