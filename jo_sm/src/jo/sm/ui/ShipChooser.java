/**
 * Copyright 2014 
 * SMEdit https://github.com/StarMade/SMEdit
 * SMTools https://github.com/StarMade/SMTools
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 **/
package jo.sm.ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import jo.sm.ui.logic.ShipSpec;
import jo.sm.ui.logic.ShipTreeLogic;

@SuppressWarnings("serial")
public class ShipChooser extends JDialog {

    private ShipSpec mSelected;

    private final JTree mTree;

    public ShipChooser(JFrame base) {
        super(base, "Choose Ship", Dialog.ModalityType.DOCUMENT_MODAL);
        // instantiate
        mTree = new JTree(ShipTreeLogic.getShipTree());
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        // layout
        JPanel client = new JPanel();
        getContentPane().add(client);
        client.setLayout(new BorderLayout());
        client.add(BorderLayout.NORTH, new JLabel("Select a ship to view:"));
        client.add(BorderLayout.CENTER, new JScrollPane(mTree));
        JPanel buttonBar = new JPanel();
        client.add(BorderLayout.SOUTH, buttonBar);
        buttonBar.setLayout(new FlowLayout());
        buttonBar.add(ok);
        buttonBar.add(cancel);
        // link
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                doOK();
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                doCancel();
            }
        });
        setSize(640, 480);
        setLocationRelativeTo(base);
    }

    private void doOK() {
        TreePath selectedPath = mTree.getSelectionPath();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();
        if (selectedNode.getUserObject() instanceof ShipSpec) {
            mSelected = (ShipSpec) selectedNode.getUserObject();
        } else {
            mSelected = null;
        }
        setVisible(false);
        dispose();
    }

    private void doCancel() {
        mSelected = null;
        setVisible(false);
        dispose();
    }

    public ShipSpec getSelected() {
        return mSelected;
    }

    public void setSelected(ShipSpec selected) {
        mSelected = selected;
    }
}
