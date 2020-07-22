package com.srsbsns.adder.io;

import com.srsbsns.adder.math.IResult;

public class ResultPrinter {
    private IResult result;

    public ResultPrinter(IResult result) {
        this.result = result;
    }

    private void printPreMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("+================================================+\n");
        sb.append("| ENTERPRISE ADDER 2016 (C) 2016 ENTERPRISE CORP |\n");
        sb.append("+================================================+\n\n");
        System.out.println(sb.toString());
    }

    private void printPostMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n+================================================+\n");
        sb.append("|       THANK YOU! HAVE A NICE DAY! <3 EC        |\n");
        sb.append("|   P.S. REMEMBER TO SYNERGIZE YOUR OVALTINE     |\n");
        sb.append("+================================================+\n");
        System.out.println(sb.toString());
    }

    public void printResults() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(result.getLeftTerm()));
        sb.append(" + ");
        sb.append(String.valueOf(result.getRightTerm()));
        sb.append(" = ");
        sb.append(String.valueOf(result.getResult()));
        printPreMessage();
        System.out.println(sb.toString());
        printPostMessage();
    }
}
