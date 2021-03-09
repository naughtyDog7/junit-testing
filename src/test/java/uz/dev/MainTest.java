package uz.dev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {

    @Test
    public void testLastIdThreadSafety() throws InterruptedException {
        Main main = new Main();

        for (int i = 0; i < 10000; i++) {
            int lastId = main.lastId;
            whenAskForLastIdByTwoThreadsSimultaneously(main);
            int expectedLastId = lastId + 2;

            thenLastIdIncrementedByTwo(main, expectedLastId);
        }
    }

    private void thenLastIdIncrementedByTwo(Main main, int expectedLastId) {
        Assertions.assertEquals(expectedLastId, main.lastId);
    }

    private void whenAskForLastIdByTwoThreadsSimultaneously(Main main) throws InterruptedException {
        Runnable askForLastId = main::getLastId;

        Thread t1 = new Thread(askForLastId);
        Thread t2 = new Thread(askForLastId);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}