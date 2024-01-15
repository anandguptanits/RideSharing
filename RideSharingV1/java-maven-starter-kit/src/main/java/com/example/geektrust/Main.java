package com.example.geektrust;

import com.example.geektrust.command.CommandExecutor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            // the file to be opened for reading
            CommandExecutor commandExecutor=new CommandExecutor();
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
                commandExecutor.executeCommand(sc.nextLine().split(" "));
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }
    }
}
