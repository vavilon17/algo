package com.prinston2.baseball_elimination;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public class BaseballElimination {

    private static class EliminationResult {

        private final boolean isEliminated;
        private final Set<String> certificate;

        public EliminationResult(boolean isEliminated, Set<String> certificate) {
            this.isEliminated = isEliminated;
            this.certificate = certificate;
        }

        public boolean isEliminated() {
            return isEliminated;
        }

        public Set<String> getCertificate() {
            return certificate;
        }
    }

    private final List<String> teams = new ArrayList<>();
    private final Map<String, List<Integer>> teamsData = new HashMap<>();

    public BaseballElimination(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException();
        }
        In in = new In(filename);
        int numberOfTeams = 0;
        while (in.hasNextLine()) {
            if (numberOfTeams == 0) {
                numberOfTeams = Integer.parseInt(in.readLine().trim());
            } else {
                String[] parts = in.readLine().trim().split("\\s+");
                teams.add(parts[0]);

                List<Integer> data = new ArrayList<>(3 + numberOfTeams);
                for (int i = 0; i < 3 + numberOfTeams; i++) {
                    data.add(i, Integer.parseInt(parts[i + 1]));
                }
                teamsData.put(parts[0], data);
            }
        }
    }

    public int numberOfTeams() {
        return teams.size();
    }

    public Iterable<String> teams() {
        return teams;
    }

    public int wins(String team) {
        validateTeams(team);
        return teamsData.get(team).get(0);
    }

    public int losses(String team) {
        validateTeams(team);
        return teamsData.get(team).get(1);
    }

    public int remaining(String team) {
        validateTeams(team);
        return teamsData.get(team).get(2);
    }

    public int against(String team1, String team2) {
        validateTeams(team1, team2);
        int team2Index = teams.indexOf(team2);
        return teamsData.get(team1).get(3 + team2Index);
    }

    public boolean isEliminated(String team) {
        validateTeams(team);
        return runEliminationCheck(team).isEliminated();
    }

    public Iterable<String> certificateOfElimination(String team) {
        validateTeams(team);
        return runEliminationCheck(team).getCertificate();
    }

    private void validateTeams(String... teams) {
        for (String team : teams) {
            if (team == null || !teamsData.containsKey(team)) {
                throw new IllegalArgumentException();
            }
        }
    }

    private EliminationResult runEliminationCheck(String teamX) {
        EliminationResult trivialCheckResult = runTrivialCheck(teamX);
        if (trivialCheckResult.isEliminated()) {
            return trivialCheckResult;
        }

        // building network first
        int theorMaxTeamX = wins(teamX) + remaining(teamX);
        // N - number of teams in the network
        int N = numberOfTeams() - 1;
        // since we do not put to the network teamX, we need to keep aware of original indices
        int[] origTeamIndices = networkIdxToOrigIdx(teamX, N);
        int V = numberOfVertices(N);

        FlowNetwork net = new FlowNetwork(V);
        for (int team1 = 0; team1 < N; team1++) {
            for (int team2 = team1 + 1; team2 < N; team2++) {
                // add edge from s to the game-pair vertex
                int remainingGames = against(origTeamIndices[team1], origTeamIndices[team2]);

                int idxGameLayer = gameLayerIndex(team1, team2, N);
                net.addEdge(new FlowEdge(0, idxGameLayer, remainingGames));
                // add edges from game vertices to team vertices
                net.addEdge(new FlowEdge(idxGameLayer, teamLayerIndex(team1, V, N), Double.MAX_VALUE));
                net.addEdge(new FlowEdge(idxGameLayer, teamLayerIndex(team2, V, N), Double.MAX_VALUE));
            }
            // edge from team vertex to final vertex t
            int winsCurrTeam = wins(teams.get(origTeamIndices[team1]));
            net.addEdge(new FlowEdge(teamLayerIndex(team1, V, N), V - 1, theorMaxTeamX - winsCurrTeam));
        }
        FordFulkerson maxflow = new FordFulkerson(net, 0, net.V() - 1);

        return collectResult(maxflow, origTeamIndices, N, V);
    }

    private EliminationResult runTrivialCheck(String teamX) {
        int theorMax = wins(teamX) + remaining(teamX);
        Set<String> certificate = new HashSet<>();
        for (String team : teams) {
            if (!team.equals(teamX) && wins(team) > theorMax) {
                certificate.add(team);
            }
        }
        if (!certificate.isEmpty()) {
            return new EliminationResult(true, certificate);
        } else {
            return new EliminationResult(false, null);
        }
    }

    private int[] networkIdxToOrigIdx(String teamX, int numberOfTeamsInNetwork) {
        int teamIndex = teams.indexOf(teamX);
        int[] result = new int[numberOfTeamsInNetwork];
        for (int i = 0; i < numberOfTeamsInNetwork; i++) {
            result[i] = (i < teamIndex) ? i : i + 1;
        }
        return result;

    }

    private int numberOfVertices(int N) {
        // number of vertices: 1(s) + 1(t) + REMAINING_TEAMS_PAIRS_AMOUNT + (TEAMS_AMOUNT - 1)
        return 1 + 1 + (N * (N - 1))/2 + N;
    }

    private int gameLayerIndex(int team1Idx, int team2Idx, int N) {
        return (team1Idx * (2*N - 1 - team1Idx))/2 + (team2Idx - team1Idx);
    }

    private int teamLayerIndex(int team1Idx, int V, int N) {
        return V - N - 1 + team1Idx;
    }

    private int against(int team1Idx, int team2Idx) {
        return teamsData.get(teams.get(team1Idx)).get(3 + team2Idx);
    }

    private EliminationResult collectResult(FordFulkerson maxflow, int[] origTeamIndices, int N, int V) {
        boolean isEliminated = false;
        Set<String> certificate = new HashSet<>();
        for (int team1 = 0; team1 < N; team1++) {
            for (int team2 = team1 + 1; team2 < N; team2++) {
                if (maxflow.inCut(gameLayerIndex(team1, team2, N))) {
                    if (maxflow.inCut(teamLayerIndex(team1, V, N))) {
                        certificate.add(teams.get(origTeamIndices[team1]));
                    }
                    if (maxflow.inCut(teamLayerIndex(team2, V, N))) {
                        certificate.add(teams.get(origTeamIndices[team2]));
                    }
                    isEliminated = true;
                }
            }
        }

        if (isEliminated) {
            return new EliminationResult(true, certificate);
        } else {
            return new EliminationResult(false, null);
        }
    }
}
