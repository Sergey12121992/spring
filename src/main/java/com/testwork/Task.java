package com.testwork;

import java.util.TreeSet;

public class Task {

    private String task1OrTask2;
    private String substring[];
    private String string[];
    private int number;
    private TreeSet<String> answer = new TreeSet<>();

    public Task(String task, String number){
        task1OrTask2 = task;
        if (number.matches("[-+]?\\d+"))
            this.number = Integer.valueOf(number);
        else
            this.number = 0;
    };

    public Task(String task, String array1, String array2) {
        task1OrTask2 = task;
        this.substring = array1.split(" ");
        this.string = array2.split(" ");
    }

    public void task() {
        if ("task1".equals(task1OrTask2)) {
            task1();
        } else if ("task2".equals(task1OrTask2)) {
            task2();
        }
    }

    private void task1() {
        for (String substr : substring)
            for (String s : string)
                if(s.contains(substr))
                    answer.add(substr);
    }

    private void task2() {
        int num = number;
        int del = 1;
        String s = "";
        while (num != 0) {
            int mod = num % 10;
            num = num / 10;
            if (mod != 0) {
                if (s.isEmpty())
                    s = String.valueOf(mod * del);
                else
                    s = String.valueOf(mod * del) + " + " + s;
            }
            del = del * 10;
        }
        answer.add(s);
    }

    public String getAnswer() {
        return answer.toString();
    }

}
