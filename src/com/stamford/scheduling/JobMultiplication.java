package com.stamford.scheduling;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 08.09.13
 */
public class JobMultiplication extends Job {

    public JobMultiplication(int weight, int length) {
        super(weight, length);
    }

    public JobMultiplication(int id, int weight, int length) {
        super(id, weight, length);
    }

    @Override
    public Integer calcPriority() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int compareTo(Job o) {
        Double priority1 = (double) o.getWeight()/o.getLength();
        Double priority2 = (double) this.getWeight()/this.getLength();
        return priority1.compareTo(priority2);
    }
}
