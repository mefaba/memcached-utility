package org.marcus;
import net.spy.memcached.MemcachedClient;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: java Main <hostname:port>");
            System.exit(1);
        }

        String[] parts = args[0].split(":");
        if (parts.length != 2) {
            System.err.println("Invalid argument format. Please use <hostname:port>.");
            System.exit(1);
        }

        String hostname = parts[0];
        int port = Integer.parseInt(parts[1]);

        // Creating InetSocketAddress
        InetSocketAddress address = new InetSocketAddress(hostname, port);

        // Creating MemcachedClient with the specified address
        MemcachedClient memcachedClient = new MemcachedClient(address);

        //test connection
        Map<SocketAddress, Map<String, String>> stats = memcachedClient.getStats();
        System.out.println("Connection successful!");
    }
}