package com.back;

import com.back.domain.constant.GuideMsg;
import com.back.domain.system.controller.SystemController;

import java.util.Scanner;

public class App {
    SystemController systemController;
    public void run() {
        Scanner sc = new Scanner(System.in);
        systemController = new SystemController(sc);

        System.out.print(GuideMsg.INTRO.getValue());
        systemController.run();
    }
}
