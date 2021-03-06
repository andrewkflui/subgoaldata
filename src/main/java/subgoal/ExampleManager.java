package subgoal;

/**
 * *************************************************************
 *
 * Subgoal Data Preparation Written by Dr. Andrew Kwok-Fai LUI On 28/2/2020
 *
 * Copyright Andrew Kwok-Fai LUI 2020
 *
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ExampleManager extends javax.swing.JPanel implements ExampleEditorListener {

    private List<Example> exampleList = new ArrayList();
    private Example selectedExample = null;

    public ExampleManager() {
        initComponents();
        DefaultListModel model = new DefaultListModel();
        jList1.setModel(model);
        programEditor1.setListener(this);
    }

    public List<Example> getExampleList() {
        return exampleList;
    }

    public void setExampleList(List exampleList) {
        if (exampleList == null) {
            return;
        }
        this.exampleList = exampleList;
        refreshLabelJList();
        selectedExample = null;
        programEditor1.refreshExampleInfoJPanel(selectedExample);
    }

    public boolean existExampleWithID(String id) {
        if (exampleList == null) {
            return false;
        }
        for (Example m : exampleList) {
            if (m.getID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private String getNextExampleID() {
        int count = exampleList.size();
        String id = "example" + count;
        while (true) {
            if (!existExampleWithID(id)) {
                break;
            }
            count++;
            id = "example" + String.format("%04d", count);
        }
        return id;
    }

    public void mergeExampleList(List<Example> exampleList) {
        if (exampleList == null) {
            return;
        }
        if (this.exampleList.size() == 0) {
            this.exampleList.addAll(exampleList);
        } else {
            for (Example m : exampleList) {
                if (!existExampleWithID(m.getID())) {
                    this.exampleList.add(m);
                }
            }
        }
        refreshLabelJList();
        selectedExample = null;
        programEditor1.refreshExampleInfoJPanel(selectedExample);
    }

    public void appendExampleList(List<Example> exampleList) {
        if (exampleList == null) {
            return;
        }
        this.exampleList.addAll(exampleList);
        refreshLabelJList();
        selectedExample = null;
        programEditor1.refreshExampleInfoJPanel(selectedExample);
    }

    public void sortExampleList() {
        Collections.sort(exampleList, Example.idComparator);
    }

    void refreshLabelJList() {
        DefaultListModel model = (DefaultListModel) jList1.getModel();
        model.removeAllElements();
        for (Example m : exampleList) {
            model.addElement(m);
        }
    }

    public void valueChanged(Example theExample, String which) {
        if (which.equals("ID")) {
            refreshLabelJList();
        }
    }

    private void fixExampleID() {
        String prefix = "example";
        int prefixLen = prefix.length();
        for (Example m : exampleList) {
            String id = m.getID();
            String numstr = id.substring(prefixLen);
            try {
                int num = Integer.parseInt(numstr);
                id = "example" + String.format("%04d", num);
                m.setID(id);
                //System.out.println(id);
            } catch (Exception ex) {
            }
        }
        refreshLabelJList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        programEditor1 = new subgoal.ExampleEditor();

        setPreferredSize(new java.awt.Dimension(1000, 300));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(180, 80));
        jPanel1.setPreferredSize(new java.awt.Dimension(180, 300));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButton1.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jButton1.setText("New");
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setMaximumSize(new java.awt.Dimension(80, 28));
        jButton1.setMinimumSize(new java.awt.Dimension(80, 28));
        jButton1.setPreferredSize(new java.awt.Dimension(80, 28));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButton1, gridBagConstraints);

        jButton3.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jButton3.setText("Clone");
        jButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton3.setMaximumSize(new java.awt.Dimension(80, 28));
        jButton3.setMinimumSize(new java.awt.Dimension(80, 28));
        jButton3.setPreferredSize(new java.awt.Dimension(80, 28));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButton3, gridBagConstraints);

        jButton2.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jButton2.setText("Delete");
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.setMaximumSize(new java.awt.Dimension(80, 28));
        jButton2.setMinimumSize(new java.awt.Dimension(80, 28));
        jButton2.setPreferredSize(new java.awt.Dimension(80, 28));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButton2, gridBagConstraints);

        jButton4.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jButton4.setText("Sort");
        jButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton4.setMaximumSize(new java.awt.Dimension(80, 28));
        jButton4.setMinimumSize(new java.awt.Dimension(80, 28));
        jButton4.setPreferredSize(new java.awt.Dimension(80, 28));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButton4, gridBagConstraints);

        jButton5.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jButton5.setText("Custom");
        jButton5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton5.setMaximumSize(new java.awt.Dimension(80, 28));
        jButton5.setMinimumSize(new java.awt.Dimension(80, 28));
        jButton5.setPreferredSize(new java.awt.Dimension(80, 28));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButton5, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(120, 132));

        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(programEditor1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        boolean isAdjusting = evt.getValueIsAdjusting();
        if (isAdjusting) {
            return;
        }
        JList obj = (JList) evt.getSource();
        int selected = obj.getSelectedIndex();
        if (selected < 0 || selected >= exampleList.size()) {
            return;
        }
        selectedExample = exampleList.get(selected);
        programEditor1.setExample(selectedExample);
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JButton btn = (JButton) evt.getSource();
        if (btn.getText().equals("New")) {
            Example newExample = programEditor1.newExample(getNextExampleID());
            exampleList.add(newExample);
            refreshLabelJList();
            jList1.setSelectedValue(newExample, true);
        } else if (btn.getText().equals("Clone")) {
            int indices[] = jList1.getSelectedIndices();
            if (indices.length != 1) {
                JOptionPane.showMessageDialog(this, "Select only one example to copy");
                return;
            }
            Example selected = exampleList.get(indices[0]);
            Example newExample = selected.copy();
            newExample.setID(getNextExampleID());
            exampleList.add(newExample);
            refreshLabelJList();
            jList1.setSelectedValue(newExample, true);
        } else if (btn.getText().equals("Delete")) {
            int indices[] = jList1.getSelectedIndices();
            for (int i = indices.length - 1; i >= 0; i--) {
                exampleList.remove(indices[i]);
            }
            refreshLabelJList();
            selectedExample = null;
            programEditor1.refreshExampleInfoJPanel(selectedExample);
        } else if (btn.getText().equals("Sort")) {
            sortExampleList();
            refreshLabelJList();
            selectedExample = null;
            programEditor1.refreshExampleInfoJPanel(selectedExample);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Custom Button
        
        //fixExampleID();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private subgoal.ExampleEditor programEditor1;
    // End of variables declaration//GEN-END:variables
}
