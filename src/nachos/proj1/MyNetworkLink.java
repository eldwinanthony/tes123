package nachos.proj1;

import nachos.machine.Machine;
import nachos.machine.MalformedPacketException;
import nachos.machine.NetworkLink;
import nachos.machine.Packet;
import nachos.threads.Semaphore;

public class MyNetworkLink {
	
	private NetworkLink nl = Machine.networkLink();
	private Semaphore sema = new Semaphore(0);
	
	public MyNetworkLink() {
		// TODO Auto-generated constructor stub
		
		Runnable receiveInterruptHandler =new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sema.V();
			}
		};
		Runnable sendInterruptHandler=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sema.V();
			}
		};
		nl.setInterruptHandlers(receiveInterruptHandler, sendInterruptHandler);
	}
	
	public void send(String msg, Integer des) {
		try {
			Packet p = new Packet(des, getId(), msg.getBytes());
			nl.send(p);
			sema.P();
		} catch (MalformedPacketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String receive() {
		sema.P();
		Packet p = nl.receive();
		return new String(p.contents);
	}
	
	public Integer getId() {
		return nl.getLinkAddress();
	}

}
