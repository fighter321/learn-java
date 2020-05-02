package com.example.thread.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author wangxueqing
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

  /**
   * max thread number
   */
  private static final int MAX_WORKER_NUMBERS = 10;

  /**
   * default thread number
   */
  private static final int DEFAULT_WORKER_NUMBERS = 5;

  /**
   * min thread number
   */
  private static final int MIN_WORKER_NUMBERS = 1;

  /**
   * a queue of job
   */
  private final LinkedList<Job> jobs = new LinkedList<>();

  /**
   * a list of workers
   */
  private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

  /**
   * the number of workers
   */
  private int workerNum = DEFAULT_WORKER_NUMBERS;

  /**
   * ID of thread
   */
  private AtomicLong threadNum = new AtomicLong();

  public DefaultThreadPool() {
    initializeWorkers(DEFAULT_WORKER_NUMBERS);
  }

  public DefaultThreadPool(int num){
    workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS ?
        MIN_WORKER_NUMBERS : num;
    initializeWorkers(workerNum);
  }

  private void initializeWorkers(int num){
    for (int i = 0; i < num; i++) {
      Worker worker = new Worker();
      workers.add(worker);
      Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
      thread.start();
    }
  }

  @Override
  public void execute(Job job) {
    if(job != null){
      synchronized (jobs){
        jobs.addLast(job);
        jobs.notify();
      }
    }
  }

  @Override
  public void shutdown() {
    for(Worker worker : workers){
      worker.shutdown();
    }
  }

  @Override
  public void addWorkers(int num) {
    synchronized (jobs){
      if(num + this.workerNum > MAX_WORKER_NUMBERS){
        num = MAX_WORKER_NUMBERS - this.workerNum;
      }
      initializeWorkers(num);
      this.workerNum += num;
    }
  }

  @Override
  public void removeWorker(int num) {
    synchronized (jobs){
      if(num > workerNum){
        throw new IllegalArgumentException("beyond workNum");
      }
      int count = 0;
      while(count < num){
        Worker worker = workers.get(count);
        if(workers.remove(worker)){
          worker.shutdown();
          count++;
        }
      }
      this.workerNum -= count;
    }
  }

  @Override
  public int getJobSize() {
    return jobs.size();
  }

  class Worker implements Runnable {

    /**
     * the state of running
     */
    private volatile boolean running = true;

    @Override
    public void run() {
      while (running) {
        Job job = null;
        synchronized (jobs) {
          while (jobs.isEmpty()){
            try {
              jobs.wait();
            } catch (InterruptedException ex){
              Thread.currentThread().interrupt();
              return;
            }
          }
          // get a job
          job = jobs.removeFirst();
          if(job != null){
            try {
              job.run();
            } catch (Exception ex) {

            }
          }
        }
      }
    }

    public void shutdown(){
      running = false;
    }
  }
}
