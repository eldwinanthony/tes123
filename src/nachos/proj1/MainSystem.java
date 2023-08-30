package nachos.proj1;

import nachos.machine.Machine;
import nachos.machine.Timer;

public class MainSystem {
	
	MyConsole mc = new MyConsole();
	MyNetworkLink mn = new MyNetworkLink();
	
	public MainSystem() {
		// TODO Auto-generated constructor stub
		
		Integer choose = 0;
		
		//timer
		Timer t = Machine.timer();
		Runnable handler = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		};
		t.setInterruptHandler(handler);
		
		
		
		do {
			mc.cls();
			mc.writeLn("Whatsap" + mn.getId());
			mc.writeLn("1. chat");
			mc.writeLn("2. read");
			mc.writeLn("3. exit");
			mc.writeLn("choose = ");
			choose = mc.readInt();
			
			switch(choose) {
				case 1:{
					mc.write("Input destination: ");
					Integer des = mc.readInt();
					mc.write("Input message: ");
					String msg = mc.read();
					while(msg.length()<5 || msg.length()>20) {
						mc.writeLn("salah");
						mc.write("Input message: ");
						msg = mc.read();
					}
					break;
				}
				
				case 2:{
					mc.writeLn("Recent Message = " + mn.receive());
					mc.read();
					break;
				}
			}
			
		}while(choose != 3);
		mc.writeLn("Shutting down..");
		mc.writeLn("Ticks of time:"+t.getTime()+"");
	}

}
