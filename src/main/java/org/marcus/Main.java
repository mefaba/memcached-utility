package org.marcus;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;
import java.io.IOException;
import java.net.InetSocketAddress;
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
        try{
            //test connection
            InetSocketAddress address = AddrUtil.getAddresses(args[0]).get(0);
            // Creating MemcachedClient with the specified address
            MemcachedClient memcachedClient = new MemcachedClient(address);
            Map<String, String> stats = memcachedClient.getStats().get(address);
            int pid = Integer.parseInt(stats.get("pid"));
            if(stats.containsKey("pid") && pid>0){
                System.out.println("Connection to Memcached established successfully!");
            }else{
                System.out.println("Connection failed.");
            }
            // Printing stats
            for (Map.Entry<String, String> stat : stats.entrySet()) {
                System.out.println("  " + stat.getKey() + ": " + stat.getValue());
            }
            memcachedClient.shutdown();
        }
        catch (IOException e){
            System.err.println("Failed to establish connection to Memcached: " + e.getMessage());

        }





    }
}