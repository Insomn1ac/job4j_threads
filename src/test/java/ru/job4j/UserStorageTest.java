package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStorageTest {

    private static class UserStorageThread extends Thread {
        private final UserStorage storage;
        private final User from;
        private final User to;
        private final int amount;

        public UserStorageThread(final UserStorage storage, final User from, final User to, final int amount) {
            this.storage = storage;
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        @Override
        public void run() {
            storage.transfer(from.getId(), to.getId(), amount);
        }
    }

    @Test
    public void whenAdd10UsersIn2ThreadsThenSizeIs10() throws InterruptedException {
        final UserStorage storage = new UserStorage();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                storage.add(new User(i, i * 100));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 5; i < 10; i++) {
                storage.add(new User(i, i * 100));
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertEquals(10, storage.getSize());
    }

    @Test
    public void whenAddUsersInOneThreadAndDeleteInAnother() throws InterruptedException {
        final UserStorage storage = new UserStorage();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                storage.add(new User(i, i * 100));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(250);
                    storage.delete(storage.getUser(i));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertEquals(7, storage.getSize());
    }

    @Test
    public void whenAddUsersInOneThreadAndUpdateInAnother() throws InterruptedException {
        final UserStorage storage = new UserStorage();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                storage.add(new User(i, i * 100));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(10);
                    storage.update(new User(i, i * 40));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertEquals(80, storage.getUser(2).getAmount());
    }

    @Test
    public void whenTransferMoneyBetweenTwoUsers() throws InterruptedException {
        final UserStorage storage = new UserStorage();
        final User user = new User(1, 100);
        final User user2 = new User(2, 200);
        storage.add(user);
        storage.add(user2);
        Thread thread1 = new UserStorageThread(storage, user, user2, 96);
        Thread thread2 = new UserStorageThread(storage, user2, user, 193);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertEquals(197, storage.getUser(user.getId()).getAmount());
        assertEquals(103, storage.getUser(user2.getId()).getAmount());
    }
}