/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import com.sun.glass.events.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import static library.LogIn.getMd5;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import oracle.jdbc.pool.OracleDataSource;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author LENOVO
 */

public class AdminGUI extends javax.swing.JFrame {
    boolean checkNull(String s)
    {
        if(!(s!=null&&!s.isEmpty()))
            return true;
        else
            return false;
    }
    public String iPath;
    String DateConv(Calendar calendar)
    {
        SimpleDateFormat dateFormatY = new SimpleDateFormat("YYYY");
        SimpleDateFormat dateFormatM = new SimpleDateFormat("MM");
        SimpleDateFormat dateFormatD = new SimpleDateFormat("dd");
        String monthV = dateFormatM.format(calendar.getTime());
   
        switch (monthV) {
            case "01":  monthV= "JAN";
                     break;
            case "02":  monthV= "FEB";
                   break;
            case "03":  monthV= "MAR";
                     break;
            case "04":  monthV= "APR";
                     break;
            case "05":  monthV=  "MAY";
                     break;
            case "06":  monthV= "JUN";
                     break;
            case "07":  monthV= "JUL";
                     break;
            case "08":  monthV=  "AUG";
                     break;
            case "09":  monthV= "SEP";
                     break;
            case "10": monthV= "OCT";
                     break;
            case "11": monthV= "NOV";
                     break;
            case "12": monthV= "DEC";
                     break;
            default: monthV= "Invalid month";
        }
    
           String datef=dateFormatD.format(calendar.getTime())+ "-"+ monthV +"-" +dateFormatY.format(calendar.getTime());
           return datef;
    
    }

