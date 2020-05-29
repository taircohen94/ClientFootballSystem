package Client;

import Client.Strategies.ClientSender;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Set;

public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private ClientSender clientStrategy;
   // private Socket theServer;

    public Client(InetAddress serverIP, int serverPort, ClientSender clientStrategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.clientStrategy = clientStrategy;
    }

    public void start() {
        try {
      //      theServer = new Socket(serverIP, serverPort);
            System.out.println(String.format("Client is connected to server (IP: %s, port: %s)", serverIP, serverPort));
//            clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String login(String userName, String password) {
        String ans = null;
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            clientStrategy.setRequest(new StringBuilder("Login,"+userName+","+password));
            ans = clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public String getAllTeams() {
        String ans = null;
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            clientStrategy.setRequest(new StringBuilder("getAllTeams,"));
            ans = clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public String getAllSeasons() {
        String ans = null;
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            clientStrategy.setRequest(new StringBuilder("getAllSeasons,"));
            ans = clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public String getAllLeagues() {
        String ans = null;
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            clientStrategy.setRequest(new StringBuilder("getAllLeagues,"));
            ans = clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public String getAllFields() {
        String ans = null;
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            clientStrategy.setRequest(new StringBuilder("getAllFields,"));
            ans = clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }


    public String availableSeasonsForTeam(String teamName) {
        String ans = null;
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            clientStrategy.setRequest(new StringBuilder("availableSeasonsForTeam,"+teamName));
            ans = clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public String createTeam(String teamName, String league, String season, String field) {
        String ans = null;
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            StringBuilder req = new StringBuilder();
            req.append("createTeam,");
            req.append(teamName);
            req.append(",");
            req.append(league);
            req.append(",");
            req.append(season);
            req.append(",");
            req.append(field);
            clientStrategy.setRequest(req);
            ans = clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
