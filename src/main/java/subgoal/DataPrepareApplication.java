/** *************************************************************
 *
 * Subgoal Data Preparation
 * Written by Dr. Andrew Kwok-Fai LUI
 * On 28/2/2020
 *
 * Copyright Andrew Kwok-Fai LUI 2020
 *
 */
package subgoal;

import helper.OSValidator;
import helper.SerializableHelper;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.InputMap;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.text.DefaultEditorKit;

public class DataPrepareApplication extends javax.swing.JFrame {

    private JFileChooser loadfileChooser = null;
    private JFileChooser savefileChooser = null;
    private File currentFile = null;

    private JFileChooser exportfolderChooser = null;
    private File exportFolder = null;

    private String defaultTitle;

    public DataPrepareApplication() {
        initComponents();
        this.defaultTitle = this.getTitle();

        if (OSValidator.isMac()) {
            InputMap im = (InputMap) UIManager.get("TextField.focusInputMap");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.META_DOWN_MASK), DefaultEditorKit.copyAction);
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.META_DOWN_MASK), DefaultEditorKit.pasteAction);
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.META_DOWN_MASK), DefaultEditorKit.cutAction);
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.META_DOWN_MASK), DefaultEditorKit.selectAllAction);
            InputMap im2 = (InputMap) UIManager.get("TextArea.focusInputMap");
            im2.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.META_DOWN_MASK), DefaultEditorKit.copyAction);
            im2.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.META_DOWN_MASK), DefaultEditorKit.pasteAction);
            im2.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.META_DOWN_MASK), DefaultEditorKit.cutAction);
            im2.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.META_DOWN_MASK), DefaultEditorKit.selectAllAction);
        }
    }

    void updateTitle() {
        if (currentFile == null) {
            this.setTitle(defaultTitle);
        } else {
            this.setTitle(defaultTitle + " [" + currentFile.getName() + "]");
        }
    }

    public List<Example> load() {
        File chosenFile = null;
        if (loadfileChooser == null) {
            loadfileChooser = new JFileChooser();
            loadfileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (chosenFile != null) {
                loadfileChooser.setCurrentDirectory(chosenFile.getParentFile());
            } else {
                loadfileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            }
            loadfileChooser.setDialogTitle("Select a file");
        }
        int result = loadfileChooser.showOpenDialog(null);
        if (result == JFileChooser.CANCEL_OPTION) {
            return null;
        }
        chosenFile = loadfileChooser.getSelectedFile();
        List<Example> exampleList = (List) SerializableHelper.load(chosenFile);
        this.currentFile = chosenFile;
        return exampleList;
    }

    public File save(List<Example> exampleList) {
        File chosenFile;
        if (savefileChooser == null) {
            savefileChooser = new JFileChooser();
            savefileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (loadfileChooser != null) {
                savefileChooser.setCurrentDirectory(loadfileChooser.getSelectedFile());
            } else {
                savefileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            }
            savefileChooser.setDialogTitle("Save File");
        }
        int result = savefileChooser.showSaveDialog(null);
        if (result == JFileChooser.CANCEL_OPTION) {
            return null;
        }
        chosenFile = savefileChooser.getSelectedFile();
        saveToFile(exampleList, chosenFile);
        return chosenFile;
    }

    public void saveToFile(List<Example> exampleList, File chosenFile) {
        String name = chosenFile.getName().toLowerCase();
        if (!name.endsWith(".ser")) {
            name += ".ser";
        }
        chosenFile = new File(chosenFile.getParent(), name);
        SerializableHelper.save(chosenFile, exampleList);
    }

    public void exportToXMLFolder(List<Example> exampleList) {
        File chosenFolder;
        if (exportfolderChooser == null) {
            exportfolderChooser = new JFileChooser();
            exportfolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (exportFolder != null) {
                exportfolderChooser.setCurrentDirectory(exportFolder);
            } else {
                exportfolderChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            }
            exportfolderChooser.setDialogTitle("Export to XML Files");
        }
        int result = exportfolderChooser.showSaveDialog(null);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
        chosenFolder = exportfolderChooser.getSelectedFile();
        this.exportFolder = chosenFolder;
        // start export
        for (Example m : exampleList) {
            String id = m.getID();
            String filename = id + ".xml";
            File xmlFile = new File(chosenFolder, filename);
            try {
                exportToXMLFile(m, xmlFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void exportToXMLFile(Example example, File xmlFile) throws IOException {
        PrintWriter writer = new PrintWriter(new FileOutputStream(xmlFile));
        writer.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        writer.println(example.getXmlExample().toString());
        writer.close();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exampleManager1 = new subgoal.ExampleManager();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Subgoal Data Preparation");
        setLocation(new java.awt.Point(600, 200));
        setMinimumSize(new java.awt.Dimension(800, 480));
        setPreferredSize(new java.awt.Dimension(1200, 640));
        setSize(new java.awt.Dimension(800, 800));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        exampleManager1.setPreferredSize(new java.awt.Dimension(1200, 600));
        getContentPane().add(exampleManager1);

        jMenu1.setText("File");

        jMenuItem1.setText("Load");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Load and Merge");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem6.setText("Load and Append");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem3.setText("Save");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("Save As");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);
        jMenu1.add(jSeparator2);

        jMenuItem7.setText("Export to XML");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);
        jMenu1.add(jSeparator1);

        jMenuItem4.setText("Quit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JMenuItem item = (JMenuItem) evt.getSource();
        String label = (String) item.getText();
        List<Example> exampleList = null;
        switch (label) {
            case "Load":
                exampleList = load();
                if (exampleList == null) {
                    return;
                }
                exampleManager1.setExampleList(exampleList);
                break;
            case "Load and Merge":
                exampleList = load();
                if (exampleList == null) {
                    return;
                }
                exampleManager1.mergeExampleList(exampleList);
                JOptionPane.showMessageDialog(this, "Merge examples completed");
                break;
            case "Load and Append":
                exampleList = load();
                if (exampleList == null) {
                    return;
                }
                exampleManager1.appendExampleList(exampleList);
                JOptionPane.showMessageDialog(this, "Append examples completed");
                break;
            case "Save":
                exampleList = exampleManager1.getExampleList();
                if (currentFile != null) {
                    saveToFile(exampleList, currentFile);
                    JOptionPane.showMessageDialog(this, "File saved successfully to " + currentFile.getName());
                } else {
                    File savedFile = save(exampleList);
                    if (savedFile == null) {
                        return;
                    }
                    currentFile = savedFile;
                    JOptionPane.showMessageDialog(this, "File saved successfully to " + currentFile.getName());
                }
                break;
            case "Save As":
                exampleList = exampleManager1.getExampleList();
                File savedFile = save(exampleList);
                if (savedFile == null) {
                    return;
                }
                currentFile = savedFile;
                JOptionPane.showMessageDialog(this, "File saved successfully to " + currentFile.getName());
                break;
            case "Export to XML":
                exampleList = exampleManager1.getExampleList();
                exportToXMLFolder(exampleList);
                JOptionPane.showMessageDialog(this, "File saved successful");
                break;
            case "Quit":
                System.exit(0);
                break;
        }
        // update the Frame title
        updateTitle();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(
                            UIManager.getCrossPlatformLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataPrepareApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataPrepareApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataPrepareApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataPrepareApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataPrepareApplication().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private subgoal.ExampleManager exampleManager1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
