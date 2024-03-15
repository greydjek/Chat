package ChatServePacket.server;

import ChatServePacket.auteh.InMemoryAutoServis;

public class App {
    public static void  main(String... args){ new Server(new InMemoryAutoServis()).start();
    }
}
