package com.test.GoFetchRailways;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;


public class GoFetchRailways  extends JFrame implements ActionListener {
    private JMenuItem calcDistMI, openJourneyPlannerMI, calcShortestRouteMI;
	private JButton calcDistBtn, openJourneyPlannerBtn, calcShortestRouteBtn;
	private JPanel topPanel, calcDistPanel, journeyPlannerPanel, shortestRoutePanel;
	private Graph graph;
	public static void start() {
        //Create and set up the window.
		GoFetchRailways frame = new GoFetchRailways();
		frame.load("routes.properties");
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
	public GoFetchRailways() {
		super("GoFetch Railways");
		graph = new Graph();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400,300));
        setLocation(20, 20);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
      //Where the GUI is created:
        JMenuBar menuBar;
        JMenu menu;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Operations");
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        calcDistMI = new JMenuItem("Calculate Distance");
        calcDistMI.addActionListener(this);
        calcDistMI.getAccessibleContext().setAccessibleDescription(
                "Allow the user to enter a number of stops and then print the distance involved in traversing the route.");
        menu.add(calcDistMI);

        openJourneyPlannerMI = new JMenuItem("Journey Planner");
        openJourneyPlannerMI.addActionListener(this);
        openJourneyPlannerMI.getAccessibleContext().setAccessibleDescription(
                "Allow the user to enter a start and end station and optionally a maximum or exact number of “stops” along the way.");
        menu.add(openJourneyPlannerMI);

        calcShortestRouteMI = new JMenuItem("Shortest route");
        calcShortestRouteMI.addActionListener(this);
        calcShortestRouteMI.getAccessibleContext().setAccessibleDescription(
                "Allow the user to enter a start and end station and then print out the length and details of the shortest route.");
        menu.add(calcShortestRouteMI);

        //Build the second menu.
        menu = new JMenu("Settings");
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        menuBar.add(menu);

        setJMenuBar(menuBar);
        
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        
        calcDistBtn = new JButton("Calculate Distance");
        calcDistBtn.addActionListener(this);
        topPanel.add(calcDistBtn);
 
        openJourneyPlannerBtn = new JButton("Journey Planner");
        openJourneyPlannerBtn.addActionListener(this);
        topPanel.add(openJourneyPlannerBtn);
 
        calcShortestRouteBtn = new JButton("Shortest route");
        calcShortestRouteBtn.addActionListener(this);
        topPanel.add(calcShortestRouteBtn);
        
        getContentPane().add(topPanel);
        
        calcDistPanel = new CalculateDistancePanel(graph);
        journeyPlannerPanel = new JourneyPlannerPanel(graph);
        shortestRoutePanel = new ShortestRoutePanel(graph);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == calcDistBtn || e.getSource() == calcDistMI) {
			getContentPane().removeAll();
			getContentPane().add(calcDistPanel);
		} else if(e.getSource() == openJourneyPlannerBtn || e.getSource() == openJourneyPlannerMI) {
			getContentPane().removeAll();
			getContentPane().add(journeyPlannerPanel);
		} else if(e.getSource() == calcShortestRouteBtn || e.getSource() == calcShortestRouteMI) {
			getContentPane().removeAll();
			getContentPane().add(shortestRoutePanel);
		}
		validate();
		repaint();
	}
	public void load(String file) {
		try {
			graph.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
