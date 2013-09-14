package com.stamford

import com.stamford.scheduling.Schedule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 08.09.13
 */
@RunWith(JUnit4.class)
class ScheduleTest {

    Schedule program = new Schedule()

    @Test
    public void schedTest() {
        String filePath = "resources/stamford/test/ass21/jobsTest3"
        assert program.startSubstractScheduling(filePath) == 15
    }

    @Test
    public void substrForumTests() {
        String filePath = "resources/stamford/test/ass21/jobsTest4"
        assert program.startSubstractScheduling(filePath) == 40135
    }

    @Test
    public void multiplicationForumTests() {
        String filePath = "resources/stamford/test/ass21/jobsTest4"
        assert program.startMultiplicationScheduling(filePath) == 38638
    }

}
