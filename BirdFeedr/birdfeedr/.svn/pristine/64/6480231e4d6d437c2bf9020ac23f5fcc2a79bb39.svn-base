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
public class Caller {
    
    public static void main(String args[]) throws Exception {
        Populator p = new Populator();
        System.out.println("Starting.");
        Holder h = p.exec();
        Thread.sleep(1000);
        p.exec(); // run while running
        synchronized(h) {
            h.wait();
        }
        System.out.println(h.getData());
    }
}
