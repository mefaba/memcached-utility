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
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress("localhost", 11211));

        //test connection
        Map<SocketAddress, Map<String, String>> stats = memcachedClient.getStats();
        System.out.println("Connection successful!");

        String key = "exampleKey";
        String value = "exampleValue";
        int expirationTimeInSeconds = 3600; // 1 hour
        // Store a value (async) for one hour
        memcachedClient.set(key, expirationTimeInSeconds, value);

        // Retrieve a value.
        String cachedValue = (String)memcachedClient.get(key);
        System.out.println("Cached value: " + cachedValue);
        memcachedClient.shutdown();
    }
}