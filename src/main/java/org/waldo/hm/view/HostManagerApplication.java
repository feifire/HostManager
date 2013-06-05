package org.waldo.hm.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * User: wangyin
 * Date: 13-6-4
 * Time: 上午12:06
 * TODO 怎么设置初始显示在屏幕中央
 */
public class HostManagerApplication {

    private JFrame mainFrame;
    private JMenuBar menuBar;
    private JToolBar toolBar;

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        JMenuItem exitItem = fileMenu.add(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exitItem.setText("Exit");
        menuBar.add(fileMenu);

        JMenu actionsMenu = new JMenu("Action");
        actionsMenu.setName("actionsMenu");
        actionsMenu.setMnemonic('A');
        menuBar.add(actionsMenu);

        // Menu for the look and feels (lnfs).
        UIManager.LookAndFeelInfo[] lnfs = UIManager.getInstalledLookAndFeels();

        ButtonGroup lnfGroup = new ButtonGroup();
        JMenu lnfMenu = new JMenu("Look&Feel");
        lnfMenu.setMnemonic('L');

        menuBar.add(lnfMenu);

        for (UIManager.LookAndFeelInfo lnf : lnfs) {
            if (!lnf.getName().equals("CDE/Motif")) {
                JRadioButtonMenuItem rbmi = new JRadioButtonMenuItem(lnf.getName());
                lnfMenu.add(rbmi);

                // preselect the current Look & feel
                rbmi.setSelected(UIManager.getLookAndFeel().getName().equals(lnf.getName()));

                // store lool & feel info as client property
                rbmi.putClientProperty("lnf name", lnf);

                // create and add the item listener
                rbmi.addItemListener(
                        // inlining
                        new ItemListener() {
                            public void itemStateChanged(ItemEvent ie) {
                                JRadioButtonMenuItem rbmi2 = (JRadioButtonMenuItem) ie.getSource();

                                if (rbmi2.isSelected()) {
                                    // get the stored look & feel info
                                    UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo) rbmi2.getClientProperty("lnf name");

                                    try {
                                        UIManager.setLookAndFeel(info.getClassName());

                                        // update the complete application's
                                        // look & feel
                                        SwingUtilities.updateComponentTreeUI(mainFrame);
                                    } catch (Exception e) {
                                        e.printStackTrace();

                                        System.err.println("Unable to set UI " + e.getMessage());
                                    }
                                }
                            }
                        });
                lnfGroup.add(rbmi);
            }
        }

        // the help menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');

        JMenuItem aboutItem = helpMenu.add(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                showAboutDialog();
            }
        });
        aboutItem.setText("About");
        aboutItem.setMnemonic('A');
        aboutItem.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_MASK));

        menuBar.add(helpMenu);

        return menuBar;
    }

    public void launch() {
        Runnable appStarter = new Runnable() {
            @Override
            public void run() {
                mainFrame = new JFrame("HostManager");
                mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                mainFrame.setSize(800, 600);

                menuBar = createMenuBar();
                mainFrame.setJMenuBar(menuBar);

                mainFrame.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(appStarter);
    }
}
