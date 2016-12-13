package s2.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by russl on 11/23/2016.
 */
public class ControlPanel extends JPanel implements ActionListener {
    JButton editButton;
    JButton deleteButton;
    JButton addButton;

    ControlPanelActionListener controlPanelActionListener;

    public ControlPanel(ControlPanelActionListener controlPanelActionListener) {
        this.controlPanelActionListener = controlPanelActionListener;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


        ImageIcon addIcon = createImageIcon("add.png", 15, 15);
        addButton = new JButton(addIcon);
        addButton.setToolTipText("add");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);

        ImageIcon editIcon = createImageIcon("edit.png", 15, 15);
        editButton = new JButton(editIcon);
        editButton.setToolTipText("edit");
        editButton.setActionCommand("edit");
        editButton.addActionListener(this);


        ImageIcon trashIcon = createImageIcon("trash.png", 15, 15);
        deleteButton = new JButton(trashIcon);
        deleteButton.setToolTipText("deleteProject");
        deleteButton.setActionCommand("deleteProject");
        deleteButton.addActionListener(this);

        add(addButton);
        add(editButton);
        add(deleteButton);

    }

    public void actionPerformed(ActionEvent e) {
//            String actionCommand = e.getActionCommand();
//
//            System.out.println("ControLPanel:  actionCommand:  " + actionCommand);

        controlPanelActionListener.handleAction(e);
    }


    protected static ImageIcon createImageIcon(String path, int width, int height) {
        URL systemResource = ClassLoader.getSystemResource(path);

//        java.net.URL imgURL = PersonNameGridPanel.class.getResource(path);
        if (systemResource != null) {
            ImageIcon imageIcon = new ImageIcon(systemResource);
            Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
