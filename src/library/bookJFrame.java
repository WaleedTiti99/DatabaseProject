/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import com.sun.glass.events.KeyEvent;
import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author LENOVO
 */
public class bookJFrame extends javax.swing.JFrame {

    /**
     * Creates new form bookJFrame
     * 
     */
    String path1="";
    public bookJFrame() {
        
        initComponents();
        editBtn.setVisible(true);
        browseBtn.setVisible(false);
        saveBtn.setVisible(false);
        
        authorField.setEditable(false);
        isbnField.setEditable(false);
        sectionField.setEditable(false);
        rowField.setEditable(false);
         descriptionField.setEditable(false);
         sectionField2.setEditable(false);
         sectionField1.setEditable(false);
    }
    public bookJFrame(BigDecimal ISBN) {
        
        initComponents();
        
         try{
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
        String sql="select * from BOOKS WHERE ISBN= "+ISBN+"";
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
         jLabel1.setText(rs.getString("TITLE"));
        }
         }
         catch (Exception e)
         {
             
         }
         try{
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
        String sql="select * from BOOKS,AUTHOR WHERE ISBN="+ISBN+"and BOOK_ISBN="+ISBN;
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            authorField.setText(rs.getString("F_NAME")+" "+rs.getString("SUR_NAME"));
            isbnField.setText(ISBN.toString());
            sectionField.setText(rs.getString("SECTION"));
            rowField.setText(rs.getString("POSITION"));
            descriptionField.setText(rs.getString("DESCREPTION"));
            sectionField2.setText(rs.getString("GENRE"));
            sectionField1.setText(rs.getString("NUMCOPIES"));
            String path = rs.getString("BOOK_PIC");
                     ImageIcon myImage  = new ImageIcon(path);
                    Image img = myImage.getImage();
                    Image newImg = img.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(newImg);
                    jLabel2.setIcon(image);
        }
        String sql2="select AVG(rating)from FEEDBACK where F_ISBN="+ISBN;
        PreparedStatement ps2=con.prepareStatement(sql2);
        ResultSet rs2=ps2.executeQuery();
        if(rs2.next())
        {
            jLabel11.setText(rs2.getString("AVG(RATING)"));
        }
        }
        catch(Exception e)
        {
            
        }
        
        
        
        editBtn.setVisible(true);
        browseBtn.setVisible(false);
        saveBtn.setVisible(false);
        
        authorField.setEditable(false);
        isbnField.setEditable(false);
        sectionField.setEditable(false);
        rowField.setEditable(false);
         descriptionField.setEditable(false);
         sectionField2.setEditable(false);
         sectionField1.setEditable(false);
       
    }
    public bookJFrame(String ISBN)
    {
        initComponents();
        
        try{
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
         String sql="select * from BOOKS WHERE ISBN= "+BigDecimal.valueOf(Long.parseLong(ISBN))+"";
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
         jLabel1.setText(rs.getString("TITLE"));
        }
         }
         catch (Exception e)
         {
             
         }
        
        try{
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
        String sql="select * from BOOKS,AUTHOR WHERE ISBN="+BigDecimal.valueOf(Long.parseLong(ISBN))+"and BOOK_ISBN="+BigDecimal.valueOf(Long.parseLong(ISBN));
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            authorField.setText(rs.getString("F_NAME")+" "+rs.getString("SUR_NAME"));
            isbnField.setText(ISBN);
            sectionField.setText(rs.getString("SECTION"));
            rowField.setText(rs.getString("POSITION"));
            descriptionField.setText(rs.getString("DESCREPTION"));
            sectionField2.setText(rs.getString("GENRE"));
            sectionField1.setText(rs.getString("NUMCOPIES"));
            String path = rs.getString("BOOK_PIC");
                     ImageIcon myImage  = new ImageIcon(path);
                    Image img = myImage.getImage();
                    Image newImg = img.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(newImg);
                    jLabel2.setIcon(image);
        }
        String sql2="select AVG(rating)from FEEDBACK where F_ISBN="+BigDecimal.valueOf(Long.parseLong(ISBN));
        PreparedStatement ps2=con.prepareStatement(sql2);
        ResultSet rs2=ps2.executeQuery();
        if(rs2.next())
        {
            jLabel11.setText(rs2.getString("AVG(RATING)"));
        }
        }
        catch(Exception e)
        {
            
        }
        editBtn.setVisible(false);
        browseBtn.setVisible(false);
        saveBtn.setVisible(false);
        
        authorField.setEditable(false);
        isbnField.setEditable(false);
        sectionField.setEditable(false);
        rowField.setEditable(false);
         descriptionField.setEditable(false);
            sectionField2.setEditable(false);
         sectionField1.setEditable(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionField = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        browseBtn = new javax.swing.JButton();
        authorField = new javax.swing.JTextField();
        isbnField = new javax.swing.JTextField();
        sectionField = new javax.swing.JTextField();
        rowField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        sectionField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        sectionField2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        editBtn = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        descriptionField.setColumns(20);
        descriptionField.setLineWrap(true);
        descriptionField.setRows(5);
        jScrollPane1.setViewportView(descriptionField);

        jLabel3.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel3.setText("Book Description");

        jLabel4.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel4.setText("Author:");

        jLabel5.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel5.setText("ISBN:");

        jLabel6.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel6.setText("Section:");

        jLabel7.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel7.setText("Row:");

        saveBtn.setBackground(new java.awt.Color(230, 57, 70));
        saveBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveBtn.setText("Save");
        saveBtn.setBorder(null);
        saveBtn.setBorderPainted(false);
        saveBtn.setFocusPainted(false);
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        browseBtn.setBackground(new java.awt.Color(230, 57, 70));
        browseBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        browseBtn.setForeground(new java.awt.Color(255, 255, 255));
        browseBtn.setText("Browse");
        browseBtn.setBorder(null);
        browseBtn.setBorderPainted(false);
        browseBtn.setFocusPainted(false);
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });

        authorField.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        authorField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        isbnField.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        isbnField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        sectionField.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        sectionField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        rowField.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        rowField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        rowField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rowFieldKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel8.setText("Quantity:");

        sectionField1.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        sectionField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        sectionField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sectionField1KeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel9.setText("Genre:");

        sectionField2.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        sectionField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel10.setText("Rating:");

        jLabel11.setText("jLabel11");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(browseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(authorField)
                            .addComponent(isbnField))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(rowField, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                                .addComponent(sectionField))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(sectionField1))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(sectionField2)))
                            .addGap(268, 268, 268)
                            .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(authorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(isbnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(saveBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(sectionField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(sectionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(rowField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(sectionField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 94, -1, 390));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setBackground(new java.awt.Color(51, 153, 255));
        jLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Book Title");

        editBtn.setBackground(new java.awt.Color(230, 57, 70));
        editBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("Edit");
        editBtn.setBorder(null);
        editBtn.setBorderPainted(false);
        editBtn.setFocusPainted(false);
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 555, Short.MAX_VALUE)
                .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 791, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
        editBtn.setVisible(false);
        browseBtn.setVisible(true);
        saveBtn.setVisible(true);
        
        authorField.setEditable(false);
        isbnField.setEditable(false);
        sectionField.setEditable(true);
        rowField.setEditable(true);
        descriptionField.setEditable(true);
        sectionField1.setEditable(true);
        sectionField2.setEditable(true);
        
        
    }//GEN-LAST:event_editBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        editBtn.setVisible(true);
        browseBtn.setVisible(false);
        saveBtn.setVisible(false);
        
        authorField.setEditable(false);
        isbnField.setEditable(false);
        sectionField.setEditable(false);
        rowField.setEditable(false);
        descriptionField.setEditable(false);
        sectionField1.setEditable(false);
        sectionField2.setEditable(false);
        
        BigDecimal ISBN1=BigDecimal.valueOf(Long.parseLong(isbnField.getText()));
        int section=Integer.parseInt(sectionField.getText());
        int row=Integer.parseInt(rowField.getText());
        String Genre=sectionField2.getText();
        int Quantity=Integer.parseInt(sectionField1.getText());
        String desc=descriptionField.getText();
        
        try{
            
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            String sql="";
            if(!path1.isEmpty())
            {
            sql="update BOOKS set GENRE='"+Genre+"',SECTION="+section+",NUMCOPIES="+Quantity+",DESCREPTION='"+desc+"',POSITION="+row+",BOOK_PIC='"+path1+"' where ISBN="+ISBN1;
            }
            else
            sql="update BOOKS set GENRE='"+Genre+"',SECTION="+section+",NUMCOPIES="+Quantity+",DESCREPTION='"+desc+"',POSITION="+row+" where ISBN="+ISBN1;    
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
        
        
        
        
    }//GEN-LAST:event_saveBtnActionPerformed

    private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnActionPerformed
        // TODO add your handling code here:
         JFileChooser file = new JFileChooser();
        file.setCurrentDirectory ( new File ( System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","jpg","gif","png","bmp","jpeg");
        file.addChoosableFileFilter(filter); 
        int result = file.showSaveDialog(null);
        if ( result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            
            String path = selectedFile.getAbsolutePath();
            path1=path;
            ImageIcon myImage  = new ImageIcon(path);
            Image img = myImage.getImage();
            Image newImg = img.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(newImg);
            jLabel2.setIcon(image);
        }
    }//GEN-LAST:event_browseBtnActionPerformed

    private void rowFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rowFieldKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c==KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_rowFieldKeyTyped

    private void sectionField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sectionField1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c==KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_sectionField1KeyTyped

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
            java.util.logging.Logger.getLogger(bookJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bookJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bookJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bookJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bookJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField authorField;
    private javax.swing.JButton browseBtn;
    private javax.swing.JTextArea descriptionField;
    private javax.swing.JButton editBtn;
    private javax.swing.JTextField isbnField;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField rowField;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField sectionField;
    private javax.swing.JTextField sectionField1;
    private javax.swing.JTextField sectionField2;
    // End of variables declaration//GEN-END:variables
}
