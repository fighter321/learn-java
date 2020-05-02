package com.example.thread.pool;

/**
 * @author wangxueqing
 */
public interface ThreadPool<Job extends Runnable> {

  /**
   * execure a job
   * @param job
   */
  void execute(Job job);

  /**
   * close the thread pool
   */
  void shutdown();

  /**
   * add the number of workers
   * @param num
   */
  void addWorkers(int num);

  /**
   * remove the number of workers
   * @param num
   */
  void removeWorker(int num);

  /**
   * get the size of waiting for executing
   * @return
   */
  int getJobSize();
}
