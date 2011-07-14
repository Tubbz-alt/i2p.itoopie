package net.i2p.itoopie.gui;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.views.ChartPanel;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import net.i2p.itoopie.gui.component.BandwidthChart;
import net.i2p.itoopie.gui.component.LogoPanel;
import net.i2p.itoopie.gui.component.ParticipatingTunnelsChart;
import net.i2p.itoopie.util.IconLoader;
import javax.swing.UIManager;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class Main {

	private JFrame frame;
	private final static Color VERY_LIGHT = new Color(230,230,230);
	private final static Color LIGHT = new Color(215,215,215);
	private final static Color MEDIUM = new Color (175,175,175);
	private final static Color DARK = new Color(145,145,145);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		GUIHelper.setDefaultStyle();


		frame = new RegisteredFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setResizable(false);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel overviewPanel = new LogoPanel("itoopie-opaque12");
		tabbedPane.addTab("Overview", null, overviewPanel, null);
		overviewPanel.setLayout(null);
				
		Chart2D bwChart = BandwidthChart.getChart();
		Chart2D partTunnelChart = ParticipatingTunnelsChart.getChart();
		//bwChart.setPreferredSize(new Dimension(30,100));
		ChartPanel pt = new ChartPanel(partTunnelChart);
		pt.setSize(300, 135);
		pt.setLocation(15, 15);;
		pt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		ChartPanel cp = new ChartPanel(bwChart);
		cp.setSize(300,135);
		cp.setLocation(15, 165);
		cp.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		overviewPanel.add(pt);
		overviewPanel.add(cp);
		
		
		JPanel configPanel = new ConfigurationPanel("itoopie-opaque12");
		tabbedPane.addTab("Configuration", null, configPanel, null);
		configPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel configArea = new JPanel();
		configArea.setOpaque(false);
		//configArea.setLayout(new Grid());
		configPanel.add(configArea, BorderLayout.CENTER);

		
		
		JPanel buttonArea = new JPanel();
		buttonArea.setOpaque(false);
		buttonArea.setLayout(new BorderLayout(0, 0));
		configPanel.add(buttonArea, BorderLayout.SOUTH);
		
		JPanel buttonAreaEast = new JPanel();
		buttonAreaEast.setLayout(new BorderLayout(0, 0));
		buttonAreaEast.setOpaque(false);
		buttonArea.add(buttonAreaEast, BorderLayout.EAST);
		
		JPanel applyBtnWrapper = new JPanel();
		applyBtnWrapper.setOpaque(false);
		FlowLayout flowApplyBtnWrapper = new FlowLayout();
		flowApplyBtnWrapper.setVgap(0);
		flowApplyBtnWrapper.setHgap(3);
		applyBtnWrapper.setLayout(flowApplyBtnWrapper);
		buttonAreaEast.add(applyBtnWrapper);
		
		
		FlowLayout flowLayout = (FlowLayout) applyBtnWrapper.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(3);
		
		JButton btnApply = new JButton("Apply");
		btnApply.setOpaque(true);
		applyBtnWrapper.add(btnApply, BorderLayout.EAST);
		
		JPanel logPanel = new LogoPanel("itoopie-opaque12");
		tabbedPane.addTab("Logs", null, logPanel, null);
		logPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel statusPanel = new JPanel();
		frame.getContentPane().add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setBounds(100, 15, 100, 100);
		statusPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel statusLbl = StatusHandler.getStatusLbl();
		statusLbl.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLbl, BorderLayout.CENTER);
		
		JPanel buttonWrapper = new JPanel();
		flowLayout = (FlowLayout) buttonWrapper.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(3);
		statusPanel.add(buttonWrapper, BorderLayout.EAST);
		
		JButton settingsBtn = new JButton("Settings");
		buttonWrapper.add(settingsBtn);
		settingsBtn.setIcon(new ImageIcon(IconLoader.getIcon("cogwheel", 16)));
		settingsBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.start();
			}
		});
		
		frame.setVisible(true);
		frame.repaint(); // Force repaint to make sure that Logo is loaded.
	}
}