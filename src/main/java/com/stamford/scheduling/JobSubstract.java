package com.stamford.scheduling;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 08.09.13
 */
public class JobSubstract extends Job {

    public JobSubstract(int weight, int length) {
        super(weight, length);
    }

    public JobSubstract(int id, int weight, int length) {
        super(id, weight, length);
    }

    @Override
    public int compareTo(Job o) {
        int res = o.calcPriority().compareTo(this.calcPriority());
        if (res == 0) {
            res = Integer.valueOf(o.getWeight()).compareTo(Integer.valueOf(this.getWeight()));
        }
        return res;
    }

    @Override
    public Integer calcPriority() {
        return this.getWeight() - this.getLength();
    }
}
