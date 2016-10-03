

	import java.awt.*;
	import java.awt.event.*;
	import java.awt.datatransfer.*;
	import java.io.*;
	import java.util.regex.*;
	
 	public class Note extends Frame         // Designing Process
       {
 
 	    MenuBar mb;    Menu m1,m2,m3,m4;
		TextArea ta;
	 	Clipboard clip = getToolkit().getSystemClipboard();
		FileDialog fd;
		String fileName,name,path,n,line;
	
 	 MenuItem nw,opn,sv,ext,ct,cpy,pst,find,rplc;
  	 CheckboxMenuItem bld,itlc;
  	

	public Note()                                 //Constuctor
       {
	
	ta=new TextArea();
	add(ta);
	 
        //TextArea ta= new TextArea();
	

	
	
	mb= new MenuBar();
	m1= new Menu("File");
	m2= new Menu("Edit");
	m3= new Menu("Others");
	m4= new Menu("Help");

	nw= new MenuItem("New");
	opn= new MenuItem("Open");
	ext= new MenuItem("Exit");
	ct= new MenuItem("Cut");
	cpy= new MenuItem("Copy");
	pst= new MenuItem("Paste");
	sv= new MenuItem("Save");
	find= new MenuItem("Find");
	rplc= new MenuItem("Find and Replace");

	//fd=new FileDialog(f,"Save As",FileDialog.SAVE);
    
	nw.addActionListener(new New());

	opn.addActionListener(new Open());

	ext.addActionListener(new Exit());

	ct.addActionListener(new Cut());

	cpy.addActionListener(new Copy());

	pst.addActionListener(new Paste());

	sv.addActionListener(new Save());

	find.addActionListener(new Find());
	//rplc.addActionListener(this);
	

	bld= new CheckboxMenuItem("Bold");	
	bld.addActionListener(new Bold());
	
	ta.setFont(new Font("Serif", Font.BOLD, 14));
	
	

	

	itlc= new CheckboxMenuItem("Italic");
	
        
	itlc.addActionListener(new Italic());


	m3.add(ct);
	m3.add(cpy);
	m3.add(pst);

	m2.add(bld);
	m2.add(itlc);
	m2.addSeparator();
	m2.add(m3);

	
	
	m1.add(nw);
	m1.add(opn);
	m1.add(sv);
	m1.addSeparator();
	m1.add(ext);

	m4.add(find);
	m4.add(rplc);

	mb.add(m1);
	mb.add(m2);
	mb.add(m4);

	

	
         
	GridLayout gl= new GridLayout(3,0);                             //Frame when Clicked on Find
	final Frame f1= new Frame();
	f1.setSize(300,300);
	f1.setLayout(gl);
 
    Label l1= new Label("Find");
	TextField t1= new TextField(20);
	Panel p= new Panel();
	Button b1= new Button("Find Next");
	Button b2= new Button("Close");

	b2.addActionListener( new ActionListener()
	{                                                                // Use of Anonymus Class

	public void actionPerformed(ActionEvent e)
	{
	 f1.setVisible(false);
	 f1.dispose();
	}
	}
	); 

	p.add(b1);
	p.add(b2);
	f1.add(l1);
	f1.add(t1);
	f1.add(p); 

	setMenuBar(mb);   

	mylistener ml= new mylistener();
	addWindowListener(ml);
	
	mylistener m2= new mylistener();
	f1.addWindowListener(m2);

	
	

	
	}


	class mylistener extends WindowAdapter
         {
         public void windowClosing (WindowEvent e)
             {
             System.exit(0);
	}
	 }



     public void actionPerformed(ActionEvent e)                                    // InterFace Method For ActionListener Interface
	{
          String str=e.getActionCommand();
	 	  System.out.print(str+"was clicked");
	
       }
	
    class New implements ActionListener                                            // New Method,By just make textArea blank;
         {
         public void actionPerformed(ActionEvent e)
             {
             ta.setText(" ");
             setTitle(fileName);
         }
     }

 		class Open implements ActionListener                                              //Open Method , use BufferReader to read that file and setText onto the textArea                     
      {
         public void actionPerformed(ActionEvent e)
        {
        FileDialog fd = new FileDialog(Note.this, "select File",FileDialog.LOAD);
        fd.show();
        if (fd.getFile()!=null)
        {
        fileName = fd.getDirectory() + fd.getFile();                              // TO change the Name of the File [ Now required new name which of file ]
         setTitle(fileName);
         ReadFile();                                                                //Read Method Defined Below
        }
         ta.requestFocus();
         }
     }



 void ReadFile()
         {
         BufferedReader d;
         StringBuffer sb = new StringBuffer();
         try
             {
             d = new BufferedReader(new FileReader(fileName));                        // UseBufferReader in FileName 
             String line;
             while((line=d.readLine())!=null)
             sb.append(line + "\n");
             ta.setText(sb.toString());
             d.close();
         }
         catch(FileNotFoundException fe)
             {
             System.out.println("File not Found");
         }
         catch(IOException ioe){}
     }


	  class Save implements ActionListener                                           //Save Method, Use DataOutputStreams
         {
         public void actionPerformed(ActionEvent e)
           {
          FileDialog fd = new FileDialog(Note.this,"Save File",FileDialog.SAVE);
          fd.show();
          if (fd.getFile()!=null)
          {
           fileName = fd.getDirectory() + fd.getFile();
            setTitle(fileName);
           try
            {
         DataOutputStream d = new DataOutputStream(new FileOutputStream(fileName));
        String line = ta.getText();
         BufferedReader br = new BufferedReader(new StringReader(line));
         while((line = br.readLine())!=null)
         {
          d.writeBytes(line + "\r\n");
         d.close();
          }
         }
          catch(Exception ex)
          {
            System.out.println("File not found");
           }
           ta.requestFocus();
         }
         }
         }

	

	class Exit implements ActionListener                            // Exit Method
	{
	public void actionPerformed(ActionEvent e)
	{ 
	
                               
            System.exit(1);
	
	}
	}

	class Find implements ActionListener 
	{
	public void actionPerformed(ActionEvent e)
	{
	
	GridLayout gl= new GridLayout(3,0);                             //Frame when Clicked on Find
	Frame f1= new Frame();
	f1.setSize(300,300);
	f1.setLayout(gl);
	
	
	String line = ta.getText();
	try{
	
	  DataOutputStream d = new DataOutputStream(new FileOutputStream(fileName));
	}
		catch(Exception e2)
	{
	}
        
         BufferedReader br = new BufferedReader(new StringReader(line));
 
        Label l1= new Label("Find");
	TextField t1= new TextField(20);
	Panel p= new Panel();
	Button b1= new Button("Find Next");
	Button b2= new Button("Close");
   final String str1= t1.getText();
	

	


	b1.addActionListener( new ActionListener()                                
	{
	public void actionPerformed(ActionEvent e)
	{
	 //final String str1= t1.getText();
	 String line = ta.getText();	
	Pattern pa= Pattern.compile(str1);
	
	Matcher m=pa.matcher(line);

	if(m.find()==true)
	{
	
         line= (String)line.replaceAll(line, str1); 
	}
	}
	}
	);
	
	

	b2.addActionListener(new ActionListener()
	{                                                                          // Use of Anonymus Class

	public void actionPerformed(ActionEvent e)
	{
	 //f1.setVisible(false);
	//f1.dispose();
	}
	}
	);
	f1.setVisible(true);
	p.add(b1);
	p.add(b2);
	f1.add(l1);
	f1.add(t1);
	f1.add(p);
	}
	}


    class Cut implements ActionListener                           // Cut Method, take selected String in new string pool, clip its end and start 
      {
    public void actionPerformed(ActionEvent e)
      {
      String sel = ta.getSelectedText();
       StringSelection ss = new StringSelection(sel);
       clip.setContents(ss,ss);                                                  // Method in java.DataTransfer Package
      ta.replaceRange(" ",ta.getSelectionStart(),ta.getSelectionEnd());
         }
     }
     
     class Copy implements ActionListener                                     // Copy Method
         {
         public void actionPerformed(ActionEvent e)
        {
        String sel = ta.getSelectedText();
       StringSelection clipString = new StringSelection(sel);
       clip.setContents(clipString,clipString);
         }
     }
     
     class Paste implements ActionListener                                               // Paste Method ,
	
         {
         public void actionPerformed(ActionEvent e)                                        // java.DataTransfer Package
        {
        Transferable cliptran = clip.getContents(Note.this);
         try
	{
                 
         String sel = (String) cliptran.getTransferData(DataFlavor.stringFlavor);
        ta.replaceRange(sel,ta.getSelectionStart(),ta.getSelectionEnd());
             }
        catch(Exception exc)
       {
       System.out.println("not string flavour");
       }
      }
     }

	class Bold implements ActionListener              //To change Letters To BOLD
	{
	public void actionPerformed(ActionEvent e)
	{
	ta.setFont(new Font("Serif", Font.BOLD, 14));
	}	
	}

	class Italic implements ActionListener                  //Italic
	{
	public void actionPerformed(ActionEvent e)
	{

	//selectAll(Note.this);
	ta.setFont(new Font("Serif", Font.ITALIC, 14));
	}	
	}

	
	
	

	public static void main(String args[])
	{
	  
         Frame f = new Note();
         f.setSize(500,400);
         f.setVisible(true);
         f.show();
          
	}

	}
	
      