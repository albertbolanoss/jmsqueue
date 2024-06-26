package org.example;

public class JMSQueueExample {
    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        while (!Thread.currentThread().isInterrupted()) {
            Thread publisherThread = new Thread(new Publisher());
            publisherThread.start();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}
