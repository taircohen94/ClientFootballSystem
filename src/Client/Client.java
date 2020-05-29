package Client;

import Client.Strategies.ClientSender;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

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
            clientStrategy.setRequest(new StringBuilder("Login," + userName + "," + password));
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
            clientStrategy.setRequest(new StringBuilder("availableSeasonsForTeam," + teamName));
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
            StringBuilder req = getStringBuilderEditTeamProp(teamName, league, "createTeam,");
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

    public String getCoachesForTeamAtSeason(String teamName, String seasonYear) {
        String ans = null;
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            StringBuilder req = getStringBuilderEditTeamProp(teamName, seasonYear, "getCoachesForTeamAtSeason,");
            clientStrategy.setRequest(req);
            ans = clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public String getTeamManagersForTeamAtSeason(String teamName, String seasonYear) {
        String ans = null;
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            StringBuilder req = getStringBuilderEditTeamProp(teamName, seasonYear, "getTeamManagersForTeamAtSeason,");
            clientStrategy.setRequest(req);
            ans = clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public String getPlayersForTeamAtSeason(String teamName, String seasonYear) {
        String ans = null;
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            StringBuilder req = getStringBuilderEditTeamProp(teamName, seasonYear, "getPlayersForTeamAtSeason,");
            clientStrategy.setRequest(req);
            ans = clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    private StringBuilder getStringBuilderEditTeamProp(String teamName, String seasonYear, String s) {
        StringBuilder req = new StringBuilder();
        req.append(s);
        req.append(teamName);
        req.append(",");
        req.append(seasonYear);
        return req;
    }

    public String getFieldsForTeamAtSeason(String teamName, String seasonYear) {
        String ans = null;
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            StringBuilder req = getStringBuilderEditTeamProp(teamName, seasonYear, "getFieldsForTeamAtSeason,");
            clientStrategy.setRequest(req);
            ans = clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public String editCoachDetails(String teamName, String seasonYear, String coachUserName,
                                   String coachFirstName, String coachLastName, String training, String role) {
        String ans = null;
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            StringBuilder req = new StringBuilder();
            req.append("editCoachDetails,");
            req.append(",");
            req.append(teamName);
            req.append(",");
            req.append(seasonYear);
            req.append(",");
            req.append(coachUserName);
            req.append(",");
            req.append(coachFirstName);
            req.append(",");
            req.append(coachLastName);
            req.append(",");
            req.append(training);
            req.append(",");
            req.append(role);
            clientStrategy.setRequest(req);
            ans = clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
