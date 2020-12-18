package com.example.concurrent.lock;

import java.util.concurrent.Semaphore;

public class SemaphoreSample {

    public static void main(String[] args) {
        Pool pool = new Pool();
        try {
            Object a = pool.getItem();
            Object b = pool.getItem();
            System.out.println(a);
            System.out.println(b);
            pool.putItem(a);
            pool.putItem(b);
        } catch (InterruptedException ex) {

        }
    }

    static class Pool {
        private static final int MAX_AVAILABLE = 100;
        private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

        public Pool() {
            for (int i = 0; i < MAX_AVAILABLE; i++) {
                used[i] = false;
                items[i] = i;
            }
        }

        public Object getItem() throws InterruptedException {
            available.acquire();
            return getNextAvailableItem();
        }

        public void putItem(Object x) {
            if (markAsUnused(x)) {
                available.release();
            }
        }

        protected Object[] items = new Integer[MAX_AVAILABLE];
        protected boolean[] used = new boolean[MAX_AVAILABLE];

        protected synchronized Object getNextAvailableItem() {
            for (int i = 0; i < MAX_AVAILABLE; i++) {
                if (!used[i]) {
                    used[i] = true;
                    return items[i];
                }
            }
            return null;
        }

        protected synchronized boolean markAsUnused(Object item) {
            for (int i = 0; i < MAX_AVAILABLE; i++) {
                if (items[i].equals(item)) {
                    if (used[i]) {
                        used[i] = false;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }
    }
}
