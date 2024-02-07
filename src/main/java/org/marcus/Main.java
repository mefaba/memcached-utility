package org.marcus;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            //TODO
            System.err.println("memcacheUtility.exe <hostname:port>");
            System.err.println("Example: memcacheUtility.exe localhost:11211");
            System.exit(1);
        }

        String[] parts = args[0].split(":");
        if (parts.length != 2) {
            System.err.println("Invalid argument format. Please use <hostname:port>.");
            System.exit(1);
        }
        try{
            UUID uuid = UUID.randomUUID();
            //test connection
            InetSocketAddress address = AddrUtil.getAddresses(args[0]).get(0);
            // Creating MemcachedClient with the specified address
            MemcachedClient memcachedClient = new MemcachedClient(address);
            // Perform a test operation (set a value)
            memcachedClient.set(String.valueOf(uuid), 900, "testValue");

            // Perform a get operation to verify connection
            String recievedValue = (String)memcachedClient.get(String.valueOf(uuid));
            if(!recievedValue.equals("testValue"))
            {
                throw new ConnectException("Failed to establish connection");
            }
            System.out.println("Connection to Memcached established successfully!");

            // Close the client
            memcachedClient.shutdown();
        }
        catch (Exception e){
            System.err.println("Failed to establish connection: ");
            e.printStackTrace();
        }
    }
}