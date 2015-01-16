/*
 * @author: Ionica Bizau
 * @project: Text to speech
 * @year: 2013-15
 * @license: MIT
 */


import com.gtranslate.Audio;
import com.gtranslate.Language;
import com.gtranslate.Translator;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ionică Bizău
 */
public class MainJFrame extends javax.swing.JFrame {

    String currentLanguage = "ENGLISH";
    
    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        getLanguages(currentLanguage);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setText("Read Now");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton2.setText("About");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * The button is pressed
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            ReadMessage(jTextArea1.getText());
        } catch (IOException ex) {
            showError(ex.toString());
        } catch (JavaLayerException ex) {
            showError(ex.toString());
        } catch (NoSuchFieldException ex) {
            showError(ex.toString());
        } catch (IllegalArgumentException ex) {
            showError(ex.toString());
        } catch (IllegalAccessException ex) {
            showError(ex.toString());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JOptionPane.showMessageDialog(
                null,  
                "Author: Ionică Bizău\n" + 
                "Github: http://github.com/IonicaBizau\n" + 
                "Contact: bizauionica@gmail.com\n", 
                "About",
                JOptionPane.DEFAULT_OPTION);
    }//GEN-LAST:event_jButton2ActionPerformed

    Boolean initialized = false;
    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
        if (!initialized) {
            initialized = true;
            return;
        }
        
        String oldLanguage = currentLanguage;
        currentLanguage = jComboBox1.getSelectedItem().toString();
        
        Translator translate = Translator.getInstance();
        String translatedText = null;
        
        // TODO Solving the problem with comma replacing it with a blank space.
        //      https://github.com/IonicaBizau/text-to-speech/issues/1
        //      http://code.google.com/p/java-google-translate-text-to-speech/issues/detail?id=1
        
        String textInserted = jTextArea1.getText().replace(",", " ");
        
        try {
            try {
                translatedText = translate.translate(
                    textInserted,
                    (String)Language.class.getField(oldLanguage).get(null),
                    (String)Language.class.getField(currentLanguage).get(null));
                
            } catch (IllegalArgumentException ex) {
                showError(ex.toString());
            } catch (IllegalAccessException ex) {
                showError(ex.toString());
            }
        } catch (NoSuchFieldException ex) {
            showError(ex.toString());
        } catch (SecurityException ex) {
            showError(ex.toString());
        }
        
        jTextArea1.setText(translatedText);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * Show error
     * @param err 
     */
    private void showError(String err) {
        JOptionPane.showMessageDialog(null, "Unfortunately there is an error: \n" 
                + err, "Error reading the text", JOptionPane.WARNING_MESSAGE);
    }
    
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
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainJFrame mjf = new MainJFrame();
                mjf.setVisible(true);
                mjf.setTitle("Text to speech Java APP");
            }
        });
    }
    
    /**
     * Get languages and insert them into combobox
     * @param defaultLanguage 
     */
    public void getLanguages(String defaultLanguage) {
        Field[] fields = Language.class.getDeclaredFields();
        jComboBox1.removeAllItems();
        for(Field field: fields) {
            if (field.getType() == String.class){
                jComboBox1.addItem(field.getName());
            }
        }
        
        jComboBox1.setSelectedItem(defaultLanguage);
    }
    
    /**
     * Read the message that is a String
     * @param message
     * @throws IOException
     * @throws JavaLayerException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public void ReadMessage(String message) throws IOException, JavaLayerException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        Audio audio = Audio.getInstance();
        
        String language = jComboBox1.getSelectedItem().toString();    
        InputStream sound = audio.getAudio(message, (String)Language.class.getField(language).get(null));
        audio.play(sound);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}