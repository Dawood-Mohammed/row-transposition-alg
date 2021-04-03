import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ooo
 */
public class RowTransposition {
    
    private JFrame frame;
    private JLabel l1 , l2 , l3;
    private JPanel panel0 , panel , panel2 ,panel3;
    private JButton b1 , b2 ;
    private JTextField tf1 , tf2;
    private JFileChooser fc;
    
    public static void main(String args[]){
        RowTransposition rt = new RowTransposition();
    }
    
    public RowTransposition(){
        prepare();
    }
    
    public void prepare(){
        frame = new JFrame("Row_Transposition algorithim");
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(4,1));
        frame.setVisible(true);
        panel0 = new JPanel();
        panel0.setBackground(new java.awt.Color(0,151,230));
        panel0.setLayout(new FlowLayout());
        panel0.add(new JLabel("Row Transposition"));
        l1 = new JLabel("Text",JLabel.CENTER);
        l2 = new JLabel("Keyword",JLabel.CENTER);
        l3 = new JLabel("",JLabel.CENTER);
        l3.setOpaque(true);
        l3.setBackground(Color.WHITE);
        b1 = new JButton("Encrypt");
        b1.setBackground(Color.WHITE);
        b2 = new JButton("Decrypt");
        b2.setBackground(Color.WHITE);
        tf1 = new JTextField(10);
        tf1.setBorder(null);
        tf2 = new JTextField(10);
        tf2.setBorder(null);
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(new java.awt.Color(0,151,230));
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBackground(new java.awt.Color(0,151,230));
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.setBackground(new java.awt.Color(0,151,230));
        fc = new JFileChooser();
        b1.addActionListener((ActionEvent e)->{
            try{
                
                String s = tf1.getText(),s1 = tf2.getText();
                if(s.length()>0 && isValid(s1)){
                    l3.setText(cipher(s,s1));
                }else if(s.length()==0){
                    s="";
                    int approve = fc.showOpenDialog(frame);
                    if(approve == JFileChooser.APPROVE_OPTION){
                    File  f = fc.getSelectedFile();
                    try{
                    FileInputStream fis = new FileInputStream(f);
                    int c;
                    while((c=fis.read())!= -1){
                        s+=(char)c;
                    }
                    
                    }catch(IOException w){
                        l3.setText(w.toString());
                    }
                    Thread t = new Thread();
                    try{
                        t.sleep(1000);
                    }catch(InterruptedException ez){
                        ez.printStackTrace();
                    }
                    int ap = fc.showOpenDialog(frame);
                    if(ap == JFileChooser.APPROVE_OPTION){
                    File f2 = fc.getSelectedFile();
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(f2));
                    dos.writeBytes(cipher(s,s1));
                    //l3.setText(cipherText(s,s1));
                    }
                    }
                }
            }catch(Exception ex){
                l3.setText("something is wrong >> please check your data and try again");
            }
        });
        b2.addActionListener((ActionEvent e)->{
            try{
                String s = tf1.getText(),s1 = tf2.getText();
                if(s.length()>0 && isValid(s1)){
                    l3.setText(plain(s,s1));
                }else if(s.length()==0 && isValid(s1)){
                    s="";
                    int approve = fc.showOpenDialog(frame);
                    if(approve == JFileChooser.APPROVE_OPTION){
                    File  f = fc.getSelectedFile();
                    try{
                    FileInputStream fis = new FileInputStream(f);
                    int c;
                    while((c=fis.read())!= -1){
                        s+=(char)c;
                    }
                    
                    }catch(IOException w){
                        l3.setText(w.toString());
                    }
                    Thread t = new Thread();
                    try{
                        t.sleep(1000);
                    }catch(InterruptedException ez){
                        ez.printStackTrace();
                    }
                    int ap = fc.showOpenDialog(frame);
                    if(ap == JFileChooser.APPROVE_OPTION){
                    File f2 = fc.getSelectedFile();
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(f2));
                    dos.writeBytes(plain(s,s1));
                    //l3.setText(cipherText(s,s1));
                    }
                    }
                }
                
            }catch(Exception ex){
                l3.setText("something is wrong >> please check your data and try again");
            }
        });
        panel.add(l1);
        panel.add(tf1);
        panel.add(l2);
        panel.add(tf2);
        panel2.add(b1);
        panel2.add(b2);
        panel3.add(l3);
        frame.add(panel0);
        frame.add(panel);
        frame.add(panel2);
        frame.add(panel3);
        frame.getContentPane().setBackground(new Color(255,204,0));
        frame.setVisible(true);
    }
    
    public String cipher(String plain , String key){
        int length = plain.length();
        String cipherText="";
        int holder = 0;
        //making sure that message length mode 7 = 0 (since i am using just seven columns)
        if(length%7!=0){
            for(int i=1; i<7; i++){
                length++;
                holder=i;
                if(length%7==0){
                    break;
                }
            }
        }
        //checking last row if not completed then will fill it
        if(holder == 6)
            plain+="uvwxyz";
        else if(holder == 5)
            plain+="vwxyz";
        else if(holder == 4)
            plain+="wxyz";
        else if(holder == 3)
            plain+="xyz";
        else if(holder == 2)
            plain+="yz";
        else if(holder == 1)
            plain+="z";
        
        //filling the table with our message which has been splited into pieces as an array of letters
        char el[]= plain.toCharArray();
        int help_i = length/7;
        char [][] table = new char[help_i][7];
        for(int i=0; i<help_i; i++){
            for(int j=0; j<7; j++){
                    table[i][j]=el[i*7+j];
            }
        }
        //pulling out our seven columns as strings 
        String row1 = "" , row2 = "" , row3 = "" , row4 = "" , row5 = "" , row6 = "" , row7 = "";
        for(int j=0; j<7; j++){
            for(int i=0; i<help_i; i++){
                if(j==0)
                    row1+=table[i][j];
                else if(j==1)
                    row2+=table[i][j];
                else if(j==2)
                    row3+=table[i][j];
                else if(j==3)
                    row4+=table[i][j];
                else if(j==4)
                    row5+=table[i][j];
                else if(j==5)
                    row6+=table[i][j];
                else if(j==6)
                    row7+=table[i][j];
            }       
        }
        //getting the columns in the rigth order according to our key
        String rows[] = {row1,row2,row3,row4,row5,row6,row7};
        String[] found = new String[7];
        for(int i=0; i<key.length(); i++){
            found[0]=rows[key.indexOf('1')];
            found[1]=rows[key.indexOf('2')];
            found[2]=rows[key.indexOf('3')];
            found[3]=rows[key.indexOf('4')];
            found[4]=rows[key.indexOf('5')];
            found[5]=rows[key.indexOf('6')];
            found[6]=rows[key.indexOf('7')];
        }
        //concatonating our columns into one string
        for (String row : found) {
            cipherText+=row;
        }
        
        //flipping our final string letters as upper case characters
        cipherText = cipherText.toUpperCase();
        //returning the final encrypted message
        return cipherText;
    }
    //DECRYPTION METHOD
    public String plain(String cipher ,String key){
        String plainText = "";
        //GETTING NUMBER OF ROWS ON OUR SEVEN COLUMN TABLE
        int holder = cipher.length()/7;
        String col1="",col2="",col3="",col4="",col5="",col6="",col7="";
        //ASSIGNING COLUMNS
        col1=cipher.substring(0,holder);
        col2=cipher.substring(holder,(holder)*2);
        col3=cipher.substring((holder)*2,(holder)*3);
        col4=cipher.substring((holder)*3,(holder)*4);
        col5=cipher.substring((holder)*4,(holder)*5);
        col6=cipher.substring((holder)*5,(holder)*6);
        col7=cipher.substring((holder)*6,(holder)*7);
        //GETTING COLUMN IN THE RIGTH ORDER ACCORDING TO OUR KEY
        String rows[] = {col1,col2,col3,col4,col5,col6,col7};
        String[] found = new String[7];
        char keys[] = key.toCharArray();
        for(int i=0; i<keys.length; i++){
            found[i]=rows[Integer.parseInt(""+keys[i])-1];
            
        }
        //SPLITTING ORDERED COLUMNS
        String row1="",row2="",row3="",row4="",row5="",row6="",row7="";
        row1 = found[0];
        row2 = found[1];
        row3 = found[2];
        row4 = found[3];
        row5 = found[4];
        row6 = found[5];
        row7 = found[6];
        //CONOLUDING FINAL MESSAGE
        for(int i=0; i<holder; i++){
            plainText+=""+row1.charAt(i)+row2.charAt(i)+row3.charAt(i)+row4.charAt(i)+row5.charAt(i)+row6.charAt(i)+row7.charAt(i);
        }
        
        return plainText;
    }
    public static boolean isValid(String key){
        char keys[] = new char[7];
        List com = new ArrayList();
        boolean check = true;
        if (key.length() == 7) {
            keys = key.toCharArray();
            for (int i = 0; i < keys.length; i++) {
                if (Integer.parseInt("" + keys[i]) <= 7 && Integer.parseInt("" + keys[i]) >= 1){
                
                    if (!com.contains(keys[i])) {
                        com.add(keys[i]);
                    } else {
                        check = false;
                        break;
                    }
                }
            }
        }else{
            check = false;
        }
       
        return check;
    }
    
}
    
