package com.maokg;

import jpcap.packet.DatalinkPacket;
import jpcap.packet.EthernetPacket;
import jpcap.packet.ICMPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

public class DataLinkLayer implements jpcap.PacketReceiver {
    String protocoll[] = {"HOPOPT", "ICMP", "IGMP", "GGP", "IPV4", "ST", "TCP", "CBT", "EGP", "IGP", "BBN", "NV2", "PUP", "ARGUS", "EMCON", "XNET", "CHAOS", "UDP", "mux"};

    @Override
    public void receivePacket(Packet packet) {

        boolean show_tcp = false, show_icmp = true, show_udp = false;

        IPPacket tpt=(IPPacket)packet;
        if (packet != null) {
            int ppp=tpt.protocol;
            String proto=protocoll[ppp];

            if (proto.equals(("TCP")) && show_tcp) {
                System.out.println("\nthis is TCP packet");
                TCPPacket tp = (TCPPacket) packet;
                System.out.println("this is destination port of tcp :" + tp.dst_port);
                if (tp.ack) {
                    System.out.println("\n" + "this is an acknowledgement");
                } else {
                    System.out.println("this is not an acknowledgment packet");
                }

                if (tp.rst) {
                    System.out.println("reset connection ");
                }
                System.out.println(" \n protocol version is :" + tp.version);
                System.out.println("\n this is destination ip " + tp.dst_ip);
                System.out.println("this is source ip"+tp.src_ip);
                if(tp.fin){
                    System.out.println("sender does not have more data to transfer");
                }
                if(tp.syn){
                    System.out.println("\n request for connection");
                }

            }else if(proto.equals("ICMP") && show_icmp){
                ICMPPacket ipc=(ICMPPacket)packet;

                System.out.println("\nThis ICMP Packet");
                System.out.println(" \n this is alive time :"+ipc.alive_time);
                System.out.println("\n number of advertised address :"+(int)ipc.addr_num);
                System.out.println("mtu of the packet is :"+(int)ipc.mtu);
                System.out.println("subnet mask :"+ipc.subnetmask);
                System.out.println("\n source ip :"+ipc.src_ip);
                System.out.println("\n destination ip:"+ipc.dst_ip);
                System.out.println("\n check sum :"+ipc.checksum);
                System.out.println("\n icmp type :"+ipc.type);
                System.out.println("");
            }
            else if(proto.equals("UDP")  && show_udp){
                UDPPacket pac=(UDPPacket)packet;
                System.out.println("this is udp packet \n");
                System.out.println("this is source port :"+pac.src_port);
                System.out.println("this is destination port :"+pac.dst_port);

            }
        }
        else{
            System.out.println("dft bi is not set. packet will  be fragmented \n");
        }
    }
}