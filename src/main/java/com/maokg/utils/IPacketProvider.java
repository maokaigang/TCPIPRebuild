package com.maokg.utils;

public interface IPacketProvider {
	public void registerPacketReceiver(jpcap.PacketReceiver receiver);
}