    /**
     * Creates new form AdminGUI
     */
    public AdminGUI() {
        initComponents();
    }
     public AdminGUI(Employee e) {
         initComponents();
         if(e.getEmpType().equals("Normal"))
         {
             addbBtn.setVisible(false);
             suggestBtn.setVisible(false);
             addempBtn.setVisible(false);
         }
         try
        {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            String sql1="select * from IMAGES where IMAGE_ID='"+e.getEmpId()+"'";
            PreparedStatement ps=con.prepareStatement(sql1);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                String path=rs.getString("IMAGE_PATH");
                ImageIcon myImage  = new ImageIcon(path);
                Image img = myImage.getImage();
                 Image newImg = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon image = new ImageIcon(newImg);
                imageLabel.setIcon(image);
                newImg = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                image = new ImageIcon(newImg);
                imageLabel.setIcon(image);
                        
            }
            
            con.commit();
            con.close();
        }
         catch(Exception E)
         {
             
         }
         try
        {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            String sql1="select * from BOOKS order by NUM_TIME_B DESC";
            PreparedStatement ps=con.prepareStatement(sql1);
            ResultSet rs=ps.executeQuery();
            
            DefaultCategoryDataset data = new DefaultCategoryDataset();
             
                  for (int i =0; i<4;i++){
                       if(rs.next())
                   {
                      data.setValue(Double.parseDouble(rs.getString("NUM_TIME_B")),"Number",rs.getString("TITLE"));
                      jLabel2.setText(rs.getString("TITLE"));
                  
                  JFreeChart barChart = ChartFactory.createBarChart("Number of Times Borrowed", "Book Title", "Numbers", data, PlotOrientation.VERTICAL, rootPaneCheckingEnabled, rootPaneCheckingEnabled, rootPaneCheckingEnabled);
                  CategoryPlot barch = barChart.getCategoryPlot();
                  barch.setRangeGridlinePaint(Color.MAGENTA);
                  ChartPanel barP = new ChartPanel (barChart);
                  panel.add(barP,BorderLayout.CENTER);
                  panel.validate();
                   }  
              }    
        }
         catch( Exception h )
         {
             System.out.println(h.getMessage());
         }
         
         
         
         
         
         
         
         
        try
        {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            String sql1="select * from SUGGESTION";
            PreparedStatement ps=con.prepareStatement(sql1);
            ResultSet rs=ps.executeQuery();
            int i=0;
            while(rs.next())
            {
                
            
                jTable1.getModel().setValueAt(rs.getString("SUGTITLE"),i , 0);
                jTable1.getModel().setValueAt(rs.getString("SUGGENRE"),i , 1);
                jTable1.getModel().setValueAt(rs.getString("SUGISBN"),i , 2);
                jTable1.getModel().setValueAt(rs.getString("SUGAUTHOR"),i , 3);
                jTable1.getModel().setValueAt(rs.getString("NOTESUG"),i , 4);
                i++;
            }
            con.commit();
            con.close();
        }
        catch(Exception ex)
        {
            
        }
        jLabel15.setText(e.getEmpId());
        jLabel68.setText(e.getEmpId());
        jLabel69.setText(e.getPerson1().getPasword());
        jLabel13.setText(e.getStartHour().toString()+":00");
        jLabel8.setText(e.getEndHour().toString()+":00");
        jLabel9.setText(e.getNoHours().toString());
        jLabel14.setText(e.getSlryEmp()+"");
        fnamePField.setText(e.getPerson1().getFName());
        lnamePField.setText(e.getPerson1().getLName());
        phonePField.setText(e.getPerson1().getPhonenumber().toString());
        birthField.setText(e.getPerson1().getBirthday().toString());
        userPField.setText(e.getPerson1().getUsername());
        emailPField.setText(e.getPerson1().getEmail());
        passPField.setText(e.getPerson1().getPasword());
        cityPField.setText(e.getPerson1().getCity());
        stPField.setText(e.getPerson1().getStreet());
        buildPField.setText(e.getPerson1().getBuilding());
        String path = "";
        nameLabel.setText(e.getPerson1().getFName()+" "+e.getPerson1().getLName());
        try
        {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            
            String sql1="select * from IMAGES where IMAGE_ID='"+e.getEmpId()+"'";
            PreparedStatement ps=con.prepareStatement(sql1);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                path=rs.getString("IMAGE_PATH");
            }
            con.commit();
            con.close();
            
        }
        catch(Exception ex)
        {
            
        }
        
        ImageIcon myImage  = new ImageIcon(path);
        Image img = myImage.getImage();
        Image newImg = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        imageLabel.setIcon(image);
        newImg = img.getScaledInstance(jLabel55.getWidth(), jLabel55.getHeight(), Image.SCALE_SMOOTH);
        image = new ImageIcon(newImg);
        jLabel55.setIcon(image);
        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        memberGrp = new javax.swing.ButtonGroup();
        jTextField13 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        GenderGroup = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dbBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        borrowBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        profileBtn = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        suggestBtn = new javax.swing.JButton();
        memberBtn = new javax.swing.JButton();
        addbBtn = new javax.swing.JButton();
        addempBtn = new javax.swing.JButton();
        returnBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cardPanel = new javax.swing.JPanel();
        dbPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        searchPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();
        grid = new javax.swing.JPanel();
        borrowPanel = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        checkBBtn = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        checkLabel = new javax.swing.JLabel();
        borrowBBtn = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        returnPanel = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        overdueBtn = new javax.swing.JButton();
        overdueLabel = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jTextField8 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        suggestPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        memberPanel = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        grid1 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        addBookPanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        bookField = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        addEmpPanel = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        savepBtn = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        phoneField = new javax.swing.JTextField();
        browseBtn = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        passLabel = new javax.swing.JLabel();
        buildingLabel = new javax.swing.JLabel();
        cityLabel = new javax.swing.JLabel();
        fnameField = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        buildField = new javax.swing.JTextField();
        passField = new javax.swing.JPasswordField();
        cityField = new javax.swing.JTextField();
        userLabel = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        lnameField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        userField = new javax.swing.JTextField();
        streetLabel = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel52 = new javax.swing.JLabel();
        stField = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel54 = new javax.swing.JLabel();
        userLabel1 = new javax.swing.JLabel();
        shourField = new javax.swing.JTextField();
        emailLabel1 = new javax.swing.JLabel();
        ehourField = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel53 = new javax.swing.JLabel();
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        profilePanel = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        lnamePField = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        browsePBtn = new javax.swing.JButton();
        streetLabel1 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        fnamePField = new javax.swing.JTextField();
        passPField = new javax.swing.JPasswordField();
        birthField = new javax.swing.JTextField();
        phonePField = new javax.swing.JTextField();
        stPField = new javax.swing.JTextField();
        cityPField = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        savepPBtn = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        emailLabel2 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel61 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        cityLabel1 = new javax.swing.JLabel();
        userPField = new javax.swing.JTextField();
        buildingLabel1 = new javax.swing.JLabel();
        buildPField = new javax.swing.JTextField();
        phoneLabel1 = new javax.swing.JLabel();
        passLabel1 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        updatePBtn = new javax.swing.JButton();
        emailPField = new javax.swing.JTextField();
        userLabel2 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();

        jTextField13.setText("jTextField3");

        jLabel25.setText("Book Title:");

        jTextField16.setText("jTextField3");

        jLabel35.setText("Book Publish Year:");

        jTextField18.setText("jTextField3");

        jLabel37.setText("Book Publish Year:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(23, 35, 50));

        imageLabel.setBackground(new java.awt.Color(255, 255, 255));
        imageLabel.setForeground(new java.awt.Color(255, 255, 255));

        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setText("Name");

        jLabel4.setToolTipText("");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(255, 255, 255)));

        dbBtn.setBackground(new java.awt.Color(49, 86, 89));
        dbBtn.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        dbBtn.setForeground(new java.awt.Color(230, 230, 230));
        dbBtn.setText("Dashboard");
        dbBtn.setBorder(null);
        dbBtn.setFocusable(false);
        dbBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbBtnActionPerformed(evt);
            }
        });

        searchBtn.setBackground(new java.awt.Color(2, 17, 27));
        searchBtn.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(230, 230, 230));
        searchBtn.setText("Search");
        searchBtn.setBorder(null);
        searchBtn.setFocusable(false);
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        borrowBtn.setBackground(new java.awt.Color(2, 17, 27));
        borrowBtn.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        borrowBtn.setForeground(new java.awt.Color(230, 230, 230));
        borrowBtn.setText("Borrow");
        borrowBtn.setBorder(null);
        borrowBtn.setFocusable(false);
        borrowBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowBtnActionPerformed(evt);
            }
        });

        jLabel5.setToolTipText("");
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(255, 255, 255)));

        profileBtn.setBackground(new java.awt.Color(2, 17, 27));
        profileBtn.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        profileBtn.setForeground(new java.awt.Color(230, 230, 230));
        profileBtn.setText("Profile");
        profileBtn.setBorder(null);
        profileBtn.setFocusable(false);
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 51, 51));
        jButton6.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(230, 230, 230));
        jButton6.setText("Sign Out");
        jButton6.setBorder(null);
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        suggestBtn.setBackground(new java.awt.Color(2, 17, 27));
        suggestBtn.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        suggestBtn.setForeground(new java.awt.Color(230, 230, 230));
        suggestBtn.setText("Suggestions");
        suggestBtn.setBorder(null);
        suggestBtn.setFocusable(false);
        suggestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suggestBtnActionPerformed(evt);
            }
        });

        memberBtn.setBackground(new java.awt.Color(2, 17, 27));
        memberBtn.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        memberBtn.setForeground(new java.awt.Color(230, 230, 230));
        memberBtn.setText("Members");
        memberBtn.setBorder(null);
        memberBtn.setFocusable(false);
        memberBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberBtnActionPerformed(evt);
            }
        });

        addbBtn.setBackground(new java.awt.Color(2, 17, 27));
        addbBtn.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        addbBtn.setForeground(new java.awt.Color(230, 230, 230));
        addbBtn.setText("Add Book");
        addbBtn.setBorder(null);
        addbBtn.setFocusable(false);
        addbBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbBtnActionPerformed(evt);
            }
        });

        addempBtn.setBackground(new java.awt.Color(2, 17, 27));
        addempBtn.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        addempBtn.setForeground(new java.awt.Color(230, 230, 230));
        addempBtn.setText("Add Employee");
        addempBtn.setBorder(null);
        addempBtn.setFocusable(false);
        addempBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addempBtnActionPerformed(evt);
            }
        });

        returnBtn.setBackground(new java.awt.Color(2, 17, 27));
        returnBtn.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        returnBtn.setForeground(new java.awt.Color(230, 230, 230));
        returnBtn.setText("Return");
        returnBtn.setBorder(null);
        returnBtn.setFocusable(false);
        returnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addempBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addbBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dbBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(borrowBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(suggestBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(profileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(memberBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(returnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(dbBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(borrowBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(returnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(memberBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addbBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(suggestBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addempBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 660));

        jPanel5.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Dashboard");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(686, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(42, 42, 42))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 820, 90));

        cardPanel.setLayout(new java.awt.CardLayout());

        dbPanel.setBackground(new java.awt.Color(255, 255, 255));
        dbPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        dbPanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 106, 820, 10));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        dbPanel.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(826, 106, -1, -1));

        jLabel12.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel12.setText("Employee ID:");
        dbPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel15.setText("jLabel15");
        dbPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 21, -1, 30));

        jLabel10.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel10.setText("Total Hours Worked:");
        dbPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 62, -1, -1));

        jLabel13.setText("jLabel13");
        dbPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 29, -1, -1));

        jLabel11.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel11.setText("Salary:");
        dbPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 62, -1, -1));

        jLabel14.setText("jLabel14");
        dbPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 71, -1, -1));

        jLabel6.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel6.setText("Starting Hour:");
        dbPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 20, -1, -1));

        jLabel7.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel7.setText("Ending Hour:");
        dbPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(556, 20, -1, -1));

        jLabel8.setText("jLabel8");
        dbPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(674, 29, 95, -1));

        jLabel9.setText("jLabel9");
        dbPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 71, 87, -1));

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setLayout(new java.awt.BorderLayout());
        dbPanel.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 780, 450));
        dbPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, -1, -1));

        jButton2.setBackground(new java.awt.Color(255, 51, 102));
        jButton2.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Get Report");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        dbPanel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, -1, -1));

        cardPanel.add(dbPanel, "card2");

        searchPanel.setBackground(new java.awt.Color(255, 255, 255));
        searchPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        searchPanel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 84, 820, 10));

        jButton1.setBackground(new java.awt.Color(255, 51, 102));
        jButton1.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        searchPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 126, -1));

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jRadioButton4.setText("Genre");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        searchPanel.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 53, -1, -1));

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jRadioButton3.setText("ISBN");
        searchPanel.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 53, -1, -1));

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jRadioButton2.setText("Author");
        searchPanel.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 53, -1, -1));

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Title");
        searchPanel.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 53, -1, -1));

        jTextField1.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        searchPanel.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 24, 554, -1));

        grid.setBackground(new java.awt.Color(255, 255, 255));
        grid.setLayout(new java.awt.GridLayout(1, 0));
        searchPanel.add(grid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 750, 450));

        cardPanel.add(searchPanel, "card3");

        borrowPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel26.setText("Book`s Title:");

        jTextField4.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel29.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel29.setText("Book`s ISBN:");

        jTextField5.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel27.setText("Member ID:");

        jTextField6.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        checkBBtn.setBackground(new java.awt.Color(255, 51, 102));
        checkBBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        checkBBtn.setForeground(new java.awt.Color(255, 255, 255));
        checkBBtn.setText("Check");
        checkBBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBBtnActionPerformed(evt);
            }
        });

        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        borrowBBtn.setBackground(new java.awt.Color(255, 51, 102));
        borrowBBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        borrowBBtn.setForeground(new java.awt.Color(255, 255, 255));
        borrowBBtn.setText("Borrow");
        borrowBBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowBBtnActionPerformed(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));

        jLabel62.setForeground(new java.awt.Color(255, 255, 255));

        jLabel63.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout borrowPanelLayout = new javax.swing.GroupLayout(borrowPanel);
        borrowPanel.setLayout(borrowPanelLayout);
        borrowPanelLayout.setHorizontalGroup(
            borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borrowPanelLayout.createSequentialGroup()
                .addGroup(borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(borrowPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(borrowPanelLayout.createSequentialGroup()
                                .addComponent(checkBBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(borrowBBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(borrowPanelLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel62)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel63))
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(191, 191, 191))
        );
        borrowPanelLayout.setVerticalGroup(
            borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(borrowPanelLayout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkBBtn)
                            .addComponent(checkLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(borrowBBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addGroup(borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19)
                    .addComponent(jLabel62)
                    .addComponent(jLabel63))
                .addGap(89, 89, 89))
        );

        cardPanel.add(borrowPanel, "card4");

        returnPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton5.setBackground(new java.awt.Color(255, 51, 102));
        jButton5.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Return");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        overdueBtn.setBackground(new java.awt.Color(255, 51, 102));
        overdueBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        overdueBtn.setForeground(new java.awt.Color(255, 255, 255));
        overdueBtn.setText("Over due Check");
        overdueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overdueBtnActionPerformed(evt);
            }
        });

        jTextField7.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel28.setText("Member ID:");

        jTextField8.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel30.setText("Book`s ISBN:");

        jTextField9.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel31.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel31.setText("Book`s Title:");

        jLabel32.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel32.setText("Penalty:");

        jTextField10.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField10.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jLabel64.setForeground(new java.awt.Color(255, 255, 255));

        jLabel65.setForeground(new java.awt.Color(255, 255, 255));

        jLabel66.setForeground(new java.awt.Color(255, 255, 255));

        jLabel67.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout returnPanelLayout = new javax.swing.GroupLayout(returnPanel);
        returnPanel.setLayout(returnPanelLayout);
        returnPanelLayout.setHorizontalGroup(
            returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(returnPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, returnPanelLayout.createSequentialGroup()
                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(returnPanelLayout.createSequentialGroup()
                        .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(returnPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(returnPanelLayout.createSequentialGroup()
                                        .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(overdueBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(overdueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE))
                    .addGroup(returnPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel64)
                        .addGap(20, 20, 20)))
                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(returnPanelLayout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel66)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel67))
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(141, 141, 141))
        );
        returnPanelLayout.setVerticalGroup(
            returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(returnPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(returnPanelLayout.createSequentialGroup()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(overdueBtn)
                            .addComponent(overdueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addGap(81, 81, 81)
                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(jLabel65)
                    .addComponent(jLabel66)
                    .addComponent(jLabel67))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        cardPanel.add(returnPanel, "card11");

        suggestPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel20.setText("Recent Suggestions:");

        jTable1.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title", "Genre", "Auther", "ISBN", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout suggestPanelLayout = new javax.swing.GroupLayout(suggestPanel);
        suggestPanel.setLayout(suggestPanelLayout);
        suggestPanelLayout.setHorizontalGroup(
            suggestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(suggestPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(suggestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(suggestPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2))
                    .addGroup(suggestPanelLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(0, 628, Short.MAX_VALUE)))
                .addContainerGap())
        );
        suggestPanelLayout.setVerticalGroup(
            suggestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(suggestPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addGap(155, 155, 155))
        );

        cardPanel.add(suggestPanel, "card6");

        memberPanel.setBackground(new java.awt.Color(255, 255, 255));
        memberPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton8.setBackground(new java.awt.Color(255, 51, 102));
        jButton8.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Search");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        memberPanel.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 27, 126, -1));

        grid1.setBackground(new java.awt.Color(255, 255, 255));
        grid1.setLayout(new java.awt.GridLayout(1, 0));
        memberPanel.add(grid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 750, 450));

        jTextField2.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        memberPanel.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 19, 554, -1));
        memberPanel.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 75, 810, 10));

        jRadioButton5.setBackground(new java.awt.Color(255, 255, 255));
        memberGrp.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jRadioButton5.setText("Name");
        jRadioButton5.setBorder(null);
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });
        memberPanel.add(jRadioButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, -1));

        jRadioButton7.setBackground(new java.awt.Color(255, 255, 255));
        memberGrp.add(jRadioButton7);
        jRadioButton7.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jRadioButton7.setText("E-mail");
        jRadioButton7.setBorder(null);
        memberPanel.add(jRadioButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, -1));

        jRadioButton8.setBackground(new java.awt.Color(255, 255, 255));
        memberGrp.add(jRadioButton8);
        jRadioButton8.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jRadioButton8.setText("Username");
        jRadioButton8.setBorder(null);
        memberPanel.add(jRadioButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, -1, -1));

        jRadioButton9.setBackground(new java.awt.Color(255, 255, 255));
        memberGrp.add(jRadioButton9);
        jRadioButton9.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jRadioButton9.setText("Phone Number");
        jRadioButton9.setBorder(null);
        memberPanel.add(jRadioButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, -1, -1));

        cardPanel.add(memberPanel, "card7");

        addBookPanel.setBackground(new java.awt.Color(255, 255, 255));
        addBookPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addBookPanelKeyPressed(evt);
            }
        });

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel22.setText("Book Title:");

        bookField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        bookField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jTextField11.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel23.setText("Book Genre:");

        jTextField12.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField12.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField12KeyTyped(evt);
            }
        });

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel24.setText("Book Edition:");

        jTextField14.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField14KeyTyped(evt);
            }
        });

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel33.setText("Book Publish Year:");

        jTextField15.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField15.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel34.setText("Book Author (First Name):");

        jTextField17.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField17.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField17KeyTyped(evt);
            }
        });

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel36.setText("Book ISBN:");

        jTextField19.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField19.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel38.setText("Book Author (Surname):");

        jLabel39.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel39.setText("Book Position:");

        jLabel40.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel40.setText("R:");

        jLabel41.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel41.setText("#");

        jLabel42.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel42.setText("Section:");

        jTextField3.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jTextField20.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField20.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jTextField20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField20KeyTyped(evt);
            }
        });

        jTextField21.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField21.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel43.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel43.setText("Description:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel44.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel44.setText("Book Cover:");

        jLabel45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton9.setBackground(new java.awt.Color(255, 51, 102));
        jButton9.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Browse");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(255, 51, 102));
        jButton10.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Add");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addBookPanelLayout = new javax.swing.GroupLayout(addBookPanel);
        addBookPanel.setLayout(addBookPanelLayout);
        addBookPanelLayout.setHorizontalGroup(
            addBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(addBookPanelLayout.createSequentialGroup()
                        .addGroup(addBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addBookPanelLayout.createSequentialGroup()
                                .addGroup(addBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel38)
                                    .addComponent(jTextField15)
                                    .addComponent(jTextField19)
                                    .addComponent(bookField)
                                    .addComponent(jLabel36)
                                    .addComponent(jTextField17))
                                .addGap(18, 18, 18)
                                .addGroup(addBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel33)
                                    .addComponent(jTextField14)
                                    .addComponent(jTextField12)
                                    .addComponent(jTextField11)
                                    .addComponent(jLabel39)
                                    .addGroup(addBookPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel40)
                                        .addGap(2, 2, 2)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel41)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel42)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel43))
                        .addGap(54, 54, 54)
                        .addGroup(addBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        addBookPanelLayout.setVerticalGroup(
            addBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addBookPanelLayout.createSequentialGroup()
                        .addGroup(addBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addBookPanelLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bookField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addBookPanelLayout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addComponent(jLabel36)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43))
                    .addGroup(addBookPanelLayout.createSequentialGroup()
                        .addGroup(addBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addBookPanelLayout.createSequentialGroup()
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel42)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        cardPanel.add(addBookPanel, "card8");

        addEmpPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel46.setText("PP");
        jLabel46.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        phoneLabel.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        phoneLabel.setText("Phone Number:");

        savepBtn.setBackground(new java.awt.Color(230, 57, 70));
        savepBtn.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        savepBtn.setForeground(new java.awt.Color(241, 250, 238));
        savepBtn.setText("Add");
        savepBtn.setMaximumSize(new java.awt.Dimension(67, 23));
        savepBtn.setMinimumSize(new java.awt.Dimension(67, 23));
        savepBtn.setPreferredSize(new java.awt.Dimension(67, 23));
        savepBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savepBtnActionPerformed(evt);
            }
        });

        phoneField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        phoneField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        phoneField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneFieldKeyTyped(evt);
            }
        });

        browseBtn.setBackground(new java.awt.Color(230, 57, 70));
        browseBtn.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        browseBtn.setForeground(new java.awt.Color(241, 250, 238));
        browseBtn.setText("Browse");
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel49.setText("Date of Birth:");

        jLabel50.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel50.setText("Basic Info");

        passLabel.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        passLabel.setText("Password:");

        buildingLabel.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        buildingLabel.setText("Building:");

        cityLabel.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        cityLabel.setText("City:");

        fnameField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        fnameField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel47.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel47.setText("First Name:");

        emailLabel.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        emailLabel.setText("Email:");

        buildField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        buildField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        passField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        passField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        cityField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        cityField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        userLabel.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        userLabel.setText("Username:");

        jLabel51.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel51.setText("Login Credentials");

        lnameField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        lnameField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        emailField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        emailField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        userField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        userField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        streetLabel.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        streetLabel.setText("Street:");

        jLabel52.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel52.setText("Address:");

        stField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        stField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        stField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stFieldActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel48.setText("Last Name:");

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel54.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel54.setText("Work Info");

        userLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        userLabel1.setText("Starting Hour:");

        shourField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        shourField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        shourField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                shourFieldKeyTyped(evt);
            }
        });

        emailLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        emailLabel1.setText("Ending Hour:");

        ehourField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        ehourField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        ehourField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ehourFieldActionPerformed(evt);
            }
        });
        ehourField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ehourFieldKeyTyped(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel53.setText("Gender:");

        jRadioButton10.setBackground(new java.awt.Color(255, 255, 255));
        GenderGroup.add(jRadioButton10);
        jRadioButton10.setFont(new java.awt.Font("Nirmala UI", 1, 11)); // NOI18N
        jRadioButton10.setSelected(true);
        jRadioButton10.setFocusPainted(false);
        jRadioButton10.setFocusable(false);
        jRadioButton10.setLabel("Male");
        jRadioButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton10ActionPerformed(evt);
            }
        });

        jRadioButton11.setBackground(new java.awt.Color(255, 255, 255));
        GenderGroup.add(jRadioButton11);
        jRadioButton11.setFont(new java.awt.Font("Nirmala UI", 1, 11)); // NOI18N
        jRadioButton11.setText("Female");
        jRadioButton11.setFocusPainted(false);
        jRadioButton11.setFocusable(false);

        javax.swing.GroupLayout addEmpPanelLayout = new javax.swing.GroupLayout(addEmpPanel);
        addEmpPanel.setLayout(addEmpPanelLayout);
        addEmpPanelLayout.setHorizontalGroup(
            addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addEmpPanelLayout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49)
                    .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneLabel)
                            .addComponent(jLabel48)
                            .addComponent(jLabel47))
                        .addComponent(jLabel53)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lnameField, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(phoneField)
                    .addGroup(addEmpPanelLayout.createSequentialGroup()
                        .addComponent(jRadioButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton11)))
                .addGap(484, 484, 484))
            .addGroup(addEmpPanelLayout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(addEmpPanelLayout.createSequentialGroup()
                        .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userLabel1)
                            .addComponent(emailLabel1)
                            .addComponent(ehourField)
                            .addComponent(shourField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(454, 454, 454))
            .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(addEmpPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator10)
                        .addComponent(jSeparator9)
                        .addGroup(addEmpPanelLayout.createSequentialGroup()
                            .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel50)
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(userLabel)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(emailLabel)
                                    .addComponent(passLabel)
                                    .addComponent(passField)
                                    .addComponent(emailField)
                                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel52)
                                .addGroup(addEmpPanelLayout.createSequentialGroup()
                                    .addComponent(cityLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cityField, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(streetLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(stField, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35)
                                    .addComponent(buildingLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(buildField, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(savepBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 325, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        addEmpPanelLayout.setVerticalGroup(
            addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addEmpPanelLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(fnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabel)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jRadioButton10)
                    .addComponent(jRadioButton11))
                .addGap(50, 50, 50)
                .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addEmpPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shourField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(emailLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ehourField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(137, Short.MAX_VALUE))
            .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(addEmpPanelLayout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jLabel50)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(jLabel51)
                    .addGap(18, 18, 18)
                    .addComponent(userLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(emailLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(passLabel)
                    .addGap(4, 4, 4)
                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel52)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(addEmpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cityLabel)
                        .addComponent(cityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(streetLabel)
                        .addComponent(stField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buildingLabel)
                        .addComponent(buildField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(savepBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(21, Short.MAX_VALUE)))
        );

        cardPanel.add(addEmpPanel, "card9");

        profilePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel55.setText("PP");
        jLabel55.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lnamePField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        lnamePField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        browsePBtn.setBackground(new java.awt.Color(230, 57, 70));
        browsePBtn.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        browsePBtn.setForeground(new java.awt.Color(241, 250, 238));
        browsePBtn.setText("Browse");
        browsePBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browsePBtnActionPerformed(evt);
            }
        });

        streetLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        streetLabel1.setText("Street:");

        jLabel60.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel60.setText("Login Credentials");

        fnamePField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        fnamePField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        passPField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        passPField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        birthField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        birthField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        phonePField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        phonePField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        phonePField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phonePFieldKeyTyped(evt);
            }
        });

        stPField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        stPField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        stPField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stPFieldActionPerformed(evt);
            }
        });

        cityPField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        cityPField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel57.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel57.setText("Last Name:");

        savepPBtn.setBackground(new java.awt.Color(230, 57, 70));
        savepPBtn.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        savepPBtn.setForeground(new java.awt.Color(241, 250, 238));
        savepPBtn.setText("Save");
        savepPBtn.setMaximumSize(new java.awt.Dimension(67, 23));
        savepPBtn.setMinimumSize(new java.awt.Dimension(67, 23));
        savepPBtn.setPreferredSize(new java.awt.Dimension(67, 23));
        savepPBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savepPBtnActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel59.setText("Basic Info");

        emailLabel2.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        emailLabel2.setText("Email:");

        jLabel61.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel61.setText("Address:");

        jLabel56.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel56.setText("First Name:");

        cityLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        cityLabel1.setText("City:");

        userPField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        userPField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        buildingLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        buildingLabel1.setText("Building:");

        buildPField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        buildPField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        phoneLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        phoneLabel1.setText("Phone Number:");

        passLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        passLabel1.setText("Password:");

        jLabel58.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel58.setText("Date of Birth:");

        updatePBtn.setBackground(new java.awt.Color(230, 57, 70));
        updatePBtn.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        updatePBtn.setForeground(new java.awt.Color(241, 250, 238));
        updatePBtn.setText("Update");
        updatePBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePBtnActionPerformed(evt);
            }
        });

        emailPField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        emailPField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        emailPField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailPFieldActionPerformed(evt);
            }
        });

        userLabel2.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        userLabel2.setText("Username:");

        jLabel68.setForeground(new java.awt.Color(255, 255, 255));

        jLabel69.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout profilePanelLayout = new javax.swing.GroupLayout(profilePanel);
        profilePanel.setLayout(profilePanelLayout);
        profilePanelLayout.setHorizontalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePanelLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(phoneLabel1))
                .addGap(18, 18, 18)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lnamePField, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(birthField)
                    .addComponent(phonePField)
                    .addComponent(fnamePField))
                .addContainerGap(468, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, profilePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69)
                    .addComponent(jLabel68))
                .addGap(153, 153, 153))
            .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(profilePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(profilePanelLayout.createSequentialGroup()
                            .addComponent(jLabel59)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updatePBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(77, 77, 77))
                        .addGroup(profilePanelLayout.createSequentialGroup()
                            .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(profilePanelLayout.createSequentialGroup()
                                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel56)
                                        .addComponent(jLabel57)))
                                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(userLabel2)
                                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(emailLabel2)
                                    .addComponent(passLabel1)
                                    .addComponent(passPField)
                                    .addComponent(emailPField)
                                    .addComponent(userPField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel61)
                                .addGroup(profilePanelLayout.createSequentialGroup()
                                    .addComponent(cityLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cityPField, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(streetLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(stPField, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35)
                                    .addComponent(buildingLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(buildPField, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(browsePBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(savepPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(335, Short.MAX_VALUE))
                        .addGroup(profilePanelLayout.createSequentialGroup()
                            .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator12)
                                .addComponent(jSeparator11))
                            .addContainerGap()))))
        );
        profilePanelLayout.setVerticalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePanelLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(fnamePField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lnamePField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(phonePField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(birthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58))
                .addGap(146, 146, 146)
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel69)
                .addContainerGap(244, Short.MAX_VALUE))
            .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(profilePanelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(updatePBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel59))
                    .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(profilePanelLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(jLabel56)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel57))
                        .addGroup(profilePanelLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(browsePBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(jLabel60)
                    .addGap(18, 18, 18)
                    .addComponent(userLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(userPField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(emailLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(emailPField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(passLabel1)
                    .addGap(4, 4, 4)
                    .addComponent(passPField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel61)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cityLabel1)
                        .addComponent(cityPField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(streetLabel1)
                        .addComponent(stPField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buildingLabel1)
                        .addComponent(buildPField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(savepPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(21, Short.MAX_VALUE)))
        );

        cardPanel.add(profilePanel, "card10");

        getContentPane().add(cardPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 820, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dbBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbBtnActionPerformed
        // TODO add your handling code here:
        Color clicked = new Color (49,86,89);
        Color ntClicked = new Color (2,17,27);
        dbBtn.setBackground(clicked);
        

        returnBtn.setBackground(ntClicked);
        memberBtn.setBackground(ntClicked);
        addbBtn.setBackground(ntClicked);
        addempBtn.setBackground(ntClicked);
        searchBtn.setBackground(ntClicked);
        borrowBtn.setBackground(ntClicked);
        suggestBtn.setBackground(ntClicked);
        returnBtn.setBackground(ntClicked);
        profileBtn.setBackground(ntClicked);
        
        
        jLabel1.setText("Dashboard");

        dbPanel.setVisible(true);
        searchPanel.setVisible(false);
        borrowPanel.setVisible(false);
        returnPanel.setVisible(false);
        memberPanel.setVisible(false);
        addBookPanel.setVisible(false);
        addEmpPanel.setVisible(false);
        suggestPanel.setVisible(false);
        profilePanel.setVisible(false);
        try
        {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            String sql1="select * from BOOKS order by NUM_TIME_B DESC";
            PreparedStatement ps=con.prepareStatement(sql1);
            ResultSet rs=ps.executeQuery();
            
            DefaultCategoryDataset data = new DefaultCategoryDataset();
             
                  for (int i =0; i<4;i++){
                       if(rs.next())
                   {
                      data.setValue(Double.parseDouble(rs.getString("NUM_TIME_B")),"Number",rs.getString("TITLE"));
                      jLabel2.setText(rs.getString("TITLE"));
                  
                  JFreeChart barChart = ChartFactory.createBarChart("Number of Times Borrowed", "Book Title", "Numbers", data, PlotOrientation.VERTICAL, rootPaneCheckingEnabled, rootPaneCheckingEnabled, rootPaneCheckingEnabled);
                  CategoryPlot barch = barChart.getCategoryPlot();
                  barch.setRangeGridlinePaint(Color.MAGENTA);
                  ChartPanel barP = new ChartPanel (barChart);
                  panel.add(barP,BorderLayout.CENTER);
                  panel.validate();
                  panel.repaint();
                   }  
              }    
        }
         catch( Exception h )
         {
             System.out.println(h.getMessage());
         }

    }//GEN-LAST:event_dbBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
       Color clicked = new Color (49,86,89);
        Color ntClicked = new Color (2,17,27);
        dbBtn.setBackground(ntClicked);
        

        returnBtn.setBackground(ntClicked);
        memberBtn.setBackground(ntClicked);
        addbBtn.setBackground(ntClicked);
        addempBtn.setBackground(ntClicked);
        searchBtn.setBackground(clicked);
        borrowBtn.setBackground(ntClicked);
        suggestBtn.setBackground(ntClicked);
        returnBtn.setBackground(ntClicked);
        profileBtn.setBackground(ntClicked);
        jLabel1.setText("Search");

        dbPanel.setVisible(false);
        searchPanel.setVisible(true);
        borrowPanel.setVisible(false);
        returnPanel.setVisible(false);
        memberPanel.setVisible(false);
        addBookPanel.setVisible(false);
        addEmpPanel.setVisible(false);
        suggestPanel.setVisible(false);
        profilePanel.setVisible(false);
    }//GEN-LAST:event_searchBtnActionPerformed

    private void borrowBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowBtnActionPerformed
        // TODO add your handling code here:Color clicked = new Color (49,86,89);
        Color clicked = new Color (49,86,89);
        Color ntClicked = new Color (2,17,27);
        dbBtn.setBackground(ntClicked);
        
        borrowBBtn.setEnabled(false);
      

        returnBtn.setBackground(ntClicked);
        memberBtn.setBackground(ntClicked);
        addbBtn.setBackground(ntClicked);
        addempBtn.setBackground(ntClicked);
        searchBtn.setBackground(ntClicked);
        borrowBtn.setBackground(clicked);
        suggestBtn.setBackground(ntClicked);
        returnBtn.setBackground(ntClicked);
        profileBtn.setBackground(ntClicked);
        jLabel1.setText("Borrow");

        dbPanel.setVisible(false);
        searchPanel.setVisible(false);
        borrowPanel.setVisible(true);
        returnPanel.setVisible(false);
        memberPanel.setVisible(false);
        addBookPanel.setVisible(false);
        addEmpPanel.setVisible(false);
        suggestPanel.setVisible(false);
        profilePanel.setVisible(false);

    }//GEN-LAST:event_borrowBtnActionPerformed

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
        // TODO add your handling code here:
        Color clicked = new Color (49,86,89);
        Color ntClicked = new Color (2,17,27);
        dbBtn.setBackground(ntClicked);
        

        returnBtn.setBackground(ntClicked);
        memberBtn.setBackground(ntClicked);
        addbBtn.setBackground(ntClicked);
        addempBtn.setBackground(ntClicked);
        searchBtn.setBackground(ntClicked);
        borrowBtn.setBackground(ntClicked);
        suggestBtn.setBackground(ntClicked);
        returnBtn.setBackground(ntClicked);
        profileBtn.setBackground(clicked);
        
        jLabel1.setText("Profile");

        dbPanel.setVisible(false);
        searchPanel.setVisible(false);
        borrowPanel.setVisible(false);
        returnPanel.setVisible(false);
        memberPanel.setVisible(false);
        addBookPanel.setVisible(false);
        addEmpPanel.setVisible(false);
        suggestPanel.setVisible(false);
        profilePanel.setVisible(true);

        updatePBtn.setVisible(true);
        savepPBtn.setVisible(false);
        browsePBtn.setVisible(false);

        fnamePField.setEditable(false);
        lnamePField.setEditable(false);
        phonePField.setEditable(false);
        birthField.setEditable(false);
        userPField.setEditable(false);
        passPField.setEditable(false);
        emailPField.setEditable(false);
        cityPField.setEditable(false);
        stPField.setEditable(false);
        buildPField.setEditable(false);

    }//GEN-LAST:event_profileBtnActionPerformed

    private void suggestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suggestBtnActionPerformed
        // TODO add your handling code here:
        Color clicked = new Color (49,86,89);
        Color ntClicked = new Color (2,17,27);
        dbBtn.setBackground(ntClicked);
        

        returnBtn.setBackground(ntClicked);
        memberBtn.setBackground(ntClicked);
        addbBtn.setBackground(ntClicked);
        addempBtn.setBackground(ntClicked);
        searchBtn.setBackground(ntClicked);
        borrowBtn.setBackground(ntClicked);
        suggestBtn.setBackground(clicked);
        returnBtn.setBackground(ntClicked);
        profileBtn.setBackground(ntClicked);
        
        
        jLabel1.setText("Suggestions");
        
        dbPanel.setVisible(false);
        searchPanel.setVisible(false);
        borrowPanel.setVisible(false);
        returnPanel.setVisible(false);
        memberPanel.setVisible(false);
        addBookPanel.setVisible(false);
        addEmpPanel.setVisible(false);
        suggestPanel.setVisible(true);
        profilePanel.setVisible(false);

    }//GEN-LAST:event_suggestBtnActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        grid.removeAll();
        char[]c=jTextField1.getText().toCharArray();
        if(jRadioButton1.isSelected())
        {
            try
            {
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="select * from books where TITLE like '%"+jTextField1.getText()+"%'";
               
                String sql2="select count(*) from books where TITLE like '%"+jTextField1.getText()+"%'";
                PreparedStatement ps2=con.prepareStatement(sql2);
                ResultSet rs2=ps2.executeQuery();
                int Count=0;
                if(rs2.next())
                {
                    Count=rs2.getInt("Count(*)");
                }
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                for(int i=0;rs.next()||i<Count;i++)
                {
                    JLabel l1=new JLabel();
                    l1.setText(rs.getString("TITLE"));
                    l1.setSize(160,230);
                    String path=rs.getString("BOOK_PIC");
                    BigDecimal bISBN=BigDecimal.valueOf(Long.parseLong(rs.getString("ISBN")));
                    ImageIcon myImage  = new ImageIcon(path);
                    Image img = myImage.getImage();
                    Image newImg = img.getScaledInstance(l1.getWidth(), l1.getHeight()-20, Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(newImg);
                    l1.setIcon(image);
                    l1.setHorizontalTextPosition(SwingConstants.CENTER);
                    l1.setVerticalTextPosition(SwingConstants.BOTTOM);
                    l1.addMouseListener(new MouseAdapter()
                    {
                        public void mouseClicked(MouseEvent event)
                        {
                            bookJFrame bjf=new bookJFrame(bISBN);
                            bjf.setVisible(true);
                        }
                    });
                     grid.add(l1);
                }
                   
                    this.repaint();
                    grid.repaint();
                   this.setVisible(false);
                    this.setVisible(true);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        else if(jRadioButton2.isSelected())
        {
            try
            {
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="select * from books,author where (F_NAME  like '%"+jTextField1.getText()+"%' OR SUR_NAME LIKE '%"+jTextField1.getText()+"%') and BOOK_ISBN=ISBN ";
               
                String sql2="select count(*) from author where F_NAME  like '%"+jTextField1.getText()+"%' OR SUR_NAME LIKE '%"+jTextField1.getText()+"%' ";
                PreparedStatement ps2=con.prepareStatement(sql2);
                ResultSet rs2=ps2.executeQuery();
                int Count=0;
                if(rs2.next())
                {
                    Count=rs2.getInt("Count(*)");
                }
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                for(int i=0;rs.next()||i<Count;i++)
                {
                    JLabel l1=new JLabel();
                    l1.setText(rs.getString("TITLE"));
                    l1.setSize(160,230);
                    String path=rs.getString("BOOK_PIC");
                    BigDecimal bISBN=BigDecimal.valueOf(Long.parseLong(rs.getString("ISBN")));
                    ImageIcon myImage  = new ImageIcon(path);
                    Image img = myImage.getImage();
                    Image newImg = img.getScaledInstance(l1.getWidth(), l1.getHeight()-20, Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(newImg);
                    l1.setIcon(image);
                    l1.setHorizontalTextPosition(SwingConstants.CENTER);
                    l1.setVerticalTextPosition(SwingConstants.BOTTOM);
                    l1.addMouseListener(new MouseAdapter()
                    {
                        public void mouseClicked(MouseEvent event)
                        {
                            bookJFrame bjf=new bookJFrame(bISBN);
                            bjf.setVisible(true);
                        }
                    });
                     grid.add(l1);
                }
                   
                    this.repaint();
                    grid.repaint();
                   this.setVisible(false);
                    this.setVisible(true);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        else if(jRadioButton3.isSelected())
        {
            try
            {
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="select * from books where ISBN="+BigInteger.valueOf(Long.parseLong(jTextField1.getText()));
               
                String sql2="select count(*) from BOOKS where ISBN="+BigInteger.valueOf(Long.parseLong(jTextField1.getText()));
                PreparedStatement ps2=con.prepareStatement(sql2);
                ResultSet rs2=ps2.executeQuery();
                int Count=0;
                if(rs2.next())
                {
                    Count=rs2.getInt("Count(*)");
                }
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                for(int i=0;rs.next()||i<Count;i++)
                {
                    JLabel l1=new JLabel();
                    l1.setText(rs.getString("TITLE"));
                    l1.setSize(160,230);
                    String path=rs.getString("BOOK_PIC");
                    BigDecimal bISBN=BigDecimal.valueOf(Long.parseLong(rs.getString("ISBN")));
                    ImageIcon myImage  = new ImageIcon(path);
                    Image img = myImage.getImage();
                    Image newImg = img.getScaledInstance(l1.getWidth(), l1.getHeight()-20, Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(newImg);
                    l1.setIcon(image);
                    l1.setHorizontalTextPosition(SwingConstants.CENTER);
                    l1.setVerticalTextPosition(SwingConstants.BOTTOM);
                    l1.addMouseListener(new MouseAdapter()
                    {
                        public void mouseClicked(MouseEvent event)
                        {
                            bookJFrame bjf=new bookJFrame(bISBN);
                            bjf.setVisible(true);
                        }
                    });
                     grid.add(l1);
                }
                   
                    this.repaint();
                    grid.repaint();
                   this.setVisible(false);
                    this.setVisible(true);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        else if(jRadioButton4.isSelected())
        {
             
            try
            {
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="select * from books where GENRE like '%"+jTextField1.getText()+"%'";
               
                String sql2="select count(*) from books where GENRE like '%"+jTextField1.getText()+"%'";
                PreparedStatement ps2=con.prepareStatement(sql2);
                ResultSet rs2=ps2.executeQuery();
                int Count=0;
                if(rs2.next())
                {
                    Count=rs2.getInt("Count(*)");
                }
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                for(int i=0;rs.next()||i<Count;i++)
                {
                    JLabel l1=new JLabel();
                    l1.setText(rs.getString("TITLE"));
                    l1.setSize(160,230);
                    String path=rs.getString("BOOK_PIC");
                    BigDecimal bISBN=BigDecimal.valueOf(Long.parseLong(rs.getString("ISBN")));
                    ImageIcon myImage  = new ImageIcon(path);
                    Image img = myImage.getImage();
                    Image newImg = img.getScaledInstance(l1.getWidth(), l1.getHeight()-20, Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(newImg);
                    l1.setIcon(image);
                    l1.setHorizontalTextPosition(SwingConstants.CENTER);
                    l1.setVerticalTextPosition(SwingConstants.BOTTOM);
                    l1.addMouseListener(new MouseAdapter()
                    {
                        public void mouseClicked(MouseEvent event)
                        {
                            bookJFrame bjf=new bookJFrame(bISBN);
                            bjf.setVisible(true);
                        }
                    });
                     grid.add(l1);
                }
                   
                    this.repaint();
                    grid.repaint();
                   this.setVisible(false);
                    this.setVisible(true);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void stFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stFieldActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_stFieldActionPerformed

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
            iPath=path;
            ImageIcon myImage  = new ImageIcon(path);
            Image img = myImage.getImage();
            Image newImg = img.getScaledInstance(jLabel46.getWidth(), jLabel46.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(newImg);
            jLabel46.setIcon(image);
        }
    }//GEN-LAST:event_browseBtnActionPerformed

    private void savepBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savepBtnActionPerformed
        // TODO add your handling code here:
        String fname =fnameField.getText();
        String lname=lnameField.getText();
        String uniqueID = UUID.randomUUID().toString();
        String ID=uniqueID.substring(0,6);
        int PNumber=Integer.parseInt(phoneField.getText());
        Calendar bday=jDateChooser1.getCalendar();
        String bDate=DateConv(bday);
        String username=userField.getText();
        String email=emailField.getText();
        String regex =  "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        String pass=getMd5(passField.getText());
        int sh=Integer.parseInt(shourField.getText());
        int eh=Integer.parseInt(ehourField.getText());
        String city=cityField.getText();
        String street=stField.getText();
        String build=buildField.getText();
        int nHours=(eh-sh)*5;
        int salary=nHours*70;
        char gender='m';
        if(jRadioButton11.isSelected())
            gender='f';
        if(fname.isEmpty()&&fname.startsWith(" "))
            JOptionPane.showMessageDialog(null,"Enter First Name");
        else if(lname.isEmpty()&&lname.startsWith(" "))
            JOptionPane.showMessageDialog(null,"Enter Last Name");
        else if(username.isEmpty()&&username.startsWith(" "))
            JOptionPane.showMessageDialog(null,"Enter Username");
        else if(pass.isEmpty()&&pass.startsWith(" "))
            JOptionPane.showMessageDialog(null,"Enter Password");
        else if(phoneField.getText().isEmpty()&&phoneField.getText().startsWith(" "))
            JOptionPane.showMessageDialog(null,"Enter Phone Number");
        else if(shourField.getText().isEmpty()&&shourField.getText().startsWith(" "))
            JOptionPane.showMessageDialog(null,"Enter Start Hour");
        else if(ehourField.getText().isEmpty()&&ehourField.getText().startsWith(" "))
            JOptionPane.showMessageDialog(null,"Enter End Hour");
        else if(matcher.matches())
        {
            if(city.isEmpty()&&city.startsWith(" "))
                city=null;
            if(street.isEmpty()&&street.startsWith(" "))
                street=null;
            if(build.isEmpty()&&build.startsWith(" "))
                build=null;
            try
            {
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="insert into PERSON1 values('"+fname+"','"+lname+"','"+gender+"','"+bDate+"','"+city+"','"+street+"','"+build+"','"+email
                        +"','"+pass+"','"+username+"','"+ID+"',"+PNumber+")";
                String sql2="insert into EMPLOYEE values('"+ID+"','Normal',"+sh+","+eh+","+nHours+","+salary+")";
                String sql3="insert into IMAGES values('"+iPath+"','"+ID+"')";
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                PreparedStatement ps2=con.prepareStatement(sql2);
                ResultSet rs2=ps2.executeQuery();
                PreparedStatement ps3=con.prepareStatement(sql3);
                ResultSet rs3=ps3.executeQuery();
                con.commit();
                con.close();
            }
            catch(Exception E)
            {
                String dup = "ORA-00001: unique constraint (LIBRARY.USERNAMEUNIQUE) violated";
                if(E.getMessage().contains("USERNAMEUNIQUE"))
                JOptionPane.showMessageDialog(null, "Username used");
                if(E.getMessage().contains("EMAILUNIQUE"))
                JOptionPane.showMessageDialog(null, "Email used");
                if(E.getMessage().contains("PHONENUMBERUNIQUE"))
                JOptionPane.showMessageDialog(null, "Phone Number used");
               
            }
            
        }
        
        

        fnameField.setText("");
        lnameField.setText("");
        phoneField.setText("");
        jDateChooser1.setCalendar(null);
        shourField.setText("");
        ehourField.setText("");
        jRadioButton10.setSelected(true);
        jLabel46.setIcon(null);
        jLabel46.setText("PP");
        
        userField.setText("");
        passField.setText("");
        emailField.setText("");
        cityField.setText("");
        stField.setText("");
        buildField.setText("");
    }//GEN-LAST:event_savepBtnActionPerformed

    private void ehourFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ehourFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ehourFieldActionPerformed

    private void emailPFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailPFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailPFieldActionPerformed

    private void stPFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stPFieldActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_stPFieldActionPerformed

    private void updatePBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePBtnActionPerformed
        // TODO add your handling code here:
        updatePBtn.setVisible(false);
        savepPBtn.setVisible(true);
        browsePBtn.setVisible(true);

        fnamePField.setEditable(false);
        lnamePField.setEditable(false);
        phonePField.setEditable(true);
        birthField.setEditable(false);
        userPField.setEditable(true);
        passPField.setEditable(true);
        emailPField.setEditable(true);
        cityPField.setEditable(true);
        stPField.setEditable(true);
        buildPField.setEditable(true);

    }//GEN-LAST:event_updatePBtnActionPerformed

    private void browsePBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browsePBtnActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory ( new File ( System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","jpg","gif","png","bmp","jpeg");
        file.addChoosableFileFilter(filter); 
        int result = file.showSaveDialog(null);
        if ( result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            
            String path = selectedFile.getAbsolutePath();
            iPath=path;
            ImageIcon myImage  = new ImageIcon(path);
            Image img = myImage.getImage();
            Image newImg = img.getScaledInstance(jLabel55.getWidth(), jLabel55.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(newImg);
            jLabel55.setIcon(image);
        }
    }//GEN-LAST:event_browsePBtnActionPerformed

    private void savepPBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savepPBtnActionPerformed
        // TODO add your handling code here:
        
        String pNumber=phonePField.getText();
        String username=userPField.getText();
        String pass=passPField.getText();
        String email=emailPField.getText();
        String regex =  "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        String city=cityPField.getText();
        String build=buildPField.getText();
        String street=stPField.getText();
        if(pNumber.isEmpty()&&pNumber.startsWith(" "))
        {
            JOptionPane.showMessageDialog(null, "Enter Phone Number");
        }
        else if(username.isEmpty()&&username.startsWith(" "))
        {
            JOptionPane.showMessageDialog(null, "Enter Username");
        }
        else if(pass.isEmpty()&&pass.startsWith(" "))
        {
            JOptionPane.showMessageDialog(null, "Enter Username");
        }
        else if(matcher.matches())
        {
            try
            {
                if(!pass.equals(jLabel69.getText()))
                    pass=getMd5(pass);
                if(city.isEmpty()&&city.startsWith(" "))
                    city=null;
                if(build.isEmpty()&&build.startsWith(" "))
                    build=null;
                if(street.isEmpty()&&street.startsWith(" "))
                    street=null;
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="update PERSON1 set USERNAME='"+username+"',PASWORD='"+pass+"',EMAIL='"+email+"',CITY='"+city+"',BUILDING='"+build+"',"
                        + "STREET='"+street+"',PHONENUMBER="+Integer.parseInt(pNumber)+" where ID='"+jLabel68.getText()+"'";
                String sql2="update IMAGES set IMAGE_PATH='"+iPath+"' where IMAGE_ID='"+jLabel68.getText()+"'";
                PreparedStatement ps2=con.prepareStatement(sql2);
                ResultSet rs2=ps2.executeQuery();
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
            }
            catch(Exception e)
            {
                
                String dup = "ORA-00001: unique constraint (LIBRARY.USERNAMEUNIQUE) violated";
                if(e.getMessage().contains("USERNAMEUNIQUE"))
                JOptionPane.showMessageDialog(null, "Username used");
                if(e.getMessage().contains("EMAILUNIQUE"))
                JOptionPane.showMessageDialog(null, "Email used");
                if(e.getMessage().contains("PHONENUMBERUNIQUE"))
                JOptionPane.showMessageDialog(null, "Phone Number used");
            }
        }
        updatePBtn.setVisible(true);
        savepPBtn.setVisible(false);
        browsePBtn.setVisible(false);

        fnamePField.setEditable(false);
        lnamePField.setEditable(false);
        phonePField.setEditable(false);
        birthField.setEditable(false);
        userPField.setEditable(false);
        passPField.setEditable(false);
        emailPField.setEditable(false);
        cityPField.setEditable(false);
        stPField.setEditable(false);
        buildPField.setEditable(false);
    }//GEN-LAST:event_savepPBtnActionPerformed

    private void returnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBtnActionPerformed
        // TODO add your handling code here:
        Color clicked = new Color (49,86,89);
        Color ntClicked = new Color (2,17,27);
        dbBtn.setBackground(ntClicked);
        overdueBtn.setEnabled(true);

        returnBtn.setBackground(ntClicked);
        memberBtn.setBackground(ntClicked);
        addbBtn.setBackground(ntClicked);
        addempBtn.setBackground(ntClicked);
        searchBtn.setBackground(ntClicked);
        borrowBtn.setBackground(ntClicked);
        suggestBtn.setBackground(ntClicked);
        returnBtn.setBackground(clicked);
        profileBtn.setBackground(ntClicked);
        
        dbPanel.setVisible(false);
        searchPanel.setVisible(false);
        borrowPanel.setVisible(false);
        returnPanel.setVisible(true);
        memberPanel.setVisible(false);
        addBookPanel.setVisible(false);
        addEmpPanel.setVisible(false);
        suggestPanel.setVisible(false);
        profilePanel.setVisible(false);
        jLabel1.setText("Return");
    }//GEN-LAST:event_returnBtnActionPerformed

    private void memberBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberBtnActionPerformed
        // TODO add your handling code here:
         Color clicked = new Color (49,86,89);
        Color ntClicked = new Color (2,17,27);
        dbBtn.setBackground(ntClicked);
        

        returnBtn.setBackground(ntClicked);
        memberBtn.setBackground(clicked);
        addbBtn.setBackground(ntClicked);
        addempBtn.setBackground(ntClicked);
        searchBtn.setBackground(ntClicked);
        borrowBtn.setBackground(ntClicked);
        suggestBtn.setBackground(ntClicked);
        returnBtn.setBackground(ntClicked);
        profileBtn.setBackground(ntClicked);
        
        
        jLabel1.setText("Members");
        
        dbPanel.setVisible(false);
        searchPanel.setVisible(false);
        borrowPanel.setVisible(false);
        returnPanel.setVisible(false);
        memberPanel.setVisible(true);
        addBookPanel.setVisible(false);
        addEmpPanel.setVisible(false);
        suggestPanel.setVisible(false);
        profilePanel.setVisible(false);
    }//GEN-LAST:event_memberBtnActionPerformed

    private void addbBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbBtnActionPerformed
        // TODO add your handling code here:
         Color clicked = new Color (49,86,89);
        Color ntClicked = new Color (2,17,27);
        dbBtn.setBackground(ntClicked);
        

        returnBtn.setBackground(ntClicked);
        memberBtn.setBackground(ntClicked);
        addbBtn.setBackground(clicked);
        addempBtn.setBackground(ntClicked);
        searchBtn.setBackground(ntClicked);
        borrowBtn.setBackground(ntClicked);
        suggestBtn.setBackground(ntClicked);
        returnBtn.setBackground(ntClicked);
        profileBtn.setBackground(ntClicked);
        
  
        jLabel1.setText("Add a Book");
        
        dbPanel.setVisible(false);
        searchPanel.setVisible(false);
        borrowPanel.setVisible(false);
        returnPanel.setVisible(false);
        memberPanel.setVisible(false);
        addBookPanel.setVisible(true);
        addEmpPanel.setVisible(false);
        suggestPanel.setVisible(false);
        profilePanel.setVisible(false);
    }//GEN-LAST:event_addbBtnActionPerformed

    private void addempBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addempBtnActionPerformed
        // TODO add your handling code here:
        
         Color clicked = new Color (49,86,89);
        Color ntClicked = new Color (2,17,27);
        dbBtn.setBackground(ntClicked);
        

        returnBtn.setBackground(ntClicked);
        memberBtn.setBackground(ntClicked);
        addbBtn.setBackground(ntClicked);
        addempBtn.setBackground(clicked);
        searchBtn.setBackground(ntClicked);
        borrowBtn.setBackground(ntClicked);
        suggestBtn.setBackground(ntClicked);
        returnBtn.setBackground(ntClicked);
        profileBtn.setBackground(ntClicked);
        
      
        jLabel1.setText("Add Employee");
        
        dbPanel.setVisible(false);
        searchPanel.setVisible(false);
        borrowPanel.setVisible(false);
        returnPanel.setVisible(false);
        memberPanel.setVisible(false);
        addBookPanel.setVisible(false);
        addEmpPanel.setVisible(true);
        suggestPanel.setVisible(false);
        profilePanel.setVisible(false);
    }//GEN-LAST:event_addempBtnActionPerformed

    private void borrowBBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowBBtnActionPerformed
        // TODO add your handling code here:
       String mID=jLabel17.getText();
       int nBr=Integer.parseInt(jLabel19.getText())+1;
       int nCpy=Integer.parseInt(jLabel62.getText())-1;
       int flag=Integer.parseInt(jLabel63.getText());
       Calendar cal1=Calendar.getInstance();
       Calendar cal2=Calendar.getInstance();
       cal2.add(Calendar.MONTH,1);
       String av="Available";
       if(nCpy==0)
           av="Not Available";

       String bDate=DateConv(cal1);
       String bDate2=DateConv(cal2);
       try
       {
           Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            String sql="";
            if(flag==1)
            {
                sql="update MEMBER set NUM_BOOKS_BORROWED="+nBr+",I_S_B_N="+BigInteger.valueOf(Long.parseLong(jTextField5.getText()))+",BORROW_DATE='"+bDate+"',RETURN_DATE='"+bDate2+"' where MEMBERID='"+mID+"'";
                
            }
            else if(flag==2)
            {
                sql="update MEMBER set NUM_BOOKS_BORROWED="+nBr+",ISBN2="+BigInteger.valueOf(Long.parseLong(jTextField5.getText()))+",BORROW_DATE2='"+bDate+"',RDATE2='"+bDate2+"' where MEMBERID='"+mID+"'";
            }
             else if(flag==3)
            {
                sql="update MEMBER set NUM_BOOKS_BORROWED="+nBr+",IS_BN3="+BigInteger.valueOf(Long.parseLong(jTextField5.getText()))+",BDATE3='"+bDate+"',RETDATE3='"+bDate2+"' where MEMBERID='"+mID+"'";
            }
            String sql2="update BOOKS set NUM_TIME_B=NUM_TIME_B+1,NUMCOPIES="+nCpy+",Status='"+av+"' where ISBN="+BigInteger.valueOf(Long.parseLong(jTextField5.getText()));
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            PreparedStatement ps2=con.prepareStatement(sql2);
            ResultSet rs2=ps2.executeQuery();
            con.commit();
            con.close();
       }
       catch(Exception e)
       {
           
       }
       
       
       borrowBBtn.setEnabled(false);
       checkBBtn.setEnabled(true);
    }//GEN-LAST:event_borrowBBtnActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        LogIn lg = new LogIn();
        lg.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void checkBBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBBtnActionPerformed
        // TODO add your handling code here:
       String av = "Available";
        String ntav = "Not Available";
        //checkLabel.setText("Available");
        String bTitle=jTextField4.getText();
        BigDecimal bISBN=BigDecimal.valueOf(Long.parseLong(jTextField5.getText()));
        int nCopies=0;
        String status="";
        String memType="";
        int numBorrowed=0;
        String path="";
        try
        {
            
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            String sql="select * from PERSON1,MEMBER where USERNAME='"+jTextField6.getText()+"'";
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                jLabel17.setText(rs.getString("ID"));
            }
           
            Connection con2=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            String sql2="select * from BOOKS,MEMBER where MEMBERID='"+jLabel17.getText()+"' and ISBN="+bISBN;
            PreparedStatement ps2=con2.prepareStatement(sql2);
            ResultSet rs2=ps2.executeQuery();
            if(rs2.next())
            {
                numBorrowed=rs2.getInt("NUM_BOOKS_BORROWED");
                
                jLabel19.setText(numBorrowed+"");
                memType=rs2.getString("MEM_TYPE");
                
                status=rs2.getString("STATUS");
                checkLabel.setText(status);
                nCopies=rs2.getInt("NUMCOPIES");
                jLabel62.setText(nCopies+"");
                System.out.print("faa");
                path=rs2.getString("BOOK_PIC");
                String isbn1=rs2.getString("I_S_B_N");
                String isbn2=rs2.getString("ISBN2");
                String isbn3=rs2.getString("IS_BN3");
                if(checkNull(isbn1))
                    jLabel63.setText("1");
                else if(checkNull(isbn2))
                    jLabel63.setText("2");
                else if(checkNull(isbn3))
                    jLabel63.setText("3");

                
            }
             con.commit();
            con.close();
        }
        catch(Exception E)
        {
                  JOptionPane.showMessageDialog(null, E.getMessage());
        }
        ImageIcon myImage  = new ImageIcon(path);
        Image img = myImage.getImage();
        Image newImg = img.getScaledInstance(jLabel16.getWidth(), jLabel16.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        jLabel16.setIcon(image);
        
        if(checkLabel.getText().equals(av)&&nCopies>0&&numBorrowed!=3&&memType.equals("Regular")){
        borrowBBtn.setEnabled(true);
        checkBBtn.setEnabled(false);
        }
        if (checkLabel.getText().equals(ntav)){
             borrowBBtn.setEnabled(false);
         }
        
        
         
    }//GEN-LAST:event_checkBBtnActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String mID=jLabel64.getText();
        int nBorrowed=Integer.parseInt(jLabel65.getText())-1;
        int nCopies=Integer.parseInt(jLabel66.getText())+1;
        int flag=Integer.parseInt(jLabel67.getText());
        try
        {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            String sql="";
            if(flag==1)
            {
               sql="update MEMBER SET NUM_BOOKS_BORROWED="+nBorrowed+",I_S_B_N=null,BORROW_DATE=null,RETURN_DATE=null where MEMBERID='"+mID+"'";
            }
            if(flag==2)
            {
               sql="update MEMBER SET NUM_BOOKS_BORROWED="+nBorrowed+",ISBN2=null,BORROW_DATE2=null,RDATE2=null where MEMBERID='"+mID+"'";
            }
            if(flag==3)
            {
               sql="update MEMBER SET NUM_BOOKS_BORROWED="+nBorrowed+",IS_BN3=null,BDATE3=null,RETDATE3=null where MEMBERID='"+mID+"'";
            }
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            String sql2="update BOOKS set NUMCOPIES="+nCopies+",STATUS='Available' where ISBN="+BigDecimal.valueOf(Long.parseLong(jTextField8.getText()));
            PreparedStatement ps2=con.prepareStatement(sql2);
            ResultSet rs2=ps2.executeQuery();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
        overdueBtn.setEnabled(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void overdueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overdueBtnActionPerformed
        // TODO add your handling code here:
        String bTitle=jTextField9.getText();
        BigDecimal bISBN=BigDecimal.valueOf(Long.parseLong(jTextField8.getText()));
        long bISBN2=Long.parseLong(jTextField8.getText());
        String mUN=jTextField7.getText();
        int flag=0;
        try
        {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            String sql="select * from PERSON1,MEMBER where USERNAME='"+mUN+"'";
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                jLabel64.setText(rs.getString("ID"));
            }
            String sql2="select * from BOOKS,MEMBER where MEMBERID='"+jLabel64.getText()+"'and ISBN="+bISBN+" and (I_S_B_N="+bISBN+" OR ISBN2="+bISBN+" OR IS_BN3="+bISBN+")";
            PreparedStatement ps2=con.prepareStatement(sql2);
            ResultSet rs2=ps2.executeQuery();
            if(rs2.next())
            {
                int nBor=rs2.getInt("NUM_BOOKS_BORROWED");
                jLabel65.setText(nBor+"");
                int nCopies=rs2.getInt("NUMCOPIES");
                jLabel66.setText(nCopies+"");
                String path=rs2.getString("BOOK_PIC");
                ImageIcon myImage  = new ImageIcon(path);
                Image img = myImage.getImage();
                Image newImg = img.getScaledInstance(jLabel18.getWidth(), jLabel18.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon image = new ImageIcon(newImg);
                jLabel18.setIcon(image);
                long isbn1=rs2.getLong("I_S_B_N");
                long isbn2=rs2.getLong("ISBN2");
                long isbn3=rs2.getLong("IS_BN3");
                Date retd=null;
                if(isbn1==bISBN2)
                {
                    flag=1;
                    retd=rs2.getDate("RETURN_DATE");
                }
                else if(isbn2==bISBN2)
                {
                    flag=2;
                    retd=rs2.getDate("RDATE2");
                }
                else if(isbn3==bISBN2)
                {
                    flag=3;
                    retd=rs2.getDate("RETDATE3");
                }
                jLabel67.setText(flag+"");
                Calendar cal=Calendar.getInstance();
                Date today =cal.getTime();
                if(today.compareTo(retd)<=0)
                {
                    
                    overdueLabel.setText("No Overdue");
                    jTextField10.setText("0");
                }
                if(today.compareTo(retd)>0)
                {
                    overdueLabel.setText("Overdue");
                    long diff=today.getTime()-retd.getTime();
                    int penalty=(int)diff/30*5;
                    jTextField10.setText(penalty+"");
                    
                    
                }
                
                
            }
            con.commit();
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            
        }
        
        
    }//GEN-LAST:event_overdueBtnActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here
        String bTitle=bookField.getText();
        String bGenre=jTextField11.getText();
        String afn=jTextField15.getText();
        String asn=jTextField19.getText();
        String bEdition=jTextField12.getText();
        String pubYear =jTextField14.getText();
        String bISBN=jTextField17.getText();
        String row=jTextField3.getText();
        String nCopies=jTextField20.getText();
        String section=jTextField20.getText();
        String Descrip=jTextArea1.getText();
        try
        {
            if(bTitle.isEmpty()&&bTitle.startsWith(" "))
            {
                JOptionPane.showMessageDialog(null, "Enter Title");
            }
            else if(bGenre.isEmpty()&&bGenre.startsWith(" "))
            {
                JOptionPane.showMessageDialog(null, "Enter Genre");
            }
            else if(afn.isEmpty()&&afn.startsWith(" "))
            {
                JOptionPane.showMessageDialog(null, "Enter Author First name");
            }
            else if(asn.isEmpty()&&asn.startsWith(" "))
            {
                JOptionPane.showMessageDialog(null, "Enter Author Last name");
            }
            else if(bISBN.isEmpty()&&bISBN.startsWith(" "))
            {
                JOptionPane.showMessageDialog(null, "Enter ISBN");
            }
            
            else
            {
                if(jTextField12.getText().isEmpty()&&jTextField12.getText().startsWith(" "))
                {
                    bEdition=null;
                }
                if(pubYear.isEmpty()&&pubYear.startsWith(" "))
                {
                    pubYear=null;
                }
                if(row.isEmpty()&&row.startsWith(" "))
                {
                    row=null;
                }
                if(nCopies.isEmpty()&&nCopies.startsWith(" "))
                {
                    nCopies="1";
                }
                if(section.isEmpty()&&section.startsWith(" "))
                {
                    section=null;
                }
                if(Descrip.isEmpty()&&Descrip.startsWith(" "))
                {
                    Descrip=null;
                }
                if(iPath.isEmpty()&&iPath.startsWith(" "))
                {
                    iPath=null;
                }
               
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="insert into BOOKS values ('"+bTitle+"','"+bGenre+"',"+Integer.parseInt(section)+",'"+Descrip+"',"+Integer.parseInt(row)+","+pubYear+""
                        + ","+BigDecimal.valueOf(Long.parseLong(bISBN))+",0,'"+iPath+"','Available',"+Integer.parseInt(nCopies)+")";
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                String sql2="insert into AUTHOR values('"+afn+"','"+asn+"',"+BigDecimal.valueOf(Long.parseLong(bISBN))+")";
                 PreparedStatement ps2=con.prepareStatement(sql2);
                ResultSet rs2=ps2.executeQuery();
            }
        }
        catch(Exception e)
        {
            
        }
        
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory ( new File ( System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","jpg","gif","png","bmp","jpeg");
        file.addChoosableFileFilter(filter); 
        int result = file.showSaveDialog(null);
        if ( result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            
            String path = selectedFile.getAbsolutePath();
            iPath=path;
            ImageIcon myImage  = new ImageIcon(path);
            Image img = myImage.getImage();
            Image newImg = img.getScaledInstance(jLabel45.getWidth(), jLabel45.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(newImg);
            jLabel45.setIcon(image);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jRadioButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton10ActionPerformed

    private void addBookPanelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addBookPanelKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c==KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_addBookPanelKeyPressed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here
        grid1.removeAll();
        char[]c=jTextField1.getText().toCharArray();
        if(jRadioButton5.isSelected())
        {
            try
            {
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="select * from PERSON1,MEMBER,IMAGES where (F_NAME like '%"+jTextField2.getText()+"%' OR L_NAME like'%"+jTextField2.getText()+"%')and ID=MEMBERID AND ID=IMAGE_ID";
               
                String sql2="select count(*) from PERSON1,MEMBER,IMAGES where (F_NAME like '%"+jTextField2.getText()+"%' OR L_NAME like'%"+jTextField2.getText()+"%')and ID=MEMBERID AND ID=IMAGE_ID";
                PreparedStatement ps2=con.prepareStatement(sql2);
                ResultSet rs2=ps2.executeQuery();
                int Count=0;
                if(rs2.next())
                {
                    Count=rs2.getInt("Count(*)");
                }
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                for(int i=0;rs.next()||i<Count;i++)
                {
                    JLabel l1=new JLabel();
                    l1.setText(rs.getString("F_NAME")+" "+rs.getString("L_NAME"));
                    l1.setSize(160,230);
                    String path=rs.getString("IMAGE_PATH");
                    String ID=rs.getString("ID");
                    ImageIcon myImage  = new ImageIcon(path);
                    Image img = myImage.getImage();
                    Image newImg = img.getScaledInstance(l1.getWidth(), l1.getHeight()-20, Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(newImg);
                    l1.setIcon(image);
                    l1.setHorizontalTextPosition(SwingConstants.CENTER);
                    l1.setVerticalTextPosition(SwingConstants.BOTTOM);
                    l1.addMouseListener(new MouseAdapter()
                    {
                        public void mouseClicked(MouseEvent event)
                        {
                           profileJFrame pfj=new profileJFrame(ID);
                           pfj.setVisible(true);
                        }
                    });
                     grid1.add(l1);
                }
                   
                    this.repaint();
                    grid1.repaint();
                   this.setVisible(false);
                    this.setVisible(true);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        else if(jRadioButton7.isSelected())
        {
            try
            {
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="select * from PERSON1,MEMBER,IMAGES where EMAIL='"+jTextField2.getText()+"' and MEMBERID=ID and IMAGE_ID=ID ";
               
                String sql2="select count(*) from PERSON1,MEMBER,IMAGES where EMAIL='"+jTextField2.getText()+"' and MEMBERID=ID and IMAGE_ID=ID ";
                PreparedStatement ps2=con.prepareStatement(sql2);
                ResultSet rs2=ps2.executeQuery();
                int Count=0;
                if(rs2.next())
                {
                    Count=rs2.getInt("Count(*)");
                }
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                for(int i=0;rs.next()||i<Count;i++)
                {
                    JLabel l1=new JLabel();
                    l1.setText(rs.getString("F_NAME")+" "+rs.getString("L_NAME"));
                    l1.setSize(160,230);
                    String path=rs.getString("IMAGE_PATH");
                    String ID=rs.getString("ID");
                    ImageIcon myImage  = new ImageIcon(path);
                    Image img = myImage.getImage();
                    Image newImg = img.getScaledInstance(l1.getWidth(), l1.getHeight()-20, Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(newImg);
                    l1.setIcon(image);
                    l1.setHorizontalTextPosition(SwingConstants.CENTER);
                    l1.setVerticalTextPosition(SwingConstants.BOTTOM);
                    l1.addMouseListener(new MouseAdapter()
                    {
                        public void mouseClicked(MouseEvent event)
                        {
                           profileJFrame pfj=new profileJFrame(ID);
                           pfj.setVisible(true);
                        }
                    });
                     grid1.add(l1);
                }
                   
                    this.repaint();
                    grid1.repaint();
                   this.setVisible(false);
                    this.setVisible(true);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        else if(jRadioButton8.isSelected())
        {
            try
            {
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="select * from PERSON1,MEMBER,IMAGES where USERNAME='"+jTextField2.getText()+"' and MEMBERID=ID and IMAGE_ID=ID ";
               
                String sql2="select count(*) from PERSON1,MEMBER,IMAGES where USERNAME='"+jTextField2.getText()+"' and MEMBERID=ID and IMAGE_ID=ID ";
                PreparedStatement ps2=con.prepareStatement(sql2);
                ResultSet rs2=ps2.executeQuery();
                int Count=0;
                if(rs2.next())
                {
                    Count=rs2.getInt("Count(*)");
                }
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                for(int i=0;rs.next()||i<Count;i++)
                {
                    JLabel l1=new JLabel();
                    l1.setText(rs.getString("F_NAME")+" "+rs.getString("L_NAME"));
                    l1.setSize(160,230);
                    String path=rs.getString("IMAGE_PATH");
                    String ID=rs.getString("ID");
                    ImageIcon myImage  = new ImageIcon(path);
                    Image img = myImage.getImage();
                    Image newImg = img.getScaledInstance(l1.getWidth(), l1.getHeight()-20, Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(newImg);
                    l1.setIcon(image);
                    l1.setHorizontalTextPosition(SwingConstants.CENTER);
                    l1.setVerticalTextPosition(SwingConstants.BOTTOM);
                    l1.addMouseListener(new MouseAdapter()
                    {
                        public void mouseClicked(MouseEvent event)
                        {
                           profileJFrame pfj=new profileJFrame(ID);
                           pfj.setVisible(true);
                        }
                    });
                     grid1.add(l1);
                }
                   
                    this.repaint();
                    grid1.repaint();
                   this.setVisible(false);
                    this.setVisible(true);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        else if(jRadioButton9.isSelected())
        {
            try
            {
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="select * from PERSON1,MEMBER,IMAGES where PHONENUMBER="+Long.parseLong(jTextField2.getText())+" and MEMBERID=ID and IMAGE_ID=ID ";
               
                String sql2="select count(*) from PERSON1,MEMBER,IMAGES where PHONENUMBER="+Long.parseLong(jTextField2.getText())+" and MEMBERID=ID and IMAGE_ID=ID ";
                PreparedStatement ps2=con.prepareStatement(sql2);
                ResultSet rs2=ps2.executeQuery();
                int Count=0;
                if(rs2.next())
                {
                    Count=rs2.getInt("Count(*)");
                }
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                for(int i=0;rs.next()||i<Count;i++)
                {
                    JLabel l1=new JLabel();
                    l1.setText(rs.getString("F_NAME")+" "+rs.getString("L_NAME"));
                    l1.setSize(160,230);
                    String path=rs.getString("IMAGE_PATH");
                    String ID=rs.getString("ID");
                    ImageIcon myImage  = new ImageIcon(path);
                    Image img = myImage.getImage();
                    Image newImg = img.getScaledInstance(l1.getWidth(), l1.getHeight()-20, Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(newImg);
                    l1.setIcon(image);
                    l1.setHorizontalTextPosition(SwingConstants.CENTER);
                    l1.setVerticalTextPosition(SwingConstants.BOTTOM);
                    l1.addMouseListener(new MouseAdapter()
                    {
                        public void mouseClicked(MouseEvent event)
                        {
                           profileJFrame pfj=new profileJFrame(ID);
                           pfj.setVisible(true);
                        }
                    });
                     grid1.add(l1);
                }
                   
                    this.repaint();
                    grid1.repaint();
                   this.setVisible(false);
                    this.setVisible(true);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
                Connection con = null;
        InputStream input = null;
        JasperDesign jasperDesign = null;
        JasperReport jasperReport = null;
        JasperPrint print = null;
        OutputStream output = null;
        OracleDataSource ods = null;
        try {
            ods=new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@localhost:1521:oracle");
        ods.setUser("library");
        ods.setPassword("123456");
            con=ods.getConnection();

            input=new FileInputStream(new File("C:\\Users\\Waleed\\Documents\\NetBeansProjects\\Library\\src\\report\\report1.jrxml"));
        
            jasperDesign=JRXmlLoader.load(input);
        
            jasperReport=JasperCompileManager.compileReport(jasperDesign);
        
            print=JasperFillManager.fillReport(jasperReport,null,con);
           
        
            output=new FileOutputStream(new File("Book.pdf"));
      
            JasperExportManager.exportReportToPdfStream(print, output);
       
            output.close();
      
            input.close();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
        JFrame frame=new JFrame("Book Report");
        frame.getContentPane().add(new JRViewer(print));
        frame.pack();
         frame.setLocationRelativeTo(this);
        frame.setSize(800,1000);
        frame.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        // TODO add your handling code here:
          char c = evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c==KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c==KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c==KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_jTextField12KeyTyped

    private void jTextField17KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField17KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c==KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_jTextField17KeyTyped

    private void jTextField14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c==KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_jTextField14KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c==KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField20KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField20KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c==KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_jTextField20KeyTyped

    private void phoneFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneFieldKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c==KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_phoneFieldKeyTyped

    private void shourFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shourFieldKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c==KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_shourFieldKeyTyped

    private void ehourFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ehourFieldKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c==KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_ehourFieldKeyTyped

    private void phonePFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phonePFieldKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c==KeyEvent.VK_DELETE))
            evt.consume();
    }//GEN-LAST:event_phonePFieldKeyTyped

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
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GenderGroup;
    private javax.swing.JPanel addBookPanel;
    private javax.swing.JPanel addEmpPanel;
    private javax.swing.JButton addbBtn;
    private javax.swing.JButton addempBtn;
    private javax.swing.JTextField birthField;
    private javax.swing.JTextField bookField;
    private javax.swing.JButton borrowBBtn;
    private javax.swing.JButton borrowBtn;
    private javax.swing.JPanel borrowPanel;
    private javax.swing.JButton browseBtn;
    private javax.swing.JButton browsePBtn;
    private javax.swing.JTextField buildField;
    private javax.swing.JTextField buildPField;
    private javax.swing.JLabel buildingLabel;
    private javax.swing.JLabel buildingLabel1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JButton checkBBtn;
    private javax.swing.JLabel checkLabel;
    private javax.swing.JTextField cityField;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JLabel cityLabel1;
    private javax.swing.JTextField cityPField;
    private javax.swing.JButton dbBtn;
    private javax.swing.JPanel dbPanel;
    private javax.swing.JTextField ehourField;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emailLabel1;
    private javax.swing.JLabel emailLabel2;
    private javax.swing.JTextField emailPField;
    private javax.swing.JTextField fnameField;
    private javax.swing.JTextField fnamePField;
    private javax.swing.JPanel grid;
    private javax.swing.JPanel grid1;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField lnameField;
    private javax.swing.JTextField lnamePField;
    private javax.swing.JButton memberBtn;
    private javax.swing.ButtonGroup memberGrp;
    private javax.swing.JPanel memberPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton overdueBtn;
    private javax.swing.JLabel overdueLabel;
    private javax.swing.JPanel panel;
    private javax.swing.JPasswordField passField;
    private javax.swing.JLabel passLabel;
    private javax.swing.JLabel passLabel1;
    private javax.swing.JPasswordField passPField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JLabel phoneLabel1;
    private javax.swing.JTextField phonePField;
    private javax.swing.JButton profileBtn;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JButton returnBtn;
    private javax.swing.JPanel returnPanel;
    private javax.swing.JButton savepBtn;
    private javax.swing.JButton savepPBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTextField shourField;
    private javax.swing.JTextField stField;
    private javax.swing.JTextField stPField;
    private javax.swing.JLabel streetLabel;
    private javax.swing.JLabel streetLabel1;
    private javax.swing.JButton suggestBtn;
    private javax.swing.JPanel suggestPanel;
    private javax.swing.JButton updatePBtn;
    private javax.swing.JTextField userField;
    private javax.swing.JLabel userLabel;
    private javax.swing.JLabel userLabel1;
    private javax.swing.JLabel userLabel2;
    private javax.swing.JTextField userPField;
    // End of variables declaration//GEN-END:variables
}
