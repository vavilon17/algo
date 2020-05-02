package com.prinston2.baseball_elimination;

import com.prinston2.Prinston2Test;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class BaseballEliminationTest extends Prinston2Test {

    @Test
    public void testInitSetup() {
        BaseballElimination unit = new BaseballElimination(getFullPath("baseball_elimination/teams4.txt"));
        assertEquals(4, unit.numberOfTeams());
        assertEquals(80, unit.wins("Philadelphia"));
        assertEquals(79, unit.losses("Philadelphia"));
        assertEquals(3, unit.remaining("Philadelphia"));
        assertEquals(6, unit.against("Atlanta", "New_York"));
        assertEquals(2, unit.against("Philadelphia", "Montreal"));
        assertEquals(0, unit.against("New_York", "Montreal"));

        unit = new BaseballElimination(getFullPath("baseball_elimination/teams24.txt"));
        assertEquals(24, unit.numberOfTeams());
        assertEquals(81, unit.wins("Team17"));
        assertEquals(66, unit.losses("Team0"));
        assertEquals(21, unit.remaining("Team23"));
        assertEquals(4, unit.against("Team21", "Team23"));
        assertEquals(1, unit.against("Team14", "Team0"));
        assertEquals(0, unit.against("Team7", "Team2"));

    }

    @Test
    public void testIsEliminated() {
        BaseballElimination unit = new BaseballElimination(getFullPath("baseball_elimination/teams5.txt"));
        assertFalse(unit.isEliminated("New_York"));
        assertFalse(unit.isEliminated("Baltimore"));
        assertFalse(unit.isEliminated("Boston"));
        assertFalse(unit.isEliminated("Toronto"));
        assertTrue(unit.isEliminated("Detroit"));
        checkCertificate(unit.certificateOfElimination("Detroit"), "New_York", "Baltimore",
                "Boston", "Toronto");


        unit = new BaseballElimination(getFullPath("baseball_elimination/teams4.txt"));
        assertFalse(unit.isEliminated("Atlanta"));
        assertTrue(unit.isEliminated("Philadelphia"));
        checkCertificate(unit.certificateOfElimination("Philadelphia"), "Atlanta", "New_York");
        assertFalse(unit.isEliminated("New_York"));
        assertTrue(unit.isEliminated("Montreal"));
        checkCertificate(unit.certificateOfElimination("Montreal"), "Atlanta");

        unit = new BaseballElimination(getFullPath("baseball_elimination/teams4a.txt"));
        assertTrue(unit.isEliminated("Ghaddafi"));

        unit = new BaseballElimination(getFullPath("baseball_elimination/teams7.txt"));
        assertTrue(unit.isEliminated("Ireland"));

        unit = new BaseballElimination(getFullPath("baseball_elimination/teams24.txt"));
        assertTrue(unit.isEliminated("Team13"));

        unit = new BaseballElimination(getFullPath("baseball_elimination/teams54.txt"));
        assertTrue(unit.isEliminated("Team3"));
        assertTrue(unit.isEliminated("Team29"));
        assertTrue(unit.isEliminated("Team37"));
        assertTrue(unit.isEliminated("Team50"));
    }

    @Ignore
    @Test
    public void run() {
        runForFile("baseball_elimination/teams5.txt");
        runForFile("baseball_elimination/teams4.txt");
        runForFile("baseball_elimination/teams54.txt");
    }

    private void runForFile(String path) {
        StdOut.println("Run for " + path);
        BaseballElimination division = new BaseballElimination(getFullPath(path));
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
        StdOut.println("----");
    }

    private void checkCertificate(Iterable<String> actual, String... expected) {
        Set<String> expectedSet = new HashSet<>(expected.length);
        Set<String> actualSet = new HashSet<>();
        Collections.addAll(expectedSet, expected);
        for (String item : actual) {
            actualSet.add(item);
        }
        assertEquals(actualSet.size(), expectedSet.size());
        for (String item : expectedSet) {
            assertTrue(actualSet.contains(item));
        }
    }
}
