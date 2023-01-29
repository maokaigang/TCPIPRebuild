package com.maokg;

import java.io.IOException;
import java.net.Inet4Address;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
import jpcap.PacketReceiver;
import jpcap.packet.Packet;
public class ProtocolEntry implements PacketReceiver {
	public void receivePacket(Packet packet) {
		System.out.println(packet);
		System.out.println("Receive a packet");
	}
	public static void main(String[] args) throws IOException {
		//获取网卡列表
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		NetworkInterface device = null;

		System.out.println("there are " + devices.length +  " devices");

		for (int i = 0; i < devices.length; i++) {
			boolean findDevice = false;
			System.out.println(i+": "+devices[i].name + "(" + devices[i].description + ")");
			for (NetworkInterfaceAddress addr  : devices[i].addresses) {
				//网卡网址符合ipv4规范才是可用网卡
				if (!(addr.address instanceof Inet4Address)) {
					continue;
				}
				findDevice = true;
				break;
			}

			if (findDevice) {
				device = devices[i];
				break;
			}
		}
		JpcapCaptor jpcap = JpcapCaptor.openDevice(device, 2000, true, 20);
		jpcap.loopPacket(-1, (jpcap.PacketReceiver) new DataLinkLayer());
//	    for (int i = 0; i < devices.length; i++) {
//	    	//显示网卡名字
//	        System.out.println(i+": "+devices[i].name + "(" + devices[i].description + ")");
//			JpcapCaptor jpcap = JpcapCaptor.openDevice(devices[i], 2000, true, 20);
//
//			jpcap.loopPacket(-1, (jpcap.PacketReceiver) new DataLinkLayer());
//	        System.out.println(" datalink: " + devices[i].datalink_name + "(" + devices[i].datalink_description + ")");
//
//	        System.out.println(" Mac Address: ");
//	        for (byte b : devices[i].mac_address) {
//	            System.out.print(Integer.toHexString(b & 0xff) + ":");
//	        }
//
//	        System.out.println();
//
//	        for (NetworkInterfaceAddress a : devices[i].addresses) {
//	            System.out.println(" address:" + a.address + " " + a.subnet + " " + a.broadcast);
//	        }
//
//	        captor = JpcapCaptor.openDevice(devices[i], 65536, false, 20);
//	        if (captor != null) {
//	            System.out.println("Open captor on device" + i);
//	            break;
//	        }
//
//	    }
	    
	   
	}
}
