/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import static library.LogIn.getMd5;

/**
 *
 * @author LENOVO
 */
public class MemeberGUI extends javax.swing.JFrame {
    boolean checkNull(String s)
    {
        if(!(s!=null&&!s.isEmpty()))
            return true;
        else
            return false;
    }
    String iPath="";

    /**
     * Creates new form MemeberGUI
     */
    public MemeberGUI() {
        initComponents();
    }
       public MemeberGUI(Member1 m) {
        initComponents();
        jLabel7.setText(m.getMemberid());
        jLabel8.setText(m.getMemSd().toString());
        jLabel46.setText(m.getMemberid());
        jLabel9.setText(m.getMemEd().toString());
        nameLabel.setText(m.getPerson1().getFName()+" "+m.getPerson1().getLName());
        memberLabel.setText(m.getMemType());
        String path2="";
        fnameField.setText(m.getPerson1().getFName());
        lnameField.setText(m.getPerson1().getLName());
        phoneField.setText(m.getPerson1().getPhonenumber().toString());
        birthField.setText(m.getPerson1().getBirthday().toString());
        userField.setText(m.getPerson1().getUsername());
        passField.setText(m.getPerson1().getPasword());
        cityField.setText(m.getPerson1().getCity());
        stField.setText(m.getPerson1().getStreet());
        buildField.setText(m.getPerson1().getBuilding());
        jRadioButton7.setEnabled(false);
        jRadioButton8.setEnabled(false);
        jLabel59.setText(m.getMemberid());
        jLabel60.setText(m.getPerson1().getPasword());
        jRadioButton9.setEnabled(false);
        jRadioButton10.setEnabled(false);
        jRadioButton11.setEnabled(false);
        jRadioButton12.setEnabled(false);
        jLabel55.setText(m.getMemberid());
        jRadioButton13.setEnabled(false);
        jRadioButton14.setEnabled(false);
        jRadioButton15.setEnabled(false);
        jRadioButton16.setEnabled(false);
        jRadioButton17.setEnabled(false);
        jRadioButton18.setEnabled(false);
        jRadioButton19.setEnabled(false);
        jRadioButton20.setEnabled(false);
        jRadioButton21.setEnabled(false);
        try
        {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="select * from notes where NOTEID='"+m.getMemberid()+"' order by NOTENUM DESC";
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                int f=47;
                
                    if(rs.next())
                    {
                       
                        String title=rs.getString("NOTETITLE");
                        jLabel47.setText(title);
                    
                    if(rs.next())
                    {
                         title=rs.getString("NOTETITLE");
                        jLabel48.setText(title);
                    
                    if(rs.next())
                    {
                         title=rs.getString("NOTETITLE");
                        jLabel49.setText(title);
                    }
                    if(rs.next())
                    {
                         title=rs.getString("NOTETITLE");
                        jLabel50.setText(title);
                    }
                    }
                    }
                    
                    
                
        }
        catch(Exception e)
        {
            
        }
         try
        {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            String sql="select * from BOOKS,MEMBER where MEMBERID='"+m.getMemberid()+"'and (I_S_B_N=ISBN OR ISBN2=ISBN OR IS_BN3=ISBN) ";
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            String isbn1="";
            String isbn2="";
            String isbn3="";
            if(rs.next())
            {
                 isbn1=rs.getString("I_S_B_N");
                 isbn2=rs.getString("ISBN2");
                 isbn3=rs.getString("IS_BN3");
            }
            String sql2="select * from BOOKS where ISBN="+Long.parseLong(isbn1)+"or ISBN="+Long.parseLong(isbn2)+"or ISBN="+Long.parseLong(isbn3);
            PreparedStatement ps2=con.prepareStatement(sql2);
            ResultSet rs2=ps2.executeQuery();
            if(rs2.next())
            {
                jLabel51.setText(rs2.getString("TITLE"));
                jLabel54.setText(rs2.getString("ISBN"));
                if(rs2.next())
                {
                    jLabel52.setText(rs2.getString("TITLE"));
                    jLabel56.setText(rs2.getString("ISBN"));

                    if(rs2.next())
                    {
                        jLabel53.setText(rs2.getString("TITLE"));
                        jLabel57.setText(rs2.getString("ISBN"));

                    }
                }
            }
            con.commit();
            con.close();
        }
        catch(Exception e)
        {
            
        }
        try
        {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            String sql="select * from IMAGES where IMAGE_ID='"+m.getMemberid()+"'";
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
                path2=rs.getString("IMAGE_PATH");
            con.commit();
            con.close();
        }
        catch(Exception e)
        {
            
        }
        ImageIcon myImage2  = new ImageIcon(path2);
        Image img2 = myImage2.getImage();
        Image newImg2 = img2.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image2 = new ImageIcon(newImg2);
        imageLabel.setIcon(image2);
        
        int bb=m.getNumBooksBorrowed().intValue();
        int flag=0;
        
        try
        {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            String sql="select * from MEMBER where MEMBERID='"+m.getMemberid()+"'";
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                String isbn1=rs.getString("I_S_B_N");
                String isbn2=rs.getString("ISBN2");
                String isbn3=rs.getString("IS_BN3");
                if(bb==1)
                {
                   if(!checkNull(isbn1))
                       flag=1;
                   else if(!checkNull(isbn2))
                       flag=2;
                   else if(!checkNull(isbn3))
                       flag=3;
                   if(flag==1)
                   {
                        String sql2="select * from BOOKS,AUTHOR WHERE BOOK_ISBN="+BigDecimal.valueOf(Long.parseLong(isbn1))+" and ISBN="+BigDecimal.valueOf(Long.parseLong(isbn1));
                        PreparedStatement ps2=con.prepareStatement(sql2);
                        ResultSet rs2=ps2.executeQuery();
                        if(rs2.next())
                        {
                            jLabel37.setText(rs2.getString("TITLE"));
                            jLabel41.setText(rs2.getString("F_NAME")+" "+rs2.getString("SUR_NAME"));
                            jLabel42.setText(rs2.getString("ISBN"));
                            jLabel43.setText(m.getBorrowDate().toString());
                            jLabel45.setText(m.getReturnDate().toString());
                            String path=rs2.getString("BOOK_PIC");
                            ImageIcon myImage  = new ImageIcon(path);
                            Image img = myImage.getImage();
                            Image newImg = img.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon image = new ImageIcon(newImg);
                            jLabel11.setIcon(image);
                        }
                   }
                   else if(flag==2)
                   {
                       String sql2="select * from BOOKS,AUTHOR WHERE BOOK_ISBN="+BigDecimal.valueOf(Long.parseLong(isbn2))+" and ISBN="+BigDecimal.valueOf(Long.parseLong(isbn2));
                        PreparedStatement ps2=con.prepareStatement(sql2);
                        ResultSet rs2=ps2.executeQuery();
                        if(rs2.next())
                        {
                            jLabel37.setText(rs2.getString("TITLE"));
                            jLabel41.setText(rs2.getString("F_NAME")+" "+rs2.getString("SUR_NAME"));
                            jLabel42.setText(rs2.getString("ISBN"));
                            jLabel43.setText(m.getBorrowDate2().toString());
                            jLabel45.setText(m.getRdate2().toString());
                            String path=rs2.getString("BOOK_PIC");
                            ImageIcon myImage  = new ImageIcon(path);
                            Image img = myImage.getImage();
                            Image newImg = img.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon image = new ImageIcon(newImg);
                            jLabel11.setIcon(image);
                        }
                   }
                   else if(flag==3)
                   {
                       String sql2="select * from BOOKS,AUTHOR WHERE BOOK_ISBN="+BigDecimal.valueOf(Long.parseLong(isbn3))+" and ISBN="+BigDecimal.valueOf(Long.parseLong(isbn3));
                        PreparedStatement ps2=con.prepareStatement(sql2);
                        ResultSet rs2=ps2.executeQuery();
                        if(rs2.next())
                        {
                            jLabel37.setText(rs2.getString("TITLE"));
                            jLabel41.setText(rs2.getString("F_NAME")+" "+rs2.getString("SUR_NAME"));
                            jLabel42.setText(rs2.getString("ISBN"));
                            jLabel43.setText(m.getBdate3().toString());
                            jLabel45.setText(m.getRetdate3().toString());
                            String path=rs2.getString("BOOK_PIC");
                            ImageIcon myImage  = new ImageIcon(path);
                            Image img = myImage.getImage();
                            Image newImg = img.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon image = new ImageIcon(newImg);
                            jLabel11.setIcon(image);
                        }
                   }
                   
                }
                else if(bb==2)
                {
                    if(checkNull(isbn1))
                       flag=1;
                   else if(checkNull(isbn2))
                       flag=2;
                   else if(checkNull(isbn3))
                       flag=3;
                    if(flag==1)
                    {
                        if(m.getBorrowDate2().compareTo(m.getBdate3())<0)
                        {
                            String sql2="select * from BOOKS,AUTHOR WHERE BOOK_ISBN="+BigDecimal.valueOf(Long.parseLong(isbn3))+" and ISBN="+BigDecimal.valueOf(Long.parseLong(isbn3));
                            PreparedStatement ps2=con.prepareStatement(sql2);
                            ResultSet rs2=ps2.executeQuery();
                            if(rs2.next())
                            {
                                jLabel37.setText(rs2.getString("TITLE"));
                                jLabel41.setText(rs2.getString("F_NAME")+" "+rs2.getString("SUR_NAME"));
                                jLabel42.setText(rs2.getString("ISBN"));
                                jLabel43.setText(m.getBdate3().toString());
                                jLabel45.setText(m.getRetdate3().toString());
                                String path=rs2.getString("BOOK_PIC");
                                ImageIcon myImage  = new ImageIcon(path);
                                Image img = myImage.getImage();
                                Image newImg = img.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon image = new ImageIcon(newImg);
                                jLabel11.setIcon(image);
                            }
                        }
                        else
                        {
                            String sql2="select * from BOOKS,AUTHOR WHERE BOOK_ISBN="+BigDecimal.valueOf(Long.parseLong(isbn2))+" and ISBN="+BigDecimal.valueOf(Long.parseLong(isbn2));
                            PreparedStatement ps2=con.prepareStatement(sql2);
                            ResultSet rs2=ps2.executeQuery();
                            if(rs2.next())
                            {
                                jLabel37.setText(rs2.getString("TITLE"));
                                jLabel41.setText(rs2.getString("F_NAME")+" "+rs2.getString("SUR_NAME"));
                                jLabel42.setText(rs2.getString("ISBN"));
                                jLabel43.setText(m.getBorrowDate2().toString());
                                jLabel45.setText(m.getRdate2().toString());
                                String path=rs2.getString("BOOK_PIC");
                                ImageIcon myImage  = new ImageIcon(path);
                                Image img = myImage.getImage();
                                Image newImg = img.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon image = new ImageIcon(newImg);
                                jLabel11.setIcon(image);
                            }
                        }
                    }
                    else if(flag==2)
                    {
                        if(m.getBorrowDate().compareTo(m.getBdate3())<0)
                        {
                            String sql2="select * from BOOKS,AUTHOR WHERE BOOK_ISBN="+BigDecimal.valueOf(Long.parseLong(isbn3))+" and ISBN="+BigDecimal.valueOf(Long.parseLong(isbn3));
                            PreparedStatement ps2=con.prepareStatement(sql2);
                            ResultSet rs2=ps2.executeQuery();
                            if(rs2.next())
                            {
                                jLabel37.setText(rs2.getString("TITLE"));
                                jLabel41.setText(rs2.getString("F_NAME")+" "+rs2.getString("SUR_NAME"));
                                jLabel42.setText(rs2.getString("ISBN"));
                                jLabel43.setText(m.getBdate3().toString());
                                jLabel45.setText(m.getRetdate3().toString());
                                String path=rs2.getString("BOOK_PIC");
                                ImageIcon myImage  = new ImageIcon(path);
                                Image img = myImage.getImage();
                                Image newImg = img.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon image = new ImageIcon(newImg);
                                jLabel11.setIcon(image);
                            }
                        }
                        else
                        {
                            String sql2="select * from BOOKS,AUTHOR WHERE BOOK_ISBN="+BigDecimal.valueOf(Long.parseLong(isbn1))+" and ISBN="+BigDecimal.valueOf(Long.parseLong(isbn1));
                            PreparedStatement ps2=con.prepareStatement(sql2);
                            ResultSet rs2=ps2.executeQuery();
                            if(rs2.next())
                            {
                                jLabel37.setText(rs2.getString("TITLE"));
                                jLabel41.setText(rs2.getString("F_NAME")+" "+rs2.getString("SUR_NAME"));
                                jLabel42.setText(rs2.getString("ISBN"));
                                jLabel43.setText(m.getBorrowDate().toString());
                                jLabel45.setText(m.getReturnDate().toString());
                                String path=rs2.getString("BOOK_PIC");
                                ImageIcon myImage  = new ImageIcon(path);
                                Image img = myImage.getImage();
                                Image newImg = img.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon image = new ImageIcon(newImg);
                                jLabel11.setIcon(image);
                            }
                        }
                    }
                    else if(flag==3)
                    {
                        if(m.getBorrowDate().compareTo(m.getBorrowDate2())<0)
                        {
                            String sql2="select * from BOOKS,AUTHOR WHERE BOOK_ISBN="+BigDecimal.valueOf(Long.parseLong(isbn2))+" and ISBN="+BigDecimal.valueOf(Long.parseLong(isbn2));
                            PreparedStatement ps2=con.prepareStatement(sql2);
                            ResultSet rs2=ps2.executeQuery();
                            if(rs2.next())
                            {
                                jLabel37.setText(rs2.getString("TITLE"));
                                jLabel41.setText(rs2.getString("F_NAME")+" "+rs2.getString("SUR_NAME"));
                                jLabel42.setText(rs2.getString("ISBN"));
                                jLabel43.setText(m.getBorrowDate2().toString());
                                jLabel45.setText(m.getRdate2().toString());
                                String path=rs2.getString("BOOK_PIC");
                                ImageIcon myImage  = new ImageIcon(path);
                                Image img = myImage.getImage();
                                Image newImg = img.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon image = new ImageIcon(newImg);
                                jLabel11.setIcon(image);
                            }
                        }
                        else
                        {
                            String sql2="select * from BOOKS,AUTHOR WHERE BOOK_ISBN="+BigDecimal.valueOf(Long.parseLong(isbn1))+" and ISBN="+BigDecimal.valueOf(Long.parseLong(isbn1));
                            PreparedStatement ps2=con.prepareStatement(sql2);
                            ResultSet rs2=ps2.executeQuery();
                            if(rs2.next())
                            {
                                jLabel37.setText(rs2.getString("TITLE"));
                                jLabel41.setText(rs2.getString("F_NAME")+" "+rs2.getString("SUR_NAME"));
                                jLabel42.setText(rs2.getString("ISBN"));
                                jLabel43.setText(m.getBorrowDate().toString());
                                jLabel45.setText(m.getReturnDate().toString());
                                String path=rs2.getString("BOOK_PIC");
                                ImageIcon myImage  = new ImageIcon(path);
                                Image img = myImage.getImage();
                                Image newImg = img.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon image = new ImageIcon(newImg);
                                jLabel11.setIcon(image);
                            }
                        }
                    }
                }
                else if(bb==3)
                {
                    if(m.getBorrowDate().compareTo(m.getBorrowDate2())<0)
                    {
                        if(m.getBorrowDate2().compareTo(m.getBdate3())<0)
                        {
                            
                            String sql2="select * from BOOKS,AUTHOR WHERE BOOK_ISBN="+BigDecimal.valueOf(Long.parseLong(isbn3))+" and ISBN="+BigDecimal.valueOf(Long.parseLong(isbn3));
                            PreparedStatement ps2=con.prepareStatement(sql2);
                            ResultSet rs2=ps2.executeQuery();
                            if(rs2.next())
                            {
                                jLabel37.setText(rs2.getString("TITLE"));
                                jLabel41.setText(rs2.getString("F_NAME")+" "+rs2.getString("SUR_NAME"));
                                jLabel42.setText(rs2.getString("ISBN"));
                                jLabel43.setText(m.getBdate3().toString());
                                jLabel45.setText(m.getRetdate3().toString());
                                String path=rs2.getString("BOOK_PIC");
                                ImageIcon myImage  = new ImageIcon(path);
                                Image img = myImage.getImage();
                                Image newImg = img.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon image = new ImageIcon(newImg);
                                jLabel11.setIcon(image);
                            }
                        }
                        else
                        {
                             String sql2="select * from BOOKS,AUTHOR WHERE BOOK_ISBN="+BigDecimal.valueOf(Long.parseLong(isbn2))+" and ISBN="+BigDecimal.valueOf(Long.parseLong(isbn2));
                            PreparedStatement ps2=con.prepareStatement(sql2);
                            ResultSet rs2=ps2.executeQuery();
                            if(rs2.next())
                            {
                                jLabel37.setText(rs2.getString("TITLE"));
                                jLabel41.setText(rs2.getString("F_NAME")+" "+rs2.getString("SUR_NAME"));
                                jLabel42.setText(rs2.getString("ISBN"));
                                jLabel43.setText(m.getBorrowDate2().toString());
                                jLabel45.setText(m.getRdate2().toString());
                                String path=rs2.getString("BOOK_PIC");
                                ImageIcon myImage  = new ImageIcon(path);
                                Image img = myImage.getImage();
                                Image newImg = img.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon image = new ImageIcon(newImg);
                                jLabel11.setIcon(image);
                            }
                        }
                    }
                    else if(m.getBorrowDate().compareTo(m.getBdate3())<0)
                        {
                            String sql2="select * from BOOKS,AUTHOR WHERE BOOK_ISBN="+BigDecimal.valueOf(Long.parseLong(isbn3))+" and ISBN="+BigDecimal.valueOf(Long.parseLong(isbn3));
                            PreparedStatement ps2=con.prepareStatement(sql2);
                            ResultSet rs2=ps2.executeQuery();
                            if(rs2.next())
                            {
                                jLabel37.setText(rs2.getString("TITLE"));
                                jLabel41.setText(rs2.getString("F_NAME")+" "+rs2.getString("SUR_NAME"));
                                jLabel42.setText(rs2.getString("ISBN"));
                                jLabel43.setText(m.getBdate3().toString());
                                jLabel45.setText(m.getRetdate3().toString());
                                String path=rs2.getString("BOOK_PIC");
                                ImageIcon myImage  = new ImageIcon(path);
                                Image img = myImage.getImage();
                                Image newImg = img.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon image = new ImageIcon(newImg);
                                jLabel11.setIcon(image);
                            }
                        }
                        else
                        {
                            String sql2="select * from BOOKS,AUTHOR WHERE BOOK_ISBN="+BigDecimal.valueOf(Long.parseLong(isbn1))+" and ISBN="+BigDecimal.valueOf(Long.parseLong(isbn1));
                            PreparedStatement ps2=con.prepareStatement(sql2);
                            ResultSet rs2=ps2.executeQuery();
                            if(rs2.next())
                            {
                                jLabel37.setText(rs2.getString("TITLE"));
                                jLabel41.setText(rs2.getString("F_NAME")+" "+rs2.getString("SUR_NAME"));
                                jLabel42.setText(rs2.getString("ISBN"));
                                jLabel43.setText(m.getBorrowDate().toString());
                                jLabel45.setText(m.getReturnDate().toString());
                                String path=rs2.getString("BOOK_PIC");
                                ImageIcon myImage  = new ImageIcon(path);
                                Image img = myImage.getImage();
                                Image newImg = img.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon image = new ImageIcon(newImg);
                                jLabel11.setIcon(image);
                            }
                        }
                    }
                    else
                    {
                            jLabel37.setText("NA");
                            jLabel41.setText("NA");
                            jLabel42.setText("NA");
                            jLabel43.setText("NA");
                            jLabel45.setText("NA");
                            
                    
                    }
                        con.commit();
                        con.close();
                
                }
            

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchGroup = new javax.swing.ButtonGroup();
        noteGroup = new javax.swing.ButtonGroup();
        fb_recommendBtn = new javax.swing.ButtonGroup();
        fb_recommend2Btn = new javax.swing.ButtonGroup();
        fb_RateGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        memberLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        homeBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        noteBtn = new javax.swing.JButton();
        fbBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        profileBtn = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        suggestBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        searchPanel = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        grid = new javax.swing.JPanel();
        notePanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        fbPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jRadioButton12 = new javax.swing.JRadioButton();
        jRadioButton13 = new javax.swing.JRadioButton();
        jRadioButton14 = new javax.swing.JRadioButton();
        jRadioButton15 = new javax.swing.JRadioButton();
        jRadioButton16 = new javax.swing.JRadioButton();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jRadioButton17 = new javax.swing.JRadioButton();
        jRadioButton18 = new javax.swing.JRadioButton();
        jRadioButton19 = new javax.swing.JRadioButton();
        jRadioButton20 = new javax.swing.JRadioButton();
        jRadioButton21 = new javax.swing.JRadioButton();
        jSeparator7 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        suggestPanel = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jTextField4 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        profilePanel = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        userLabel = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        passLabel = new javax.swing.JLabel();
        passField = new javax.swing.JPasswordField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel44 = new javax.swing.JLabel();
        cityLabel = new javax.swing.JLabel();
        cityField = new javax.swing.JTextField();
        streetLabel = new javax.swing.JLabel();
        stField = new javax.swing.JTextField();
        buildingLabel = new javax.swing.JLabel();
        buildField = new javax.swing.JTextField();
        fnameField = new javax.swing.JTextField();
        lnameField = new javax.swing.JTextField();
        phoneField = new javax.swing.JTextField();
        birthField = new javax.swing.JTextField();
        updateBtn = new javax.swing.JButton();
        browseBtn = new javax.swing.JButton();
        savepBtn = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(23, 35, 50));

