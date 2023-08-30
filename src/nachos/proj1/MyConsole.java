package nachos.proj1;

import nachos.machine.Machine;
import nachos.machine.SerialConsole;
import nachos.threads.Semaphore;

public class MyConsole {
	
	private SerialConsole sc= Machine.console();
	private Semaphore sema = new Semaphore(0);
	private Character temp;
	
	public MyConsole() {
		Runnable receiveInterruptHandler = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				temp = (char)sc.readByte();
				sema.V();
			}
		};
		Runnable sendInterruptHandler =new Runnable() {
			public void run() {
				sema.V();
			}
		};
		// TODO Auto-generated constructor stub
		sc.setInterruptHandlers(receiveInterruptHandler, sendInterruptHandler);
	}
	
	
	public void write(String s) {
		for(int i=0; i<s.length();i++) {
			sc.writeByte(s.charAt(i));
			sema.P();
		}
	}
	
	public void writeLn(String s) {
		write(s+"\n");
	}
	
	public void cls() {
		for(int i = 0; i<20;i++) {
			writeLn("");
		}
	}
	
	public String read() {
		String s = "";
		
		do {
			sema.P();
			if(temp == '\n')break;
			s+=temp;
		}while(true);
		
		
		return s;
	}
	
	
	public void write(Integer i) {
		write(i+"");
	}
	
	public Integer readInt() {
		String s = read();
		Integer i =0;
		try {
			i = Integer.parseInt(s);
		} catch (Exception e) {
			// TODO: handle exception
			i=-1;
		}
		return i;
	}
	
}
