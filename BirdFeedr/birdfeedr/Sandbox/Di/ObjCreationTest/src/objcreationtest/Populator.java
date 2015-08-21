/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objcreationtest;

/**
 *
 * @author Ditran
 */
public class Populator {

    Holder h;
    
    public Holder exec() throws Exception {
        if (h == null) {
            h = new Holder();
            populate();
            return h;
        }
        throw new Exception("Still running.");
    }
    
    public void populate() {
        new Thread() {
            @Override
            public void run() {
                System.out.println("Run thread called.");
                try {
                    Thread.sleep(3000);
                } catch(Exception e) {
                    System.out.print(e);
                }
                System.out.println("Sleep ended.");
                h.setData("I have data!");
                synchronized(h) {
                    h.notifyAll();
                }
                System.out.println("Thread exiting.");
                h = null;
            }
        }.start();
    }
}