        imageLabel.setBackground(new java.awt.Color(255, 255, 255));
        imageLabel.setForeground(new java.awt.Color(255, 255, 255));

        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setText("Name");

        memberLabel.setForeground(new java.awt.Color(255, 255, 255));
        memberLabel.setText("Member Type");

        jLabel4.setToolTipText("");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(255, 255, 255)));

        homeBtn.setBackground(new java.awt.Color(49, 86, 89));
        homeBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        homeBtn.setForeground(new java.awt.Color(230, 230, 230));
        homeBtn.setText("Home");
        homeBtn.setBorder(null);
        homeBtn.setFocusPainted(false);
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });

        searchBtn.setBackground(new java.awt.Color(2, 17, 27));
        searchBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(230, 230, 230));
        searchBtn.setText("Search");
        searchBtn.setBorder(null);
        searchBtn.setFocusPainted(false);
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        noteBtn.setBackground(new java.awt.Color(2, 17, 27));
        noteBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        noteBtn.setForeground(new java.awt.Color(230, 230, 230));
        noteBtn.setText("Note");
        noteBtn.setBorder(null);
        noteBtn.setFocusPainted(false);
        noteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noteBtnActionPerformed(evt);
            }
        });

        fbBtn.setBackground(new java.awt.Color(2, 17, 27));
        fbBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        fbBtn.setForeground(new java.awt.Color(230, 230, 230));
        fbBtn.setText("Feedback");
        fbBtn.setBorder(null);
        fbBtn.setFocusPainted(false);
        fbBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fbBtnActionPerformed(evt);
            }
        });

        jLabel5.setToolTipText("");
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(255, 255, 255)));

        profileBtn.setBackground(new java.awt.Color(2, 17, 27));
        profileBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        profileBtn.setForeground(new java.awt.Color(230, 230, 230));
        profileBtn.setText("Profile");
        profileBtn.setBorder(null);
        profileBtn.setFocusPainted(false);
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 51, 51));
        jButton6.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(230, 230, 230));
        jButton6.setText("Sign Out");
        jButton6.setBorder(null);
        jButton6.setFocusPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        suggestBtn.setBackground(new java.awt.Color(2, 17, 27));
        suggestBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        suggestBtn.setForeground(new java.awt.Color(230, 230, 230));
        suggestBtn.setText("Suggest");
        suggestBtn.setBorder(null);
        suggestBtn.setFocusPainted(false);
        suggestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suggestBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fbBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(profileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel)
                            .addComponent(memberLabel))
                        .addGap(0, 45, Short.MAX_VALUE))
                    .addComponent(suggestBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(memberLabel)
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(homeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fbBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(suggestBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 660));

        jPanel5.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Home");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(742, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(42, 42, 42))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 820, 90));

        rightPanel.setBackground(new java.awt.Color(255, 255, 255));
        rightPanel.setLayout(new java.awt.CardLayout());

        homePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel2.setText("Member ID:");

        jLabel3.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel3.setText("Member Since:");

        jLabel6.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel6.setText("Membership Expirtaion Date:");

        jLabel7.setText("ID");

        jLabel8.setText("DATE");

        jLabel9.setText("DATE");

        jLabel10.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        jLabel10.setText("Last Book You Borrowed:");

        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel12.setText("Title:");

        jLabel13.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel13.setText("Author:");

        jLabel14.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel14.setText("ISBN:");

        jLabel15.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel15.setText("Borrow Date:");

        jLabel16.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel16.setText("Return Date:");

        jLabel37.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        jLabel37.setText("jLabel37");

        jLabel41.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        jLabel41.setText("jLabel41");

        jLabel42.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        jLabel42.setText("jLabel42");

        jLabel43.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        jLabel43.setText("jLabel43");

        jLabel45.setFont(new java.awt.Font("Nirmala UI", 0, 11)); // NOI18N
        jLabel45.setText("jLabel45");

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(68, 68, 68))
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(homePanelLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGap(36, 36, 36)
                                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel45))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel41))
                        .addGap(18, 18, 18)
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel42))
                        .addGap(18, 18, 18)
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel43))
                        .addGap(18, 18, 18)
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel45))))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        rightPanel.add(homePanel, "card2");

        searchPanel.setBackground(new java.awt.Color(255, 255, 255));
        searchPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        searchPanel.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 32, 554, -1));

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        searchGroup.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Title");
        searchPanel.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 61, -1, -1));

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        searchGroup.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jRadioButton2.setText("Author");
        searchPanel.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 61, -1, -1));

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        searchGroup.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jRadioButton3.setText("ISBN");
        searchPanel.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 61, -1, -1));

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        searchGroup.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jRadioButton4.setText("Genre");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        searchPanel.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 61, -1, -1));

        jButton1.setBackground(new java.awt.Color(255, 51, 102));
        jButton1.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        searchPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 126, -1));
        searchPanel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 92, 800, 10));

        grid.setBackground(new java.awt.Color(255, 255, 255));
        grid.setLayout(new java.awt.GridLayout(1, 0));
        searchPanel.add(grid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 750, 450));

        rightPanel.add(searchPanel, "card3");

        notePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        jLabel17.setText("Current Notes:");

        jLabel18.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        jLabel18.setText("Add a Note:");

        noteGroup.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        jRadioButton5.setSelected(true);
        jRadioButton5.setText("General");

        noteGroup.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        jRadioButton6.setText("Specific (Please Specify)");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel19.setText("Please Specify Note`s Type:");

        jTextField2.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setBackground(new java.awt.Color(255, 51, 102));
        jButton2.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(241, 250, 238));
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel24.setText("Note Title:");

        jTextField3.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel46.setForeground(new java.awt.Color(255, 255, 255));

        jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel47MouseClicked(evt);
            }
        });

        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });

        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel49MouseClicked(evt);
            }
        });

        jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel50MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout notePanelLayout = new javax.swing.GroupLayout(notePanel);
        notePanel.setLayout(notePanelLayout);
        notePanelLayout.setHorizontalGroup(
            notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notePanelLayout.createSequentialGroup()
                .addComponent(jSeparator3)
                .addGap(44, 44, 44))
            .addGroup(notePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(notePanelLayout.createSequentialGroup()
                        .addGroup(notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 344, Short.MAX_VALUE))
                    .addGroup(notePanelLayout.createSequentialGroup()
                        .addGroup(notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel46)
                        .addGap(126, 126, 126))
                    .addGroup(notePanelLayout.createSequentialGroup()
                        .addGroup(notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jRadioButton6)
                            .addComponent(jRadioButton5)
                            .addComponent(jLabel18)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, notePanelLayout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(112, 112, 112)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        notePanelLayout.setVerticalGroup(
            notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(43, 43, 43)
                .addGroup(notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(notePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        rightPanel.add(notePanel, "card4");

        fbPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        jLabel20.setText("Your Recent Books:");

        jLabel21.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel21.setText("How did You Find the Book:");

        jRadioButton7.setBackground(new java.awt.Color(255, 255, 255));
        fb_recommendBtn.add(jRadioButton7);
        jRadioButton7.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton7.setSelected(true);
        jRadioButton7.setText("Very Bad");

        jRadioButton8.setBackground(new java.awt.Color(255, 255, 255));
        fb_recommendBtn.add(jRadioButton8);
        jRadioButton8.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton8.setText("Bad");

        jRadioButton9.setBackground(new java.awt.Color(255, 255, 255));
        fb_recommendBtn.add(jRadioButton9);
        jRadioButton9.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton9.setText("Normal");

        jRadioButton10.setBackground(new java.awt.Color(255, 255, 255));
        fb_recommendBtn.add(jRadioButton10);
        jRadioButton10.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton10.setText("Good");

        jRadioButton11.setBackground(new java.awt.Color(255, 255, 255));
        fb_recommendBtn.add(jRadioButton11);
        jRadioButton11.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton11.setText("Very Good");

        jLabel22.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel22.setText("Would You Recommend the Book:");

        jRadioButton12.setBackground(new java.awt.Color(255, 255, 255));
        fb_recommend2Btn.add(jRadioButton12);
        jRadioButton12.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton12.setSelected(true);
        jRadioButton12.setText("Never");

        jRadioButton13.setBackground(new java.awt.Color(255, 255, 255));
        fb_recommend2Btn.add(jRadioButton13);
        jRadioButton13.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton13.setText("Maybe");

        jRadioButton14.setBackground(new java.awt.Color(255, 255, 255));
        fb_recommend2Btn.add(jRadioButton14);
        jRadioButton14.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton14.setText("Don`t Think So");

        jRadioButton15.setBackground(new java.awt.Color(255, 255, 255));
        fb_recommend2Btn.add(jRadioButton15);
        jRadioButton15.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton15.setText("Yes");

        jRadioButton16.setBackground(new java.awt.Color(255, 255, 255));
        fb_recommend2Btn.add(jRadioButton16);
        jRadioButton16.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton16.setText("Absolutely");

        jLabel23.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel23.setText("How Would You Rate the Book:");

        jRadioButton17.setBackground(new java.awt.Color(255, 255, 255));
        fb_RateGroup.add(jRadioButton17);
        jRadioButton17.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton17.setSelected(true);
        jRadioButton17.setText("1");

        jRadioButton18.setBackground(new java.awt.Color(255, 255, 255));
        fb_RateGroup.add(jRadioButton18);
        jRadioButton18.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton18.setText("2");

        jRadioButton19.setBackground(new java.awt.Color(255, 255, 255));
        fb_RateGroup.add(jRadioButton19);
        jRadioButton19.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton19.setText("3");

        jRadioButton20.setBackground(new java.awt.Color(255, 255, 255));
        fb_RateGroup.add(jRadioButton20);
        jRadioButton20.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton20.setText("4");

        jRadioButton21.setBackground(new java.awt.Color(255, 255, 255));
        fb_RateGroup.add(jRadioButton21);
        jRadioButton21.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jRadioButton21.setText("5");

        jButton3.setBackground(new java.awt.Color(255, 51, 102));
        jButton3.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(241, 250, 238));
        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel51MouseClicked(evt);
            }
        });

        jLabel52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel52MouseClicked(evt);
            }
        });

        jLabel53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel53MouseClicked(evt);
            }
        });

        jLabel54.setForeground(new java.awt.Color(255, 255, 255));

        jLabel55.setForeground(new java.awt.Color(255, 255, 255));

        jLabel56.setForeground(new java.awt.Color(255, 255, 255));

        jLabel57.setForeground(new java.awt.Color(255, 255, 255));

        jLabel58.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout fbPanelLayout = new javax.swing.GroupLayout(fbPanel);
        fbPanel.setLayout(fbPanelLayout);
        fbPanelLayout.setHorizontalGroup(
            fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addComponent(jSeparator5)
            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(fbPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fbPanelLayout.createSequentialGroup()
                        .addGroup(fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fbPanelLayout.createSequentialGroup()
                                .addComponent(jRadioButton7)
                                .addGap(86, 86, 86)
                                .addComponent(jRadioButton8))
                            .addComponent(jLabel22)
                            .addGroup(fbPanelLayout.createSequentialGroup()
                                .addComponent(jRadioButton12)
                                .addGap(100, 100, 100)
                                .addComponent(jRadioButton14)))
                        .addGroup(fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fbPanelLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jRadioButton13))
                            .addGroup(fbPanelLayout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jRadioButton9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                        .addGroup(fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(fbPanelLayout.createSequentialGroup()
                                .addComponent(jRadioButton15)
                                .addGap(105, 105, 105)
                                .addComponent(jRadioButton16))
                            .addGroup(fbPanelLayout.createSequentialGroup()
                                .addComponent(jRadioButton10)
                                .addGap(93, 93, 93)
                                .addComponent(jRadioButton11)))
                        .addGap(42, 42, 42))
                    .addGroup(fbPanelLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(fbPanelLayout.createSequentialGroup()
                        .addComponent(jRadioButton17)
                        .addGap(130, 130, 130)
                        .addComponent(jRadioButton18)
                        .addGap(134, 134, 134)
                        .addComponent(jRadioButton19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton20)
                        .addGap(118, 118, 118)
                        .addComponent(jRadioButton21)
                        .addGap(88, 88, 88))
                    .addGroup(fbPanelLayout.createSequentialGroup()
                        .addGroup(fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addGroup(fbPanelLayout.createSequentialGroup()
                                .addGap(167, 167, 167)
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fbPanelLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fbPanelLayout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(jLabel55)
                                .addGap(53, 53, 53))))))
            .addComponent(jSeparator7)
            .addGroup(fbPanelLayout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(fbPanelLayout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jLabel56)
                .addGap(83, 83, 83)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel58)
                .addGap(107, 107, 107))
        );
        fbPanelLayout.setVerticalGroup(
            fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fbPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel55))
                .addGap(51, 51, 51)
                .addGroup(fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, fbPanelLayout.createSequentialGroup()
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(jLabel57))
                        .addGap(12, 12, 12))
                    .addGroup(fbPanelLayout.createSequentialGroup()
                        .addGroup(fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(fbPanelLayout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton7)
                    .addComponent(jRadioButton8)
                    .addComponent(jRadioButton10)
                    .addComponent(jRadioButton11)
                    .addComponent(jRadioButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addGroup(fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton12)
                    .addComponent(jRadioButton14)
                    .addComponent(jRadioButton15)
                    .addComponent(jRadioButton16)
                    .addComponent(jRadioButton13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addGroup(fbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton17)
                    .addComponent(jRadioButton18)
                    .addComponent(jRadioButton19)
                    .addComponent(jRadioButton20)
                    .addComponent(jRadioButton21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        rightPanel.add(fbPanel, "card5");

        suggestPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        jLabel25.setText("Please Fill in the Following:");

        jLabel26.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel26.setText("Book Title:");

        jLabel27.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel27.setText("Book Genre:");

        jTextField4.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel28.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel28.setText("Book Author:");

        jTextField6.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel29.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel29.setText("Book ISBN:");

        jTextField7.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jTextField8.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jTextField8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel30.setForeground(new java.awt.Color(255, 51, 0));
        jLabel30.setText("*");

        jLabel31.setForeground(new java.awt.Color(255, 51, 0));
        jLabel31.setText("*");

        jLabel32.setFont(new java.awt.Font("Nirmala UI", 0, 10)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 51, 0));
        jLabel32.setText("You Must Fill the (*)");

        jLabel33.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel33.setText("Tell US a Little More About Your Suggestion:");

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jButton4.setBackground(new java.awt.Color(230, 57, 70));
        jButton4.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(241, 250, 238));
        jButton4.setText("Add Suggestion");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout suggestPanelLayout = new javax.swing.GroupLayout(suggestPanel);
        suggestPanel.setLayout(suggestPanelLayout);
        suggestPanelLayout.setHorizontalGroup(
            suggestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator8)
            .addGroup(suggestPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(suggestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(suggestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2)
                        .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, suggestPanelLayout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addGroup(suggestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel30)
                                .addComponent(jLabel31))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(suggestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField6)
                                .addComponent(jTextField4)
                                .addComponent(jLabel29)
                                .addComponent(jLabel28)
                                .addComponent(jLabel27)
                                .addComponent(jLabel26)
                                .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)))
                        .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton4))
                .addContainerGap(430, Short.MAX_VALUE))
        );
        suggestPanelLayout.setVerticalGroup(
            suggestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(suggestPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addGap(5, 5, 5)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(suggestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(suggestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel31))
                .addGap(4, 4, 4)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        rightPanel.add(suggestPanel, "card6");

        profilePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel34.setText("PP");
        jLabel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel35.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel35.setText("First Name:");

        jLabel36.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel36.setText("Last Name:");

        phoneLabel.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        phoneLabel.setText("Phone Number:");

        jLabel38.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        jLabel38.setText("Date of Birth:");

        jLabel39.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel39.setText("Basic Info");

        jLabel40.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel40.setText("Login Credentials");

        userLabel.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        userLabel.setText("Username:");

        userField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        userField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        emailField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        emailField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        emailLabel.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        emailLabel.setText("Email:");

        passLabel.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        passLabel.setText("Password:");

        passField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        passField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel44.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel44.setText("Address:");

        cityLabel.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        cityLabel.setText("City:");

        cityField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        cityField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        streetLabel.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        streetLabel.setText("Street:");

        stField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        stField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        stField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stFieldActionPerformed(evt);
            }
        });

        buildingLabel.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        buildingLabel.setText("Building:");

        buildField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        buildField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        fnameField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        fnameField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        lnameField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        lnameField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        phoneField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        phoneField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        birthField.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        birthField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        updateBtn.setBackground(new java.awt.Color(230, 57, 70));
        updateBtn.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(241, 250, 238));
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
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

        savepBtn.setBackground(new java.awt.Color(230, 57, 70));
        savepBtn.setFont(new java.awt.Font("Nirmala UI", 1, 12)); // NOI18N
        savepBtn.setForeground(new java.awt.Color(241, 250, 238));
        savepBtn.setText("Save");
        savepBtn.setMaximumSize(new java.awt.Dimension(67, 23));
        savepBtn.setMinimumSize(new java.awt.Dimension(67, 23));
        savepBtn.setPreferredSize(new java.awt.Dimension(67, 23));
        savepBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savepBtnActionPerformed(evt);
            }
        });

        jLabel59.setForeground(new java.awt.Color(255, 255, 255));

        jLabel60.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout profilePanelLayout = new javax.swing.GroupLayout(profilePanel);
        profilePanel.setLayout(profilePanelLayout);
        profilePanelLayout.setHorizontalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator10)
            .addComponent(jSeparator9)
            .addGroup(profilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(profilePanelLayout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(profilePanelLayout.createSequentialGroup()
                        .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(profilePanelLayout.createSequentialGroup()
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(profilePanelLayout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(profilePanelLayout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(profilePanelLayout.createSequentialGroup()
                                        .addComponent(phoneLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(profilePanelLayout.createSequentialGroup()
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(birthField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel44)
                            .addGroup(profilePanelLayout.createSequentialGroup()
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
                        .addContainerGap(335, Short.MAX_VALUE))
                    .addGroup(profilePanelLayout.createSequentialGroup()
                        .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userLabel)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(emailLabel)
                            .addComponent(passLabel)
                            .addComponent(passField)
                            .addComponent(emailField)
                            .addComponent(userField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel59)
                            .addComponent(jLabel60))
                        .addGap(134, 134, 134))))
        );
        profilePanelLayout.setVerticalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(profilePanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(fnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(lnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phoneLabel)
                            .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(birthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(profilePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel59))
                .addGap(18, 18, 18)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userLabel)
                    .addComponent(jLabel60))
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
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel)
                    .addComponent(cityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(streetLabel)
                    .addComponent(stField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buildingLabel)
                    .addComponent(buildField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(savepBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        rightPanel.add(profilePanel, "card7");

        getContentPane().add(rightPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 820, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        // TODO add your handling code here:
        Color clicked = new Color (49,86,89);
        Color ntClicked = new Color (2,17,27);
        homeBtn.setBackground(clicked);
        
        searchBtn.setBackground(ntClicked);
        noteBtn.setBackground(ntClicked);
        suggestBtn.setBackground(ntClicked);
        fbBtn.setBackground(ntClicked);
        profileBtn.setBackground(ntClicked);
        jLabel1.setText("Home");
        
        homePanel.setVisible(true);
        searchPanel.setVisible(false);
        notePanel.setVisible(false);
        fbPanel.setVisible(false);
        suggestPanel.setVisible(false);
        profilePanel.setVisible(false);
        
        
    }//GEN-LAST:event_homeBtnActionPerformed

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
        // TODO add your handling code here:
        Color clicked = new Color (49,86,89);
        Color ntClicked = new Color (2,17,27);
        homeBtn.setBackground(ntClicked);
        
        searchBtn.setBackground(ntClicked);
        noteBtn.setBackground(ntClicked);
        suggestBtn.setBackground(ntClicked);
        fbBtn.setBackground(ntClicked);
        profileBtn.setBackground(clicked);
        jLabel1.setText("Profile");
        
        homePanel.setVisible(false);
        searchPanel.setVisible(false);
        notePanel.setVisible(false);
        fbPanel.setVisible(false);
        suggestPanel.setVisible(false);
        profilePanel.setVisible(true);
       
        
        updateBtn.setVisible(true);
        savepBtn.setVisible(false);
        browseBtn.setVisible(false);
        
        fnameField.setEditable(false);
        lnameField.setEditable(false);
        phoneField.setEditable(false);
        birthField.setEditable(false);
        userField.setEditable(false);
        passField.setEditable(false);
        emailField.setEditable(false);
        cityField.setEditable(false);
        stField.setEditable(false);
        buildField.setEditable(false);
        
    }//GEN-LAST:event_profileBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        Color clicked = new Color (49,86,89);
        Color ntClicked = new Color (2,17,27);
        homeBtn.setBackground(ntClicked);
        
        searchBtn.setBackground(clicked);
        noteBtn.setBackground(ntClicked);
        suggestBtn.setBackground(ntClicked);
        fbBtn.setBackground(ntClicked);
        profileBtn.setBackground(ntClicked);
        jLabel1.setText("Search");
        
        homePanel.setVisible(false);
        searchPanel.setVisible(true);
        notePanel.setVisible(false);
        fbPanel.setVisible(false);
        suggestPanel.setVisible(false);
        profilePanel.setVisible(false);
    }//GEN-LAST:event_searchBtnActionPerformed

    private void noteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noteBtnActionPerformed
        // TODO add your handling code here:Color clicked = new Color (49,86,89);
        try
        {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="select * from notes where NOTEID='"+jLabel46.getText()+"' order by NOTENUM DESC";
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                int f=47;
                
                    if(rs.next())
                    {
                       
                        String title=rs.getString("NOTETITLE");
                        jLabel47.setText(title);
                    
                    if(rs.next())
                    {
                         title=rs.getString("NOTETITLE");
                        jLabel48.setText(title);
                    
                    if(rs.next())
                    {
                         title=rs.getString("NOTETITLE");
                        jLabel49.setText(title);
                    }
                    if(rs.next())
                    {
                         title=rs.getString("NOTETITLE");
                        jLabel50.setText(title);
                    }
                    }
                    }
                    
                    
                
        }
        catch(Exception e)
        {
            
        }
        Color ntClicked = new Color (2,17,27);
        Color clicked = new Color (49,86,89);
        homeBtn.setBackground(ntClicked);
        
        searchBtn.setBackground(ntClicked);
        noteBtn.setBackground(clicked);
        suggestBtn.setBackground(ntClicked);
        fbBtn.setBackground(ntClicked);
        profileBtn.setBackground(ntClicked);
        jLabel1.setText("Notes");
        
        homePanel.setVisible(false);
        searchPanel.setVisible(false);
        notePanel.setVisible(true);
        fbPanel.setVisible(false);
        suggestPanel.setVisible(false);
        profilePanel.setVisible(false);
        
    }//GEN-LAST:event_noteBtnActionPerformed

    private void fbBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fbBtnActionPerformed
        // TODO add your handling code here:
        Color clicked = new Color (49,86,89);
        Color ntClicked = new Color (2,17,27);
        homeBtn.setBackground(ntClicked);
        
        searchBtn.setBackground(ntClicked);
        noteBtn.setBackground(ntClicked);
        suggestBtn.setBackground(ntClicked);
        fbBtn.setBackground(clicked);
        profileBtn.setBackground(ntClicked);
        jLabel1.setText("Feedback");
        
         homePanel.setVisible(false);
        searchPanel.setVisible(false);
        notePanel.setVisible(false);
        fbPanel.setVisible(true);
        suggestPanel.setVisible(false);
        profilePanel.setVisible(false);
        
    }//GEN-LAST:event_fbBtnActionPerformed

    private void suggestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suggestBtnActionPerformed
        // TODO add your handling code here:
        Color clicked = new Color (49,86,89);
        Color ntClicked = new Color (2,17,27);
        homeBtn.setBackground(ntClicked);
        
        searchBtn.setBackground(ntClicked);
        noteBtn.setBackground(ntClicked);
        suggestBtn.setBackground(clicked);
        fbBtn.setBackground(ntClicked);
        profileBtn.setBackground(ntClicked);
        jLabel1.setText("Suggestion");
        homePanel.setVisible(false);
        searchPanel.setVisible(false);
        notePanel.setVisible(false);
        fbPanel.setVisible(false);
        suggestPanel.setVisible(true);
        
        jTextField4.setText("");
        jTextField8.setText("");
        jTextField6.setText("");
      jTextField7.setText("");
    jTextArea2.setText("");
        
    }//GEN-LAST:event_suggestBtnActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        String fb="";
        String rb="";
        int rating=1;
        if(jRadioButton7.isSelected())
            fb=jRadioButton7.getText();
        else if(jRadioButton8.isSelected())
            fb=jRadioButton8.getText();
        else if(jRadioButton9.isSelected())
            fb=jRadioButton9.getText();
        else if(jRadioButton10.isSelected())
            fb=jRadioButton10.getText();
        else if(jRadioButton11.isSelected())
            fb=jRadioButton11.getText();
        
        if (jRadioButton12.isSelected())
            rb=jRadioButton12.getText();
        else if (jRadioButton13.isSelected())
            rb=jRadioButton13.getText();
        else if (jRadioButton14.isSelected())
            rb=jRadioButton14.getText();
        else if (jRadioButton15.isSelected())
            rb=jRadioButton15.getText();
        else if (jRadioButton16.isSelected())
            rb=jRadioButton16.getText();
        
        if (jRadioButton17.isSelected())
            rating=Integer.parseInt(jRadioButton17.getText());
        else if (jRadioButton18.isSelected())
            rating=Integer.parseInt(jRadioButton18.getText());
        else if (jRadioButton19.isSelected())
            rating=Integer.parseInt(jRadioButton19.getText());
        else if (jRadioButton20.isSelected())
            rating=Integer.parseInt(jRadioButton20.getText());
        else if (jRadioButton21.isSelected())
            rating=Integer.parseInt(jRadioButton21.getText());
        
      //  int flag=Integer.parseInt(jLabel58.getText());
        
       String fISBN=jLabel58.getText();
       BigDecimal is = BigDecimal.valueOf(Long.parseLong(fISBN));
       int isFB=0;
        try
        {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
            String sql="select * from Feedback where F_ISBN="+is+" and F_ID='"+jLabel55.getText()+"'";
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                isFB=1;
            }
            con.commit();
            con.close();
        }
          catch(Exception e)
        {
            
        }
        if(isFB==0)
        {
            try
            {
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="insert into FEEDBACK values(5,"+is+",'"+jLabel55.getText()+"','"+fb+"','"+rb+"',"+rating+")";
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                con.commit();
                con.close();
            }
            catch(Exception e)
            {
            
            }
        }
        else
        {
             try
            {
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="update  FEEDBACK set FEEDBACK_FIND='"+fb+"',FB_RECOM='"+rb+"',rating="+rating+"where F_ISBN="+is+" and  F_ID='"+jLabel55.getText()+"'";
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                con.commit();
                con.close();
            }
            catch(Exception e)
            {
            
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void stFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stFieldActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_stFieldActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        updateBtn.setVisible(false);
        savepBtn.setVisible(true);
        browseBtn.setVisible(true);
        
        fnameField.setEditable(false);
        lnameField.setEditable(false);
        phoneField.setEditable(true);
        birthField.setEditable(false);
        userField.setEditable(true);
        passField.setEditable(true);
        emailField.setEditable(true);
        cityField.setEditable(true);
        stField.setEditable(true);
        buildField.setEditable(true);
        
    }//GEN-LAST:event_updateBtnActionPerformed

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
            Image newImg = img.getScaledInstance(jLabel34.getWidth(), jLabel34.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(newImg);
            jLabel34.setIcon(image);
        }
    }//GEN-LAST:event_browseBtnActionPerformed

    private void savepBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savepBtnActionPerformed
        // TODO add your handling code here:
        String pNumber=phoneField.getText();
        String username=userField.getText();
        String pass=passField.getText();
        String email=emailField.getText();
        String regex =  "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        String city=cityField.getText();
        String build=buildField.getText();
        String street=stField.getText();
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
                if(!pass.equals(jLabel60.getText()))
                    pass=getMd5(pass);
                if(city.isEmpty()&&city.startsWith(" "))
                    city=null;
                if(build.isEmpty()&&build.startsWith(" "))
                    build=null;
                if(street.isEmpty()&&street.startsWith(" "))
                    street=null;
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="update PERSON1 set USERNAME='"+username+"',PASWORD='"+pass+"',EMAIL='"+email+"',CITY='"+city+"',BUILDING='"+build+"',"
                        + "STREET='"+street+"',PHONENUMBER="+Integer.parseInt(pNumber)+" where ID='"+jLabel59.getText()+"'";
                String sql2="update IMAGES set IMAGE_PATH='"+iPath+"' where IMAGE_ID='"+jLabel59.getText()+"'";
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
       
        updateBtn.setVisible(true);
        savepBtn.setVisible(false);
        browseBtn.setVisible(false);
        
        fnameField.setEditable(false);
        lnameField.setEditable(false);
        phoneField.setEditable(false);
        birthField.setEditable(false);
        userField.setEditable(false);
        passField.setEditable(false);
        emailField.setEditable(false);
        cityField.setEditable(false);
        stField.setEditable(false);
        buildField.setEditable(false);
    }//GEN-LAST:event_savepBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String nType="General";
        if(jRadioButton6.isSelected())
            nType=jTextField2.getText();
        String nTitle=jTextField3.getText();
        String nDescrep=jTextArea1.getText();
        if(nTitle.isEmpty()&&nTitle.startsWith(" "))
        {
            JOptionPane.showMessageDialog(null, "Please enter a title");
        }
        else if(nDescrep.isEmpty()&&nDescrep.startsWith(" "))
        {
            JOptionPane.showMessageDialog(null, "Please enter a Descreption");
        }
        else
        {
            try
            {
                int note_num=1;
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="select * from notes where NOTEID='"+jLabel46.getText()+"' order by NOTENUM DESC";
                PreparedStatement ps=con.prepareStatement(sql);
                
                int f=47;
                
                String sql2="insert into NOTES values('"+nType+"','"+nTitle+"','"+nDescrep+"','"+jLabel46.getText()+"',"+note_num+")";
                PreparedStatement ps2=con.prepareStatement(sql2);
                ResultSet rs2=ps2.executeQuery();
                ResultSet rs=ps.executeQuery();
                for(int i=0;i<4&&rs.next();i++)
                {   
                    if(i==0)
                    {
                        int nn=rs.getInt("NOTENUM");
                        note_num=nn+1;
                        String title=rs.getString("NOTETITLE");
                        jLabel47.setText(title);
                    }
                    if(i==2)
                    {
                        String title=rs.getString("NOTETITLE");
                        jLabel48.setText(title);
                    }
                    if(i==3)
                    {
                        String title=rs.getString("NOTETITLE");
                        jLabel49.setText(title);
                    }
                    if(i==4)
                    {
                        String title=rs.getString("NOTETITLE");
                        jLabel50.setText(title);
                    }
                    
                    
                }
                this.setVisible(false);
                this.setVisible(true);
                con.commit();
                con.close();
                
            }
            catch(Exception e)
            {
                
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseClicked
        // TODO add your handling code here:
        String nTitle=jLabel47.getText();
        notesJFrame njf=new notesJFrame(nTitle);
        njf.setVisible(true);
    }//GEN-LAST:event_jLabel47MouseClicked

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
        // TODO add your handling code here:
         String nTitle=jLabel48.getText();
        notesJFrame njf=new notesJFrame(nTitle);
        njf.setVisible(true);
    }//GEN-LAST:event_jLabel48MouseClicked

    private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseClicked
        // TODO add your handling code here:
         String nTitle=jLabel49.getText();
        notesJFrame njf=new notesJFrame(nTitle);
        njf.setVisible(true);
    }//GEN-LAST:event_jLabel49MouseClicked

    private void jLabel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel50MouseClicked
        // TODO add your handling code here:
         String nTitle=jLabel50.getText();
        notesJFrame njf=new notesJFrame(nTitle);
        njf.setVisible(true);
    }//GEN-LAST:event_jLabel50MouseClicked

    private void jLabel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MouseClicked
        // TODO add your handling code here:
        jRadioButton7.setEnabled(true);
        jRadioButton8.setEnabled(true);
        jRadioButton9.setEnabled(true);
        jRadioButton10.setEnabled(true);
        jRadioButton11.setEnabled(true);
        jRadioButton12.setEnabled(true);
        jRadioButton13.setEnabled(true);
        jRadioButton14.setEnabled(true);
        jRadioButton15.setEnabled(true);
        jRadioButton16.setEnabled(true);
        jRadioButton17.setEnabled(true);
        jRadioButton18.setEnabled(true);
        jRadioButton19.setEnabled(true);
        jRadioButton20.setEnabled(true);
        jRadioButton21.setEnabled(true);
        jLabel58.setText(jLabel54.getText());
    }//GEN-LAST:event_jLabel51MouseClicked

    private void jLabel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseClicked
        // TODO add your handling code here:
        jRadioButton7.setEnabled(true);
        jRadioButton8.setEnabled(true);
        jRadioButton9.setEnabled(true);
        jRadioButton10.setEnabled(true);
        jRadioButton11.setEnabled(true);
        jRadioButton12.setEnabled(true);
        jRadioButton13.setEnabled(true);
        jRadioButton14.setEnabled(true);
        jRadioButton15.setEnabled(true);
        jRadioButton16.setEnabled(true);
        jRadioButton17.setEnabled(true);
        jRadioButton18.setEnabled(true);
        jRadioButton19.setEnabled(true);
        jRadioButton20.setEnabled(true);
        jRadioButton21.setEnabled(true);
        jLabel58.setText(jLabel56.getText());
    }//GEN-LAST:event_jLabel52MouseClicked

    private void jLabel53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel53MouseClicked
        // TODO add your handling code here:
        jRadioButton7.setEnabled(true);
        jRadioButton8.setEnabled(true);
        jRadioButton9.setEnabled(true);
        jRadioButton10.setEnabled(true);
        jRadioButton11.setEnabled(true);
        jRadioButton12.setEnabled(true);
        jRadioButton13.setEnabled(true);
        jRadioButton14.setEnabled(true);
        jRadioButton15.setEnabled(true);
        jRadioButton16.setEnabled(true);
        jRadioButton17.setEnabled(true);
        jRadioButton18.setEnabled(true);
        jRadioButton19.setEnabled(true);
        jRadioButton20.setEnabled(true);
        jRadioButton21.setEnabled(true);
        jLabel58.setText(jLabel57.getText());
    }//GEN-LAST:event_jLabel53MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String bTitle=jTextField4.getText();
        String bGenre=jTextField8.getText();
        String bAuthor=jTextField6.getText();
        String bISBN=jTextField7.getText();
        String bDesp=jTextArea2.getText();
        if(bTitle.isEmpty()&&bTitle.startsWith(" "))
            JOptionPane.showMessageDialog(null, "Please enter the title");
        else if(bGenre.isEmpty()&&bGenre.startsWith(" "))
            JOptionPane.showMessageDialog(null, "Please enter the title");
        else
        {
            try
            {
                if(bAuthor.isEmpty()&&bAuthor.startsWith(" "))
                    bAuthor=null;
                if(bISBN.isEmpty()&&bISBN.startsWith(" "))
                    bISBN=null;
                if(bDesp.isEmpty()&&bDesp.startsWith(" "))
                    bDesp=null;
                    
                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle","library","123456"); 
                String sql="insert into SUGGESTION values('"+bTitle+"','"+bGenre+"',"+BigDecimal.valueOf(Long.parseLong(bISBN))+",'"+bAuthor+"','"+bDesp+"')";
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
            }
            catch(Exception e)
            {
                
            }
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

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
                    String bISBN=rs.getString("ISBN");
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
                    String bISBN=rs.getString("ISBN");
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
                    String bISBN=rs.getString("ISBN");
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
                    String bISBN=rs.getString("ISBN");
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

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        LogIn lg=new LogIn();
        lg.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(MemeberGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemeberGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemeberGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemeberGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemeberGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField birthField;
    private javax.swing.JButton browseBtn;
    private javax.swing.JTextField buildField;
    private javax.swing.JLabel buildingLabel;
    private javax.swing.JTextField cityField;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JButton fbBtn;
    private javax.swing.JPanel fbPanel;
    private javax.swing.ButtonGroup fb_RateGroup;
    private javax.swing.ButtonGroup fb_recommend2Btn;
    private javax.swing.ButtonGroup fb_recommendBtn;
    private javax.swing.JTextField fnameField;
    private javax.swing.JPanel grid;
    private javax.swing.JButton homeBtn;
    private javax.swing.JPanel homePanel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton14;
    private javax.swing.JRadioButton jRadioButton15;
    private javax.swing.JRadioButton jRadioButton16;
    private javax.swing.JRadioButton jRadioButton17;
    private javax.swing.JRadioButton jRadioButton18;
    private javax.swing.JRadioButton jRadioButton19;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton20;
    private javax.swing.JRadioButton jRadioButton21;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField lnameField;
    private javax.swing.JLabel memberLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton noteBtn;
    private javax.swing.ButtonGroup noteGroup;
    private javax.swing.JPanel notePanel;
    private javax.swing.JPasswordField passField;
    private javax.swing.JLabel passLabel;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JButton profileBtn;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JButton savepBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.ButtonGroup searchGroup;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTextField stField;
    private javax.swing.JLabel streetLabel;
    private javax.swing.JButton suggestBtn;
    private javax.swing.JPanel suggestPanel;
    private javax.swing.JButton updateBtn;
    private javax.swing.JTextField userField;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
