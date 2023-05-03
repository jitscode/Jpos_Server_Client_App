package com.example.server_app;

import java.io.*;
import org.jpos.iso.*;
import org.jpos.util.*;
import org.jpos.iso.channel.*;
import org.jpos.iso.packager.*;
public class Server implements ISORequestListener {
    public Server() {
    super();
}
    public boolean process (ISOSource source, ISOMsg m) {
        try {
        m.setResponseMTI ();
        m.set (39, "00");
        source.send (m);
    }
    catch (ISOException e)
    {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
        return true;
}
    public static void main (String[] args) throws Exception {
        Logger logger = new Logger ();
        logger.addListener (new SimpleLogListener (System.out));
        ServerChannel channel = new ASCIIChannel(new EuroPackager());
        ((LogSource)channel).setLogger (logger, "channel");
        ISOServer server = new ISOServer (10000, channel, null);
        server.setLogger (logger, "server");
        server.addISORequestListener (new Server());
        new Thread (server).start ();
}
}

