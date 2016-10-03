 class WindowCloser extends WindowAdapter                     //TO close The screen

	{ 	
          public void WindowClosed(WindowEvent e1)
	{
	 Window w=e1.getWindow();
	 w.setVisible(false);
	 w.dispose();
	System.exit(1);
       }
}

        
	}

